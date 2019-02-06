package com.myrran.view.ui.spellbook.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.spellbook.icon.contentview.DebuffSlotsContentView;
import com.myrran.view.ui.spellbook.icon.contentview.SubformSlotContentView;
import com.myrran.view.ui.spellbook.stats.CustomStatsView;
import com.myrran.view.ui.spellbook.stats.DebuffSlotsStatsView;
import com.myrran.view.ui.widgets.DetailedTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubformView extends DetailedTable implements Disposable, PropertyChangeListener
{
    private CustomSubformSlot model;

    private SubformSlotContentView icon;
    private CustomStatsView formStats;
    private DebuffSlotsStatsView debuffsSlotsStatsView;
    private DebuffSlotsContentView debuffsSlotsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformView(CustomSpellController controller)
    {
        icon                    = new SubformSlotContentView(controller);
        formStats               = new CustomStatsView(controller);
        debuffsSlotsStatsView   = new DebuffSlotsStatsView(controller);
        debuffsSlotsView        = new DebuffSlotsContentView(controller);

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
            debuffsSlotsStatsView.setModel(model.getContent().getDebuffSlots());
            debuffsSlotsView.setModel(model.getContent().getDebuffSlots());
            tableDetails.padBottom(4);
        }
        else
        {
            formStats.setModel(null);
            debuffsSlotsStatsView.setModel(null);
            debuffsSlotsView.setModel(null);
            tableDetails.padBottom(0);
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
        tableDetails.add(formStats).left().row();
        tableDetails.add(debuffsSlotsStatsView).left().row();
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
