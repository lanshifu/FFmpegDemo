package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.d;
import com.opensource.svgaplayer.f;
import com.tomatolive.library.R;
import com.tomatolive.library.model.db.GiftBoxEntity;
import com.tomatolive.library.ui.interfaces.impl.SimpleSVGACallBack;
import com.tomatolive.library.ui.view.widget.progress.AnimDownloadProgressButton;
import com.tomatolive.library.utils.a;
import com.tomatolive.library.utils.c;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.utils.z;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sh;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.k;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import q.rorbin.badgeview.QBadgeView;

public class GiftBoxView extends RelativeLayout {
    private RelativeLayout boxRoot;
    private b chronographDisposable;
    private b countDownDisposable;
    private b expiredDisposable;
    private List<GiftBoxEntity> giftBoxEntityList;
    private ImageView ivIcon;
    private SVGAImageView ivSvga;
    private OnSendGiftBoxMsgListener listener;
    private State mState;
    private AnimDownloadProgressButton progressLoading;
    private QBadgeView qBadgeView;
    private d svgaParser;
    private TextView tvShowTip;

    public interface OnSendGiftBoxMsgListener {
        void onSendGiftBoxMsg(GiftBoxEntity giftBoxEntity);

        void onShowDialog(GiftBoxEntity giftBoxEntity);
    }

    public enum State {
        INIT,
        WAITING,
        OPENING,
        LOADING,
        EXPIRED
    }

    public GiftBoxView(Context context) {
        this(context, null);
    }

    public GiftBoxView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GiftBoxView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mState = State.INIT;
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.fq_giftbox_layout, this);
        this.ivIcon = (ImageView) findViewById(R.id.iv_box);
        this.ivSvga = (SVGAImageView) findViewById(R.id.iv_svga);
        this.boxRoot = (RelativeLayout) findViewById(R.id.rl_box_root);
        this.boxRoot.setVisibility(4);
        this.ivSvga.setVisibility(4);
        this.qBadgeView = new QBadgeView(context);
        this.qBadgeView.a(this.ivIcon).b(-1).a(3.0f, true).c(8388661).a(-65536).a(-1, 1.0f, true);
        this.tvShowTip = (TextView) findViewById(R.id.tv_show_tip);
        this.progressLoading = (AnimDownloadProgressButton) findViewById(R.id.fq_loading_btn);
        this.progressLoading.setVisibility(4);
        this.giftBoxEntityList = new ArrayList();
        this.svgaParser = new d(getContext());
        initListener();
    }

    private void initListener() {
        r.a().a(this.boxRoot, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$GiftBoxView$AHaBvCj4gvx-GdcqXKDuK60BGSg(this));
        this.ivSvga.setCallback(new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                GiftBoxView.this.ivSvga.setVisibility(4);
                GiftBoxView.this.boxRoot.setVisibility(0);
                GiftBoxView.this.progressLoading.setVisibility(8);
                GiftBoxView.this.progressLoading.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
                GiftBoxView.this.showNextBox();
            }
        });
    }

    public static /* synthetic */ void lambda$initListener$0(GiftBoxView giftBoxView, Object obj) {
        switch (giftBoxView.mState) {
            case INIT:
            case EXPIRED:
                break;
            case OPENING:
                giftBoxView.cancelExpiredDisposable();
                GiftBoxEntity giftBoxEntity = (GiftBoxEntity) giftBoxView.giftBoxEntityList.remove(0);
                c.a(giftBoxEntity);
                if (giftBoxView.listener != null) {
                    giftBoxView.listener.onSendGiftBoxMsg(giftBoxEntity);
                }
                if (giftBoxView.giftBoxEntityList.size() != 0) {
                    giftBoxView.setBadgeCount();
                    giftBoxView.showLoading();
                    break;
                }
                giftBoxView.showEmptyBox();
                break;
            case LOADING:
            case WAITING:
                if (giftBoxView.listener != null) {
                    giftBoxView.listener.onShowDialog((GiftBoxEntity) giftBoxView.giftBoxEntityList.get(0));
                    break;
                }
                break;
            default:
                return;
        }
    }

    private void showEmptyBox() {
        this.mState = State.INIT;
        cancelChronographDisposable();
        this.ivSvga.setVisibility(4);
        this.boxRoot.setVisibility(4);
    }

    public void showLoading() {
        this.mState = State.LOADING;
        this.ivIcon.setImageResource(R.drawable.fq_imgs_box_close);
        this.tvShowTip.setVisibility(4);
        this.progressLoading.setVisibility(0);
        this.progressLoading.setLoadingEndListener(new -$$Lambda$GiftBoxView$xovRGYXDzcFJVJhUgp54pk-67i8(this));
        this.progressLoading.setProgressText("loading...", 100.0f);
    }

    public static /* synthetic */ void lambda$showLoading$1(GiftBoxView giftBoxView) {
        giftBoxView.progressLoading.setVisibility(8);
        giftBoxView.progressLoading.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
        giftBoxView.showNextBox();
    }

    public void setOnSendGiftBoxMsgListener(OnSendGiftBoxMsgListener onSendGiftBoxMsgListener) {
        this.listener = onSendGiftBoxMsgListener;
    }

    public void startChronographTimer() {
        this.chronographDisposable = k.interval(1, TimeUnit.SECONDS).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new -$$Lambda$GiftBoxView$F4oD6vu0UJsJ5aqwNQYSvMsEePk(this));
    }

    public static /* synthetic */ void lambda$startChronographTimer$2(GiftBoxView giftBoxView, Long l) throws Exception {
        for (int size = giftBoxView.giftBoxEntityList.size() - 1; size >= 0; size--) {
            GiftBoxEntity giftBoxEntity = (GiftBoxEntity) giftBoxView.giftBoxEntityList.get(size);
            giftBoxEntity.incrementTime++;
        }
    }

    public void cancelChronographDisposable() {
        if (this.chronographDisposable != null && !this.chronographDisposable.isDisposed()) {
            this.chronographDisposable.dispose();
            this.chronographDisposable = null;
        }
    }

    public void cancelCountDownDisposable() {
        if (this.countDownDisposable != null && !this.countDownDisposable.isDisposed()) {
            this.countDownDisposable.dispose();
            this.countDownDisposable = null;
        }
    }

    public void cancelExpiredDisposable() {
        if (this.expiredDisposable != null && !this.expiredDisposable.isDisposed()) {
            this.expiredDisposable.dispose();
            this.expiredDisposable = null;
        }
    }

    public void cancelLoading() {
        if (this.progressLoading != null) {
            this.progressLoading.cancelAnimaiton();
        }
    }

    public void showBoxList(List<GiftBoxEntity> list, String str) {
        if (list == null || list.size() == 0) {
            c.d(str);
            this.boxRoot.setVisibility(4);
            this.ivSvga.setVisibility(4);
            return;
        }
        this.giftBoxEntityList.clear();
        List c = c.c(str);
        ArrayList arrayList = new ArrayList();
        for (GiftBoxEntity giftBoxEntity : list) {
            if (!c.contains(giftBoxEntity.giftBoxUniqueCode)) {
                giftBoxEntity.liveId = str;
                giftBoxEntity.userId = z.a().c();
                arrayList.add(giftBoxEntity);
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            GiftBoxEntity giftBoxEntity2 = (GiftBoxEntity) arrayList.get(size);
            if (giftBoxEntity2.openTime == 0 && giftBoxEntity2.expirationTime == 0) {
                arrayList.remove(size);
            }
        }
        if (arrayList.size() == 0) {
            this.boxRoot.setVisibility(4);
            this.ivSvga.setVisibility(4);
            return;
        }
        this.giftBoxEntityList.addAll(arrayList);
        startChronographTimer();
        setBadgeCount();
        this.boxRoot.setVisibility(0);
        showLoading();
    }

    private void showAnim() {
        this.mState = State.LOADING;
        if (this.svgaParser != null) {
            this.svgaParser.a("anim/box.svga", new d.b() {
                public void onComplete(f fVar) {
                    GiftBoxView.this.ivSvga.setVisibility(0);
                    GiftBoxView.this.ivSvga.setImageDrawable(new com.opensource.svgaplayer.b(fVar));
                    GiftBoxView.this.ivSvga.b();
                }

                public void onError() {
                    GiftBoxView.this.ivSvga.setVisibility(4);
                    GiftBoxView.this.boxRoot.setVisibility(0);
                    GiftBoxView.this.progressLoading.setVisibility(8);
                    GiftBoxView.this.progressLoading.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
                    GiftBoxView.this.showNextBox();
                }
            });
        }
    }

    public void addOneBox(GiftBoxEntity giftBoxEntity) {
        this.giftBoxEntityList.add(giftBoxEntity);
        setBadgeCount();
        if (this.mState == State.INIT) {
            startChronographTimer();
            showAnim();
        }
    }

    public void showNextBox() {
        if (this.giftBoxEntityList.size() > 0) {
            GiftBoxEntity giftBoxEntity = (GiftBoxEntity) this.giftBoxEntityList.get(0);
            long j = giftBoxEntity.incrementTime;
            long j2 = giftBoxEntity.openTime;
            long j3 = giftBoxEntity.expirationTime;
            if (j < j2) {
                this.mState = State.WAITING;
                this.ivIcon.setImageResource(R.drawable.fq_imgs_box_close);
                startWaitCountDown(j, j2, j3);
                return;
            }
            if (j >= j2) {
                long j4 = j - j2;
                if (j4 < j3) {
                    showOpenBoxAnim();
                    startExpiredCountDown(j3 - j4);
                    return;
                }
            }
            if (j >= j2 + j3) {
                this.mState = State.EXPIRED;
                this.ivIcon.setImageResource(R.drawable.fq_imgs_box_open);
                this.tvShowTip.setVisibility(0);
                this.tvShowTip.setText(R.string.fq_receive_box);
                giftBoxEntity = (GiftBoxEntity) this.giftBoxEntityList.remove(0);
                setBadgeCount();
                this.progressLoading.setVisibility(8);
                this.progressLoading.setProgress(CropImageView.DEFAULT_ASPECT_RATIO);
                showNextBox();
                return;
            }
            return;
        }
        showEmptyBox();
    }

    private void startWaitCountDown(long j, long j2, final long j3) {
        this.tvShowTip.setVisibility(0);
        long j4 = j2 - j;
        this.tvShowTip.setText(com.tomatolive.library.utils.d.b(j4));
        k.interval(1, TimeUnit.SECONDS).take(j4 + 1).map(new -$$Lambda$GiftBoxView$le1qctUa8Id64cEPhM2gm7IqJFE(j2, j)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new sh<Long>() {
            public void accept(Long l) {
                GiftBoxView.this.tvShowTip.setText(com.tomatolive.library.utils.d.b(l.longValue()));
            }

            public void onComplete() {
                GiftBoxView.this.showOpenBoxAnim();
                GiftBoxView.this.startExpiredCountDown(j3);
            }

            public void onSubscribe(b bVar) {
                super.onSubscribe(bVar);
                GiftBoxView.this.countDownDisposable = bVar;
            }
        });
    }

    private void startExpiredCountDown(long j) {
        k.interval(1, TimeUnit.SECONDS).take(1 + j).map(new -$$Lambda$GiftBoxView$FGIfQ5NOYILt7vp48AxoVzybJMk(j)).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new sh<Long>() {
            public void accept(Long l) {
            }

            public void onComplete() {
                GiftBoxEntity giftBoxEntity = (GiftBoxEntity) GiftBoxView.this.giftBoxEntityList.remove(0);
                GiftBoxView.this.setBadgeCount();
                if (GiftBoxView.this.giftBoxEntityList.size() == 0) {
                    GiftBoxView.this.showEmptyBox();
                } else {
                    GiftBoxView.this.showLoading();
                }
            }

            public void onSubscribe(b bVar) {
                super.onSubscribe(bVar);
                GiftBoxView.this.expiredDisposable = bVar;
            }
        });
    }

    private void showOpenBoxAnim() {
        this.mState = State.OPENING;
        this.ivIcon.setImageResource(R.drawable.fq_imgs_box_open);
        a.c(this.boxRoot);
        this.tvShowTip.setVisibility(0);
        this.tvShowTip.setText(R.string.fq_receive_box);
    }

    public void setBadgeCount() {
        int size = this.giftBoxEntityList.size();
        if (size == 1) {
            size = 0;
        }
        this.qBadgeView.d(size);
    }

    public void clear() {
        this.ivSvga.setVisibility(4);
        this.boxRoot.setVisibility(4);
        this.giftBoxEntityList.clear();
        this.mState = State.INIT;
        cancelExpiredDisposable();
        cancelCountDownDisposable();
        cancelChronographDisposable();
        cancelLoading();
        if (this.svgaParser != null) {
            this.svgaParser = null;
        }
    }

    public void release() {
        clear();
    }
}
