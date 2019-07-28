package defpackage;

import android.os.Looper;
import io.reactivex.disposables.b;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: MainThreadDisposable */
/* renamed from: wb */
public abstract class wb implements b {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    /* compiled from: MainThreadDisposable */
    /* renamed from: wb$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            wb.this.onDispose();
        }
    }

    public abstract void onDispose();

    public static void verifyMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected to be called on the main thread but was ");
            stringBuilder.append(Thread.currentThread().getName());
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    public final boolean isDisposed() {
        return this.unsubscribed.get();
    }

    public final void dispose() {
        if (!this.unsubscribed.compareAndSet(false, true)) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            onDispose();
        } else {
            wd.a().a(new 1());
        }
    }
}
