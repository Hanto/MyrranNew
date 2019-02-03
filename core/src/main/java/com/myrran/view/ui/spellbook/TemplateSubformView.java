package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadSubformSource;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.customspell.header.SubformHeaderView;
import com.myrran.view.ui.customspell.icon.AbstractSpellIconView;
import com.myrran.view.ui.customspell.icon.DebuffIconView;
import com.myrran.view.ui.customspell.stats.TemplateStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.util.List;
import java.util.stream.Collectors;

/** @author Ivan Delgado Huerta */
public class TemplateSubformView extends DetailsTable implements DetailedActorI, Disposable
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;

    private DadSubformSource dadSource;
    private SubformHeaderView header;
    private TemplateStatsView subformStats;
    private List<DebuffIconView> debuffs;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        header          = new SubformHeaderView();
        dadSource       = new DadSubformSource(header.getIcon(), controller);
        tableDetails    = new Table();
        subformStats    = new TemplateStatsView();

        controller.getDadSubform().addSource(dadSource);
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
    }

    @Override public void dispose()
    {
        controller.getDadDebuff().removeSource(dadSource);
        header.dispose();

        if (debuffs != null)
            debuffs.forEach(AbstractSpellIconView::dispose);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellSubform templateSpellSubform)
    {
        if (templateSpellSubform == null)
        {
            dadSource.setModel(null);
            header.setModel(null);
            subformStats.setModel(null);
        }
        else
        {
            model = templateSpellSubform;
            dadSource.setModel(model);
            header.setModel(model);
            subformStats.setModel(model.getSpellStats());
            update();
        }
    }

    private void update()
    {
        tableHeader.clear();
        tableHeader.add(header);

        Table slots = new Table().top().left();
        debuffs = model.getSpellSlots().stream()
            .map(DebuffIconView::new)
            .collect(Collectors.toList());

        debuffs.forEach(o -> slots.add(o).top().left());

        tableDetails.clear();
        tableDetails.add().size(32);
        tableDetails.add(slots).row();
        tableDetails.add().size(32);
        tableDetails.add(subformStats);
    }
}