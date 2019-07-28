package defpackage;

import com.one.tomato.entity.db.LoginInfo;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.utils.af;
import com.one.tomato.utils.g;
import com.one.tomato.utils.y;
import defpackage.oj.a;
import io.reactivex.q;
import io.reactivex.r;
import java.util.Map;
import kotlin.jvm.internal.f;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: LoginPresenter.kt */
/* renamed from: om */
public class om extends oe<a> {

    /* compiled from: LoginPresenter.kt */
    /* renamed from: om$a */
    public static final class a extends com.one.tomato.mvp.base.okhttp.a<LoginInfo> {
        final /* synthetic */ om a;
        final /* synthetic */ boolean b;

        a(om omVar, boolean z) {
            this.a = omVar;
            this.b = z;
        }

        public void a(LoginInfo loginInfo) {
            g.a(loginInfo);
            defpackage.oj.a a = ((defpackage.oj.a) this.a.a());
            if (a != null) {
                a.a(loginInfo);
            }
            if (this.b) {
                af.b();
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            this.a.d();
        }
    }

    public void e() {
    }

    public void a(String str, Map<String, Object> map, boolean z) {
        f.b(str, OnNativeInvokeListener.ARG_URL);
        f.b(map, "paramsMap");
        of.a.a().a(str, (Map) map).compose((q) y.a(c())).compose(y.a()).subscribe((r) new a(this, z));
    }
}
