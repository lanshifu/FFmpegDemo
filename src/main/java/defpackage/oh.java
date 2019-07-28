package defpackage;

import com.one.tomato.entity.db.VideoDownload;
import com.one.tomato.utils.g;
import com.one.tomato.utils.k;
import com.one.tomato.utils.y;
import defpackage.og.a;
import io.reactivex.disposables.b;
import io.reactivex.m;
import io.reactivex.n;
import io.reactivex.q;
import io.reactivex.r;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.f;

/* compiled from: DownloadPresenter.kt */
/* renamed from: oh */
public final class oh extends oe<a> {

    /* compiled from: DownloadPresenter.kt */
    /* renamed from: oh$a */
    public static final class a implements n<String[]> {
        a() {
        }

        public void subscribe(m<String[]> mVar) {
            f.b(mVar, "emitter");
            Object k = g.k();
            f.a(k, "DBUtil.getAllVideoDownload()");
            Iterator it = k.iterator();
            long j = 0;
            while (it.hasNext()) {
                Object obj = (VideoDownload) it.next();
                f.a(obj, "bean");
                j += k.a(new File(pl.c(obj.getUrl())));
            }
            k = k.a(j);
            Object a = k.a(k.i());
            r2 = new String[2];
            f.a(k, "cachedSize");
            r2[0] = k;
            f.a(a, "sdcardFree");
            r2[1] = a;
            mVar.onNext(r2);
            mVar.onComplete();
        }
    }

    /* compiled from: DownloadPresenter.kt */
    /* renamed from: oh$b */
    public static final class b implements r<String[]> {
        final /* synthetic */ oh a;

        public void onComplete() {
        }

        public void onError(Throwable th) {
            f.b(th, "e");
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            f.b(bVar, "d");
        }

        b(oh ohVar) {
            this.a = ohVar;
        }

        /* renamed from: a */
        public void onNext(String[] strArr) {
            f.b(strArr, "t");
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(strArr);
            }
        }
    }

    /* compiled from: DownloadPresenter.kt */
    /* renamed from: oh$c */
    public static final class c implements n<ArrayList<VideoDownload>> {
        c() {
        }

        public void subscribe(m<ArrayList<VideoDownload>> mVar) {
            f.b(mVar, "emitter");
            ArrayList k = g.k();
            Iterator it = k.iterator();
            while (it.hasNext()) {
                Object obj = (VideoDownload) it.next();
                f.a(obj, "item");
                if (obj.getState() != 3) {
                    obj.setState(0);
                }
            }
            mVar.onNext(k);
            mVar.onComplete();
        }
    }

    /* compiled from: DownloadPresenter.kt */
    /* renamed from: oh$d */
    public static final class d implements r<ArrayList<VideoDownload>> {
        final /* synthetic */ oh a;

        public void onComplete() {
        }

        public void onError(Throwable th) {
            f.b(th, "e");
        }

        public void onSubscribe(b bVar) {
            f.b(bVar, "d");
        }

        d(oh ohVar) {
            this.a = ohVar;
        }

        /* renamed from: a */
        public void onNext(ArrayList<VideoDownload> arrayList) {
            f.b(arrayList, "t");
            a a = ((a) this.a.a());
            if (a != null) {
                a.a((ArrayList) arrayList);
            }
        }
    }

    public void e() {
    }

    public void f() {
        io.reactivex.k.create(new c()).compose(y.a()).compose((q) y.a(c())).subscribe((r) new d(this));
    }

    public void g() {
        io.reactivex.k.create(new a()).delay(1, TimeUnit.SECONDS).compose(y.a()).compose((q) y.a(c())).subscribe((r) new b(this));
    }
}
