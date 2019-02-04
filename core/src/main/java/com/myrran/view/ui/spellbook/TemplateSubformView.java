package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadSubformSource;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.spellbook.header.SubformHeaderView;
import com.myrran.view.ui.spellbook.icon.iconview.SlotIconsView;
import com.myrran.view.ui.spellbook.stats.TemplateStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;
import com.myrran.view.ui.widgets.DetailedTable;

/** @author Ivan Delgado Huerta */
public class TemplateSubformView extends DetailedTable implements DetailedActorI, Disposable
{
    private CustomSpellController controller;

    private DadSubformSource dadSource;
    private SubformHeaderView header;
    private TemplateStatsView subformStats;
    private SlotIconsView debuffIcons;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        header          = new SubformHeaderView();
        dadSource       = new DadSubformSource(header.getIcon(), controller);
        subformStats    = new TemplateStatsView();
        debuffIcons     = new SlotIconsView();

        controller.getDadSubform().addSource(dadSource);
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
    }

    @Override public void dispose()
    {
        controller.getDadDebuff().removeSource(dadSource);
        header.dispose();
        debuffIcons.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellSubform model)
    {
        if (model == null)
        {
            dadSource.setModel(null);
            header.setModel(null);
            subformStats.setModel(null);
            debuffIcons.setModel(null);
        }
        else
        {
            dadSource.setModel(model);
            header.setModel(model);
            subformStats.setModel(model.getSpellStats());
            debuffIcons.setModel(model.getSpellSlots());
            createLayoutImp();
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayoutImp()
    {
        tableHeader.clear();
        tableHeader.add(header);

        tableDetails.clear();
        tableDetails.add().size(32, 0);
        tableDetails.add(subformStats).padBottom(4).row();
        tableDetails.add().size(32, 0);
        tableDetails.add(debuffIcons).row();
    }
}