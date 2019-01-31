package com.myrran.view.ui.customspell;

import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.CustomSubformSlot;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubformSlotView implements PropertyChangeListener, Disposable
{
    private CustomSubformSlot modelSlot;
    private CustomSpellSubform modelSubform;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformSlotView(CustomSpellController customSpellController)
    {   controller = customSpellController; }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);

        if (modelSubform != null)
            modelSubform.removeObserver(this);
    }

    @Override public void dispose()
    {   disposeObservers(); }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    private void update()
    {

    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
