package com.tomatolive.library.ui.view.dialog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tomatolive.library.R;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.ui.adapter.ActionSheetDialogAdapter;
import com.tomatolive.library.ui.view.divider.RVDividerLinear;

public class LiveActionBottomDialog extends BaseBottomDialogFragment {
    public static final int LIVE_ACTION_BANNED = 2;
    private static final String LIVE_ACTION_BANNED_STR = "isBanned";
    public static final int LIVE_ACTION_CTRL = 1;
    private static final String LIVE_ACTION_CTRL_STR = "isCtrl";
    public static final int LIVE_ACTION_KICK_OUT = 4;
    private static final String LIVE_ACTION_ROLE = "role";
    public static final int LIVE_ACTION_SHIELDED = 3;
    private static final String LIVE_ACTION_SHIELDED_STR = "isShielded";
    private OnLiveActionListener onLiveActionListener;

    public interface OnLiveActionListener {
        void onLiveAction(int i, boolean z);
    }

    public static LiveActionBottomDialog create(String str, OnLiveActionListener onLiveActionListener) {
        LiveActionBottomDialog liveActionBottomDialog = new LiveActionBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putString(LIVE_ACTION_ROLE, str);
        liveActionBottomDialog.setArguments(bundle);
        liveActionBottomDialog.setOnLiveActionListener(onLiveActionListener);
        return liveActionBottomDialog;
    }

    public static LiveActionBottomDialog create(String str, boolean z, OnLiveActionListener onLiveActionListener) {
        LiveActionBottomDialog liveActionBottomDialog = new LiveActionBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putString(LIVE_ACTION_ROLE, str);
        bundle.putBoolean(LIVE_ACTION_SHIELDED_STR, z);
        liveActionBottomDialog.setArguments(bundle);
        liveActionBottomDialog.setOnLiveActionListener(onLiveActionListener);
        return liveActionBottomDialog;
    }

    public static LiveActionBottomDialog create(String str, boolean z, boolean z2, OnLiveActionListener onLiveActionListener) {
        LiveActionBottomDialog liveActionBottomDialog = new LiveActionBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putString(LIVE_ACTION_ROLE, str);
        bundle.putBoolean(LIVE_ACTION_SHIELDED_STR, z);
        bundle.putBoolean(LIVE_ACTION_BANNED_STR, z2);
        liveActionBottomDialog.setArguments(bundle);
        liveActionBottomDialog.setOnLiveActionListener(onLiveActionListener);
        return liveActionBottomDialog;
    }

    public static LiveActionBottomDialog create(String str, boolean z, boolean z2, boolean z3, OnLiveActionListener onLiveActionListener) {
        LiveActionBottomDialog liveActionBottomDialog = new LiveActionBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putString(LIVE_ACTION_ROLE, str);
        bundle.putBoolean(LIVE_ACTION_CTRL_STR, z);
        bundle.putBoolean(LIVE_ACTION_SHIELDED_STR, z2);
        bundle.putBoolean(LIVE_ACTION_BANNED_STR, z3);
        liveActionBottomDialog.setArguments(bundle);
        liveActionBottomDialog.setOnLiveActionListener(onLiveActionListener);
        return liveActionBottomDialog;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutRes() {
        return R.layout.fq_dialog_live_action;
    }

    /* Access modifiers changed, original: protected */
    public void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_operate);
        ActionSheetDialogAdapter actionSheetDialogAdapter = new ActionSheetDialogAdapter(R.layout.fq_item_actionsheet_text, getMenuList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        recyclerView.addItemDecoration(new RVDividerLinear(this.mContext, R.color.fq_view_divider_color));
        actionSheetDialogAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(actionSheetDialogAdapter);
        actionSheetDialogAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (LiveActionBottomDialog.this.onLiveActionListener != null) {
                    LiveActionBottomDialog.this.dismiss();
                    MenuEntity menuEntity = (MenuEntity) baseQuickAdapter.getItem(i);
                    if (menuEntity != null) {
                        LiveActionBottomDialog.this.onLiveActionListener.onLiveAction(menuEntity.menuType, menuEntity.isSelected ^ 1);
                    }
                }
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new -$$Lambda$LiveActionBottomDialog$V8-YEHgFxfdqG2fO7_0sNk8sNpI(this));
    }

    private java.util.List<com.tomatolive.library.model.MenuEntity> getMenuList() {
        /*
        r9 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = "role";
        r1 = r9.getArgumentsString(r1);
        r2 = "isCtrl";
        r2 = r9.getArgumentsBoolean(r2);
        r3 = "isBanned";
        r3 = r9.getArgumentsBoolean(r3);
        r4 = "isShielded";
        r4 = r9.getArgumentsBoolean(r4);
        r5 = r1.hashCode();
        r6 = 1;
        r7 = 2;
        r8 = 3;
        switch(r5) {
            case 49: goto L_0x0046;
            case 50: goto L_0x003c;
            case 51: goto L_0x0032;
            case 52: goto L_0x0027;
            case 53: goto L_0x0028;
            default: goto L_0x0027;
        };
    L_0x0027:
        goto L_0x0050;
    L_0x0028:
        r5 = "5";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0050;
    L_0x0030:
        r1 = 0;
        goto L_0x0051;
    L_0x0032:
        r5 = "3";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0050;
    L_0x003a:
        r1 = 2;
        goto L_0x0051;
    L_0x003c:
        r5 = "2";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0050;
    L_0x0044:
        r1 = 3;
        goto L_0x0051;
    L_0x0046:
        r5 = "1";
        r1 = r1.equals(r5);
        if (r1 == 0) goto L_0x0050;
    L_0x004e:
        r1 = 1;
        goto L_0x0051;
    L_0x0050:
        r1 = -1;
    L_0x0051:
        r5 = 4;
        switch(r1) {
            case 0: goto L_0x00e4;
            case 1: goto L_0x00a0;
            case 2: goto L_0x006e;
            case 3: goto L_0x005b;
            default: goto L_0x0055;
        };
    L_0x0055:
        if (r4 == 0) goto L_0x00ff;
    L_0x0057:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded_cancel;
        goto L_0x0101;
    L_0x005b:
        if (r4 == 0) goto L_0x0060;
    L_0x005d:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded_cancel;
        goto L_0x0062;
    L_0x0060:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded;
    L_0x0062:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r8, r4);
        r0.add(r1);
        return r0;
    L_0x006e:
        if (r3 == 0) goto L_0x0073;
    L_0x0070:
        r1 = com.tomatolive.library.R.string.btn_cancel_banned;
        goto L_0x0075;
    L_0x0073:
        r1 = com.tomatolive.library.R.string.btn_banned;
    L_0x0075:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r7, r3);
        r0.add(r1);
        r1 = com.tomatolive.library.R.string.btn_kick_out;
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r5);
        r0.add(r1);
        if (r4 == 0) goto L_0x0092;
    L_0x008f:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded_cancel;
        goto L_0x0094;
    L_0x0092:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded;
    L_0x0094:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r8, r4);
        r0.add(r1);
        return r0;
    L_0x00a0:
        if (r2 == 0) goto L_0x00a5;
    L_0x00a2:
        r1 = com.tomatolive.library.R.string.fq_my_live_house_manager_cancel;
        goto L_0x00a7;
    L_0x00a5:
        r1 = com.tomatolive.library.R.string.fq_my_live_house_manager;
    L_0x00a7:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r6, r2);
        r0.add(r1);
        if (r3 == 0) goto L_0x00b7;
    L_0x00b4:
        r1 = com.tomatolive.library.R.string.btn_cancel_banned;
        goto L_0x00b9;
    L_0x00b7:
        r1 = com.tomatolive.library.R.string.btn_banned;
    L_0x00b9:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r7, r3);
        r0.add(r1);
        r1 = com.tomatolive.library.R.string.btn_kick_out;
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r5);
        r0.add(r1);
        if (r4 == 0) goto L_0x00d6;
    L_0x00d3:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded_cancel;
        goto L_0x00d8;
    L_0x00d6:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded;
    L_0x00d8:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r8, r4);
        r0.add(r1);
        return r0;
    L_0x00e4:
        r1 = com.tomatolive.library.R.string.fq_btn_banned_forever;
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r7);
        r0.add(r1);
        r1 = com.tomatolive.library.R.string.fq_btn_kick_out_forever;
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r5);
        r0.add(r1);
        return r0;
    L_0x00ff:
        r1 = com.tomatolive.library.R.string.fq_btn_shielded;
    L_0x0101:
        r1 = r9.getString(r1);
        r1 = r9.getMenuEntity(r1, r8, r4);
        r0.add(r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.LiveActionBottomDialog.getMenuList():java.util.List");
    }

    private MenuEntity getMenuEntity(String str, int i) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.menuTitle = str;
        menuEntity.menuType = i;
        return menuEntity;
    }

    private MenuEntity getMenuEntity(String str, int i, boolean z) {
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.menuTitle = str;
        menuEntity.menuType = i;
        menuEntity.isSelected = z;
        return menuEntity;
    }

    public OnLiveActionListener getOnLiveActionListener() {
        return this.onLiveActionListener;
    }

    public void setOnLiveActionListener(OnLiveActionListener onLiveActionListener) {
        this.onLiveActionListener = onLiveActionListener;
    }
}
