package defpackage;

import android.content.Context;
import com.one.tomato.base.BaseActivity;
import com.one.tomato.entity.PublishInfo;
import com.one.tomato.entity.event.PublishTypeEvent;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.ui.publish.PapaPublishStatusActivity;
import com.one.tomato.ui.publish.PublishActivity;
import com.one.tomato.utils.af;
import de.greenrobot.event.EventBus;

/* compiled from: PapaPublishUtil */
/* renamed from: qb */
public class qb {
    private static qb a;
    private Context b;
    private int c = -1;
    private PublishInfo d;
    private a e;

    /* compiled from: PapaPublishUtil */
    /* renamed from: qb$a */
    public interface a {
        void a();

        void a(Context context, PublishInfo publishInfo);

        void a(boolean z);

        void b();
    }

    private qb() {
    }

    public static qb a() {
        if (a == null) {
            a = new qb();
        }
        return a;
    }

    public void a(Context context, a aVar) {
        this.b = context;
        this.e = aVar;
        e();
    }

    private void e() {
        switch (this.c) {
            case 0:
                if (this.e != null) {
                    this.e.a();
                    return;
                }
                return;
            case 1:
                if (this.e != null) {
                    this.e.a(false);
                    return;
                }
                return;
            case 2:
                if (this.e != null) {
                    this.e.b();
                    return;
                }
                return;
            case 3:
                if (this.e != null) {
                    this.e.a(this.b, this.d);
                    return;
                }
                return;
            case 4:
                if (this.e != null) {
                    this.e.a(this.b, this.d);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(PublishInfo publishInfo) {
        if (!((BaseActivity) this.b).g()) {
            if (this.c == 1) {
                a(1);
                PapaPublishStatusActivity.a(this.b, this.c, publishInfo, null);
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
        PapaPublishStatusActivity.a(this.b, this.c, this.d, null);
        if (this.e != null) {
            this.e.b();
        }
        af.b(true);
        this.c = 0;
    }

    public void a(PublishInfo publishInfo, ResponseThrowable responseThrowable) {
        this.d = publishInfo;
        a(4);
        PapaPublishStatusActivity.a(this.b, this.c, publishInfo, responseThrowable);
        if (this.e != null) {
            this.e.a(this.b, publishInfo);
        }
        this.c = 0;
    }

    public void d() {
        a(0);
        this.d = null;
        if (this.e != null) {
            this.e.a();
        }
        this.c = 0;
    }

    public void a(int i) {
        this.c = i;
        PublishTypeEvent publishTypeEvent = new PublishTypeEvent();
        publishTypeEvent.type = i;
        EventBus.getDefault().post(publishTypeEvent);
    }
}
