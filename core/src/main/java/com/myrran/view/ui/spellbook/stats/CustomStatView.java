package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.model.spell.generators.CustomSpellStat;
import com.myrran.view.Atlas;
import com.myrran.view.ui.spellbook.stats.bar.UpgradeBarView;
import com.myrran.view.ui.widgets.WidgetText;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

/** @author Ivan Delgado Huerta */
public class CustomStatView extends Table implements PropertyChangeListener, Disposable
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
    private UpgradeBarView upgradesView;

    private static final DecimalFormat df = Atlas.get().getFormater();

    // GETTERS:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat getModel()       { return model; }
    public UpgradeBarView getUpgradesView() { return upgradesView; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomStatView(CustomSpellStat customSpellStat)
    {
        BitmapFont font14 = Atlas.get().getFont("14");
        BitmapFont font12 = Atlas.get().getFont("Calibri12");
        BitmapFont font10 = Atlas.get().getFont("Arial10");
        Color white = Color.WHITE;
        Color orange = Color.ORANGE;
        Color purpleL = new Color(163/255f, 170/255f, 255/255f, 1);
        Color purpleH = new Color(110/255f, 110/255f, 211/255f, 1);
        Color black = Color.BLACK;

        name            = new WidgetText(font12, white,   black,1);
        baseValue       = new WidgetText(font14, orange,  black,1);
        total           = new WidgetText(font14, purpleH, black,1);
        numUpgrades     = new WidgetText(font10, purpleL, black,1);
        upgradeCost     = new WidgetText(font10, purpleL, black,1);
        bonusPerUpgrade = new WidgetText(font10, purpleL, black,1);
        maxUpgrades     = new WidgetText(font10, purpleL, black,1);
        gearBonus       = new WidgetText(font10, purpleL, black,1);
        upgradesView    = new UpgradeBarView();

        createLayout();
        setModel(customSpellStat);
    }

    public void dispose()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // CREATE / UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellStat customSpellStat)
    {
        dispose();

        if (customSpellStat == null)
            removeModel();
        else
        {
            model = customSpellStat;
            model.addObserver(this);
            upgradesView.setModel(model);
            update();
        }
    }

    public void removeModel()
    {   clear(); }

    public void update()
    {
        name.setText(model.getName());
        baseValue.setText(df.format(model.getBaseValue()));
        total.setText(df.format(model.getTotal()));
        numUpgrades.setText(format(model.getNumUpgrades()) + "-" + format(model.getMaxUpgrades()));
        upgradeCost.setText(format(model.getUpgradeCost()));
        bonusPerUpgrade.setText(format(model.getBonusPerUpgrade()));
        maxUpgrades.setText(format(model.getMaxUpgrades()));
        gearBonus.setText(df.format(model.getGearBonus()));
        upgradesView.update();
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        int vPad = -4;
        int hPad = +3;

        add(name).bottom().left()            .minWidth(90).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(baseValue).bottom().right()      .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradesView).center()           .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(total).bottom().right()          .minWidth(35).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(numUpgrades).bottom().right()    .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(upgradeCost).bottom().right()    .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(bonusPerUpgrade).bottom().right().minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(gearBonus).bottom().right()      .minWidth(20).padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }

    private String format(Float rawData)
    {   return model.getIsUpgradeable() ? df.format(rawData) : "-"; }

    private String format(Integer rawData)
    {   return model.getIsUpgradeable() ? rawData.toString() : "-"; }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}