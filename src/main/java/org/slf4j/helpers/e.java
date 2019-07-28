package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.a;
import org.slf4j.b;
import org.slf4j.event.c;

/* compiled from: SubstituteLoggerFactory */
public class e implements a {
    boolean a = false;
    final Map<String, d> b = new HashMap();
    final LinkedBlockingQueue<c> c = new LinkedBlockingQueue();

    public synchronized b a(String str) {
        b bVar;
        bVar = (d) this.b.get(str);
        if (bVar == null) {
            bVar = new d(str, this.c, this.a);
            this.b.put(str, bVar);
        }
        return bVar;
    }

    public List<d> a() {
        return new ArrayList(this.b.values());
    }

    public LinkedBlockingQueue<c> b() {
        return this.c;
    }

    public void c() {
        this.a = true;
    }

    public void d() {
        this.b.clear();
        this.c.clear();
    }
}
