package com.myrran.model.spell.generators;

import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.ObservableI;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface SpellSlotI<T extends ObservableI, U> extends ObservableI, Identifiable
{
    String getID();
    String getName();
    boolean hasData();
    T getContent();
    boolean setContent(U content);
    void removeContent();
    String getSlotType();
    List<CustomSpellSlotKey>getLock();
    boolean opensLock(List<CustomSpellSlotKey>keys);
    int getTotalCost();
}