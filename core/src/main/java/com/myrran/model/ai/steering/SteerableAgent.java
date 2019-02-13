package com.myrran.model.ai.steering;

import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public class SteerableAgent implements SteerableAgentI, SpatialDeco, MovLimiterDeco
{
    private Vector2 linearVelocity = new Vector2();
    private float angularVelocity = 0f;
    private float boundingRadius = 1f;
    private boolean isTagged = false;

    // DECOS:
    //--------------------------------------------------------------------------------------------------------

    private SpatialI spatial = new Spatial();
    private MovLimiter limiter = new MovLimiter();

    @Override public SpatialI getSpatial()      { return spatial; }
    @Override public MovLimiterI getLimiter()       { return limiter; }

    // STEERABLE AGENT:
    //--------------------------------------------------------------------------------------------------------

    @Override public Vector2 getLinearVelocity()
    {   return linearVelocity; }

    @Override public void setLinearVelocity(Vector2 vector)
    {   linearVelocity.set(vector).limit(getMaxAngularSpeed()); }

    @Override public float getAngularVelocity()
    {   return angularVelocity; }

    @Override public void setAngularVelocity(float newAngularVelocity)
    {   angularVelocity = newAngularVelocity; }

    @Override public float getBoundingRadius()
    {   return boundingRadius; }

    @Override public void setBoundingRadius(float newBoundingRadius)
    {   boundingRadius = newBoundingRadius; }

    @Override public boolean isTagged()
    {   return isTagged; }

    @Override public void setTagged(boolean tagged)
    {   this.isTagged = tagged; }
}
