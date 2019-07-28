package defpackage;

import android.net.Uri;
import java.util.Arrays;

/* compiled from: AdPlaybackState */
/* renamed from: jh */
public final class jh {
    public static final jh a = new jh(new long[0]);
    public final int b;
    public final long[] c;
    public final a[] d;
    public final long e;
    public final long f;

    /* compiled from: AdPlaybackState */
    /* renamed from: jh$a */
    public static final class a {
        public final int a;
        public final Uri[] b;
        public final int[] c;
        public final long[] d;

        public a() {
            this(-1, new int[0], new Uri[0], new long[0]);
        }

        private a(int i, int[] iArr, Uri[] uriArr, long[] jArr) {
            com.google.android.exoplayer2.util.a.a(iArr.length == uriArr.length);
            this.a = i;
            this.c = iArr;
            this.b = uriArr;
            this.d = jArr;
        }

        public int a() {
            return a(-1);
        }

        public int a(int i) {
            i++;
            while (i < this.c.length && this.c[i] != 0 && this.c[i] != 1) {
                i++;
            }
            return i;
        }

        public boolean b() {
            return this.a == -1 || a() < this.a;
        }
    }

    public jh(long... jArr) {
        int length = jArr.length;
        this.b = length;
        this.c = Arrays.copyOf(jArr, length);
        this.d = new a[length];
        for (int i = 0; i < length; i++) {
            this.d[i] = new a();
        }
        this.e = 0;
        this.f = -9223372036854775807L;
    }

    public int a(long j) {
        int length = this.c.length - 1;
        while (length >= 0 && (this.c[length] == Long.MIN_VALUE || this.c[length] > j)) {
            length--;
        }
        return (length < 0 || !this.d[length].b()) ? -1 : length;
    }

    public int b(long j) {
        int i = 0;
        while (i < this.c.length && this.c[i] != Long.MIN_VALUE && (j >= this.c[i] || !this.d[i].b())) {
            i++;
        }
        return i < this.c.length ? i : -1;
    }
}
