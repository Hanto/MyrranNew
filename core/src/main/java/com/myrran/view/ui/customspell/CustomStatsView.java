package com.myrran.view.ui.customspell;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellStat;
import com.myrran.model.spell.generators.CustomSpellStatsI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomStatsView extends Table implements Disposable
{
    private CustomSpellStatsI model;
    private CustomSpellController controller;

    private List<CustomStatView> statsViewList = new ArrayList<>();

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomStatsView(CustomSpellController spellController)
    {
        controller = spellController;
        createLayout();
    }

    @Override public void dispose()
    {   statsViewList.forEach(CustomStatView::dispose); }

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
        statsViewList = model.getCustomSpellStats().stream()
            .sorted(Comparator.comparing(CustomSpellStat::getName))
            .map(this::getView)
            .collect(Collectors.toList());

        statsViewList.forEach(this::tableAddRow);
        createListeners();
    }

    private void createLayout()
    {
        top().left();
    }

    // LISTENERS:
    //--------------------------------------------------------------------------------------------------------

    public void createListeners()
    {
        for (CustomStatView view: statsViewList)
        {
            String statID = view.getModel().getID();
            view.getUpgradesView().addListener(new CustomUBarListener(controller, model, statID));
        }
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    private CustomStatView getView(CustomSpellStat customSpellStat)
    {   return new CustomStatView(customSpellStat); }

    private void tableAddRow(SpellStatRow row)
    {
        int vPad = -4;
        int hPad = +3;

        add(row.getName()).left()             .minWidth(80).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getBaseValue()).right()       .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getUpgradesView()).center()   .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getTotal()).right()           .minWidth(30).padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getNumUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getUpgradeCost()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getBonusPerUpgrade()).right() .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getMaxUpgrades()).right()     .padRight(hPad).padTop(vPad).padBottom(vPad);
        add(row.getGearBonus()).right()       .padRight(hPad).padTop(vPad).padBottom(vPad);
        row();
    }
}
