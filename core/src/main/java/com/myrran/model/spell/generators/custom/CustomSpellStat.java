package com.myrran.model.spell.generators.custom;

import com.myrran.model.components.Identifiable;
import com.myrran.model.components.observable.Observable;
import com.myrran.model.components.observable.ObservableDeco;
import com.myrran.model.components.observable.ObservableI;
import com.myrran.model.spell.parameters.SpellStatParams;
import com.myrran.model.spell.templates.TemplateSpellStat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellStat implements Identifiable, ObservableDeco
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;                    //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private Float baseValue;                //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isUpgradeable = false;  //Indica si es un SkillStat mejorable por Talentos
    private Integer maxUpgrades;            //numero de Talentos maximos que se pueden gastar en este SkillStat
    private Integer upgradeCost;            //coste por mejorar cada punto de talento
    private Float bonusPerUpgrade;          //Valor con el que mejora el baseValue por punto de talento
    private Float gearBonus = 0f;
    private Integer numUpgrades = 0;
    @XmlTransient
    private ObservableI observable = new Observable(this);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public String getID()                                   { return id;}
    public String getName()                                 { return name; }
    public Float getBaseValue()                             { return baseValue; }
    public boolean getIsUpgradeable()                       { return isUpgradeable; }
    public Integer getMaxUpgrades()                         { return maxUpgrades; }
    public Integer getUpgradeCost()                         { return upgradeCost; }
    public Float getBonusPerUpgrade()                       { return bonusPerUpgrade; }
    public Float getGearBonus()                             { return gearBonus; }
    public Integer getNumUpgrades()                         { return numUpgrades; }

    public void setID(String id)                            { this.id = id; }
    public void setName(String name)                        { this.name = name; notifyChanges();}
    public void setBaseValue(float baseValue)               { this.baseValue = baseValue; notifyChanges();}
    public void setIsUpgradeable(boolean b)                 { this.isUpgradeable = b; notifyChanges();}
    public void setMaxUpgrades(int talentoMaximo)           { this.maxUpgrades = talentoMaximo; notifyChanges();}
    public void setUpgradeCost(int upgradeCost)             { this.upgradeCost = upgradeCost; notifyChanges();}
    public void setBonusPerUpgrade(float bonusPerUpgrade)   { this.bonusPerUpgrade = bonusPerUpgrade; notifyChanges();}
    public void setGearBonus(float gearBonus)               { this.gearBonus = gearBonus; notifyChanges();}
    public void setNumUpgrades(int numUpgrades)             { this.numUpgrades = numUpgrades; notifyChanges();}
    @Override public ObservableI getObservable()            { return observable; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat() {}
    public CustomSpellStat(TemplateSpellStat template)
    {   setSpellStatTemplate(template); }

    public void setSpellStatTemplate(TemplateSpellStat template)
    {
        this.id = template.getID();
        this.name = template.getName();
        this.baseValue = template.getBaseValue();
        this.isUpgradeable = template.getIsUpgradeable();
        this.maxUpgrades = template.getMaxUpgrades();
        this.upgradeCost = template.getUpgradeCost();
        this.bonusPerUpgrade = template.getBonusPerUpgrade();
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatParams getSpellStatParams()
    {
        return new SpellStatParams()
            .setID(this.id)
            .setName(this.name)
            .setTotal(getTotal());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public float getTotal()
    {   return baseValue + (numUpgrades * bonusPerUpgrade) + gearBonus; }

    public int getTotalCost()
    {   return numUpgrades * upgradeCost; }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    private void notifyChanges()
    {   notify("stat", null, null); }
}
