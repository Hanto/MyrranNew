package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.steer.Limiter;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.math.Vector2;
import com.myrran.model.ai.steering.MovLimiterI;
import com.myrran.model.ai.steering.SpatialI;

/** @author Ivan Delgado Huerta */
public interface SteerableAgentI extends Steerable<Vector2>
{
    void setLinearVelocity(Vector2 vector);
    void setAngularVelocity(float newAngularVelocity);
    void setBoundingRadius(float newBoundingRadius);
    SpatialI getSpatial();
    MovLimiterI getLimiter();
}
