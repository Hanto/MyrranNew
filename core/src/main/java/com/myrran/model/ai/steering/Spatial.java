package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public class Spatial implements SpatialI
{
    private Vector2 position = new Vector2();
    private float orientation = 0f;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public Vector2 getPosition()
    {   return position; }

    @Override public void setPosition(float x, float y)
    {   position.set(x, y); }

    @Override public float getOrientation()
    {   return orientation; }

    @Override public void setOrientation(float newOrientation)
    {   orientation = newOrientation; }

    @Override public float vectorToAngle(Vector2 vector)
    {   return vector.angleRad(); }

    @Override public Vector2 angleToVector(Vector2 outVector, float angle)
    {   return outVector.setAngleRad(angle); }

    @Override public Location<Vector2> newLocation()
    {   return new Spatial(); }
}