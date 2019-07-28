package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.dialog.GuardOpenContentDialog.OnOpenGuardCallbackListener;

public class GuardOpenTipsDialog extends BaseDialogFragment {
    private static final String SER_ITEM = "serItem";
    private static final String TIP_KEY = "TIP_KEY";
    public static final int TYPE_CONTINUE_OPEN = 13;
    public static final int TYPE_COVER_OPEN = 15;
    public static final int TYPE_NOW_OPEN = 12;
    public static final int TYPE_NO_OPEN = 14;
    public static final int TYPE_ROOM_OPEN = 11;
    private LiveEntity guardItem;
    private OnClickListener openClickListener;
    private OnOpenGuardCallbackListener openGuardCallbackListener;

    @SuppressLint({"ValidFragment"})
    private GuardOpenTipsDialog() {
    }

    private void setOpenClickListener(OnClickListener onClickListener) {
        this.openClickListener = onClickListener;
    }

    public static GuardOpenTipsDialog newInstance(int i, LiveEntity liveEntity, OnOpenGuardCallbackListener onOpenGuardCallbackListener) {
        GuardOpenTipsDialog guardOpenTipsDialog = new GuardOpenTipsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(TIP_KEY, i);
        bundle.putSerializable(SER_ITEM, liveEntity);
        guardOpenTipsDialog.setArguments(bundle);
        guardOpenTipsDialog.setOpenGuardCallbackListener(onOpenGuardCallbackListener);
        return guardOpenTipsDialog;
    }

    public static GuardOpenTipsDialog newInstance(int i, LiveEntity liveEntity, OnClickListener onClickListener) {
        GuardOpenTipsDialog guardOpenTipsDialog = new GuardOpenTipsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(TIP_KEY, i);
        bundle.putSerializable(SER_ITEM, liveEntity);
        guardOpenTipsDialog.setOpenClickListener(onClickListener);
        guardOpenTipsDialog.setArguments(bundle);
        return guardOpenTipsDialog;
    }

    public static GuardOpenTipsDialog newInstance(int i) {
        GuardOpenTipsDialog guardOpenTipsDialog = new GuardOpenTipsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(TIP_KEY, i);
        guardOpenTipsDialog.setArguments(bundle);
        return guardOpenTipsDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_open_guard_tips;
    }

    public void initView(View view) {
        this.guardItem = (LiveEntity) getArguments().getSerializable(SER_ITEM);
        TextView textView = (TextView) view.findViewById(R.id.tv_cancel);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_sure);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_content);
        TextView textView4 = (TextView) view.findViewById(R.id.tv_tips);
        TextView textView5 = (TextView) view.findViewById(R.id.tv_open_room);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_btn_bg);
        switch (getArgumentsInt(TIP_KEY)) {
            case 11:
                textView4.setVisibility(0);
                textView3.setText(R.string.fq_guard_tips);
                textView5.setVisibility(0);
                linearLayout.setVisibility(4);
                break;
            case 12:
                textView4.setVisibility(4);
                textView.setVisibility(0);
                textView5.setVisibility(4);
                linearLayout.setVisibility(0);
                if (((LiveEntity) getArguments().getSerializable(SER_ITEM)) != null) {
                    textView3.setText(Html.fromHtml(this.mContext.getString(R.string.fq_guard_open_dialog_tips, new Object[]{r12.nickname, r12.amount, r12.label})));
                    break;
                }
                break;
            case 13:
                textView4.setVisibility(0);
                textView.setVisibility(0);
                textView5.setVisibility(4);
                linearLayout.setVisibility(0);
                textView4.setText(R.string.fq_guard_continue_open_tips);
                if (((LiveEntity) getArguments().getSerializable(SER_ITEM)) != null) {
                    textView3.setText(Html.fromHtml(this.mContext.getString(R.string.fq_guard_open_dialog_tips, new Object[]{r12.nickname, r12.amount, r12.label})));
                    break;
                }
                break;
            case 14:
                textView4.setVisibility(4);
                textView.setVisibility(8);
                linearLayout.setVisibility(0);
                textView5.setVisibility(4);
                textView2.setText(R.string.fq_kown);
                textView3.setText(R.string.fq_guard_no_open_tips);
                break;
            case 15:
                textView4.setVisibility(0);
                textView.setVisibility(0);
                textView5.setVisibility(4);
                linearLayout.setVisibility(0);
                textView4.setText(R.string.fq_guard_cover_open_tips);
                if (((LiveEntity) getArguments().getSerializable(SER_ITEM)) != null) {
                    textView3.setText(Html.fromHtml(this.mContext.getString(R.string.fq_guard_open_dialog_tips, new Object[]{r12.nickname, r12.amount, r12.label})));
                    break;
                }
                break;
            default:
                return;
        }
        textView.setOnClickListener(new -$$Lambda$GuardOpenTipsDialog$rcah6i_IP4z72LM418peb8whloU(this));
        textView2.setOnClickListener(new -$$Lambda$GuardOpenTipsDialog$mT4qvjYhuafjAHTKBcecKrcPoLM(this));
        textView5.setOnClickListener(new -$$Lambda$GuardOpenTipsDialog$EEe_5rugUmbxtKkccpmpb1uKe2Y(this));
    }

    public static /* synthetic */ void lambda$initView$1(GuardOpenTipsDialog guardOpenTipsDialog, View view) {
        guardOpenTipsDialog.dismiss();
        if (guardOpenTipsDialog.openClickListener != null) {
            guardOpenTipsDialog.openClickListener.onClick(view);
        }
    }

    public static /* synthetic */ void lambda$initView$2(GuardOpenTipsDialog guardOpenTipsDialog, View view) {
        guardOpenTipsDialog.dismiss();
        GuardOpenContentDialog.newInstance(guardOpenTipsDialog.guardItem, guardOpenTipsDialog.openGuardCallbackListener).show(guardOpenTipsDialog.getFragmentManager());
    }

    private void setOpenGuardCallbackListener(OnOpenGuardCallbackListener onOpenGuardCallbackListener) {
        this.openGuardCallbackListener = onOpenGuardCallbackListener;
    }
}
