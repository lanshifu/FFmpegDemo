package org.xutils.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DoubleKeyValueMap<K1, K2, V> {
    private final ConcurrentHashMap<K1, ConcurrentHashMap<K2, V>> a = new ConcurrentHashMap();

    public void put(K1 k1, K2 k2, V v) {
        if (k1 != null && k2 != null && v != null) {
            ConcurrentHashMap concurrentHashMap;
            if (this.a.containsKey(k1)) {
                concurrentHashMap = (ConcurrentHashMap) this.a.get(k1);
                if (concurrentHashMap != null) {
                    concurrentHashMap.put(k2, v);
                } else {
                    concurrentHashMap = new ConcurrentHashMap();
                    concurrentHashMap.put(k2, v);
                    this.a.put(k1, concurrentHashMap);
                }
            } else {
                concurrentHashMap = new ConcurrentHashMap();
                concurrentHashMap.put(k2, v);
                this.a.put(k1, concurrentHashMap);
            }
        }
    }

    public Set<K1> getFirstKeys() {
        return this.a.keySet();
    }

    public ConcurrentHashMap<K2, V> get(K1 k1) {
        return (ConcurrentHashMap) this.a.get(k1);
    }

    public V get(K1 k1, K2 k2) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.a.get(k1);
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.get(k2);
    }

    public Collection<V> getAllValues(K1 k1) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.a.get(k1);
        if (concurrentHashMap == null) {
            return null;
        }
        return concurrentHashMap.values();
    }

    public Collection<V> getAllValues() {
        Set<Object> keySet = this.a.keySet();
        if (keySet == null) {
            return null;
        }
        Collection<V> arrayList = new ArrayList();
        for (Object obj : keySet) {
            Collection values = ((ConcurrentHashMap) this.a.get(obj)).values();
            if (values != null) {
                arrayList.addAll(values);
            }
        }
        return arrayList;
    }

    public boolean containsKey(K1 k1, K2 k2) {
        return this.a.containsKey(k1) ? ((ConcurrentHashMap) this.a.get(k1)).containsKey(k2) : false;
    }

    public boolean containsKey(K1 k1) {
        return this.a.containsKey(k1);
    }

    public int size() {
        int i = 0;
        if (this.a.size() == 0) {
            return 0;
        }
        for (ConcurrentHashMap size : this.a.values()) {
            i += size.size();
        }
        return i;
    }

    public void remove(K1 k1) {
        this.a.remove(k1);
    }

    public void remove(K1 k1, K2 k2) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.a.get(k1);
        if (concurrentHashMap != null) {
            concurrentHashMap.remove(k2);
        }
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            this.a.remove(k1);
        }
    }

    public void clear() {
        if (this.a.size() > 0) {
            for (ConcurrentHashMap clear : this.a.values()) {
                clear.clear();
            }
            this.a.clear();
        }
    }
}
