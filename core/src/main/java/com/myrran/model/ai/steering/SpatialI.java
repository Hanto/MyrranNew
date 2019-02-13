package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

/** @author Ivan Delgado Huerta */
public interface SpatialI extends Location<Vector2>
{
    void setPosition(float x, float y);
}
