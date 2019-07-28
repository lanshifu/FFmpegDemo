package com.tomatolive.library.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.MenuEntity;
import java.util.List;

public class ActionSheetDialogAdapter extends BaseQuickAdapter<MenuEntity, BaseViewHolder> {
    public ActionSheetDialogAdapter(@LayoutRes int i, @Nullable List<MenuEntity> list) {
        super(i, list);
    }

    /* Access modifiers changed, original: protected */
    public void convert(BaseViewHolder baseViewHolder, MenuEntity menuEntity) {
        baseViewHolder.setText(R.id.tv_item_title, menuEntity.getMenuTitle()).getView(R.id.tv_item_title).setSelected(menuEntity.isSelected);
    }
}
