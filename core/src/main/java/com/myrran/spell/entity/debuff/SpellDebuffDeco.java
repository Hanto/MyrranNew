package com.myrran.spell.entity.debuff;

import com.myrran.misc.Consumable.ConsumableDeco;
import com.myrran.misc.Consumable.ConsumableI;
import com.myrran.misc.Debuffable;

/** @author Ivan Delgado Huerta */
public interface SpellDebuffDeco extends ConsumableDeco
{
    SpellDebuffI getSpellDebuff();

    void applyTick(Debuffable debuffable);

    // DECORATOR:
    //--------------------------------------------------------------------------------------------------------

    default ConsumableI getConsumable()
    {   return getSpellDebuff().getConsumable(); }

    default int getTicksAplicados()
    {   return getSpellDebuff().getTicksAplicados(); }

    default int getActualStacks()
    {   return getSpellDebuff().getActualStacks(); }

    default int getMaxStacks()
    {   return getSpellDebuff().getMaxStacks(); }

    default void setTicksAplicados(int ticksAplicados)
    {   getSpellDebuff().setTicksAplicados(ticksAplicados); }

    default void setActualStacks(int actualStacks)
    {   getSpellDebuff().setActualStacks(actualStacks); }

    default void setMaxStacks(int maxStacks)
    {   getSpellDebuff().setMaxStacks(maxStacks); }

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
