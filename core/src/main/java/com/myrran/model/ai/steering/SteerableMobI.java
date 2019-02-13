package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public interface SteerableMobI
{
    SteeringBehavior<Vector2> getSteeringBehavior();
    void setSteeringBehavior(SteeringBehavior<Vector2> steeringBehavior);
    void setIndependentFacing(boolean b);
    void calculateSteering(float delta);
}
