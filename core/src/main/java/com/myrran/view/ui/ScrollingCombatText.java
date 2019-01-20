package com.myrran.view.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.myrran.view.ui.widgets.TextView;

/** @author Ivan Delgado Huerta */
public class ScrollingCombatText
{
    private BitmapFont font;
    private float moveY = 50;
    private float moveX = 20;
    private float duration = 5;
    private Interpolation interpolationY = Interpolation.smooth;
    private Interpolation interpolationX = Interpolation.smooth;

    // SETTERS:
    //--------------------------------------------------------------------------------------------------------

    public ScrollingCombatText setMoveY(float y)
    {   this.moveY = y; return this; }

    public ScrollingCombatText setMoveX(float x)
    {   this.moveX = x; return this; }

    public ScrollingCombatText setDuration(float duration)
    {   this.duration = duration; return this; }

    public ScrollingCombatText setInterpolationX(Interpolation interpolation)
    {   this.interpolationX = interpolation; return this; }

    public ScrollingCombatText setInterpolationY(Interpolation interpolation)
    {   this.interpolationY = interpolation; return this; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public ScrollingCombatText(BitmapFont font)
    {   this.font = font; }

    // VARIANTS:
    //--------------------------------------------------------------------------------------------------------

    public TextView sct(String text)
    {   return sct(text, duration, moveX, moveY, interpolationX, interpolationY) ;}

    public TextView sct(String text, float duration)
    {   return sct(text, duration, moveX, moveY, interpolationX, interpolationY) ;}

    public TextView sct(String text, float duration, float moveX, float moveY)
    {   return sct(text, duration, moveX, moveY, interpolationX, interpolationY) ;}

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public TextView sct(String text, float duration, float moveX, float moveY,
        Interpolation interpolationX, Interpolation interpolationY)
    {
        TextView textView = new TextView(text, font, Color.RED, Color.BLACK, 2);
        textView.setColor(textView.getColor().r, textView.getColor().g, textView.getColor().b, 0);

        textView.addAction(Actions.sequence(
            Actions.fadeIn(duration / 4),
            Actions.delay(duration / 4 * 2),
            Actions.fadeOut(duration / 4)));

        textView.addAction(Actions.sequence(
            Actions.moveBy(0, moveY, duration, interpolationY),
            Actions.removeActor()));

        textView.addAction(Actions.sequence(
            Actions.moveBy(moveX, 0, duration / 4, interpolationX),
            Actions.moveBy(-moveX, 0, duration / 4, interpolationX),
            Actions.moveBy(moveX, 0, duration / 4, interpolationX),
            Actions.moveBy(-moveX, 0, duration / 4, interpolationX)));

        return textView;
    }
}
