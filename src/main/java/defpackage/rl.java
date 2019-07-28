package defpackage;

import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: ScrollBoundaryUtil */
/* renamed from: rl */
public class rl {
    public static boolean a(@NonNull View view, PointF pointF) {
        if (rl.a(view) && view.getVisibility() == 0) {
            return false;
        }
        if ((view instanceof ViewGroup) && pointF != null) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            while (childCount > 0) {
                View childAt = viewGroup.getChildAt(childCount - 1);
                if (!rl.a(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    childCount--;
                } else if ("fixed".equals(childAt.getTag())) {
                    return false;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean a = rl.a(childAt, pointF);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return a;
                }
            }
        }
        return true;
    }

    public static boolean a(@NonNull View view, PointF pointF, boolean z) {
        boolean z2 = false;
        if (rl.b(view) && view.getVisibility() == 0) {
            return false;
        }
        if (!(!(view instanceof ViewGroup) || pointF == null || rm.b(view))) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            PointF pointF2 = new PointF();
            int i = 0;
            while (i < childCount) {
                View childAt = viewGroup.getChildAt(i);
                if (!rl.a(viewGroup, childAt, pointF.x, pointF.y, pointF2)) {
                    i++;
                } else if ("fixed".equals(childAt.getTag())) {
                    return false;
                } else {
                    pointF.offset(pointF2.x, pointF2.y);
                    boolean a = rl.a(childAt, pointF, z);
                    pointF.offset(-pointF2.x, -pointF2.y);
                    return a;
                }
            }
        }
        if (z || rl.a(view)) {
            z2 = true;
        }
        return z2;
    }

    public static boolean a(@NonNull View view) {
        if (VERSION.SDK_INT >= 14) {
            return view.canScrollVertically(-1);
        }
        boolean z = true;
        if (view instanceof AbsListView) {
            ViewGroup viewGroup = (ViewGroup) view;
            AbsListView absListView = (AbsListView) view;
            if (viewGroup.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && viewGroup.getChildAt(0).getTop() >= view.getPaddingTop())) {
                z = false;
            }
            return z;
        }
        if (view.getScrollY() <= 0) {
            z = false;
        }
        return z;
    }

    /* JADX WARNING: Missing block: B:9:0x002b, code skipped:
            if (r0.getChildAt(r4).getBottom() <= r5.getPaddingBottom()) goto L_0x002e;
     */
    public static boolean b(@android.support.annotation.NonNull android.view.View r5) {
        /*
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 1;
        r2 = 14;
        if (r0 >= r2) goto L_0x0039;
    L_0x0007:
        r0 = r5 instanceof android.widget.AbsListView;
        r2 = 0;
        if (r0 == 0) goto L_0x0030;
    L_0x000c:
        r0 = r5;
        r0 = (android.view.ViewGroup) r0;
        r3 = r5;
        r3 = (android.widget.AbsListView) r3;
        r4 = r0.getChildCount();
        if (r4 <= 0) goto L_0x002e;
    L_0x0018:
        r3 = r3.getLastVisiblePosition();
        r4 = r4 - r1;
        if (r3 < r4) goto L_0x002f;
    L_0x001f:
        r0 = r0.getChildAt(r4);
        r0 = r0.getBottom();
        r5 = r5.getPaddingBottom();
        if (r0 <= r5) goto L_0x002e;
    L_0x002d:
        goto L_0x002f;
    L_0x002e:
        r1 = 0;
    L_0x002f:
        return r1;
    L_0x0030:
        r5 = r5.getScrollY();
        if (r5 >= 0) goto L_0x0037;
    L_0x0036:
        goto L_0x0038;
    L_0x0037:
        r1 = 0;
    L_0x0038:
        return r1;
    L_0x0039:
        r5 = r5.canScrollVertically(r1);
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rl.b(android.view.View):boolean");
    }

    public static boolean a(@NonNull View view, @NonNull View view2, float f, float f2, PointF pointF) {
        if (view2.getVisibility() != 0) {
            return false;
        }
        float[] fArr = new float[]{f, f2};
        fArr[0] = fArr[0] + ((float) (view.getScrollX() - view2.getLeft()));
        fArr[1] = fArr[1] + ((float) (view.getScrollY() - view2.getTop()));
        boolean z = fArr[0] >= CropImageView.DEFAULT_ASPECT_RATIO && fArr[1] >= CropImageView.DEFAULT_ASPECT_RATIO && fArr[0] < ((float) view2.getWidth()) && fArr[1] < ((float) view2.getHeight());
        if (z && pointF != null) {
            pointF.set(fArr[0] - f, fArr[1] - f2);
        }
        return z;
    }
}
