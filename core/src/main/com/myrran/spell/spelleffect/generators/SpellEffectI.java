package main.com.myrran.spell.spelleffect.generators;

import main.com.myrran.spell.SpellStat;

import java.util.List;

public interface SpellEffectI
{
    SpellEffectType getType();
    List<SpellStat> getSpellStats();
}
