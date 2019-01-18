package com.myrran.spell.generators.custom.stats;

import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.utils.InvalidIDException;

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

    public Collection<CustomSpellStat> values() { return stats.values(); }

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void setSpellStatsTemplates(Collection<SpellStatTemplate> templates)
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
        else throw new InvalidIDException("SpellStat with the following ID doesn't exist: %s", statID);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public int getStatsTotalCost()
    {
        return stats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum();
    }
}
