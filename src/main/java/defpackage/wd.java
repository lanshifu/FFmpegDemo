package defpackage;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.s;
import java.util.concurrent.Callable;

/* compiled from: AndroidSchedulers */
/* renamed from: wd */
public final class wd {
    private static final s a = wc.a(new 1());

    /* compiled from: AndroidSchedulers */
    /* renamed from: wd$1 */
    static class 1 implements Callable<s> {
        1() {
        }

        /* renamed from: a */
        public s call() throws Exception {
            return a.a;
        }
    }

    /* compiled from: AndroidSchedulers */
    /* renamed from: wd$a */
    private static final class a {
        static final s a = new we(new Handler(Looper.getMainLooper()), false);
    }

    public static s a() {
        return wc.a(a);
    }

    private wd() {
        throw new AssertionError("No instances.");
    }
}
