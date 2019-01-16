package com.myrran.dataestructures.QuantityMap;

import com.myrran.dataestructures.DefaultMap;

public interface QuantityMap extends DefaultMap<String, QuantityObject>
{
    void add(String key);
    boolean borrow(String key);
    boolean returnBack(String key);
}
