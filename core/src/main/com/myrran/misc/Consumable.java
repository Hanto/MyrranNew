package main.com.myrran.misc;

public interface Consumable
{
    float getActualDuration();
    float getMaxDuration();
    void setMaxDuration(float maxDuration);
    void setActualDuration(float actualDuration);

    default boolean updateDuration(float delta)
    {
        setActualDuration(getActualDuration() + delta);
        return getActualDuration() > getMaxDuration();
    }
}
