package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.spellbook.header.FormHeaderView;
import com.myrran.view.ui.spellbook.icon.iconview.SlotIconsView;
import com.myrran.view.ui.spellbook.stats.TemplateStatsView;
import com.myrran.view.ui.widgets.DetailedActorI;
import com.myrran.view.ui.widgets.DetailedTable;

/** @author Ivan Delgado Huerta */
public class TemplateFormView extends DetailedTable implements DetailedActorI, Disposable
{
    private FormHeaderView header;
    private TemplateStatsView subformStats;
    private SlotIconsView debuffIcons;
    private SlotIconsView subformIcons;

    private static final int MINWIDTH = 245;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateFormView(CustomSpellController customSpellController)
    {
        header          = new FormHeaderView();
        subformStats    = new TemplateStatsView();
        debuffIcons     = new SlotIconsView();
        subformIcons    = new SlotIconsView();

        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        tableHeader.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.85f));
        tableDetails.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", Color.WHITE,0.90f));

        createLayout();
    }

    @Override public void dispose()
    {
        header.dispose();
        debuffIcons.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellForm model)
    {
        if (model == null)
        {
            header.setModel(null);
            subformStats.setModel(null);
            debuffIcons.setModel(null);
            subformIcons.setModel(null);
        }
        else
        {
            header.setModel(model);
            subformStats.setModel(model.getSpellStats());
            debuffIcons.setModel(model.getSpellSlots());
            subformIcons.setModel(model.getSpellSubforms());
            createLayoutImp();
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayoutImp()
    {
        tableHeader.clear();
        tableHeader.add(header).minWidth(MINWIDTH);

        tableDetails.clear();
        tableDetails.add(subformStats).minWidth(MINWIDTH).row();
        tableDetails.add(debuffIcons).minWidth(MINWIDTH).row();
        tableDetails.add(subformIcons).minWidth(MINWIDTH).row();
    }
}
