package com.myrran.controller;

import com.badlogic.gdx.math.Vector2;
import com.myrran.model.mob.Player;

/** @author Ivan Delgado Huerta */
public class PlayerInputs
{
    private Player model;
    private Vector2 orientation = new Vector2();

    private boolean inputUp;
    private boolean inputDown;
    private boolean inputLeft;
    private boolean inputRight;

    private boolean outputUp;
    private boolean outputDown;
    private boolean outputLeft;
    private boolean outputRight;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public PlayerInputs(Player player)
    {   model = player; }

    // MOVEMENT:
    //--------------------------------------------------------------------------------------------------------

    public void setInputRight(boolean b)
    {
        inputRight = b;
        outputRight = !outputLeft && inputRight;
        outputLeft = !outputRight && inputLeft;
        setPlayerOrientationVector();
    }

    public void setInputLeft(boolean b)
    {
        inputLeft = b;
        outputLeft = !outputRight && inputLeft;
        outputRight = !outputLeft && inputRight;
        setPlayerOrientationVector();
    }

    public void setInputUp(boolean b)
    {
        inputUp = b;
        outputUp = !outputDown && inputUp;
        outputDown = !outputUp && inputDown;
        setPlayerOrientationVector();
    }

    public void setInputDown(boolean b)
    {
        inputDown = b;
        outputDown = !outputUp && inputDown;
        outputUp = !outputDown && inputUp;
        setPlayerOrientationVector();
    }

    private void setPlayerOrientationVector()
    {   model.setOrientation(getOrientationVector()); }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private int getOrientationX()
    {   return outputLeft ? -1 : (outputRight ? 1 : 0); }

    private int getOrientationY()
    {   return outputDown ? -1 : (outputUp ? 1 : 0); }

    private Vector2 getOrientationVector()
    {
        orientation.set(getOrientationX(), getOrientationY());
        return orientation;
    }
}