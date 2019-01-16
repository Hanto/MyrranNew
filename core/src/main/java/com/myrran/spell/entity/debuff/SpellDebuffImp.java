package com.myrran.spell.entity.debuff;

import com.myrran.misc.Consumable;
import com.myrran.misc.ConsumableImp;
import com.myrran.misc.Debuffable;

import java.util.function.Consumer;

/** @author Ivan Delgado Huerta */
public class SpellDebuffImp implements Consumable
{
    private ConsumableImp consumable = new ConsumableImp();
    private int appliedTicks = 0;
    private int actualStacks = 0;
    private int maxStacks = 1;

    private static final float TICKDURATION = 0.5f;

    // SETTERS GETTERS:
    //--------------------------------------------------------------------------------------------------------

    @Override public ConsumableImp getConsumable()      { return consumable; }
    public int getAppliedTicks()                        { return appliedTicks; }
    public int getActualStacks()                        { return actualStacks; }
    public int getMaxStacks()                           { return maxStacks; }
    public void setAppliedTicks(int appliedTicks)       { this.appliedTicks = appliedTicks; }
    public void setActualStacks(int actualStacks)       { this.actualStacks = actualStacks;}
    public void setMaxStacks(int maxStacks)             { this.maxStacks = maxStacks; }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public int getMaxTicks()
    {   return (int)(getMaxDuration() / TICKDURATION); }

    public int getTickActual()
    {   return (int)(getActualDuration() / TICKDURATION); }

    public int getTicksRestantes()
    {   return getMaxTicks() - getAppliedTicks(); }

    public void resetDuration()
    {
        setAppliedTicks(0);
        setActualDuration(getActualDuration() % TICKDURATION);
    }

    public void applyTicks(Debuffable debuffable, Consumer<Debuffable> consumer)
    {
        for (int i = getAppliedTicks(); i< getTickActual(); i++)
        {
            setAppliedTicks(i);
            consumer.accept(debuffable);
        }
    }
}
