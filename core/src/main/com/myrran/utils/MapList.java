package main.com.myrran.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface MapList<K, V> extends Map<K, List<V>>
{
    void add(K key, V value);
    void add(K key, Collection<V> values);
}
