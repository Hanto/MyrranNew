package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellForm;
import com.myrran.view.ui.Atlas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomFormIconView extends CustomIconView implements PropertyChangeListener, Disposable
{
    private CustomSpellForm model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomFormIconView(CustomSpellController customSpellController)
    {   controller = customSpellController; }

    @Override public void dispose()
    {   disposeObservers(); }

    private void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSpellForm customSubformSlot)
    {
        disposeObservers();

        if (customSubformSlot == null)
            removeAll();
        else
        {
            model = customSubformSlot;
            model.addObserver(this);
            update();
        }
    }

    private void update()
    {
        setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
        setCorner(model.getStatsCost().toString());
        setName1(model.getName());
        setName1Color(Color.ORANGE);
        setName2(model.getTemplateID().toUpperCase());
        setName2Color(Color.WHITE);
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
