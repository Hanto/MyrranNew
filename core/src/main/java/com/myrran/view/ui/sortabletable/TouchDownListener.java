package com.myrran.view.ui.sortabletable;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.function.Consumer;

/** @author Ivan Delgado Huerta */
public class TouchDownListener extends InputListener
{
    private Consumer<Object> consumer;

    public TouchDownListener(Consumer<Object> consumer)
    {   this.consumer = consumer; }

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        consumer.accept(null);
        return true;
    }
}
