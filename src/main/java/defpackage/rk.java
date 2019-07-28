package defpackage;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: DesignUtil */
/* renamed from: rk */
public class rk {
    public static void a(View view, qz qzVar, rc rcVar) {
        try {
            if (view instanceof CoordinatorLayout) {
                qzVar.a().e(false);
                rk.a((ViewGroup) view, rcVar);
            }
        } catch (Throwable unused) {
        }
    }

    private static void a(ViewGroup viewGroup, final rc rcVar) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof AppBarLayout) {
                ((AppBarLayout) childAt).addOnOffsetChangedListener(new OnOffsetChangedListener() {
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        rc rcVar = rcVar;
                        boolean z = false;
                        boolean z2 = i >= 0;
                        if (appBarLayout.getTotalScrollRange() + i <= 0) {
                            z = true;
                        }
                        rcVar.a(z2, z);
                    }
                });
            }
        }
    }
}
