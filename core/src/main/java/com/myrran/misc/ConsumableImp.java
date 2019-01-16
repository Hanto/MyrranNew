package com.myrran.misc;

/** @author Ivan Delgado Huerta */
public class ConsumableImp
{
    private float actualDuration = 0.0f;
    private float maxDuration = 5.0f;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public float getActualDuration()                      { return actualDuration; }
    public float getMaxDuration()                         { return maxDuration; }
    public void setMaxDuration(float maxDuration)         { this.maxDuration = maxDuration; }
    public void setActualDuration(float actualDuration)   { this.actualDuration = actualDuration; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public boolean updateDuration(float delta)
    {
        setActualDuration(getActualDuration() + delta);
        return getActualDuration() > getMaxDuration();
    }
}
