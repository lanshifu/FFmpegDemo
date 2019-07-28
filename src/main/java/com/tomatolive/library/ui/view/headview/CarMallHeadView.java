package com.tomatolive.library.ui.view.headview;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.l;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.u;

public class CarMallHeadView extends LinearLayout {
    private AnimatorSet mCarNoticeAnimatorSet;
    private Context mContext;
    private TextView mTvTopNotice;

    private long formatAnimatorSetDuration(long j) {
        return j;
    }

    public CarMallHeadView(Context context) {
        super(context);
        initView(context);
    }

    public CarMallHeadView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.fq_layout_head_view_car_mall, this);
        this.mContext = context;
        this.mTvTopNotice = (TextView) findViewById(R.id.tv_notice_tips);
        setVisibility(4);
    }

    public void setCarNoticeAnimListener(AnimatorListener animatorListener) {
        if (animatorListener != null) {
            int a = l.a();
            this.mCarNoticeAnimatorSet = new AnimatorSet();
            this.mCarNoticeAnimatorSet.play(ObjectAnimator.ofFloat(this.mTvTopNotice, "translationX", new float[]{(float) a, (float) (-a)}));
            this.mCarNoticeAnimatorSet.addListener(animatorListener);
        }
    }

    public void setCarNoticeNoticeAnim(String str, String str2, String str3, long j) {
        str = u.a(str, 7);
        this.mTvTopNotice.setText(Html.fromHtml(this.mContext.getString(R.string.fq_text_car_top_notice_tips, new Object[]{str, str2, str3})));
        if (this.mCarNoticeAnimatorSet != null) {
            this.mCarNoticeAnimatorSet.setDuration(formatAnimatorSetDuration(j));
            this.mCarNoticeAnimatorSet.start();
        }
    }

    public void onDestroy() {
        if (this.mCarNoticeAnimatorSet != null) {
            this.mCarNoticeAnimatorSet.cancel();
            this.mCarNoticeAnimatorSet.removeAllListeners();
            this.mCarNoticeAnimatorSet = null;
        }
    }
}
