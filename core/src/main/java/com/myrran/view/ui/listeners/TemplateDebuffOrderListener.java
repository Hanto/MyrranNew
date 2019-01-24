package com.myrran.view.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.view.ui.spellbook.TemplateDebuffOptions;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffOrderListener extends InputListener
{
    private TemplateDebuffOptions options;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffOrderListener(TemplateDebuffOptions debuffView)
    {   options = debuffView; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        options.sortBy(options.getSortBy());
        return true;
    }
}
