package com.myrran.controller;

import static com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.*;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.myrran.model.spell.generators.custom.*;
import com.myrran.misc.InvalidIDException;
import com.myrran.model.spell.templates.TemplateSpellDebuff;

/** @author Ivan Delgado Huerta */
public class CustomSpellController
{
    private CustomSpellBook spellBook;
    private DragAndDrop dad;

    public DragAndDrop getDragAndDrop()         { return dad; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellController(CustomSpellBook spellBook)
    {
        this.spellBook = spellBook;
        dad = new DragAndDrop();
    }

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

    public void addSource(Source source)
    {   dad.addSource(source); }

    public void removeSource(Source source)
    {   dad.removeSource(source); }

    public void addTarget(Target target)
    {   dad.addTarget(target); }

    public void removeTarget(Target target)
    {   dad.removeTarget(target);}

    public void addCustomSpellDebuff(CustomDebuffSlot slot, String debuffTemplateID) throws InvalidIDException
    {   spellBook.addCustomSpellDebuff(slot, debuffTemplateID); }
}