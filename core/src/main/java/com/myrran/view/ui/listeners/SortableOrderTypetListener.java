package com.myrran.view.ui.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.view.ui.spellbook.SortableOptions;

/** @author Ivan Delgado Huerta */
public class SortableOrderTypetListener extends InputListener
{
    private SortableOptions options;
    private String type;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SortableOrderTypetListener(SortableOptions debuffView, String sortName)
    {
        options = debuffView;
        type = sortName;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        options.setSortBy(type);
        return true;
    }
}
