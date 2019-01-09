package main.com.myrran.spell.generators.custom;

import main.com.myrran.spell.data.entityparams.SpellStatParams;
import main.com.myrran.spell.data.templatedata.SpellStatTemplate;

/** @author Ivan Delgado Huerta */
public class CustomSpellStat
{
    private String id;
    private String name;                                        //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float baseValue;                                    //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isUpgradeable = false;                      //Indica si es un SkillStat mejorable por Talentos
    private int maxUpgrades;                                    //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int upgradeCost;                                    //coste por mejorar cada punto de talento
    private float bonusPerUpgrade;                              //Valor con el que mejora el baseValue por punto de talento
    private float gearBonus;
    private int numUpgrades;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getID()                                       { return id;}
    public String getName()                                     { return name; }
    public float getBaseValue()                                 { return baseValue; }
    public boolean getIsUpgradeable()                           { return isUpgradeable; }
    public int getMaxUpgrades()                                 { return maxUpgrades; }
    public int getUpgradeCost()                                 { return upgradeCost; }
    public float getBonusPerUpgrade()                           { return bonusPerUpgrade; }
    public float getGearBonus()                                 { return gearBonus; }
    public int getNumUpgrades()                                 { return numUpgrades; }

    public CustomSpellStat setID(String id)                     { this.id = id; return this; }
    public CustomSpellStat setName(String name)                 { this.name = name; return this; }
    public CustomSpellStat setBaseValue(float baseValue)        { this.baseValue = baseValue; return this; }
    public CustomSpellStat setIsUpgradeable(boolean b)          { this.isUpgradeable = b; return this; }
    public CustomSpellStat setMaxUpgrades(int talentoMaximo)    { this.maxUpgrades = talentoMaximo; return this; }
    public CustomSpellStat setUpgradeCost(int upgradeCost)      { this.upgradeCost = upgradeCost; return this; }
    public CustomSpellStat setBonusPerUpgrade(float bonusPerUpgrade)  { this.bonusPerUpgrade = bonusPerUpgrade; return this; }
    public CustomSpellStat setGearBonus(float gearBonus)        { this.gearBonus = gearBonus; return this; }
    public CustomSpellStat setNumUpgrades(int numUpgrades)      { this.numUpgrades = numUpgrades; return this; }

    // TEMPLATE TO CUSTOM:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellStatTemplate(SpellStatTemplate data)
    {
        this.name = data.getName();
        this.baseValue = data.getBaseValue();
        this.isUpgradeable = data.getIsUpgradeable();
        this.maxUpgrades = data.getMaxUpgrades();
        this.upgradeCost = data.getUpgradeCost();
        this.bonusPerUpgrade = data.getBonusPerUpgrade();
    }

    // CUSTOM TO ENTITY DATA:
    //------------------------------------------------------------------------------------------------------------------

    public SpellStatParams getSpellStatData()
    {
        return new SpellStatParams()
            .setID(this.id)
            .setName(this.name)
            .setTotal(getTotal());
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public float getTotal()
    {   return baseValue + (numUpgrades * bonusPerUpgrade) + gearBonus; }

    public int getTotalCost()
    {   return numUpgrades * upgradeCost; }
}
