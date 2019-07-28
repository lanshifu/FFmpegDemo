package com.tomatolive.library.utils;

import android.content.Context;
import android.view.View;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import defpackage.mh;
import defpackage.sh;
import java.util.concurrent.TimeUnit;

/* compiled from: RxViewUtils */
public class r {

    /* compiled from: RxViewUtils */
    public interface a {
        void action(Object obj);
    }

    /* compiled from: RxViewUtils */
    private static class b {
        private static final r a = new r();
    }

    /* synthetic */ r(AnonymousClass1 anonymousClass1) {
        this();
    }

    private r() {
    }

    public static r a() {
        return b.a;
    }

    public void a(View view, int i, a aVar) {
        a(view, (long) i, TimeUnit.MILLISECONDS, aVar);
    }

    public void a(View view, long j, TimeUnit timeUnit, final a aVar) {
        if (view != null) {
            Context context = view.getContext();
            if (context instanceof RxAppCompatActivity) {
                mh.a(view).throttleFirst(j, timeUnit).compose(((RxAppCompatActivity) context).bindToLifecycle()).subscribe(new sh<Object>() {
                    public void accept(Object obj) {
                        if (aVar != null) {
                            aVar.action(obj);
                        }
                    }
                });
            } else {
                mh.a(view).throttleFirst(j, timeUnit).subscribe(new sh<Object>() {
                    public void accept(Object obj) {
                        if (aVar != null) {
                            aVar.action(obj);
                        }
                    }
                });
            }
        }
    }
}
