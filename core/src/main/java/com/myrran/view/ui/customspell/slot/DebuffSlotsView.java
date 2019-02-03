package com.myrran.view.ui.customspell.slot;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class DebuffSlotsView extends Table implements Disposable
{
    private CustomSpellController controller;

    private Collection<CustomDebuffSlot> model;
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
            .map(this::addDebuffIcons)
            .collect(Collectors.toList());

        views.forEach(view -> add(view).bottom().left());
    }

    private DebuffSlotView addDebuffIcons(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        DebuffSlotView icon = new DebuffSlotView(controller);
        icon.setModel(slot);
        return icon;
    }
}
