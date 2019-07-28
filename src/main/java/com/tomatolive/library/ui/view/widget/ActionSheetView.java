package com.tomatolive.library.ui.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.ui.adapter.ActionSheetDialogAdapter;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;
import java.util.List;

public class ActionSheetView {

    public interface ActionSheetOperateListener {
        void onCancel();

        void onOperateListener(MenuEntity menuEntity, int i);
    }

    private ActionSheetView() {
    }

    public static void showOperateCancelDialog(Context context, List<MenuEntity> list, final ActionSheetOperateListener actionSheetOperateListener) {
        final Dialog dialog = new Dialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fq_layout_actionsheet_cancel_view, new LinearLayout(context), false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_operate);
        ActionSheetDialogAdapter actionSheetDialogAdapter = new ActionSheetDialogAdapter(R.layout.fq_item_actionsheet_text, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new RVDividerLinear(context, R.color.fq_view_divider_color));
        actionSheetDialogAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(actionSheetDialogAdapter);
        actionSheetDialogAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                dialog.dismiss();
                if (actionSheetOperateListener != null) {
                    actionSheetOperateListener.onOperateListener((MenuEntity) baseQuickAdapter.getItem(i), i);
                }
            }
        });
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (actionSheetOperateListener != null) {
                    actionSheetOperateListener.onCancel();
                }
            }
        });
        inflate.setMinimumWidth(context.getResources().getDisplayMetrics().widthPixels);
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.gravity = 80;
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
    }

    public static void showFixedHeightDialog(Context context, List<MenuEntity> list, final ActionSheetOperateListener actionSheetOperateListener) {
        final Dialog dialog = new Dialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fq_layout_actionsheet_fixed_height_view, new LinearLayout(context), false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_operate);
        ActionSheetDialogAdapter actionSheetDialogAdapter = new ActionSheetDialogAdapter(R.layout.fq_item_actionsheet_text, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new RVDividerLinear(context, R.color.fq_view_divider_color));
        actionSheetDialogAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(actionSheetDialogAdapter);
        actionSheetDialogAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                dialog.dismiss();
                if (actionSheetOperateListener != null) {
                    actionSheetOperateListener.onOperateListener((MenuEntity) baseQuickAdapter.getItem(i), i);
                }
            }
        });
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (actionSheetOperateListener != null) {
                    actionSheetOperateListener.onCancel();
                }
            }
        });
        inflate.setMinimumWidth(context.getResources().getDisplayMetrics().widthPixels);
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.x = 0;
        attributes.gravity = 80;
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
    }
}
