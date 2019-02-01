package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.customspell.header.TSubformHeaderView;
import com.myrran.view.ui.widgets.DetailedActorI;

/** @author Ivan Delgado Huerta */
public class TemplateSubformView extends Table implements DetailedActorI, Disposable
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;
    private TSubformHeaderView header;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        header      = new TSubformHeaderView();

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
        }
    }

    private void removeModel()
    {   header.removeAll(); }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).bottom().left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {

    }
}
