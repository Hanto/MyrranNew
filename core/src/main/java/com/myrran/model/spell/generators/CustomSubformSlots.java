package com.myrran.model.spell.generators;

import com.myrran.misc.InvalidIDException;
import com.myrran.misc.dataestructures.maplist.MapList;
import com.myrran.misc.dataestructures.maplist.MapListI;
import com.myrran.model.spell.parameters.SpellSubformParams;
import com.myrran.model.spell.templates.TemplateSpellSlot;
import com.myrran.model.spell.templates.TemplateSpellSubform;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSubformSlots
{
    private Map<String, CustomSubformSlot> slots;

    public Collection<CustomSubformSlot> getCustomSubformSlots()   { return slots.values(); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public void setSubformSlotsTemplate(Collection<TemplateSpellSlot> templates)
    {
        slots = templates.stream()
            .map(CustomSubformSlot::new)
            .collect(Collectors.toMap(CustomSubformSlot::getID, slot -> slot));
    }

    // CUSTOM TO ENTITY PARAM:
    //--------------------------------------------------------------------------------------------------------

    public MapListI<String, SpellSubformParams> getSpellEffectParams()
    {
        MapListI<String, SpellSubformParams> mapList = new MapList<String, SpellSubformParams>(HashMap::new, ArrayList::new);

        slots.values().stream()
            .filter(CustomSubformSlot::hasData)
            .map(CustomSubformSlot::getSpellDebuffParams)
            .forEach(debuffParam -> mapList.add(debuffParam.getSlotType(), debuffParam));

        return mapList;
    }

    // SPELL SUBFORM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellSubform getCustomSpellSubform(String slotID) throws InvalidIDException
    {
        CustomSubformSlot slot = getCustomSubformSlot(slotID);
        return slot.getContent();
    }

    public boolean setCustomSpellSubform(TemplateSpellSubform template, String slotID) throws InvalidIDException
    {
        CustomSubformSlot slot = getCustomSubformSlot(slotID);
        return slot.setCustomSpellSubform(template);
    }

    public void removeCustomSpellSubform(String slotID) throws InvalidIDException
    {
        CustomSubformSlot slot = getCustomSubformSlot(slotID);
        slot.removeCustomSpellSubform();
    }

    public CustomSubformSlot getCustomSubformSlot(String slotID) throws InvalidIDException
    {
        CustomSubformSlot slot = slots.get(slotID);
        if (slot != null) return slot;
        else throw new InvalidIDException("SpellSlot with the following ID doesn't exist: %s", slotID);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getSubFormsTotalCost()
    {
        return slots.values().stream()
            .filter(CustomSubformSlot::hasData)
            .mapToInt(CustomSubformSlot::getTotalCost)
            .sum();
    }
}
