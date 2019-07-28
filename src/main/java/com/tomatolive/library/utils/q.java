package com.tomatolive.library.utils;

import android.content.Context;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import defpackage.sh;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.k;
import java.util.concurrent.TimeUnit;

/* compiled from: RxTimerUtils */
public class q {

    /* compiled from: RxTimerUtils */
    public interface a {
        void action(long j);
    }

    /* compiled from: RxTimerUtils */
    private static class b {
        private static final q a = new q();
    }

    /* synthetic */ q(AnonymousClass1 anonymousClass1) {
        this();
    }

    private q() {
    }

    public static q a() {
        return b.a;
    }

    public void a(Context context, long j, TimeUnit timeUnit, final a aVar) {
        if (context instanceof RxAppCompatActivity) {
            k.timer(j, timeUnit).observeOn(wd.a()).compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new sh<Long>() {
                /* renamed from: a */
                public void accept(Long l) {
                    if (aVar != null) {
                        aVar.action(l.longValue());
                    }
                }
            });
        } else {
            k.timer(j, timeUnit).observeOn(wd.a()).subscribe(new sh<Long>() {
                /* renamed from: a */
                public void accept(Long l) {
                    if (aVar != null) {
                        aVar.action(l.longValue());
                    }
                }
            });
        }
    }

    public void b(Context context, long j, TimeUnit timeUnit, final a aVar) {
        if (context instanceof RxAppCompatActivity) {
            k.timer(j, timeUnit).observeOn(xl.b()).compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new sh<Long>() {
                /* renamed from: a */
                public void accept(Long l) {
                    if (aVar != null) {
                        aVar.action(l.longValue());
                    }
                }
            });
        }
    }

    public void c(Context context, long j, TimeUnit timeUnit, final a aVar) {
        if (context instanceof RxAppCompatActivity) {
            k.timer(j, timeUnit).observeOn(wd.a()).compose(((RxAppCompatActivity) context).bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new sh<Long>() {
                /* renamed from: a */
                public void accept(Long l) {
                    if (aVar != null) {
                        aVar.action(l.longValue());
                    }
                }
            });
        }
    }
}
