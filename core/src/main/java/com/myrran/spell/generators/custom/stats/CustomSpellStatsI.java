package com.myrran.spell.generators.custom.stats;

import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.utils.InvalidIDException;

import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomSpellStatsI
{
    void setSpellStatTemplate(SpellStatTemplate template);
    Map<String, SpellStatParams> getSpellStatParams();
    CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException;
    int getStatsTotalCost();
}
