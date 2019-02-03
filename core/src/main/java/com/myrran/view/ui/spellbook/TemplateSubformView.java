package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
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
public class TemplateSubformView extends Table implements DetailedActorI, Disposable
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;

    private DadSubformSource dadSource;
    private SubformHeaderView header;
    private Table tableDetails;

    private List<DebuffIconView> debuffs;
    private TemplateStatsView subformStats;

    private boolean detailsVisible = false;
    private Cell<Actor> cellDetails;

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
        cellDetails = getCell(tableDetails);
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
            removeModel();
        else
        {
            model = templateSpellSubform;
            dadSource.setModel(model);
            header.setModel(model);
            subformStats.setModel(model.getSpellStats());
            update();
        }
    }

    private void removeModel()
    {   header.removeModel(); }

    private void update()
    {
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

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
        add(tableDetails).bottom().left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        cellDetails.setActor(visible ? tableDetails : null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }
}
