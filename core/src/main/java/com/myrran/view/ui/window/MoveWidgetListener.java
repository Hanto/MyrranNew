package com.myrran.view.ui.window;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

/** @author Ivan Delgado Huerta */
public class MoveWidgetListener extends DragListener
{
    private Actor parent;
    private Actor dragActor;

    public MoveWidgetListener(Actor drager)
    {
        this.dragActor = drager;
        this.parent = drager.getParent();
        setTapSquareSize(0);
    }

    @Override public boolean touchDown (InputEvent event, float x, float y, int pointer, int button)
    {
        super.touchDown(event, x, y, pointer, button);
        parent.toFront();
        return true;
    }

    @Override public void drag(InputEvent event, float x, float y, int pointer)
    {
        float scrollX = getDragX() - getTouchDownX();
        float scrollY = getDragY() - getTouchDownY();

        parent.moveBy(scrollX, scrollY);

        Vector2 dragPos = dragActor.localToStageCoordinates(new Vector2());

        if (dragPos.x < 0)
            parent.setX(-dragActor.getX());

        if (dragPos.x + dragActor.getWidth() > Gdx.graphics.getWidth())
            parent.setX(Gdx.graphics.getWidth() - dragActor.getWidth() - dragActor.getX());

        if (dragPos.y < 0)
            parent.setY(-dragActor.getY());

        if (dragPos.y + dragActor.getHeight() > Gdx.graphics.getHeight())
            parent.setY(Gdx.graphics.getHeight() - dragActor.getHeight() - dragActor.getY());
    }
}
