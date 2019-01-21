package com.myrran.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import com.myrran.spell.generators.custom.StatsDTO;
import com.myrran.utils.InvalidIDException;
import com.myrran.view.ui.Atlas;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class SpellUpgradesListener extends InputListener
{
    private CustomSpellController controller;
    private StatsDTO dto;
    private String statID;


    private static Logger LOG = LogManager.getFormatterLogger(Atlas.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public SpellUpgradesListener(CustomSpellController controller, StatsDTO dto, String statID)
    {
        this.controller = controller;
        this.dto = dto;
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
        {
            switch (dto.type)
            {
                case FormStats: controller.formUpgradesModifyBy(dto.form.getID(), statID, modifyBy); break;
                case FormDebuffStats: controller.debuffUpgradesModifyBy(dto.form.getID(), dto.debuffSlot.getID(), statID, modifyBy); break;
                case SubformStats: break;
                case SubformDebuffStats: break;
            }
        }
        catch (InvalidIDException e)
        {   LOG.error("Cannot modify stat: ", e); }
    }
}