package defpackage;

import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.utils.y;
import defpackage.ok.a;
import io.reactivex.q;
import io.reactivex.r;
import java.util.Map;
import kotlin.jvm.internal.f;

/* compiled from: RegisterUpdatePresenter.kt */
/* renamed from: oo */
public final class oo extends oe<a> {

    /* compiled from: RegisterUpdatePresenter.kt */
    /* renamed from: oo$a */
    public static final class a extends com.one.tomato.mvp.base.okhttp.a<Object> {
        final /* synthetic */ oo a;

        public void a(ResponseThrowable responseThrowable) {
        }

        a(oo ooVar) {
            this.a = ooVar;
        }

        public void a(Object obj) {
            defpackage.ok.a a = ((defpackage.ok.a) this.a.a());
            if (a != null) {
                a.f_();
            }
        }
    }

    public void e() {
    }

    public void a(Map<String, Object> map) {
        f.b(map, "map");
        of.a.a().m(map).compose((q) y.a(c())).compose(y.a()).subscribe((r) new a(this));
    }
}
