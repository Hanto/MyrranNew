package com.myrran.model.spell.generators.custom;

import com.myrran.model.spell.parameters.SpellStatParams;
import com.myrran.model.spell.templates.SpellStatTemplate;
import com.myrran.misc.InvalidIDException;

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

    default Collection<CustomSpellStat>values()
    {   return getSpellStats().values(); }

    default CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {   return getSpellStats().getCustomSpellStat(statID); }

    default int getStatsTotalCost()
    {   return getSpellStats().getStatsTotalCost(); }
}
