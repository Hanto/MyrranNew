package com.myrran.controller;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.myrran.model.spell.generators.custom.*;
import com.myrran.misc.InvalidIDException;

/** @author Ivan Delgado Huerta */
public class CustomSpellController
{
    private CustomSpellBook spellBook;
    private DragAndDrop dadDebuff;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellController(CustomSpellBook spellBook)
    {
        this.spellBook = spellBook;
        dadDebuff = new DragAndDrop();
    }

    // DRAG AND DROP:
    //--------------------------------------------------------------------------------------------------------

    public DragAndDrop getDadDebuff()  { return dadDebuff; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void modifyStatBy(CustomSpellStatsI stats, String statID, int upgradesBy) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getSpellFormWithTheStats(stats.getID());
        CustomSpellStat stat = stats.getCustomSpellStat(statID);

        int upgrades = stat.getNumUpgrades() + upgradesBy;

        if (upgrades < 0)
        {   stats.setNumUpgrades(statID, 0); }
        else if (upgrades > stat.getMaxUpgrades())
        {   stats.setNumUpgrades(statID, stat.getMaxUpgrades()); }
        else
        {   stats.setNumUpgrades(statID, upgrades); }

        form.notify("", null, null);
    }

    public void addCustomSpellDebuff(CustomDebuffSlot slot, String debuffTemplateID) throws InvalidIDException
    {   spellBook.addCustomSpellDebuff(slot, debuffTemplateID); }
}