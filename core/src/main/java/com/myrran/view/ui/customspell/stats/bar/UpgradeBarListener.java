package com.myrran.view.ui.customspell.stats.bar;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.controller.CustomSpellController;
import com.myrran.model.spell.generators.CustomSpellStatsI;
import com.myrran.misc.InvalidIDException;
import com.myrran.view.ui.Atlas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class UpgradeBarListener extends InputListener
{
    private CustomSpellController controller;
    private CustomSpellStatsI stats;
    private String statID;

    private static Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public UpgradeBarListener(CustomSpellController controller, CustomSpellStatsI stats, String statID)
    {
        this.controller = controller;
        this.stats = stats;
        this.statID = statID;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        float width = event.getListenerActor().getWidth();
        int modifyBy = getAmount(button);

        if (x < (width / 2)) modifyStatBy(-modifyBy);
        else if (x > (width / 2)) modifyStatBy(+modifyBy);

        return true;
    }

    private int getAmount(int button)
    {
        if      (button == Input.Buttons.LEFT) return 1;
        else if (button == Input.Buttons.RIGHT) return 5;
        else return 0;
    }

    private void modifyStatBy(int modifyBy)
    {
        try
        {   controller.modifyStatBy(stats, statID, modifyBy);}
        catch (InvalidIDException e)
        {   LOG.error("Cannot modify stat: ", e); }
    }
}