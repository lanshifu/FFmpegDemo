package com.tomatolive.library.ui.view.custom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.gift.GiftAnimationUtil;
import com.tomatolive.library.utils.i;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sh;
import defpackage.wd;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.r;
import java.util.concurrent.TimeUnit;

public class CarDanmuView extends RelativeLayout {
    private ImageView ivLogo;
    private OnAnimPlayListener listener;
    private Context mContext;
    private View rootView;
    private final long showTime = 3;
    private TextView tvName;
    private TextView tvTips;

    public interface OnAnimPlayListener {
        void onEnd();

        void onStart();
    }

    public CarDanmuView(Context context) {
        super(context);
        initView(context);
    }

    public CarDanmuView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.rootView = inflate(context, R.layout.fq_layout_left_car_anim_view, this);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvTips = (TextView) findViewById(R.id.tv_tips);
        this.ivLogo = (ImageView) findViewById(R.id.iv_logo);
        setRootViewVisibility(4);
    }

    public void addCarItemInfo(String str, String str2, String str3) {
        this.tvName.setText(str);
        this.tvTips.setText(Html.fromHtml(this.mContext.getString(R.string.fq_car_anim_danmu_tips, new Object[]{str2})));
        i.c(this.mContext, this.ivLogo, str3);
        k.timer(500, TimeUnit.MILLISECONDS, wd.a()).subscribe(new r<Long>() {
            public void onComplete() {
            }

            public void onError(Throwable th) {
            }

            public void onSubscribe(b bVar) {
            }

            public void onNext(Long l) {
                CarDanmuView.this.startAnim(CarDanmuView.this.rootView);
            }
        });
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
                CarDanmuView.this.startAnim();
            }

            public void onAnimationStart(Animator animator, boolean z) {
                CarDanmuView.this.startAnim();
            }

            public void onAnimationEnd(Animator animator) {
                k.timer(3, TimeUnit.SECONDS, wd.a()).subscribe(new sh<Long>() {
                    public void accept(Long l) {
                        CarDanmuView.this.endAnim(CarDanmuView.this.rootView);
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
                CarDanmuView.this.endAnim();
            }

            public void onAnimationEnd(Animator animator, boolean z) {
                CarDanmuView.this.endAnim();
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
        if (this.listener != null) {
            this.listener.onEnd();
        }
    }

    private void setRootViewVisibility(int i) {
        setVisibility(i);
    }
}
