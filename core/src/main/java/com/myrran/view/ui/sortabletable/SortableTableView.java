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
    //protected Table options;

    protected WidgetText name;
    protected SortableOptions<T> options;
    protected Collection<T>modelData;
    protected List<Actor> list;

    private static final BitmapFont font20 = Atlas.get().getFont("20");

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public void build(String text, SortableOptions<T> sortableOptions, boolean movable)
    {
        name    = new WidgetText(text, font20, Color.WHITE, Color.BLACK, 2);
        options = sortableOptions;

        if (movable)
            name.addListener(new ActorMoveListener(this));
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void createLayout(Collection<T>data)
    {
        modelData = data;
        createLayout(options.getActualSortBy().comparator, options.getShowDetails(), options.getReverseOrder());
    }

    private void createOptionsLayout()
    {

    }

    @Override
    public void createLayout(Comparator<T> comparator, boolean showDetails, boolean reverseOrder)
    {
        top().left();
        clear();

        list = modelData.stream()
            .sorted(reverseOrder ? comparator.reversed() : comparator)
            .map(this::getIcon)
            .collect(Collectors.toList());

        list.stream()
            .filter(DetailedActorI.class::isInstance)
            .map(DetailedActorI.class::cast)
            .forEach(actor -> actor.showDetails(showDetails));

        add(name).left().padBottom(-8).padTop(-8).row();
        add(options).left().row();

        for (Actor icon: list)
            add(icon).left().row();
    }

    public abstract Actor getIcon(T model);
}
