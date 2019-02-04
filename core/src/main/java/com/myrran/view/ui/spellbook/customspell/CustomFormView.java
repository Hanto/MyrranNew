package com.myrran.view.ui.spellbook.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.spellbook.icon.FormIconView;
import com.myrran.view.ui.spellbook.stats.CustomStatsView;
import com.myrran.view.ui.spellbook.stats.DebuffSlotsStatsView;
import com.myrran.view.ui.widgets.DetailedTable;
import com.myrran.view.ui.spellbook.slot.DebuffSlotsView;
import com.myrran.view.ui.listeners.TouchDownListener;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends DetailedTable implements Disposable
{
    private FormIconView icon;
    private CustomStatsView formStats;
    private DebuffSlotsStatsView debuffsSlotsStatsView;
    private DebuffSlotsView debuffsSlotsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController controller)
    {
        icon                    = new FormIconView(controller);
        formStats               = new CustomStatsView(controller);
        debuffsSlotsStatsView   = new DebuffSlotsStatsView(controller);
        debuffsSlotsView        = new DebuffSlotsView(controller);

        icon.addListener(new TouchDownListener(event ->
        {   if (event.getButton() == Input.Buttons.LEFT) showDetails(); }));

        createLayout();
        createLayoutImp();
    }

    @Override public void dispose()
    {
        icon.dispose();
        formStats.dispose();
        debuffsSlotsStatsView.dispose();
        debuffsSlotsView.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm model)
    {
        if (model == null)
        {
            icon.setModel(null);
            formStats.setModel(null);
            debuffsSlotsStatsView.setModel(null);
            debuffsSlotsView.setModel(null);
            clear();
        }
        else
        {
            icon.setModel(model);
            formStats.setModel(model);
            debuffsSlotsStatsView.setModel(model.getDebuffSlots());
            debuffsSlotsView.setModel(model.getDebuffSlots());
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
        tableDetails.padBottom(4).padLeft(4).padTop(2);
        tableDetails.add(formStats).left().row();
        tableDetails.add(debuffsSlotsStatsView).left().row();
    }
}
