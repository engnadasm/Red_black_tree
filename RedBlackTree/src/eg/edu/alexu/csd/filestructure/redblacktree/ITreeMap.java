/*
 * Decompiled with CFR 0.139.
 */
package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ITreeMap<T extends Comparable<T>, V> {
    public Map.Entry<T, V> ceilingEntry(T var1);

    public T ceilingKey(T var1);

    public void clear();

    public boolean containsKey(T var1);

    public boolean containsValue(V var1);

    public Set<Map.Entry<T, V>> entrySet();

    public Map.Entry<T, V> firstEntry();

    public T firstKey();

    public Map.Entry<T, V> floorEntry(T var1);

    public T floorKey(T var1);

    public V get(T var1);

    public ArrayList<Map.Entry<T, V>> headMap(T var1);

    public ArrayList<Map.Entry<T, V>> headMap(T var1, boolean var2);

    public Set<T> keySet();

    public Map.Entry<T, V> lastEntry();

    public T lastKey();

    public Map.Entry<T, V> pollFirstEntry();

    public Map.Entry<T, V> pollLastEntry();

    public void put(T var1, V var2);

    public void putAll(Map<T, V> var1);

    public boolean remove(T var1);

    public int size();

    public Collection<V> values();
}

