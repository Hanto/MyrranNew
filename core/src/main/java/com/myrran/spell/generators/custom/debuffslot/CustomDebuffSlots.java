package com.myrran.spell.generators.custom.debuffslot;

import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.templatedata.SpellDebuffSlotTemplate;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.utils.InvalidIDException;

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
    public void setDebuffSlotsTemplate(Collection<SpellDebuffSlotTemplate> templates)
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
    public boolean setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebufflot(slotID);
        return slot.setCustomSpellDebuff(debuff);
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
