package com.myrran.misc;

/** @author Ivan Delgado Huerta */
public interface Consumable
{
    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    void setMaxDuration(float maxDuration);
    void setActualDuration(float actualDuration);

    float getActualDuration();
    float getMaxDuration();

    // DEFAULT:
    //--------------------------------------------------------------------------------------------------------

    default boolean updateDuration(float delta)
    {
        setActualDuration(getActualDuration() + delta);
        return getActualDuration() > getMaxDuration();
    }
}
