package com.myrran.model.spell.generators;

import com.myrran.model.spell.parameters.SpellStatParams;
import com.myrran.model.spell.templates.TemplateSpellStat;
import com.myrran.misc.InvalidIDException;

import java.util.Collection;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomSpellStatsDeco extends CustomSpellStatsI
{
    CustomSpellStatsI getSpellStats();

    // DECORATOR
    //--------------------------------------------------------------------------------------------------------

    default void setSpellStatsTemplates(Collection<TemplateSpellStat> templates)
    {   getSpellStats().setSpellStatsTemplates(templates); }

    default Map<String, SpellStatParams> getSpellStatParams()
    {   return getSpellStats().getSpellStatParams(); }

    default Collection<CustomSpellStat> getCustomSpellStats()
    {   return getSpellStats().getCustomSpellStats(); }

    default CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {   return getSpellStats().getCustomSpellStat(statID); }

    default Integer getStatsTotalCost()
    {   return getSpellStats().getStatsTotalCost(); }
}
