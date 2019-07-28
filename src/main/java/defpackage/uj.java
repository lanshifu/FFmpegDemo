package defpackage;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;
import com.zzhoujay.richtext.spans.b;
import com.zzhoujay.richtext.spans.d;

/* compiled from: LongClickableLinkMovementMethod */
/* renamed from: uj */
public class uj extends LinkMovementMethod {
    private long a;

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1 && action != 0) {
            return super.onTouchEvent(textView, spannable, motionEvent);
        }
        int x = (int) motionEvent.getX();
        x = (x - textView.getTotalPaddingLeft()) + textView.getScrollX();
        int y = (((int) motionEvent.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
        Layout layout = textView.getLayout();
        y = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
        d[] dVarArr = (d[]) spannable.getSpans(y, y, d.class);
        if (dVarArr.length != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            Object obj = dVarArr[0];
            int spanStart = spannable.getSpanStart(obj);
            int spanEnd = spannable.getSpanEnd(obj);
            b[] bVarArr = (b[]) spannable.getSpans(spanStart, spanEnd, b.class);
            if (bVarArr.length > 0) {
                if (!bVarArr[0].a(x)) {
                    Selection.removeSelection(spannable);
                    return false;
                }
            } else if (y < layout.getOffsetToLeftOf(spanStart) || y > layout.getOffsetToLeftOf(spanEnd + 1)) {
                Selection.removeSelection(spannable);
                return false;
            }
            if (action != 1) {
                Selection.setSelection(spannable, spanStart, spanEnd);
            } else if (currentTimeMillis - this.a <= 500) {
                obj.onClick(textView);
            } else if (!obj.a(textView)) {
                obj.onClick(textView);
            }
            this.a = currentTimeMillis;
            return true;
        }
        Selection.removeSelection(spannable);
        return false;
    }
}
