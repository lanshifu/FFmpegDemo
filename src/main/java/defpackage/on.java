package defpackage;

import com.one.tomato.entity.ForgetPsSec;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.mvp.base.okhttp.a;
import com.one.tomato.utils.ad;
import com.one.tomato.utils.af;
import com.one.tomato.utils.y;
import io.reactivex.q;
import io.reactivex.r;
import java.util.Map;
import kotlin.jvm.internal.f;

/* compiled from: RegisterPresenter.kt */
/* renamed from: on */
public final class on extends om {

    /* compiled from: RegisterPresenter.kt */
    /* renamed from: on$a */
    public static final class a extends com.one.tomato.mvp.base.okhttp.a<Object> {
        final /* synthetic */ on a;

        a(on onVar) {
            this.a = onVar;
        }

        public void a(Object obj) {
            this.a.d();
            ad.a(2131755877);
        }

        public void a(ResponseThrowable responseThrowable) {
            this.a.d();
        }
    }

    /* compiled from: RegisterPresenter.kt */
    /* renamed from: on$b */
    public static final class b extends a<Object> {
        final /* synthetic */ on a;

        b(on onVar) {
            this.a = onVar;
        }

        public void a(Object obj) {
            af.b();
        }

        public void a(ResponseThrowable responseThrowable) {
            this.a.d();
        }
    }

    /* compiled from: RegisterPresenter.kt */
    /* renamed from: on$c */
    public static final class c extends a<ForgetPsSec> {
        final /* synthetic */ on a;

        c(on onVar) {
            this.a = onVar;
        }

        public void a(ForgetPsSec forgetPsSec) {
            oj.a a = ((oj.a) this.a.a());
            if (a != null) {
                a.a(forgetPsSec);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            this.a.d();
        }
    }

    public void a(String str, String str2) {
        f.b(str, "countryCode");
        f.b(str2, "phone");
        of.a.a().a(str, str2).compose((q) y.a(c())).compose(y.a()).subscribe((r) new a(this));
    }

    public void a(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().k(map).compose((q) y.a(c())).compose(y.a()).subscribe((r) new b(this));
    }

    public void b(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().l(map).compose((q) y.a(c())).compose(y.a()).subscribe((r) new c(this));
    }
}
