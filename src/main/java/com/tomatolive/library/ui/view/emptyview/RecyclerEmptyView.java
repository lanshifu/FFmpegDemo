package com.tomatolive.library.ui.view.emptyview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;

public class RecyclerEmptyView extends LinearLayout {
    private int emptyType;
    private Context mContext;
    private TextView tvTextTips;

    public RecyclerEmptyView(Context context) {
        this(context, null, 0);
    }

    public RecyclerEmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public RecyclerEmptyView(Context context, int i) {
        this(context, null, i);
    }

    public RecyclerEmptyView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, 0, i);
    }

    public RecyclerEmptyView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.emptyType = i2;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_empty_view, this);
        TextView textView = (TextView) findViewById(R.id.tv_empty_text);
        this.tvTextTips = (TextView) findViewById(R.id.tv_text_tips);
        textView.setText(getEmptyText());
    }

    private String getEmptyText() {
        switch (this.emptyType) {
            case 30:
                this.tvTextTips.setVisibility(8);
                return this.mContext.getString(R.string.fq_text_empty_tips_anchor);
            case 31:
                this.tvTextTips.setVisibility(8);
                return this.mContext.getString(R.string.fq_text_empty_tips_live);
            default:
                this.tvTextTips.setVisibility(0);
                return this.mContext.getString(R.string.fq_text_list_empty);
        }
    }
}
