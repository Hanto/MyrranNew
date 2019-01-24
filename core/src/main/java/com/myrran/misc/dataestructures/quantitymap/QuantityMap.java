package com.myrran.misc.dataestructures.quantitymap;

import java.util.Map;

public class QuantityMap<T extends QuantifiableI> implements QuantityMapI<T>
{
    private Map<String, T>map;

    @Override public Map<String, T>getMap()        { return map; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public QuantityMap(Map<String, T> map)
    {   this.map = map; }

    // MEW FEATURES:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public boolean isAvailable(String key)
    {   return (containsKey(key) && get(key).getAvailable() > 0); }

    @Override
    public void add(T object)
    {
        computeIfAbsent(object.getID(), v -> put(object.getID(), object));
        object.setAvailable(object.getAvailable() + 1);
        object.setTotal(object.getTotal() + 1);
    }

    @Override
    public boolean borrow(String key)
    {
        T quantityObject = get(key);

        if (quantityObject != null)
            quantityObject.setAvailable(quantityObject.getAvailable() - 1);

        return quantityObject != null;
    }

    @Override
    public boolean returnBack(String key)
    {
        QuantifiableI quantityObject = get(key);

        if (quantityObject != null)
            quantityObject.setAvailable(quantityObject.getAvailable() + 1);

        return quantityObject != null;
    }
}
