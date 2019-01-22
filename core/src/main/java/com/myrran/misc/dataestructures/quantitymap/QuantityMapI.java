package com.myrran.misc.dataestructures.quantitymap;

import com.myrran.misc.DefaultMap;

public interface QuantityMapI extends DefaultMap<String, QuantityObject>
{
    void add(String key);
    boolean borrow(String key);
    boolean returnBack(String key);
}
