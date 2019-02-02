package com.myrran.controller;

import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.generators.*;
import com.myrran.view.ui.widgets.DaD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author Ivan Delgado Huerta */
public class CustomSpellController
{
    private CustomSpellBook spellBook;
    private DaD dadDebuff;
    private DaD dadSubform;

    private static final Logger LOG = LogManager.getFormatterLogger(CustomSpellController.class);

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellController(CustomSpellBook spellBook)
    {
        this.spellBook = spellBook;
        dadDebuff = new DaD();
        dadSubform = new DaD();
        //dadDebuff.setDragActorPosition(16, -16);
    }

    // DRAG AND DROP:
    //--------------------------------------------------------------------------------------------------------

    public DaD getDadDebuff()   { return dadDebuff; }
    public DaD getDadSubform()  { return dadSubform; }

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

    public void addCustomSpellSubform(CustomSubformSlot slot, String subformTemplateID)
    {
        try
        {
            spellBook.addCustomSpellSubform(slot, subformTemplateID);
            CustomSpellForm form = spellBook.getSpellFormWithTheStats(slot.getContent());
            notifyForm(form);
        }
        catch (InvalidIDException e)
        {   LOG.warn("Cannot add subform: %S into %S", subformTemplateID, slot.getName());}
    }

    public void removeCustomSpellSubform(CustomSubformSlot slot)
    {
        try
        {
            CustomSpellForm form = spellBook.getSpellFormWithTheStats(slot.getContent());
            spellBook.removeCustomSpellSubform(slot);
            notifyForm(form);
        }
        catch (InvalidIDException e)
        {   LOG.warn("Cannot remove subform from slot", slot.getName());}
    }

    public void addCustomSpellDebuff(CustomDebuffSlot slot, String debuffTemplateID)
    {
        try
        {
            spellBook.addCustomSpellDebuff(slot, debuffTemplateID);
            CustomSpellForm form = spellBook.getSpellFormWithTheStats(slot.getContent());
            notifyForm(form);
        }
        catch (InvalidIDException e)
        {   LOG.warn("Cannot add debuf: %S into %S", debuffTemplateID, slot.getName());}
    }

    public void removeCustomSpellDebuff(CustomDebuffSlot slot)
    {
        try
        {
            CustomSpellForm form = spellBook.getSpellFormWithTheStats(slot.getContent());
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