package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.view.ui.spellbook.TemplateDebuffOptions;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffShowListener extends InputListener
{
    private TemplateDebuffOptions options;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffShowListener(TemplateDebuffOptions debuffView)
    {   options = debuffView; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        options.setShowDetails();
        return true;
    }
}
