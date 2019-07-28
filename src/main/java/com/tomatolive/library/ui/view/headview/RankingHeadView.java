package com.tomatolive.library.ui.view.headview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.ui.view.custom.AnchorGradeView;
import com.tomatolive.library.ui.view.custom.UserGradeView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import java.util.List;

public class RankingHeadView extends RelativeLayout implements OnClickListener {
    private AnchorGradeView anchorGradeView;
    private ImageView ivAttention;
    private ImageView ivAvatar;
    private ImageView ivLive;
    private ImageView ivSex;
    private List<AnchorEntity> list;
    private OnAvatarClickListener onAvatarClickListener;
    private OnBtnAttentionClickListener onBtnAttentionClickListener;
    private TextView tvNickName;
    private TextView tvNums;
    private UserGradeView userGradeView;

    public interface OnAvatarClickListener {
        void onAvatarClick(LiveEntity liveEntity);
    }

    public interface OnBtnAttentionClickListener {
        void onBtnAttentionClick(View view, AnchorEntity anchorEntity);
    }

    public RankingHeadView(Context context) {
        this(context, null);
    }

    public RankingHeadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RankingHeadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.fq_layout_head_view_ranking, this);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.ivSex = (ImageView) findViewById(R.id.iv_gender_sex);
        this.ivLive = (ImageView) findViewById(R.id.iv_live);
        this.tvNickName = (TextView) findViewById(R.id.tv_nick_name);
        this.tvNums = (TextView) findViewById(R.id.tv_num);
        this.userGradeView = (UserGradeView) findViewById(R.id.grade_view);
        this.anchorGradeView = (AnchorGradeView) findViewById(R.id.anchor_grade_view);
        this.ivAttention = (ImageView) findViewById(R.id.iv_attention);
        this.ivAvatar.setOnClickListener(this);
        this.ivAttention.setOnClickListener(this);
    }

    public void initData(List<AnchorEntity> list, int i) {
        this.list = list;
        if (list != null && list.size() > 0) {
            int i2 = 0;
            AnchorEntity anchorEntity = (AnchorEntity) list.get(0);
            i.a(getContext(), this.ivAvatar, anchorEntity.avatar);
            this.ivAttention.setVisibility(i == 4 ? 0 : 4);
            this.ivAttention.setSelected(anchorEntity.isAttention());
            this.tvNickName.setText(anchorEntity.nickname);
            this.tvNums.setText(getNumStr(i, i == 4 ? anchorEntity.income : anchorEntity.expend));
            int i3 = 8;
            this.anchorGradeView.setVisibility(i == 4 ? 0 : 8);
            UserGradeView userGradeView = this.userGradeView;
            if (i != 4) {
                i3 = 0;
            }
            userGradeView.setVisibility(i3);
            this.anchorGradeView.initUserGrade(anchorEntity.expGrade);
            this.userGradeView.initUserGrade(anchorEntity.expGrade);
            this.ivSex.setImageResource(b.e(anchorEntity.sex));
            ImageView imageView = this.ivLive;
            if (!b.b(anchorEntity.liveStatus)) {
                i2 = 4;
            }
            imageView.setVisibility(i2);
            i.a(getContext(), this.ivLive);
        }
    }

    public OnAvatarClickListener getOnAvatarClickListener() {
        return this.onAvatarClickListener;
    }

    public void setOnAvatarClickListener(OnAvatarClickListener onAvatarClickListener) {
        this.onAvatarClickListener = onAvatarClickListener;
    }

    public OnBtnAttentionClickListener getOnBtnAttentionClickListener() {
        return this.onBtnAttentionClickListener;
    }

    public void setOnBtnAttentionClickListener(OnBtnAttentionClickListener onBtnAttentionClickListener) {
        this.onBtnAttentionClickListener = onBtnAttentionClickListener;
    }

    private String getNumStr(int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        if (i == 4) {
            stringBuilder.append(getContext().getString(R.string.fq_tomato_money_gain));
            stringBuilder.append(str);
            stringBuilder.append(b.b(getContext()));
        } else {
            stringBuilder.append(getContext().getString(R.string.fq_tomato_money_reward));
            stringBuilder.append(str);
            stringBuilder.append(b.b(getContext()));
        }
        return stringBuilder.toString();
    }

    public void onClick(View view) {
        if (this.list != null) {
            if (view.getId() == R.id.iv_avatar) {
                if (this.list.size() >= 1 && this.onAvatarClickListener != null) {
                    this.onAvatarClickListener.onAvatarClick(formatLiveEntity((AnchorEntity) this.list.get(0)));
                }
                return;
            }
            if (view.getId() == R.id.iv_attention && this.list.size() >= 1 && this.onBtnAttentionClickListener != null) {
                this.onBtnAttentionClickListener.onBtnAttentionClick(view, (AnchorEntity) this.list.get(0));
            }
        }
    }

    private LiveEntity formatLiveEntity(AnchorEntity anchorEntity) {
        LiveEntity liveEntity = new LiveEntity();
        liveEntity.liveId = anchorEntity.liveId;
        liveEntity.userId = anchorEntity.userId;
        liveEntity.isLiving = anchorEntity.isLiving;
        liveEntity.liveStatus = anchorEntity.liveStatus;
        liveEntity.streamName = anchorEntity.streamName;
        return liveEntity;
    }
}
