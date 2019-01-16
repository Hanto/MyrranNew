package com.myrran.misc.Consumable;

/** @author Ivan Delgado Huerta */
public class Consumable implements ConsumableI
{
    private float actualDuration = 0.0f;
    private float maxDuration = 5.0f;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public float getActualDuration()                      { return actualDuration; }
    @Override public float getMaxDuration()                         { return maxDuration; }
    @Override public void setMaxDuration(float maxDuration)         { this.maxDuration = maxDuration; }
    @Override public void setActualDuration(float actualDuration)   { this.actualDuration = actualDuration; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public boolean updateDuration(float delta)
    {
        setActualDuration(getActualDuration() + delta);
        return getActualDuration() > getMaxDuration();
    }
}
