package defpackage;

import android.app.Activity;
import com.luck.picture.lib.permissions.b;
import com.one.tomato.entity.Ad;
import com.one.tomato.entity.db.SystemParam;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.mvp.ui.start.view.SplashActivity;
import com.one.tomato.utils.g;
import defpackage.ow.a;
import io.reactivex.r;
import kotlin.TypeCastException;
import kotlin.jvm.internal.f;

/* compiled from: SplashPresenterMvp.kt */
/* renamed from: ox */
public final class ox extends oe<a> {

    /* compiled from: SplashPresenterMvp.kt */
    /* renamed from: ox$b */
    public static final class b implements r<Boolean> {
        final /* synthetic */ ox a;

        public void onComplete() {
        }

        public void onError(Throwable th) {
            f.b(th, "e");
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            f.b(bVar, "d");
        }

        b(ox oxVar) {
            this.a = oxVar;
        }

        public /* synthetic */ void onNext(Object obj) {
            a(((Boolean) obj).booleanValue());
        }

        public void a(boolean z) {
            a a;
            if (z) {
                a = ((a) this.a.a());
                if (a != null) {
                    a.g_();
                    return;
                }
                return;
            }
            a = ((a) this.a.a());
            if (a != null) {
                SplashActivity splashActivity = (SplashActivity) a;
                if (splashActivity != null) {
                    splashActivity.a(null, true);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.one.tomato.mvp.ui.start.view.SplashActivity");
        }
    }

    /* compiled from: SplashPresenterMvp.kt */
    /* renamed from: ox$a */
    public static final class a extends com.one.tomato.mvp.base.okhttp.a<Ad> {
        public void a(ResponseThrowable responseThrowable) {
        }

        a() {
        }

        public void a(Ad ad) {
            g.a(ad);
        }
    }

    /* compiled from: SplashPresenterMvp.kt */
    /* renamed from: ox$c */
    public static final class c extends com.one.tomato.mvp.base.okhttp.a<SystemParam> {
        public void a(ResponseThrowable responseThrowable) {
        }

        c() {
        }

        public void a(SystemParam systemParam) {
            g.a(systemParam);
        }
    }

    public void e() {
    }

    public void f() {
        com.one.tomato.mvp.base.view.a a = a();
        if (a != null) {
            new b((Activity) a).b(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"}).subscribe((r) new b(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Activity");
    }

    public void g() {
        of.a.a().a().subscribeOn(xl.b()).observeOn(xl.b()).subscribe((r) new c());
    }

    public void h() {
        of.a.a().b().subscribeOn(xl.b()).observeOn(xl.b()).subscribe((r) new a());
    }
}
