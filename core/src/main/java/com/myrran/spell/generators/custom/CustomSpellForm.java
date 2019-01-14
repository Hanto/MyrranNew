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
    private Map<String, CustomSpellStat> customSpellStats = new HashMap<>();
    private Map<String, CustomSpellSlot> customSpellSlots = new HashMap<>();
    private SpellFormFactory factory;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellForm.class);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    @Override public String getName()                           { return name; }
    public String getTemplateID()                               { return templateID; }
    public Map<String, CustomSpellStat> getCustomSpellStats()   { return customSpellStats; }
    public Map<String, CustomSpellSlot> getCustomSpellSlots()   { return customSpellSlots; }

    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setName(String name)                  { this.name = name; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    @Override public void setSpellFormTemplate(SpellFormTemplate spellFormTemplate)
    {
        id = spellFormTemplate.getID();
        name = spellFormTemplate.getName();
        templateID = spellFormTemplate.getID();
        factory = spellFormTemplate.getFactory();

        spellFormTemplate.getSpellStats()
            .forEach(this::setSpellStatTemplate);

        spellFormTemplate.getSpellSlots()
            .forEach(this::setSpellSlotTemplate);
    }

    private void setSpellStatTemplate(SpellStatTemplate template)
    {
        CustomSpellStat customSpellStat = customSpellStats.get(template.getID());
        if (customSpellStat == null)
        {
            customSpellStat = new CustomSpellStat();
            customSpellStats.put(template.getID(), customSpellStat);
        }
        customSpellStat.setSpellStatTemplate(template);
    }

    private void setSpellSlotTemplate(SpellSlotTemplate template)
    {
        CustomSpellSlot customSpellSlot = customSpellSlots.get(template.getID());
        if (customSpellSlot == null)
        {
            customSpellSlot = new CustomSpellSlot();
            customSpellSlots.put(template.getID(), customSpellSlot);
        }
        customSpellSlot.setSpellSlotTemplate(template);
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellFormParams getSpellFormData()
    {
        SpellFormParams data = new SpellFormParams();
        data.setFactory(factory);

        for (CustomSpellStat stat: getCustomSpellStats().values())
            data.addStat(stat.getSpellStatData());

        return data;
    }

    @Override public List<SpellDebuffParams> getSpellEffectDataList()
    {
        return customSpellSlots.values().stream()
            .filter(customSpellSlot -> customSpellSlot.getCustomSpellDebuff() != null)
            .map(CustomSpellSlot::getSpellEffectData)
            .collect(Collectors.toList());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormParams(getSpellFormData());
        entity.setSpellEffectData(getSpellEffectDataList());
        return entity;
    }

    public int getTotalCost()
    {
        return customSpellStats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum() +

        customSpellSlots.values().stream()
            .mapToInt(CustomSpellSlot::getTotalCost)
            .sum();
    }

    public void setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID)
    {
        CustomSpellSlot slot = customSpellSlots.get(slotID);

        if (slot!=null)
            slot.setCustomSpellDebuff(debuff);

        else
            LOG.warn("SpellSlot with the following ID doesn't exist: %s", slotID);
    }

    public void removeCustomSpellDebuff(String slotID)
    {
        CustomSpellSlot slot = customSpellSlots.get(slotID);

        if (slot!=null)
            slot.removeCustomSpellDebuff();

        else
            LOG.warn("SpellSlot with the following ID doesn't exist: %s", slotID);
    }
}