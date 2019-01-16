package com.myrran.spell.generators.custom.stats;

import com.myrran.spell.data.entityparams.SpellStatParams;
import com.myrran.spell.data.templatedata.SpellStatTemplate;
import com.myrran.utils.InvalidIDException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class CustomSpellStatsImp
{
    private Map<String, CustomSpellStat> stats = new HashMap<>();

    // TEMPLATE TO CUSTOM:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellStatTemplate(SpellStatTemplate template)
    {
        stats.computeIfAbsent(template.getID(), v -> new CustomSpellStat());
        CustomSpellStat customSpellStat = stats.get(template.getID());
        customSpellStat.setSpellStatTemplate(template);
    }

    // CUSTOM TO ENTITY PARAMS:
    //--------------------------------------------------------------------------------------------------------

    public Map<String, SpellStatParams> getSpellStatParams()
    {
        return stats.values().stream()
            .map(CustomSpellStat::getSpellStatParams)
            .collect(Collectors.toMap(SpellStatParams::getID, spellStatParams -> spellStatParams));
    }

    // STATS:
    //--------------------------------------------------------------------------------------------------------

    public CustomSpellStat getCustomSpellStat(String statID) throws InvalidIDException
    {
        CustomSpellStat stat = stats.get(statID);
        if (stat != null) return stat;
        else throw new InvalidIDException("SpellStat with the following ID doesn't exist: %s", statID);
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getStatsTotalCost()
    {
        return stats.values().stream()
            .mapToInt(CustomSpellStat::getTotalCost)
            .sum();
    }
}
