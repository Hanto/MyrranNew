package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.ActorMoveListener;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.listeners.TouchDownRightListener;
import com.myrran.view.ui.spellbook.customspell.CustomFormView;
import com.myrran.view.ui.spellbook.customspell.CustomSubformsView;
import com.myrran.view.ui.spellbook.header.SpellHeaderView;
import com.myrran.view.ui.widgets.DetailedTable;

/** @author Ivan Delgado Huerta */
public class CustomSpellView extends DetailedTable implements Disposable
{
    private CustomSpellForm model;

    private SpellHeaderView header;
    private CustomFormView formView;
    private CustomSubformsView subForms;

    private static final int MINWIDTH = 348;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellView(CustomSpellController controller, boolean movable)
    {
        header          = new SpellHeaderView();
        formView        = new CustomFormView(controller);
        subForms        = new CustomSubformsView(controller);

        header.getIconName().addListener(new TouchDownListener(o -> showDetails()));
        header.getIcon().addListener(new TouchDownRightListener(event -> newWindow(movable, controller)));
        tableHeader.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine", 0.3f));
        tableDetails.setBackground(Atlas.get().getNinePatchDrawable("TexturasIconos/IconoVacioNine2", 0.90f));

        if (movable)
        {
            tableHeader.setTouchable(Touchable.enabled);
            tableHeader.addListener(new ActorMoveListener(this));
        }

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
        this.model = model;

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
        tableHeader.clearChildren();
        tableHeader.add(header).minWidth(MINWIDTH);

        tableDetails.clearChildren();
        tableDetails.top().left();;

        tableDetails.add(formView).minWidth(MINWIDTH).row();
        tableDetails.add(subForms).top().left().row();
    }

    // NEW WINDOW:
    //--------------------------------------------------------------------------------------------------------

    private void newWindow(boolean movable, CustomSpellController controller)
    {
        if (!movable)
        {
            CustomSpellView spellView = new CustomSpellView(controller);
            getStage().addActor(spellView);
            spellView.setModel(model);
            spellView.setPosition(Gdx.input.getX(), Gdx.graphics.getHeight() -Gdx.input.getY());
            spellView.addAction(new Action()
            {
                @Override public boolean act(float delta)
                {   spellView.toFront(); return true; }
            });
        }
        else
        {
            dispose();
            remove();
        }
    }
}