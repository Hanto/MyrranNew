package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.view.ui.spellbook.TemplateDebuffOptions;

import static com.myrran.view.ui.spellbook.TemplateDebuffOptions.SortDebuffsBy;

/** @author Ivan Delgado Huerta */
public class TemplateDebuffSortListener extends InputListener
{
    private TemplateDebuffOptions options;
    private SortDebuffsBy type;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public TemplateDebuffSortListener(TemplateDebuffOptions debuffView, SortDebuffsBy sortType)
    {
        options = debuffView;
        type = sortType;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        options.sortBy(type);
        return true;
    }
}
