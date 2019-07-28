package com.tomatolive.library.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LabelEntity;
import java.util.List;

public class LabelMenuAdapter extends BaseQuickAdapter<LabelEntity, BaseViewHolder> {
    private int checkItemPosition = 0;

    public LabelMenuAdapter(int i) {
        super(i);
    }

    public LabelMenuAdapter(int i, @Nullable List<LabelEntity> list) {
        super(i, list);
    }

    public void setCheckItem(int i) {
        this.checkItemPosition = i;
        notifyDataSetChanged();
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, LabelEntity labelEntity) {
        int adapterPosition = baseViewHolder.getAdapterPosition();
        baseViewHolder.setText(R.id.tv_menu_title, labelEntity.name);
        fillValue(adapterPosition, (TextView) baseViewHolder.getView(R.id.tv_menu_title));
    }

    private void fillValue(int i, TextView textView) {
        if (this.checkItemPosition != -1) {
            textView.setSelected(this.checkItemPosition == i);
        }
    }
}
