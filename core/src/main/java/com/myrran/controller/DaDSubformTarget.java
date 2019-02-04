package com.myrran.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.model.spell.generators.CustomSpellSubform;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.spellbook.icon.contentview.SubformSlotContentView;
import com.myrran.view.ui.widgets.DaD;
import com.myrran.view.ui.widgets.DaD.Payload;
import com.myrran.view.ui.widgets.DaD.Source;
import com.myrran.view.ui.widgets.DaD.Target;

/** @author Ivan Delgado Huerta */
public class DaDSubformTarget extends Target
{
    private SpellSlotI<CustomSpellSubform, TemplateSpellSubform> model;
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

    public void setModel(SpellSlotI<CustomSpellSubform, TemplateSpellSubform> subformSlot)
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
        SubformSlotContentView view = (SubformSlotContentView)getActor();
        TemplateSpellSubform template = (TemplateSpellSubform)payload.getObject();

        view.setLockColor(model.opensLock(template.getKeys()) ? Color.GREEN : Color.RED);
    }

    @Override
    public void notifyNoPayload()
    {
        SubformSlotContentView view = (SubformSlotContentView)getActor();
        view.setDefaultLockColor();
    }
}
