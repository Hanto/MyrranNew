package com.myrran.spell.entity.debuff;

import com.myrran.misc.Consumable;
import com.myrran.misc.ConsumableImp;
import com.myrran.misc.Debuffable;

/** @author Ivan Delgado Huerta */
public interface SpellDebuff extends Consumable
{
    SpellDebuffImp getSpellDebuff();

    void applyTick(Debuffable debuffable);

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    default ConsumableImp getConsumable()
    {   return getSpellDebuff().getConsumable(); }

    default int getTicksAplicados()
    {   return getSpellDebuff().getAppliedTicks(); }

    default int getActualStacks()
    {   return getSpellDebuff().getActualStacks(); }

    default int getMaxStacks()
    {   return getSpellDebuff().getMaxStacks(); }

    default void setTicksAplicados(int ticksAplicados)
    {   getSpellDebuff().setAppliedTicks(ticksAplicados); }

    default void setActualStacks(int actualStacks)
    {   getSpellDebuff().setActualStacks(actualStacks); }

    default void setMaxStacks(int maxStacks)
    {   getSpellDebuff().setMaxStacks(maxStacks); }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    default int getMaxTicks()
    {   return getSpellDebuff().getMaxTicks(); }

    default int getTickActual()
    {   return getSpellDebuff().getTickActual(); }

    default int getTicksRestantes()
    {   return getSpellDebuff().getTicksRestantes(); }

    default void resetDuration()
    {   getSpellDebuff().resetDuration(); }

    default void applyTicks(Debuffable debuffable)
    {   getSpellDebuff().applyTicks(debuffable, this::applyTick); }
}
