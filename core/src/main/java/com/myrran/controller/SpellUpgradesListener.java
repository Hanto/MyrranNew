package com.myrran.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.myrran.utils.InvalidIDException;
import com.myrran.view.ui.Atlas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class SpellUpgradesListener extends InputListener
{
    public enum StatsType { FormStats, DebuffStats, SubformStats }

    private CustomSpellController controller;
    private StatsType type;
    private String formID;
    private String slotID;
    private String statID;

    private static Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellUpgradesListener(CustomSpellController controller, StatsType type, String formID, String slotID, String statID)
    {
        this.controller = controller;
        this.formID = formID;
        this.slotID = slotID;
        this.statID = statID;
        this.type = type;
    }

    public SpellUpgradesListener(CustomSpellController controller, String formID, String statID)
    {
        this.controller = controller;
        this.formID = formID;
        this.slotID = slotID;
        this.statID = statID;
        this.type = StatsType.FormStats;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        float width = event.getListenerActor().getWidth();

        if (button == Input.Buttons.LEFT)
        {
            if      (x < (width / 2))   modifyStatBy(-1);
            else if (x > (width / 2))   modifyStatBy(+1);
        }
        else if (button == Input.Buttons.RIGHT)
            modifyStatTo((int)(x/(width/25)));

        return true;
    }

    private void modifyStatBy(int modifyBy)
    {
        try
        {
            switch (type)
            {
                case FormStats: controller.formUpgradesModifyBy(formID, statID, modifyBy); break;
                case DebuffStats: controller.debuffUpgradesModifyBy(formID, slotID, statID, modifyBy); break;
                case SubformStats: break;
            }
        }
        catch (InvalidIDException e)
        {   LOG.error("Cannot modify stat: ", e); }
    }

    private void modifyStatTo(int modifyTo)
    {
        try
        {
            switch (type)
            {
                case FormStats: controller.formUpgradesModifyTo(formID, statID, modifyTo); break;
                case DebuffStats: controller.debuffUpgradesModifyTo(formID, slotID, statID, modifyTo); break;
                case SubformStats: break;
            }
        }
        catch (InvalidIDException e)
        {   LOG.error("Cannot modify stat: ", e); }
    }

}
