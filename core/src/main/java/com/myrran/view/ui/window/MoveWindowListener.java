package com.myrran.view.ui.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/** @author Ivan Delgado Huerta */
public class MoveWindowListener extends DragListener
{
    private Actor actor;
    private Actor dragActor;

    public MoveWindowListener(Actor drager, Actor actor)
    {
        this.dragActor = drager;
        this.actor = actor;
        setTapSquareSize(1);
    }

    @Override public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
    {
        super.touchDown(event, x, y, pointer, button);
        actor.toFront();
        return true;
    }

    @Override public void drag(InputEvent event, float x, float y, int pointer)
    {
        float scrollX = getDragX() - getDragStartX();
        float scrollY = getDragY() - getDragStartY();

        actor.moveBy(scrollX, scrollY);

        Vector2 dragPos = dragActor.localToStageCoordinates(new Vector2());

        if (dragPos.x < 0)
            actor.setX(-dragActor.getX());

        if (dragPos.x + dragActor.getWidth() > Gdx.graphics.getWidth())
            actor.setX(Gdx.graphics.getWidth() - dragActor.getWidth() - dragActor.getX());

        if (dragPos.y < 0)
            actor.setY(-dragActor.getY());

        if (dragPos.y + dragActor.getHeight() > Gdx.graphics.getHeight())
            actor.setY(Gdx.graphics.getHeight() - dragActor.getHeight() - dragActor.getY());
    }
}
