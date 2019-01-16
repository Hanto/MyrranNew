package com.myrran.view.ui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.SnapshotArray;

import java.util.*;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class TextView extends Group
{
    private LabelStyle textStyle;
    private LabelStyle shadowStyle;
    private Label textLabel;
    private Label shadowLabel;
    private int shadowTickness;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TextView(BitmapFont font, Color textColor, Color shadowColor, int shadowTickness)
    {
        this.textStyle = new LabelStyle(font, textColor);
        this.shadowStyle = new LabelStyle(font, shadowColor);
        this.shadowTickness = shadowTickness;
    }

    public TextView(String text, BitmapFont font, Color textColor, Color shadowColor, int shadowTickness)
    {
        this(font, textColor, shadowColor, shadowTickness);
        setText(text);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setText(String text)
    {
        removeActor(textLabel);
        removeActor(shadowLabel);

        textLabel = new Label(text, textStyle);
        shadowLabel = new Label(text, shadowStyle);
        textLabel.setPosition(0, shadowTickness);
        shadowLabel.setPosition(shadowTickness, 0);

        addActor(shadowLabel);
        addActor(textLabel);
        setBounds();
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

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    // Actors should always be placed in the top, right quadrant
    private void setBounds()
    {
        SnapshotArray<Actor> actorsList = getChildren();

        List<Actor>list= Arrays.stream(actorsList.begin())
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

        setWidth(maxX);
        setHeight(maxY);
    }
}
