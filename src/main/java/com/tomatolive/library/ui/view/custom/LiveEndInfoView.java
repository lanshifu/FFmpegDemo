package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LiveEndEntity;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;

public class LiveEndInfoView extends LinearLayout implements OnClickListener {
    private Context mContext;
    private ImageView mHeadImg;
    private LiveEndClickListener mLiveEndClickListener;
    private TextView mTvAttention;
    private TextView mTvId;
    private TextView mTvLastEndTime;
    private TextView mTvLiveTime;
    private TextView mTvWatchNum;
    private TextView tvLivePre;
    private UserNickNameGradeView userNickNameGradeView;

    public interface LiveEndClickListener {
        void onAttentionClick(View view);

        void onGoHomeClick();

        void onNavBackClick();
    }

    public LiveEndInfoView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_live_end_cover, this);
        this.mHeadImg = (ImageView) findViewById(R.id.iv_anchor_head);
        this.userNickNameGradeView = (UserNickNameGradeView) findViewById(R.id.user_nickname);
        this.mTvId = (TextView) findViewById(R.id.tv_anchor_id);
        this.mTvAttention = (TextView) findViewById(R.id.tv_attention_anchor);
        this.mTvLastEndTime = (TextView) findViewById(R.id.tv_live_end_tips);
        this.mTvWatchNum = (TextView) findViewById(R.id.tv_personal);
        this.mTvLiveTime = (TextView) findViewById(R.id.tv_time);
        this.tvLivePre = (TextView) findViewById(R.id.tv_live_pre);
        this.mTvAttention.setOnClickListener(this);
        findViewById(R.id.tv_home).setOnClickListener(this);
        findViewById(R.id.iv_end_back).setOnClickListener(this);
    }

    public void initData(LiveEndEntity liveEndEntity) {
        if (liveEndEntity != null) {
            Context context;
            int i;
            this.userNickNameGradeView.initAnchorData(liveEndEntity.nickname, R.color.fq_colorWhite, liveEndEntity.sex, liveEndEntity.expGrade);
            setHeadImg(liveEndEntity.avatar);
            setTvLastEndTime(liveEndEntity.startTime);
            setTvWatchNum(liveEndEntity.maxPopularity);
            setTvLiveTime(liveEndEntity.startTime, liveEndEntity.endTime);
            setTvId(liveEndEntity.liveId);
            TextView textView = this.mTvAttention;
            if (liveEndEntity.isAttention()) {
                context = this.mContext;
                i = R.string.fq_home_btn_attention_yes;
            } else {
                context = this.mContext;
                i = R.string.fq_home_attention;
            }
            textView.setText(context.getString(i));
            this.mTvAttention.setSelected(liveEndEntity.isAttention());
            if (TextUtils.isEmpty(liveEndEntity.herald)) {
                this.tvLivePre.setVisibility(8);
            } else {
                this.tvLivePre.setVisibility(0);
                this.tvLivePre.setText(this.mContext.getString(R.string.fq_anchor_live_end_pre_notice_tips, new Object[]{d.c(p.b(liveEndEntity.publishTime) * 1000), liveEndEntity.herald}));
            }
        }
    }

    private void setTvId(String str) {
        this.mTvId.setText(this.mContext.getString(R.string.fq_live_room_id, new Object[]{str}));
    }

    private void setTvLastEndTime(String str) {
        str = d.a(str);
        this.mTvLastEndTime.setText(this.mContext.getString(R.string.fq_last_live_state_time, new Object[]{str}));
    }

    private void setTvWatchNum(String str) {
        this.mTvWatchNum.setText(Html.fromHtml(this.mContext.getString(R.string.fq_anchor_live_end_personal, new Object[]{str})));
    }

    private void setTvLiveTime(String str, String str2) {
        long b = p.b(str2) - p.b(str);
        str = d.a(1000 * b);
        if (b < 0) {
            str = "00:00";
        }
        this.mTvLiveTime.setText(Html.fromHtml(this.mContext.getString(R.string.fq_anchor_live_end_timer, new Object[]{str})));
    }

    public void setTvAttentionText(boolean z) {
        Context context;
        int i;
        TextView textView = this.mTvAttention;
        if (z) {
            context = this.mContext;
            i = R.string.fq_home_btn_attention_yes;
        } else {
            context = this.mContext;
            i = R.string.fq_home_attention;
        }
        textView.setText(context.getString(i));
        this.mTvAttention.setSelected(z);
    }

    private void setHeadImg(String str) {
        i.a(this.mContext, this.mHeadImg, str, 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
    }

    public void setLiveEndClickListener(LiveEndClickListener liveEndClickListener) {
        this.mLiveEndClickListener = liveEndClickListener;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_home && this.mLiveEndClickListener != null) {
            this.mLiveEndClickListener.onNavBackClick();
        }
        if (id == R.id.iv_end_back && this.mLiveEndClickListener != null) {
            this.mLiveEndClickListener.onGoHomeClick();
        }
        if (id == R.id.tv_attention_anchor && this.mLiveEndClickListener != null) {
            this.mLiveEndClickListener.onAttentionClick(view);
        }
    }
}
