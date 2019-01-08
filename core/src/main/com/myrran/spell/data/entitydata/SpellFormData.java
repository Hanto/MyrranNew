package main.com.myrran.spell.data.entitydata;

import main.com.myrran.spell.entity.form.SpellFormFactory;

import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SpellFormData
{
    private SpellFormFactory factory;
    private Map<String, SpellStatData> stats = new HashMap<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public SpellFormFactory getFactory()                        { return factory; }
    public SpellStatData getStat(String stat)                   { return stats.get(stat); }

    public SpellFormData setFactory(SpellFormFactory factory)   { this.factory = factory; return this; }
    public SpellFormData addStat(SpellStatData stat)            { stats.put(stat.getID(), stat); return this; }
}
