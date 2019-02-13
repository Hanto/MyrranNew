package com.myrran.model.ai.steering;

/** @author Ivan Delgado Huerta */
public class MovLimiter implements MovLimiterI
{
    private float linearSpeedThreshold = 0.001f;
    private float maxLinearSpeed = 50f;
    private float maxLinearAcceleration = 200f;
    private float maxAngularSpeed = 5f;
    private float maxAngularAcceleration = 5f;

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public float getZeroLinearSpeedThreshold()
    {   return linearSpeedThreshold; }

    @Override public void setZeroLinearSpeedThreshold(float value)
    {   linearSpeedThreshold = value; }

    @Override public float getMaxLinearSpeed()
    {   return maxLinearSpeed; }

    @Override public void setMaxLinearSpeed(float maxLinearSpeed)
    {   this.maxLinearSpeed = maxLinearSpeed; }

    @Override public float getMaxLinearAcceleration()
    {   return maxLinearAcceleration; }

    @Override public void setMaxLinearAcceleration(float maxLinearAcceleration)
    {   this.maxLinearAcceleration = maxLinearAcceleration; }

    @Override public float getMaxAngularSpeed()
    {   return maxAngularSpeed; }

    @Override public void setMaxAngularSpeed(float maxAngularSpeed)
    {   this.maxAngularSpeed = maxAngularSpeed; }

    @Override public float getMaxAngularAcceleration()
    {   return maxAngularAcceleration; }

    @Override public void setMaxAngularAcceleration(float maxAngularAcceleration)
    {   this.maxAngularAcceleration = maxAngularAcceleration; }
}
