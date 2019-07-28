package defpackage;

import android.view.View;
import android.view.View.OnClickListener;
import com.jakewharton.rxbinding2.internal.Notification;
import com.jakewharton.rxbinding2.internal.a;
import io.reactivex.k;
import io.reactivex.r;

/* compiled from: ViewClickObservable */
/* renamed from: mi */
final class mi extends k<Object> {
    private final View a;

    /* compiled from: ViewClickObservable */
    /* renamed from: mi$a */
    static final class a extends wb implements OnClickListener {
        private final View a;
        private final r<? super Object> b;

        a(View view, r<? super Object> rVar) {
            this.a = view;
            this.b = rVar;
        }

        public void onClick(View view) {
            if (!isDisposed()) {
                this.b.onNext(Notification.INSTANCE);
            }
        }

        /* Access modifiers changed, original: protected */
        public void onDispose() {
            this.a.setOnClickListener(null);
        }
    }

    mi(View view) {
        this.a = view;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Object> rVar) {
        if (a.a(rVar)) {
            a aVar = new a(this.a, rVar);
            rVar.onSubscribe(aVar);
            this.a.setOnClickListener(aVar);
        }
    }
}
