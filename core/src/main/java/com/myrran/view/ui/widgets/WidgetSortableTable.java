package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.util.*;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public abstract class WidgetSortableTable<T> extends Table
{
    private Table optionsTable;
    private SortOptions options;
    private boolean showDetails = false;
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText reverseOrderText;
    private Map<String, SortOptions> sortMap = new HashMap<>();

    private WidgetText name;
    private Collection<T>modelData;
    private List<Actor> list;

    private static final String ASC = "Asc";
    private static final String DESC = "Desc";
    private static final String SHOW = "Show Details";
    private static final String HIDE = "Hide Details";
    private static final Color selectedSort = Color.ORANGE;
    private static final Color unselectedSort = Color.GRAY;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public void build(String text, boolean movable)
    {
        name            = new WidgetText(text, font20, Color.WHITE, Color.BLACK, 2);
        reverseOrderText= new WidgetText(DESC, Atlas.get().getFont("10"), Color.WHITE, Color.BLACK, 1);
        showDetailsText = new WidgetText(SHOW, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        reverseOrderText.addListener(new TouchDownListener(o -> setSortOption(options.text)));
        showDetailsText.addListener(new TouchDownListener(o -> setShowDetails()));

        if (movable)
            name.addListener(new ActorMoveListener(this));

        createOptionsLayout();
    }

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    public void addSortOption(String text, Comparator<T>comparator)
    {
        SortOptions options = new SortOptions(text, comparator);

        if (sortMap.isEmpty())
            setSortOption(options);

        sortMap.put(text, options);
    }

    public void setShowDetails()
    {
        showDetails = !showDetails;
        showDetailsText.setText(showDetails? HIDE : SHOW);
        createLayout();
    }

    public void setSortOption(String optionName)
    {
        if (options.text.equals(optionName))
            setReverseOrder(!reverseOrder);

        setSortOption(sortMap.get(optionName));
        createLayout();
    }

    private void setSortOption(SortOptions newOptions)
    {
        if (options != null)
            options.widgetText.setTextColor(unselectedSort);
        options = newOptions;
        options.widgetText.setTextColor(selectedSort);
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
            .sorted(Comparator.comparing(options -> options.insertOrder))
            .forEach(options -> optionsTable.add(options.widgetText));

        optionsTable.add(reverseOrderText);
        optionsTable.row();
    }

    // GLOBAL LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public void createLayout(Collection<T>data)
    {
        modelData = data;
        createLayout();
    }

    private void createLayout()
    {
        list = modelData.stream()
            .sorted(reverseOrder ? options.comparator.reversed() : options.comparator)
            .map(this::getIcon)
            .collect(Collectors.toList());

        list.stream()
            .filter(DetailedActorI.class::isInstance)
            .map(DetailedActorI.class::cast)
            .forEach(actor -> actor.showDetails(showDetails));

        clear();
        top().left();
        add(name).left().padBottom(-8).padTop(-8).row();
        add(optionsTable).left().row();

        for (Actor icon: list)
            add(icon).left().row();
    }

    public abstract Actor getIcon(T model);


    // SORT COMPARATORS:
    //--------------------------------------------------------------------------------------------------------

    public class SortOptions
    {
        int insertOrder;
        String text;
        WidgetText widgetText;
        Comparator<T> comparator;

        SortOptions(String text, Comparator<T>comparator)
        {
            this.text = text;
            this.comparator = comparator;
            this.insertOrder = sortMap.size();

            widgetText = new WidgetText(text, Atlas.get().getFont("10"), unselectedSort, Color.BLACK, 1);
            widgetText.addListener(new TouchDownListener( o -> setSortOption(text)));
        }
    }
}