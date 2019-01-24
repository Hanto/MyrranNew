package com.myrran.view.ui.sortabletable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.widgets.WidgetText;

import java.util.*;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public abstract class SortableTableView<T> extends Table
{
    protected Table optionsTable;
    private SortBy options;
    private boolean showDetails = false;
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText reverseOrderText;
    private Map<String, SortBy> sortMap = new HashMap<>();

    protected WidgetText name;
    protected Collection<T>modelData;
    protected List<Actor> list;

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
        reverseOrderText.addListener(new TouchDownListener( o -> setSortBy(options.text)));
        showDetailsText.addListener(new TouchDownListener(o -> setShowDetails()));

        if (movable)
            name.addListener(new ActorMoveListener(this));

        createOptionsLayout();
    }

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    private void createOptionsLayout()
    {
        optionsTable = new Table().top().left();
        optionsTable.add(showDetailsText).minWidth(80).bottom().left();

        sortMap.values().stream()
            .sorted(Comparator.comparing(sortBy -> sortBy.insertOrder))
            .forEach(sortBy -> optionsTable.add(sortBy.widgetText));

        optionsTable.add(reverseOrderText);
        optionsTable.row();
    }

    private void setShowDetails()
    {
        showDetails = !showDetails;
        showDetailsText.setText(showDetails? HIDE : SHOW);
        createLayout();
    }

    private void setSortBy(String sort)
    {
        if (options.text.equals(sort))
            reverseOrder = !reverseOrder;

        reverseOrderText.setText(reverseOrder ? ASC : DESC);

        options.widgetText.setTextColor(unselectedSort);
        options = sortMap.get(sort);
        options.widgetText.setTextColor(selectedSort);
        createLayout();
    }

    private void setOptions(SortBy newOptions)
    {   options = newOptions; options.widgetText.setTextColor(selectedSort); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void createLayout(Collection<T>data)
    {
        modelData = data;
        createLayout();
    }

    public void createLayout()
    {
        top().left();
        clear();

        list = modelData.stream()
            .sorted(reverseOrder ? options.comparator.reversed() : options.comparator)
            .map(this::getIcon)
            .collect(Collectors.toList());

        list.stream()
            .filter(DetailedActorI.class::isInstance)
            .map(DetailedActorI.class::cast)
            .forEach(actor -> actor.showDetails(showDetails));

        add(name).left().padBottom(-8).padTop(-8).row();
        add(optionsTable).left().row();

        for (Actor icon: list)
            add(icon).left().row();
    }

    public abstract Actor getIcon(T model);


    // SORT COMPARATORS:
    //--------------------------------------------------------------------------------------------------------

    public void addSortComparator(String text, Comparator<T>comparator)
    {
        SortBy sortBy = new SortBy(text, comparator);

        if (sortMap.isEmpty())
            setOptions(sortBy);

        sortMap.put(text, sortBy);
    }

    public class SortBy
    {
        public int insertOrder;
        public String text;
        public WidgetText widgetText;
        public Comparator<T> comparator;

        SortBy(String text, Comparator<T>comparator)
        {
            this.text = text;
            this.comparator = comparator;
            this.insertOrder = sortMap.size();

            widgetText = new WidgetText(text, Atlas.get().getFont("10"), unselectedSort, Color.BLACK, 1);
            widgetText.addListener(new TouchDownListener( o -> setSortBy(text)));
        }
    }
}
