package com.myrran.view.ui.customspell.form;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomFormI;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.customspell.CDebuffStatsView;
import com.myrran.view.ui.customspell.slot.DebuffSlotView;
import com.myrran.view.ui.customspell.stats.CStatsView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class FormView implements Disposable
{
    private CustomFormI model;
    private CustomSpellController controller;

    private CStatsView formStats;
    private List<DebuffSlotView> slotList;
    private List<CDebuffStatsView> statList;

    public CStatsView getFormStats()                { return formStats; }
    public List<DebuffSlotView>getDebuffIcons()     { return slotList; }
    public List<CDebuffStatsView>getDebuffStats()   { return statList; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public FormView(CustomSpellController spellController)
    {
        controller  = spellController;
        formStats   = new CStatsView(controller);
    }

    private void disposeObservers()
    {
        if (slotList != null)
            slotList.forEach(DebuffSlotView::dispose);

        if (statList != null)
            statList.forEach(CDebuffStatsView::dispose);
    }

    @Override public void dispose()
    {
        disposeObservers();

        if (formStats != null)
            formStats.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomFormI customForm)
    {
        disposeObservers();

        if (customForm == null)
            removeModel();
        else
        {
            model = customForm;
            update();
        }
    }

    private void removeModel()
    {   formStats.setModel(null); }

    private void update()
    {
        formStats.setModel(model);

        slotList = model.getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(SpellSlotI::getID))
            .map(this::addDebuffIcons)
            .collect(Collectors.toList());

        statList = model.getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(SpellSlotI::getID))
            .map(this::addDebuffStats)
            .collect(Collectors.toList());
    }

    private DebuffSlotView addDebuffIcons(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        DebuffSlotView icon = new DebuffSlotView(controller);
        icon.setModel(slot);
        return icon;
    }

    private CDebuffStatsView addDebuffStats(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> slot)
    {
        CDebuffStatsView details = new CDebuffStatsView(controller);
        details.setModel(slot);
        return details;
    }
}
