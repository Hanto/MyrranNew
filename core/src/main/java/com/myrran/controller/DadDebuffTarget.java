package com.myrran.controller;

import com.badlogic.gdx.graphics.Color;
import com.myrran.model.spell.generators.CustomSpellDebuff;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.model.spell.generators.SpellSlotI;
import com.myrran.view.ui.spellbook.icon.contentview.DebuffSlotContentView;
import com.myrran.view.ui.widgets.DaD.Payload;
import com.myrran.view.ui.widgets.DaD.Source;
import com.myrran.view.ui.widgets.DaD.Target;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class DadDebuffTarget extends Target
{
    private SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> model;
    private CustomSpellController controller;

    private static Logger LOG = LogManager.getFormatterLogger(DadDebuffTarget.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DadDebuffTarget(DebuffSlotContentView actor, CustomSpellController spellController)
    {
        super(actor);
        controller = spellController;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(SpellSlotI<CustomSpellDebuff, TemplateSpellDebuff> customDebuffSlot)
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
        DebuffSlotContentView view = (DebuffSlotContentView)getActor();
        TemplateSpellDebuff template = (TemplateSpellDebuff)payload.getObject();

        view.setLockColor(model.opensLock(template.getKeys()) ? Color.GREEN : Color.RED);
    }

    @Override
    public void notifyNoPayload()
    {
        DebuffSlotContentView view = (DebuffSlotContentView)getActor();
        view.setDefaultLockColor();
    }
}