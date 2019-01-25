package com.myrran.model.spell.generators.custom;

import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.generators.SpellDebuffGenerator;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.parameters.SpellDebuffParams;
import com.myrran.model.spell.entities.debuff.SpellDebuffFactory;
import com.myrran.misc.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellDebuff implements ObservableDeco, SpellDebuffGenerator, CustomSpellStatsDeco, Identifiable
{
    private String id = UUID.randomUUID().toString();
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String templateID;
    private int baseCost;
    private SpellDebuffFactory factory;
    private List<CustomSpellSlotKey> keys;
    private CustomSpellStats spellStats = new CustomSpellStats();
    @XmlTransient
    private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                             { return id; }
    @Override public String getName()                           { return name; }
    public Integer getBaseCost()                                { return baseCost; }
    public String getTemplateID()                               { return templateID; }
    public SpellDebuffFactory getFactory()                      { return factory; }
    public List<CustomSpellSlotKey> getKeys()                   { return keys;  }
    public boolean hasDebuff()                                  { return templateID != null; }
    @Override public CustomSpellStats getSpellStats()           { return spellStats; }
    @Override public void setID(String id)                      { this.id = id; }
    @Override public void setName(String name)                  { this.name = name; notifyChanges(); }
    public void setKeys(CustomSpellSlotKey... keys)             { this.keys = Arrays.asList(keys); notifyChanges(); }
    @Override public ObservableI getObservable()                { return observable; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellDebuff() { }
    public CustomSpellDebuff(TemplateSpellDebuff template)
    {   setSpellDebuffTemplate(template); }

    @Override public void setSpellDebuffTemplate(TemplateSpellDebuff template)
    {
        if (template != null)
        {
            name = template.getName();
            templateID = template.getID();
            baseCost = template.getBaseCost();
            factory = template.getFactory();
            keys = template.getKeys();
            spellStats.setSpellStatsTemplates(template.getSpellStats());
        }
        else
        {
            name = null;
            templateID = null;
            baseCost = 0;
            factory = null;
            keys = null;
            spellStats.values().clear();
        }
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffParams getSpellEffectData()
    {
        return new SpellDebuffParams()
            .setFactory(factory)
            .setSpellStatParams(getSpellStatParams());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public Integer getTotalCost()
    {   return getStatsTotalCost() + baseCost; }

    public Integer getStatsCost()
    {   return getStatsTotalCost(); }

    @Override public void setNumUpgrades(String statID, int numUpgrades) throws InvalidIDException
    {
        spellStats.setNumUpgrades(statID, numUpgrades);
        notifyChanges();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChanges()
    {   notify("spellDebuff", null, null); }
}