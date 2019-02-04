package com.myrran.view.ui.customspell.slot;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.*;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsView extends Table implements Disposable
{
    private CustomSpellController controller;
    private List<DebuffSlotView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotsView(CustomSpellController spellController)
    {   controller = spellController; }

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(DebuffSlotView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlotsI model)
    {
        dispose();

        if (model == null)
            clear();
        else
        {
            clear();
            views = model.getCustomDebuffSlots().stream()
                .sorted(Comparator.comparing(CustomDebuffSlot::getID))
                .map(this::addDebuffIcons)
                .collect(Collectors.toList());

            views.forEach(view -> add(view).bottom().left());
        }
    }

    private DebuffSlotView addDebuffIcons(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        DebuffSlotView icon = new DebuffSlotView(controller);
        icon.setModel(slot);
        return icon;
    }
}
