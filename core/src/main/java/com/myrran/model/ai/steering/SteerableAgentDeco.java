package com.myrran.model.ai.steering;

import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public interface SteerableAgentDeco extends SteerableAgentI, SpatialDeco, MovLimiterDeco
{
    SteerableAgentI getSteerableAgent();

    // STEERABLE:
    //--------------------------------------------------------------------------------------------------------

    default SpatialI getSpatial()
    {   return getSteerableAgent().getSpatial(); }

    default MovLimiterI getLimiter()
    {   return getSteerableAgent().getLimiter(); }

    default Vector2 getLinearVelocity()
    {   return getSteerableAgent().getLinearVelocity(); }

    default void setLinearVelocity(Vector2 vector)
    {   getSteerableAgent().setLinearVelocity(vector); }

    default float getAngularVelocity()
    {   return getSteerableAgent().getAngularVelocity(); }

    default void setAngularVelocity(float newAngularVelocity)
    {   getSteerableAgent().setAngularVelocity(newAngularVelocity); }

    default float getBoundingRadius()
    {   return getSteerableAgent().getBoundingRadius(); }

    default void setBoundingRadius(float newBoundingRadius)
    {   getSteerableAgent().setBoundingRadius(newBoundingRadius);}

    default boolean isTagged()
    {   return getSteerableAgent().isTagged(); }

    default void setTagged(boolean tagged)
    {   getSteerableAgent().setTagged(tagged); }
}
