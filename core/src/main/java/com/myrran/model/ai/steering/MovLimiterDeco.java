package com.myrran.model.ai.steering;

import com.badlogic.gdx.ai.steer.Limiter;

/** @author Ivan Delgado Huerta */
public interface MovLimiterDeco extends MovLimiterI
{
    MovLimiterI getLimiter();

    default float getZeroLinearSpeedThreshold()
    {   return getLimiter().getZeroLinearSpeedThreshold(); }

    default void setZeroLinearSpeedThreshold(float value)
    {   getLimiter().setZeroLinearSpeedThreshold(value); }

    default float getMaxLinearSpeed()
    {   return getLimiter().getMaxLinearSpeed(); }

    default void setMaxLinearSpeed(float maxLinearSpeed)
    {   getLimiter().setMaxLinearSpeed(maxLinearSpeed); }

    default float getMaxLinearAcceleration()
    {   return getLimiter().getMaxLinearAcceleration(); }

    default void setMaxLinearAcceleration(float maxLinearAcceleration)
    {   getLimiter().setMaxLinearAcceleration(maxLinearAcceleration); }

    default float getMaxAngularSpeed()
    {   return getLimiter().getMaxAngularSpeed(); }

    default void setMaxAngularSpeed(float maxAngularSpeed)
    {   getLimiter().setMaxAngularSpeed(maxAngularSpeed); }

    default float getMaxAngularAcceleration()
    {   return getLimiter().getMaxAngularAcceleration(); }

    default void setMaxAngularAcceleration(float maxAngularAcceleration)
    {   getLimiter().setMaxAngularAcceleration(maxAngularAcceleration); }
}
