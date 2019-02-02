package com.myrran.view.ui.customspell.icon;

import com.badlogic.gdx.graphics.Color;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DaDSubformTarget;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;

/** @author Ivan Delgado Huerta */
public class SubformSlotView extends AbstractSpellIconView<CustomSubformSlot>
{
    private CustomSpellSubform modelSubform;
    private CustomSpellController controller;
    private DaDSubformTarget dadTarget;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SubformSlotView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        dadTarget   = new DaDSubformTarget(this, controller);

        controller.getDadSubform().addTarget(dadTarget);
        addListener(new TouchDownRightListener(event ->
            controller.removeCustomSpellSubform(model)));
    }

    @Override protected void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);

        if (modelSubform != null)
            modelSubform.removeObserver(this);
    }

    @Override public void dispose()
    {
        disposeObservers();
        controller.getDadSubform().removeTarget(dadTarget);
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
            model = customSubformSlot;
            modelSubform = model.getContent();
            dadTarget.setModel(model);
            model.addObserver(this);
            modelSubform.addObserver(this);
            update();
        }
    }

    @Override
    protected void update()
    {
        setName2(model.getSlotType());

        if (model.hasData())
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
            setName1(model.getLock().toString().toLowerCase());
            setName1Color(Color.LIGHT_GRAY);
        }
    }

    public void setLockColor(Color color)
    {   setName1Color(color); }

    public void setDefaultLocColor()
    {   setName1Color(modelSubform.hasData() ? Color.ORANGE : Color.LIGHT_GRAY); }
}
