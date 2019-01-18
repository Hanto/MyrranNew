package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellSubformParams;
import com.myrran.spell.data.templatedata.SpellSubformTemplate;
import com.myrran.spell.entity.subform.SpellSubformFactory;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlotsDeco;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlots;
import com.myrran.spell.generators.custom.stats.CustomSpellStats;
import com.myrran.spell.generators.custom.stats.CustomSpellStatsDeco;

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

    public CustomSpellSubform(SpellSubformTemplate template)
    {   setSpellSubformTemplate(template); }

    public void setSpellSubformTemplate(SpellSubformTemplate template)
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
}
