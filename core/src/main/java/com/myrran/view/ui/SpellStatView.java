package com.myrran.view.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/** @author Ivan Delgado Huerta */
public class SpellStatView implements PropertyChangeListener, Disposable
{
    private CustomSpellStat model;

    private TextView name;
    private TextView baseValue;
    private TextView total;
    private TextView numUpgrades;
    private TextView upgradeCost;
    private TextView bonusPerUpgrade;
    private TextView maxUpgrades;
    private TextView gearBonus;
    private SpellUpgradesView upgradesView;

    private static final Color white = Color.WHITE;
    private static final Color orange = Color.ORANGE;
    private static final Color purpleL = new Color(163/255f, 170/255f, 255/255f, 1);
    private static final Color purpleH = new Color(110/255f, 110/255f, 211/255f, 1);
    private static final Color black = Color.BLACK;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormatSymbols simbolos = df.getDecimalFormatSymbols();

    // GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat getModel()           { return model; }
    public TextView getName()                   { return name; }
    public TextView getBaseValue()              { return baseValue; }
    public TextView getTotal()                  { return total; }
    public TextView getNumUpgrades()            { return numUpgrades; }
    public TextView getUpgradeCost()            { return upgradeCost; }
    public TextView getBonusPerUpgrade()        { return bonusPerUpgrade; }
    public TextView getMaxUpgrades()            { return maxUpgrades; }
    public TextView getGearBonus()              { return gearBonus; }
    public SpellUpgradesView getUpgradesView()  { return upgradesView; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellStatView(CustomSpellStat customSpellStat)
    {
        simbolos.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(simbolos);

        this.model = customSpellStat;
        this.model.addObserver(this);

        createView();
        updateView();
    }

    @Override public void dispose()
    {   model.removeObserver(this); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    private void createView()
    {
        BitmapFont font14 = Atlas.get().getFont("14");
        BitmapFont font10 = Atlas.get().getFont("10");

        name            = new TextView(font10, white,   black,1);
        baseValue       = new TextView(font14, orange,  black,1);
        total           = new TextView(font14, purpleH, black,1);
        numUpgrades     = new TextView(font10, purpleL, black,1);
        upgradeCost     = new TextView(font10, purpleL, black,1);
        bonusPerUpgrade = new TextView(font10, purpleL, black,1);
        maxUpgrades     = new TextView(font10, purpleL, black,1);
        gearBonus       = new TextView(font10, purpleL, black,1);

        upgradesView    = new SpellUpgradesView(model);
    }

    private void updateView()
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

    // OBSERVER:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   updateView(); }
}