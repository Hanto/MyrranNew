package main.com.myrran.spell.data.entitydata;

import main.com.myrran.spell.entity.effect.SpellEffectFactory;

import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SpellEffectData
{
    private String slotType;
    private SpellEffectFactory factory;
    private Map<String, SpellStatData> stats = new HashMap<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getSlotType()                                     { return slotType; }
    public SpellEffectFactory getFactory()                          { return factory; }
    public SpellStatData getStat(String stat)                       { return stats.get(stat); }

    public SpellEffectData setSlotType(String slotType)             { this.slotType = slotType; return this; }
    public SpellEffectData setFactory(SpellEffectFactory factory)   { this.factory = factory; return this; }
    public SpellEffectData addStat(SpellStatData stat)              { stats.put(stat.getID(), stat); return this; }
}
