package com.myrran.model.spell.generators;

import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.misc.InvalidIDException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomDebuffSlotsI
{
    public Collection<CustomDebuffSlot> getCustomDebuffSlots();
    void setDebuffSlotsTemplate(Collection<TemplateSpellSlot> templates);
    MapListI<String, SpellDebuffParams> getSpellEffectParams();
    CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException;
    boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException;
    void removeCustomSpellDebuff(String slotID) throws InvalidIDException;
    int getDebuffSlotsTotalCost();
    CustomDebuffSlot getCustomDebuffSlot(String slotID) throws InvalidIDException;
}
