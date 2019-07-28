package defpackage;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ScrollView;

/* compiled from: SmartUtil */
/* renamed from: rm */
public abstract class rm {
    public static int a(View view) {
        int makeMeasureSpec;
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        if (layoutParams.height > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
        return view.getMeasuredHeight();
    }

    public static void a(@NonNull AbsListView absListView, int i) {
        if (VERSION.SDK_INT >= 19) {
            absListView.scrollListBy(i);
        } else if (absListView instanceof ListView) {
            int firstVisiblePosition = absListView.getFirstVisiblePosition();
            if (firstVisiblePosition != -1) {
                View childAt = absListView.getChildAt(0);
                if (childAt != null) {
                    ((ListView) absListView).setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i);
                }
            }
        } else {
            absListView.smoothScrollBy(i, 0);
        }
    }

    public static boolean b(View view) {
        return (view instanceof AbsListView) || (view instanceof ScrollView) || (view instanceof ScrollingView) || (view instanceof WebView) || (view instanceof NestedScrollingChild);
    }

    public static boolean c(View view) {
        return rm.b(view) || (view instanceof ViewPager) || (view instanceof NestedScrollingParent);
    }

    public static void a(View view, int i) {
        if (view instanceof ScrollView) {
            ((ScrollView) view).fling(i);
        } else if (view instanceof AbsListView) {
            if (VERSION.SDK_INT >= 21) {
                ((AbsListView) view).fling(i);
            }
        } else if (view instanceof WebView) {
            ((WebView) view).flingScroll(0, i);
        } else if (view instanceof NestedScrollView) {
            ((NestedScrollView) view).fling(i);
        } else if (view instanceof RecyclerView) {
            ((RecyclerView) view).fling(0, i);
        }
    }
}
