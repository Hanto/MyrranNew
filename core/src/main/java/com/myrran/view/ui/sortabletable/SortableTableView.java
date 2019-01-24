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
public abstract class SortableTableView<T> extends Table implements SortableTableI<T>
{
    private WidgetText name;
    private SortableOptions<T> options;
    private Collection<T>modelData;
    private List<Actor> list;
    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SortableTableView(SortableOptions<T> sortableOptions, boolean movable)
    {
        name    = new WidgetText("SpellForm SpellBook", font20, Color.WHITE, Color.BLACK, 2);
        options = sortableOptions;

        if (movable)
            name.addListener(new ActorMoveListener(this));
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setData(Collection<T>data)
    {
        modelData = data;
        createLayout(options.getActualSortBy().comparator, options.getShowDetails(), options.getReverseOrder());
    }

    @Override
    public void createLayout(Comparator<T> comparator, boolean showDetails, boolean reverseOrder)
    {
        top().left();
        clear();

        list = modelData.stream()
            .sorted(comparator)
            .map(this::getIcon)
            .collect(Collectors.toList());

        if (reverseOrder)
            Collections.reverse(list);

        add(name).left().padBottom(-8).padTop(-8).row();
        add(options).left().row();

        for (Actor icon: list)
            add(icon).left().row();
    }

    public abstract Actor getIcon(T model);
}
