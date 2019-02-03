package com.myrran.view.ui.customspell.book.customform;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.customspell.book.DetailsTable;
import com.myrran.view.ui.customspell.icon.FormIconView;
import com.myrran.view.ui.customspell.slot.DebuffSlotsView;
import com.myrran.view.ui.customspell.stats.CustomStatsView;
import com.myrran.view.ui.customspell.stats.DebuffSlotsStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends DetailsTable implements Disposable
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private FormIconView icon;
    private CustomStatsView formStats;
    private DebuffSlotsStatsView debuffsStatsView;
    private DebuffSlotsView debuffsSlotsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController spellController)
    {
        controller      = spellController;
        icon            = new FormIconView();
        formStats       = new CustomStatsView(controller);
        debuffsStatsView= new DebuffSlotsStatsView(controller);
        debuffsSlotsView= new DebuffSlotsView(controller);

        icon.addListener(new TouchDownListener(event ->
        {   if (event.getButton() == Input.Buttons.LEFT) showDetails(); }));

        createLayout();
    }

    @Override public void dispose()
    {
        icon.dispose();
        formStats.dispose();
        debuffsStatsView.dispose();
        debuffsSlotsView.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm customForm)
    {
        if (customForm == null)
        {
            icon.setModel(null);
            formStats.setModel(null);
            debuffsStatsView.setModel(null);
            debuffsSlotsView.setModel(null);
            clear();
        }
        else
        {
            model = customForm;
            icon.setModel(model);
            formStats.setModel(model);
            debuffsStatsView.setModel(model.getCustomDebuffSlots());
            debuffsSlotsView.setModel(model.getCustomDebuffSlots());
            update();
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void update()
    {
        tableHeader.clear();
        tableHeader.add(icon).left().bottom();
        tableHeader.add(debuffsSlotsView).left().row();

        tableDetails.clear();
        tableDetails.padBottom(4).padLeft(4).padTop(2);
        tableDetails.add(formStats).left().row();
        tableDetails.add(debuffsStatsView).left().row();
    }
}
