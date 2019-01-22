package com.myrran.model.spell.entities.debuff;

import com.myrran.model.components.consumer.ConsumableI;
import com.myrran.model.components.Debuffable;

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
