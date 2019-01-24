package com.myrran.view.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.view.ui.spellbook.SortableOptions;

/** @author Ivan Delgado Huerta */
public class SortableOrderDescListener extends InputListener
{
    private SortableOptions options;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SortableOrderDescListener(SortableOptions debuffView)
    {   options = debuffView; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        options.setSortBy(options.getActualSortBy().text);
        return true;
    }
}
