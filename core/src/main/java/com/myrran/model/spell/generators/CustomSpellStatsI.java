package com.myrran.model.spell.generators;

import com.myrran.model.spell.parameters.SpellStatParams;
import com.myrran.model.spell.templates.TemplateSpellStat;
import com.myrran.misc.InvalidIDException;

import java.util.Collection;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface CustomSpellStatsI
{
    void setSpellStatsTemplates(Collection<TemplateSpellStat> templates);
    void setNumUpgrades(String statID, int numUpgrades) throws InvalidIDException;

    default String getID()  { return null; }
    Collection<CustomSpellStat> getCustomSpellStats();
    Map<String, SpellStatParams> getSpellStatParams();
    CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException;
    int getStatsTotalCost();
}
