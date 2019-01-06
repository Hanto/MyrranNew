package main.com.myrran.spell;

import main.com.myrran.spell.data.SpellStatTemplate;

public class SpellStat
{
    private String id;
    private String name;                                //Nombre del SkillStat: por ej: "Daño, velocidad, Casting Time"
    private float baseValue;                            //Valor Base del SkillStat: por ej: 100 de Daño
    private boolean isUpgradeable = false;              //Indica si es un SkillStat mejorable por Talentos
    private int maxUpgrades;                            //numero de Talentos maximos que se pueden gastar en este SkillStat
    private int upgradeCost;                            //coste por mejorar cada punto de talento
    private float bonusPerUpgrade;                      //Valor con el que mejora el baseValue por punto de talento

    private float gearBonus;
    private int numUpgrades;

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public String getID()                               { return id;}
    public String getName()                             { return name; }
    public float getBaseValue()                         { return baseValue; }
    public boolean getIsUpgradeable()                   { return isUpgradeable; }
    public int getMaxUpgrades()                         { return maxUpgrades; }
    public int getUpgradeCost()                         { return upgradeCost; }
    public float getBonusPerUpgrade()                   { return bonusPerUpgrade; }

    public float getGearBonus()                         { return gearBonus; }
    public int getNumUpgrades()                         { return numUpgrades; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public SpellStat setID(String id)                   { this.id = id; return this; }
    public SpellStat setName(String name)               { this.name = name; return this; }
    public SpellStat setBaseValue(float baseValue)      { this.baseValue = baseValue; return this; }
    public SpellStat setIsUpgradeable(boolean b)        { this.isUpgradeable = b; return this; }
    public SpellStat setMaxUpgrades(int talentoMaximo)  { this.maxUpgrades = talentoMaximo; return this; }
    public SpellStat setUpgradeCost(int upgradeCost)    { this.upgradeCost = upgradeCost; return this; }
    public SpellStat setBonusPerUpgrade(float bonusPerUpgrade)  { this.bonusPerUpgrade = bonusPerUpgrade; return this; }

    public SpellStat setGearBonus(float gearBonus)      { this.gearBonus = gearBonus; return this; }
    public SpellStat setNumUpgrades(int numUpgrades)    { this.numUpgrades = numUpgrades; return this; }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

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

    public float getTotal()
    {   return baseValue + (numUpgrades * bonusPerUpgrade) + gearBonus; }
}
