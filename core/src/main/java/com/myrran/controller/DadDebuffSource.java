package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;

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
        Image dragActor = Atlas.get().getImage("TexturasIconos/IceBall");
        Payload payload = new Payload();
        payload.setObject(model);
        payload.setDragActor(dragActor);
        return payload;
    }
}
