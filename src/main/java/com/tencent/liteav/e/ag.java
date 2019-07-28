package com.tencent.liteav.e;

import android.content.Context;
import android.graphics.Bitmap;
import com.tencent.liteav.c.h;
import com.tencent.liteav.c.k;
import com.tencent.liteav.i.b.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoTimelistThumbnailGenerate */
public class ag extends e {
    private a n;
    private ArrayList<Long> o;
    private s p;

    /* Access modifiers changed, original: protected */
    public int a(int i, int i2, int i3, long j) {
        return i;
    }

    /* Access modifiers changed, original: protected */
    public void a(long j) {
    }

    /* Access modifiers changed, original: protected */
    public void d() {
    }

    /* Access modifiers changed, original: protected */
    public void e() {
    }

    public ag(Context context) {
        super(context);
        this.p = new s() {
            public void a(int i, long j, Bitmap bitmap) {
                if (ag.this.n != null) {
                    ag.this.n.a(i, j / 1000, bitmap);
                }
            }
        };
        this.o = new ArrayList();
        this.c = new af();
        this.f.a(this.p);
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public void a() {
        a(k.a().a);
        f();
        super.a();
    }

    /* Access modifiers changed, original: protected */
    public void f() {
        h.a().a(this.o);
        this.c.a(h.a().b());
    }

    public void a(int i) {
        h.a().a(i);
    }

    public void b(int i) {
        h.a().b(i);
    }

    public void a(List<Long> list) {
        this.o.clear();
        for (int i = 0; i < list.size(); i++) {
            this.o.add(Long.valueOf(((Long) list.get(i)).longValue() * 1000));
        }
    }

    public void c() {
        super.c();
        this.o.clear();
        this.p = null;
    }

    public void b(boolean z) {
        if (this.c != null) {
            this.c.a(z);
        }
    }
}
