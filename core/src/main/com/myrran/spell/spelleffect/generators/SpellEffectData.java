package main.com.myrran.spell.spelleffect.generators;

import main.com.myrran.spell.SpellSlot;
import main.com.myrran.spell.SpellStat;
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

    public void setSpellEffect(SpellEffectI spellEffect)
    {
        this.factory = spellEffect.getFactory();

        for (SpellStat stat: spellEffect.getSpellStats())
            stats.put(stat.getID(), stat.getTotal());
    }

    public void setSpellSlot(SpellSlot spellSlot)
    {   this.slotType = spellSlot.getSlotType(); }
}
