package com.myrran.model.spell.generators.custom;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.SpellDebuffSlotTemplate;
import com.myrran.misc.InvalidIDException;

import java.util.Collection;
import java.util.List;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsDeco extends CustomDebuffSlotsI
{
    CustomDebuffSlotsI getDebuffSlots();

    // DECORATOR
    //--------------------------------------------------------------------------------------------------------

    default void setDebuffSlotsTemplate(Collection<SpellDebuffSlotTemplate> templates)
    {   getDebuffSlots().setDebuffSlotsTemplate(templates); }

    default List<SpellDebuffParams> getSpellEffectParams()
    {   return getDebuffSlots().getSpellEffectParams(); }

    default CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomSpellDebuff(slotID); }

    default boolean setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException
    {   return getDebuffSlots().setCustomSpellDebuff(debuff, slotID); }

    default void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {   getDebuffSlots().removeCustomSpellDebuff(slotID); }

    default int getDebuffSlotsTotalCost()
    {   return getDebuffSlots().getDebuffSlotsTotalCost(); }

    default CustomDebuffSlot getCustomDebufflot(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomDebufflot(slotID); }
}
