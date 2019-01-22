package com.myrran.spell.generators.custom.stats;

import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.utils.InvalidIDException;

import java.util.Collection;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomSpellStatsI
{
    void setSpellStatsTemplates(Collection<SpellStatTemplate> templates);
    void setNumUpgrades(String statID, int numUpgrades) throws InvalidIDException;

    default String getID()  { return null; }
    Collection<CustomSpellStat>values();
    Map<String, SpellStatParams> getSpellStatParams();
    CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException;
    int getStatsTotalCost();
}
