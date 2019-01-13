package com.myrran.spell.generators.custom;

import com.myrran.misc.Identifiable;
import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/** @author Ivan Delgado Huerta */
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellStat implements Identifiable
{
    private String id;
    private String name;                                        //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float baseValue;                                    //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isUpgradeable = false;                      //Indica si es un SkillStat mejorable por Talentos
    private int maxUpgrades;                                    //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int upgradeCost;                                    //coste por mejorar cada punto de talento
    private float bonusPerUpgrade;                              //Valor con el que mejora el baseValue por punto de talento
    private float gearBonus = 0;
    private int numUpgrades = 0;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public String getID()                                   { return id;}
    public String getName()                                 { return name; }
    public float getBaseValue()                             { return baseValue; }
    public boolean getIsUpgradeable()                       { return isUpgradeable; }
    public int getMaxUpgrades()                             { return maxUpgrades; }
    public int getUpgradeCost()                             { return upgradeCost; }
    public float getBonusPerUpgrade()                       { return bonusPerUpgrade; }
    public float getGearBonus()                             { return gearBonus; }
    public int getNumUpgrades()                             { return numUpgrades; }

    public void setID(String id)                            { this.id = id; }
    public void setName(String name)                        { this.name = name; }
    public void setBaseValue(float baseValue)               { this.baseValue = baseValue; }
    public void setIsUpgradeable(boolean b)                 { this.isUpgradeable = b; }
    public void setMaxUpgrades(int talentoMaximo)           { this.maxUpgrades = talentoMaximo; }
    public void setUpgradeCost(int upgradeCost)             { this.upgradeCost = upgradeCost; }
    public void setBonusPerUpgrade(float bonusPerUpgrade)   { this.bonusPerUpgrade = bonusPerUpgrade; }
    public void setGearBonus(float gearBonus)               { this.gearBonus = gearBonus; }
    public void setNumUpgrades(int numUpgrades)             { this.numUpgrades = numUpgrades; }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellStatTemplate(SpellStatTemplate data)
    {
        this.id = data.getID();
        this.name = data.getName();
        this.baseValue = data.getBaseValue();
        this.isUpgradeable = data.getIsUpgradeable();
        this.maxUpgrades = data.getMaxUpgrades();
        this.upgradeCost = data.getUpgradeCost();
        this.bonusPerUpgrade = data.getBonusPerUpgrade();
    }

    // CUSTOM TO ENTITY DATA:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatParams getSpellStatData()
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
}
