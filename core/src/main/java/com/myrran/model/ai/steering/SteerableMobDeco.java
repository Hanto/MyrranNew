package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public interface SteerableMobDeco
{
    SteerableMobI getSteerableMob();

    default SteeringBehavior<Vector2> getSteeringBehavior()
    {   return getSteerableMob().getSteeringBehavior(); }

    default void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior)
    {   getSteerableMob().setSteeringBehavior(steeringBehavior); }

    default void setIndependentFacing(boolean b)
    {   getSteerableMob().setIndependentFacing(b); }

    default void calculateSteering(float delta)
    {   getSteerableMob().calculateSteering(delta); }
}
