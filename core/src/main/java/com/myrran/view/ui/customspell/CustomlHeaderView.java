package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class CustomlHeaderView implements SpellStatRow
{
    private WidgetText name;
    private WidgetText baseValue;
    private WidgetText upgradesView;
    private WidgetText total;
    private WidgetText numUpgrades;
    private WidgetText upgradeCost;
    private WidgetText bonusPerUpgrade;
    private WidgetText maxUpgrades;
    private WidgetText gearBonus;

    @Override public WidgetText getName()             { return name; }
    @Override public WidgetText getBaseValue()        { return baseValue; }
    @Override public WidgetText getUpgradesView()     { return upgradesView; }
    @Override public WidgetText getTotal()            { return total; }
    @Override public WidgetText getNumUpgrades()      { return numUpgrades; }
    @Override public WidgetText getUpgradeCost()      { return upgradeCost; }
    @Override public WidgetText getBonusPerUpgrade()  { return bonusPerUpgrade; }
    @Override public WidgetText getMaxUpgrades()      { return maxUpgrades; }
    @Override public WidgetText getGearBonus()        { return gearBonus; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomlHeaderView()
    {
        BitmapFont font11 = Atlas.get().getFont("11");
        Color pink = new Color(255/255f, 84/255f, 118/255f, 1);
        Color black = Color.BLACK;

        name            = new WidgetText("NAME", font11, pink, black, 1);
        baseValue       = new WidgetText("Base", font11, pink, black, 1);
        upgradesView    = new WidgetText("Level",font11, pink, black, 1);
        total           = new WidgetText("Total",font11, pink, black, 1);
        numUpgrades     = new WidgetText("nv",   font11, pink, black, 1);
        upgradeCost     = new WidgetText("c",    font11, pink, black, 1);
        bonusPerUpgrade = new WidgetText("bon",  font11, pink, black, 1);
        maxUpgrades     = new WidgetText("mx",   font11, pink, black, 1);
        gearBonus       = new WidgetText("gear", font11, pink, black, 1);
    }
}
