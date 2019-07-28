package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.f;

/* compiled from: _Ranges.kt */
/* renamed from: yj */
class yj extends yi {
    public static final int c(int i, int i2) {
        return i < i2 ? i2 : i;
    }

    public static final int d(int i, int i2) {
        return i > i2 ? i2 : i;
    }

    public static final int a(yf yfVar, yb ybVar) {
        f.b(yfVar, "$this$random");
        f.b(ybVar, "random");
        try {
            return yc.a(ybVar, yfVar);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public static final yd a(int i, int i2) {
        return yd.a.a(i, i2, -1);
    }

    public static final yf b(int i, int i2) {
        if (i2 <= CheckView.UNCHECKED) {
            return yf.b.a();
        }
        return new yf(i, i2 - 1);
    }

    public static final int a(int i, int i2, int i3) {
        if (i2 > i3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot coerce value to an empty range: maximum ");
            stringBuilder.append(i3);
            stringBuilder.append(" is less than minimum ");
            stringBuilder.append(i2);
            stringBuilder.append('.');
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (i < i2) {
            return i2;
        } else {
            return i > i3 ? i3 : i;
        }
    }
}
