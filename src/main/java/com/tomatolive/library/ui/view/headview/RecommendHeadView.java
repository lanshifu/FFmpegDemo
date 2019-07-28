package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;

public class RecommendHeadView extends LinearLayout {
    private TextView tvText;

    public RecommendHeadView(Context context) {
        this(context, null);
    }

    public RecommendHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecommendHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_empty_view_recommend, this);
        this.tvText = (TextView) findViewById(R.id.tv_your_recommend);
    }

    public void hideRecommendTextView(boolean z) {
        this.tvText.setVisibility(z ? 4 : 0);
    }
}
