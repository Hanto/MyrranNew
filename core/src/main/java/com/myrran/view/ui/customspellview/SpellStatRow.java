package com.myrran.view.ui.customspellview;

/** @author Ivan Delgado Huerta */

import com.badlogic.gdx.scenes.scene2d.Actor;

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
