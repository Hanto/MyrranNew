package com.myrran.view.ui.customspell.book.customform;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.customspell.book.DetailsTable;
import com.myrran.view.ui.customspell.slot.DebuffSlotsView;
import com.myrran.view.ui.customspell.slot.SubformSlotView;
import com.myrran.view.ui.customspell.stats.CustomStatsView;
import com.myrran.view.ui.customspell.stats.DebuffSlotsStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubformView extends DetailsTable implements Disposable, PropertyChangeListener
{
    private CustomSubformSlot model;

    private SubformSlotView icon;
    private CustomStatsView formStats;
    private DebuffSlotsStatsView debuffsSlotsStatsView;
    private DebuffSlotsView debuffsSlotsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformView(CustomSpellController controller)
    {
        icon                    = new SubformSlotView(controller);
        formStats               = new CustomStatsView(controller);
        debuffsSlotsStatsView   = new DebuffSlotsStatsView(controller);
        debuffsSlotsView        = new DebuffSlotsView(controller);

        icon.addListener(new TouchDownListener(event ->
        {   if (event.getButton() == Input.Buttons.LEFT) showDetails(); }));

        createLayout();
        createLayoutImp();
    }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    @Override public void dispose()
    {
        disposeObservers();
        icon.dispose();
        formStats.dispose();
        debuffsSlotsStatsView.dispose();
        debuffsSlotsView.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlot customSubformSlot)
    {
        disposeObservers();

        if (customSubformSlot == null)
        {
            icon.setModel(null);
            formStats.setModel(null);
            debuffsSlotsStatsView.setModel(null);
            debuffsSlotsView.setModel(null);
            clear();
        }
        else
        {
            model = customSubformSlot;
            model.addObserver(this);
            update();
        }
    }

    private void update()
    {
        icon.setModel(model);

        if (model.hasData())
        {
            formStats.setModel(model.getContent());
            debuffsSlotsStatsView.setModel(model.getContent().getCustomDebuffSlots());
            debuffsSlotsView.setModel(model.getContent().getDebuffSlots());
        }
        else
        {
            formStats.setModel(null);
            debuffsSlotsStatsView.setModel(null);
            debuffsSlotsView.setModel(null);
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayoutImp()
    {
        tableHeader.clear();
        tableHeader.add(icon).left().bottom();
        tableHeader.add(debuffsSlotsView).left().row();

        tableDetails.clear();
        tableDetails.padBottom(4).padLeft(4).padBottom(2);
        tableDetails.add(formStats).left().row();
        tableDetails.add(debuffsSlotsStatsView).left().row();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
