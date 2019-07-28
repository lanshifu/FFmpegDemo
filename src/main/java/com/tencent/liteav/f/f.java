package com.tencent.liteav.f;

import com.tencent.liteav.i.a.e;
import com.tencent.liteav.i.a.g;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: PasterFilterChain */
public class f extends c {
    private static f d;
    private List<e> e;
    private CopyOnWriteArrayList<e> f = new CopyOnWriteArrayList();

    public static f a() {
        if (d == null) {
            d = new f();
        }
        return d;
    }

    private f() {
    }

    public void a(List<e> list) {
        this.e = list;
        b(this.f);
        if (this.c != null) {
            a(this.c);
        }
    }

    public List<e> b() {
        return this.f;
    }

    /* JADX WARNING: Missing block: B:17:0x0041, code skipped:
            return;
     */
    public void a(com.tencent.liteav.d.e r4) {
        /*
        r3 = this;
        r0 = r3.a;
        if (r0 == 0) goto L_0x0041;
    L_0x0004:
        r0 = r3.b;
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        goto L_0x0041;
    L_0x0009:
        r0 = r3.e;
        if (r0 == 0) goto L_0x0040;
    L_0x000d:
        r0 = r3.e;
        r0 = r0.size();
        if (r0 != 0) goto L_0x0016;
    L_0x0015:
        goto L_0x0040;
    L_0x0016:
        r4 = r3.b(r4);
        r0 = r3.e;
        r0 = r0.iterator();
    L_0x0020:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x003f;
    L_0x0026:
        r1 = r0.next();
        r1 = (com.tencent.liteav.i.a.e) r1;
        if (r1 != 0) goto L_0x002f;
    L_0x002e:
        goto L_0x0020;
    L_0x002f:
        r2 = r1.b;
        r2 = r3.a(r2, r4);
        r1 = r3.a(r1, r2);
        r2 = r3.f;
        r2.add(r1);
        goto L_0x0020;
    L_0x003f:
        return;
    L_0x0040:
        return;
    L_0x0041:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.f.a(com.tencent.liteav.d.e):void");
    }

    private e a(e eVar, g gVar) {
        e eVar2 = new e();
        eVar2.b = gVar;
        eVar2.a = eVar.a;
        eVar2.c = eVar.c;
        eVar2.d = eVar.d;
        return eVar2;
    }

    public void c() {
        b(this.f);
        b(this.e);
        this.e = null;
    }

    /* Access modifiers changed, original: protected */
    public void b(List<e> list) {
        if (list != null) {
            for (e eVar : list) {
                if (!(eVar == null || eVar.a == null || eVar.a.isRecycled())) {
                    eVar.a.recycle();
                    eVar.a = null;
                }
            }
            list.clear();
        }
    }
}
