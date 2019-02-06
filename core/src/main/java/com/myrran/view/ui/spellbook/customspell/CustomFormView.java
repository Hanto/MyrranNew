package com.myrran.view.ui.spellbook.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.spellbook.icon.contentview.DebuffSlotsContentView;
import com.myrran.view.ui.spellbook.icon.iconview.FormIconView;
import com.myrran.view.ui.spellbook.stats.CustomStatsView;
import com.myrran.view.ui.spellbook.stats.DebuffSlotsStatsView;
import com.myrran.view.ui.widgets.DetailedTable;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends DetailedTable implements Disposable
{
    private FormIconView icon;
    private CustomStatsView formStats;
    private DebuffSlotsStatsView debuffsSlotsStatsView;
    private DebuffSlotsContentView debuffsSlotsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController controller)
    {
        icon                    = new FormIconView(controller);
        formStats               = new CustomStatsView(controller);
        debuffsSlotsStatsView   = new DebuffSlotsStatsView(controller);
        debuffsSlotsView        = new DebuffSlotsContentView(controller);

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
            clearChildren();
        }
        else
        {
            icon.setModel(model);
            formStats.setModel(model);
            debuffsSlotsStatsView.setModel(model.getDebuffSlots());
            debuffsSlotsView.setModel(model.getDebuffSlots());
            tableDetails.padBottom(4);
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayoutImp()
    {
        tableHeader.clearChildren();
        tableHeader.add(icon).left().bottom();
        tableHeader.add(debuffsSlotsView).left().row();

        tableDetails.clearChildren();
        tableDetails.add(formStats).left().row();
        tableDetails.add(debuffsSlotsStatsView).left().row();
    }
}
