package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsStatsView extends Table implements Disposable
{
    private CustomSpellController controller;

    private Collection<CustomDebuffSlot> model;
    private List<DebuffSlotStatsView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotsStatsView(CustomSpellController spellController)
    {   controller = spellController; }

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(DebuffSlotStatsView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(Collection<CustomDebuffSlot> models)
    {
        dispose();

        if (models == null)
            clear();
        else
        {
            model = models;
            update();
        }
    }

    private void update()
    {
        clear();
        top().left();
        views = model.stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffStats)
            .collect(Collectors.toList());

        views.forEach(view -> add(view).bottom().left().row());
    }

    private DebuffSlotStatsView addDebuffStats(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        DebuffSlotStatsView details = new DebuffSlotStatsView(controller);
        details.setModel(slot);
        return details;
    }
}
