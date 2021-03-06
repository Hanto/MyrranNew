package com.myrran.model.spell.generators;

import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Collection;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsDeco extends CustomDebuffSlotsI
{
    CustomDebuffSlotsI getDebuffSlots();

    // DECORATOR
    //--------------------------------------------------------------------------------------------------------

    default void setDebuffSlotsTemplate(Collection<TemplateSpellSlot> templates)
    {   getDebuffSlots().setDebuffSlotsTemplate(templates); }

    default MapListI<String, SpellDebuffParams> getSpellEffectParams()
    {   return getDebuffSlots().getSpellEffectParams(); }

    default CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomSpellDebuff(slotID); }

    default boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException
    {   return getDebuffSlots().setCustomSpellDebuff(template, slotID); }

    default void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {   getDebuffSlots().removeCustomSpellDebuff(slotID); }

    default int getDebuffSlotsTotalCost()
    {   return getDebuffSlots().getDebuffSlotsTotalCost(); }

    default SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> getCustomDebuffSlot(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomDebuffSlot(slotID); }

    default Collection<CustomDebuffSlot> getCustomDebuffSlots()
    {   return getDebuffSlots().getCustomDebuffSlots(); }
}
