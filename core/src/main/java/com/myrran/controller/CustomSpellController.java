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

    public void modifySpellFormUpgrades(String formID, String statID, int numUpgrades) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        form.setNumUpgrades(statID, numUpgrades);
    }

    public void modifySpellDebuffUpgrades(String formID, String slotID, String statID, int numUpgrades) throws InvalidIDException
    {
        CustomSpellForm form = spellBook.getCustomSpellForm(formID);
        form.setNumUpgrades(slotID, statID, numUpgrades);
    }

}
