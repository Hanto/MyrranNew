package com.myrran.view.ui.sortabletable;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

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
