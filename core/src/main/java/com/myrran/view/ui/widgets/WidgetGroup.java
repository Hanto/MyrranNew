package com.myrran.view.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.SnapshotArray;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class WidgetGroup extends Group implements Layout
{
    private boolean layoutEnabled = true;
    private boolean needsLayout = true;
    private boolean fillParent;

    public void addActor(Actor actor)
    {
        super.addActor(actor);
        setSize();
    }

    // LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    @Override public void layout()                      { }
    @Override public float getMinWidth()                { return getWidth(); }
    @Override public float getPrefWidth()               { return getWidth(); }
    @Override public float getMaxWidth()                { return getWidth(); }
    @Override public float getMinHeight()               { return getHeight(); }
    @Override public float getPrefHeight()              { return getHeight(); }
    @Override public float getMaxHeight()               { return getHeight(); }

    @Override public void setFillParent (boolean fillParent)
    {   this.fillParent = fillParent; }

    @Override public void invalidate()
    {   this.needsLayout = true; }

    @Override public void setLayoutEnabled (boolean enabled)
    {
        layoutEnabled = enabled;

        if (layoutEnabled)
            invalidateHierarchy();
    }

    @Override public void invalidateHierarchy()
    {
        setSize();

        if (layoutEnabled)
        {
            invalidate();
            Group parent = getParent();

            if (parent instanceof Layout)
                ((Layout) parent).invalidateHierarchy();
        }
    }

    @Override public void validate()
    {
        if (layoutEnabled)
        {
            Actor parent = getParent();

            if (fillParent && parent != null)
                fillParent(parent);

            if (needsLayout)
            {
                needsLayout = false;
                layout();
            }
        }
    }

    @Override public void pack()
    {
        setSize(getPrefWidth(), getPrefHeight());
        validate();
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private void fillParent(Actor parent)
    {
        float parentWidth, parentHeight;
        Stage stage = getStage();

        if (stage != null && parent == stage.getRoot())
        {
            parentWidth = stage.getWidth();
            parentHeight = stage.getHeight();
        }
        else
        {
            parentWidth = parent.getWidth();
            parentHeight = parent.getHeight();
        }
        setSize(parentWidth, parentHeight);
    }

    private void setSize()
    {
        SnapshotArray<Actor> actorsList = getChildren();

        List<Actor> list= Arrays.stream(actorsList.begin())
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        Float maxX = list.stream()
            .max(Comparator.comparing(actor -> actor.getX() + actor.getWidth()))
            .map(actor -> actor.getX() + actor.getWidth())
            .orElse(0.0f);

        Float maxY = list.stream()
            .max(Comparator.comparing(actor -> actor.getY() + actor.getHeight()))
            .map(actor -> actor.getY() + actor.getHeight())
            .orElse(0.0f);

        actorsList.end();

        setSize(maxX, maxY);
    }
}
