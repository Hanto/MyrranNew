package com.myrran.model.spell.generators.custom;

import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellDebuffSlot;
import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomDebuffSlots implements CustomDebuffSlotsI
{
    private Map<String, CustomDebuffSlot> slots = new HashMap<>();

    public Collection<CustomDebuffSlot> values()        { return slots.values(); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void setDebuffSlotsTemplate(Collection<TemplateSpellDebuffSlot> templates)
    {
        slots = templates.stream()
            .map(CustomDebuffSlot::new)
            .collect(Collectors.toMap(CustomDebuffSlot::getID, slot -> slot));
    }

    // CUSTOM TO ENTITY PARAM:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public List<SpellDebuffParams> getSpellEffectParams()
    {
        return slots.values().stream()
            .filter(customDebuffSlot -> customDebuffSlot.getCustomSpellDebuff() != null)
            .map(CustomDebuffSlot::getSpellEffectData)
            .collect(Collectors.toList());
    }

    // SPELL DEBUFF:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebufflot(slotID);
        return slot.getCustomSpellDebuff();
    }

    @Override
    public boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebufflot(slotID);
        return slot.setCustomSpellDebuff(template);
    }

    @Override
    public void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebufflot(slotID);
        slot.removeCustomSpellDebuff();
    }

    @Override
    public CustomDebuffSlot getCustomDebufflot(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = slots.get(slotID);
        if (slot != null) return slot;
        else throw new  InvalidIDException("SpellSlot with the following ID doesn't exist: %s", slotID);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getDebuffSlotsTotalCost()
    {
        return slots.values().stream()
            .filter(customDebuffSlot -> customDebuffSlot.getCustomSpellDebuff() != null)
            .mapToInt(CustomDebuffSlot::getTotalCost)
            .sum();
    }
}
