package com.myrran.view.ui.sortabletable;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/** @author Ivan Delgado Huerta */
public class SortableOrderTypeListener extends InputListener
{
    private SortableOptions options;
    private String type;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SortableOrderTypeListener(SortableOptions debuffView, String sortName)
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
