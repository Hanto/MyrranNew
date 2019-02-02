package com.myrran.view.ui.customspell.form;

import com.myrran.model.spell.generators.CustomSpellSlotKey;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface SpellSlotI<T>
{
    T getContent();
    boolean hasData();
    String getSlotType();
    List<CustomSpellSlotKey>getLock();
    boolean opensLock(List<CustomSpellSlotKey>keys);
}
