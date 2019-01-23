package com.myrran.misc.dataestructures.quantitymap;

import com.myrran.misc.DefaultMap;

public interface QuantityMapI<T extends QuantityObjectI> extends DefaultMap<String, T>
{
    void add(T object);
    boolean isAvailable(String key);
    boolean borrow(String key);
    boolean returnBack(String key);
}
