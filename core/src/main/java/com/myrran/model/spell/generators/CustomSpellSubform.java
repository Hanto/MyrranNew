package com.myrran.model.spell.generators;

import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.parameters.SpellSubformParams;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.model.spell.entities.subform.SpellSubformFactory;
import com.myrran.misc.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;
import java.util.UUID;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellSubform implements Identifiable, CustomDebuffSlotsDeco, CustomSpellStatsDeco
{
    private String id = UUID.randomUUID().toString();
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String templateID;
    private int baseCost;
    private SpellSubformFactory factory;
    private List<CustomSpellSlotKey> keys;
    private CustomSpellStats spellStats = new CustomSpellStats();
    private CustomDebuffSlots debuffSlots = new CustomDebuffSlots();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                     { return id; }
    public String getName()                             { return name; }
    public String getTemplateID()                       { return templateID; }
    public Integer getBaseCost()                        { return baseCost; }
    @Override public CustomSpellStatsI getSpellStats()   { return spellStats; }
    @Override public CustomDebuffSlotsI getDebuffSlots(){ return debuffSlots; }
    public boolean hasData()                            { return templateID != null; }
    @Override public void setID(String id)              { this.id = id; }
    public void setName(String name)                    { this.name = name; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellSubform() {}
    public CustomSpellSubform(TemplateSpellSubform template)
    {   setSpellSubformTemplate(template); }

    public void setSpellSubformTemplate(TemplateSpellSubform template)
    {
        if (template != null)
        {
            name = template.getID();
            templateID = template.getID();
            baseCost = template.getBaseCost();
            factory = template.getFactory();
            keys = template.getKeys();

            setSpellStatsTemplates(template.getSpellStats());
            setDebuffSlotsTemplate(template.getSpellSlots());
        }
        else
        {
            name = null;
            templateID = null;
            baseCost = 0;
            factory = null;
            keys = null;
            spellStats.getCustomSpellStats().clear();
            debuffSlots.getCustomDebuffSlots().clear();
        }
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellSubformParams getSpellFormData()
    {
        getSpellEffectParams();

        return new SpellSubformParams()
            .setFactory(factory)
            .setSpellStatParams(getSpellStatParams())
            .setSpellDebuffParams(getSpellEffectParams());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public Integer getTotalCost()
    {   return getStatsTotalCost() + getDebuffSlotsTotalCost() + baseCost; }

    public Integer getStatCost()
    {   return getStatsTotalCost(); }

    @Override
    public void setNumUpgrades(String statID, int numUpgrades) throws InvalidIDException
    {   spellStats.setNumUpgrades(statID, numUpgrades); }
}
