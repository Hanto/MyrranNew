package com.myrran.spell.entity.debuff;

import com.myrran.misc.Debuffable;
import com.myrran.spell.data.entityparams.SpellDebuffParams;

/** @author Ivan Delgado Huerta */
public class SpellDebuffDOT implements SpellDebuff
{
    private static final String DURATION = "duration";
    private static final String MAXSTACKING = "maxStacks";
    private static final String DAMAGE = "damage";

    private SpellDebuffParams spellDebuffParams;
    private SpellDebuffImp spellDebuff = new SpellDebuffImp();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffImp getSpellDebuff()    { return spellDebuff; }

    // DATA:
    //--------------------------------------------------------------------------------------------------------

    public void setSpellDebuffParams(SpellDebuffParams params)
    {
        this.spellDebuffParams = params;
        setMaxDuration(spellDebuffParams.getStat(DURATION).getTotal());
        setMaxStacks(spellDebuffParams.getStat(MAXSTACKING).getTotal().intValue());
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public void applyTick(Debuffable debuffable)
    {
        Float damage = spellDebuffParams.getStat(DAMAGE).getTotal() * getActualStacks();
    }
}
