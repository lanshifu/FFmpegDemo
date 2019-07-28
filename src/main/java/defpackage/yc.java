package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import kotlin.jvm.internal.f;

/* compiled from: Random.kt */
/* renamed from: yc */
public final class yc {
    public static final int a(int i, int i2) {
        return (i >>> (32 - i2)) & ((-i2) >> 31);
    }

    public static final int a(yb ybVar, yf yfVar) {
        f.b(ybVar, "$this$nextInt");
        f.b(yfVar, "range");
        if (yfVar.e()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Cannot get random in empty range: ");
            stringBuilder.append(yfVar);
            throw new IllegalArgumentException(stringBuilder.toString());
        } else if (yfVar.b() < Filter.MAX) {
            return ybVar.a(yfVar.a(), yfVar.b() + 1);
        } else {
            if (yfVar.a() > CheckView.UNCHECKED) {
                return ybVar.a(yfVar.a() - 1, yfVar.b()) + 1;
            }
            return ybVar.b();
        }
    }

    public static final void b(int i, int i2) {
        if ((i2 > i ? 1 : null) == null) {
            throw new IllegalArgumentException(yc.a(Integer.valueOf(i), Integer.valueOf(i2)).toString());
        }
    }

    public static final String a(Object obj, Object obj2) {
        f.b(obj, "from");
        f.b(obj2, "until");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Random range is empty: [");
        stringBuilder.append(obj);
        stringBuilder.append(", ");
        stringBuilder.append(obj2);
        stringBuilder.append(").");
        return stringBuilder.toString();
    }
}
