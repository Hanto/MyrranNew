package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.generators.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class CustomSpellController
{
    private CustomSpellBook spellBook;
    private DragAndDrop dadDebuff;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellController.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellController(CustomSpellBook spellBook)
    {
        this.spellBook = spellBook;
        dadDebuff = new DragAndDrop();
        //dadDebuff.setDragActorPosition(16, -16);
    }

    // DRAG AND DROP:
    //--------------------------------------------------------------------------------------------------------

    public DragAndDrop getDadDebuff()  { return dadDebuff; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void modifyStatBy(CustomSpellStatsI stats, String statID, int upgradesBy) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getSpellFormWithTheStats(stats);
        CustomSpellStat stat = stats.getCustomSpellStat(statID);

        int upgrades = stat.getNumUpgrades() + upgradesBy;

        if (upgrades < 0)
        {   stats.setNumUpgrades(statID, 0); }
        else if (upgrades > stat.getMaxUpgrades())
        {   stats.setNumUpgrades(statID, stat.getMaxUpgrades()); }
        else
        {   stats.setNumUpgrades(statID, upgrades); }

        notifyForm(form);
    }

    public void addCustomSpellDebuff(CustomDebuffSlot slot, String debuffTemplateID)
    {
        try
        {
            spellBook.addCustomSpellDebuff(slot, debuffTemplateID);
            CustomSpellForm form = spellBook.getSpellFormWithTheStats(slot.getCustomSpellDebuff());
            notifyForm(form);
        }
        catch (InvalidIDException e)
        {   LOG.warn("Cannot add debuf: %S into %S", debuffTemplateID, slot.getName());}
    }

    public void removeCustomSpellDebuff(CustomDebuffSlot slot)
    {
        try
        {
            CustomSpellForm form = spellBook.getSpellFormWithTheStats(slot.getCustomSpellDebuff());
            spellBook.removeCustomSpellDebuff(slot);
            notifyForm(form);
        }
        catch (InvalidIDException e)
        {   LOG.warn("Cannot remove debuf from slot: %s", slot.getName());}
    }

    // NOTIFY FORM:
    //--------------------------------------------------------------------------------------------------------

    private void notifyForm(CustomSpellForm form)
    {   form.notify("fieldChange", null, null);}
}