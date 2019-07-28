package defpackage;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.k;
import defpackage.kd.a;
import java.io.IOException;

/* compiled from: HlsPlaylistTracker */
/* renamed from: kh */
public interface kh {

    /* compiled from: HlsPlaylistTracker */
    /* renamed from: kh$a */
    public interface a {
    }

    /* compiled from: HlsPlaylistTracker */
    /* renamed from: kh$b */
    public interface b {
    }

    @Nullable
    ke a(a aVar);

    void a();

    void a(Uri uri, k.a aVar, b bVar);

    void a(a aVar);

    @Nullable
    kd b();

    void b(a aVar);

    boolean b(a aVar);

    long c();

    void c(a aVar) throws IOException;

    void d() throws IOException;

    void d(a aVar);

    boolean e();
}
