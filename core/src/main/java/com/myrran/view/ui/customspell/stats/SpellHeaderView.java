package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.TextView;

/** @author Ivan Delgado Huerta */
public class SpellHeaderView implements SpellStatRow
{
    private TextView name;
    private TextView baseValue;
    private TextView upgradesView;
    private TextView total;
    private TextView numUpgrades;
    private TextView upgradeCost;
    private TextView bonusPerUpgrade;
    private TextView maxUpgrades;
    private TextView gearBonus;

    @Override public TextView getName()             { return name; }
    @Override public TextView getBaseValue()        { return baseValue; }
    @Override public TextView getUpgradesView()     { return upgradesView; }
    @Override public TextView getTotal()            { return total; }
    @Override public TextView getNumUpgrades()      { return numUpgrades; }
    @Override public TextView getUpgradeCost()      { return upgradeCost; }
    @Override public TextView getBonusPerUpgrade()  { return bonusPerUpgrade; }
    @Override public TextView getMaxUpgrades()      { return maxUpgrades; }
    @Override public TextView getGearBonus()        { return gearBonus; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellHeaderView()
    {
        BitmapFont font11 = Atlas.get().getFont("11");
        Color pink = new Color(255/255f, 84/255f, 118/255f, 1);
        Color black = Color.BLACK;

        name            = new TextView("Name", font11, pink, black, 1);
        baseValue       = new TextView("Base", font11, pink, black, 1);
        upgradesView    = new TextView("Level",font11, pink, black, 1);
        total           = new TextView("Total",font11, pink, black, 1);
        numUpgrades     = new TextView("nv",   font11, pink, black, 1);
        upgradeCost     = new TextView("c",    font11, pink, black, 1);
        bonusPerUpgrade = new TextView("bon",  font11, pink, black, 1);
        maxUpgrades     = new TextView("mx",   font11, pink, black, 1);
        gearBonus       = new TextView("gear", font11, pink, black, 1);
    }
}
