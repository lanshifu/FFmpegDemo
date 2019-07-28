package defpackage;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.jakewharton.rxbinding2.internal.a;
import io.reactivex.k;

/* compiled from: RxView */
/* renamed from: mh */
public final class mh {
    @CheckResult
    @NonNull
    public static k<Object> a(@NonNull View view) {
        a.a(view, "view == null");
        return new mi(view);
    }
}
