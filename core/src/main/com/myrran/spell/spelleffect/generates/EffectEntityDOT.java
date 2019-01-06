package main.com.myrran.spell.spelleffect.generates;

import main.com.myrran.misc.Stackable;
import main.com.myrran.misc.Tickable;
import main.com.myrran.spell.spelleffect.generators.SpellEffectData;

public class EffectEntityDOT implements EffectEntity, Tickable, Stackable
{
    private static final String DURATION = "duration";
    private static final String MAXSTACKS = "maxStacks";
    private static final String DAMAGE = "damage";

    private float actualDuration = 0.0f;
    private float maxDuration = 5.0f;
    private int ticksAplicados = 0;
    private int actualStacks = 0;
    private int maxStacks = 1;

    private SpellEffectData spellEffectData;

    // TICKEABLE CONSUMABLE:
    //------------------------------------------------------------------------------------------------------------------

    @Override public float getActualDuration()                      { return actualDuration; }
    @Override public float getMaxDuration()                         { return maxDuration; }
    @Override public void setMaxDuration(float maxDuration)         { this.maxDuration = maxDuration; }
    @Override public void setActualDuration(float actualDuration)   { this.actualDuration = actualDuration; }

    @Override public int getTicksAplicados()                        { return ticksAplicados; }
    @Override public void setTicksAplicados(int ticksAplicados)     { this.ticksAplicados = ticksAplicados; }


    // STACKABLE:
    //------------------------------------------------------------------------------------------------------------------

    @Override public int getActualStacks()                          { return actualStacks; }
    @Override public int getMaxStacks()                             { return maxStacks; }
    @Override public void setActualStacks(int actualStacks)         { this.actualDuration = actualStacks;}
    @Override public void setMaxStacks(int maxStacks)               { this.maxStacks = maxStacks; }

    // DATA:
    //------------------------------------------------------------------------------------------------------------------

    public void setSpellEffectData(SpellEffectData data)
    {   this.spellEffectData = data; }

    // INIT:
    //------------------------------------------------------------------------------------------------------------------

    public void init()
    {
        setMaxDuration(spellEffectData.getStat(DURATION));
        setMaxStacks(spellEffectData.getStat(MAXSTACKS).intValue());
    }

    @Override public void applyTick()
    {
        Float damage = spellEffectData.getStat(DAMAGE);
    }
}
