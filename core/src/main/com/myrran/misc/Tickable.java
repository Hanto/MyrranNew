package main.com.myrran.misc;

public interface Tickable extends Consumable
{
    float TICKDURATION = 0.5f;

    // SET:
    //------------------------------------------------------------------------------------------------------------------

    void setTicksAplicados(int ticksAplicados);

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    int getTicksAplicados();
    void applyTick();

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

    @Override default boolean updateDuration(float delta)
    {
        boolean isOver = Consumable.super.updateDuration(delta);

        for (int i=getTicksAplicados(); i< getTickActual(); i++)
        {
            setTicksAplicados(i);
            applyTick();
        }

        return isOver;
    }
}
