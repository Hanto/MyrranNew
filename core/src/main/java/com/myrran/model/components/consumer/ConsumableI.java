package com.myrran.model.components.consumer;

/** @author Ivan Delgado Huerta */
public interface ConsumableI
{
    void setMaxDuration(float maxDuration);
    void setActualDuration(float actualDuration);
    float getActualDuration();
    float getMaxDuration();
    boolean updateDuration(float delta);
}
