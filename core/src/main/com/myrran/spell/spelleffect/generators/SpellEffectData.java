package main.com.myrran.spell.spelleffect.generators;

import main.com.myrran.spell.spelleffect.generates.EffectEntityFactory;

import java.util.HashMap;
import java.util.Map;

public class SpellEffectData
{
    private String slotType;
    private EffectEntityFactory factory;
    private Map<String, Float> stats = new HashMap<>();

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public String getSlotType()                             { return slotType; }
    public EffectEntityFactory getFactory()                 { return factory; }
    public Float getStat(String stat)                       { return stats.get(stat); }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public void setSlotType(String slotType)                { this.slotType = slotType; }
    public void setFactory(EffectEntityFactory factory)     { this.factory = factory; }
    public void addStat(String key, Float value)            { stats.put(key, value); }
}
