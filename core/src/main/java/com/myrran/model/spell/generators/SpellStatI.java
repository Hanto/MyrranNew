package com.myrran.model.spell.generators;

/** @author Ivan Delgado Huerta */
public interface SpellStatI
{
    String getName();
    Float getBaseValue();
    boolean getIsUpgradeable();
    Integer getMaxUpgrades();
    Integer getUpgradeCost();
    Float getBonusPerUpgrade();

    default Float getGearBonus()        { return 0f; }
    default Integer getNumUpgrades()    { return 0; }
    default Float getTotal()            { return 0f; }
}
