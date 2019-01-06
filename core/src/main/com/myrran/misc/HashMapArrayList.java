package main.com.myrran.misc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class HashMapArrayList<K, V> extends HashMap<K, List<V>> implements MapList<K, V>
{
    @Override public void add(K key, V value)
    {
        List<V> list = get(key);

        if (list == null)
        {
            list = new ArrayList<V>();
            put(key, list);
        }
        list.add(value);
    }

    @Override public void add(K key, Collection<V> values)
    {
        List<V> list = get(key);

        if (list == null)
        {
            list = new ArrayList<V>();
            put(key, list);
        }
        list.addAll(values);
    }
}
