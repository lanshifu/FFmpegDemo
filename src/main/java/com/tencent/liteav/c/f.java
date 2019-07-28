package com.tencent.liteav.c;

import com.tencent.liteav.i.a.h;
import java.util.List;

/* compiled from: RepeatPlayConfig */
public class f {
    private static f a;
    private List<h> b;

    public static f a() {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new f();
                }
            }
        }
        return a;
    }

    public void a(List<h> list) {
        this.b = list;
    }

    public h b() {
        return (this.b == null || this.b.size() == 0) ? null : (h) this.b.get(0);
    }

    public void c() {
        if (this.b != null) {
            this.b.clear();
        }
        this.b = null;
    }
}
