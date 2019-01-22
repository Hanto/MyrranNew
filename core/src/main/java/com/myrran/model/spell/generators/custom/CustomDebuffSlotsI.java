package com.myrran.model.spell.generators.custom;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.SpellDebuffSlotTemplate;
import com.myrran.misc.InvalidIDException;

import java.util.Collection;
import java.util.List;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsI
{
    void setDebuffSlotsTemplate(Collection<SpellDebuffSlotTemplate> templates);
    List<SpellDebuffParams> getSpellEffectParams();
    CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException;
    boolean setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException;
    void removeCustomSpellDebuff(String slotID) throws InvalidIDException;
    int getDebuffSlotsTotalCost();
    CustomDebuffSlot getCustomDebufflot(String slotID) throws InvalidIDException;
}
