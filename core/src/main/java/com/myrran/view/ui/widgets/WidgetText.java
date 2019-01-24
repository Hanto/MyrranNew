package com.myrran.view.ui.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

/** @author Ivan Delgado Huerta */
public class WidgetText extends Widget
{
    private LabelStyle textStyle;
    private LabelStyle shadowStyle;
    private WidgetLabel textLabel;
    private WidgetLabel shadowLabel;
    private int shadowTickness;

    @Override public float getMinWidth()                { return getWidth(); }
    @Override public float getPrefWidth()               { return getWidth(); }
    @Override public float getMaxWidth()                { return getWidth(); }
    @Override public float getMinHeight()               { return getHeight(); }
    @Override public float getPrefHeight()              { return getHeight(); }
    @Override public float getMaxHeight()               { return getHeight(); }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public WidgetText(BitmapFont font, Color textColor, Color shadowColor, int shadowTickness)
    {
        this.textStyle      = new LabelStyle(font, textColor);
        this.shadowStyle    = new LabelStyle(font, shadowColor);
        this.textLabel      = new WidgetLabel(null, textStyle);
        this.shadowLabel    = new WidgetLabel(null, shadowStyle);
        this.shadowTickness = shadowTickness;
    }

    public WidgetText(String text, BitmapFont font, Color textColor, Color shadowColor, int shadowTickness)
    {
        this(font, textColor, shadowColor, shadowTickness);
        setText(text);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

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
        if (getWidth() != textLabel.getWidth() || getHeight() != textLabel.getHeight())
        {
            setWidth(textLabel.getWidth() + shadowTickness);
            setHeight(textLabel.getHeight() + shadowTickness);
            invalidateHierarchy();
        }
    }

    // DRAW:
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