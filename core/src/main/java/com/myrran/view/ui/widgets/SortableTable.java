package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.util.*;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public abstract class SortableTable<T> extends DetailedTable implements Disposable
{
    private Table tableContent;

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    private SortOptions actualSortOptions;
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText closeDetailsText;
    private WidgetText reverseOrderText;
    private Map<String, SortOptions> sortMap = new HashMap<>();
    private WidgetImage minimize;
    private WidgetImage maximize;
    private Table sortOptionsTable = new Table().bottom().left();

    // CONTENT:
    //--------------------------------------------------------------------------------------------------------

    private ScrollPane scrollPane;
    private WidgetText name;
    private Map<T, Actor> modelToActorMap = new HashMap<>();
    private List<Actor> sortedActors;

    private static final String ASC = "Asc";
    private static final String DESC = "Desc";
    private static final String SHOW = "Expand";
    private static final String HIDE = "Close";
    private static final Color selectedSort = Color.ORANGE;
    private static final Color unselectedSort = Color.GRAY;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected void build(String text, boolean movable)
    {   build(text, movable, 0, 0); }

    protected void build(String text, boolean movable, float width, float height)
    {
        tableContent    = new Table().top().left();
        name            = new WidgetText(text, font20, Color.WHITE, Color.BLACK, 3);
        reverseOrderText= new WidgetText(DESC, Atlas.get().getFont("10"), Color.WHITE, Color.BLACK, 1);
        showDetailsText = new WidgetText(SHOW, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        closeDetailsText= new WidgetText(HIDE, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        minimize        = new WidgetImage(Atlas.get().getTexture("TexturasMisc/RebindOff"));
        maximize        = new WidgetImage(Atlas.get().getTexture("TexturasMisc/RebindOff"));

        tableDetails.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine2", 0.45f));
        tableHeader.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.90f));
        sortOptionsTable.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.90f));

        reverseOrderText.addListener(new TouchDownListener(event -> setSortOption(actualSortOptions.text)));
        showDetailsText.addListener(new TouchDownListener(event -> setShowDetails(true)));
        closeDetailsText.addListener(new TouchDownListener(event -> setShowDetails(false)));
        minimize.addListener(new TouchDownListener(event -> showDetails()));

        if (width != 0 || height != 0)
        {
            scrollPane = new ScrollPane(tableContent);
            tableDetails.add(sortOptionsTable).expand().fillX().left().row();
            tableDetails.add(scrollPane).size(width, height);
        }
        else
        {
            tableDetails.add(sortOptionsTable).expand().fillX().left().row();
            tableDetails.add(tableContent);
        }

        if (movable)
        {
            tableHeader.setTouchable(Touchable.enabled);
            tableHeader.addListener(new ActorMoveListener(this));
        }

        createOptionsLayout();
        createLayout();
    }

    @Override public void dispose()
    {
        if (sortedActors != null)
            sortedActors.stream()
                .filter(Disposable.class::isInstance)
                .map(Disposable.class::cast)
                .forEach(Disposable::dispose);
    }

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    protected void addSortOption(String text, Comparator<T>comparator)
    {
        SortOptions option = new SortOptions(text, comparator);

        if (sortMap.isEmpty())
            setSortOption(option);

        sortMap.put(text, option);
    }

    private void setSortOption(String optionName)
    {
        if (actualSortOptions.text.equals(optionName))
            setReverseOrder(!reverseOrder);

        setSortOption(sortMap.get(optionName));
        sortModel();
    }

    private void setSortOption(SortOptions newOptions)
    {
        if (actualSortOptions != null)
            actualSortOptions.widgetText.setTextColor(unselectedSort);
        actualSortOptions = newOptions;
        actualSortOptions.widgetText.setTextColor(selectedSort);
    }

    private void setReverseOrder(boolean reverse)
    {
        reverseOrder = reverse;
        reverseOrderText.setText(reverseOrder ? ASC : DESC);
    }

    // OPTIONS LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createOptionsLayout()
    {
        sortOptionsTable.add(showDetailsText).padLeft(3).bottom().left();
        sortOptionsTable.add(closeDetailsText).bottom().left();

        sortMap.values().stream()
            .sorted(Comparator.comparing(sortOption -> sortOption.insertOrder))
            .forEach(sortOption -> sortOptionsTable.add(sortOption.widgetText).bottom().left());

        sortOptionsTable.add(reverseOrderText).bottom().left();

        Table header = new Table().top().left();

        header.add(name).padLeft(5).left();
        header.add(minimize).expand().padTop(2).right();
        header.add(maximize).right().padTop(2).row();

        tableHeader.add(header).minWidth(250).left().row();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(Collection<T>data)
    {
        dispose();
        modelToActorMap.clear();

        if (data != null)
            data.forEach(model -> modelToActorMap.put(model, getActor(model)));

        sortModel();
        setShowDetails(false);
    }

    private void sortModel()
    {
        tableContent.clear();
        tableContent.top().left();
        sortedActors = modelToActorMap.keySet().stream()
            .sorted(reverseOrder ? actualSortOptions.comparator.reversed() : actualSortOptions.comparator)
            .map(t -> modelToActorMap.get(t))
            .collect(Collectors.toList());

        sortedActors.forEach(actor -> tableContent.add(actor).fillX().expandX().row());
    }

    private void setShowDetails(boolean visible)
    {
        sortedActors.stream()
            .filter(DetailedActorI.class::isInstance)
            .map(DetailedActorI.class::cast)
            .forEach(actor -> actor.showDetails(visible));
    }

    public abstract Actor getActor(T model);

    // SORT COMPARATORS:
    //--------------------------------------------------------------------------------------------------------

    public class SortOptions
    {
        int insertOrder;
        String text;
        WidgetText widgetText;
        Comparator<T> comparator;

        SortOptions(String sortName, Comparator<T>sortComparator)
        {
            text = sortName;
            comparator = sortComparator;
            insertOrder = sortMap.size();

            widgetText = new WidgetText(sortName, Atlas.get().getFont("10"), unselectedSort, Color.BLACK, 1);
            widgetText.addListener(new TouchDownListener( o -> setSortOption(sortName)));
        }
    }
}
