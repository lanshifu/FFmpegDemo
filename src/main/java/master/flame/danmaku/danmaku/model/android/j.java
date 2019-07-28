package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import defpackage.zf;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ViewCacheStuffer */
public abstract class j<VH extends a> extends b {
    public static final int CACHE_VIEW_TYPE = -3;
    public static final int DRAW_VIEW_TYPE = -3;
    public static final int INVALID_TYPE = -1;
    public static final int MEASURE_VIEW_TYPE = -2;
    private final int mMaximumHeightPixels = -1;
    private final int mMaximumWidthPixels = -1;
    private SparseArray<List<VH>> mViewHolderArray = new SparseArray();

    /* compiled from: ViewCacheStuffer */
    public static abstract class a {
        protected final View itemView;

        public a(View view) {
            if (view != null) {
                this.itemView = view;
                return;
            }
            throw new IllegalArgumentException("itemView may not be null");
        }

        public void measure(int i, int i2) {
            this.itemView.measure(i, i2);
        }

        public int getMeasureWidth() {
            return this.itemView.getMeasuredWidth();
        }

        public int getMeasureHeight() {
            return this.itemView.getMeasuredHeight();
        }

        public void layout(int i, int i2, int i3, int i4) {
            this.itemView.layout(i, i2, i3, i4);
        }

        public void draw(Canvas canvas, master.flame.danmaku.danmaku.model.android.a.a aVar) {
            this.itemView.draw(canvas);
        }
    }

    public void clearCaches() {
    }

    public int getItemViewType(int i, zf zfVar) {
        return 0;
    }

    public abstract void onBindViewHolder(int i, VH vh, zf zfVar, master.flame.danmaku.danmaku.model.android.a.a aVar, TextPaint textPaint);

    public abstract VH onCreateViewHolder(int i);

    public void measure(zf zfVar, TextPaint textPaint, boolean z) {
        int itemViewType = getItemViewType(zfVar.q, zfVar);
        List list = (List) this.mViewHolderArray.get(itemViewType);
        if (list == null) {
            list = new ArrayList();
            list.add(onCreateViewHolder(itemViewType));
            list.add(onCreateViewHolder(itemViewType));
            list.add(onCreateViewHolder(itemViewType));
            this.mViewHolderArray.put(itemViewType, list);
        }
        a aVar = (a) list.get(0);
        onBindViewHolder(itemViewType, aVar, zfVar, null, textPaint);
        aVar.measure(MeasureSpec.makeMeasureSpec(this.mMaximumWidthPixels, CheckView.UNCHECKED), MeasureSpec.makeMeasureSpec(this.mMaximumHeightPixels, CheckView.UNCHECKED));
        aVar.layout(0, 0, aVar.getMeasureWidth(), aVar.getMeasureHeight());
        zfVar.n = (float) aVar.getMeasureWidth();
        zfVar.o = (float) aVar.getMeasureHeight();
    }

    public void releaseResource(zf zfVar) {
        super.releaseResource(zfVar);
        zfVar.d = null;
    }

    public void drawDanmaku(zf zfVar, Canvas canvas, float f, float f2, boolean z, master.flame.danmaku.danmaku.model.android.a.a aVar) {
        a aVar2;
        int itemViewType = getItemViewType(zfVar.q, zfVar);
        List list = (List) this.mViewHolderArray.get(itemViewType);
        Object obj = 1;
        if (list != null) {
            aVar2 = (a) list.get(z ? 1 : 2);
        } else {
            aVar2 = null;
        }
        if (aVar2 != null) {
            aVar.b(z);
            TextPaint a = aVar.a(zfVar, z);
            aVar.a(zfVar, a, false);
            onBindViewHolder(itemViewType, aVar2, zfVar, aVar, a);
            aVar2.measure(MeasureSpec.makeMeasureSpec(Math.round(zfVar.n), 1073741824), MeasureSpec.makeMeasureSpec(Math.round(zfVar.o), 1073741824));
            if (z) {
                obj = null;
            } else {
                canvas.save();
                canvas.translate(f, f2);
            }
            if (zfVar.i != 0) {
                float f3 = (zfVar.o + f2) - ((float) aVar.c);
                canvas.drawLine(f, f3, f + zfVar.n, f3, aVar.c(zfVar));
            }
            if (zfVar.k != 0) {
                canvas.drawRect(f, f2, f + zfVar.n, f2 + zfVar.o, aVar.b(zfVar));
            }
            aVar2.layout(0, 0, (int) zfVar.n, (int) zfVar.o);
            aVar2.draw(canvas, aVar);
            if (obj != null) {
                canvas.restore();
            }
        }
    }
}
