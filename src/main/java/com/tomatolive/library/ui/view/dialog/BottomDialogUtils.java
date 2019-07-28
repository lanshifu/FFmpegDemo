package com.tomatolive.library.ui.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tomatolive.library.R;
import com.tomatolive.library.model.CountryCodeEntity;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.tomatolive.library.utils.p;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

public class BottomDialogUtils {

    public interface BottomPromptMenuListener {
        void onCancel();

        void onSure();
    }

    public interface CountryCodeListener {
        void onCountryCodeListener(CountryCodeEntity countryCodeEntity, int i);
    }

    public interface LiveBottomBannedMenuListener {
        void onLiveBottomBannedMenuListener(long j);
    }

    public interface LiveBottomMoreMenuListener {
        void onLiveBottomMoreMenuListener(BaseQuickAdapter<MenuEntity, BaseViewHolder> baseQuickAdapter, MenuEntity menuEntity, int i);
    }

    public interface OnSpeakSettingListener {
        void speakSettingDone(boolean z, String str, String str2);
    }

    private static class BannedAdapter extends BaseQuickAdapter<MenuEntity, BaseViewHolder> {
        private int selectedPosition;

        /* synthetic */ BannedAdapter(int i, List list, AnonymousClass1 anonymousClass1) {
            this(i, list);
        }

        private BannedAdapter(int i, @Nullable List<MenuEntity> list) {
            super(i, list);
            this.selectedPosition = -1;
        }

        private void setSelectedPosition(int i) {
            this.selectedPosition = i;
            notifyDataSetChanged();
        }

        public int getSelectedPosition() {
            return this.selectedPosition;
        }

        private MenuEntity getSelectedItem() {
            return (MenuEntity) getItem(this.selectedPosition);
        }

        /* Access modifiers changed, original: protected */
        public void convert(BaseViewHolder baseViewHolder, MenuEntity menuEntity) {
            Object obj = this.selectedPosition == baseViewHolder.getAdapterPosition() ? 1 : null;
            baseViewHolder.setText(R.id.tv_item_title, menuEntity.menuTitle).setBackgroundColor(R.id.tv_item_title, ContextCompat.getColor(this.mContext, obj != null ? R.color.fq_list_item_bg_pressed : R.color.fq_list_item_bg_normal)).setTextColor(R.id.tv_item_title, ContextCompat.getColor(this.mContext, obj != null ? R.color.fq_colorRed : R.color.fq_text_black));
        }
    }

    private static class CountryCodeAdapter extends BaseQuickAdapter<CountryCodeEntity, BaseViewHolder> {
        private int selectedPosition;

        /* synthetic */ CountryCodeAdapter(int i, List list, AnonymousClass1 anonymousClass1) {
            this(i, list);
        }

        private CountryCodeAdapter(int i, @Nullable List<CountryCodeEntity> list) {
            super(i, list);
            this.selectedPosition = -1;
        }

        private void setSelectedPosition(int i) {
            this.selectedPosition = i;
            notifyDataSetChanged();
        }

        private int getSelectedPosition() {
            return this.selectedPosition;
        }

        private CountryCodeEntity getSelectedItem() {
            return (CountryCodeEntity) getItem(this.selectedPosition);
        }

        /* Access modifiers changed, original: protected */
        public void convert(BaseViewHolder baseViewHolder, CountryCodeEntity countryCodeEntity) {
            Object obj = this.selectedPosition == baseViewHolder.getAdapterPosition() ? 1 : null;
            baseViewHolder.setText(R.id.tv_item_title, getCodeStr(countryCodeEntity)).setBackgroundColor(R.id.tv_item_title, ContextCompat.getColor(this.mContext, obj != null ? R.color.fq_list_item_bg_pressed : R.color.fq_list_item_bg_normal)).setTextColor(R.id.tv_item_title, ContextCompat.getColor(this.mContext, obj != null ? R.color.fq_colorRed : R.color.fq_text_black));
        }

        private String getCodeStr(CountryCodeEntity countryCodeEntity) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(countryCodeEntity.countryName);
            stringBuilder.append("(");
            stringBuilder.append(Marker.ANY_NON_NULL_MARKER);
            stringBuilder.append(countryCodeEntity.countryCode);
            stringBuilder.append(")");
            return stringBuilder.toString();
        }
    }

    private static class LiveBottomMenuAdapter extends BaseQuickAdapter<MenuEntity, BaseViewHolder> {
        /* synthetic */ LiveBottomMenuAdapter(int i, List list, AnonymousClass1 anonymousClass1) {
            this(i, list);
        }

        private LiveBottomMenuAdapter(int i, @Nullable List<MenuEntity> list) {
            super(i, list);
        }

        /* Access modifiers changed, original: protected */
        public void convert(BaseViewHolder baseViewHolder, MenuEntity menuEntity) {
            baseViewHolder.setImageResource(R.id.iv_menu_icon, menuEntity.menuIcon).setText(R.id.tv_menu_title, menuEntity.menuTitle).getView(R.id.iv_menu_icon).setSelected(menuEntity.isSelected);
        }
    }

    private static class RVDividerGrid extends Y_DividerItemDecoration {
        private final int colorRes;
        private final Context context;

        /* synthetic */ RVDividerGrid(Context context, int i, AnonymousClass1 anonymousClass1) {
            this(context, i);
        }

        private RVDividerGrid(Context context, @ColorRes int i) {
            super(context);
            this.context = context;
            this.colorRes = i;
        }

        public Y_Divider getDivider(int i) {
            return new Y_DividerBuilder().setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 20.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 20.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
        }
    }

    private static long getBanndDuration(int i) {
        switch (i) {
            case 0:
                return 900;
            case 1:
                return 1800;
            case 2:
                return 3600;
            case 3:
                return 28800;
            case 4:
                return 86400;
            case 5:
                return 259200;
            case 6:
                return 604800;
            case 7:
                return 2592000;
            default:
                return -1;
        }
    }

    private BottomDialogUtils() {
    }

    public static void showBottomPromptDialog(Context context, String str, final BottomPromptMenuListener bottomPromptMenuListener) {
        final Dialog dialog = new Dialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fq_layout_bottom_prompt_menu_view, new LinearLayout(context), false);
        ((TextView) inflate.findViewById(R.id.tv_prompt)).setText(str);
        inflate.findViewById(R.id.tv_sure).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (bottomPromptMenuListener != null) {
                    bottomPromptMenuListener.onSure();
                }
            }
        });
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (bottomPromptMenuListener != null) {
                    bottomPromptMenuListener.onCancel();
                }
            }
        });
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.height = -2;
                attributes.width = -1;
                attributes.dimAmount = 0.5f;
                attributes.gravity = 80;
                window.setAttributes(attributes);
            }
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(inflate);
        dialog.show();
    }

    public static Dialog getLiveBottomDialog(Context context, boolean z, LiveBottomMoreMenuListener liveBottomMoreMenuListener) {
        Dialog dialog = new Dialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fq_layout_bottom_menu_view, new RelativeLayout(context), false);
        LiveBottomMenuAdapter liveBottomMenuAdapter = new LiveBottomMenuAdapter(R.layout.fq_item_bottom_menu_view, getMenuList(context, z), null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_operate);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        recyclerView.addItemDecoration(new RVDividerGrid(context, 17170445, null));
        recyclerView.setAdapter(liveBottomMenuAdapter);
        liveBottomMenuAdapter.bindToRecyclerView(recyclerView);
        liveBottomMenuAdapter.setOnItemClickListener(new -$$Lambda$BottomDialogUtils$B1HMJ1OAjcHNqsZXOPvRXcCiD58(dialog, liveBottomMoreMenuListener));
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.height = -2;
                attributes.width = -1;
                attributes.dimAmount = CropImageView.DEFAULT_ASPECT_RATIO;
                attributes.gravity = 80;
                window.setAttributes(attributes);
            }
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        return dialog;
    }

    static /* synthetic */ void lambda$getLiveBottomDialog$0(Dialog dialog, LiveBottomMoreMenuListener liveBottomMoreMenuListener, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        dialog.dismiss();
        MenuEntity menuEntity = (MenuEntity) baseQuickAdapter.getItem(i);
        if (menuEntity != null) {
            menuEntity.isSelected ^= 1;
            baseQuickAdapter.setData(i, menuEntity);
            if (liveBottomMoreMenuListener != null) {
                liveBottomMoreMenuListener.onLiveBottomMoreMenuListener(baseQuickAdapter, menuEntity, i);
            }
        }
    }

    private static List<MenuEntity> getMenuList(Context context, boolean z) {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(R.array.live_more_menu_title);
        r1 = new int[8];
        int i = 0;
        r1[0] = R.drawable.fq_icon_flower_selector;
        r1[1] = R.drawable.fq_icon_microphone_selector;
        r1[2] = R.drawable.fq_icon_flashlight_selector;
        r1[3] = R.drawable.fq_icon_setting_selector;
        r1[4] = R.drawable.fq_icon_mirror_black_selector;
        r1[5] = R.drawable.fq_icon_speak_setting_selector;
        r1[6] = R.drawable.fq_icon_modfiy_theme_selector;
        r1[7] = R.drawable.fq_icon_slogan_selector;
        while (i < stringArray.length) {
            if (i == 4) {
                arrayList.add(new MenuEntity(stringArray[i], r1[i], z));
            } else {
                arrayList.add(new MenuEntity(stringArray[i], r1[i]));
            }
            i++;
        }
        return arrayList;
    }

    public static void showBottomSpeakSettingDialog(Context context, String str, String str2, boolean z, OnSpeakSettingListener onSpeakSettingListener) {
        HideSoftInputDialog hideSoftInputDialog = new HideSoftInputDialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_bottom_speak_setting, new LinearLayout(context), false);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_time_limit);
        final EditText editText2 = (EditText) inflate.findViewById(R.id.et_level);
        editText2.setText(str2);
        editText.setText(str);
        editText2.addTextChangedListener(inputWatch(editText2));
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.cb_banned);
        checkBox.setChecked(z);
        final HideSoftInputDialog hideSoftInputDialog2 = hideSoftInputDialog;
        final OnSpeakSettingListener onSpeakSettingListener2 = onSpeakSettingListener;
        inflate.findViewById(R.id.iv_done).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                hideSoftInputDialog2.dismiss();
                if (onSpeakSettingListener2 != null) {
                    String obj = editText.getEditableText().toString();
                    if (TextUtils.isEmpty(obj)) {
                        obj = "1";
                    }
                    if (obj.length() == 2 && obj.startsWith("0")) {
                        obj = obj.substring(1, 2);
                    }
                    String obj2 = editText2.getEditableText().toString();
                    if (TextUtils.isEmpty(obj2)) {
                        obj2 = "1";
                    }
                    if (obj2.length() == 2 && obj2.startsWith("0")) {
                        obj2 = obj2.substring(1, 2);
                    }
                    onSpeakSettingListener2.speakSettingDone(checkBox.isChecked(), obj, obj2);
                }
            }
        });
        Window window = hideSoftInputDialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.height = -2;
                attributes.width = -1;
                attributes.dimAmount = 0.5f;
                attributes.gravity = 80;
                window.setAttributes(attributes);
            }
        }
        hideSoftInputDialog.setCanceledOnTouchOutside(true);
        hideSoftInputDialog.setContentView(inflate);
        hideSoftInputDialog.show();
    }

    private static TextWatcher inputWatch(final EditText editText) {
        return new TextWatcher() {
            private String outStr = "";

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                if (charSequence2.length() == 1 && Integer.parseInt(charSequence2) >= 6) {
                    this.outStr = charSequence2;
                }
            }

            public void afterTextChanged(Editable editable) {
                if (p.a(editable.toString()) > 60) {
                    editText.setText(this.outStr);
                    editText.setSelection(this.outStr.length());
                }
            }
        };
    }

    public static void showBannedDialog(Context context, final LiveBottomBannedMenuListener liveBottomBannedMenuListener) {
        final Dialog dialog = new Dialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fq_dialog_live_banned, new LinearLayout(context), false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_operate);
        final BannedAdapter bannedAdapter = new BannedAdapter(R.layout.fq_item_list_banned_view, getBannedMenuList(context), null);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        bannedAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(bannedAdapter);
        bannedAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                bannedAdapter.setSelectedPosition(i);
            }
        });
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_submit).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MenuEntity access$400 = bannedAdapter.getSelectedItem();
                if (access$400 != null) {
                    dialog.dismiss();
                    if (liveBottomBannedMenuListener != null) {
                        liveBottomBannedMenuListener.onLiveBottomBannedMenuListener(BottomDialogUtils.getBanndDuration(access$400.position));
                    }
                }
            }
        });
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.height = -2;
                attributes.width = -1;
                attributes.dimAmount = 0.5f;
                attributes.gravity = 80;
                window.setAttributes(attributes);
            }
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
    }

    public static Dialog showPhoneCountryCodeDialog(Context context, List<CountryCodeEntity> list, final CountryCodeListener countryCodeListener) {
        final Dialog dialog = new Dialog(context, R.style.ActionSheet);
        View inflate = LayoutInflater.from(context).inflate(R.layout.fq_dialog_country_code, new LinearLayout(context), false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.rv_operate);
        final CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(R.layout.fq_item_list_banned_view, list, null);
        inflate.findViewById(R.id.tv_dialog_title).setVisibility(4);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        countryCodeAdapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(countryCodeAdapter);
        countryCodeAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                countryCodeAdapter.setSelectedPosition(i);
            }
        });
        inflate.findViewById(R.id.tv_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tv_submit).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CountryCodeEntity access$800 = countryCodeAdapter.getSelectedItem();
                if (access$800 != null) {
                    dialog.dismiss();
                    if (countryCodeListener != null) {
                        countryCodeListener.onCountryCodeListener(access$800, countryCodeAdapter.getSelectedPosition());
                    }
                }
            }
        });
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            LayoutParams attributes = window.getAttributes();
            if (attributes != null) {
                attributes.height = -2;
                attributes.width = -1;
                attributes.dimAmount = 0.5f;
                attributes.gravity = 80;
                window.setAttributes(attributes);
            }
        }
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(inflate);
        dialog.show();
        return dialog;
    }

    private static List<MenuEntity> getBannedMenuList(Context context) {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(R.array.banned_text);
        for (int i = 0; i < stringArray.length; i++) {
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.menuTitle = stringArray[i];
            menuEntity.position = i;
            arrayList.add(menuEntity);
        }
        return arrayList;
    }

    private static List<MenuEntity> getPhoneCountryCodeMenuList(Context context) {
        ArrayList arrayList = new ArrayList();
        String[] stringArray = context.getResources().getStringArray(R.array.phone_country_code_key);
        String[] stringArray2 = context.getResources().getStringArray(R.array.phone_country_code_value);
        for (int i = 0; i < stringArray2.length; i++) {
            arrayList.add(new MenuEntity(stringArray2[i], stringArray[i]));
        }
        return arrayList;
    }
}
