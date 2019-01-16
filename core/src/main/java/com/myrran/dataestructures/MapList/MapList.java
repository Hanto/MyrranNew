package com.myrran.dataestructures.MapList;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/** @author Ivan Delgado Huerta */
public class MapList<K, V> implements MapListI<K, V>
{
    private Map<K, List<V>>mapList;
    private Supplier<List<V>>listCreator;

    @Override public Map<K, List<V>> getMap()           { return mapList; }

    // CONSTRUCTOR:
    //--------------------------------------------------------------------------------------------------------

    public MapList(Map<K, List<V>> mapList, Supplier<List<V>> listCreator)
    {
        this.mapList = mapList;
        this.listCreator = listCreator;
    }

    // MEW FEATURES:
    //--------------------------------------------------------------------------------------------------------

    public void add(K key, V value)
    {
        mapList.computeIfAbsent(key, k -> listCreator.get() );
        List<V> list = mapList.get(key);
        list.add(value);
    }

    public void add(K key, Collection<V> values)
    {
        mapList.computeIfAbsent(key, k -> listCreator.get() );
        List<V> list = mapList.get(key);
        list.addAll(values);
    }
}
