package com.myrran.view.ui.spellbook.icon.contentview;

import com.badlogic.gdx.graphics.Color;
import com.myrran.controller.CustomSpellController;
import com.myrran.controller.DaDSubformTarget;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.Atlas;
import com.myrran.view.ui.listeners.TouchDownRightListener;

/** @author Ivan Delgado Huerta */
public class SubformSlotContentView extends AbstractSlotContentView<CustomSpellSubform, TemplateSpellSubform>
{
    private CustomSpellController controller;
    private DaDSubformTarget dadTarget;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SubformSlotContentView(CustomSpellController customSpellController)
    {
        controller  = customSpellController;
        dadTarget   = new DaDSubformTarget(this, controller);

        controller.getDadSubform().addTarget(dadTarget);
        addListener(new TouchDownRightListener(event ->
            controller.removeCustomSpellSubform(model)));
    }

    // ABSTRACT IMPLEMENTATIONS:
    //--------------------------------------------------------------------------------------------------------

    @Override protected void disposeImp()
    {   controller.getDadSubform().removeTarget(dadTarget); }

    @Override protected void setModelImp()
    {   dadTarget.setModel(model); }

    @Override protected void update()
    {
        setName2(model.getSlotType());

        if (model.hasData())
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/FireBall2"));
            setName1(contentModel.getName());
            setName1Color(Color.ORANGE);
            Integer baseAndStats = contentModel.getStatCost() + contentModel.getBaseCost();
            setCorner(baseAndStats.toString());
        }
        else
        {
            setBackground(Atlas.get().getTexture("TexturasIconos/IconoVacio2"));
            setName1(model.getLock().toString().toLowerCase());
            setName1Color(Color.LIGHT_GRAY);
            setCorner(null);
        }
    }
}
