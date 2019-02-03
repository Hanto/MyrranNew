package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffSource;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.customspell.header.DebuffHeaderView;
import com.myrran.view.ui.customspell.stats.TemplateStatsView;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffView extends Table implements DetailedActorI, Disposable
{
    private TemplateSpellDebuff model;
    private CustomSpellController controller;

    private DadDebuffSource dadSource;
    private DebuffHeaderView header;
    private TemplateStatsView statsView;

    private Table details;

    private boolean detailsVisible = true;
    private Cell<Actor> detailsCell;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        header          = new DebuffHeaderView();
        dadSource       = new DadDebuffSource(header.getIcon(), controller);
        statsView       = new TemplateStatsView();
        details         = new Table();

        controller.getDadDebuff().addSource(dadSource);
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
        detailsCell = getCell(details);
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
            removeModel();
        else
        {
            model = templateSpellDebuff;
            dadSource.setModel(model);
            header.setModel(model);
            statsView.setModel(model.getSpellStats());
        }
    }

    private void removeModel()
    {
        dadSource.setModel(null);
        header.removeModel();
        statsView.setModel(null);
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
        add(details).top().left().row();

        details.clear();
        details.top().left();
        details.padBottom(8).padLeft(4);

        details.add().size(32, 0);
        details.add(statsView).top().left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        detailsCell.setActor(visible ? details : null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }
}
