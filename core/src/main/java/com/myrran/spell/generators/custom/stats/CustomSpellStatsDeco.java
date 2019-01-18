package com.myrran.spell.generators.custom.stats;

import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.utils.InvalidIDException;

import java.util.Collection;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomSpellStatsDeco extends CustomSpellStatsI
{
    CustomSpellStatsI getSpellStats();

    // DECORATOR
    //--------------------------------------------------------------------------------------------------------

    default void setSpellStatsTemplates(Collection<SpellStatTemplate> templates)
    {   getSpellStats().setSpellStatsTemplates(templates); }

    default Map<String, SpellStatParams> getSpellStatParams()
    {   return getSpellStats().getSpellStatParams(); }

    default CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {   return getSpellStats().getCustomSpellStat(statID); }

    default int getStatsTotalCost()
    {   return getSpellStats().getStatsTotalCost(); }
}
