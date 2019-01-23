package com.myrran.view.ui.listeners;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.myrran.data.Settings;

/** @author Ivan Delgado Huerta */
public class MoveActorListener extends DragListener
{
    private Actor parent;
    private Actor dragger;

    private Vector2 draggerPos;
    private Vector2 parentPos;
    private Vector2 offset;

    private static final float uiHeight = Settings.get().getGraphicSettings().verticalRes;
    private static final float uiWidth = Settings.get().getGraphicSettings().horizontalRes;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public MoveActorListener(Actor parent)
    {
        this.parent = parent;
        setTapSquareSize(0);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

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

        dragger = event.getListenerActor();
        parent.moveBy(scrollX, scrollY);

        draggerPos = dragger.localToStageCoordinates(new Vector2());
        parentPos = parent.localToStageCoordinates(new Vector2());
        offset = parentPos.sub(draggerPos);

        if (draggerPos.x < 0)
            parent.setX(offset.x);

        if (draggerPos.x + dragger.getWidth() > uiWidth)
            parent.setX(uiWidth - dragger.getWidth() + offset.x);

        if (draggerPos.y < 0)
            parent.setY(offset.y);

        if (draggerPos.y + dragger.getHeight() > uiHeight)
            parent.setY(uiHeight - dragger.getHeight() + offset.y);
    }
}
