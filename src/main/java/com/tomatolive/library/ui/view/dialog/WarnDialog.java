package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.tomatolive.library.R;

public class WarnDialog extends BaseDialogFragment {
    public static final String BAN_TIP = "BAN_TIP";
    private static final String CONTENT_KEY = "CONTENT_KEY";
    public static final String FROZEN_TIP = "FROZEN_TIP";
    public static final String STOP_WARN_TIP = "STOP_WARN_TIP";
    private static final String TIP_KEY = "TIP_KEY";
    public static final String TRANSLATION_TIP = "TRANSLATION_TIP";
    public static final String WARN_TIP = "WARN_TIP";

    @SuppressLint({"ValidFragment"})
    private WarnDialog() {
    }

    public static WarnDialog newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(TIP_KEY, str);
        WarnDialog warnDialog = new WarnDialog();
        warnDialog.setArguments(bundle);
        return warnDialog;
    }

    public static WarnDialog newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(TIP_KEY, str);
        bundle.putString(CONTENT_KEY, str2);
        WarnDialog warnDialog = new WarnDialog();
        warnDialog.setArguments(bundle);
        return warnDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_warn_tips;
    }

    public void initView(android.view.View r6) {
        /*
        r5 = this;
        r0 = com.tomatolive.library.R.id.tv_tips;
        r0 = r6.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1 = com.tomatolive.library.R.id.tv_auth_tips;
        r1 = r6.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r2 = "TIP_KEY";
        r2 = r5.getArgumentsString(r2);
        r3 = com.tomatolive.library.R.id.tv_sure;
        r6 = r6.findViewById(r3);
        r6 = (android.widget.TextView) r6;
        r3 = r2.hashCode();
        switch(r3) {
            case -460174899: goto L_0x004e;
            case 381543467: goto L_0x0044;
            case 1281379138: goto L_0x003a;
            case 1805920860: goto L_0x0030;
            case 1980849887: goto L_0x0026;
            default: goto L_0x0025;
        };
    L_0x0025:
        goto L_0x0058;
    L_0x0026:
        r3 = "STOP_WARN_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0058;
    L_0x002e:
        r2 = 2;
        goto L_0x0059;
    L_0x0030:
        r3 = "FROZEN_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0058;
    L_0x0038:
        r2 = 3;
        goto L_0x0059;
    L_0x003a:
        r3 = "WARN_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0058;
    L_0x0042:
        r2 = 1;
        goto L_0x0059;
    L_0x0044:
        r3 = "BAN_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0058;
    L_0x004c:
        r2 = 0;
        goto L_0x0059;
    L_0x004e:
        r3 = "TRANSLATION_TIP";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0058;
    L_0x0056:
        r2 = 4;
        goto L_0x0059;
    L_0x0058:
        r2 = -1;
    L_0x0059:
        switch(r2) {
            case 0: goto L_0x00a9;
            case 1: goto L_0x0096;
            case 2: goto L_0x0083;
            case 3: goto L_0x0070;
            case 4: goto L_0x005d;
            default: goto L_0x005c;
        };
    L_0x005c:
        return;
    L_0x005d:
        r2 = com.tomatolive.library.R.string.fq_tip;
        r2 = r5.getString(r2);
        r3 = "CONTENT_KEY";
        r3 = r5.getArgumentsString(r3);
        r4 = com.tomatolive.library.R.string.fq_kown;
        r4 = r5.getString(r4);
        goto L_0x00bb;
    L_0x0070:
        r2 = com.tomatolive.library.R.string.fq_frozen_tip;
        r2 = r5.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_frozen_content;
        r3 = r5.getString(r3);
        r4 = com.tomatolive.library.R.string.fq_btn_sure;
        r4 = r5.getString(r4);
        goto L_0x00bb;
    L_0x0083:
        r2 = com.tomatolive.library.R.string.fq_back_out_warn;
        r2 = r5.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_back_out_warn_content;
        r3 = r5.getString(r3);
        r4 = com.tomatolive.library.R.string.fq_kown;
        r4 = r5.getString(r4);
        goto L_0x00bb;
    L_0x0096:
        r2 = com.tomatolive.library.R.string.fq_warn_tip;
        r2 = r5.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_warn_content;
        r3 = r5.getString(r3);
        r4 = com.tomatolive.library.R.string.fq_kown;
        r4 = r5.getString(r4);
        goto L_0x00bb;
    L_0x00a9:
        r2 = com.tomatolive.library.R.string.fq_tip;
        r2 = r5.getString(r2);
        r3 = com.tomatolive.library.R.string.fq_anchor_ban_content;
        r3 = r5.getString(r3);
        r4 = com.tomatolive.library.R.string.fq_kown;
        r4 = r5.getString(r4);
    L_0x00bb:
        r0.setText(r2);
        r1.setText(r3);
        r6.setText(r4);
        r0 = new com.tomatolive.library.ui.view.dialog.WarnDialog$1;
        r0.<init>();
        r6.setOnClickListener(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.WarnDialog.initView(android.view.View):void");
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, getArgumentsString(TIP_KEY));
    }
}
