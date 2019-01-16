package com.myrran.dataestructures.QuantityMap;

import java.util.Map;
import java.util.function.Supplier;

public class QuantityMap implements QuantityMapI
{
    private Map<String, QuantityObject>map;

    @Override public Map<String, QuantityObject>getMap()        { return map; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public QuantityMap(Supplier<Map<String, QuantityObject>>mapCreator)
    {   this.map = mapCreator.get(); }

    // MEW FEATURES:
    //--------------------------------------------------------------------------------------------------------

    @Override
    public void add(String key)
    {
        QuantityObject quantityObject = get(key);

        if (quantityObject == null)
        {
            quantityObject = new QuantityObject();
            put(key, quantityObject);
        }
        quantityObject.setAvailable(quantityObject.getAvailable() + 1);
        quantityObject.setTotal(quantityObject.getTotal() + 1);
    }

    @Override
    public boolean borrow(String key)
    {
        QuantityObject quantityObject = get(key);

        if (quantityObject != null)
            quantityObject.setAvailable(quantityObject.getAvailable() - 1);

        return quantityObject != null;
    }

    @Override
    public boolean returnBack(String key)
    {
        QuantityObject quantityObject = get(key);

        if (quantityObject != null)
            quantityObject.setAvailable(quantityObject.getAvailable() + 1);

        return quantityObject != null;
    }


}
