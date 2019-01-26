package com.myrran.model.spell.generators;

import com.myrran.model.components.Identifiable;
import com.myrran.model.spell.parameters.SpellSubformParams;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.model.spell.entities.subform.SpellSubformFactory;
import com.myrran.misc.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellSubform implements Identifiable, CustomDebuffSlotsDeco, CustomSpellStatsDeco
{
    private String id;
    private String name;
    private String templateID;
    private SpellSubformFactory factory;
    private CustomSpellStats spellStats = new CustomSpellStats();
    private CustomDebuffSlots debuffSlots = new CustomDebuffSlots();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                     { return id; }
    public String getName()                             { return name; }
    public String getTemplateID()                       { return templateID; }
    @Override public CustomSpellStats getSpellStats()   { return spellStats; }
    @Override public CustomDebuffSlots getDebuffSlots() { return debuffSlots; }
    @Override public void setID(String id)              { this.id = id; }
    public void setName(String name)                    { this.name = name; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellSubform(TemplateSpellSubform template)
    {   setSpellSubformTemplate(template); }

    public void setSpellSubformTemplate(TemplateSpellSubform template)
    {
        id = template.getID();
        name = template.getID();
        templateID = template.getID();
        factory = template.getFactory();

        setSpellStatsTemplates(template.getSpellStats());
        setDebuffSlotsTemplate(template.getSpellSlots());
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellSubformParams getSpellFormData()
    {
        return new SpellSubformParams()
            .setFactory(factory)
            .setSpellStatParams(getSpellStatParams());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {   return getStatsTotalCost() + getDebuffSlotsTotalCost(); }

    @Override
    public void setNumUpgrades(String statID, int numUpgrades) throws InvalidIDException
    {   spellStats.setNumUpgrades(statID, numUpgrades); }
}
