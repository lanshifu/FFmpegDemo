package defpackage;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.jakewharton.rxbinding2.internal.a;

/* compiled from: RxTextView */
/* renamed from: mj */
public final class mj {
    @CheckResult
    @NonNull
    public static mg<CharSequence> a(@NonNull TextView textView) {
        a.a(textView, "view == null");
        return new mk(textView);
    }
}
