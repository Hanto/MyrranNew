package com.myrran.spell.generators.custom.debuffslot;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffSlotTemplate;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.utils.InvalidIDException;

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
