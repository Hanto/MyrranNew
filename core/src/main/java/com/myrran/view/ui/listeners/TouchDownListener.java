package com.myrran.view.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.function.Consumer;

/** @author Ivan Delgado Huerta */
public class TouchDownListener extends InputListener
{
    private Consumer<InputEvent> consumer;

    public TouchDownListener(Consumer<InputEvent> consumer)
    {   this.consumer = consumer; }

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        consumer.accept(event);
        return true;
    }
}
