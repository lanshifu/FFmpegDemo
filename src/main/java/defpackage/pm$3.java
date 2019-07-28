package defpackage;

import android.content.Context;
import com.one.tomato.utils.o;
import com.tomatolive.library.a.b;

/* compiled from: TomatoLiveSDKUtils */
/* renamed from: pm$3 */
class pm$3 implements b {
    final /* synthetic */ pm a;

    pm$3(pm pmVar) {
        this.a = pmVar;
    }

    public void a(Context context) {
        o.c("直播SDK登出成功");
        this.a.d();
        this.a.a(false);
    }

    public void b(Context context) {
        o.c("直播SDK登出失败");
        this.a.d();
        this.a.a(false);
    }
}
