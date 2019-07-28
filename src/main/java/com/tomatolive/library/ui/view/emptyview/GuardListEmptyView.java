package com.tomatolive.library.ui.view.emptyview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.tomatolive.library.R;

public class GuardListEmptyView extends LinearLayout {
    public GuardListEmptyView(Context context) {
        super(context);
        initView(context);
    }

    public GuardListEmptyView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.fq_layout_empty_view_guard_list, this);
    }
}
