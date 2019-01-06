package main.com.myrran.spell.spelleffect.generators;

import main.com.myrran.spell.SpellSlotKey;
import main.com.myrran.spell.SpellStat;

import java.util.Arrays;
import java.util.List;

public class SpellEffect implements SpellEffectI
{
    private String id;
    private String name;
    private SpellEffectType type;
    private List<SpellStat> spellStats;
    private List<SpellSlotKey> opens;

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    public String getId()                                               { return id; }
    public String getName()                                             { return name; }
    @Override public SpellEffectType getType()                          { return type; }
    @Override public List<SpellStat>getSpellStats()                     { return spellStats; }
    public List<SpellSlotKey> getOpens()                                { return opens; }

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    public SpellEffect setId(String id)                                 { this.id = id; return this; }
    public SpellEffect setName(String name)                             { this.name = name; return this; }
    public SpellEffect setType(SpellEffectType type)                    { this.type = type; return this; }
    public SpellEffect setSpellStats(List<SpellStat>spellStats)         { this.spellStats = spellStats; return this; }
    public SpellEffect setOpens(SpellSlotKey... spellSlotKeys)          { this.opens = Arrays.asList(spellSlotKeys); return this; }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellEffectTemplate()
    {
        //TODO
    }
}

