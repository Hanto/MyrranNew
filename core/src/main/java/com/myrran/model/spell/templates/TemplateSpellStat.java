package com.myrran.model.spell.templates;

import com.myrran.model.components.Identifiable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class TemplateSpellStat implements Identifiable
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;                                    //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float baseValue;                                //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isUpgradeable = false;                  //Indica si es un SkillStat mejorable por Talentos
    private int maxUpgrades;                                //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int upgradeCost;                                //coste por mejorar cada punto de talento
    private float bonusPerUpgrade;                          //Valor con el que mejora el baseValue por punto de talento

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public String getID()                         { return id;}
    public String getName()                                 { return name; }
    public float getBaseValue()                             { return baseValue; }
    public boolean getIsUpgradeable()                       { return isUpgradeable; }
    public int getMaxUpgrades()                             { return maxUpgrades; }
    public int getUpgradeCost()                             { return upgradeCost; }
    public float getBonusPerUpgrade()                       { return bonusPerUpgrade; }

    @Override public void setID(String id)                  { this.id = id; }
    public void setName(String name)                        { this.name = name; }
    public void setBaseValue(float baseValue)               { this.baseValue = baseValue; }
    public void setIsUpgradeable(boolean b)                 { this.isUpgradeable = b; }
    public void setMaxUpgrades(int talentoMaximo)           { this.maxUpgrades = talentoMaximo; }
    public void setUpgradeCost(int upgradeCost)             { this.upgradeCost = upgradeCost; }
    public void setBonusPerUpgrade(float bonusPerUpgrade)   { this.bonusPerUpgrade = bonusPerUpgrade; }
}