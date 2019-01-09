package main.com.myrran.spell.data.entityparams;

import main.com.myrran.spell.entity.debuff.SpellDebuffFactory;

import java.util.HashMap;
import java.util.Map;

/** @author Ivan Delgado Huerta */
public class SpellDebuffParams
{
    private String slotType;
    private SpellDebuffFactory factory;
    private Map<String, SpellStatParams> stats = new HashMap<>();

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    public String getSlotType()                                     { return slotType; }
    public SpellDebuffFactory getFactory()                          { return factory; }
    public SpellStatParams getStat(String stat)                     { return stats.get(stat); }

    public SpellDebuffParams setSlotType(String slotType)           { this.slotType = slotType; return this; }
    public SpellDebuffParams setFactory(SpellDebuffFactory factory) { this.factory = factory; return this; }
    public SpellDebuffParams addStat(SpellStatParams stat)          { stats.put(stat.getID(), stat); return this; }
}
