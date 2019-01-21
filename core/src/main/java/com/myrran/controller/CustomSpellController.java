package com.myrran.controller;

import com.myrran.spell.generators.custom.CustomSpellBook;
import com.myrran.spell.generators.custom.CustomSpellDebuff;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
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
        CustomSpellStat stat = form.getCustomSpellStat(statID);

        int upgrades = stat.getNumUpgrades() + upgradesBy;

        if (upgrades >= 0 && upgrades <= stat.getMaxUpgrades())
            form.setNumUpgrades(statID, upgrades);
    }

    public void debuffUpgradesModifyBy(String formID, String slotID, String statID, int upgradesBy) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        CustomSpellDebuff debuff = form.getCustomSpellDebuff(slotID);
        CustomSpellStat stat = debuff.getCustomSpellStat(statID);

        int upgrades = stat.getNumUpgrades() + upgradesBy;

        if (upgrades >= 0 && upgrades <= stat.getMaxUpgrades())
            form.setNumUpgrades(slotID, statID, upgrades);
    }
}
