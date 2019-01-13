package com.myrran.utils;

import java.util.Map;

/** @author Ivan Delgado Huerta */
public interface QuantityMap extends Map<String, QuantityObject>
{
    default void add(String key)
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

    default boolean borrow(String key)
    {
        QuantityObject quantityObject = get(key);

        if (quantityObject != null)
            quantityObject.setAvailable(quantityObject.getAvailable() - 1);

        return quantityObject != null;
    }

    default boolean returnBack(String key)
    {
        QuantityObject quantityObject = get(key);

        if (quantityObject != null)
            quantityObject.setAvailable(quantityObject.getAvailable() + 1);

        return quantityObject != null;
    }
}