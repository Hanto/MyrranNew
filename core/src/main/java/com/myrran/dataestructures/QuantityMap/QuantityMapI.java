package com.myrran.dataestructures.QuantityMap;

import com.myrran.dataestructures.DefaultMap;

public interface QuantityMapI extends DefaultMap<String, QuantityObject>
{
    void add(String key);
    boolean borrow(String key);
    boolean returnBack(String key);
}
