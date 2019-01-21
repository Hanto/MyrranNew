package com.myrran.controller;

import com.myrran.spell.generators.custom.CustomSpellBook;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.utils.InvalidIDException;

/** @author Ivan Delgado Huerta */
public class CustomSpellController
{
    private CustomSpellBook spellBook;

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellController(CustomSpellBook spellBook)
    {   this.spellBook = spellBook; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void formUpgradesModifyBy(String formID, String statID, int upgradesBy) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        int upgrades = form.getCustomSpellStat(statID).getNumUpgrades() + upgradesBy;
        form.setNumUpgrades(statID, upgrades);
    }

    public void formUpgradesModifyTo(String formID, String statID, int upgradesTo) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        form.setNumUpgrades(statID, upgradesTo);
    }

    public void debuffUpgradesModifyBy(String formID, String slotID, String statID, int upgradesBy) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        int upgrades = form.getCustomSpellDebuff(slotID).getCustomSpellStat(statID).getNumUpgrades() + upgradesBy;
        form.setNumUpgrades(slotID, statID, upgrades);
    }

    public void debuffUpgradesModifyTo(String formID, String slotID, String statID, int upgradesTo) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        form.setNumUpgrades(slotID, statID, upgradesTo);
    }
}
