package defpackage;

import android.content.Context;
import com.one.tomato.base.BaseActivity;
import com.one.tomato.entity.PublishInfo;
import com.one.tomato.entity.event.PublishTypeEvent;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.ui.publish.PublishActivity;
import com.one.tomato.ui.publish.PublishStatusActivity;
import com.one.tomato.utils.af;
import de.greenrobot.event.EventBus;

/* compiled from: PublishUtil */
/* renamed from: qd */
public class qd {
    private static qd a;
    private Context b;
    private int c = -1;
    private PublishInfo d;
    private a e;

    /* compiled from: PublishUtil */
    /* renamed from: qd$a */
    public interface a {
        void a();

        void a(Context context, PublishInfo publishInfo);

        void a(boolean z);

        void b();
    }

    private qd() {
    }

    public static qd a() {
        if (a == null) {
            a = new qd();
        }
        return a;
    }

    public void a(Context context) {
        this.b = context;
    }

    public void a(PublishInfo publishInfo) {
        if (!((BaseActivity) this.b).g()) {
            if (this.c == 1) {
                a(1);
                PublishStatusActivity.a(this.b, this.c, publishInfo, null);
                if (this.e != null) {
                    this.e.a(true);
                }
            } else {
                PublishActivity.a(this.b, publishInfo);
            }
        }
    }

    public void b() {
        a(1);
        if (this.e != null) {
            this.e.a(false);
        }
    }

    public void c() {
        a(2);
        PublishStatusActivity.a(this.b, this.c, this.d, null);
        if (this.e != null) {
            this.e.b();
        }
        af.b(true);
    }

    public void a(PublishInfo publishInfo, ResponseThrowable responseThrowable) {
        this.d = publishInfo;
        a(4);
        PublishStatusActivity.a(this.b, this.c, publishInfo, responseThrowable);
        if (this.e != null) {
            this.e.a(this.b, publishInfo);
        }
    }

    public void d() {
        a(0);
        this.d = null;
        if (this.e != null) {
            this.e.a();
        }
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
        PublishTypeEvent publishTypeEvent = new PublishTypeEvent();
        publishTypeEvent.type = i;
        EventBus.getDefault().post(publishTypeEvent);
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
