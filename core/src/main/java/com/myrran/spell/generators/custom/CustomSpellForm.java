package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.entity.form.SpellForm;
import com.myrran.spell.entity.form.SpellFormFactory;
import com.myrran.spell.generators.SpellFormGenerator;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlots;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlotsImp;
import com.myrran.spell.generators.custom.stats.CustomSpellStatsImp;
import com.myrran.spell.generators.custom.stats.CustomSpellStats;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellForm implements SpellFormGenerator, CustomDebuffSlots, CustomSpellStats, Identifiable
{
    private String id;
    private String name;
    private String templateID;
    private SpellFormFactory factory;
    private CustomSpellStatsImp spellStats = new CustomSpellStatsImp();
    private CustomDebuffSlotsImp debuffSlots = new CustomDebuffSlotsImp();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    @Override public String getName()                       { return name; }
    public String getTemplateID()                           { return templateID; }
    @Override public CustomSpellStatsImp getSpellStats()    { return spellStats; }
    @Override public CustomDebuffSlotsImp getDebuffSlots()  { return debuffSlots; }
    @Override public void setID(String id)                  { this.id = id; }
    @Override public void setName(String name)              { this.name = name; }

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

    // CUSTOM TO ENTITY PARAMS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public SpellFormParams getSpellFormParams()
    {
        return new SpellFormParams()
            .setFactory(factory)
            .setSpellStatParams(getSpellStatParams());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getTotalCost()
    {   return getStatsTotalCost() + getDebuffSlotsTotalCost(); }

    @Override
    public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormParams(getSpellFormParams());
        entity.setSpellEffectData(getSpellEffectParams());
        return entity;
    }
}