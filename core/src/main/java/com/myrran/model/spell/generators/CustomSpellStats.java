package com.myrran.model.spell.generators;

import com.myrran.model.spell.parameters.SpellStatParams;
import com.myrran.model.spell.templates.TemplateSpellStat;
import com.myrran.misc.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellStats implements CustomSpellStatsI
{
    private Map<String, CustomSpellStat> stats = new HashMap<>();

    public Collection<CustomSpellStat> getCustomSpellStats() { return stats.values(); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void setSpellStatsTemplates(Collection<TemplateSpellStat> templates)
    {
        stats = templates.stream()
            .map(CustomSpellStat::new)
            .collect(Collectors.toMap(CustomSpellStat::getID, stat -> stat));
    }

    // CUSTOM TO ENTITY PARAMS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Map<String, SpellStatParams> getSpellStatParams()
    {
        return stats.values().stream()
            .map(CustomSpellStat::getSpellStatParams)
            .collect(Collectors.toMap(SpellStatParams::getID, spellStatParams -> spellStatParams));
    }

    // STATS:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {
        CustomSpellStat stat = stats.get(statID);
        if (stat != null) return stat;
        else throw new InvalidIDException("CustomStatView with the following ID doesn't exist: %s", statID);
    }

    @Override
    public void setNumUpgrades(String statID, int numUpgrades) throws InvalidIDException
    {
        CustomSpellStat stat = getCustomSpellStat(statID);
        stat.setNumUpgrades(numUpgrades);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public Integer getStatsTotalCost()
    {
        return stats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum();
    }
}
