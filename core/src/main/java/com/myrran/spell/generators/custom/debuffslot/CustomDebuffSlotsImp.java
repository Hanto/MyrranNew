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
public class CustomDebuffSlotsImp
{
    private Map<String, CustomDebuffSlot> slots = new HashMap<>();

    public Collection<CustomDebuffSlot> values()            { return slots.values(); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellSlotTemplate(SpellDebuffSlotTemplate template)
    {
        slots.computeIfAbsent(template.getID(), v -> new CustomDebuffSlot());
        CustomDebuffSlot customDebuffSlot = slots.get(template.getID());
        customDebuffSlot.setSpellSlotTemplate(template);
    }

    // CUSTOM TO ENTITY PARAM:
    //--------------------------------------------------------------------------------------------------------

    public List<SpellDebuffParams> getSpellEffectParams()
    {
        return slots.values().stream()
            .filter(customDebuffSlot -> customDebuffSlot.getCustomSpellDebuff() != null)
            .map(CustomDebuffSlot::getSpellEffectData)
            .collect(Collectors.toList());
    }

    // SPELL DEBUFF:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomSpellSlot(slotID);
        return slot.getCustomSpellDebuff();
    }

    public void setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomSpellSlot(slotID);
        slot.setCustomSpellDebuff(debuff);
    }

    public void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomSpellSlot(slotID);
        slot.removeCustomSpellDebuff();
    }

    private CustomDebuffSlot getCustomSpellSlot(String slotID) throws InvalidIDException
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
            .mapToInt(CustomDebuffSlot::getTotalCost)
            .sum();
    }
}
