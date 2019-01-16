package com.myrran.dataestructures;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/** @author Ivan Delgado Huerta */
public interface DefaultMap<K, V> extends Map<K, V>
{
    Map<K, V>getMap();

    // DECORATOR:
    //--------------------------------------------------------------------------------------------------------

    @Override default int size()
    {   return getMap().size();}

    @Override default boolean isEmpty()
    {   return getMap().isEmpty();}

    @Override default boolean containsKey(Object key)
    {   return getMap().containsKey(key);}

    @Override default boolean containsValue(Object value)
    {   return getMap().containsValue(value);}

    @Override default V get(Object key)
    {   return getMap().get(key);}

    @Override default V put(K key, V value)
    {   return getMap().put(key, value);}

    @Override default V remove(Object key)
    {   return getMap().remove(key);}

    @Override default void putAll(Map<? extends K, ? extends V> m)
    {   getMap().putAll(m);}

    @Override default void clear()
    {   getMap().clear();}

    @Override default Set<K> keySet()
    {   return getMap().keySet();}

    @Override default Collection<V> values()
    {   return getMap().values();}

    @Override default Set<Entry<K, V>> entrySet()
    {   return getMap().entrySet();}
}
