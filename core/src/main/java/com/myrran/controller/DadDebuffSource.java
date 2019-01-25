package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.myrran.model.spell.templates.TemplateSpellDebuff;
import com.myrran.view.ui.Atlas;

/** @author Ivan Delgado Huerta */
public class DadDebuffSource extends Source
{
    private TemplateSpellDebuff model;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DadDebuffSource(Actor actor)
    {   super(actor); }

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
