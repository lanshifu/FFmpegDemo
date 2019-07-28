package com.tomatolive.library.ui.view.emptyview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.tomatolive.library.R;

public class HomeHotEmptyView extends RelativeLayout {
    public HomeHotEmptyView(Context context) {
        this(context, null);
    }

    public HomeHotEmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeHotEmptyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_empty_view_warp, this);
    }
}
