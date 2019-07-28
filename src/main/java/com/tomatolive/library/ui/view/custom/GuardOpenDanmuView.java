package com.tomatolive.library.ui.view.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.ui.view.gift.GiftAnimationUtil;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sh;
import defpackage.wd;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.TimeUnit;

public class GuardOpenDanmuView extends RelativeLayout {
    private ImageView ivAvatar;
    private OnAnimPlayListener listener;
    private Context mContext;
    private View rlBg;
    private View rootView;
    private long showTime;
    private TextView tvName;
    private TextView tvTip;

    public interface OnAnimPlayListener {
        void onEnd();

        void onStart();
    }

    public GuardOpenDanmuView(Context context) {
        this(context, null);
    }

    public GuardOpenDanmuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GuardOpenDanmuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.showTime = 6;
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.rootView = inflate(context, R.layout.fq_layout_open_guard_anim_view, this);
        this.rlBg = findViewById(R.id.fq_rl_name_bg);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.tvName = (TextView) findViewById(R.id.fq_name);
        this.tvTip = (TextView) findViewById(R.id.fq_tv_open_guard_tip);
        setRootViewVisibility(4);
        this.tvName.setSelected(true);
    }

    public void addGuardUser(ResultData resultData) {
        if (resultData != null) {
            this.tvName.setText(resultData.userName);
            int i = R.color.fq_guard_mouth_text_bg;
            int i2 = R.drawable.fq_mouth_guard_open_tip;
            String str = resultData.guardType;
            Object obj = -1;
            switch (str.hashCode()) {
                case 49:
                    if (str.equals("1")) {
                        obj = null;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals(ConnectSocketParams.EFFECT_TYPE_BIG)) {
                        obj = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str.equals("3")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    str = this.mContext.getString(R.string.fq_guard_week);
                    break;
                case 1:
                    str = this.mContext.getString(R.string.fq_guard_month);
                    break;
                case 2:
                    String string = this.mContext.getString(R.string.fq_guard_year);
                    i2 = R.color.fq_guard_year_text_bg;
                    int i3 = R.drawable.fq_year_guard_open_tip;
                    this.showTime = 10;
                    int i4 = i3;
                    str = string;
                    i = i2;
                    i2 = i4;
                    break;
                default:
                    return;
            }
            SpannableString spannableString = new SpannableString(this.mContext.getString(R.string.fq_open_guard_tip, new Object[]{str}));
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mContext, i)), 3, spannableString.length(), 33);
            this.tvTip.setText(spannableString);
            this.rlBg.setBackgroundResource(i2);
            i.a(this.mContext, this.ivAvatar, resultData.avatar, R.drawable.fq_ic_placeholder_avatar);
            k.timer(500, TimeUnit.MILLISECONDS, wd.a()).subscribe(new r<Long>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(b bVar) {
                }

                public void onNext(Long l) {
                    GuardOpenDanmuView.this.startAnim(GuardOpenDanmuView.this.rootView);
                }
            });
        }
    }

    public void setOnAnimPlayListener(OnAnimPlayListener onAnimPlayListener) {
        this.listener = onAnimPlayListener;
    }

    private void startAnim(View view) {
        ObjectAnimator createFlyFromLtoR = GiftAnimationUtil.createFlyFromLtoR(view, (float) (-view.getWidth()), CropImageView.DEFAULT_ASPECT_RATIO, 800, new DecelerateInterpolator());
        createFlyFromLtoR.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                GuardOpenDanmuView.this.startAnim();
            }

            public void onAnimationStart(Animator animator, boolean z) {
                GuardOpenDanmuView.this.startAnim();
            }

            public void onAnimationEnd(Animator animator) {
                k.timer(GuardOpenDanmuView.this.showTime, TimeUnit.SECONDS, wd.a()).subscribe(new sh<Long>() {
                    public void accept(Long l) {
                        GuardOpenDanmuView.this.endAnim(GuardOpenDanmuView.this.rootView);
                    }
                });
            }
        });
        createFlyFromLtoR.start();
    }

    private void endAnim(View view) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("translationX", new float[]{CropImageView.DEFAULT_ASPECT_RATIO, -200.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.5f});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{ofFloat, ofFloat2});
        ofPropertyValuesHolder.setStartDelay(0);
        ofPropertyValuesHolder.setDuration(800);
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("translationX", new float[]{-200.0f, -250.0f});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("alpha", new float[]{0.5f, CropImageView.DEFAULT_ASPECT_RATIO});
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{ofFloat3, ofFloat4});
        ofPropertyValuesHolder2.setStartDelay(0);
        ofPropertyValuesHolder2.setDuration(800);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofPropertyValuesHolder2).after(ofPropertyValuesHolder);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                GuardOpenDanmuView.this.endAnim();
            }

            public void onAnimationEnd(Animator animator, boolean z) {
                GuardOpenDanmuView.this.endAnim();
            }
        });
        animatorSet.start();
    }

    private void startAnim() {
        if (this.listener != null) {
            setRootViewVisibility(0);
            this.listener.onStart();
        }
    }

    private void endAnim() {
        if (this.listener instanceof OnAnimPlayListener) {
            this.listener.onEnd();
        }
    }

    private void setRootViewVisibility(int i) {
        setVisibility(i);
    }
}
