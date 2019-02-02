package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.customspell.header.SubformHeaderView;
import com.myrran.view.ui.customspell.icon.DebuffIconView;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.util.List;

/** @author Ivan Delgado Huerta */
public class TemplateSubformView extends Table implements DetailedActorI, Disposable
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;

    private SubformHeaderView header;
    private Table details;

    private List<DebuffIconView> debuffs;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        header      = new SubformHeaderView();
        details     = new Table();

        createLayout();
    }

    @Override public void dispose()
    {   header.dispose(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellSubform templateSpellSubform)
    {
        if (templateSpellSubform == null)
            removeModel();
        else
        {
            model = templateSpellSubform;
            header.setModel(model);
            update();
        }
    }

    private void removeModel()
    {   header.removeModel(); }

    private void update()
    {
        details.clear();
        details.add().size(32+3);

        model.getSpellSlots().stream()
            .map(DebuffIconView::new)
            .forEach(o -> details.add(o));
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
        add(details).bottom().left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {

    }
}
