package com.myrran.view.ui.spellbook.stats;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellStat;
import com.myrran.model.spell.generators.CustomSpellStatsI;
import com.myrran.view.ui.spellbook.stats.bar.UpgradeBarListener;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomStatsView extends Table implements Disposable
{
    private CustomSpellController controller;
    private List<CustomStatView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomStatsView(CustomSpellController spellController)
    {   controller = spellController; }

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(CustomStatView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellStatsI model)
    {
        dispose();
        clear();

        if (model != null)
        {
            clear();
            views = model.getCustomSpellStats().stream()
                .sorted(Comparator.comparing(CustomSpellStat::getName))
                .map(CustomStatView::new)
                .collect(Collectors.toList());

            views.forEach(row -> add(row).left().bottom().row());

            createListeners(model);
        }
    }

    // LISTENERS:
    //--------------------------------------------------------------------------------------------------------

    private void createListeners(CustomSpellStatsI model)
    {
        for (CustomStatView view: views)
        {
            String statID = view.getModel().getID();
            view.getUpgradesView().addListener(new UpgradeBarListener(controller, model, statID));
        }
    }
}
