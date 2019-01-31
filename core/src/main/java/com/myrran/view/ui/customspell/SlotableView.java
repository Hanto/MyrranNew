package com.myrran.view.ui.customspell;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomFormI;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class SlotableView extends Table implements PropertyChangeListener, Disposable, DetailedActorI
{
    private CustomFormI model;
    private CustomSpellController controller;

    private Table slots;
    private Table stats;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SlotableView(CustomSpellController spellController)
    {
        controller  = spellController;
        slots       = new Table();
        stats       = new Table();
    }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    @Override public void dispose()
    {   disposeObservers(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomFormI customForm)
    {
        disposeObservers();

        if (customForm == null)
            removeModel();
        else
        {
            model = customForm;
            model.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {

    }

    private void update()
    {

    }

    private void createLayout()
    {
        clear();
        top().left();
        add(slots);
        add(stats);
    }

    // MISC:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void showDetails(boolean visible)
    {

    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }

}
