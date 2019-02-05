package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.spellbook.customspell.CustomFormView;
import com.myrran.view.ui.spellbook.customspell.CustomSubformsView;
import com.myrran.view.ui.spellbook.header.SpellHeaderView;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedTable;

/** @author Ivan Delgado Huerta */
public class CustomSpellView extends DetailedTable implements Disposable
{
    private SpellHeaderView header;
    private CustomFormView formView;
    private CustomSubformsView subForms;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellView(CustomSpellController controller, boolean movable)
    {
        header          = new SpellHeaderView();
        formView        = new CustomFormView(controller);
        subForms        = new CustomSubformsView(controller);

        if (movable)
            header.getIcon().addListener(new ActorMoveListener(this));
        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));

        tableHeader.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.3f));
        tableDetails.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.55f));

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
        tableDetails.top().left();;

        //tableDetails.add().size(32, 0);
        tableDetails.add(formView).top().left().row();
        //tableDetails.add().size(32, 0);
        tableDetails.add(subForms).top().left().row();
    }
}