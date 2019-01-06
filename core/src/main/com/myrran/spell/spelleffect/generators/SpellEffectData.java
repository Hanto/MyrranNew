package main.com.myrran.spell.spelleffect.generators;

import main.com.myrran.spell.SpellSlot;
import main.com.myrran.spell.SpellStat;

import java.util.HashMap;
import java.util.Map;

public class SpellEffectData
{
    private Map<String, Float> stats = new HashMap<String, Float>();
    private SpellEffectType type;
    private String slotType;

    public void setSpellEffect(SpellEffectI spellEffect)
    {
        this.type = spellEffect.getType();

        for (SpellStat stat: spellEffect.getSpellStats())
            stats.put(stat.getID(), stat.getTotal());
    }

    public void setSpellSlot(SpellSlot spellSlot)
    {
        this.slotType = spellSlot.getSlotType();
    }

    public Float getStat(String stat)
    {   return stats.get(stat); }

    public String getSlotType()
    {   return slotType; }

    public SpellEffectType getSpellEffectType()
    {   return type; }
}
