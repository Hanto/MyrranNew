package main.com.myrran.spell.spelleffect.generators;

import main.com.myrran.spell.SpellStat;
import main.com.myrran.spell.spelleffect.generates.EffectEntityFactory;

import java.util.List;

public interface SpellEffectI
{
    EffectEntityFactory getFactory();
    List<SpellStat> getSpellStats();
}
