package com.tencent.liteav.f;

import com.tencent.liteav.i.a.g;
import com.tencent.liteav.i.a.j;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: SubtitleFilterChain */
public class h extends c {
    private static h d;
    private List<j> e;
    private CopyOnWriteArrayList<j> f = new CopyOnWriteArrayList();

    public static h a() {
        if (d == null) {
            d = new h();
        }
        return d;
    }

    private h() {
    }

    public void a(List<j> list) {
        this.e = list;
        this.f.clear();
        if (this.c != null) {
            a(this.c);
        }
    }

    public List<j> b() {
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
        r1 = (com.tencent.liteav.i.a.j) r1;
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
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.f.h.a(com.tencent.liteav.d.e):void");
    }

    private j a(j jVar, g gVar) {
        j jVar2 = new j();
        jVar2.b = gVar;
        jVar2.a = jVar.a;
        jVar2.c = jVar.c;
        jVar2.d = jVar.d;
        return jVar2;
    }

    public void c() {
        b(this.f);
        b(this.e);
        this.e = null;
    }

    /* Access modifiers changed, original: protected */
    public void b(List<j> list) {
        if (list != null) {
            for (j jVar : list) {
                if (!(jVar == null || jVar.a == null || jVar.a.isRecycled())) {
                    jVar.a.recycle();
                    jVar.a = null;
                }
            }
            list.clear();
        }
    }
}
