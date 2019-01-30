package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class CustomlHeaderView extends Table
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

    public WidgetText getFieldName()      { return name; }
    public WidgetText getBaseValue()      { return baseValue; }
    public WidgetText getUpgradesView()   { return upgradesView; }
    public WidgetText getTotal()          { return total; }
    public WidgetText getNumUpgrades()    { return numUpgrades; }
    public WidgetText getUpgradeCost()    { return upgradeCost; }
    public WidgetText getBonusPerUpgrade(){ return bonusPerUpgrade; }
    public WidgetText getMaxUpgrades()    { return maxUpgrades; }
    public WidgetText getGearBonus()      { return gearBonus; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomlHeaderView()
    {
        BitmapFont font11 = Atlas.get().getFont("10");
        Color pink = new Color(255/255f, 84/255f, 118/255f, 1);
        Color black = Color.BLACK;

        name            = new WidgetText("Name", font11, pink, black, 1);
        baseValue       = new WidgetText("Base", font11, pink, black, 1);
        upgradesView    = new WidgetText("Level",font11, pink, black, 1);
        total           = new WidgetText("Total",font11, pink, black, 1);
        numUpgrades     = new WidgetText("nv",   font11, pink, black, 1);
        upgradeCost     = new WidgetText("c",    font11, pink, black, 1);
        bonusPerUpgrade = new WidgetText("bon",  font11, pink, black, 1);
        maxUpgrades     = new WidgetText("mx",   font11, pink, black, 1);
        gearBonus       = new WidgetText("gear", font11, pink, black, 1);
        
        tableAddRow();
    }

    private void tableAddRow()
    {
        int vPad = -4;
        int hPad = +3;

        add(getFieldName()).left()        .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getBaseValue()).right()       .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getUpgradesView()).center()   .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getTotal()).right()           .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getNumUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getUpgradeCost()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getBonusPerUpgrade()).right() .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getMaxUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(getGearBonus()).right()       .padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }
}
