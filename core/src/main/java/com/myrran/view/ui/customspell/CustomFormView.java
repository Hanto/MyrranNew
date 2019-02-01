package com.myrran.view.ui.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.customspell.iconslot.CFormIconView;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;

/** @author Ivan Delgado Huerta */
public class CustomFormView extends Table implements Disposable, DetailedActorI
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    private CFormIconView icon;
    private FormView formView;

    private Table slots;
    private Table stats;

    private boolean detailsVisible = false;
    private Cell<Actor> detailsCell;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormView(CustomSpellController spellController)
    {
        controller  = spellController;
        formView    = new FormView(controller);
        icon        = new CFormIconView();
        slots       = new Table();
        stats       = new Table();

        icon.addListener(new TouchDownListener(event ->
        {   if (event.getButton() == Input.Buttons.LEFT) showDetails(); }));

        createLayout();
        detailsCell = getCell(stats);
        showDetails();
    }

    private void disposeObservers() {}
    @Override public void dispose()
    {
        formView.dispose();
        icon.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm customForm)
    {
        disposeObservers();

        if (customForm == null)
            removeModel();
        else
        {
            model = customForm;
            update();
        }
    }

    private void removeModel()
    {   clear(); }

    private void update()
    {
        slots.clear();
        slots.top().left();
        slots.add(icon).left().bottom();

        stats.clear();
        stats.top().left();
        stats.padBottom(4).padLeft(4).padTop(2);
        stats.add(formView.getFormStats()).left().row();

        formView.setModel(model);
        icon.setModel(model);

        formView.getDebuffIcons().forEach(icon -> slots.add(icon).left());
        formView.getDebuffStats().forEach(debuff -> stats.add(debuff).left().row());
    }

    // CREATE LAYOUT:
    //--------------------------------------------------------------------------------------------------------

    private void createLayout()
    {
        clear();
        top().left();
        add(slots).left().row();
        add(stats).left().row();
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {
        detailsCell.setActor(visible ? stats : null);
        detailsVisible = !visible;
    }

    public void showDetails()
    {   showDetails(detailsVisible); }
}
