package com.myrran.view.ui.customspell;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSubformSlot;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubformSlotView implements PropertyChangeListener, Disposable
{
    private CustomSubformSlot modelSlot;
    private CustomSpellController controller;

    private CustomSubformIconView iconView;
    public CustomFormView debuffsView;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformSlotView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        iconView    = new CustomSubformIconView(controller);
        debuffsView = new CustomFormView(controller, iconView);
    }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);
    }

    @Override public void dispose()
    {
        iconView.dispose();
        debuffsView.dispose();
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlot customSubformSlot)
    {
        disposeObservers();

        if (customSubformSlot == null)
            removeModel();
        else
        {
            modelSlot = customSubformSlot;
            modelSlot.addObserver(this);
            update();
        }
    }

    private void removeModel()
    {
        iconView.setModel(null);
        debuffsView.setModel(null);
    }

    private void update()
    {
        iconView.setModel(modelSlot);
        debuffsView.setModel(modelSlot.hasData() ? modelSlot.getCustomSpellSubform() : null);
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
