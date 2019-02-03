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
    private FormIconView icon;
    private CustomStatsView formStats;
    private DebuffSlotsStatsView debuffsSlotsStatsView;
    private DebuffSlotsView debuffsSlotsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController controller)
    {
        icon                    = new FormIconView();
        formStats               = new CustomStatsView(controller);
        debuffsSlotsStatsView   = new DebuffSlotsStatsView(controller);
        debuffsSlotsView        = new DebuffSlotsView(controller);

        icon.addListener(new TouchDownListener(event ->
        {   if (event.getButton() == Input.Buttons.LEFT) showDetails(); }));

        createLayout();
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
            debuffsSlotsStatsView.setModel(model.getCustomDebuffSlots());
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
        tableDetails.add(debuffsSlotsStatsView).left().row();
    }
}
