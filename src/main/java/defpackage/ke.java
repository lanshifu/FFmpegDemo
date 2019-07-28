package defpackage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.drm.DrmInitData;
import java.util.Collections;
import java.util.List;

/* compiled from: HlsMediaPlaylist */
/* renamed from: ke */
public final class ke extends kf {
    public final int a;
    public final long b;
    public final long c;
    public final boolean d;
    public final int e;
    public final long f;
    public final int g;
    public final long h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final DrmInitData l;
    public final List<a> m;
    public final long n;

    /* compiled from: HlsMediaPlaylist */
    /* renamed from: ke$a */
    public static final class a implements Comparable<Long> {
        public final String a;
        @Nullable
        public final a b;
        public final long c;
        public final int d;
        public final long e;
        public final String f;
        public final String g;
        public final long h;
        public final long i;
        public final boolean j;

        public a(String str, long j, long j2) {
            this(str, null, 0, -1, -9223372036854775807L, null, null, j, j2, false);
        }

        public a(String str, a aVar, long j, int i, long j2, String str2, String str3, long j3, long j4, boolean z) {
            this.a = str;
            this.b = aVar;
            this.c = j;
            this.d = i;
            this.e = j2;
            this.f = str2;
            this.g = str3;
            this.h = j3;
            this.i = j4;
            this.j = z;
        }

        /* renamed from: a */
        public int compareTo(@NonNull Long l) {
            if (this.e > l.longValue()) {
                return 1;
            }
            return this.e < l.longValue() ? -1 : 0;
        }
    }

    public ke(int i, String str, List<String> list, long j, long j2, boolean z, int i2, long j3, int i3, long j4, boolean z2, boolean z3, boolean z4, DrmInitData drmInitData, List<a> list2) {
        String str2 = str;
        List<String> list3 = list;
        super(str, list);
        this.a = i;
        this.c = j2;
        this.d = z;
        this.e = i2;
        this.f = j3;
        this.g = i3;
        this.h = j4;
        this.i = z2;
        this.j = z3;
        this.k = z4;
        this.l = drmInitData;
        this.m = Collections.unmodifiableList(list2);
        if (list2.isEmpty()) {
            this.n = 0;
        } else {
            a aVar = (a) list2.get(list2.size() - 1);
            this.n = aVar.e + aVar.c;
        }
        long j5 = j == -9223372036854775807L ? -9223372036854775807L : j >= 0 ? j : this.n + j;
        this.b = j5;
    }

    public long a() {
        return this.c + this.n;
    }
}
