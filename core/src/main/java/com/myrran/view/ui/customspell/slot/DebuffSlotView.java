package com.myrran.view.ui.customspell.slot;

import com.badlogic.gdx.graphics.Color;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DadDebuffTarget;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;

/** @author Ivan Delgado Huerta */
public class DebuffSlotView extends AbstractSpellSlotView<CustomSpellDebuff, TemplateSpellDebuff>
{
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

    // ABSTRACT IMPLEMENTATIONS:
    //--------------------------------------------------------------------------------------------------------

    @Override protected void disposeImp()
    {   controller.getDadDebuff().removeTarget(dadTarget); }

    @Override protected void setModelImp()
    {   dadTarget.setModel(model); }

    @Override
    protected void update()
    {
        setName2(model.getSlotType());

        if (model.hasData())
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
            setCorner(contentModel.getTotalCost().toString());
            setName1(contentModel.getName());
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
}