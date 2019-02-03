package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellStat;
import com.myrran.model.spell.generators.CustomSpellStatsI;
import com.myrran.view.ui.customspell.stats.bar.UpgradeBarListener;

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
            .map(CustomStatView::new)
            .collect(Collectors.toList());

        statsViewList.forEach(row -> add(row).left().bottom().row());
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
            view.getUpgradesView().addListener(new UpgradeBarListener(controller, model, statID));
        }
    }
}
