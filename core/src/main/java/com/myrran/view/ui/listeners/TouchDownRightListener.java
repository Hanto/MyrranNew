package com.myrran.view.ui.listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.function.Consumer;

/** @author Ivan Delgado Huerta */
public class TouchDownRightListener extends InputListener
{
    private Consumer<InputEvent> consumer;
    public TouchDownRightListener(Consumer<InputEvent> consumer)
    {   this.consumer = consumer; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        if (button == Input.Buttons.RIGHT)
            consumer.accept(event);
        return true;
    }
}
