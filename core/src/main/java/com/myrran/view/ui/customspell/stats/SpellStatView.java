package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

/** @author Ivan Delgado Huerta */
public class SpellStatView implements PropertyChangeListener, SpellStatRow, Disposable
{
    private CustomSpellStat model;

    private WidgetText name;
    private WidgetText baseValue;
    private WidgetText total;
    private WidgetText numUpgrades;
    private WidgetText upgradeCost;
    private WidgetText bonusPerUpgrade;
    private WidgetText maxUpgrades;
    private WidgetText gearBonus;
    private SpellUpgradesView upgradesView;

    private static final Color white = Color.WHITE;
    private static final Color orange = Color.ORANGE;
    private static final Color purpleL = new Color(163/255f, 170/255f, 255/255f, 1);
    private static final Color purpleH = new Color(110/255f, 110/255f, 211/255f, 1);
    private static final Color black = Color.BLACK;
    private static final DecimalFormat df = Atlas.get().getFormater();

    // GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat getModel()                   { return model; }
    @Override public WidgetText getName()                 { return name; }
    @Override public WidgetText getBaseValue()            { return baseValue; }
    @Override public WidgetText getTotal()                { return total; }
    @Override public WidgetText getNumUpgrades()          { return numUpgrades; }
    @Override public WidgetText getUpgradeCost()          { return upgradeCost; }
    @Override public WidgetText getBonusPerUpgrade()      { return bonusPerUpgrade; }
    @Override public WidgetText getMaxUpgrades()          { return maxUpgrades; }
    @Override public WidgetText getGearBonus()            { return gearBonus; }
    @Override public SpellUpgradesView getUpgradesView(){ return upgradesView; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatView(CustomSpellStat customSpellStat)
    {
        this.model = customSpellStat;
        this.model.addObserver(this);

        createView();
        updateView();
    }

    @Override public void dispose()
    {   model.removeObserver(this); }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font14 = Atlas.get().getFont("14");
        BitmapFont font11 = Atlas.get().getFont("11");
        BitmapFont font10 = Atlas.get().getFont("10");

        name            = new WidgetText(font11, white,   black,1);
        baseValue       = new WidgetText(font14, orange,  black,1);
        total           = new WidgetText(font14, purpleH, black,1);
        numUpgrades     = new WidgetText(font10, purpleL, black,1);
        upgradeCost     = new WidgetText(font10, purpleL, black,1);
        bonusPerUpgrade = new WidgetText(font10, purpleL, black,1);
        maxUpgrades     = new WidgetText(font10, purpleL, black,1);
        gearBonus       = new WidgetText(font10, purpleL, black,1);

        upgradesView    = new SpellUpgradesView(model);
    }

    public void updateView()
    {
        name.setText(model.getName());
        baseValue.setText(df.format(model.getBaseValue()));
        total.setText(df.format(model.getTotal()));
        numUpgrades.setText(format(model.getNumUpgrades()));
        upgradeCost.setText(format(model.getUpgradeCost()));
        bonusPerUpgrade.setText(format(model.getBonusPerUpgrade()));
        maxUpgrades.setText(format(model.getMaxUpgrades()));
        gearBonus.setText(df.format(model.getGearBonus()));

        upgradesView.updateView();
    }

    private String format(Float rawData)
    {   return model.getIsUpgradeable() ? df.format(rawData) : "-"; }

    private String format(Integer rawData)
    {   return model.getIsUpgradeable() ? rawData.toString() : "-"; }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   updateView(); }
}