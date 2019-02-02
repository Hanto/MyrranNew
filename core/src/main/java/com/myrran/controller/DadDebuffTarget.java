package com.myrran.controller;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.generators.CustomDebuffSlot;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.customspell.icon.DebuffSlotView;
import com.myrran.view.ui.widgets.DaD.Payload;
import com.myrran.view.ui.widgets.DaD.Source;
import com.myrran.view.ui.widgets.DaD.Target;
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

    public DadDebuffTarget(DebuffSlotView actor, CustomSpellController spellController)
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

        if (model.opensLock(templadeDebuff.getKeys()))
        {
            if (model.hasData())
                controller.removeCustomSpellDebuff(model);

            controller.addCustomSpellDebuff(model, templadeDebuff.getID());
        }
    }

    @Override
    public void notifyNewPayload(Payload payload)
    {
        DebuffSlotView view = (DebuffSlotView)getActor();
        TemplateSpellDebuff template = (TemplateSpellDebuff)payload.getObject();

        view.setLockColor(model.opensLock(template.getKeys()) ? Color.GREEN : Color.RED);
    }

    @Override
    public void notifyNoPayload()
    {
        DebuffSlotView view = (DebuffSlotView)getActor();
        view.setDefaultLocColor();
    }
}