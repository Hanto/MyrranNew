package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public interface SpatialDeco extends Location<Vector2>
{
    SpatialI getSpatial();

    default Vector2 getPosition()
    {   return getSpatial().getPosition(); }

    default void setPosition(float x, float y)
    {   getSpatial().setPosition(x, y); }

    default float getOrientation()
    {   return getSpatial().getOrientation(); }

    default void setOrientation(float newOrientation)
    {   getSpatial().setOrientation(newOrientation); }

    default float vectorToAngle(Vector2 vector)
    {   return getSpatial().vectorToAngle(vector); }

    default Vector2 angleToVector(Vector2 outVector, float angle)
    {   return getSpatial().angleToVector(outVector, angle); }

    default Location<Vector2> newLocation()
    {   return getSpatial().newLocation(); }
}
