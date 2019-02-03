package com.myrran.model.spell.generators;

import com.myrran.misc.InvalidIDException;
import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellSlot;

import java.util.Collection;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsI
{
    Collection<? extends SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff>> getCustomDebuffSlots();
    void setDebuffSlotsTemplate(Collection<TemplateSpellSlot> templates);
    MapListI<String, SpellDebuffParams> getSpellEffectParams();
    CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException;
    boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException;
    void removeCustomSpellDebuff(String slotID) throws InvalidIDException;
    int getDebuffSlotsTotalCost();
    SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> getCustomDebuffSlot(String slotID) throws InvalidIDException;
}
