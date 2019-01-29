package com.myrran.model.spell.generators;

import com.myrran.misc.dataestructures.maplist.MapList;
import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomDebuffSlots implements CustomDebuffSlotsI
{
    private Map<String, CustomDebuffSlot> slots;

    public Collection<CustomDebuffSlot> values()        { return slots.values(); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public void setDebuffSlotsTemplate(Collection<TemplateSpellSlot> templates)
    {
        slots = templates.stream()
            .map(CustomDebuffSlot::new)
            .collect(Collectors.toMap(CustomDebuffSlot::getID, slot -> slot));
    }

    // CUSTOM TO ENTITY PARAM:
    //--------------------------------------------------------------------------------------------------------

    public MapListI<String, SpellDebuffParams> getSpellEffectParams()
    {
        MapListI<String, SpellDebuffParams> mapList = new MapList<String, SpellDebuffParams>(HashMap::new, ArrayList::new);

        slots.values().stream()
            .filter(CustomDebuffSlot::hasData)
            .map(CustomDebuffSlot::getSpellDebuffParams)
            .forEach(debuffParam -> mapList.add(debuffParam.getSlotType(), debuffParam));

        return mapList;
    }

    // SPELL DEBUFF:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebuffSlot(slotID);
        return slot.getCustomSpellDebuff();
    }

    public boolean setCustomSpellDebuff(TemplateSpellDebuff template, String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebuffSlot(slotID);
        return slot.setCustomSpellDebuff(template);
    }

    public void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomDebuffSlot slot = getCustomDebuffSlot(slotID);
        slot.removeCustomSpellDebuff();
    }

    public CustomDebuffSlot getCustomDebuffSlot(String slotID) throws InvalidIDException
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
            .filter(CustomDebuffSlot::hasData)
            .mapToInt(CustomDebuffSlot::getTotalCost)
            .sum();
    }
}
