package com.tomatolive.library.ui.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.k;
import com.blankj.utilcode.util.o;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tomatolive.library.R;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.model.UploadFileEntity;
import com.tomatolive.library.ui.activity.home.WebViewActivity;
import com.tomatolive.library.ui.adapter.LabelMenuAdapter;
import com.tomatolive.library.ui.view.dialog.LabelDialog;
import com.tomatolive.library.ui.view.dialog.LoadingDialog;
import com.tomatolive.library.ui.view.widget.ActionSheetView;
import com.tomatolive.library.ui.view.widget.ActionSheetView.ActionSheetOperateListener;
import com.tomatolive.library.ui.view.widget.matisse.Matisse;
import com.tomatolive.library.ui.view.widget.matisse.MimeType;
import com.tomatolive.library.ui.view.widget.matisse.engine.impl.GlideEngine;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.CaptureStrategy;
import com.tomatolive.library.ui.view.widget.matisse.internal.utils.MediaStoreCompat;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.e;
import com.tomatolive.library.utils.emoji.EmojiParser;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.utils.r.a;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.mh;
import defpackage.sh;
import defpackage.wd;
import defpackage.xl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import top.zibin.luban.d;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class PreStartLiveView extends RelativeLayout implements OnClickListener {
    private AppCompatCheckBox cbProtocol;
    private EditText etTitle;
    private ImageView ivCover;
    private LabelDialog labelDialog;
    private LabelMenuAdapter labelMenuAdapter;
    private boolean lastMirrorOpen;
    private LoadingDialog loadingDialog;
    private Activity mActivity;
    private Context mContext;
    private MediaStoreCompat mediaStoreCompat;
    private OnPreStartLiveCallback onPreStartLiveCallback;
    private File tempCoverImgFile;
    private TextView tvBeauty;
    private TextView tvCamera;
    private TextView tvLabel;
    private TextView tvMirror;
    private TextView tvStartLive;

    public interface OnPreStartLiveCallback {
        void onClickBeautyListener();

        void onClickCameraListener();

        void onClickClosedListener();

        void onClickMirrorListener();

        void onClickStartLiveListener(String str, String str2);
    }

    public PreStartLiveView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mActivity = (Activity) context;
        this.mediaStoreCompat = new MediaStoreCompat(this.mActivity);
        this.mediaStoreCompat.setCaptureStrategy(new CaptureStrategy(true, getAuthority()));
        inflate(context, R.layout.fq_layout_pre_start_live_view, this);
        this.ivCover = (ImageView) findViewById(R.id.iv_upload_cover);
        this.etTitle = (EditText) findViewById(R.id.et_live_title);
        this.tvLabel = (TextView) findViewById(R.id.tv_label_sort);
        this.tvCamera = (TextView) findViewById(R.id.tv_camera_icon);
        this.tvBeauty = (TextView) findViewById(R.id.tv_beauty_icon);
        this.tvMirror = (TextView) findViewById(R.id.tv_mirror_icon);
        this.tvStartLive = (TextView) findViewById(R.id.tv_start_live);
        this.cbProtocol = (AppCompatCheckBox) findViewById(R.id.cb_protocol);
        createTempFile();
        createLoadingDialog();
        initListener();
    }

    private void createLoadingDialog() {
        if (this.loadingDialog == null) {
            this.loadingDialog = new LoadingDialog(this.mContext, this.mContext.getString(R.string.fq_uploading_cover));
        }
    }

    private String getAuthority() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mContext.getPackageName());
        stringBuilder.append(".provider");
        return stringBuilder.toString();
    }

    private void initListener() {
        findViewById(R.id.iv_closed).setOnClickListener(this);
        r.a().a(this.ivCover, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                PreStartLiveView.this.goToUploadCover();
            }
        });
        r.a().a(findViewById(R.id.rl_upload_cover), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                PreStartLiveView.this.goToUploadCover();
            }
        });
        r.a().a(this.tvLabel, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                PreStartLiveView.this.showLabel();
            }
        });
        r.a().a(this.tvCamera, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                if (PreStartLiveView.this.onPreStartLiveCallback != null) {
                    PreStartLiveView.this.onPreStartLiveCallback.onClickCameraListener();
                }
            }
        });
        r.a().a(this.tvBeauty, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                if (PreStartLiveView.this.onPreStartLiveCallback != null) {
                    PreStartLiveView.this.onPreStartLiveCallback.onClickBeautyListener();
                }
            }
        });
        r.a().a(this.tvMirror, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                if (PreStartLiveView.this.onPreStartLiveCallback != null) {
                    PreStartLiveView.this.onPreStartLiveCallback.onClickMirrorListener();
                }
            }
        });
        r.a().a(findViewById(R.id.tv_protocol), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new a() {
            public void action(Object obj) {
                Intent intent = new Intent(PreStartLiveView.this.mContext, WebViewActivity.class);
                intent.putExtra(OnNativeInvokeListener.ARG_URL, g.i("userAgreement.html"));
                intent.putExtra("title", PreStartLiveView.this.mContext.getString(R.string.fq_user_protocol));
                PreStartLiveView.this.mContext.startActivity(intent);
            }
        });
        mh.a(this.tvStartLive).throttleFirst(2, TimeUnit.SECONDS).subscribe(new sh<Object>() {
            public void accept(Object obj) {
                if (PreStartLiveView.this.onPreStartLiveCallback != null) {
                    com.tomatolive.library.utils.a.a(PreStartLiveView.this.tvStartLive);
                    if (PreStartLiveView.this.cbProtocol.isChecked()) {
                        String trim = PreStartLiveView.this.etTitle.getText().toString().trim();
                        String trim2 = PreStartLiveView.this.tvLabel.getText().toString().trim();
                        if (e.a(trim)) {
                            PreStartLiveView.this.showToast(R.string.fq_no_emoji);
                        } else if (TextUtils.isEmpty(EmojiParser.a(trim))) {
                            PreStartLiveView.this.showToast(R.string.fq_input_title);
                        } else if (TextUtils.isEmpty(trim2)) {
                            PreStartLiveView.this.showToast(R.string.fq_input_label);
                        } else {
                            k.a().a("last_topic", trim);
                            PreStartLiveView.this.onPreStartLiveCallback.onClickStartLiveListener(trim, trim2);
                        }
                    } else {
                        PreStartLiveView.this.showToast(R.string.fq_check_law);
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_upload_cover || id == R.id.iv_upload_cover) {
            goToUploadCover();
        } else if (id == R.id.tv_label_sort) {
            showLabel();
        } else if (id == R.id.tv_camera_icon) {
            if (this.onPreStartLiveCallback != null) {
                this.onPreStartLiveCallback.onClickCameraListener();
            }
        } else if (id == R.id.tv_beauty_icon) {
            if (this.onPreStartLiveCallback != null) {
                this.onPreStartLiveCallback.onClickBeautyListener();
            }
        } else if (id == R.id.tv_mirror_icon) {
            if (this.onPreStartLiveCallback != null) {
                this.onPreStartLiveCallback.onClickMirrorListener();
            }
        } else if (id == R.id.iv_closed && this.onPreStartLiveCallback != null) {
            this.onPreStartLiveCallback.onClickClosedListener();
        }
    }

    public void setOnPreStartLiveCallback(OnPreStartLiveCallback onPreStartLiveCallback) {
        this.onPreStartLiveCallback = onPreStartLiveCallback;
    }

    public String getLiveTitle() {
        return this.etTitle.getText().toString().trim();
    }

    public String getLiveTag() {
        return this.tvLabel.getText().toString().trim();
    }

    public void onTagListSuccess(List<LabelEntity> list) {
        this.labelMenuAdapter = new LabelMenuAdapter(R.layout.fq_item_live_label, list);
        this.labelDialog = LabelDialog.newInstance(this.labelMenuAdapter);
        this.labelMenuAdapter.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                PreStartLiveView.this.dismissLabelDialog();
                LabelEntity labelEntity = (LabelEntity) baseQuickAdapter.getItem(i);
                PreStartLiveView.this.tvLabel.setText(labelEntity.name);
                PreStartLiveView.this.labelMenuAdapter.setCheckItem(i);
                k.a().a("lastLabel", labelEntity.name);
            }
        });
    }

    public void updatePreStartLiveInfo(String str) {
        i.d(this.mContext, this.ivCover, str, 6);
        str = k.a().b("last_topic");
        this.etTitle.setText(TextUtils.isEmpty(str) ? "" : str);
        this.etTitle.setSelection(TextUtils.isEmpty(str) ? 0 : str.length());
        this.tvLabel.setText(k.a().b("lastLabel", ""));
    }

    public void onCameraActivityResult() {
        Uri currentPhotoUri = this.mediaStoreCompat.getCurrentPhotoUri();
        if (currentPhotoUri == null) {
            showToast(R.string.fq_pic_take_fail);
        } else {
            picCrop(currentPhotoUri);
        }
    }

    public void onAlbumActivityResult(Intent intent) {
        if (intent != null) {
            if (isPicUseAge((String) Matisse.obtainPathResult(intent).get(0))) {
                Uri uri = (Uri) Matisse.obtainResult(intent).get(0);
                if (uri == null) {
                    showToast(R.string.fq_pic_format_error);
                    return;
                } else {
                    picCrop(uri);
                    return;
                }
            }
            showToast(R.string.fq_pic_format_error);
        }
    }

    private void picCrop(@NonNull Uri uri) {
        UCrop.of(uri, Uri.fromFile(this.tempCoverImgFile)).withAspectRatio(1.0f, 1.0f).withMaxResultSize(1080, 1080).start(this.mActivity);
    }

    public void picCompression() {
        if (this.tempCoverImgFile == null || !this.tempCoverImgFile.exists()) {
            showToast(R.string.fq_pic_inexistence);
        } else {
            d.a(this.mContext).a(this.tempCoverImgFile).a(50).a(this.tempCoverImgFile.getParent()).a(new top.zibin.luban.e() {
                public void onStart() {
                }

                public void onSuccess(File file) {
                    i.b(PreStartLiveView.this.mContext, PreStartLiveView.this.ivCover, file, 6);
                    PreStartLiveView.this.uploadCoverImg(file);
                }

                public void onError(Throwable th) {
                    PreStartLiveView.this.showToast(R.string.fq_pic_compress_fail);
                }
            }).a();
        }
    }

    public void setTMirrorDrawableTop(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.tvMirror.setCompoundDrawables(null, drawable, null, null);
    }

    private void showToast(@StringRes int i) {
        o.a(i);
    }

    private void showTakePhotoDialog(ActionSheetOperateListener actionSheetOperateListener) {
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.take_photo_text);
        ArrayList arrayList = new ArrayList();
        for (String menuEntity : stringArray) {
            arrayList.add(new MenuEntity(menuEntity));
        }
        ActionSheetView.showOperateCancelDialog(this.mContext, arrayList, actionSheetOperateListener);
    }

    private void showLabel() {
        if (this.labelDialog != null && !this.labelDialog.isAdded() && (this.mContext instanceof FragmentActivity)) {
            this.labelDialog.show(((FragmentActivity) this.mContext).getSupportFragmentManager());
        }
    }

    private void dismissLabelDialog() {
        if (this.labelDialog != null && this.labelDialog.isAdded()) {
            this.labelDialog.dismiss();
        }
    }

    private void createTempFile() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(com.blankj.utilcode.util.i.a());
        stringBuilder.append(File.separator);
        stringBuilder.append("imgCache");
        this.tempCoverImgFile = new File(stringBuilder.toString(), "tempCoverImg.jpg");
        com.blankj.utilcode.util.e.c(this.tempCoverImgFile);
    }

    private void uploadCoverImg(File file) {
        showLoadingDialog();
        ApiRetrofit.getInstance().getApiService().uploadFile(b.c(), b.a(file)).map(new ServerResultFunction<UploadFileEntity>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<UploadFileEntity>() {
            public void onSuccess(UploadFileEntity uploadFileEntity) {
                if (uploadFileEntity == null) {
                    PreStartLiveView.this.showToast(R.string.fq_up_cover_fail);
                    PreStartLiveView.this.dismissLoadingDialog();
                    return;
                }
                PreStartLiveView.this.uploadCoverImg(uploadFileEntity.getFilename());
            }

            public void onError(int i, String str) {
                PreStartLiveView.this.showToast(R.string.fq_up_cover_fail);
                PreStartLiveView.this.dismissLoadingDialog();
            }
        }));
    }

    private void uploadCoverImg(String str) {
        ApiRetrofit.getInstance().getApiService().getUploadLiveCoverService(new RequestParams().getUploadLiveCoverParams(str)).map(new ServerResultFunction<Object>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<Object>() {
            public void onSuccess(Object obj) {
                PreStartLiveView.this.showToast(R.string.fq_up_cover_succ);
                PreStartLiveView.this.dismissLoadingDialog();
            }

            public void onError(int i, String str) {
                PreStartLiveView.this.showToast(R.string.fq_up_cover_fail);
                PreStartLiveView.this.dismissLoadingDialog();
            }
        }));
    }

    private void showLoadingDialog() {
        if (this.loadingDialog != null) {
            this.loadingDialog.show();
        }
    }

    private void dismissLoadingDialog() {
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
        }
    }

    private boolean isPicUseAge(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("\\.");
        str = split[split.length - 1].toLowerCase();
        if (TextUtils.equals(str, "jpg") || TextUtils.equals(str, "jpeg") || TextUtils.equals(str, "png")) {
            z = true;
        }
        return z;
    }

    public void goToUploadCover() {
        final RxPermissions rxPermissions = new RxPermissions(this.mActivity);
        showTakePhotoDialog(new ActionSheetOperateListener() {
            public void onCancel() {
            }

            public void onOperateListener(MenuEntity menuEntity, int i) {
                switch (i) {
                    case 0:
                        rxPermissions.request(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new io.reactivex.r<Boolean>() {
                            public void onComplete() {
                            }

                            public void onError(Throwable th) {
                            }

                            public void onSubscribe(io.reactivex.disposables.b bVar) {
                            }

                            public void onNext(Boolean bool) {
                                if (!bool.booleanValue()) {
                                    PreStartLiveView.this.showToast(R.string.fq_no_permission);
                                } else if (PreStartLiveView.this.mediaStoreCompat != null) {
                                    PreStartLiveView.this.mediaStoreCompat.dispatchCaptureIntent(PreStartLiveView.this.mContext, 908);
                                }
                            }
                        });
                        return;
                    case 1:
                        rxPermissions.request(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}).subscribe(new io.reactivex.r<Boolean>() {
                            public void onComplete() {
                            }

                            public void onError(Throwable th) {
                            }

                            public void onSubscribe(io.reactivex.disposables.b bVar) {
                            }

                            public void onNext(Boolean bool) {
                                if (bool.booleanValue()) {
                                    Matisse.from(PreStartLiveView.this.mActivity).choose(MimeType.of(MimeType.JPEG, MimeType.PNG)).showSingleMediaType(true).capture(false).thumbnailScale(0.85f).imageEngine(new GlideEngine()).forResult(909);
                                    return;
                                }
                                PreStartLiveView.this.showToast(R.string.fq_no_permission_write);
                            }
                        });
                        return;
                    default:
                        return;
                }
            }
        });
    }
}
