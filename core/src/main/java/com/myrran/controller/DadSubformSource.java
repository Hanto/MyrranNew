package com.myrran.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.myrran.model.spell.templates.TemplateSpellSubform;
import com.myrran.view.ui.Atlas;
import com.myrran.view.ui.widgets.WidgetText;

import static com.myrran.view.ui.widgets.DaD.Payload;
import static com.myrran.view.ui.widgets.DaD.Source;

/** @author Ivan Delgado Huerta */
public class DadSubformSource extends Source
{
    private TemplateSpellSubform model;
    private CustomSpellController controller;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public DadSubformSource(Actor actor, CustomSpellController spellController)
    {
        super(actor);
        controller = spellController;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void setModel(TemplateSpellSubform template)
    {   model = template; }

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
