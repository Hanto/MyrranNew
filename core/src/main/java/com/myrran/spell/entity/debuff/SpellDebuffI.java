package com.myrran.spell.entity.debuff;

import com.myrran.misc.consumer.ConsumableI;
import com.myrran.misc.Debuffable;

import java.util.function.Consumer;

/** @author Ivan Delgado Huerta */
public interface SpellDebuffI extends ConsumableI
{
    ConsumableI getConsumable();
    int getTicksAplicados();
    int getActualStacks();
    int getMaxStacks();
    void setTicksAplicados(int ticksAplicados);
    void setActualStacks(int actualStacks);
    void setMaxStacks(int maxStacks);
    int getMaxTicks();
    int getTickActual();
    int getTicksRestantes();
    void resetDuration();
    void applyTicks(Debuffable debuffable, Consumer<Debuffable> consumer);
}
