package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/** @author Ivan Delgado Huerta */
public class CustomSpellUpgradesInputListener extends InputListener
{
    public enum StatsType { DebuffStats, SubformStats }

    private String formID;
    private String slotID;
    private String statID;
    private StatsType type;


    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellUpgradesInputListener(String formID, StatsType type, String slotID, String statID)
    {
        this.formID = formID;
        this.slotID = slotID;
        this.statID = statID;
        this.type = type;
    }

    public CustomSpellUpgradesInputListener(String formID, String statID)
    {
        this.formID = formID;
        this.slotID = slotID;
        this.statID = statID;
    }


    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent even, float x, float y, int pointer, int button)
    {


        return true;
    }

}
