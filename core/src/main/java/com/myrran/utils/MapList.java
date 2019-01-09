package com.myrran.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MapList<K, V> extends Map<K, List<V>>
{
    List<V>createList();

    default void add(K key, V value)
    {
        List<V> list = get(key);

        if (list == null)
        {
            list = createList();
            put(key, list);
        }
        list.add(value);
    }

    default void add(K key, Collection<V> values)
    {
        List<V> list = get(key);

        if (list == null)
        {
            list = createList();
            put(key, list);
        }
        list.addAll(values);
    }
}
