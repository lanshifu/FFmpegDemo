package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.blankj.utilcode.util.o;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.d;
import com.opensource.svgaplayer.f;
import com.tomatolive.library.R;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.ui.interfaces.impl.SimpleSVGACallBack;
import com.tomatolive.library.ui.view.custom.GuardOpenDanmuView.OnAnimPlayListener;
import com.tomatolive.library.ui.view.gift.CustomAnim;
import com.tomatolive.library.ui.view.gift.GiftAnimManage;
import com.tomatolive.library.ui.view.gift.GiftAnimModel;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.m;
import com.tomatolive.library.utils.p;
import defpackage.wd;
import defpackage.wm;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LiveAnimationView extends RelativeLayout {
    private SimpleSVGACallBack carAnimCallback;
    private b carDisposable;
    private SimpleSVGACallBack giftAnimCallback;
    private GiftAnimManage giftAnimManage;
    private b giftDisposable;
    private SimpleSVGACallBack guardEnterAnimCallback;
    private SVGAImageView ivAnimCar;
    private SVGAImageView ivAnimEnter;
    private SVGAImageView ivAnimGift;
    private SVGAImageView ivAnimOpen;
    private LinearLayout llAnimGiftBg;
    private LinearLayout llLeftCarAnimView;
    private LinearLayout llLeftGuardOpenAnimView;
    private Context mContext;
    private OnGiftNotifyCallback onGiftNotifyCallback;
    private d svgaParser;

    public interface OnGiftNotifyCallback {
        void onGiftDeleteListener(GiftAnimModel giftAnimModel);

        void onGiftNotifyListener();
    }

    public LiveAnimationView(Context context) {
        super(context);
        initView(context);
    }

    public LiveAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.fq_layout_live_animation_view, this);
        this.mContext = context;
        this.llLeftGuardOpenAnimView = (LinearLayout) findViewById(R.id.ll_anim_open_bg_view);
        this.llLeftCarAnimView = (LinearLayout) findViewById(R.id.ll_anim_car_bg_view);
        this.llAnimGiftBg = (LinearLayout) findViewById(R.id.ll_anim_gift_bg_view);
        this.ivAnimGift = (SVGAImageView) findViewById(R.id.iv_anim_gift);
        this.ivAnimOpen = (SVGAImageView) findViewById(R.id.iv_anim_open);
        this.ivAnimEnter = (SVGAImageView) findViewById(R.id.iv_anim_enter);
        this.ivAnimCar = (SVGAImageView) findViewById(R.id.iv_anim_car);
        initGiftAnimManage();
    }

    private void initListener() {
        if (this.giftAnimCallback != null) {
            this.ivAnimGift.setCallback(this.giftAnimCallback);
        }
        if (this.guardEnterAnimCallback != null) {
            this.ivAnimEnter.setCallback(this.guardEnterAnimCallback);
        }
        if (this.carAnimCallback != null) {
            this.ivAnimCar.setCallback(this.carAnimCallback);
        }
    }

    private void initGiftAnimManage() {
        this.giftAnimManage = new GiftAnimManage(this.mContext);
        this.giftAnimManage.setGiftLayout(this.llAnimGiftBg, 2).setHideMode(false).setCustormAnim(new CustomAnim()).setOnDeleteGiftAnimListener(new -$$Lambda$LiveAnimationView$ohU3sUJ1snxzWH0AWlt8RniEsCA(this));
    }

    public static /* synthetic */ void lambda$initGiftAnimManage$0(LiveAnimationView liveAnimationView, GiftAnimModel giftAnimModel) {
        if (liveAnimationView.onGiftNotifyCallback != null) {
            liveAnimationView.onGiftNotifyCallback.onGiftDeleteListener(giftAnimModel);
        }
    }

    public void setAnimationCallback(SimpleSVGACallBack simpleSVGACallBack, SimpleSVGACallBack simpleSVGACallBack2, OnGiftNotifyCallback onGiftNotifyCallback, SimpleSVGACallBack simpleSVGACallBack3) {
        this.giftAnimCallback = simpleSVGACallBack;
        this.guardEnterAnimCallback = simpleSVGACallBack2;
        this.onGiftNotifyCallback = onGiftNotifyCallback;
        this.carAnimCallback = simpleSVGACallBack3;
        initListener();
    }

    public void setGiftAnimViewVisibility(int i) {
        this.ivAnimGift.setVisibility(i);
    }

    public void setGuardEnterAnimViewVisibility(int i) {
        this.ivAnimEnter.setVisibility(i);
    }

    public boolean isGiftAnimating() {
        return this.ivAnimGift.a();
    }

    public void stopGiftAnimating() {
        this.ivAnimGift.a(true);
    }

    public void loadGift(GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
        if (!(giftItemEntity.isBigAnim() || g.b(com.tomatolive.library.utils.b.a(giftItemEntity.giftDirPath, giftItemEntity.jsonname)))) {
            GiftDownLoadManager.getInstance().updateAnimOnlineSingleRes(giftItemEntity);
        }
        this.giftAnimManage.loadGift(giftAnimModel);
    }

    public void loadReceiveGift(GiftAnimModel giftAnimModel, GiftItemEntity giftItemEntity) {
        if (!(giftItemEntity.isBigAnim() || g.b(com.tomatolive.library.utils.b.a(giftItemEntity.giftDirPath, giftItemEntity.jsonname)))) {
            GiftDownLoadManager.getInstance().updateAnimOnlineSingleRes(giftItemEntity);
        }
        this.giftAnimManage.loadReceiveGift(giftAnimModel);
    }

    public void loadGiftAnimation(final String str, final String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (this.onGiftNotifyCallback != null) {
                this.onGiftNotifyCallback.onGiftNotifyListener();
            }
        } else if (this.ivAnimGift == null) {
            if (this.onGiftNotifyCallback != null) {
                this.onGiftNotifyCallback.onGiftNotifyListener();
            }
        } else {
            try {
                k.just(Boolean.valueOf(true)).map(new wm<Boolean, InputStream>() {
                    public InputStream apply(Boolean bool) throws Exception {
                        return LiveAnimationView.this.getSVGAFileInputStream(str, str2);
                    }
                }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<InputStream>() {
                    public void onComplete() {
                    }

                    public void onSubscribe(b bVar) {
                        LiveAnimationView.this.giftDisposable = bVar;
                    }

                    public void onNext(InputStream inputStream) {
                        if (inputStream == null) {
                            if (LiveAnimationView.this.onGiftNotifyCallback != null) {
                                LiveAnimationView.this.onGiftNotifyCallback.onGiftNotifyListener();
                            }
                            return;
                        }
                        if (LiveAnimationView.this.svgaParser == null) {
                            LiveAnimationView.this.svgaParser = new d(LiveAnimationView.this.mContext);
                        }
                        LiveAnimationView.this.svgaParser.a(inputStream, str2, new d.b() {
                            public void onError() {
                            }

                            public void onComplete(f fVar) {
                                LiveAnimationView.this.ivAnimGift.setVisibility(0);
                                LiveAnimationView.this.ivAnimGift.setVideoItem(fVar);
                                LiveAnimationView.this.ivAnimGift.b();
                            }
                        }, true);
                    }

                    public void onError(Throwable th) {
                        if (LiveAnimationView.this.onGiftNotifyCallback != null) {
                            LiveAnimationView.this.onGiftNotifyCallback.onGiftNotifyListener();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                if (this.onGiftNotifyCallback != null) {
                    this.onGiftNotifyCallback.onGiftNotifyListener();
                }
            }
        }
    }

    public void loadGiftAnimation(GiftItemEntity giftItemEntity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("播放在线动画");
        stringBuilder.append(giftItemEntity.onlineUrl);
        m.a(stringBuilder.toString());
        GiftDownLoadManager.getInstance().updateAnimOnlineSingleRes(giftItemEntity);
        loadPropAnimation(giftItemEntity.onlineUrl);
    }

    public void loadPropAnimation(String str) {
        if (TextUtils.isEmpty(str)) {
            if (this.onGiftNotifyCallback != null) {
                this.onGiftNotifyCallback.onGiftNotifyListener();
            }
        } else if (this.ivAnimGift == null) {
            if (this.onGiftNotifyCallback != null) {
                this.onGiftNotifyCallback.onGiftNotifyListener();
            }
        } else {
            try {
                if (this.svgaParser == null) {
                    this.svgaParser = new d(this.mContext);
                }
                this.svgaParser.a(new URL(i.c(str)), new d.b() {
                    public void onComplete(f fVar) {
                        LiveAnimationView.this.ivAnimGift.setVisibility(0);
                        LiveAnimationView.this.ivAnimGift.setVideoItem(fVar);
                        LiveAnimationView.this.ivAnimGift.b();
                    }

                    public void onError() {
                        if (LiveAnimationView.this.onGiftNotifyCallback != null) {
                            LiveAnimationView.this.onGiftNotifyCallback.onGiftNotifyListener();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                if (this.onGiftNotifyCallback != null) {
                    this.onGiftNotifyCallback.onGiftNotifyListener();
                }
            }
        }
    }

    private void loadCarAnimation(final String str) {
        if (TextUtils.isEmpty(str)) {
            if (this.carAnimCallback != null) {
                this.carAnimCallback.onFinished();
            }
        } else if (this.ivAnimCar == null) {
            if (this.carAnimCallback != null) {
                this.carAnimCallback.onFinished();
            }
        } else {
            try {
                k.just(Boolean.valueOf(true)).map(new -$$Lambda$LiveAnimationView$rcF6d_FycnN2NuM7n4Ong0A0s2U(str)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new r<InputStream>() {
                    public void onComplete() {
                    }

                    public void onSubscribe(b bVar) {
                        LiveAnimationView.this.carDisposable = bVar;
                    }

                    public void onNext(InputStream inputStream) {
                        if (inputStream == null) {
                            if (LiveAnimationView.this.carAnimCallback != null) {
                                LiveAnimationView.this.carAnimCallback.onFinished();
                            }
                            return;
                        }
                        if (LiveAnimationView.this.svgaParser == null) {
                            LiveAnimationView.this.svgaParser = new d(LiveAnimationView.this.mContext);
                        }
                        LiveAnimationView.this.svgaParser.a(inputStream, str, new d.b() {
                            public void onError() {
                                if (LiveAnimationView.this.carAnimCallback != null) {
                                    LiveAnimationView.this.carAnimCallback.onFinished();
                                }
                            }

                            public void onComplete(f fVar) {
                                LiveAnimationView.this.ivAnimCar.setVisibility(0);
                                LiveAnimationView.this.ivAnimCar.setVideoItem(fVar);
                                LiveAnimationView.this.ivAnimCar.b();
                            }
                        }, true);
                    }

                    public void onError(Throwable th) {
                        if (LiveAnimationView.this.carAnimCallback != null) {
                            LiveAnimationView.this.carAnimCallback.onFinished();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                if (this.carAnimCallback != null) {
                    this.carAnimCallback.onFinished();
                }
            }
        }
    }

    public void loadLiveEnterAnimation(java.lang.String r3, final com.opensource.svgaplayer.c r4) {
        /*
        r2 = this;
        r0 = r3.hashCode();
        switch(r0) {
            case 48: goto L_0x0026;
            case 49: goto L_0x001c;
            case 50: goto L_0x0012;
            case 51: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0030;
    L_0x0008:
        r0 = "3";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0030;
    L_0x0010:
        r3 = 0;
        goto L_0x0031;
    L_0x0012:
        r0 = "2";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0030;
    L_0x001a:
        r3 = 2;
        goto L_0x0031;
    L_0x001c:
        r0 = "1";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0030;
    L_0x0024:
        r3 = 1;
        goto L_0x0031;
    L_0x0026:
        r0 = "0";
        r3 = r3.equals(r0);
        if (r3 == 0) goto L_0x0030;
    L_0x002e:
        r3 = 3;
        goto L_0x0031;
    L_0x0030:
        r3 = -1;
    L_0x0031:
        switch(r3) {
            case 0: goto L_0x003b;
            case 1: goto L_0x0038;
            case 2: goto L_0x0038;
            case 3: goto L_0x0035;
            default: goto L_0x0034;
        };
    L_0x0034:
        return;
    L_0x0035:
        r3 = "anim/car_enter.svga";
        goto L_0x003d;
    L_0x0038:
        r3 = "anim/mouth_guard_enter.svga";
        goto L_0x003d;
    L_0x003b:
        r3 = "anim/year_guard_enter.svga";
    L_0x003d:
        r0 = r2.svgaParser;
        if (r0 != 0) goto L_0x004a;
    L_0x0041:
        r0 = new com.opensource.svgaplayer.d;
        r1 = r2.mContext;
        r0.<init>(r1);
        r2.svgaParser = r0;
    L_0x004a:
        r0 = r2.svgaParser;
        r1 = new com.tomatolive.library.ui.view.custom.LiveAnimationView$5;
        r1.<init>(r4);
        r0.a(r3, r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.custom.LiveAnimationView.loadLiveEnterAnimation(java.lang.String, com.opensource.svgaplayer.c):void");
    }

    public void loadGuardOpenAnimation(ResultData resultData, OnAnimPlayListener onAnimPlayListener) {
        loadGuardOpenSvaAnimation(p.a(resultData.guardType));
        GuardOpenDanmuView guardOpenDanmuView = new GuardOpenDanmuView(this.mContext);
        guardOpenDanmuView.addGuardUser(resultData);
        this.llLeftGuardOpenAnimView.addView(guardOpenDanmuView);
        guardOpenDanmuView.setOnAnimPlayListener(onAnimPlayListener);
    }

    public void removeGuardOpenAllViews() {
        this.llLeftGuardOpenAnimView.removeAllViews();
    }

    public void loadCarJoinAnimation(ResultData resultData, boolean z) {
        CarDownloadEntity carItemEntity = CarDownLoadManager.getInstance().getCarItemEntity(resultData.carId);
        if (carItemEntity == null) {
            loadNetAnimation(resultData, z);
            return;
        }
        if (z) {
            loadLiveEnterAnimation("0", i.b(this.mContext, resultData.avatar, resultData.userName, com.tomatolive.library.utils.b.h(resultData.expGrade), carItemEntity.name));
        }
        loadCarAnimation(carItemEntity.id);
    }

    private void loadNetAnimation(final ResultData resultData, final boolean z) {
        CarDownloadEntity carDownloadEntity = new CarDownloadEntity();
        carDownloadEntity.imgUrl = resultData.carIcon;
        carDownloadEntity.id = resultData.carId;
        carDownloadEntity.name = resultData.carName;
        carDownloadEntity.zipUrl = resultData.carResUrl;
        carDownloadEntity.onlineUrl = resultData.carOnlineUrl;
        if (TextUtils.isEmpty(carDownloadEntity.zipUrl) || TextUtils.isEmpty(carDownloadEntity.onlineUrl)) {
            CarDownLoadManager.getInstance().updateAnimOnlineAllRes();
            carFinishCallback(null);
            if (z && this.guardEnterAnimCallback != null) {
                this.guardEnterAnimCallback.onFinished();
            }
            return;
        }
        CarDownLoadManager.getInstance().updateAnimOnlineSingleRes(carDownloadEntity);
        if (this.svgaParser == null) {
            this.svgaParser = new d(this.mContext);
        }
        try {
            this.svgaParser.a(new URL(carDownloadEntity.getOnlineUrl()), new d.b() {
                public void onComplete(f fVar) {
                    LiveAnimationView.this.ivAnimCar.setVisibility(0);
                    LiveAnimationView.this.ivAnimCar.setVideoItem(fVar);
                    LiveAnimationView.this.ivAnimCar.b();
                    if (z) {
                        LiveAnimationView.this.loadLiveEnterAnimation("0", i.b(LiveAnimationView.this.mContext, resultData.avatar, resultData.userName, com.tomatolive.library.utils.b.h(resultData.expGrade), resultData.carName));
                    }
                }

                public void onError() {
                    LiveAnimationView.this.carFinishCallback(null);
                    if (z && LiveAnimationView.this.guardEnterAnimCallback != null) {
                        LiveAnimationView.this.guardEnterAnimCallback.onFinished();
                    }
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
            carFinishCallback(null);
            if (z && this.guardEnterAnimCallback != null) {
                this.guardEnterAnimCallback.onFinished();
            }
        }
    }

    private void carFinishCallback(CarDanmuView.OnAnimPlayListener onAnimPlayListener) {
        if (onAnimPlayListener != null) {
            onAnimPlayListener.onEnd();
        }
        if (this.carAnimCallback != null) {
            this.carAnimCallback.onFinished();
        }
    }

    public void removeCarJoinAllViews() {
        this.llLeftCarAnimView.removeAllViews();
    }

    public void onDestroy() {
        if (this.giftAnimManage != null) {
            this.giftAnimManage.cleanAll();
        }
        if (this.ivAnimGift != null) {
            this.ivAnimGift.a(true);
        }
        if (this.ivAnimEnter != null) {
            this.ivAnimEnter.a(true);
        }
        if (this.ivAnimCar != null) {
            this.ivAnimCar.a(true);
        }
        if (this.ivAnimOpen != null) {
            this.ivAnimOpen.a(true);
        }
        if (!(this.giftDisposable == null || this.giftDisposable.isDisposed())) {
            this.giftDisposable.dispose();
            this.giftDisposable = null;
        }
        if (!(this.carDisposable == null || this.carDisposable.isDisposed())) {
            this.carDisposable.dispose();
            this.carDisposable = null;
        }
        if (this.svgaParser != null) {
            this.svgaParser = null;
        }
    }

    private void loadGuardOpenSvaAnimation(int i) {
        String str = i == p.a("3") ? "anim/NSH.svga" : "anim/YSH.svga";
        if (this.svgaParser == null) {
            this.svgaParser = new d(this.mContext);
        }
        this.svgaParser.a(str, new d.b() {
            public void onError() {
            }

            public void onComplete(f fVar) {
                LiveAnimationView.this.ivAnimOpen.setVisibility(0);
                LiveAnimationView.this.ivAnimOpen.setVideoItem(fVar);
                LiveAnimationView.this.ivAnimOpen.b();
            }
        });
    }

    private InputStream getSVGAFileInputStream(String str, String str2) throws Exception {
        File file = new File(com.tomatolive.library.utils.b.a(str, str2));
        if (g.a(file)) {
            return new FileInputStream(file);
        }
        return null;
    }

    private void showToast(@StringRes int i) {
        o.a(i);
    }
}
