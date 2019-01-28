package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.myrran.model.spell.templates.TemplateSpellStat;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.text.DecimalFormat;

/** @author Ivan Delgado Huerta */
public class TemplateStatView
{
    private TemplateSpellStat model;

    private WidgetText name;
    private WidgetText baseValue;
    private WidgetText maxValue;
    private WidgetText upgradeCost;
    private WidgetText bonusPerUpgrade;
    private WidgetText maxUpgrades;

    private static final BitmapFont font14 = Atlas.get().getFont("14");
    private static final BitmapFont font11 = Atlas.get().getFont("11");
    private static final BitmapFont font10 = Atlas.get().getFont("10");
    private static final Color white = Color.WHITE;
    private static final Color orange = Color.ORANGE;
    private static final Color purpleL = new Color(163/255f, 170/255f, 255/255f, 1);
    private static final Color purpleH = new Color(110/255f, 110/255f, 211/255f, 1);
    private static final Color black = Color.BLACK;
    private static final DecimalFormat df = Atlas.get().getFormater();

    // GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public WidgetText getName()                 { return name; }
    public WidgetText getBaseValue()            { return baseValue; }
    public WidgetText getMaxValue()             { return maxValue; }
    public WidgetText getUpgradeCost()          { return upgradeCost; }
    public WidgetText getBonusPerUpgrade()      { return bonusPerUpgrade; }
    public WidgetText getMaxUpgrades()          { return maxUpgrades; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateStatView()
    {
        name            = new WidgetText(font11, white,   black,1);
        baseValue       = new WidgetText(font14, orange,  black,1);
        maxValue        = new WidgetText(font14, purpleH, black,1);
        upgradeCost     = new WidgetText(font10, purpleL, black,1);
        bonusPerUpgrade = new WidgetText(font10, purpleL, black,1);
        maxUpgrades     = new WidgetText(font10, purpleL, black,1);
    }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellStat templateSpellStat)
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
        maxValue.setText(df.format(model.getBaseValue() + model.getMaxUpgrades()));
        upgradeCost.setText(format(model.getUpgradeCost()));
        bonusPerUpgrade.setText(format(model.getBonusPerUpgrade()));
        maxUpgrades.setText(format(model.getMaxUpgrades()));
    }

    private String format(Float rawData)
    {   return model.getIsUpgradeable() ? df.format(rawData) : "-"; }

    private String format(Integer rawData)
    {   return model.getIsUpgradeable() ? rawData.toString() : "-"; }
    
}

