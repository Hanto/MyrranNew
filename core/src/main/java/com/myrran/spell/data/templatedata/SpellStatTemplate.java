package com.myrran.spell.data.templatedata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class SpellStatTemplate
{
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;                                                //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float baseValue;                                            //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isUpgradeable = false;                              //Indica si es un SkillStat mejorable por Talentos
    private int maxUpgrades;                                            //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int upgradeCost;                                            //coste por mejorar cada punto de talento
    private float bonusPerUpgrade;                                      //Valor con el que mejora el baseValue por punto de talento

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getID()                                               { return id;}
    public String getName()                                             { return name; }
    public float getBaseValue()                                         { return baseValue; }
    public boolean getIsUpgradeable()                                   { return isUpgradeable; }
    public int getMaxUpgrades()                                         { return maxUpgrades; }
    public int getUpgradeCost()                                         { return upgradeCost; }
    public float getBonusPerUpgrade()                                   { return bonusPerUpgrade; }

    public SpellStatTemplate setID(String id)                           { this.id = id; return this; }
    public SpellStatTemplate setName(String name)                       { this.name = name; return this; }
    public SpellStatTemplate setBaseValue(float baseValue)              { this.baseValue = baseValue; return this; }
    public SpellStatTemplate setIsUpgradeable(boolean b)                { this.isUpgradeable = b; return this; }
    public SpellStatTemplate setMaxUpgrades(int talentoMaximo)          { this.maxUpgrades = talentoMaximo; return this; }
    public SpellStatTemplate setUpgradeCost(int upgradeCost)            { this.upgradeCost = upgradeCost; return this; }
    public SpellStatTemplate setBonusPerUpgrade(float bonusPerUpgrade)  { this.bonusPerUpgrade = bonusPerUpgrade; return this; }
}
