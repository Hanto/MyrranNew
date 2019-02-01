package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.customspell.header.TFormHeaderView;
import com.myrran.view.ui.widgets.DetailedActorI;

/** @author Ivan Delgado Huerta */
public class TemplateFormView extends Table implements DetailedActorI, Disposable
{
    private TemplateSpellForm model;
    private CustomSpellController controller;

    private TFormHeaderView header;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateFormView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        header          = new TFormHeaderView();

        createLayout();
    }

    @Override public void dispose()
    {   header.dispose(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellForm templateSpellForm)
    {
        if (templateSpellForm == null)
            removeModel();
        else
        {
            model = templateSpellForm;
            header.setModel(model);
        }
    }

    private void removeModel()
    {   header.removeModel(); }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(header).top().left();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean showDetails)
    {

    }
}
