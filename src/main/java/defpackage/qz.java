package defpackage;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/* compiled from: RefreshKernel */
/* renamed from: qz */
public interface qz {
    ValueAnimator a(int i);

    qz a(int i, boolean z);

    qz a(@NonNull RefreshState refreshState);

    qz a(@NonNull qy qyVar, int i);

    qz a(@NonNull qy qyVar, boolean z);

    qz a(boolean z);

    @NonNull
    ra a();

    qz b();

    qz b(int i);
}
