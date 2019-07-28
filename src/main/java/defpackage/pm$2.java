package defpackage;

import android.content.Context;
import com.one.tomato.utils.o;
import com.tomatolive.library.a.a;

/* compiled from: TomatoLiveSDKUtils */
/* renamed from: pm$2 */
class pm$2 implements a {
    final /* synthetic */ pm a;

    pm$2(pm pmVar) {
        this.a = pmVar;
    }

    public void a(Context context) {
        o.c("直播SDK登录成功");
        this.a.a(true);
    }

    public void b(Context context) {
        o.c("直播SDK登录失败");
        this.a.a(false);
    }
}
