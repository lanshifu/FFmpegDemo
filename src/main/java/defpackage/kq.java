package defpackage;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.text.f;
import com.google.android.exoplayer2.text.h;
import com.google.android.exoplayer2.text.i;
import com.google.android.exoplayer2.util.a;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

/* compiled from: CeaDecoder */
/* renamed from: kq */
abstract class kq implements f {
    private final ArrayDeque<a> a = new ArrayDeque();
    private final ArrayDeque<i> b;
    private final PriorityQueue<a> c;
    private a d;
    private long e;
    private long f;

    /* compiled from: CeaDecoder */
    /* renamed from: kq$a */
    private static final class a extends h implements Comparable<a> {
        private long e;

        private a() {
        }

        /* renamed from: a */
        public int compareTo(@NonNull a aVar) {
            int i = -1;
            if (c() != aVar.c()) {
                if (c()) {
                    i = 1;
                }
                return i;
            }
            long j = this.c - aVar.c;
            if (j == 0) {
                j = this.e - aVar.e;
                if (j == 0) {
                    return 0;
                }
            }
            if (j > 0) {
                i = 1;
            }
            return i;
        }
    }

    /* compiled from: CeaDecoder */
    /* renamed from: kq$b */
    private final class b extends i {
        private b() {
        }

        public final void e() {
            kq.this.a((i) this);
        }
    }

    public abstract void a(h hVar);

    public void d() {
    }

    public abstract boolean e();

    public abstract e f();

    public kq() {
        for (int i = 0; i < 10; i++) {
            this.a.add(new a());
        }
        this.b = new ArrayDeque();
        for (int i2 = 0; i2 < 2; i2++) {
            this.b.add(new b());
        }
        this.c = new PriorityQueue();
    }

    public void a(long j) {
        this.e = j;
    }

    /* renamed from: h */
    public h a() throws SubtitleDecoderException {
        a.b(this.d == null);
        if (this.a.isEmpty()) {
            return null;
        }
        this.d = (a) this.a.pollFirst();
        return this.d;
    }

    /* renamed from: b */
    public void a(h hVar) throws SubtitleDecoderException {
        a.a(hVar == this.d);
        if (hVar.e_()) {
            a(this.d);
        } else {
            a aVar = this.d;
            long j = this.f;
            this.f = 1 + j;
            aVar.e = j;
            this.c.add(this.d);
        }
        this.d = null;
    }

    /* renamed from: g */
    public i b() throws SubtitleDecoderException {
        if (this.b.isEmpty()) {
            return null;
        }
        while (!this.c.isEmpty() && ((a) this.c.peek()).c <= this.e) {
            a aVar = (a) this.c.poll();
            i iVar;
            if (aVar.c()) {
                iVar = (i) this.b.pollFirst();
                iVar.b(4);
                a(aVar);
                return iVar;
            }
            a((h) aVar);
            if (e()) {
                e f = f();
                if (!aVar.e_()) {
                    iVar = (i) this.b.pollFirst();
                    iVar.a(aVar.c, f, Long.MAX_VALUE);
                    a(aVar);
                    return iVar;
                }
            }
            a(aVar);
        }
        return null;
    }

    private void a(a aVar) {
        aVar.a();
        this.a.add(aVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(i iVar) {
        iVar.a();
        this.b.add(iVar);
    }

    public void c() {
        this.f = 0;
        this.e = 0;
        while (!this.c.isEmpty()) {
            a((a) this.c.poll());
        }
        if (this.d != null) {
            a(this.d);
            this.d = null;
        }
    }
}
