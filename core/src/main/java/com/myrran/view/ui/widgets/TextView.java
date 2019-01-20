package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/** @author Ivan Delgado Huerta */
public class TextView extends Widget
{
    private LabelStyle textStyle;
    private LabelStyle shadowStyle;
    private LabelView textLabel;
    private LabelView shadowLabel;
    private int shadowTickness;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TextView(BitmapFont font, Color textColor, Color shadowColor, int shadowTickness)
    {
        this.textStyle      = new LabelStyle(font, textColor);
        this.shadowStyle    = new LabelStyle(font, shadowColor);
        this.textLabel      = new LabelView(null, textStyle);
        this.shadowLabel    = new LabelView(null, shadowStyle);
        this.shadowTickness = shadowTickness;
    }

    public TextView(String text, BitmapFont font, Color textColor, Color shadowColor, int shadowTickness)
    {
        this(font, textColor, shadowColor, shadowTickness);
        setText(text);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public float getMinWidth()                { return getWidth(); }
    @Override public float getPrefWidth()               { return getWidth(); }
    @Override public float getMaxWidth()                { return getWidth(); }
    @Override public float getMinHeight()               { return getHeight(); }
    @Override public float getPrefHeight()              { return getHeight(); }
    @Override public float getMaxHeight()               { return getHeight(); }

    public void setText(String text)
    {
        textLabel.setText(text);
        shadowLabel.setText(text);
        updateSize();
    }

    public void setTextColor(Color color)
    {
        textStyle.fontColor = color;
        textLabel.setStyle(textStyle);
    }

    public void setShadowColor(Color color)
    {
        shadowStyle.fontColor = color;
        textLabel.setStyle(shadowStyle);
    }

    private void updateSize()
    {
        setWidth(textLabel.getWidth() + shadowTickness);
        setHeight(textLabel.getHeight() + shadowTickness);
        invalidateHierarchy();
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    @Override public void draw (Batch batch, float alpha)
    {
        //Posicion Elementos:
        textLabel.setPosition(getX(), getY() + shadowTickness);
        shadowLabel.setPosition(getX() + shadowTickness, getY());

        //Dibujado Elementos:
        shadowLabel.draw(batch, this.getColor().a * alpha);
        textLabel.draw(batch, this.getColor().a * alpha);
    }
}