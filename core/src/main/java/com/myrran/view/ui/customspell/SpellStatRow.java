package com.myrran.view.ui.customspell;

import com.badlogic.gdx.scenes.scene2d.Actor;

/** @author Ivan Delgado Huerta */
public interface SpellStatRow
{
    Actor getName();
    Actor getBaseValue();
    Actor getUpgradesView();
    Actor getTotal();
    Actor getNumUpgrades();
    Actor getUpgradeCost();
    Actor getBonusPerUpgrade();
    Actor getMaxUpgrades();
    Actor getGearBonus();
}
