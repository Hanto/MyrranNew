package com.myrran.model.spell.entities.debuff;

import com.myrran.model.components.Debuffable;
import com.myrran.model.components.consumer.Consumable;
import com.myrran.model.components.consumer.ConsumableDeco;
import com.myrran.model.components.consumer.ConsumableI;

import java.util.function.Consumer;

/** @author Ivan Delgado Huerta */
public class SpellDebuff implements ConsumableDeco, SpellDebuffI
{
    private ConsumableI consumable = new Consumable();
    private int appliedTicks = 0;
    private int actualStacks = 0;
    private int maxStacks = 1;

    private static final float TICKDURATION = 0.5f;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public ConsumableI getConsumable()                { return consumable; }
    @Override public int getTicksAplicados()                    { return appliedTicks; }
    @Override public int getActualStacks()                      { return actualStacks; }
    @Override public int getMaxStacks()                         { return maxStacks; }
    @Override public void setTicksAplicados(int appliedTicks)   { this.appliedTicks = appliedTicks; }
    @Override public void setActualStacks(int actualStacks)     { this.actualStacks = actualStacks;}
    @Override public void setMaxStacks(int maxStacks)           { this.maxStacks = maxStacks; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Override public int getMaxTicks()
    {   return (int)(getMaxDuration() / TICKDURATION); }

    @Override public int getTickActual()
    {   return (int)(getActualDuration() / TICKDURATION); }

    @Override public int getTicksRestantes()
    {   return getMaxTicks() - getTicksAplicados(); }

    @Override public void resetDuration()
    {
        setTicksAplicados(0);
        setActualDuration(getActualDuration() % TICKDURATION);
    }

    @Override public void applyTicks(Debuffable debuffable, Consumer<Debuffable> consumer)
    {
        for (int i = getTicksAplicados(); i< getTickActual(); i++)
        {
            setTicksAplicados(i);
            consumer.accept(debuffable);
        }
    }
}
