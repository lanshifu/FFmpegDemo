package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import com.tomatolive.library.utils.z;

public class AnchorLiveEndView extends RelativeLayout {
    public static final int END_TYPE_1 = 1;
    public static final int END_TYPE_2 = 2;
    public static final int END_TYPE_3 = 3;
    private ImageView ivAvatar;
    private LinearLayout llNumBg;
    private final Context mContext;
    private OnLiveEndClosedListener onLiveEndClosedListener;
    private TextView tvEndText;
    private TextView tvEndTips;
    private TextView tvError;
    private TextView tvGold;
    private TextView tvId;
    private TextView tvPersonal;
    private TextView tvTime;
    private UserNickNameGradeView userNickNameGradeView;

    public interface OnLiveEndClosedListener {
        void onClosedListener();
    }

    public AnchorLiveEndView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_layout_anchor_live_end_view, this);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_anchor_head);
        this.tvId = (TextView) findViewById(R.id.tv_anchor_id);
        this.tvEndText = (TextView) findViewById(R.id.tv_live_end_text);
        this.tvEndTips = (TextView) findViewById(R.id.tv_live_end_tips);
        this.tvError = (TextView) findViewById(R.id.tv_live_end_error);
        this.tvGold = (TextView) findViewById(R.id.tv_gold);
        this.tvPersonal = (TextView) findViewById(R.id.tv_personal);
        this.tvTime = (TextView) findViewById(R.id.tv_time);
        this.llNumBg = (LinearLayout) findViewById(R.id.ll_num_bg);
        this.userNickNameGradeView = (UserNickNameGradeView) findViewById(R.id.user_nickname);
        findViewById(R.id.iv_end_back).setOnClickListener(new -$$Lambda$AnchorLiveEndView$0Omyb3wSSnd30nqoqL4VTkJ3zpo(this));
        findViewById(R.id.tv_home).setOnClickListener(new -$$Lambda$AnchorLiveEndView$ofBjqAUpENbLBnQ99QaYzFnj6Js(this));
    }

    public static /* synthetic */ void lambda$initView$0(AnchorLiveEndView anchorLiveEndView, View view) {
        if (anchorLiveEndView.onLiveEndClosedListener != null) {
            anchorLiveEndView.onLiveEndClosedListener.onClosedListener();
        }
    }

    public static /* synthetic */ void lambda$initView$1(AnchorLiveEndView anchorLiveEndView, View view) {
        if (anchorLiveEndView.onLiveEndClosedListener != null) {
            anchorLiveEndView.onLiveEndClosedListener.onClosedListener();
        }
    }

    public void initData(int i, LiveEntity liveEntity) {
        if (liveEntity != null) {
            long j;
            i.a(this.mContext, this.ivAvatar, liveEntity.avatar, 6, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
            this.userNickNameGradeView.initAnchorData(liveEntity.nickname, R.color.fq_colorWhite, liveEntity.sex, liveEntity.expGrade);
            TextView textView = this.tvId;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getResources().getString(R.string.fq_id));
            stringBuilder.append(liveEntity.liveId);
            textView.setText(stringBuilder.toString());
            if (i == 3) {
                this.tvError.setVisibility(0);
                this.tvEndTips.setVisibility(4);
                this.tvEndText.setVisibility(4);
                j = liveEntity.tempTime;
            } else {
                Context context;
                int i2;
                this.tvError.setVisibility(4);
                textView = this.tvEndText;
                if (i == 1) {
                    context = this.mContext;
                    i2 = R.string.fq_live_end;
                } else {
                    context = this.mContext;
                    i2 = R.string.fq_live_end_suspend;
                }
                textView.setText(context.getString(i2));
                this.tvEndTips.setVisibility(i == 1 ? 0 : 4);
                this.llNumBg.setVisibility(i == 1 ? 0 : 4);
                if (liveEntity.liveCount.equals("0")) {
                    this.tvEndTips.setVisibility(4);
                } else {
                    this.tvEndTips.setText(this.mContext.getString(R.string.fq_text_start_live_count_tips, new Object[]{liveEntity.liveCount}));
                }
                j = (p.b(liveEntity.endTime) - p.b(liveEntity.startTime)) * 1000;
            }
            String a = d.a(j);
            if (j < 0) {
                a = "0";
            }
            this.tvGold.setText(Html.fromHtml(getLiveEndGoldStr(liveEntity.giftIncomeCurrentLive)));
            this.tvPersonal.setText(Html.fromHtml(this.mContext.getString(R.string.fq_anchor_live_end_personal, new Object[]{liveEntity.maxPopularity})));
            this.tvTime.setText(Html.fromHtml(this.mContext.getString(R.string.fq_anchor_live_end_timer, new Object[]{a})));
        }
    }

    public void setOnLiveEndClosedListener(OnLiveEndClosedListener onLiveEndClosedListener) {
        this.onLiveEndClosedListener = onLiveEndClosedListener;
    }

    private void initDefData() {
        i.a(this.mContext, this.ivAvatar, z.a().g(), 3, ContextCompat.getColor(this.mContext, R.color.fq_colorWhite));
        this.userNickNameGradeView.initAnchorData(z.a().f(), R.color.fq_colorWhite, z.a().h(), "1");
        this.tvId.setText("");
        this.tvEndText.setText(this.mContext.getString(R.string.fq_live_end));
        this.tvEndTips.setVisibility(0);
        this.llNumBg.setVisibility(0);
        this.tvEndTips.setText("");
        this.tvGold.setText(Html.fromHtml(getLiveEndGoldStr("0")));
        this.tvPersonal.setText(Html.fromHtml(this.mContext.getString(R.string.fq_anchor_live_end_personal, new Object[]{"0"})));
        this.tvTime.setText(Html.fromHtml(this.mContext.getString(R.string.fq_anchor_live_end_timer, new Object[]{"0"})));
    }

    private String getLiveEndGoldStr(String str) {
        if (TextUtils.equals("-1", str)) {
            return this.mContext.getString(R.string.fq_anchor_live_end_gold, new Object[]{"获取中...", b.b(this.mContext)});
        }
        return this.mContext.getString(R.string.fq_anchor_live_end_gold, new Object[]{str, b.b(this.mContext)});
    }
}
