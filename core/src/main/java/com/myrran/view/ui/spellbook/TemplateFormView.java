package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellForm;
import com.myrran.view.ui.customspell.header.FormHeaderView;
import com.myrran.view.ui.widgets.DetailedActorI;

/** @author Ivan Delgado Huerta */
public class TemplateFormView extends DetailsTable implements DetailedActorI, Disposable
{
    private TemplateSpellForm model;
    private CustomSpellController controller;

    private FormHeaderView header;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateFormView(CustomSpellController customSpellController)
    {
        controller      = customSpellController;
        header          = new FormHeaderView();

        createLayout();
    }

    @Override public void dispose()
    {   header.dispose(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellForm templateSpellForm)
    {
        if (templateSpellForm == null)
        {
            header.setModel(null);
        }
        else
        {
            model = templateSpellForm;
            header.setModel(model);
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
    }

}
