package defpackage;

import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ad;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.k.b;
import com.google.android.exoplayer2.source.k.c;
import com.google.android.exoplayer2.t;
import com.google.android.exoplayer2.trackselection.f;
import java.io.IOException;

/* compiled from: AnalyticsListener */
/* renamed from: gn */
public interface gn {

    /* compiled from: AnalyticsListener */
    /* renamed from: gn$a */
    public static final class a {
        public final long a;
        public final ad b;
        public final int c;
        @Nullable
        public final com.google.android.exoplayer2.source.j.a d;
        public final long e;
        public final long f;
        public final long g;

        public a(long j, ad adVar, int i, @Nullable com.google.android.exoplayer2.source.j.a aVar, long j2, long j3, long j4) {
            this.a = j;
            this.b = adVar;
            this.c = i;
            this.d = aVar;
            this.e = j2;
            this.f = j3;
            this.g = j4;
        }
    }

    void a(a aVar);

    void a(a aVar, int i);

    void a(a aVar, int i, int i2, int i3, float f);

    void a(a aVar, int i, long j);

    void a(a aVar, int i, long j, long j2);

    void a(a aVar, int i, Format format);

    void a(a aVar, int i, gr grVar);

    void a(a aVar, int i, String str, long j);

    void a(a aVar, Surface surface);

    void a(a aVar, ExoPlaybackException exoPlaybackException);

    void a(a aVar, Metadata metadata);

    void a(a aVar, TrackGroupArray trackGroupArray, f fVar);

    void a(a aVar, b bVar, c cVar);

    void a(a aVar, b bVar, c cVar, IOException iOException, boolean z);

    void a(a aVar, c cVar);

    void a(a aVar, t tVar);

    void a(a aVar, Exception exception);

    void a(a aVar, boolean z);

    void a(a aVar, boolean z, int i);

    void b(a aVar);

    void b(a aVar, int i);

    void b(a aVar, int i, long j, long j2);

    void b(a aVar, int i, gr grVar);

    void b(a aVar, b bVar, c cVar);

    void b(a aVar, c cVar);

    void b(a aVar, boolean z);

    void c(a aVar);

    void c(a aVar, int i);

    void c(a aVar, b bVar, c cVar);

    void d(a aVar);

    void d(a aVar, int i);

    void e(a aVar);

    void f(a aVar);

    void g(a aVar);

    void h(a aVar);
}
