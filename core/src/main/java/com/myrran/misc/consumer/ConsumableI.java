package com.myrran.misc.consumer;

/** @author Ivan Delgado Huerta */
public interface ConsumableI
{
    void setMaxDuration(float maxDuration);
    void setActualDuration(float actualDuration);
    float getActualDuration();
    float getMaxDuration();
    boolean updateDuration(float delta);
}
