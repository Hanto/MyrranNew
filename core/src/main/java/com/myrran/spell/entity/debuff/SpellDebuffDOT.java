package com.myrran.spell.entity.debuff;

import com.myrran.misc.Debuffable;
import com.myrran.misc.Stackable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;

/** @author Ivan Delgado Huerta */
public class SpellDebuffDOT implements SpellDebuff, Stackable
{
    private static final String DURATION = "duration";
    private static final String MAXSTACKS = "maxStacks";
    private static final String DAMAGE = "damage";

    private float actualDuration = 0.0f;
    private float maxDuration = 5.0f;
    private int ticksAplicados = 0;
    private int actualStacks = 0;
    private int maxStacks = 1;

    private SpellDebuffParams spellDebuffParams;

    // SPELLDEBUFF (CONSUMABLE):
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

    @Override public void setSpellDebuffParams(SpellDebuffParams data)
    {
        this.spellDebuffParams = data;
        setMaxDuration(spellDebuffParams.getStat(DURATION).getTotal());
        setMaxStacks(spellDebuffParams.getStat(MAXSTACKS).getTotal().intValue());
    }

    // MAIN:
    //------------------------------------------------------------------------------------------------------------------

    @Override public void applyTick(Debuffable debuffable)
    {
        int ticksAplicados = getTicksAplicados();
        int ticksMax = getMaxTicks();

        Float damage = spellDebuffParams.getStat(DAMAGE).getTotal() * getActualStacks();
    }
}
