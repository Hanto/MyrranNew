package com.myrran.view.ui.customspell;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.SpellUpgradesListener;
import com.myrran.model.spell.generators.custom.CustomSpellStat;
import com.myrran.model.spell.generators.custom.CustomSpellStatsI;

import java.util.ArrayList;
import java.util.List;

/** @author Ivan Delgado Huerta */
public class SpellStats extends Table implements Disposable
{
    private CustomSpellStatsI model;
    private CustomSpellController controller;

    private List<SpellStat> statsViewList;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellStats(CustomSpellController spellController)
    {
        controller = spellController;
        statsViewList = new ArrayList<>();

        createLayout();
    }

    @Override public void dispose()
    {   statsViewList.forEach(SpellStat::dispose); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellStatsI customSpellStats)
    {
        dispose();

        if (customSpellStats == null)
            removeModel();
        else
        {
            model = customSpellStats;
            update();
        }
    }

    private void removeModel()
    {
        clear();
        model = null;
        statsViewList.clear();
    }

    private void update()
    {
        clear();
        statsViewList.clear();

        model.values().stream()
            .map(this::getView)
            .forEach(statView -> statsViewList.add(statView));

        createListeners();

        statsViewList.forEach(this::tableAddRow);
    }

    // LISTENERS:
    //--------------------------------------------------------------------------------------------------------

    public void createListeners()
    {
        for (SpellStat view: statsViewList)
        {
            String statID = view.getModel().getID();
            view.getUpgradesView().addListener(new SpellUpgradesListener(controller, model, statID));
        }
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        top().left();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    private SpellStat getView(CustomSpellStat customSpellStat)
    {   return new SpellStat(customSpellStat); }

    private void tableAddRow(SpellStatRow row)
    {
        int vPad = -4;
        int hPad = +3;

        add(row.getName()).left()             .minWidth(100).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getBaseValue()).right()       .minWidth(45).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getUpgradesView()).center()   .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getTotal()).right()           .minWidth(45).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getNumUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getUpgradeCost()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getBonusPerUpgrade()).right() .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getMaxUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getGearBonus()).right()       .padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }
}
