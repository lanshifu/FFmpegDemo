package com.tomatolive.library.ui.view.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.a;
import com.opensource.svgaplayer.d;
import com.opensource.svgaplayer.f;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

public class GiftFrameLayout extends RelativeLayout implements Callback {
    private static final int INTERVAL = 200;
    private static final int RESTART_GIFT_ANIMATION_CODE = 1002;
    private static final String TAG = "GiftFrameLayout";
    private ICustomAnim anim;
    private BarrageEndAnimationListener barrageEndAnimationListener;
    private Handler comboHandler;
    private boolean isDelete;
    private boolean isEnd;
    private boolean isHideMode;
    private boolean isShowing;
    private ImageView ivAvatar;
    private SVGAImageView ivSvgaImageView;
    private volatile int mCombo;
    private Context mContext;
    private Runnable mCurrentAnimRunnable;
    private GiftAnimModel mGift;
    private LeftGiftAnimationStatusListener mGiftAnimationListener;
    private volatile int mGiftCount;
    private Handler mHandler;
    private int mIndex;
    private LayoutInflater mInflater;
    private volatile int mJumpCombo;
    private RelativeLayout rlBaseInfo;
    private View rootView;
    private Runnable runnable;
    private final String strX;
    private d svgaParser;
    private StrokeTextView tvAnimNum;
    private TextView tvGiftName;
    private TextView tvNickName;

    public interface BarrageEndAnimationListener {
        void onEndAnimation(GiftAnimModel giftAnimModel);

        void onStartAnimation(GiftAnimModel giftAnimModel);
    }

    private class GiftNumAnimRunnable implements Runnable {
        private GiftNumAnimRunnable() {
        }

        /* synthetic */ GiftNumAnimRunnable(GiftFrameLayout giftFrameLayout, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void run() {
            GiftFrameLayout.this.dismissGiftLayout();
        }
    }

    public interface LeftGiftAnimationStatusListener {
        void dismiss(GiftFrameLayout giftFrameLayout);
    }

    private class SVGAAnimationListener implements a {
        public void onPause() {
        }

        public void onRepeat() {
        }

        public void onStep(int i, double d) {
        }

        private SVGAAnimationListener() {
        }

        /* synthetic */ SVGAAnimationListener(GiftFrameLayout giftFrameLayout, AnonymousClass1 anonymousClass1) {
            this();
        }

        public void onFinished() {
            if (GiftFrameLayout.this.mGift != null) {
                GiftFrameLayout.this.loadGiftImage();
            }
        }
    }

    public GiftFrameLayout(Context context) {
        this(context, null);
    }

    public GiftFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler(this);
        this.comboHandler = new Handler(this);
        this.mIndex = 1;
        this.mCombo = 0;
        this.mJumpCombo = 1;
        this.isShowing = false;
        this.isEnd = true;
        this.isHideMode = false;
        this.strX = "x";
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        initView();
    }

    @SuppressLint({"InflateParams"})
    private void initView() {
        this.rootView = this.mInflater.inflate(R.layout.fq_layout_gift_anim_view, null);
        this.rlBaseInfo = (RelativeLayout) this.rootView.findViewById(R.id.fq_rl_name_bg);
        this.tvAnimNum = (StrokeTextView) this.rootView.findViewById(R.id.fq_tv_number);
        this.tvNickName = (TextView) this.rootView.findViewById(R.id.fq_stv_name);
        this.tvGiftName = (TextView) this.rootView.findViewById(R.id.fq_tv_gift_name);
        this.ivAvatar = (ImageView) this.rootView.findViewById(R.id.iv_avatar);
        this.ivSvgaImageView = (SVGAImageView) this.rootView.findViewById(R.id.fq_iv_gift_svga_view);
        this.svgaParser = new d(this.mContext);
        this.ivSvgaImageView.setCallback(new SVGAAnimationListener(this, null));
        this.tvNickName.setSelected(true);
        addView(this.rootView);
    }

    public void firstHideLayout() {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{ofFloat});
        ofPropertyValuesHolder.setStartDelay(0);
        ofPropertyValuesHolder.setDuration(0);
        ofPropertyValuesHolder.start();
    }

    public void setHideMode(boolean z) {
        this.isHideMode = z;
    }

    private void hideView() {
        this.tvAnimNum.setVisibility(4);
    }

    public void setGiftViewEndVisibility(boolean z) {
        if (this.isHideMode && z) {
            setVisibility(8);
        } else {
            setVisibility(4);
        }
    }

    public boolean setGift(GiftAnimModel giftAnimModel) {
        if (giftAnimModel == null) {
            return false;
        }
        this.mGift = giftAnimModel;
        if (this.mGift.getJumpCombo() == 0) {
            this.mGiftCount = this.mGift.getGiftCount();
            this.mCombo = 1;
        } else {
            this.mGiftCount = this.mGift.getJumpCombo();
            this.mCombo = this.mGift.getJumpCombo();
        }
        this.mJumpCombo = this.mGift.getJumpCombo();
        if (!TextUtils.isEmpty(giftAnimModel.getSendUserName())) {
            this.tvNickName.setText(giftAnimModel.getSendUserName());
        }
        if (!TextUtils.isEmpty(giftAnimModel.getGiftId())) {
            TextView textView = this.tvGiftName;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.fq_send));
            stringBuilder.append(this.mContext.getString(R.string.fq_black_space));
            stringBuilder.append(giftAnimModel.getGiftName());
            textView.setText(stringBuilder.toString());
        }
        this.barrageEndAnimationListener = giftAnimModel.getAnimationListener();
        i.a(this.mContext, this.ivAvatar, giftAnimModel.getSendUserPic(), R.drawable.fq_ic_placeholder_avatar);
        return true;
    }

    public GiftAnimModel getGift() {
        return this.mGift;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 1002) {
            this.mCombo++;
            this.tvAnimNum.setText(getGiftNumStr(this.mCombo));
            comboAnimation(false);
            removeDismissGiftCallback();
        }
        return true;
    }

    private void dismissGiftLayout() {
        removeDismissGiftCallback();
        if (this.mGiftAnimationListener != null) {
            this.mGiftAnimationListener.dismiss(this);
        }
    }

    private void removeDismissGiftCallback() {
        stopCheckGiftCount();
        if (this.mCurrentAnimRunnable != null) {
            this.mHandler.removeCallbacks(this.mCurrentAnimRunnable);
            this.mCurrentAnimRunnable = null;
        }
    }

    public void setJumpCombo(int i) {
        this.mJumpCombo = i;
    }

    public void setDelete(boolean z) {
        this.isDelete = z;
    }

    public void setIndex(int i) {
        this.mIndex = i;
    }

    public int getIndex() {
        return this.mIndex;
    }

    public void setGiftAnimationListener(LeftGiftAnimationStatusListener leftGiftAnimationStatusListener) {
        this.mGiftAnimationListener = leftGiftAnimationStatusListener;
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void setCurrentShowStatus(boolean z) {
        this.mCombo = 0;
        this.isShowing = z;
    }

    public boolean isEnd() {
        return this.isEnd;
    }

    public void CurrentEndStatus(boolean z) {
        this.isEnd = z;
    }

    public String getCurrentSendUserId() {
        return this.mGift != null ? this.mGift.getSendUserId() : null;
    }

    public String getCurrentGiftId() {
        return this.mGift != null ? this.mGift.getGiftId() : null;
    }

    public synchronized void setGiftCount(int i) {
        this.mGiftCount += i;
        this.mGift.setGiftCount(this.mGiftCount);
    }

    public synchronized void setGiftAddCount(int i) {
        this.mGiftCount = i;
        this.mGift.setGiftCount(this.mGiftCount);
    }

    public int getGiftCount() {
        int i;
        synchronized (this) {
            i = this.mGiftCount;
        }
        return i;
    }

    public synchronized void setSendGiftTime(long j) {
        this.mGift.setSendGiftTime(Long.valueOf(j));
    }

    public long getSendGiftTime() {
        long longValue;
        synchronized (this) {
            longValue = this.mGift.getSendGiftTime().longValue();
        }
        return longValue;
    }

    public boolean isCurrentStart() {
        return this.mGift.isCurrentStart();
    }

    public void setCurrentStart(boolean z) {
        this.mGift.setCurrentStart(z);
    }

    public int getCombo() {
        return this.mCombo;
    }

    public int getJumpCombo() {
        return this.mJumpCombo;
    }

    private void checkGiftCountSubscribe() {
        this.runnable = new Runnable() {
            public void run() {
                if (GiftFrameLayout.this.mGiftCount > GiftFrameLayout.this.mCombo) {
                    GiftFrameLayout.this.mHandler.sendEmptyMessage(1002);
                }
                GiftFrameLayout.this.comboHandler.postDelayed(GiftFrameLayout.this.runnable, 200);
            }
        };
        this.comboHandler.postDelayed(this.runnable, 200);
    }

    private void stopCheckGiftCount() {
        this.comboHandler.removeCallbacksAndMessages(null);
    }

    public void clearHandler() {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        if (this.comboHandler != null) {
            this.comboHandler.removeCallbacksAndMessages(null);
            this.comboHandler = null;
        }
        if (this.mGiftAnimationListener != null) {
            this.mGiftAnimationListener = null;
        }
        if (this.barrageEndAnimationListener != null) {
            this.barrageEndAnimationListener = null;
        }
        if (this.ivSvgaImageView != null) {
            this.ivSvgaImageView.setCallback(null);
        }
        if (this.svgaParser != null) {
            this.svgaParser = null;
        }
        resetGift();
    }

    private void resetGift() {
        this.runnable = null;
        this.mCurrentAnimRunnable = null;
        this.mGift = null;
        this.mIndex = -1;
        this.mGiftCount = 0;
        this.mCombo = 0;
        this.mJumpCombo = 0;
        this.isShowing = false;
        this.isEnd = true;
        this.isHideMode = false;
    }

    public void initLayoutState() {
        setVisibility(0);
        this.isShowing = true;
        this.isEnd = false;
        this.tvAnimNum.setVisibility(4);
        this.tvAnimNum.setText(getGiftNumStr(this.mCombo));
        if (this.mGift.isProp) {
            if (this.mGift.isPropPlay()) {
                startOnLineSVGAAnim(this.mGift.onLineUrl);
            } else {
                loadGiftImage();
            }
        } else if (!this.mGift.isStartLottieAnim()) {
            loadGiftImage();
        } else if (g.b(b.a(this.mGift.getGiftDirPath(), this.mGift.getJsonName()))) {
            startSVGAAnim(this.mGift.getGiftDirPath(), this.mGift.getJsonName());
        } else {
            startOnLineSVGAAnim(this.mGift.onLineUrl);
        }
    }

    public void comboEndAnim() {
        if (this.mHandler == null) {
            return;
        }
        if (this.mGiftCount > this.mCombo) {
            this.mHandler.sendEmptyMessage(1002);
            return;
        }
        this.mCurrentAnimRunnable = new GiftNumAnimRunnable(this, null);
        this.mHandler.postDelayed(this.mCurrentAnimRunnable, this.mGift == null ? 3000 : (long) this.mGift.getGiftShowTime());
        checkGiftCountSubscribe();
    }

    public void startAnimation(ICustomAnim iCustomAnim) {
        if (this.barrageEndAnimationListener != null) {
            GiftAnimModel gift = getGift();
            gift.setGiftTotalCount(getGiftTotalNum());
            this.barrageEndAnimationListener.onStartAnimation(gift);
        }
        this.anim = iCustomAnim;
        if (iCustomAnim == null) {
            hideView();
            ObjectAnimator createFlyFromLtoR = GiftAnimationUtil.createFlyFromLtoR(this.rlBaseInfo, (float) (-getWidth()), CropImageView.DEFAULT_ASPECT_RATIO, 400, new OvershootInterpolator());
            createFlyFromLtoR.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    GiftFrameLayout.this.initLayoutState();
                }
            });
            ObjectAnimator createFlyFromLtoR2 = GiftAnimationUtil.createFlyFromLtoR(this.ivSvgaImageView, (float) (-getWidth()), CropImageView.DEFAULT_ASPECT_RATIO, 400, new DecelerateInterpolator());
            createFlyFromLtoR2.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                    GiftFrameLayout.this.ivSvgaImageView.setVisibility(0);
                }

                public void onAnimationEnd(Animator animator) {
                    GiftFrameLayout.this.comboAnimation(true);
                }
            });
            GiftAnimationUtil.startAnimation(createFlyFromLtoR, createFlyFromLtoR2);
            return;
        }
        iCustomAnim.startAnim(this, this.rootView);
    }

    public void comboAnimation(boolean z) {
        if (this.anim != null) {
            this.anim.comboAnim(this, this.rootView, z);
        } else if (z) {
            this.tvAnimNum.setVisibility(0);
            this.tvAnimNum.setText(getGiftNumStr(this.mCombo));
            comboEndAnim();
        } else {
            ObjectAnimator scaleGiftNum = GiftAnimationUtil.scaleGiftNum(this.tvAnimNum);
            scaleGiftNum.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    GiftFrameLayout.this.comboEndAnim();
                }
            });
            scaleGiftNum.start();
        }
    }

    public AnimatorSet endAnimation(ICustomAnim iCustomAnim) {
        if (!(this.barrageEndAnimationListener == null || this.isDelete)) {
            GiftAnimModel gift = getGift();
            gift.setGiftTotalCount(getGiftTotalNum());
            this.barrageEndAnimationListener.onEndAnimation(gift);
        }
        if (iCustomAnim != null) {
            return iCustomAnim.endAnim(this, this.rootView);
        }
        ObjectAnimator createFadeAnimator = GiftAnimationUtil.createFadeAnimator(this, CropImageView.DEFAULT_ASPECT_RATIO, -80.0f, IjkMediaCodecInfo.RANK_SECURE, 0);
        createFadeAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                GiftFrameLayout.this.tvAnimNum.setVisibility(4);
            }
        });
        return GiftAnimationUtil.startAnimation(createFadeAnimator, GiftAnimationUtil.createFadeAnimator(this, 100.0f, CropImageView.DEFAULT_ASPECT_RATIO, 0, 0));
    }

    private void startOnLineSVGAAnim(String str) {
        if (!TextUtils.isEmpty(str) && this.ivSvgaImageView != null) {
            try {
                if (this.svgaParser == null) {
                    this.svgaParser = new d(this.mContext);
                }
                this.svgaParser.a(new URL(i.c(str)), new d.b() {
                    public void onComplete(f fVar) {
                        GiftFrameLayout.this.ivSvgaImageView.setVisibility(0);
                        GiftFrameLayout.this.ivSvgaImageView.setVideoItem(fVar);
                        GiftFrameLayout.this.ivSvgaImageView.b();
                    }

                    public void onError() {
                        GiftFrameLayout.this.loadGiftImage();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                loadGiftImage();
            }
        }
    }

    /* JADX WARNING: Missing block: B:13:0x0045, code skipped:
            return;
     */
    private void startSVGAAnim(final java.lang.String r3, final java.lang.String r4) {
        /*
        r2 = this;
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x0045;
    L_0x0006:
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 == 0) goto L_0x000d;
    L_0x000c:
        goto L_0x0045;
    L_0x000d:
        r0 = r2.ivSvgaImageView;
        if (r0 != 0) goto L_0x0012;
    L_0x0011:
        return;
    L_0x0012:
        r0 = 1;
        r0 = java.lang.Boolean.valueOf(r0);	 Catch:{ Exception -> 0x003d }
        r0 = io.reactivex.k.just(r0);	 Catch:{ Exception -> 0x003d }
        r1 = new com.tomatolive.library.ui.view.gift.GiftFrameLayout$8;	 Catch:{ Exception -> 0x003d }
        r1.<init>(r3, r4);	 Catch:{ Exception -> 0x003d }
        r3 = r0.map(r1);	 Catch:{ Exception -> 0x003d }
        r0 = defpackage.xl.b();	 Catch:{ Exception -> 0x003d }
        r3 = r3.subscribeOn(r0);	 Catch:{ Exception -> 0x003d }
        r0 = defpackage.wd.a();	 Catch:{ Exception -> 0x003d }
        r3 = r3.observeOn(r0);	 Catch:{ Exception -> 0x003d }
        r0 = new com.tomatolive.library.ui.view.gift.GiftFrameLayout$7;	 Catch:{ Exception -> 0x003d }
        r0.<init>(r4);	 Catch:{ Exception -> 0x003d }
        r3.subscribe(r0);	 Catch:{ Exception -> 0x003d }
        goto L_0x0044;
    L_0x003d:
        r3 = move-exception;
        r3.printStackTrace();
        r2.loadGiftImage();
    L_0x0044:
        return;
    L_0x0045:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.gift.GiftFrameLayout.startSVGAAnim(java.lang.String, java.lang.String):void");
    }

    private InputStream getSVGAFileInputStream(String str, String str2) throws Exception {
        File file = new File(b.a(str, str2));
        if (file.exists()) {
            return new FileInputStream(file);
        }
        return null;
    }

    public String getGiftNumStr(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("x");
        stringBuffer.append(i);
        return stringBuffer.toString();
    }

    private int getGiftTotalNum() {
        if (this.tvAnimNum == null) {
            return 0;
        }
        return p.a(this.tvAnimNum.getText().toString().replace("x", ""));
    }

    private void loadGiftImage() {
        i.b(getContext(), this.ivSvgaImageView, this.mGift.getGiftPic(), com.blankj.utilcode.util.b.a(35.0f), com.blankj.utilcode.util.b.a(35.0f));
    }
}
