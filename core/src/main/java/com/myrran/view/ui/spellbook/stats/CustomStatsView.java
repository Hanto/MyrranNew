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
    private StatHeader statHeader;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomStatsView(CustomSpellController spellController)
    {
        controller = spellController;
        statHeader = new StatHeader();
    }

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(CustomStatView::dispose);

        statHeader.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellStatsI model)
    {
        dispose();
        clear();
        padTop(0).padBottom(0);

        if (model != null)
        {
            padTop(2).padBottom(2).padLeft(4);

            views = model.getCustomSpellStats().stream()
                .sorted(Comparator.comparing(CustomSpellStat::getName))
                .map(CustomStatView::new)
                .collect(Collectors.toList());

            statHeader.setModel(model);
            add(statHeader).left().row();
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
