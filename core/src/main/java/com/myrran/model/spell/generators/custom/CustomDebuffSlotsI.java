package com.myrran.model.spell.generators.custom;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellDebuffSlot;
import com.myrran.misc.InvalidIDException;

import java.util.Collection;
import java.util.List;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsI
{
    void setDebuffSlotsTemplate(Collection<TemplateSpellDebuffSlot> templates);
    List<SpellDebuffParams> getSpellEffectParams();
    CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException;
    boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException;
    void removeCustomSpellDebuff(String slotID) throws InvalidIDException;
    int getDebuffSlotsTotalCost();
    CustomDebuffSlot getCustomDebufflot(String slotID) throws InvalidIDException;
}
