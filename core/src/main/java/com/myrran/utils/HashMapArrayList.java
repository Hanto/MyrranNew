package com.myrran.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapArrayList<K, V> extends HashMap<K, List<V>> implements MapList<K, V>
{
    @Override public List<V> createList()
    {   return new ArrayList<>(); }
}
