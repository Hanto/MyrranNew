package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.model.spell.generators.SpellStatI;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.customspell.CustomUBarView;
import com.myrran.view.ui.widgets.WidgetText;

import java.text.DecimalFormat;

/** @author Ivan Delgado Huerta */
public class TStatView extends Table
{
    private SpellStatI model;

    private WidgetText name;
    private WidgetText baseValue;
    private WidgetText total;
    private WidgetText numUpgrades;
    private WidgetText upgradeCost;
    private WidgetText bonusPerUpgrade;
    private WidgetText maxUpgrades;
    private WidgetText gearBonus;
    private CustomUBarView upgradesView;

    private static final DecimalFormat df = Atlas.get().getFormater();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TStatView(SpellStatI model)
    {
        this();
        setModel(model);
    }

    public TStatView()
    {
        BitmapFont font14 = Atlas.get().getFont("14");
        BitmapFont font11 = Atlas.get().getFont("11");
        BitmapFont font10 = Atlas.get().getFont("10");
        Color white = Color.WHITE;
        Color orange = Color.ORANGE;
        Color purpleL = new Color(163/255f, 170/255f, 255/255f, 1);
        Color purpleH = new Color(110/255f, 110/255f, 211/255f, 1);
        Color black = Color.BLACK;

        name            = new WidgetText(font11, white,   black,1);
        baseValue       = new WidgetText(font14, orange,  black,1);
        total           = new WidgetText(font14, purpleH, black,1);
        numUpgrades     = new WidgetText(font10, purpleL, black,1);
        upgradeCost     = new WidgetText(font10, purpleL, black,1);
        bonusPerUpgrade = new WidgetText(font10, purpleL, black,1);
        maxUpgrades     = new WidgetText(font10, purpleL, black,1);
        gearBonus       = new WidgetText(font10, purpleL, black,1);
        upgradesView    = new CustomUBarView();

        createLayout();
    }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(SpellStatI templateSpellStat)
    {
        if (templateSpellStat == null)
            removeModel();
        else
        {
            model = templateSpellStat;
            update();
        }
    }

    private void removeModel()
    {
        name.setText(null);
        baseValue.setText(null);
        upgradeCost.setText(null);
        bonusPerUpgrade.setText(null);
        maxUpgrades.setText(null);
    }

    public void update()
    {
        name.setText(model.getName());
        baseValue.setText(df.format(model.getBaseValue()));;
        total.setText(df.format(model.getBaseValue() + model.getMaxUpgrades() * model.getBonusPerUpgrade()));
        upgradeCost.setText(format(model.getUpgradeCost()));
        bonusPerUpgrade.setText(format(model.getBonusPerUpgrade()));
        maxUpgrades.setText(format(model.getMaxUpgrades()));
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        int vPad = -4;
        int hPad = +3;

        add(name).left()            .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).right()      .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).right()          .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).right()    .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).right().padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).right()    .padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }

    private String format(Float rawData)
    {   return model.getIsUpgradeable() ? df.format(rawData) : "-"; }

    private String format(Integer rawData)
    {   return model.getIsUpgradeable() ? rawData.toString() : "-"; }
}

