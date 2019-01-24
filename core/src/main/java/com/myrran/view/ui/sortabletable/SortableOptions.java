package com.myrran.view.ui.sortabletable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.util.*;

/** @author Ivan Delgado Huerta */
public abstract class SortableOptions<T> extends Table
{
    private SortableTableI<T> sortableTable;

    private SortBy actualSortBy;
    private boolean showDetails = false;
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText reverseOrderText;

    private Map<String, SortBy> sortMap = new HashMap<>();
    private static final Color selectedSort = Color.ORANGE;
    private static final Color unselectedSort = Color.GRAY;
    private static final String ASC = "Asc";
    private static final String DESC = "Desc";
    private static final String SHOW = "Show Details";
    private static final String HIDE = "Hide Details";

    public boolean getShowDetails()                 { return showDetails; }
    public boolean getReverseOrder()                { return reverseOrder; }
    public SortBy getActualSortBy()                 { return actualSortBy; }
    public SortBy getSortBy(String sortByName)      { return sortMap.get(sortByName); }
    public void setActualSortBy(SortBy newSortBy)   { actualSortBy = newSortBy; actualSortBy.widgetText.setTextColor(selectedSort);}

    // CONSTRUCTOR:
    //-----------------------------------------------------------------------------------------------

    public SortableOptions(SortableTableI<T> sortableTabl)
    {
        sortableTable = sortableTabl;

        reverseOrderText= new WidgetText(DESC, Atlas.get().getFont("10"), Color.WHITE, Color.BLACK, 1);
        showDetailsText = new WidgetText(SHOW, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        showDetailsText.addListener(new SortableShowListener(this));
        reverseOrderText.addListener(new SortableOrderDescListener(this));
    }

    public void createLayout()
    {
        top().left();
        add(showDetailsText).minWidth(80).bottom().left();

        sortMap.values().stream()
            .sorted(Comparator.comparing(sortBy -> sortBy.insertOrder))
            .forEach(sortBy -> add(sortBy.widgetText));

        add(reverseOrderText);
        row();
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setShowDetails()
    {
        showDetails = !showDetails;
        showDetailsText.setText(showDetails? HIDE : SHOW);
        sortableTable.createLayout(actualSortBy.comparator, showDetails, reverseOrder);
    }

    public void setSortBy(String sort)
    {
        if (actualSortBy.text.equals(sort))
            reverseOrder = !reverseOrder;

        reverseOrderText.setText(reverseOrder ? ASC : DESC);

        actualSortBy.widgetText.setTextColor(unselectedSort);
        actualSortBy = sortMap.get(sort);
        actualSortBy.widgetText.setTextColor(selectedSort);

        sortableTable.createLayout(actualSortBy.comparator, showDetails, reverseOrder);
    }

    // SORT COMPARATORS:
    //--------------------------------------------------------------------------------------------------------

    public void addSortComparator(String text, Comparator<T>comparator)
    {
        SortBy sortBy = new SortBy(text, comparator);

        if (sortMap.isEmpty())
            setActualSortBy(sortBy);

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
            widgetText.addListener(new SortableOrderTypeListener(SortableOptions.this, text));
        }
    }
}
