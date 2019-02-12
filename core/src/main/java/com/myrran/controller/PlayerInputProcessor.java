package com.myrran.controller;

import com.badlogic.gdx.InputProcessor;
import com.myrran.model.mob.Player;

/** @author Ivan Delgado Huerta */
public class PlayerInputProcessor implements InputProcessor
{
    private Player model;
    private PlayerInputs inputs;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public PlayerInputProcessor(Player player)
    {
        model = player;
        inputs = new PlayerInputs(model);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean keyDown(int keycode)
    {
        switch (keycode)
        {
            case 47: inputs.setInputDown(true); break;
            case 51: inputs.setInputUp(true); break;
            case 29: inputs.setInputLeft(true); break;
            case 32: inputs.setInputRight(true); break;
        }
        return true;
    }

    @Override public boolean keyUp(int keycode)
    {
        switch (keycode)
        {
            case 47: inputs.setInputDown(false); break;
            case 51: inputs.setInputUp(false); break;
            case 29: inputs.setInputLeft(false); break;
            case 32: inputs.setInputRight(false); break;
        }
        return true;
    }

    // UNINPLEMENTED:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean keyTyped(char character) { return true; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return true; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return true; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return true; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return true; }
    @Override public boolean scrolled(int amount) { return true; }
}
