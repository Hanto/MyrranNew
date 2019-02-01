package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.Atlas;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomSubformIconView extends CustomIconView implements PropertyChangeListener, Disposable
{
    private CustomSubformSlot modelSlot;
    private CustomSpellSubform modelSubform;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSubformIconView(CustomSpellController customSpellController)
    {   controller = customSpellController; }

    @Override public void dispose()
    {   disposeObservers(); }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);

        if (modelSubform != null)
            modelSubform.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlot customSubformSlot)
    {
        disposeObservers();

        if (customSubformSlot == null)
            removeAll();
        else
        {
            modelSlot = customSubformSlot;
            modelSubform = modelSlot.getCustomSpellSubform();
            modelSlot.addObserver(this);
            modelSubform.addObserver(this);
            update();
        }
    }

    private void update()
    {
        setName2(modelSlot.getSlotType());

        if (modelSlot.hasData())
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
            Integer baseAndStats = modelSubform.getStatCost() + modelSubform.getBaseCost();
            setCorner(baseAndStats.toString());
            setName1(modelSubform.getName());
            setName1Color(Color.ORANGE);
        }
        else
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/IconoVacio2"));
            setCorner("0");
            setName1(modelSlot.getLock().toString().toLowerCase());
            setName1Color(Color.LIGHT_GRAY);
        }
    }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
