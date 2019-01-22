package com.myrran.view.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/** @author Ivan Delgado Huerta */
public class WidgetLabel extends Label
{
    // Original Label doesn't change prefsizes on setText
    // This wrapper overrides the setText, so the size is recalculated

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public WidgetLabel(CharSequence text, LabelStyle style)
    {   super(text, style); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public void setText(CharSequence newText)
    {
        super.setText(newText);
        if (newText != null)
            setSize(getPrefWidth(), getPrefHeight());
        else
            setSize(0, 0);
    }
}
