package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffSource;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.spellbook.header.DebuffHeaderView;
import com.myrran.view.ui.spellbook.stats.TemplateStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedTable;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffView extends DetailedTable implements Disposable
{
    private CustomSpellController controller;

    private DadDebuffSource dadSource;
    private DebuffHeaderView header;
    private TemplateStatsView statsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        header          = new DebuffHeaderView();
        dadSource       = new DadDebuffSource(header.getIcon(), controller);
        statsView       = new TemplateStatsView();

        controller.getDadDebuff().addSource(dadSource);
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
    }

    @Override public void dispose()
    {
        controller.getDadDebuff().removeSource(dadSource);
        header.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellDebuff model)
    {
        if (model == null)
        {
            dadSource.setModel(null);
            header.setModel(null);
            statsView.setModel(null);
        }
        else
        {
            dadSource.setModel(model);
            header.setModel(model);
            statsView.setModel(model.getSpellStats());
            update();
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void update()
    {
        tableHeader.clear();
        tableHeader.add(header);

        tableDetails.clear();
        tableDetails.add().size(32, 0);
        tableDetails.add(statsView).top().left().padBottom(4).row();
    }
}