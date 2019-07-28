package com.tomatolive.library.ui.activity.live;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.blankj.utilcode.util.o;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.ui.presenter.CoverUploadPresenter;
import com.tomatolive.library.ui.view.dialog.LoadingDialog;
import com.tomatolive.library.ui.view.iview.ICoverUploadView;
import com.tomatolive.library.ui.view.widget.ActionSheetView;
import com.tomatolive.library.ui.view.widget.ActionSheetView.ActionSheetOperateListener;
import com.tomatolive.library.ui.view.widget.matisse.Matisse;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;
import com.tomatolive.library.ui.view.widget.matisse.engine.impl.GlideEngine;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.yalantis.ucrop.UCrop;
import defpackage.mh;
import defpackage.sh;
import defpackage.wl;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import top.zibin.luban.d;
import top.zibin.luban.e;

@Deprecated
public class CoverUploadActivity extends BaseActivity<CoverUploadPresenter> implements OnClickListener, ICoverUploadView {
    private static final String BOTH_UP_LIVE = "both_up_live";
    private static final String BOTH_UP_NAV = "both_up_nav";
    private static final int LIVE_REQUESTCODE = 10;
    private static final int NAV_REQUESTCODE = 20;
    private static final String ONLY_LIVE = "only_live";
    private static final String ONLY_NAV = "only_nav";
    private boolean isLiveCover = true;
    private String liveCoverUrl = "";
    private File liveImageFile;
    private LoadingDialog loadingDialog;
    private ImageView mLiveCover;
    private ImageView mNavCover;
    private RxPermissions mRxPermissions;
    private String navCoverUrl = "";
    private File navImageFile;
    private String parentImgPath = "";
    private File upLiveFile;
    private File upNavFile;

    private class MyActionListener implements ActionSheetOperateListener {
        private final int mRequestCode;

        public void onCancel() {
        }

        /* synthetic */ MyActionListener(CoverUploadActivity coverUploadActivity, int i, AnonymousClass1 anonymousClass1) {
            this(i);
        }

        private MyActionListener(int i) {
            this.mRequestCode = i;
        }

        public void onOperateListener(MenuEntity menuEntity, int i) {
            if (i == 0) {
                CoverUploadActivity.this.mRxPermissions.request(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new sh<Boolean>() {
                    public void accept(Boolean bool) {
                        if (!bool.booleanValue()) {
                            o.b(CoverUploadActivity.this.getResources().getString(R.string.fq_no_permission));
                        } else if (Environment.getExternalStorageState().equals("mounted")) {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            if (intent.resolveActivity(CoverUploadActivity.this.getPackageManager()) != null) {
                                intent.putExtra("output", MyActionListener.this.parUri(MyActionListener.this.getFile()));
                                intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                            }
                            CoverUploadActivity.this.startActivityForResult(intent, 908);
                        } else {
                            o.b(CoverUploadActivity.this.getResources().getString(R.string.fq_no_sd_card));
                        }
                    }
                });
            } else if (i == 1) {
                CoverUploadActivity.this.mRxPermissions.request(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new wl<Boolean>() {
                    public void accept(Boolean bool) {
                        if (bool.booleanValue()) {
                            Matisse.from(CoverUploadActivity.this).choose(MimeType.of(MimeType.JPEG, MimeType.PNG)).showSingleMediaType(true).capture(false).thumbnailScale(0.85f).imageEngine(new GlideEngine()).forResult(MyActionListener.this.mRequestCode);
                            return;
                        }
                        o.b(CoverUploadActivity.this.getResources().getString(R.string.fq_no_permission_write));
                    }
                });
            }
        }

        private File getFile() {
            if (CoverUploadActivity.this.isLiveCover) {
                return CoverUploadActivity.this.liveImageFile;
            }
            return CoverUploadActivity.this.navImageFile;
        }

        private Uri parUri(File file) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(CoverUploadActivity.this.getPackageName());
            stringBuilder.append(".provider");
            String stringBuilder2 = stringBuilder.toString();
            if (VERSION.SDK_INT > 23) {
                return FileProvider.getUriForFile(CoverUploadActivity.this.mContext, stringBuilder2, file);
            }
            return Uri.fromFile(file);
        }
    }

    /* Access modifiers changed, original: protected */
    public CoverUploadPresenter createPresenter() {
        return new CoverUploadPresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_cover_upload;
    }

    public void initView(Bundle bundle) {
        this.mRxPermissions = new RxPermissions(this);
        this.mLiveCover = (ImageView) findViewById(R.id.iv_live_cover);
        this.mNavCover = (ImageView) findViewById(R.id.iv_nav_cover);
        setActivityTitle(R.string.fq_up_img_title);
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this.mContext, getString(R.string.hint_text_dialog_loading_data));
        }
    }

    public void initListener() {
        super.initListener();
        initFile();
        this.mLiveCover.setOnClickListener(this);
        this.mNavCover.setOnClickListener(this);
        mh.a(findViewById(R.id.tv_start_upload)).throttleFirst(5, TimeUnit.SECONDS).subscribe(new sh<Object>() {
            public void accept(Object obj) {
                if (CoverUploadActivity.this.loadingDialog != null) {
                    CoverUploadActivity.this.loadingDialog.show();
                }
                CoverUploadActivity.this.upLoadCover();
            }
        });
    }

    private void initFile() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            this.liveImageFile = new File(Environment.getExternalStorageDirectory(), "/tomatoLive/image/liveImg.jpg");
            File parentFile = this.liveImageFile.getParentFile();
            this.parentImgPath = this.liveImageFile.getParent();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            this.navImageFile = new File(Environment.getExternalStorageDirectory(), "/tomatoLive/image/navImg.jpg");
            return;
        }
        o.b(getResources().getString(R.string.fq_no_sd_card));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_live_cover) {
            this.isLiveCover = true;
            showTakePhotoDialog(new MyActionListener(this, 10, null));
        } else if (id == R.id.iv_nav_cover) {
            this.isLiveCover = false;
            showTakePhotoDialog(new MyActionListener(this, 20, null));
        }
    }

    private void upLoadCover() {
        boolean c = g.c(this.upLiveFile);
        boolean c2 = g.c(this.upNavFile);
        if (!c && !c2) {
            o.b(getResources().getString(R.string.fq_need_one_pic));
            dismissDialog();
        } else if (c && c2) {
            ((CoverUploadPresenter) this.mPresenter).onUpload(this.upLiveFile.getAbsolutePath(), BOTH_UP_LIVE);
        } else if (c) {
            ((CoverUploadPresenter) this.mPresenter).onUpload(this.upLiveFile.getAbsolutePath(), ONLY_LIVE);
        } else {
            ((CoverUploadPresenter) this.mPresenter).onUpload(this.upNavFile.getAbsolutePath(), ONLY_NAV);
        }
    }

    private void finishActivity() {
        Intent intent = new Intent();
        intent.putExtra("imagePath", this.liveCoverUrl);
        setResult(-1, intent);
        finish();
    }

    private void showTakePhotoDialog(ActionSheetOperateListener actionSheetOperateListener) {
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.take_photo_text);
        ArrayList arrayList = new ArrayList();
        for (String menuEntity : stringArray) {
            arrayList.add(new MenuEntity(menuEntity));
        }
        ActionSheetView.showOperateCancelDialog(this.mContext, arrayList, actionSheetOperateListener);
    }

    private void picPress(File file, final Boolean bool) {
        if (file == null || !file.exists()) {
            showToast(R.string.fq_pic_compress_fail);
        } else {
            d.a((Context) this).a(file).a(50).a(this.liveImageFile.getParent()).a(new e() {
                public void onStart() {
                }

                public void onSuccess(File file) {
                    if (bool.booleanValue()) {
                        CoverUploadActivity.this.upLiveFile = file;
                        i.a(CoverUploadActivity.this.mContext, CoverUploadActivity.this.mLiveCover, CoverUploadActivity.this.upLiveFile, R.drawable.fq_ic_placeholder);
                        return;
                    }
                    CoverUploadActivity.this.upNavFile = file;
                    i.a(CoverUploadActivity.this.mContext, CoverUploadActivity.this.mNavCover, CoverUploadActivity.this.upNavFile, R.drawable.fq_ic_placeholder);
                }

                public void onError(Throwable th) {
                    CoverUploadActivity.this.showToast(R.string.fq_pic_compress_fail);
                }
            }).a();
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

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10) {
            if (i != 20) {
                if (i != 69) {
                    if (i != 908 || i2 != -1) {
                        return;
                    }
                    if (this.isLiveCover) {
                        if (this.liveImageFile == null || !this.liveImageFile.exists()) {
                            showToast(R.string.fq_pic_take_fail);
                        }
                        UCrop.of(Uri.fromFile(this.liveImageFile), Uri.fromFile(this.liveImageFile)).withAspectRatio(1.0f, 1.0f).withMaxResultSize(1080, 1080).start(this);
                        return;
                    }
                    if (this.navImageFile == null || !this.navImageFile.exists()) {
                        showToast(R.string.fq_pic_take_fail);
                    }
                    UCrop.of(Uri.fromFile(this.navImageFile), Uri.fromFile(this.navImageFile)).withAspectRatio(16.0f, 9.0f).withMaxResultSize(1080, 608).start(this);
                } else if (i2 == -1) {
                    picPress(this.isLiveCover ? this.liveImageFile : this.navImageFile, Boolean.valueOf(this.isLiveCover));
                } else if (i2 == 96) {
                    showToast(R.string.fq_pic_cut_fail);
                }
            } else if (i2 != -1) {
            } else {
                if (isPicUseAge((String) Matisse.obtainPathResult(intent).get(0))) {
                    UCrop.of((Uri) Matisse.obtainResult(intent).get(0), Uri.fromFile(this.navImageFile)).withAspectRatio(16.0f, 9.0f).withMaxResultSize(1080, 608).start(this);
                } else {
                    showToast(R.string.fq_pic_format_error);
                }
            }
        } else if (i2 != -1) {
        } else {
            if (isPicUseAge((String) Matisse.obtainPathResult(intent).get(0))) {
                UCrop.of((Uri) Matisse.obtainResult(intent).get(0), Uri.fromFile(this.liveImageFile)).withAspectRatio(1.0f, 1.0f).withMaxResultSize(1080, 1080).start(this);
            } else {
                showToast(R.string.fq_pic_format_error);
            }
        }
    }

    public void onUploadCoverSuccess() {
        dismissDialog();
        o.b(getResources().getString(R.string.fq_up_cover_succ));
        g.d(this.parentImgPath);
        finishActivity();
    }

    public void onUploadCoverFail() {
        dismissDialog();
        o.b(getResources().getString(R.string.fq_up_cover_fail));
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    public void onUploadSuc(com.tomatolive.library.model.UploadFileEntity r3, java.lang.String r4) {
        /*
        r2 = this;
        r0 = r4.hashCode();
        r1 = -2068758000; // 0xffffffff84b14210 float:-4.167317E-36 double:NaN;
        if (r0 == r1) goto L_0x0037;
    L_0x0009:
        r1 = -1118887939; // 0xffffffffbd4f1ffd float:-0.050567616 double:NaN;
        if (r0 == r1) goto L_0x002d;
    L_0x000e:
        r1 = -325839534; // 0xffffffffec941552 float:-1.4321736E27 double:NaN;
        if (r0 == r1) goto L_0x0023;
    L_0x0013:
        r1 = 292959647; // 0x1176359f float:1.9422502E-28 double:1.44741297E-315;
        if (r0 == r1) goto L_0x0019;
    L_0x0018:
        goto L_0x0041;
    L_0x0019:
        r0 = "only_live";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0041;
    L_0x0021:
        r4 = 0;
        goto L_0x0042;
    L_0x0023:
        r0 = "both_up_live";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0041;
    L_0x002b:
        r4 = 2;
        goto L_0x0042;
    L_0x002d:
        r0 = "both_up_nav";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0041;
    L_0x0035:
        r4 = 3;
        goto L_0x0042;
    L_0x0037:
        r0 = "only_nav";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x0041;
    L_0x003f:
        r4 = 1;
        goto L_0x0042;
    L_0x0041:
        r4 = -1;
    L_0x0042:
        switch(r4) {
            case 0: goto L_0x0080;
            case 1: goto L_0x006e;
            case 2: goto L_0x0058;
            case 3: goto L_0x0046;
            default: goto L_0x0045;
        };
    L_0x0045:
        goto L_0x0091;
    L_0x0046:
        r3 = r3.getFilename();
        r2.navCoverUrl = r3;
        r3 = r2.mPresenter;
        r3 = (com.tomatolive.library.ui.presenter.CoverUploadPresenter) r3;
        r4 = r2.liveCoverUrl;
        r0 = r2.navCoverUrl;
        r3.uploadCover(r4, r0);
        goto L_0x0091;
    L_0x0058:
        r3 = r3.getFilename();
        r2.liveCoverUrl = r3;
        r3 = r2.mPresenter;
        r3 = (com.tomatolive.library.ui.presenter.CoverUploadPresenter) r3;
        r4 = r2.upNavFile;
        r4 = r4.getAbsolutePath();
        r0 = "both_up_nav";
        r3.onUpload(r4, r0);
        goto L_0x0091;
    L_0x006e:
        r3 = r3.getFilename();
        r2.navCoverUrl = r3;
        r3 = r2.mPresenter;
        r3 = (com.tomatolive.library.ui.presenter.CoverUploadPresenter) r3;
        r4 = r2.liveCoverUrl;
        r0 = r2.navCoverUrl;
        r3.uploadCover(r4, r0);
        goto L_0x0091;
    L_0x0080:
        r3 = r3.getFilename();
        r2.liveCoverUrl = r3;
        r3 = r2.mPresenter;
        r3 = (com.tomatolive.library.ui.presenter.CoverUploadPresenter) r3;
        r4 = r2.liveCoverUrl;
        r0 = r2.navCoverUrl;
        r3.uploadCover(r4, r0);
    L_0x0091:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.live.CoverUploadActivity.onUploadSuc(com.tomatolive.library.model.UploadFileEntity, java.lang.String):void");
    }

    public void onUploadFail() {
        dismissDialog();
    }

    private void dismissDialog() {
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
    }
}
