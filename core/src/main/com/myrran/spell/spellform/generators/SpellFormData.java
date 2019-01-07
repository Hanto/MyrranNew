package main.com.myrran.spell.spellform.generators;

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

    public void setFactory(FormEntityFactory factory)       { this.factory = factory; }
    public void addStat(String key, Float value)            { stats.put(key, value); }
}
