package com.myrran.view.ui.spellbook.icon.contentview;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.*;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsContentView extends Table implements Disposable
{
    private CustomSpellController controller;
    private List<DebuffSlotContentView> views;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotsContentView(CustomSpellController spellController)
    {   controller = spellController; }

    @Override public void dispose()
    {
        if (views != null)
            views.forEach(DebuffSlotContentView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlotsI model)
    {
        dispose();
        clear();

        if (model != null)
        {
            clear();
            views = model.getCustomDebuffSlots().stream()
                .sorted(Comparator.comparing(CustomDebuffSlot::getID))
                .map(this::addDebuffSlotView)
                .collect(Collectors.toList());

            views.forEach(view -> add(view).bottom().left());
        }
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private DebuffSlotContentView addDebuffSlotView(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        DebuffSlotContentView icon = new DebuffSlotContentView(controller);
        icon.setModel(slot);
        return icon;
    }
}
