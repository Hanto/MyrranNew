package main.com.myrran.spell.entity.debuff;

import main.com.myrran.misc.Consumable;
import main.com.myrran.misc.Debuffable;
import main.com.myrran.spell.data.entitydata.SpellDebuffData;

/** @author Ivan Delgado Huerta */
public interface SpellDebuff extends Consumable
{
    float TICKDURATION = 0.5f;

    // SETTERS GETTERS:
    //------------------------------------------------------------------------------------------------------------------

    void setSpellDebuffData(SpellDebuffData data);
    void setTicksAplicados(int ticksAplicados);

    int getTicksAplicados();
    void applyTick(Debuffable debuffable);

    // DEFAULT:
    //------------------------------------------------------------------------------------------------------------------

    default int getMaxTicks()
    {   return (int)(getMaxDuration() / TICKDURATION); }

    default int getTickActual()
    {   return (int)(getActualDuration() / TICKDURATION); }

    default int getTicksRestantes()
    {   return getMaxTicks() - getTicksAplicados(); }

    default void resetDuration()
    {
        setTicksAplicados(0);
        setActualDuration(getActualDuration() % TICKDURATION);
    }

    default void applyTicks(Debuffable debuffable)
    {
        for (int i=getTicksAplicados(); i< getTickActual(); i++)
        {
            setTicksAplicados(i);
            applyTick(debuffable);
        }
    }
}