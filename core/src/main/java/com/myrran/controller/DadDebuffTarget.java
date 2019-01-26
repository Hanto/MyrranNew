package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;

/** @author Ivan Delgado Huerta */
public class DadDebuffTarget extends Target
{
    private CustomDebuffSlot model;
    private CustomSpellController controller;

    private static Logger LOG = LogManager.getFormatterLogger(DadDebuffTarget.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DadDebuffTarget(Actor actor, CustomSpellController spellController)
    {
        super(actor);
        controller = spellController;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(CustomDebuffSlot customDebuffSlot)
    {   model = customDebuffSlot; }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer)
    {   return true; }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer)
    {
        TemplateSpellDebuff templadeDebuff = (TemplateSpellDebuff) payload.getObject();

        if (model.opensLock(templadeDebuff.getKeys()) && !model.hasDebuff())
        { controller.addCustomSpellDebuff(model, templadeDebuff.getID()); }
    }
}
