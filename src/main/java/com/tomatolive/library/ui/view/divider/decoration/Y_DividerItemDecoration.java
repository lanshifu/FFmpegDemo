package com.tomatolive.library.ui.view.divider.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public abstract class Y_DividerItemDecoration extends ItemDecoration {
    private Context context;
    private Paint mPaint = new Paint(1);

    @Nullable
    public abstract Y_Divider getDivider(int i);

    public Y_DividerItemDecoration(Context context) {
        this.context = context;
        this.mPaint.setStyle(Style.FILL);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            Y_Divider divider = getDivider(((LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition());
            if (divider == null) {
                super.onDraw(canvas, recyclerView, state);
                return;
            }
            int convert;
            int convert2;
            int convert3;
            if (divider.getLeftSideLine().isHave()) {
                convert = Dp2Px.convert(this.context, divider.getLeftSideLine().getWidthDp());
                convert2 = Dp2Px.convert(this.context, divider.getLeftSideLine().getStartPaddingDp());
                convert3 = Dp2Px.convert(this.context, divider.getLeftSideLine().getEndPaddingDp());
                drawChildLeftVertical(childAt, canvas, recyclerView, divider.getLeftSideLine().getColor(), convert, convert2, convert3);
            }
            if (divider.getTopSideLine().isHave()) {
                convert = Dp2Px.convert(this.context, divider.getTopSideLine().getWidthDp());
                convert2 = Dp2Px.convert(this.context, divider.getTopSideLine().getStartPaddingDp());
                convert3 = Dp2Px.convert(this.context, divider.getTopSideLine().getEndPaddingDp());
                drawChildTopHorizontal(childAt, canvas, recyclerView, divider.topSideLine.getColor(), convert, convert2, convert3);
            }
            if (divider.getRightSideLine().isHave()) {
                convert = Dp2Px.convert(this.context, divider.getRightSideLine().getWidthDp());
                convert2 = Dp2Px.convert(this.context, divider.getRightSideLine().getStartPaddingDp());
                convert3 = Dp2Px.convert(this.context, divider.getRightSideLine().getEndPaddingDp());
                drawChildRightVertical(childAt, canvas, recyclerView, divider.getRightSideLine().getColor(), convert, convert2, convert3);
            }
            if (divider.getBottomSideLine().isHave()) {
                convert = Dp2Px.convert(this.context, divider.getBottomSideLine().getWidthDp());
                convert2 = Dp2Px.convert(this.context, divider.getBottomSideLine().getStartPaddingDp());
                convert3 = Dp2Px.convert(this.context, divider.getBottomSideLine().getEndPaddingDp());
                drawChildBottomHorizontal(childAt, canvas, recyclerView, divider.getBottomSideLine().getColor(), convert, convert2, convert3);
            }
        }
    }

    private void drawChildBottomHorizontal(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        if (i3 <= 0) {
            i3 = -i2;
        }
        int i5 = i4 <= 0 ? i2 : -i4;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int left = (view.getLeft() - layoutParams.leftMargin) + i3;
        i3 = (view.getRight() + layoutParams.rightMargin) + i5;
        int bottom = view.getBottom() + layoutParams.bottomMargin;
        i2 += bottom;
        this.mPaint.setColor(i);
        canvas.drawRect((float) left, (float) bottom, (float) i3, (float) i2, this.mPaint);
    }

    private void drawChildTopHorizontal(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        if (i3 <= 0) {
            i3 = -i2;
        }
        int i5 = i4 <= 0 ? i2 : -i4;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int left = (view.getLeft() - layoutParams.leftMargin) + i3;
        i3 = (view.getRight() + layoutParams.rightMargin) + i5;
        int top = view.getTop() - layoutParams.topMargin;
        i5 = top - i2;
        this.mPaint.setColor(i);
        canvas.drawRect((float) left, (float) i5, (float) i3, (float) top, this.mPaint);
    }

    private void drawChildLeftVertical(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        if (i3 <= 0) {
            i3 = -i2;
        }
        int i5 = i4 <= 0 ? i2 : -i4;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int top = (view.getTop() - layoutParams.topMargin) + i3;
        i3 = (view.getBottom() + layoutParams.bottomMargin) + i5;
        int left = view.getLeft() - layoutParams.leftMargin;
        i5 = left - i2;
        this.mPaint.setColor(i);
        canvas.drawRect((float) i5, (float) top, (float) left, (float) i3, this.mPaint);
    }

    private void drawChildRightVertical(View view, Canvas canvas, RecyclerView recyclerView, @ColorInt int i, int i2, int i3, int i4) {
        if (i3 <= 0) {
            i3 = -i2;
        }
        int i5 = i4 <= 0 ? i2 : -i4;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int top = (view.getTop() - layoutParams.topMargin) + i3;
        i3 = (view.getBottom() + layoutParams.bottomMargin) + i5;
        int right = view.getRight() + layoutParams.rightMargin;
        i2 += right;
        this.mPaint.setColor(i);
        canvas.drawRect((float) right, (float) top, (float) i2, (float) i3, this.mPaint);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        Y_Divider divider = getDivider(((LayoutParams) view.getLayoutParams()).getViewLayoutPosition());
        if (divider == null) {
            divider = new Y_DividerBuilder().create();
        }
        int i = 0;
        int convert = divider.getLeftSideLine().isHave() ? Dp2Px.convert(this.context, divider.getLeftSideLine().getWidthDp()) : 0;
        int convert2 = divider.getTopSideLine().isHave() ? Dp2Px.convert(this.context, divider.getTopSideLine().getWidthDp()) : 0;
        int convert3 = divider.getRightSideLine().isHave() ? Dp2Px.convert(this.context, divider.getRightSideLine().getWidthDp()) : 0;
        if (divider.getBottomSideLine().isHave()) {
            i = Dp2Px.convert(this.context, divider.getBottomSideLine().getWidthDp());
        }
        rect.set(convert, convert2, convert3, i);
    }
}
