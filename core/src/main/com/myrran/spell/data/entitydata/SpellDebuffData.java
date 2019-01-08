package main.com.myrran.spell.data.entitydata;

import main.com.myrran.spell.entity.debuff.SpellDebuffFactory;

import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SpellDebuffData
{
    private String slotType;
    private SpellDebuffFactory factory;
    private Map<String, SpellStatData> stats = new HashMap<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getSlotType()                                     { return slotType; }
    public SpellDebuffFactory getFactory()                          { return factory; }
    public SpellStatData getStat(String stat)                       { return stats.get(stat); }

    public SpellDebuffData setSlotType(String slotType)             { this.slotType = slotType; return this; }
    public SpellDebuffData setFactory(SpellDebuffFactory factory)   { this.factory = factory; return this; }
    public SpellDebuffData addStat(SpellStatData stat)              { stats.put(stat.getID(), stat); return this; }
}
