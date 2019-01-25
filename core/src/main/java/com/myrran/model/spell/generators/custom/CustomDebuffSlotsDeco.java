package com.myrran.model.spell.generators.custom;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuffSlot;
import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Collection;
import java.util.List;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsDeco extends CustomDebuffSlotsI
{
    CustomDebuffSlotsI getDebuffSlots();

    // DECORATOR
    //--------------------------------------------------------------------------------------------------------

    default void setDebuffSlotsTemplate(Collection<TemplateSpellDebuffSlot> templates)
    {   getDebuffSlots().setDebuffSlotsTemplate(templates); }

    default List<SpellDebuffParams> getSpellEffectParams()
    {   return getDebuffSlots().getSpellEffectParams(); }

    default CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomSpellDebuff(slotID); }

    default boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException
    {   return getDebuffSlots().setCustomSpellDebuff(template, slotID); }

    default void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {   getDebuffSlots().removeCustomSpellDebuff(slotID); }

    default int getDebuffSlotsTotalCost()
    {   return getDebuffSlots().getDebuffSlotsTotalCost(); }

    default CustomDebuffSlot getCustomDebuffSlot(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomDebuffSlot(slotID); }
}
