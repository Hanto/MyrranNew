package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SortOptionsTable<T> extends Table
{
    private Map<String, SortOptions> sortMap = new HashMap<>();
    private SortOptions actualSortOptions;
    private boolean reverseOrder = false;
    private WidgetText showDetailsText;
    private WidgetText closeDetailsText;
    private WidgetText reverseOrderText;

    private Map<T, Actor> modelToActorMap;
    private Table sortedTable;

    private static final String ASC = "Asc";
    private static final String DESC = "Desc";
    private static final String SHOW = "Expand";
    private static final String HIDE = "Close";
    private static final Color unselectedSort = Color.GRAY;
    private static final Color selectedSort = Color.ORANGE;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SortOptionsTable(Map<T, Actor> modelToActor, Table contentTable)
    {
        modelToActorMap = modelToActor;
        sortedTable = contentTable;
        showDetailsText = new WidgetText(SHOW, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        closeDetailsText= new WidgetText(HIDE, Atlas.get().getFont("14"), Color.WHITE, Color.BLACK, 1);
        reverseOrderText= new WidgetText(DESC, Atlas.get().getFont("10"), Color.WHITE, Color.BLACK, 1);
    }

    // SORT OPTIONS:
    //--------------------------------------------------------------------------------------------------------

    private void addSortOption(String text, Comparator<T>comparator)
    {
        SortOptions option = new SortOptions(text, comparator);

        if (sortMap.isEmpty())
            setSortOption(option);

        sortMap.put(text, option);
    }

    private void setReverseOrder(boolean reverse)
    {
        reverseOrder = reverse;
        reverseOrderText.setText(reverseOrder ? ASC : DESC);
    }

    private void setSortOption(SortOptions options)
    {
        if (options == actualSortOptions)
            setReverseOrder(!reverseOrder);
        else
        {
            if (actualSortOptions != null)
                actualSortOptions.widgetText.setTextColor(unselectedSort);
            actualSortOptions = options;
            actualSortOptions.widgetText.setTextColor(selectedSort);
        }
        sortModel();
    }

    private void sortModel()
    {
        modelToActorMap.keySet().stream()
            .sorted(reverseOrder ? actualSortOptions.comparator.reversed() : actualSortOptions.comparator)
            .map(t -> modelToActorMap.get(t))
            .forEach(actor -> sortedTable.add(actor).fillX().expandX().row());
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createOptionsLayout()
    {
        add(showDetailsText).padLeft(3).bottom().left();
        add(closeDetailsText).bottom().left();

        sortMap.values().stream()
            .sorted(Comparator.comparing(sortOption -> sortOption.insertOrder))
            .forEach(sortOption -> add(sortOption.widgetText).bottom().left());

        add(reverseOrderText).bottom().left();
    }

    // SORT OBJECT:
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
            widgetText.addListener(new TouchDownListener(o -> setSortOption(this)));
        }
    }
}
