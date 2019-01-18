package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.misc.observable.Observable;
import com.myrran.misc.observable.ObservableDeco;
import com.myrran.misc.observable.ObservableI;
import com.myrran.spell.data.entityparams.SpellFormParams;
import com.myrran.spell.data.templatedata.SpellFormTemplate;
import com.myrran.spell.entity.form.SpellForm;
import com.myrran.spell.entity.form.SpellFormFactory;
import com.myrran.spell.generators.SpellFormGenerator;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlots;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlotsDeco;
import com.myrran.spell.generators.custom.stats.CustomSpellStats;
import com.myrran.spell.generators.custom.stats.CustomSpellStatsDeco;
import com.myrran.utils.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellForm implements SpellFormGenerator, Identifiable, CustomDebuffSlotsDeco,
    CustomSpellStatsDeco, ObservableDeco
{
    private String id;
    private String name;
    private String templateID;
    private SpellFormFactory factory;
    private CustomSpellStats spellStats = new CustomSpellStats();
    private CustomDebuffSlots debuffSlots = new CustomDebuffSlots();
    @XmlTransient
    private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id; }
    @Override public String getName()                       { return name; }
    public String getTemplateID()                           { return templateID; }
    @Override public CustomSpellStats getSpellStats()       { return spellStats; }
    @Override public CustomDebuffSlots getDebuffSlots()     { return debuffSlots; }
    @Override public ObservableI getObservable()            { return observable; }
    @Override public void setID(String id)                  { this.id = id; }
    @Override public void setName(String name)              { this.name = name; notifyFieldChange();}

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellForm() {}
    public CustomSpellForm(SpellFormTemplate template)
    {   setSpellFormTemplate(template); }

    @Override
    public void setSpellFormTemplate(SpellFormTemplate template)
    {
        id = template.getID();
        name = template.getName();
        templateID = template.getID();
        factory = template.getFactory();

        setSpellStatsTemplates(template.getSpellStats());
        setDebuffSlotsTemplate(template.getSpellSlots());
        notifyChange();
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

    @Override
    public boolean setCustomSpellDebuff(CustomSpellDebuff debuff, String slotID) throws InvalidIDException
    {
        boolean changed = debuffSlots.setCustomSpellDebuff(debuff, slotID);
        if (changed) notifyChange();
        return changed;
    }

    @Override
    public void removeCustomSpellDebuff(String slotID) throws InvalidIDException
    {
        debuffSlots.removeCustomSpellDebuff(slotID);
        notifyChange();
    }

    public void setNumUpgrades(String slotID, String statID, int upgrades) throws InvalidIDException
    {
        getCustomDebufflot(slotID).setNumUpgrades(statID, upgrades);
        notifyFieldChange();
    }

    public Integer getTotalCost()
    {   return getStatsTotalCost() + getDebuffSlotsTotalCost(); }

    @Override
    public SpellForm cast()
    {
        SpellForm entity = factory.getFormEntity();
        entity.setSpellFormParams(getSpellFormParams());
        entity.setSpellEffectData(getSpellEffectParams());
        return entity;
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyFieldChange()
    {   notify("fieldChange", null, null); }

    private void notifyChange()
    {   notify("fullChange", null, null); }
}