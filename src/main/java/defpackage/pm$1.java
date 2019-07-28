package defpackage;

import android.content.Context;
import com.one.tomato.mvp.ui.login.view.LoginActivity;
import com.one.tomato.ui.livepay.IncomeActivity;
import com.one.tomato.ui.recharge.ui.RechargeActivity;
import com.tomatolive.library.a.d;

/* compiled from: TomatoLiveSDKUtils */
/* renamed from: pm$1 */
class pm$1 implements d {
    final /* synthetic */ pm a;

    pm$1(pm pmVar) {
        this.a = pmVar;
    }

    public void a(Context context) {
        RechargeActivity.a(context);
    }

    public void b(Context context) {
        this.a.a(false);
        LoginActivity.a.a(context);
    }

    public void c(Context context) {
        IncomeActivity.a(context);
    }
}
