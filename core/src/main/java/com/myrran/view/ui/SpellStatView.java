package com.myrran.view.ui;

import com.badlogic.gdx.Gdx;
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
    private TextView numUpgrades;
    private TextView maxUpgrades;
    private TextView upgradeCost;
    private TextView bonusPerUpgrade;
    private TextView gearBonus;
    private TextView total;

    private Color white = Color.WHITE;
    private Color orange = Color.ORANGE;
    private Color yellow = Color.YELLOW;
    private Color green = Color.GREEN;
    private Color black = white.BLACK;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormatSymbols simbolos = df.getDecimalFormatSymbols();

    // GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public TextView getName()           { return name; }
    public TextView getBaseValue()      { return baseValue; }
    public TextView getNumUpgrades()    { return numUpgrades; }
    public TextView getMaxUpgrades()    { return maxUpgrades; }
    public TextView getTotal()          { return total; }

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
        BitmapFont font14 = new BitmapFont(Gdx.files.internal("fonts/14.fnt"), false);
        BitmapFont font11 = new BitmapFont(Gdx.files.internal("fonts/11.fnt"), false);

        name        = new TextView(font11, white, black, 1);
        baseValue   = new TextView(font14, orange, black, 1);
        total       = new TextView(font14, green, black, 1);
        numUpgrades = new TextView(font11, yellow, black, 1);
    }

    private void updateView()
    {
        name.setText(model.getName());
        baseValue.setText(df.format(model.getBaseValue()));
        total.setText(df.format(model.getTotal()));
        numUpgrades.setText(model.getIsUpgradeable() ? model.getNumUpgrades().toString() : "-");
    }

    // OBSERVER:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   updateView(); }
}
