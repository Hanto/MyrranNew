package com.myrran.model.spell.entities.debuff;

import com.myrran.model.components.Debuffable;
import com.myrran.model.spell.parameters.SpellDebuffParams;

/** @author Ivan Delgado Huerta */
public class SpellDebuffDOT implements SpellDebuffDeco
{
    private static final String DURATION = "duration";
    private static final String MAXSTACKING = "maxStacks";
    private static final String DAMAGE = "damage";

    private SpellDebuffParams spellDebuffParams;
    private SpellDebuffI spellDebuff = new SpellDebuff();

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public SpellDebuffI getSpellDebuff()    { return spellDebuff; }

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
