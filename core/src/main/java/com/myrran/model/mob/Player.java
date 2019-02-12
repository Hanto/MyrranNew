package com.myrran.model.mob;

import com.badlogic.gdx.math.Vector2;
import com.myrran.controller.PlayerInputs;

/** @author Ivan Delgado Huerta */
public class Player
{
    private Vector2 position;
    private PlayerInputs playerInputs;

    public Vector2 getPosition()        { return position; }

    public enum AnimationState { idle, runningRigh, runningLeft }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public Player(PlayerInputs inputs)
    {   playerInputs = inputs; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public AnimationState getAnimationState()
    {
        Vector2 direction = playerInputs.getOrientationVector();
        if (direction.x == 0f && direction.y == 0f)
            return AnimationState.idle;
        else
            return direction.x < 0 ? AnimationState.runningLeft : AnimationState.runningRigh;
    }

}
