package com.myrran.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import static com.myrran.view.ui.widgets.DaD.Payload;
import static com.myrran.view.ui.widgets.DaD.Source;

/** @author Ivan Delgado Huerta */
public class DadDebuffSource extends Source
{
    private TemplateSpellDebuff model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DadDebuffSource(Actor actor, CustomSpellController spellController)
    {
        super(actor);
        controller = spellController;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellDebuff templateSpellDebuff)
    {   model = templateSpellDebuff; }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer)
    {
        if (model != null)
        {
            WidgetText dragActor = new WidgetText(model.getName(), Atlas.get().getFont("20"), Color.ORANGE, Color.BLACK, 1);
            Payload payload = new Payload();
            payload.setObject(model);
            payload.setDragActor(dragActor);
            return payload;
        }
        else return null;
    }
}
