package com.myrran.spell.generators.custom;

import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;

/** @author Ivan Delgado Huerta */
public class StatsDTO
{
    public enum StatsType { FormStats, FormDebuffStats, SubformStats, SubformDebuffStats }

    public CustomSpellForm form;
    public StatsType type;
    public CustomDebuffSlot debuffSlot;

    public StatsDTO(CustomSpellForm form)
    {
        this.form = form;
        this.type = StatsType.FormStats;
    }

    public StatsDTO(CustomSpellForm form, CustomDebuffSlot debuffSlot)
    {
        this.form = form;
        this.debuffSlot = debuffSlot;
        this.type = StatsType.FormDebuffStats;
    }
}
