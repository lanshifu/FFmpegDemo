package com.tomatolive.library.ui.adapter;

import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.MenuEntity;

public class BannedSearchAdapter extends BaseQuickAdapter<MenuEntity, BaseViewHolder> {
    public BannedSearchAdapter(int i) {
        super(i);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, MenuEntity menuEntity) {
        baseViewHolder.setText(R.id.tv_keyword, Html.fromHtml(this.mContext.getString(R.string.fq_my_live_search_key_desc, new Object[]{menuEntity.getMenuTitle()})));
    }
}
