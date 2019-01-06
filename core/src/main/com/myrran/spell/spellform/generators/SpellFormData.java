package main.com.myrran.spell.spellform.generators;

import main.com.myrran.spell.SpellStat;

import java.util.HashMap;
import java.util.Map;

public class SpellFormData
{
    private Map<String, Float> stats = new HashMap<String, Float>();

    public void setSpellForm(SpellForm spellForm)
    {
        for (SpellStat stat: spellForm.getSpellStats())
            stats.put(stat.getID(), stat.getTotal());
    }

    public Float getStat(String stat)
    {   return stats.get(stat); }
}
