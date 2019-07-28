package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.tomatolive.library.R;

public class PermissionDialog extends BaseDialogFragment implements OnClickListener {
    public static final String CAMERA_TIP = "CAMERA_TIP";
    private static final String LOCATION_TIP = "LOCATION_TIP";
    public static final String MIC_TIP = "MIC_TIP";
    private static final String TIP_KEY = "TIP_KEY";

    public boolean getCancelOutside() {
        return false;
    }

    @SuppressLint({"ValidFragment"})
    private PermissionDialog() {
    }

    public static PermissionDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(TIP_KEY, str);
        PermissionDialog permissionDialog = new PermissionDialog();
        permissionDialog.setArguments(bundle);
        return permissionDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_tans_tips;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057 A:{RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058  */
    public void initView(android.view.View r9) {
        /*
        r8 = this;
        r0 = com.tomatolive.library.R.id.tv_cancel;
        r0 = r9.findViewById(r0);
        r0.setOnClickListener(r8);
        r0 = com.tomatolive.library.R.id.tv_tips;
        r0 = r9.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1 = com.tomatolive.library.R.id.tv_auth_tips;
        r1 = r9.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r2 = "TIP_KEY";
        r2 = r8.getArgumentsString(r2);
        r3 = r2.hashCode();
        r4 = -2034687759; // 0xffffffff86b920f1 float:-6.963767E-35 double:NaN;
        r5 = 0;
        r6 = 1;
        if (r3 == r4) goto L_0x0049;
    L_0x002a:
        r4 = -1525739423; // 0xffffffffa50f1061 float:-1.2408822E-16 double:NaN;
        if (r3 == r4) goto L_0x003f;
    L_0x002f:
        r4 = 1773023843; // 0x69ae3263 float:2.632388E25 double:8.7599017E-315;
        if (r3 == r4) goto L_0x0035;
    L_0x0034:
        goto L_0x0053;
    L_0x0035:
        r3 = "MIC_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0053;
    L_0x003d:
        r2 = 1;
        goto L_0x0054;
    L_0x003f:
        r3 = "CAMERA_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0053;
    L_0x0047:
        r2 = 0;
        goto L_0x0054;
    L_0x0049:
        r3 = "LOCATION_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0053;
    L_0x0051:
        r2 = 2;
        goto L_0x0054;
    L_0x0053:
        r2 = -1;
    L_0x0054:
        switch(r2) {
            case 0: goto L_0x008a;
            case 1: goto L_0x0071;
            case 2: goto L_0x0058;
            default: goto L_0x0057;
        };
    L_0x0057:
        return;
    L_0x0058:
        r2 = com.tomatolive.library.R.string.fq_no_permission_location;
        r2 = r8.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_permission_location_allow;
        r4 = new java.lang.Object[r6];
        r6 = r8.mContext;
        r7 = com.tomatolive.library.R.string.fq_live_sdk_app_name;
        r6 = r6.getString(r7);
        r4[r5] = r6;
        r3 = r8.getString(r3, r4);
        goto L_0x00a2;
    L_0x0071:
        r2 = com.tomatolive.library.R.string.fq_mic_bannd;
        r2 = r8.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_permission_mic_allow;
        r4 = new java.lang.Object[r6];
        r6 = r8.mContext;
        r7 = com.tomatolive.library.R.string.fq_live_sdk_app_name;
        r6 = r6.getString(r7);
        r4[r5] = r6;
        r3 = r8.getString(r3, r4);
        goto L_0x00a2;
    L_0x008a:
        r2 = com.tomatolive.library.R.string.fq_camera_bannd;
        r2 = r8.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_permission_camera_allow;
        r4 = new java.lang.Object[r6];
        r6 = r8.mContext;
        r7 = com.tomatolive.library.R.string.fq_live_sdk_app_name;
        r6 = r6.getString(r7);
        r4[r5] = r6;
        r3 = r8.getString(r3, r4);
    L_0x00a2:
        r0.setText(r2);
        r1.setText(r3);
        r0 = com.tomatolive.library.R.id.tv_start;
        r9 = r9.findViewById(r0);
        r9 = (android.widget.TextView) r9;
        r0 = com.tomatolive.library.R.string.fq_goto_setting;
        r9.setText(r0);
        r0 = new com.tomatolive.library.ui.view.dialog.PermissionDialog$1;
        r0.<init>();
        r9.setOnClickListener(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.PermissionDialog.initView(android.view.View):void");
    }

    public void onClick(View view) {
        dismiss();
        if (!TextUtils.equals(getArgumentsString(TIP_KEY), LOCATION_TIP)) {
            getActivity().finish();
        }
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, getArgumentsString(TIP_KEY));
    }
}
