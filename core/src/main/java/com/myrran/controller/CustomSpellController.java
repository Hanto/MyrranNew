package com.myrran.controller;

import com.myrran.spell.generators.custom.CustomSpellBook;
import com.myrran.spell.generators.custom.CustomSpellForm;
import com.myrran.spell.generators.custom.stats.CustomSpellStat;
import com.myrran.spell.generators.custom.stats.CustomSpellStatsI;
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

    public void modifyStatBy(CustomSpellStatsI stats, String statID, int upgradesBy) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getSpellFormWithTheStats(stats.getID());

        CustomSpellStat stat = stats.getCustomSpellStat(statID);

        int upgrades = stat.getNumUpgrades() + upgradesBy;

        if (upgrades >= 0 && upgrades <= stat.getMaxUpgrades())
            stats.setNumUpgrades(statID, upgrades);

        form.notify("", null, null);
    }
}
