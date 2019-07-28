package org.xutils.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    /* Access modifiers changed, original: protected */
    public V create(K k) {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void entryRemoved(boolean z, K k, V v, V v2) {
    }

    /* Access modifiers changed, original: protected */
    public int sizeOf(K k, V v) {
        return 1;
    }

    public LruCache(int i) {
        if (i > 0) {
            this.c = i;
            this.a = new LinkedHashMap(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void resize(int i) {
        if (i > 0) {
            synchronized (this) {
                this.c = i;
            }
            trimToSize(i);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    /* JADX WARNING: Missing block: B:10:0x001a, code skipped:
            r0 = create(r5);
     */
    /* JADX WARNING: Missing block: B:11:0x001e, code skipped:
            if (r0 != null) goto L_0x0022;
     */
    /* JADX WARNING: Missing block: B:13:0x0021, code skipped:
            return null;
     */
    /* JADX WARNING: Missing block: B:14:0x0022, code skipped:
            monitor-enter(r4);
     */
    /* JADX WARNING: Missing block: B:16:?, code skipped:
            r4.e++;
            r1 = r4.a.put(r5, r0);
     */
    /* JADX WARNING: Missing block: B:17:0x002f, code skipped:
            if (r1 == null) goto L_0x0037;
     */
    /* JADX WARNING: Missing block: B:18:0x0031, code skipped:
            r4.a.put(r5, r1);
     */
    /* JADX WARNING: Missing block: B:19:0x0037, code skipped:
            r4.b += a(r5, r0);
     */
    /* JADX WARNING: Missing block: B:20:0x0040, code skipped:
            monitor-exit(r4);
     */
    /* JADX WARNING: Missing block: B:21:0x0041, code skipped:
            if (r1 == null) goto L_0x0048;
     */
    /* JADX WARNING: Missing block: B:22:0x0043, code skipped:
            entryRemoved(false, r5, r0, r1);
     */
    /* JADX WARNING: Missing block: B:23:0x0047, code skipped:
            return r1;
     */
    /* JADX WARNING: Missing block: B:24:0x0048, code skipped:
            trimToSize(r4.c);
     */
    /* JADX WARNING: Missing block: B:25:0x004d, code skipped:
            return r0;
     */
    public final V get(K r5) {
        /*
        r4 = this;
        if (r5 == 0) goto L_0x0054;
    L_0x0002:
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ all -> 0x0051 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0051 }
        if (r0 == 0) goto L_0x0013;
    L_0x000b:
        r5 = r4.g;	 Catch:{ all -> 0x0051 }
        r5 = r5 + 1;
        r4.g = r5;	 Catch:{ all -> 0x0051 }
        monitor-exit(r4);	 Catch:{ all -> 0x0051 }
        return r0;
    L_0x0013:
        r0 = r4.h;	 Catch:{ all -> 0x0051 }
        r0 = r0 + 1;
        r4.h = r0;	 Catch:{ all -> 0x0051 }
        monitor-exit(r4);	 Catch:{ all -> 0x0051 }
        r0 = r4.create(r5);
        if (r0 != 0) goto L_0x0022;
    L_0x0020:
        r5 = 0;
        return r5;
    L_0x0022:
        monitor-enter(r4);
        r1 = r4.e;	 Catch:{ all -> 0x004e }
        r1 = r1 + 1;
        r4.e = r1;	 Catch:{ all -> 0x004e }
        r1 = r4.a;	 Catch:{ all -> 0x004e }
        r1 = r1.put(r5, r0);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x0037;
    L_0x0031:
        r2 = r4.a;	 Catch:{ all -> 0x004e }
        r2.put(r5, r1);	 Catch:{ all -> 0x004e }
        goto L_0x0040;
    L_0x0037:
        r2 = r4.b;	 Catch:{ all -> 0x004e }
        r3 = r4.a(r5, r0);	 Catch:{ all -> 0x004e }
        r2 = r2 + r3;
        r4.b = r2;	 Catch:{ all -> 0x004e }
    L_0x0040:
        monitor-exit(r4);	 Catch:{ all -> 0x004e }
        if (r1 == 0) goto L_0x0048;
    L_0x0043:
        r2 = 0;
        r4.entryRemoved(r2, r5, r0, r1);
        return r1;
    L_0x0048:
        r5 = r4.c;
        r4.trimToSize(r5);
        return r0;
    L_0x004e:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x004e }
        throw r5;
    L_0x0051:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0051 }
        throw r5;
    L_0x0054:
        r5 = new java.lang.NullPointerException;
        r0 = "key == null";
        r5.<init>(r0);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.cache.LruCache.get(java.lang.Object):java.lang.Object");
    }

    public final V put(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        Object put;
        synchronized (this) {
            this.d++;
            this.b += a(k, v);
            put = this.a.put(k, v);
            if (put != null) {
                this.b -= a(k, put);
            }
        }
        if (put != null) {
            entryRemoved(false, k, put, v);
        }
        trimToSize(this.c);
        return put;
    }

    /* JADX WARNING: Missing block: B:20:0x0070, code skipped:
            throw new java.lang.IllegalStateException(r0.toString());
     */
    public void trimToSize(int r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.b;	 Catch:{ all -> 0x0071 }
        if (r0 < 0) goto L_0x0052;
    L_0x0005:
        r0 = r4.a;	 Catch:{ all -> 0x0071 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x0011;
    L_0x000d:
        r0 = r4.b;	 Catch:{ all -> 0x0071 }
        if (r0 != 0) goto L_0x0052;
    L_0x0011:
        r0 = r4.b;	 Catch:{ all -> 0x0071 }
        if (r0 <= r5) goto L_0x0050;
    L_0x0015:
        r0 = r4.a;	 Catch:{ all -> 0x0071 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0071 }
        if (r0 == 0) goto L_0x001e;
    L_0x001d:
        goto L_0x0050;
    L_0x001e:
        r0 = r4.a;	 Catch:{ all -> 0x0071 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0071 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0071 }
        r0 = r0.next();	 Catch:{ all -> 0x0071 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0071 }
        r1 = r0.getKey();	 Catch:{ all -> 0x0071 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0071 }
        r2 = r4.a;	 Catch:{ all -> 0x0071 }
        r2.remove(r1);	 Catch:{ all -> 0x0071 }
        r2 = r4.b;	 Catch:{ all -> 0x0071 }
        r3 = r4.a(r1, r0);	 Catch:{ all -> 0x0071 }
        r2 = r2 - r3;
        r4.b = r2;	 Catch:{ all -> 0x0071 }
        r2 = r4.f;	 Catch:{ all -> 0x0071 }
        r3 = 1;
        r2 = r2 + r3;
        r4.f = r2;	 Catch:{ all -> 0x0071 }
        monitor-exit(r4);	 Catch:{ all -> 0x0071 }
        r2 = 0;
        r4.entryRemoved(r3, r1, r0, r2);
        goto L_0x0000;
    L_0x0050:
        monitor-exit(r4);	 Catch:{ all -> 0x0071 }
        return;
    L_0x0052:
        r5 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0071 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0071 }
        r0.<init>();	 Catch:{ all -> 0x0071 }
        r1 = r4.getClass();	 Catch:{ all -> 0x0071 }
        r1 = r1.getName();	 Catch:{ all -> 0x0071 }
        r0.append(r1);	 Catch:{ all -> 0x0071 }
        r1 = ".sizeOf() is reporting inconsistent results!";
        r0.append(r1);	 Catch:{ all -> 0x0071 }
        r0 = r0.toString();	 Catch:{ all -> 0x0071 }
        r5.<init>(r0);	 Catch:{ all -> 0x0071 }
        throw r5;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0071 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.cache.LruCache.trimToSize(int):void");
    }

    public final V remove(K k) {
        if (k != null) {
            Object remove;
            synchronized (this) {
                remove = this.a.remove(k);
                if (remove != null) {
                    this.b -= a(k, remove);
                }
            }
            if (remove != null) {
                entryRemoved(false, k, remove, null);
            }
            return remove;
        }
        throw new NullPointerException("key == null");
    }

    private int a(K k, V v) {
        int sizeOf = sizeOf(k, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Negative size: ");
        stringBuilder.append(k);
        stringBuilder.append("=");
        stringBuilder.append(v);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.b;
    }

    public final synchronized int maxSize() {
        return this.c;
    }

    public final synchronized int hitCount() {
        return this.g;
    }

    public final synchronized int missCount() {
        return this.h;
    }

    public final synchronized int createCount() {
        return this.e;
    }

    public final synchronized int putCount() {
        return this.d;
    }

    public final synchronized int evictionCount() {
        return this.f;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.a);
    }

    public final synchronized String toString() {
        int i;
        i = this.g + this.h;
        i = i != 0 ? (this.g * 100) / i : 0;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.g), Integer.valueOf(this.h), Integer.valueOf(i)});
    }
}
