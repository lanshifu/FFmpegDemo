package defpackage;

import android.os.Handler;
import android.os.Message;
import com.contrarywind.view.WheelView;
import com.contrarywind.view.WheelView.ACTION;
import com.tomatolive.library.http.exception.ExceptionEngine;

/* compiled from: MessageHandler */
/* renamed from: ed */
public final class ed extends Handler {
    private final WheelView a;

    public ed(WheelView wheelView) {
        this.a = wheelView;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1000) {
            this.a.invalidate();
        } else if (i == ExceptionEngine.SERVER_ERROR) {
            this.a.a(ACTION.FLING);
        } else if (i == 3000) {
            this.a.b();
        }
    }
}
