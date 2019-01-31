package com.myrran.model.spell.generators;

import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.parameters.SpellFormParams;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.model.spell.entities.form.SpellForm;
import com.myrran.model.spell.entities.form.SpellFormFactory;
import com.myrran.misc.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.UUID;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellForm implements SpellFormGenerator, Identifiable,
    CustomDebuffSlotsDeco, CustomSpellStatsDeco, ObservableDeco, CustomFormI
{
    private String id = UUID.randomUUID().toString();
    private String name;
    private String templateID;
    private SpellFormFactory factory;
    private CustomSpellStats spellStats = new CustomSpellStats();
    private CustomDebuffSlots debuffSlots = new CustomDebuffSlots();
    private CustomSubformSlots subformSlots = new CustomSubformSlots();
    @XmlTransient private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    @Override public String getName()                       { return name; }
    public String getTemplateID()                           { return templateID; }
    @Override public CustomSpellStatsI getSpellStats()      { return spellStats; }
    @Override public CustomDebuffSlotsI getDebuffSlots()    { return debuffSlots; }
    public CustomSubformSlots getSubformSlots()             { return subformSlots; }
    @Override public ObservableI getObservable()            { return observable; }
    @Override public void setID(String id)                  { this.id = id; }
    @Override public void setName(String name)              { this.name = name; notifyFieldChange();}

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellForm() {}
    public CustomSpellForm(TemplateSpellForm template)
    {   setSpellFormTemplate(template); }

    @Override
    public void setSpellFormTemplate(TemplateSpellForm template)
    {
        name = template.getName();
        templateID = template.getID();
        factory = template.getFactory();

        setSpellStatsTemplates(template.getSpellStats());
        setDebuffSlotsTemplate(template.getSpellDebuffs());
        subformSlots.setSubformSlotsTemplate(template.getSpellSubforms());
        notifyChange();
    }

    // CUSTOM TO ENTITY PARAMS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public SpellFormParams getSpellFormParams()
    {
        return new SpellFormParams()
            .setFactory(factory)
            .setSpellStatParams(getSpellStatParams())
            .setSpellDebuffParams(getSpellEffectParams());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public Integer getTotalCost()
    {   return getStatsTotalCost() + getDebuffSlotsTotalCost() + subformSlots.getSubFormsTotalCost(); }

    public Integer getStatsCost()
    {   return getStatsTotalCost(); }

    @Override
    public void setNumUpgrades(String statID, int upgrades) throws InvalidIDException
    {
        getCustomSpellStat(statID).setNumUpgrades(upgrades);
        notifyFieldChange();
    }

    @Override
    public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormParams(getSpellFormParams());
        return entity;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyFieldChange()
    {   notify("fieldChange", null, null); }

    private void notifyChange()
    {   notify("fullChange", null, null); }
}