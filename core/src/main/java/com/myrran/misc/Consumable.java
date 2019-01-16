package com.myrran.misc;

/** @author Ivan Delgado Huerta */
public interface Consumable
{
    ConsumableImp getConsumable();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    default void setMaxDuration(float maxDuration)
    {   getConsumable().setMaxDuration(maxDuration); }

    default void setActualDuration(float actualDuration)
    {   getConsumable().setActualDuration(actualDuration); }

    default float getActualDuration()
    {   return getConsumable().getActualDuration(); }

    default float getMaxDuration()
    {   return getConsumable().getMaxDuration(); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    default boolean updateDuration(float delta)
    {   return getConsumable().updateDuration(delta); }
}
