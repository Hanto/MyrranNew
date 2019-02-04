package com.myrran.view.ui.customspell.spellbook;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.customspell.spellbook.customspell.CustomFormView;
import com.myrran.view.ui.customspell.spellbook.customspell.CustomSubformsView;
import com.myrran.view.ui.customspell.header.SpellHeaderView;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;

/** @author Ivan Delgado Huerta */
public class CustomSpellView extends DetailsTable implements Disposable
{
    private CustomSpellController controller;
    private SpellHeaderView header;
    private CustomFormView formView;
    private CustomSubformsView subForms;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellView(CustomSpellController spellController, boolean movable)
    {
        controller      = spellController;
        header          = new SpellHeaderView();
        formView        = new CustomFormView(controller);
        subForms        = new CustomSubformsView(controller);

        if (movable)
            header.getIcon().addListener(new ActorMoveListener(this));
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        createLayout();
        createLayoutImp();
    }

    public CustomSpellView(CustomSpellController spellController)
    {   this(spellController, true); }

    @Override public void dispose()
    {
        header.dispose();
        formView.dispose();
        subForms.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm model)
    {
        if (model == null)
        {
            formView.setModel(null);
            header.setModel(null);
            subForms.setModel(null);
        }
        else
        {
            formView.setModel(model);
            header.setModel(model);
            subForms.setModel(model.getSubformSlots());
        }
    }

    // CREATE LAYOUTS:
    //--------------------------------------------------------------------------------------------------------

    private void createLayoutImp()
    {
        tableHeader.clear();
        tableHeader.add(header);

        tableDetails.clear();
        tableDetails.top().left();
        tableDetails.padBottom(8);

        tableDetails.add().size(32, 0);
        tableDetails.add(formView).top().left().row();
        tableDetails.add().size(32, 0);
        tableDetails.add(subForms).top().left().row();
    }
}