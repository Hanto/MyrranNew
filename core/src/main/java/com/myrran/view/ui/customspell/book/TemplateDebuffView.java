package com.myrran.view.ui.customspell.book;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffSource;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.customspell.header.DebuffHeaderView;
import com.myrran.view.ui.customspell.stats.TemplateStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffView extends DetailsTable implements Disposable
{
    private TemplateSpellDebuff model;
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

    public void setModel(TemplateSpellDebuff templateSpellDebuff)
    {
        if (templateSpellDebuff == null)
        {
            dadSource.setModel(null);
            header.setModel(null);
            statsView.setModel(null);
        }
        else
        {
            model = templateSpellDebuff;
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
        tableDetails.add(statsView).top().left().row();
    }
}