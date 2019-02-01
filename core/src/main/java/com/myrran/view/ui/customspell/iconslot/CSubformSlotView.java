package com.myrran.view.ui.customspell.iconslot;

import com.badlogic.gdx.graphics.Color;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class CSubformSlotView extends SpellIconView<CustomSubformSlot>
{
    private CustomSpellSubform modelSubform;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CSubformSlotView(CustomSpellController customSpellController)
    {   controller = customSpellController; }

    @Override protected void disposeObservers()
    {
        if (model != null)
            model.removeObserver(this);

        if (modelSubform != null)
            modelSubform.removeObserver(this);
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
            modelSubform = model.getCustomSpellSubform();
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
}
