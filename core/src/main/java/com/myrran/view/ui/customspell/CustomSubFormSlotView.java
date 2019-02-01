package com.myrran.view.ui.customspell;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.customspell.iconslot.CSubformSlotView;
import com.myrran.view.ui.listeners.TouchDownListener;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubFormSlotView extends Table implements Disposable, DetailedActorI, PropertyChangeListener
{
    private CustomSubformSlot modelSlot;
    private CustomSpellSubform modelSubform;
    private CustomSpellController controller;

    private CSubformSlotView icon;
    private FormView formView;

    private Table slots;
    private Table stats;

    private boolean detailsVisible = false;
    private Cell<Actor> detailsCell;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubFormSlotView(CustomSpellController spellController)
    {
        controller  = spellController;
        formView    = new FormView(controller);
        icon        = new CSubformSlotView(controller);
        slots       = new Table();
        stats       = new Table();

        icon.addListener(new TouchDownListener(event ->
        {   if (event.getButton() == Input.Buttons.LEFT) showDetails(); }));

        createLayout();
        detailsCell = getCell(stats);
        showDetails();
    }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);
    }

    @Override public void dispose()
    {
        disposeObservers();
        formView.dispose();
        icon.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlot subformSlot)
    {
        disposeObservers();

        if (subformSlot == null)
            removeModel();
        else
        {
            modelSlot = subformSlot;
            modelSlot.addObserver(this);
            modelSubform = modelSlot.getCustomSpellSubform();
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
        stats.add(formView.getFormStats()).row();

        icon.setModel(modelSlot);

        if (modelSlot.hasData())
        {
            formView.setModel(modelSubform);
            formView.getDebuffIcons().forEach(icon -> slots.add(icon).left());
            formView.getDebuffStats().forEach(debuff -> stats.add(debuff).left().row());
        }
        else
            formView.setModel(null);
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

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
