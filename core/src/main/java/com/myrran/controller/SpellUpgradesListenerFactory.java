package com.myrran.controller;

import com.myrran.spell.generators.custom.CustomSpellForm;
import static com.myrran.spell.generators.custom.StatsDTO.StatsType;

import com.myrran.spell.generators.custom.StatsDTO;
import com.myrran.spell.generators.custom.debuffslot.CustomDebuffSlot;
import com.myrran.utils.InvalidIDException;


/** @author Ivan Delgado Huerta */
public class SpellUpgradesListenerFactory
{
    private CustomSpellController controller;
    private StatsDTO dto;

    public SpellUpgradesListenerFactory(CustomSpellController controller)
    {   this.controller = controller; }

    public SpellUpgradesListenerFactory set(String uuid) throws InvalidIDException
    {
        this.dto = controller.getStatsDTO(uuid);
        return this;
    }

    public SpellUpgradesListener newListener(String statID)
    {   return new SpellUpgradesListener(controller, dto, statID); }
}
