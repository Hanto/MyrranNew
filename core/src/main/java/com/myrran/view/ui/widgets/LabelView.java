package com.myrran.view.ui.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Label;

/** @author Ivan Delgado Huerta */
public class LabelView extends Label
{
    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public LabelView (CharSequence text, LabelStyle style)
    {   super(text, style); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public void setText(CharSequence newText)
    {
        super.setText(newText);
        setSize(getPrefWidth(), getPrefHeight());
    }
}
