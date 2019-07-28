package defpackage;

import com.one.tomato.entity.db.CountryDB;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.utils.g;
import com.one.tomato.utils.y;
import defpackage.oi.a;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.n;
import io.reactivex.q;
import io.reactivex.r;
import java.util.ArrayList;
import kotlin.jvm.internal.f;

/* compiled from: CountryCodePresenter.kt */
/* renamed from: ol */
public final class ol extends oe<a> {

    /* compiled from: CountryCodePresenter.kt */
    /* renamed from: ol$a */
    public static final class a implements n<ArrayList<CountryDB>> {
        a() {
        }

        public void subscribe(m<ArrayList<CountryDB>> mVar) {
            f.b(mVar, "emitter");
            Object l = g.l();
            f.a(l, "DBUtil.getCountryList()");
            mVar.onNext(l);
            mVar.onComplete();
        }
    }

    /* compiled from: CountryCodePresenter.kt */
    /* renamed from: ol$b */
    public static final class b implements r<ArrayList<CountryDB>> {
        final /* synthetic */ ol a;

        public void onComplete() {
        }

        public void onError(Throwable th) {
            f.b(th, "e");
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            f.b(bVar, "d");
        }

        b(ol olVar) {
            this.a = olVar;
        }

        /* renamed from: a */
        public void onNext(ArrayList<CountryDB> arrayList) {
            f.b(arrayList, "t");
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(arrayList);
            }
        }
    }

    /* compiled from: CountryCodePresenter.kt */
    /* renamed from: ol$c */
    public static final class c extends com.one.tomato.mvp.base.okhttp.a<ArrayList<CountryDB>> {
        final /* synthetic */ ol a;

        c(ol olVar) {
            this.a = olVar;
        }

        public void a(ArrayList<CountryDB> arrayList) {
            a a = ((a) this.a.a());
            if (a != null) {
                if (arrayList == null) {
                    f.a();
                }
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    public void e() {
    }

    public void f() {
        k.create(new a()).compose(y.a()).compose((q) y.a(c())).subscribe((r) new b(this));
    }

    public void g() {
        of.a.a().e().compose(y.a()).compose((q) y.a(c())).subscribe((r) new c(this));
    }
}
