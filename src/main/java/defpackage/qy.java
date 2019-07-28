package defpackage;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/* compiled from: RefreshInternal */
/* renamed from: qy */
public interface qy extends rh {
    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    int a(@NonNull ra raVar, boolean z);

    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    void a(float f, int i, int i2);

    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    void a(@NonNull qz qzVar, int i, int i2);

    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    void a(@NonNull ra raVar, int i, int i2);

    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    void a(boolean z, float f, int i, int i2, int i3);

    boolean a();

    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    void b(@NonNull ra raVar, int i, int i2);

    @NonNull
    SpinnerStyle getSpinnerStyle();

    @NonNull
    View getView();

    @RestrictTo({Scope.LIBRARY, Scope.LIBRARY_GROUP, Scope.SUBCLASSES})
    void setPrimaryColors(@ColorInt int... iArr);
}
