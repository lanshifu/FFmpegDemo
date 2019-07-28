package com.tomatolive.library.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.o;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.CountryCodeEntity;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.model.UploadFileEntity;
import com.tomatolive.library.ui.presenter.AnchorAuthPresenter;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils;
import com.tomatolive.library.ui.view.dialog.LoadingDialog;
import com.tomatolive.library.ui.view.iview.IAnchorAuthView;
import com.tomatolive.library.ui.view.widget.ActionSheetView;
import com.tomatolive.library.ui.view.widget.ActionSheetView.ActionSheetOperateListener;
import com.tomatolive.library.ui.view.widget.matisse.Matisse;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;
import com.tomatolive.library.ui.view.widget.matisse.engine.impl.GlideEngine;
import com.tomatolive.library.utils.emoji.EmojiParser;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.u;
import defpackage.mh;
import defpackage.mj;
import defpackage.sh;
import defpackage.wd;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import top.zibin.luban.d;
import top.zibin.luban.e;

public class AnchorAuthActivity extends BaseActivity<AnchorAuthPresenter> implements OnClickListener, IAnchorAuthView {
    private static final int ABLUM_REQUEST_CODE = 10;
    private String countryCode = "86";
    private List<CountryCodeEntity> countryCodeList;
    private EditText etCode;
    private EditText etIdCard;
    private EditText etName;
    private EditText etPhone;
    private FrameLayout flIdCardBack;
    private FrameLayout flIdCardFront;
    private String idCardFront = "";
    private boolean isFront = true;
    private LoadingDialog loadingDialog;
    private ImageView mIdCardBack;
    private ImageView mIdCardFront;
    private RxPermissions mRxPermissions;
    private String parentImgPath = "";
    private File tempBackImgFile;
    private File tempFrontImgFile;
    private final int timeCount = 60;
    private TextView tvCountryCode;
    private TextView tvSendCode;
    private TextView tvSubmit;
    private File upBackFile;
    private File upFrontFile;

    private class MyActionListener implements ActionSheetOperateListener {
        public void onCancel() {
        }

        /* synthetic */ MyActionListener(AnchorAuthActivity anchorAuthActivity, AnonymousClass1 anonymousClass1) {
            this();
        }

        private MyActionListener() {
        }

        public void onOperateListener(MenuEntity menuEntity, int i) {
            if (i == 0) {
                AnchorAuthActivity.this.mRxPermissions.request(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new sh<Boolean>() {
                    public void accept(Boolean bool) {
                        if (!bool.booleanValue()) {
                            AnchorAuthActivity.this.showToast(R.string.fq_no_permission);
                        } else if (Environment.getExternalStorageState().equals("mounted")) {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            if (intent.resolveActivity(AnchorAuthActivity.this.getPackageManager()) != null) {
                                intent.putExtra("output", MyActionListener.this.parUri(MyActionListener.this.getFile()));
                                intent.addFlags(1);
                                intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                            }
                            AnchorAuthActivity.this.startActivityForResult(intent, 908);
                        } else {
                            AnchorAuthActivity.this.showToast(R.string.fq_no_sd_card);
                        }
                    }
                });
            } else if (i == 1) {
                AnchorAuthActivity.this.mRxPermissions.request(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}).subscribe(new sh<Boolean>() {
                    public void accept(Boolean bool) {
                        if (bool.booleanValue()) {
                            Matisse.from(AnchorAuthActivity.this).choose(MimeType.of(MimeType.JPEG, MimeType.PNG)).showSingleMediaType(true).capture(false).thumbnailScale(0.85f).imageEngine(new GlideEngine()).forResult(10);
                            return;
                        }
                        AnchorAuthActivity.this.showToast(R.string.fq_no_permission_write);
                    }
                });
            }
        }

        private File getFile() {
            return AnchorAuthActivity.this.isFront ? AnchorAuthActivity.this.tempFrontImgFile : AnchorAuthActivity.this.tempBackImgFile;
        }

        private Uri parUri(File file) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(AnchorAuthActivity.this.getPackageName());
            stringBuilder.append(".provider");
            String stringBuilder2 = stringBuilder.toString();
            if (VERSION.SDK_INT > 23) {
                return FileProvider.getUriForFile(AnchorAuthActivity.this.mContext, stringBuilder2, file);
            }
            return Uri.fromFile(file);
        }
    }

    /* Access modifiers changed, original: protected */
    public AnchorAuthPresenter createPresenter() {
        return new AnchorAuthPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_anchor_identy;
    }

    /* Access modifiers changed, original: protected */
    public void initImmersionBar() {
        super.initImmersionBar();
        this.mImmersionBar.c(true).b();
    }

    public void initView(Bundle bundle) {
        setActivityTitle(R.string.fq_anchor_identy);
        this.mRxPermissions = new RxPermissions(this);
        this.mIdCardBack = (ImageView) findViewById(R.id.iv_up_idcrad_back);
        this.mIdCardFront = (ImageView) findViewById(R.id.iv_up_idcrad_front);
        this.etName = (EditText) findViewById(R.id.edit_name);
        this.etIdCard = (EditText) findViewById(R.id.edit_idcard);
        this.etPhone = (EditText) findViewById(R.id.edit_phone);
        this.etCode = (EditText) findViewById(R.id.edit_code);
        this.tvSendCode = (TextView) findViewById(R.id.tv_send_code);
        this.tvSubmit = (TextView) findViewById(R.id.tv_commit_info);
        this.tvCountryCode = (TextView) findViewById(R.id.tv_country_code);
        this.flIdCardFront = (FrameLayout) findViewById(R.id.fl_id_card_front);
        this.flIdCardBack = (FrameLayout) findViewById(R.id.fl_id_card_back);
        initFile();
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this.mContext, getString(R.string.hint_text_dialog_loading_data));
        }
    }

    public void initListener() {
        super.initListener();
        this.mIdCardFront.setOnClickListener(this);
        this.mIdCardBack.setOnClickListener(this);
        this.tvSendCode.setOnClickListener(this);
        this.flIdCardFront.setOnClickListener(this);
        this.flIdCardBack.setOnClickListener(this);
        mh.a(this.tvSubmit).throttleFirst(3, TimeUnit.SECONDS).subscribe(new sh<Object>() {
            public void accept(Object obj) {
                AnchorAuthActivity.this.commitInfo();
            }
        });
        k.combineLatest(mj.a(this.etName), mj.a(this.etIdCard), mj.a(this.etPhone), mj.a(this.etCode), -$$Lambda$AnchorAuthActivity$okW5quXbaF8CHNLX256WkD3ynp4.INSTANCE).subscribeOn(wd.a()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new sh<Boolean>() {
            public void accept(Boolean bool) {
                if (AnchorAuthActivity.this.upFrontFile != null && AnchorAuthActivity.this.upFrontFile.exists() && AnchorAuthActivity.this.upBackFile != null && AnchorAuthActivity.this.upBackFile.exists()) {
                    AnchorAuthActivity.this.tvSubmit.setEnabled(bool.booleanValue());
                }
            }
        });
        mj.a(this.etPhone).map(-$$Lambda$AnchorAuthActivity$oDsBqvbst1fC8d8SjCzONqRHSFY.INSTANCE).compose(bindToLifecycle()).subscribe(new r<Boolean>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
            }

            public void onNext(Boolean bool) {
                AnchorAuthActivity.this.tvSendCode.setEnabled(bool.booleanValue());
            }
        });
        this.tvCountryCode.setOnClickListener(new -$$Lambda$AnchorAuthActivity$1SHbz4QwEQeRd8NVqogQmxsMZB0(this));
    }

    static /* synthetic */ Boolean lambda$initListener$0(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4) throws Exception {
        boolean z = (TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(charSequence2) || TextUtils.isEmpty(charSequence3) || TextUtils.isEmpty(charSequence4)) ? false : true;
        return Boolean.valueOf(z);
    }

    public static /* synthetic */ void lambda$initListener$2(AnchorAuthActivity anchorAuthActivity, View view) {
        anchorAuthActivity.initTvCountryCodeDrawable(true);
        if (anchorAuthActivity.countryCodeList == null || anchorAuthActivity.countryCodeList.size() <= 0) {
            ((AnchorAuthPresenter) anchorAuthActivity.mPresenter).onCountryCode();
        } else {
            anchorAuthActivity.showPhoneCountryCodeDialog(anchorAuthActivity.countryCodeList);
        }
    }

    private void sendPhoneCode() {
        k.interval(0, 1, TimeUnit.SECONDS).take(61).map(new -$$Lambda$AnchorAuthActivity$5uukOjQ2SRueA9bTi-ov4bskStQ(this)).observeOn(wd.a()).doOnSubscribe(new -$$Lambda$AnchorAuthActivity$O8Ljcjr8EqMKd6-ceQfPjU5TPmA(this)).compose(bindToLifecycle()).subscribe(new r<Long>() {
            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
            }

            public void onNext(Long l) {
                AnchorAuthActivity.this.tvSendCode.setText(AnchorAuthActivity.this.getString(R.string.fq_second, new Object[]{String.valueOf(l)}));
            }

            public void onComplete() {
                AnchorAuthActivity.this.tvSendCode.setText(R.string.fq_get_code);
                AnchorAuthActivity.this.tvSendCode.setEnabled(true);
            }
        });
    }

    public static /* synthetic */ void lambda$sendPhoneCode$4(AnchorAuthActivity anchorAuthActivity, b bVar) throws Exception {
        anchorAuthActivity.showToast(R.string.fq_code_send_suc);
        anchorAuthActivity.tvSendCode.setEnabled(false);
    }

    private void initFile() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            this.tempFrontImgFile = new File(Environment.getExternalStorageDirectory(), "/tomatoLive/image/tempFrontImg.jpg");
            File parentFile = this.tempFrontImgFile.getParentFile();
            this.parentImgPath = this.tempFrontImgFile.getParent();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            parentFile = new File(Environment.getExternalStorageDirectory(), "/tomatoLive/image/.nomedia");
            if (!parentFile.exists()) {
                try {
                    parentFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.tempBackImgFile = new File(Environment.getExternalStorageDirectory(), "/tomatoLive/image/tempBackImg.jpg");
            return;
        }
        o.b(getResources().getString(R.string.fq_no_sd_card));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_up_idcrad_front || id == R.id.fl_id_card_front) {
            this.isFront = true;
            showTakePhotoDialog(new MyActionListener(this, null));
        } else if (id == R.id.iv_up_idcrad_back || id == R.id.fl_id_card_back) {
            this.isFront = false;
            showTakePhotoDialog(new MyActionListener(this, null));
        } else if (id == R.id.tv_send_code) {
            if (TextUtils.isEmpty(this.countryCode)) {
                showToast(R.string.fq_choose_phone_encode);
            } else {
                ((AnchorAuthPresenter) this.mPresenter).onSendPhoneCode(u.a(this.etPhone.getText().toString().trim()), this.countryCode);
            }
        }
    }

    private void commitInfo() {
        if (this.upFrontFile != null && this.upFrontFile.exists() && this.upBackFile != null && this.upBackFile.exists()) {
            if (this.loadingDialog != null) {
                this.loadingDialog.show();
            }
            ((AnchorAuthPresenter) this.mPresenter).onUpload(this.upFrontFile.getAbsolutePath(), true);
        }
    }

    private void showTakePhotoDialog(ActionSheetOperateListener actionSheetOperateListener) {
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.take_photo_text);
        ArrayList arrayList = new ArrayList();
        for (String menuEntity : stringArray) {
            arrayList.add(new MenuEntity(menuEntity));
        }
        ActionSheetView.showOperateCancelDialog(this.mContext, arrayList, actionSheetOperateListener);
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 10) {
            String str = (String) Matisse.obtainPathResult(intent).get(0);
            if (isPicUseAge(str)) {
                picPress(new File(str), Boolean.valueOf(this.isFront));
            } else {
                showToast(R.string.fq_pic_format_error);
            }
        } else if (i == 908) {
            picPress(this.isFront ? this.tempFrontImgFile : this.tempBackImgFile, Boolean.valueOf(this.isFront));
        }
    }

    private boolean isPicUseAge(String str) {
        boolean z = false;
        if (str == null || TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\.");
        str = split[split.length - 1].toLowerCase();
        if (str.equals("jpg") || str.equals("jpeg") || str.equals("png")) {
            z = true;
        }
        return z;
    }

    private void picPress(File file, final Boolean bool) {
        if (file == null || !file.exists()) {
            showToast(R.string.fq_pic_compress_fail);
            return;
        }
        try {
            d.a((Context) this).a(file).a(50).a(this.tempFrontImgFile.getParent()).a(new e() {
                public void onStart() {
                }

                public void onSuccess(File file) {
                    if (bool.booleanValue()) {
                        AnchorAuthActivity.this.upFrontFile = file;
                        AnchorAuthActivity.this.flIdCardFront.setVisibility(4);
                        AnchorAuthActivity.this.mIdCardFront.setVisibility(0);
                        i.b(AnchorAuthActivity.this.mContext, AnchorAuthActivity.this.mIdCardFront, AnchorAuthActivity.this.upFrontFile, 6);
                        AnchorAuthActivity.this.setSubmitAble();
                        return;
                    }
                    AnchorAuthActivity.this.upBackFile = file;
                    AnchorAuthActivity.this.flIdCardBack.setVisibility(4);
                    AnchorAuthActivity.this.mIdCardBack.setVisibility(0);
                    i.b(AnchorAuthActivity.this.mContext, AnchorAuthActivity.this.mIdCardBack, AnchorAuthActivity.this.upBackFile, 6);
                    AnchorAuthActivity.this.setSubmitAble();
                }

                public void onError(Throwable th) {
                    AnchorAuthActivity.this.showToast(R.string.fq_pic_compress_fail);
                }
            }).a();
        } catch (Exception e) {
            e.printStackTrace();
            showToast(R.string.fq_pic_compress_fail);
        }
    }

    private void setSubmitAble() {
        String trim = this.etName.getText().toString().trim();
        String trim2 = this.etIdCard.getText().toString().trim();
        String a = u.a(this.etPhone.getText().toString().trim());
        String trim3 = this.etCode.getText().toString().trim();
        trim = EmojiParser.a(trim);
        trim2 = EmojiParser.a(trim2);
        if (TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2) || TextUtils.isEmpty(a) || TextUtils.isEmpty(trim3) || this.upFrontFile == null || !this.upFrontFile.exists() || this.upBackFile == null || !this.upBackFile.exists()) {
            this.tvSubmit.setEnabled(false);
        } else {
            this.tvSubmit.setEnabled(true);
        }
    }

    public void onResultError(int i) {
        runOnUiThread(new -$$Lambda$AnchorAuthActivity$YxqwtFw-vV2p_hcotJLfiZQol9Q(this));
    }

    public void onAuthSuccess() {
        runOnUiThread(new -$$Lambda$AnchorAuthActivity$YxqwtFw-vV2p_hcotJLfiZQol9Q(this));
        showToast(R.string.fq_submit_suc);
        g.d(this.parentImgPath);
        Intent intent = new Intent(this.mContext, AnchorAuthResultActivity.class);
        intent.putExtra("authType", 0);
        startActivity(intent);
        finish();
    }

    public void onUploadSuc(UploadFileEntity uploadFileEntity, boolean z) {
        if (z) {
            this.idCardFront = uploadFileEntity.getFilename();
            ((AnchorAuthPresenter) this.mPresenter).onUpload(this.upBackFile.getAbsolutePath(), false);
            return;
        }
        String trim = this.etName.getText().toString().trim();
        String trim2 = this.etIdCard.getText().toString().trim();
        String a = u.a(this.etPhone.getText().toString().trim());
        String trim3 = this.etCode.getText().toString().trim();
        ((AnchorAuthPresenter) this.mPresenter).onAnchorAuth(EmojiParser.a(trim), EmojiParser.a(trim2), a, trim3, this.idCardFront, uploadFileEntity.getFilename(), this.countryCode);
    }

    public void onUploadFail(boolean z) {
        showToast(R.string.fq_pic_upload_fail);
        dismissDialog();
    }

    public void onSendPhoneCodeSuccess() {
        sendPhoneCode();
    }

    public void onCountryPhoneCodeSuccess(List<CountryCodeEntity> list) {
        if (list != null) {
            this.countryCodeList = list;
            showPhoneCountryCodeDialog(list);
        }
    }

    private void initTvCountryCodeDrawable(boolean z) {
        this.tvCountryCode.setCompoundDrawablesWithIntrinsicBounds(null, null, ContextCompat.getDrawable(this.mContext, z ? R.drawable.fq_ic_code_arrow_up : R.drawable.fq_ic_code_arrow_down), null);
    }

    private void showPhoneCountryCodeDialog(List<CountryCodeEntity> list) {
        BottomDialogUtils.showPhoneCountryCodeDialog(this.mContext, list, new -$$Lambda$AnchorAuthActivity$AJlUFELmCTMs5MFSV0onCZ_qTEE(this)).setOnDismissListener(new -$$Lambda$AnchorAuthActivity$Kv3o16KxhmUFr7T08XdtMz4H4IA(this));
    }

    public static /* synthetic */ void lambda$showPhoneCountryCodeDialog$5(AnchorAuthActivity anchorAuthActivity, CountryCodeEntity countryCodeEntity, int i) {
        anchorAuthActivity.countryCode = countryCodeEntity.countryCode;
        anchorAuthActivity.tvCountryCode.setText(String.format(anchorAuthActivity.getString(R.string.fq_add), new Object[]{countryCodeEntity.countryCode}));
    }

    private void dismissDialog() {
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
    }
}
