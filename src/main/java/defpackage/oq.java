package defpackage;

import com.one.tomato.entity.PostList;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.utils.g;
import com.one.tomato.utils.o;
import com.one.tomato.utils.y;
import defpackage.op.a;
import io.reactivex.q;
import io.reactivex.r;
import java.util.ArrayList;

/* compiled from: NewPaPaPresenterMvp.kt */
/* renamed from: oq */
public final class oq extends oe<a> {

    /* compiled from: NewPaPaPresenterMvp.kt */
    /* renamed from: oq$a */
    public static final class a extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ oq a;

        a(oq oqVar) {
            this.a = oqVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            defpackage.op.a a = ((defpackage.op.a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("拍拍 -獲取數據失敗=");
            String str = null;
            stringBuilder.append(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            o.a(stringBuilder.toString());
            defpackage.op.a a = ((defpackage.op.a) this.a.a());
            if (a != null) {
                if (responseThrowable != null) {
                    str = responseThrowable.getThrowableMessage();
                }
                a.a(str);
            }
        }
    }

    public void e() {
    }

    public void a(int i, int i2) {
        of.a.a().a(i, i2, g.d()).compose((q) y.a(c())).compose(y.a()).subscribe((r) new a(this));
    }
}
