package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.myrran.model.spell.generators.SpellStatI;
import com.myrran.view.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.text.DecimalFormat;

/** @author Ivan Delgado Huerta */
public class TemplateStatView extends Table
{
    private SpellStatI model;

    private WidgetText name;
    private WidgetText baseValue;
    private WidgetText total;
    private WidgetText upgradeCost;
    private WidgetText bonusPerUpgrade;
    private WidgetText maxUpgrades;

    private static final DecimalFormat df = Atlas.get().getFormater();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateStatView(SpellStatI model)
    {
        this();
        setModel(model);
    }

    public TemplateStatView()
    {
        BitmapFont font14 = Atlas.get().getFont("14");
        BitmapFont font12 = Atlas.get().getFont("Calibri12");
        BitmapFont font10 = Atlas.get().getFont("10");
        Color white = Color.WHITE;
        Color orange = Color.ORANGE;
        Color purpleL = new Color(163/255f, 170/255f, 255/255f, 1);
        Color purpleH = new Color(110/255f, 110/255f, 211/255f, 1);
        Color black = Color.BLACK;

        name            = new WidgetText(font12, white,   black,1);
        baseValue       = new WidgetText(font14, orange,  black,1);
        total           = new WidgetText(font14, purpleH, black,1);
        upgradeCost     = new WidgetText(font10, purpleL, black,1);
        bonusPerUpgrade = new WidgetText(font10, purpleL, black,1);
        maxUpgrades     = new WidgetText(font10, purpleL, black,1);

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

        add(name).bottom().left()            .minWidth(90).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).bottom().right()      .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).bottom().right()          .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(maxUpgrades).bottom().right()    .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).bottom().right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).bottom().right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }

    private String format(Float rawData)
    {   return model.getIsUpgradeable() ? df.format(rawData) : "-"; }

    private String format(Integer rawData)
    {   return model.getIsUpgradeable() ? rawData.toString() : "-"; }
}

