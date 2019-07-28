package net.vidageek.mirror.thirdparty.org.objenesis;

import java.util.HashMap;
import java.util.Map;
import net.vidageek.mirror.thirdparty.org.objenesis.strategy.InstantiatorStrategy;

public class ObjenesisBase implements Objenesis {
    protected Map cache;
    protected final InstantiatorStrategy strategy;

    public ObjenesisBase(InstantiatorStrategy instantiatorStrategy) {
        this(instantiatorStrategy, true);
    }

    public ObjenesisBase(InstantiatorStrategy instantiatorStrategy, boolean z) {
        if (instantiatorStrategy != null) {
            this.strategy = instantiatorStrategy;
            this.cache = z ? new HashMap() : null;
            return;
        }
        throw new IllegalArgumentException("A strategy can't be null");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getName());
        stringBuffer.append(" using ");
        stringBuffer.append(this.strategy.getClass().getName());
        stringBuffer.append(this.cache == null ? " without" : " with");
        stringBuffer.append(" caching");
        return stringBuffer.toString();
    }

    public Object newInstance(Class cls) {
        return getInstantiatorOf(cls).newInstance();
    }

    /* JADX WARNING: Missing block: B:12:0x002b, code skipped:
            return r0;
     */
    public synchronized net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator getInstantiatorOf(java.lang.Class r3) {
        /*
        r2 = this;
        monitor-enter(r2);
        r0 = r2.cache;	 Catch:{ all -> 0x002c }
        if (r0 != 0) goto L_0x000d;
    L_0x0005:
        r0 = r2.strategy;	 Catch:{ all -> 0x002c }
        r3 = r0.newInstantiatorOf(r3);	 Catch:{ all -> 0x002c }
        monitor-exit(r2);
        return r3;
    L_0x000d:
        r0 = r2.cache;	 Catch:{ all -> 0x002c }
        r1 = r3.getName();	 Catch:{ all -> 0x002c }
        r0 = r0.get(r1);	 Catch:{ all -> 0x002c }
        r0 = (net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator) r0;	 Catch:{ all -> 0x002c }
        if (r0 != 0) goto L_0x002a;
    L_0x001b:
        r0 = r2.strategy;	 Catch:{ all -> 0x002c }
        r0 = r0.newInstantiatorOf(r3);	 Catch:{ all -> 0x002c }
        r1 = r2.cache;	 Catch:{ all -> 0x002c }
        r3 = r3.getName();	 Catch:{ all -> 0x002c }
        r1.put(r3, r0);	 Catch:{ all -> 0x002c }
    L_0x002a:
        monitor-exit(r2);
        return r0;
    L_0x002c:
        r3 = move-exception;
        monitor-exit(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.vidageek.mirror.thirdparty.org.objenesis.ObjenesisBase.getInstantiatorOf(java.lang.Class):net.vidageek.mirror.thirdparty.org.objenesis.instantiator.ObjectInstantiator");
    }
}
