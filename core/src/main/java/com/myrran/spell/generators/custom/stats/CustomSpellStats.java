package com.myrran.spell.generators.custom.stats;

import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.utils.InvalidIDException;

import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomSpellStats
{
    CustomSpellStatsImp getSpellStats();

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    default void setSpellStatTemplate(SpellStatTemplate template)
    {   getSpellStats().setSpellStatTemplate(template); }

    // CUSTOM TO ENTITY PARAMS:
    //--------------------------------------------------------------------------------------------------------

    default Map<String, SpellStatParams> getSpellStatParams()
    {   return getSpellStats().getSpellStatParams(); }

    // STATS:
    //--------------------------------------------------------------------------------------------------------

    default CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {   return getSpellStats().getCustomSpellStat(statID); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    default int getStatsTotalCost()
    {   return getSpellStats().getStatsTotalCost(); }
}
