package com.myrran.controller;

import com.myrran.model.spell.generators.custom.CustomSpellBook;
import com.myrran.model.spell.generators.custom.CustomSpellForm;
import com.myrran.model.spell.generators.custom.CustomSpellStat;
import com.myrran.model.spell.generators.custom.CustomSpellStatsI;
import com.myrran.misc.InvalidIDException;

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
