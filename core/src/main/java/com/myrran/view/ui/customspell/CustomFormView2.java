package com.myrran.view.ui.customspell;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomFormI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class CustomFormView2 implements Disposable
{
    private CustomFormI model;
    private CustomSpellController controller;

    private CustomStatsView formStats;
    private List<CustomDebuffIconView> slotList;
    private List<CustomDebuffStatsView> statList;

    public CustomStatsView getFormStats()                   { return formStats; }
    public List<CustomDebuffIconView>getDebuffIcons()       { return slotList; }
    public List<CustomDebuffStatsView>getDebuffStats()      { return statList; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView2(CustomSpellController spellController)
    {
        controller  = spellController;
        formStats   = new CustomStatsView(controller);
    }

    private void disposeObservers()
    {
        if (slotList != null)
            slotList.forEach(CustomDebuffIconView::dispose);

        if (statList != null)
            statList.forEach(CustomDebuffStatsView::dispose);
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
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffIcons)
            .collect(Collectors.toList());

        statList = model.getCustomDebuffSlots().stream()
            .sorted(Comparator.comparing(CustomDebuffSlot::getID))
            .map(this::addDebuffStats)
            .collect(Collectors.toList());
    }

    private CustomDebuffIconView addDebuffIcons(CustomDebuffSlot slot)
    {
        CustomDebuffIconView icon = new CustomDebuffIconView(controller);
        icon.setModel(slot);
        return icon;
    }

    private CustomDebuffStatsView addDebuffStats(CustomDebuffSlot slot)
    {
        CustomDebuffStatsView details = new CustomDebuffStatsView(controller);
        details.setModel(slot);
        return details;
    }
}
