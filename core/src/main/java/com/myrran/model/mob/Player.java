package com.myrran.model.mob;

import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public class Player
{
    private Vector2 position = new Vector2(0, 0);
    private Vector2 orientationVector = new Vector2(0, 0);
    private float orientation;

    public Vector2 getPosition()            { return position; }
    public float getOrientation()           { return orientation; }
    public Vector2 getOrientationVector()   { return orientationVector; }

    public enum AnimationState { idle, runningRigh, runningLeft }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setOrientation(Vector2 vector)
    {
        orientationVector.set(vector);
        orientation = orientationVector.angleRad();
    }

    /*public AnimationState getAnimationState()
    {
        Vector2 direction = playerInputs.getOrientationVector();
        if (direction.x == 0f && direction.y == 0f)
            return AnimationState.idle;
        else
            return direction.x < 0 ? AnimationState.runningLeft : AnimationState.runningRigh;
    }*/
}
