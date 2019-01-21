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
        int modifyBy = getAmount(button);

        if      (x < (width / 2)) modifyStatBy(-modifyBy);
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
}