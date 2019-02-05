package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

/** @author Ivan Delgado Huerta */
public class StatHeader extends Table
{
    private int vPad = -4;
    private int hPad = +3;
    private BitmapFont font11 = Atlas.get().getFont("11");
    private BitmapFont font10 = Atlas.get().getFont("10");
    private Color orange = Color.ORANGE;

    private WidgetText name            = new WidgetText("Name", font11, orange,   Color.BLACK,1);
    private WidgetText baseValue       = new WidgetText("Min", font10, orange,  Color.BLACK,1);
    private WidgetText upgradesView    = new WidgetText("Upgrades", font10, orange,  Color.BLACK,1);
    private WidgetText total           = new WidgetText("Max", font10, orange, Color.BLACK,1);
    private WidgetText numUpgrades     = new WidgetText("ups", font10, orange, Color.BLACK,1);
    private WidgetText upgradeCost     = new WidgetText("cost", font10, orange, Color.BLACK,1);
    private WidgetText bonusPerUpgrade = new WidgetText("bon", font10, orange, Color.BLACK,1);
    private WidgetText maxUpgrades     = new WidgetText("ups", font10, orange, Color.BLACK,1);
    private WidgetText gearBonus       = new WidgetText("Max", font10, orange, Color.BLACK,1);

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public Table createTemplateStatsHeader()
    {
        padLeft(4);
        add(name).left()            .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
        return this;
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    public Table createCustomStatsHeader()
    {
        padLeft(4);
        add(name).left()            .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradesView).center()  .minWidth(75).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(numUpgrades).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(gearBonus).right()      .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
        return this;
    }

    public Table clearTable()
    {   clear(); return this;}
}
