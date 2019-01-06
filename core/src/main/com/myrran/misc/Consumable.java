package main.com.myrran.misc;

public interface Consumable
{
    // SET:
    //------------------------------------------------------------------------------------------------------------------

    void setMaxDuration(float maxDuration);
    void setActualDuration(float actualDuration);

    // GET:
    //------------------------------------------------------------------------------------------------------------------

    float getActualDuration();
    float getMaxDuration();

    default boolean updateDuration(float delta)
    {
        setActualDuration(getActualDuration() + delta);
        return getActualDuration() > getMaxDuration();
    }
}
