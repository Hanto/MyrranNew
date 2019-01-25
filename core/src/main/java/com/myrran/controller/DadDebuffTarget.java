package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;

import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.generators.custom.CustomDebuffSlot;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        try
        {
            TemplateSpellDebuff templadeDebuff = (TemplateSpellDebuff) payload.getObject();

            if (model.opensLock(templadeDebuff.getKeys()) && !model.hasDebuff())
            { controller.addCustomSpellDebuff(model, templadeDebuff.getID()); }
        }
        catch (InvalidIDException e)
        {   LOG.warn("Cannot drop debuff here", e); }
    }
}
