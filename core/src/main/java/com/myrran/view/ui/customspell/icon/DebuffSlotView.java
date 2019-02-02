package com.myrran.view.ui.customspell.icon;

import com.badlogic.gdx.graphics.Color;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffTarget;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;

/** @author Ivan Delgado Huerta */
public class DebuffSlotView extends AbstractSpellIconView<CustomDebuffSlot>
{
    private CustomSpellDebuff modelDebuff;
    private CustomSpellController controller;
    private DadDebuffTarget dadTarget;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DebuffSlotView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        dadTarget   = new DadDebuffTarget(this, controller);

        controller.getDadDebuff().addTarget(dadTarget);

        addListener(new TouchDownRightListener(event ->
            controller.removeCustomSpellDebuff(model)));
    }

    @Override protected void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);

        if (modelDebuff != null)
            modelDebuff.removeObserver(this);
    }

    @Override public void dispose()
    {
        disposeObservers();
        controller.getDadDebuff().removeTarget(dadTarget);
    }

    // UPDATE:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot customDebuffSlot)
    {
        disposeObservers();

        if (customDebuffSlot == null)
            removeModel();
        else
        {
            model = customDebuffSlot;
            modelDebuff = model.getCustomSpellDebuff();
            dadTarget.setModel(model);
            model.addObserver(this);
            modelDebuff.addObserver(this);
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
            setCorner(modelDebuff.getTotalCost().toString());
            setName1(modelDebuff.getName());
            setName1Color(Color.ORANGE);
        }
        else
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/IconoVacio2"));
            setCorner(null);
            setName1(model.getLock().toString().toLowerCase());
            setName1Color(Color.LIGHT_GRAY);
        }
    }

    public void setLockColor(Color color)
    {   setName1Color(color); }

    public void setDefaultLocColor()
    {   setName1Color(modelDebuff.hasData() ? Color.ORANGE : Color.LIGHT_GRAY); }
}