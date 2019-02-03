package com.myrran.view.ui.customspell.spellbook;

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
import com.myrran.view.ui.widgets.DetailedActorI;
import com.myrran.view.ui.widgets.WidgetText;

import java.util.*;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public abstract class SortableTable<T> extends Table implements Disposable
{
    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    private Table optionsTable;
    private SortOptions actualSortOptions;
    private boolean detailsVisible = false;
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText reverseOrderText;
    private Map<String, SortOptions> sortMap = new HashMap<>();

    // CONTENT:
    //--------------------------------------------------------------------------------------------------------

    private ScrollPane scrollPane;
    private float scrollPaneWidth = 0;
    private float scrollPaneHeight = 0;
    private Table contentTable;
    private WidgetText name;
    private Map<T, Actor> modelToActorMap = new HashMap<>();
    private List<Actor> sortedActors;

    private static final String ASC = "Asc";
    private static final String DESC = "Desc";
    private static final String SHOW = "Show Details";
    private static final String HIDE = "Hide Details";
    private static final Color selectedSort = Color.ORANGE;
    private static final Color unselectedSort = Color.GRAY;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    protected void build(String text, boolean movable)
    {   build(text, movable, 0, 0); }

    protected void build(String text, boolean movable, float width, float height)
    {
        name            = new WidgetText(text, font20, Color.WHITE, Color.BLACK, 2);
        reverseOrderText= new WidgetText(DESC, Atlas.get().getFont("10"), Color.WHITE, Color.BLACK, 1);
        showDetailsText = new WidgetText(SHOW, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        contentTable    = new Table();

        reverseOrderText.addListener(new TouchDownListener(o -> setSortOption(actualSortOptions.text)));
        showDetailsText.addListener(new TouchDownListener(o -> setShowDetails()));

        if (width != 0 || height != 0)
        {
            scrollPane = new ScrollPane(contentTable);
            scrollPane.setTouchable(Touchable.childrenOnly);
            scrollPaneWidth = width;
            scrollPaneHeight = height;
        }

        if (movable)
            name.addListener(new ActorMoveListener(this));

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

    private void setShowDetails()
    {
        detailsVisible = !detailsVisible;
        showDetailsText.setText(detailsVisible ? HIDE : SHOW);
        showDetails();
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
        optionsTable = new Table().top().left();
        optionsTable.add(showDetailsText).minWidth(80).bottom().left();

        sortMap.values().stream()
            .sorted(Comparator.comparing(sortOption -> sortOption.insertOrder))
            .forEach(sortOption -> optionsTable.add(sortOption.widgetText));

        optionsTable.add(reverseOrderText);
        optionsTable.row();
    }

    // CONTENT LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public void createLayout()
    {
        clear();
        top().left();
        add(name).top().left().padBottom(-8).padTop(-8).row();
        add(optionsTable).top().left().row();
        add(scrollPane == null ? contentTable : scrollPane).size(scrollPaneWidth, scrollPaneHeight).top().left().row();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(Collection<T>data)
    {
        data.forEach(model -> modelToActorMap.put(model, getActor(model)));

        sortModel();
        showDetails();
    }

    private void sortModel()
    {
        contentTable.clear();
        contentTable.top().left();
        sortedActors = modelToActorMap.keySet().stream()
            .sorted(reverseOrder ? actualSortOptions.comparator.reversed() : actualSortOptions.comparator)
            .map(t -> modelToActorMap.get(t))
            .collect(Collectors.toList());

        sortedActors.forEach(actor -> contentTable.add(actor).fillX().expandX().row());
    }

    private void showDetails()
    {
        sortedActors.stream()
            .filter(DetailedActorI.class::isInstance)
            .map(DetailedActorI.class::cast)
            .forEach(actor -> actor.showDetails(detailsVisible));
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
