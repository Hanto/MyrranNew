package com.myrran.view.ui.customspell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffTarget;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** @author Ivan Delgado Huerta */
public class CustomDebuffIconView extends CustomIconView implements PropertyChangeListener, Disposable
{
    private CustomDebuffSlot modelSlot;
    private CustomSpellDebuff modelDebuff;
    private CustomSpellController controller;
    private DadDebuffTarget dadTarget;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomDebuffIconView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        dadTarget   = new DadDebuffTarget(this, controller);

        controller.getDadDebuff().addTarget(dadTarget);
        addListener(new TouchDownRightListener(event ->
            controller.removeCustomSpellDebuff(modelSlot)));
    }

    @Override public void dispose()
    {
        disposeObservers();
        controller.getDadDebuff().removeTarget(dadTarget);
    }

    private void disposeObservers()
    {
        if (modelSlot != null)
            modelSlot.removeObserver(this);

        if (modelDebuff != null)
            modelDebuff.removeObserver(this);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot customDebuffSlot)
    {
        disposeObservers();

        if (customDebuffSlot == null)
            setVisible(false);
        else
        {
            setVisible(true);
            modelSlot = customDebuffSlot;
            modelDebuff = modelSlot.getCustomSpellDebuff();
            dadTarget.setModel(modelSlot);
            modelSlot.addObserver(this);
            modelDebuff.addObserver(this);
            update();
        }
    }

    private void update()
    {
        setName2(modelSlot.getSlotType());

        if (modelSlot.hasData())
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
            setCorner(modelDebuff.getTotalCost().toString());
            setName1(modelDebuff.getName());
            setName1Color(Color.ORANGE);
        }
        else
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/IconoVacio2"));
            setCorner(null);
            setName1(modelSlot.getLock().toString().toLowerCase());
            setName1Color(Color.LIGHT_GRAY);
        }
    }

    public void setLockColor(Color color)
    {   setName1Color(color); }

    public void setDefaultLocColor()
    {   setName1Color(modelDebuff.hasData() ? Color.ORANGE : Color.LIGHT_GRAY); }

    // MVC:
    //--------------------------------------------------------------------------------------------------------

    @Override public void propertyChange(PropertyChangeEvent evt)
    {   update(); }
}
