package com.myrran.controller;

import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public class PlayerInputs
{
    private boolean inputUp;
    private boolean inputDown;
    private boolean inputLeft;
    private boolean inputRight;

    private boolean outputUp;
    private boolean outputDown;
    private boolean outputLeft;
    private boolean outputRight;

    public enum AnimationState { idle, runningRigh, runningLeft }

    // MOVEMENT:
    //--------------------------------------------------------------------------------------------------------

    public void setInputRight(boolean b)
    {
        inputRight = b;
        outputRight = !outputLeft && inputRight;
        outputLeft = !outputRight && inputLeft;
    }

    public void setInputLeft(boolean b)
    {
        inputLeft = b;
        outputLeft = !outputRight && inputLeft;
        outputRight = !outputLeft && inputRight;
    }

    public void setInputUp(boolean b)
    {
        inputUp = b;
        outputUp = !outputDown && inputUp;
        outputDown = !outputUp && inputDown;
    }

    public void setInputDown(boolean b)
    {
        inputDown = b;
        outputDown = !outputUp && inputDown;
        outputUp = !outputDown && inputUp;
    }

    public int getDirectionX()
    {   return outputLeft ? -1 : (outputRight ? 1 : 0); }

    public int getDirectionY()
    {   return outputDown ? -1 : (outputUp ? 1 : 0); }

    public AnimationState getAnimationState()
    {
        Vector2 direction = getDirection();
        if (direction.x == 0f && direction.y == 0f)
            return AnimationState.idle;
        else
            return direction.x < 0 ? AnimationState.runningLeft : AnimationState.runningRigh;
    }

    public Vector2 getDirection()
    {   return new Vector2(getDirectionX(), getDirectionY()); }
}