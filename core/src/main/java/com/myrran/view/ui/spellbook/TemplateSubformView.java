package com.myrran.view.ui.spellbook;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.widgets.DetailedActorI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class TemplateSubformView extends Table implements DetailedActorI, Disposable, PropertyChangeListener
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateSubformView(CustomSpellController customSpellController)
    {
        controller = customSpellController;
        createLayout();
    }


    @Override public void dispose()
    {
        disposeObservers();
   }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    private void update()
    {

    }

    private void createLayout()
    {

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
