package com.myrran.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.model.spell.generators.CustomSubformSlot;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.customspell.icon.SubformSlotView;
import com.myrran.view.ui.widgets.DaD;
import com.myrran.view.ui.widgets.DaD.Payload;
import com.myrran.view.ui.widgets.DaD.Source;
import com.myrran.view.ui.widgets.DaD.Target;

/** @author Ivan Delgado Huerta */
public class DaDSubformTarget extends Target
{
    private CustomSubformSlot model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DaDSubformTarget(Actor actor, CustomSpellController spellController)
    {
        super(actor);
        controller = spellController;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomSubformSlot subformSlot)
    {   model = subformSlot; }

    @Override
    public boolean drag(Source source, DaD.Payload payload, float x, float y, int pointer)
    {   return true; }

    @Override
    public void drop(Source source, DaD.Payload payload, float x, float y, int pointer)
    {
        TemplateSpellSubform templateSubform = (TemplateSpellSubform) payload.getObject();

        if (model.opensLock(templateSubform.getKeys()))
        {
            if (model.hasData())
                controller.removeCustomSpellSubform(model);

            controller.addCustomSpellSubform(model, templateSubform.getID());
        }
    }

    @Override
    public void notifyNewPayload(Payload payload)
    {
        SubformSlotView view = (SubformSlotView)getActor();
        TemplateSpellSubform template = (TemplateSpellSubform)payload.getObject();

        view.setLockColor(model.opensLock(template.getKeys()) ? Color.GREEN : Color.RED);
    }

    @Override
    public void notifyNoPayload()
    {
        SubformSlotView view = (SubformSlotView)getActor();
        view.setDefaultLocColor();
    }
}
