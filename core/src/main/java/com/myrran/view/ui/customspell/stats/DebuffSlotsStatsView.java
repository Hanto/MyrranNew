package com.myrran.view.ui.customspell.stats;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomDebuffSlotsI;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsStatsView extends Table implements Disposable
{
    private CustomSpellController controller;

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

    public void setModel(CustomDebuffSlotsI models)
    {
        dispose();

        if (models == null)
            clear();
        else
        {
            clear();
            views = models.getCustomDebuffSlots().stream()
                .sorted(Comparator.comparing(CustomDebuffSlot::getID))
                .map(this::addDebuffSlotStatsView)
                .collect(Collectors.toList());

            views.forEach(view -> add(view).bottom().left().row());
        }
    }

    private DebuffSlotStatsView addDebuffSlotStatsView(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        DebuffSlotStatsView details = new DebuffSlotStatsView(controller);
        details.setModel(slot);
        return details;
    }
}
