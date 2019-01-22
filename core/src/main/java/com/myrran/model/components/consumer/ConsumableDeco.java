package com.myrran.model.components.consumer;


/** @author Ivan Delgado Huerta */
public interface ConsumableDeco extends ConsumableI
{
    ConsumableI getConsumable();

    // DECORATOR:
    //--------------------------------------------------------------------------------------------------------

    default void setMaxDuration(float maxDuration)
    {   getConsumable().setMaxDuration(maxDuration); }

    default void setActualDuration(float actualDuration)
    {   getConsumable().setActualDuration(actualDuration); }

    default float getActualDuration()
    {   return getConsumable().getActualDuration(); }

    default float getMaxDuration()
    {   return getConsumable().getMaxDuration(); }

    default boolean updateDuration(float delta)
    {   return getConsumable().updateDuration(delta); }
}
