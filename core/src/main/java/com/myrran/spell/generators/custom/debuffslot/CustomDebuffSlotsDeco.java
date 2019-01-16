package com.myrran.spell.generators.custom.debuffslot;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffSlotTemplate;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.utils.InvalidIDException;

import java.util.List;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsDeco extends CustomDebuffSlotsI
{
    CustomDebuffSlotsI getDebuffSlots();

    // DECORATOR
    //--------------------------------------------------------------------------------------------------------

    default void setSpellSlotTemplate(SpellDebuffSlotTemplate template)
    {   getDebuffSlots().setSpellSlotTemplate(template); }

    default List<SpellDebuffParams> getSpellEffectParams()
    {   return getDebuffSlots().getSpellEffectParams(); }

    default CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {   return getDebuffSlots().getCustomSpellDebuff(slotID); }

    default void setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException
    {   getDebuffSlots().setCustomSpellDebuff(debuff, slotID); }

    default void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {   getDebuffSlots().removeCustomSpellDebuff(slotID); }

    default int getDebuffSlotsTotalCost()
    {   return getDebuffSlots().getDebuffSlotsTotalCost(); }
}
