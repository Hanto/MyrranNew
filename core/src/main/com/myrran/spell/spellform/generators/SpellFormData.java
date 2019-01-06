package main.com.myrran.spell.spellform.generators;

import main.com.myrran.spell.SpellStat;
import main.com.myrran.spell.spellform.generates.FormEntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpellFormData
{
    private FormEntityFactory factory;
    private Map<String, Float> stats = new HashMap<>();

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public FormEntityFactory getFactory()                   { return factory; }
    public Float getStat(String stat)                       { return stats.get(stat); }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellForm(SpellForm spellForm)
    {
        this.factory = spellForm.getFactory();

        for (SpellStat stat: spellForm.getSpellStats())
            stats.put(stat.getID(), stat.getTotal());
    }
}
