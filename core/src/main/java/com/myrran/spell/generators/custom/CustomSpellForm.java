package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;
import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.data.templatedata.SpellSlotTemplate;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.spell.entity.form.SpellForm;
import com.myrran.spell.entity.form.SpellFormFactory;
import com.myrran.spell.generators.SpellFormGenerator;
import com.myrran.utils.InvalidIDException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellForm implements SpellFormGenerator, Identifiable
{
    private String id;
    private String name;
    private String templateID;
    private SpellFormFactory factory;
    private Map<String, CustomSpellStat> stats = new HashMap<>();
    private Map<String, CustomSpellSlot> slots = new HashMap<>();

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellForm.class);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                     { return id; }
    @Override public String getName()                   { return name; }
    public String getTemplateID()                       { return templateID; }
    public Map<String, CustomSpellSlot> getSlots()      { return slots; }
    @Override public void setID(String id)              { this.id = id; }
    @Override public void setName(String name)          { this.name = name; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellForm(SpellFormTemplate template)
    {   setSpellFormTemplate(template); }

    @Override
    public void setSpellFormTemplate(SpellFormTemplate template)
    {
        id = template.getID();
        name = template.getName();
        templateID = template.getID();
        factory = template.getFactory();

        template.getSpellStats()
            .forEach(this::setSpellStatTemplate);

        template.getSpellSlots()
            .forEach(this::setSpellSlotTemplate);
    }

    private void setSpellStatTemplate(SpellStatTemplate template)
    {
        CustomSpellStat customSpellStat = stats.get(template.getID());
        if (customSpellStat == null)
        {
            customSpellStat = new CustomSpellStat();
            stats.put(template.getID(), customSpellStat);
        }
        customSpellStat.setSpellStatTemplate(template);
    }

    private void setSpellSlotTemplate(SpellSlotTemplate template)
    {
        CustomSpellSlot customSpellSlot = slots.get(template.getID());
        if (customSpellSlot == null)
        {
            customSpellSlot = new CustomSpellSlot();
            slots.put(template.getID(), customSpellSlot);
        }
        customSpellSlot.setSpellSlotTemplate(template);
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public SpellFormParams getSpellFormData()
    {
        SpellFormParams data = new SpellFormParams();
        data.setFactory(factory);

        for (CustomSpellStat stat : stats.values())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    @Override
    public List<SpellDebuffParams> getSpellEffectDataList()
    {
        return slots.values().stream()
            .filter(customSpellSlot -> customSpellSlot.getCustomSpellDebuff() != null)
            .map(CustomSpellSlot::getSpellEffectData)
            .collect(Collectors.toList());
    }

    // SPELL DEBUFF:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellDebuff getCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomSpellSlot slot = getCustomSpellSlot(slotID);
        return slot.getCustomSpellDebuff();
    }

    public void setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException
    {
        CustomSpellSlot slot = getCustomSpellSlot(slotID);
        slot.setCustomSpellDebuff(debuff);
    }

    public void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        CustomSpellSlot slot = getCustomSpellSlot(slotID);
        slot.removeCustomSpellDebuff();
    }

    private CustomSpellSlot getCustomSpellSlot(String slotID) throws InvalidIDException
    {
        CustomSpellSlot slot = slots.get(slotID);
        if (slot != null) return slot;
        else throw new  InvalidIDException("SpellSlot with the following ID doesn't exist: %s", slotID);
    }

    // STATS:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {
        CustomSpellStat stat = stats.get(statID);
        if (stat != null) return stat;
        else throw new InvalidIDException("SpellStat with the following ID doesn't exist: %s", statID);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormParams(getSpellFormData());
        entity.setSpellEffectData(getSpellEffectDataList());
        return entity;
    }

    public int getTotalCost()
    {
        return stats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum() +

            slots.values().stream()
                .mapToInt(CustomSpellSlot::getTotalCost)
                .sum();
    }
}