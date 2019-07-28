package com.tomatolive.library.ui.view.custom;

import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.l;
import com.tomatolive.library.R;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.ui.adapter.UserAvatarListAdapter;
import com.tomatolive.library.ui.interfaces.OnLivePusherInfoCallback;
import com.tomatolive.library.ui.view.dialog.DedicateTopDialog;
import com.tomatolive.library.ui.view.dialog.GuardListDialog;
import com.tomatolive.library.ui.view.dialog.GuardOpenContentDialog.OnOpenGuardCallbackListener;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.utils.u;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.yv;
import defpackage.zf;
import defpackage.zh;
import defpackage.zn;
import defpackage.zy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.e;
import master.flame.danmaku.danmaku.model.android.j;
import master.flame.danmaku.danmaku.model.android.j.a;
import master.flame.danmaku.ui.widget.DanmakuView;

public class LivePusherInfoView extends LinearLayout {
    public static final int LIVE_TYPE_ANCHOR = 1;
    public static final int LIVE_TYPE_AUDIENCE = 2;
    private long anchorContribution = 0;
    private String anchorId;
    private String animPropertyName = "translationX";
    private DanmakuContext danmakuContext;
    private DanmakuView danmakuView;
    private DedicateTopDialog dedicateTopDialog;
    private FragmentManager fragmentManager;
    private AnimatorSet giftAnimatorSet;
    private LiveEntity guardInfoItem;
    private ImageView ivAvatar;
    private ImageView ivFollow;
    private ImageView ivGiftImg;
    private LiveAdBannerView liveAdBannerView;
    private ArrayList<String> liveRankConfig;
    private int liveType;
    private Context mContext;
    private RecyclerView mRvUserAvatar;
    private UserAvatarListAdapter mUserAvatarListAdapter;
    private OnLivePusherInfoCallback onLivePusherInfoCallback;
    private OnOpenGuardCallbackListener openGuardCallbackListener;
    private RelativeLayout rlGiftNoticeBg;
    private RelativeLayout rlSysNoticeBg;
    private AnimatorSet sysAnimatorSet;
    private TextView tvGold;
    private TextView tvGuardNumber;
    private TextView tvName;
    private TextView tvPersonalNum;
    private TextView tvSysNotice;
    private TextView tvTopNotice;

    private class DanmuViewHolder extends a {
        private ImageView ivAvatar;
        private ImageView ivAvatarBg;
        private ImageView ivGuardType;
        private RelativeLayout rlContentBg;
        private TextView tvContent;
        private TextView tvGrade;
        private TextView tvNickName;

        /* synthetic */ DanmuViewHolder(LivePusherInfoView livePusherInfoView, View view, AnonymousClass1 anonymousClass1) {
            this(view);
        }

        private DanmuViewHolder(View view) {
            super(view);
            this.tvContent = (TextView) view.findViewById(R.id.tv_msg_content);
            this.tvGrade = (TextView) view.findViewById(R.id.tv_grade);
            this.tvNickName = (TextView) view.findViewById(R.id.tv_nick_name);
            this.ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            this.rlContentBg = (RelativeLayout) view.findViewById(R.id.rl_msg_bg);
            this.ivAvatarBg = (ImageView) view.findViewById(R.id.iv_avatar_bg);
            this.ivGuardType = (ImageView) view.findViewById(R.id.iv_guard_type);
            b.a(LivePusherInfoView.this.mContext, this.tvGrade, "");
        }

        public void measure(int i, int i2) {
            try {
                super.measure(i, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void layout(int i, int i2, int i3, int i4) {
            try {
                super.layout(i, i2, i3, i4);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void initUserGrade(TextView textView, int i) {
            textView.setBackgroundResource(b.a(false, i));
            setTextViewDrawable(textView, b.b(true, i));
            textView.setText(String.valueOf(i));
        }

        private void setTextViewDrawable(TextView textView, @DrawableRes int i) {
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(LivePusherInfoView.this.mContext, i), null, null, null);
        }
    }

    private long formatAnimatorSetDuration(long j) {
        j -= 3000;
        return j < 0 ? 6000 : j;
    }

    public LivePusherInfoView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        initView();
    }

    private void initView() {
        inflate(this.mContext, R.layout.fq_include_live_pusher_info, this);
        this.tvName = (TextView) findViewById(R.id.tv_name);
        this.tvPersonalNum = (TextView) findViewById(R.id.tv_member);
        this.ivAvatar = (ImageView) findViewById(R.id.iv_avatar);
        this.ivFollow = (ImageView) findViewById(R.id.iv_follow);
        this.tvGold = (TextView) findViewById(R.id.tv_money);
        this.tvTopNotice = (TextView) findViewById(R.id.tv_top_notice);
        this.tvGuardNumber = (TextView) findViewById(R.id.tv_guard_number);
        this.mRvUserAvatar = (RecyclerView) findViewById(R.id.recycle_user_avatar);
        this.ivGiftImg = (ImageView) findViewById(R.id.iv_gift_img);
        this.rlGiftNoticeBg = (RelativeLayout) findViewById(R.id.rl_top_notice_bg);
        this.liveAdBannerView = (LiveAdBannerView) findViewById(R.id.live_banner_view);
        this.tvSysNotice = (TextView) findViewById(R.id.tv_sys_notice);
        this.rlSysNoticeBg = (RelativeLayout) findViewById(R.id.rl_sys_notice_bg);
        initDanmakuContext();
        initUserAvatarListAdapter();
        initListener();
    }

    private void initListener() {
        r.a().a(this.tvGold, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new r.a() {
            public void action(Object obj) {
                if (LivePusherInfoView.this.fragmentManager != null) {
                    LivePusherInfoView.this.dedicateTopDialog = DedicateTopDialog.newInstance(LivePusherInfoView.this.liveType, LivePusherInfoView.this.anchorId, LivePusherInfoView.this.liveRankConfig);
                    LivePusherInfoView.this.dedicateTopDialog.show(LivePusherInfoView.this.fragmentManager);
                }
            }
        });
        r.a().a(findViewById(R.id.rl_guard_number), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$LivePusherInfoView$0vHEfcYZxe5hBAs5rFM419DgxyQ(this));
        r.a().a(findViewById(R.id.iv_avatar), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$LivePusherInfoView$Blir6I1A2xalwRumSAc6QoFcDus(this));
        r.a().a(findViewById(R.id.tv_top_notice), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$LivePusherInfoView$G4nFKhH8zgJH5e2Oq2J8YVrM3Ws(this));
        r.a().a(findViewById(R.id.tv_sys_notice), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$LivePusherInfoView$khotqmBFWbzNhHqQG8TlUdRlFUk(this));
        r.a().a(findViewById(R.id.iv_follow), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$LivePusherInfoView$KQ1Lmmny7yI3VvhrY8ag228HUN8(this));
    }

    public static /* synthetic */ void lambda$initListener$1(LivePusherInfoView livePusherInfoView, Object obj) {
        if (livePusherInfoView.fragmentManager != null) {
            GuardListDialog.newInstance(livePusherInfoView.liveType, livePusherInfoView.guardInfoItem, new -$$Lambda$LivePusherInfoView$jM4ijG-Fa8MAGZ_GfAxWAEOqygQ(livePusherInfoView)).show(livePusherInfoView.fragmentManager);
        }
    }

    public static /* synthetic */ void lambda$initListener$2(LivePusherInfoView livePusherInfoView, Object obj) {
        if (livePusherInfoView.onLivePusherInfoCallback != null) {
            livePusherInfoView.onLivePusherInfoCallback.onClickAnchorAvatarListener(livePusherInfoView.findViewById(R.id.iv_avatar));
        }
    }

    public static /* synthetic */ void lambda$initListener$3(LivePusherInfoView livePusherInfoView, Object obj) {
        if (livePusherInfoView.onLivePusherInfoCallback != null) {
            livePusherInfoView.onLivePusherInfoCallback.onClickGiftNoticeListener(livePusherInfoView.findViewById(R.id.tv_top_notice));
        }
    }

    public static /* synthetic */ void lambda$initListener$4(LivePusherInfoView livePusherInfoView, Object obj) {
        if (livePusherInfoView.onLivePusherInfoCallback != null) {
            livePusherInfoView.onLivePusherInfoCallback.onClickSysNoticeListener(livePusherInfoView.findViewById(R.id.tv_sys_notice));
        }
    }

    public static /* synthetic */ void lambda$initListener$5(LivePusherInfoView livePusherInfoView, Object obj) {
        if (livePusherInfoView.onLivePusherInfoCallback != null) {
            livePusherInfoView.onLivePusherInfoCallback.onFollowAnchorListener(livePusherInfoView.findViewById(R.id.iv_follow));
        }
    }

    private void initUserAvatarListAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        linearLayoutManager.setOrientation(0);
        this.mUserAvatarListAdapter = new UserAvatarListAdapter(R.layout.fq_recycle_item_user_avatar);
        this.mRvUserAvatar.setAdapter(this.mUserAvatarListAdapter);
        this.mRvUserAvatar.setLayoutManager(linearLayoutManager);
        this.mUserAvatarListAdapter.bindToRecyclerView(this.mRvUserAvatar);
        this.mUserAvatarListAdapter.setOnItemClickListener(new -$$Lambda$LivePusherInfoView$9rqbrFOA0g8t5uz6PFo9TZo-WXw(this));
    }

    public static /* synthetic */ void lambda$initUserAvatarListAdapter$6(LivePusherInfoView livePusherInfoView, UserEntity userEntity) {
        if (livePusherInfoView.onLivePusherInfoCallback != null) {
            livePusherInfoView.onLivePusherInfoCallback.onClickUserAvatarListener(userEntity);
        }
    }

    public void dismissDedicateTopDialog() {
        if (this.dedicateTopDialog != null && this.dedicateTopDialog.isAdded()) {
            this.dedicateTopDialog.dismiss();
        }
    }

    public void initLivePusherInfoCallback(int i, FragmentManager fragmentManager, OnLivePusherInfoCallback onLivePusherInfoCallback) {
        this.liveType = i;
        this.fragmentManager = fragmentManager;
        this.ivFollow.setVisibility(i == 2 ? 0 : 8);
        this.onLivePusherInfoCallback = onLivePusherInfoCallback;
    }

    public void initData(LiveEntity liveEntity) {
        if (liveEntity == null) {
            this.tvGold.setText(this.mContext.getString(R.string.fq_top_contribution));
            return;
        }
        this.guardInfoItem = getAnchorGuardInfo(liveEntity);
        this.anchorId = liveEntity.anchorId;
        this.anchorContribution = p.b(liveEntity.anchorContribution);
        this.tvGuardNumber.setText(liveEntity.anchorGuardCount);
        i.a(this.mContext, this.ivAvatar, liveEntity.avatar);
        this.tvName.setText(u.a(liveEntity.nickname, 6));
        if (this.anchorContribution == -1) {
            this.tvGold.setText(this.mContext.getString(R.string.fq_top_contribution));
        } else {
            if (this.anchorContribution < 0) {
                this.anchorContribution = 0;
            }
            this.tvGold.setText(String.valueOf(this.anchorContribution));
        }
    }

    public void setRoomPerson(long j) {
        this.tvPersonalNum.setText(p.d(String.valueOf(j)));
    }

    public void setAnchorContribution(String str) {
        if (this.anchorContribution == -1) {
            this.tvGold.setText(this.mContext.getString(R.string.fq_top_contribution));
            return;
        }
        this.anchorContribution += p.b(str);
        if (this.anchorContribution < 0) {
            this.anchorContribution = 0;
        }
        this.tvGold.setText(String.valueOf(this.anchorContribution));
    }

    public void setGiftAnimListener(AnimatorListener animatorListener) {
        if (animatorListener != null) {
            int a = l.a();
            this.giftAnimatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.rlGiftNoticeBg, this.animPropertyName, new float[]{(float) a, CropImageView.DEFAULT_ASPECT_RATIO});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.rlGiftNoticeBg, this.animPropertyName, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, (float) (-a)});
            ofFloat2.setStartDelay(3000);
            this.giftAnimatorSet.play(ofFloat).before(ofFloat2);
            this.giftAnimatorSet.addListener(animatorListener);
        }
    }

    public void setGiftNoticeAnim(String str, String str2, String str3, String str4, String str5, long j) {
        this.rlGiftNoticeBg.setVisibility(0);
        str = u.a(str, 5);
        str2 = u.a(str2, 5);
        this.tvTopNotice.setText(Html.fromHtml(this.mContext.getString(R.string.fq_text_live_top_notice_tips, new Object[]{str, str2, str3, str4})));
        str = GiftDownLoadManager.getInstance().getGiftItemImgUrl(str5);
        if (!TextUtils.isEmpty(str)) {
            i.c(this.mContext, this.ivGiftImg, str);
        }
        if (this.giftAnimatorSet != null) {
            this.giftAnimatorSet.setDuration(formatAnimatorSetDuration(j));
            this.giftAnimatorSet.start();
        }
    }

    public void setSysNoticeAnimListener(AnimatorListener animatorListener) {
        if (animatorListener != null) {
            int a = l.a();
            this.sysAnimatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.rlSysNoticeBg, this.animPropertyName, new float[]{(float) a, CropImageView.DEFAULT_ASPECT_RATIO});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.rlSysNoticeBg, this.animPropertyName, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, (float) (-a)});
            ofFloat2.setStartDelay(3000);
            this.sysAnimatorSet.play(ofFloat).before(ofFloat2);
            this.sysAnimatorSet.addListener(animatorListener);
        }
    }

    public void setSysNoticeAnim(String str, long j) {
        this.rlSysNoticeBg.setVisibility(0);
        this.tvSysNotice.setText(str);
        if (this.sysAnimatorSet != null) {
            this.sysAnimatorSet.setDuration(formatAnimatorSetDuration(j));
            this.sysAnimatorSet.start();
        }
    }

    public void addUserItem(UserEntity userEntity) {
        try {
            List data = this.mUserAvatarListAdapter.getData();
            data.add(userEntity);
            Collections.sort(data);
            this.mUserAvatarListAdapter.replaceData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortUserList(String str, String str2, String str3) {
        try {
            List<UserEntity> data = this.mUserAvatarListAdapter.getData();
            for (UserEntity userEntity : data) {
                if (TextUtils.equals(userEntity.getUserId(), str)) {
                    userEntity.setGuardType(p.a(str2));
                    userEntity.setExpGrade(str3);
                    break;
                }
            }
            Collections.sort(data);
            this.mUserAvatarListAdapter.replaceData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replaceData(List<UserEntity> list) {
        this.mUserAvatarListAdapter.replaceData(list);
    }

    public boolean contains(String str) {
        try {
            for (UserEntity userId : this.mUserAvatarListAdapter.getData()) {
                if (TextUtils.equals(userId.getUserId(), str)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeUserItemById(String str) {
        this.mUserAvatarListAdapter.removeItemById(str);
    }

    public void initAdBannerImages(List<BannerEntity> list) {
        this.liveAdBannerView.initAdBannerImages(list);
    }

    public void initVerticalAdImage(List<BannerEntity> list) {
        this.liveAdBannerView.initVerticalAdImage(list);
    }

    public void hideNoticeViews() {
        this.rlGiftNoticeBg.setVisibility(4);
        this.rlSysNoticeBg.setVisibility(4);
    }

    public void addDanmuMsg(ChatEntity chatEntity) {
        if (this.danmakuContext != null) {
            zf a = this.danmakuContext.t.a(1);
            if (a != null && this.danmakuView != null) {
                a.a((Object) chatEntity);
                a.m = (byte) 1;
                a.d(this.danmakuView.getCurrentTime());
                a.h = 0;
                this.danmakuView.a(a);
            }
        }
    }

    public void onPause() {
        if (this.danmakuView != null && this.danmakuView.g()) {
            this.danmakuView.i();
        }
    }

    public void onResume() {
        if (this.danmakuView != null && this.danmakuView.g() && this.danmakuView.k()) {
            this.danmakuView.j();
        }
    }

    public void onDestroy() {
        if (this.giftAnimatorSet != null) {
            this.giftAnimatorSet.cancel();
            this.giftAnimatorSet.removeAllListeners();
            this.giftAnimatorSet = null;
            this.rlGiftNoticeBg.setVisibility(4);
        }
        if (this.sysAnimatorSet != null) {
            this.sysAnimatorSet.cancel();
            this.sysAnimatorSet.removeAllListeners();
            this.sysAnimatorSet = null;
            this.rlSysNoticeBg.setVisibility(4);
        }
        if (this.danmakuView != null) {
            this.danmakuView.setCallback(null);
            this.danmakuView.e();
            this.danmakuView = null;
        }
        if (this.onLivePusherInfoCallback != null) {
            this.onLivePusherInfoCallback = null;
        }
        setOpenGuardCallbackListener(null);
    }

    public void setOpenGuardCallbackListener(OnOpenGuardCallbackListener onOpenGuardCallbackListener) {
        this.openGuardCallbackListener = onOpenGuardCallbackListener;
    }

    public void setLiveRankConfig(ArrayList<String> arrayList) {
        this.liveRankConfig = arrayList;
    }

    public void setFollowed(boolean z) {
        this.ivFollow.setSelected(z);
    }

    public boolean isFollowed() {
        return this.ivFollow.isSelected();
    }

    public void initAnchorGuard(LiveEntity liveEntity) {
        if (liveEntity != null) {
            this.guardInfoItem = getAnchorGuardInfo(liveEntity);
            this.tvGuardNumber.setText(liveEntity.anchorGuardCount);
        }
    }

    public void updateOpenGuardInfo(GuardItemEntity guardItemEntity) {
        if (guardItemEntity != null && this.guardInfoItem != null) {
            this.guardInfoItem.userGuardType = guardItemEntity.guardType;
            this.guardInfoItem.userGuardExpireTime = guardItemEntity.endTime;
            if (p.a(guardItemEntity.guardType) == p.a("1")) {
                this.guardInfoItem.isOpenWeekGuard = "1";
            }
        }
    }

    public void updateOpenGuardCount(String str) {
        if (!(this.guardInfoItem == null || TextUtils.isEmpty(str))) {
            this.guardInfoItem.anchorGuardCount = str;
            this.tvGuardNumber.setText(this.guardInfoItem.anchorGuardCount);
        }
    }

    public void updateUserGradeInfo(String str) {
        if (this.guardInfoItem != null) {
            this.guardInfoItem.expGrade = str;
        }
    }

    private LiveEntity getAnchorGuardInfo(LiveEntity liveEntity) {
        LiveEntity liveEntity2 = new LiveEntity();
        liveEntity2.anchorId = liveEntity.anchorId;
        liveEntity2.anchorGuardCount = liveEntity.anchorGuardCount;
        liveEntity2.userGuardType = liveEntity.userGuardType;
        liveEntity2.userGuardExpireTime = liveEntity.userGuardExpireTime;
        liveEntity2.isOpenWeekGuard = liveEntity.isOpenWeekGuard;
        liveEntity2.nickname = liveEntity.nickname;
        liveEntity2.expGrade = liveEntity.audienceExpGrade;
        return liveEntity2;
    }

    private void initDanmakuContext() {
        Map hashMap = new HashMap();
        hashMap.put(Integer.valueOf(1), Integer.valueOf(3));
        Map hashMap2 = new HashMap();
        hashMap2.put(Integer.valueOf(1), Boolean.valueOf(true));
        hashMap2.put(Integer.valueOf(5), Boolean.valueOf(true));
        this.danmakuView = (DanmakuView) findViewById(R.id.sv_danmaku);
        this.danmakuContext = DanmakuContext.a();
        this.danmakuContext.a(true);
        this.danmakuContext.b(false).c(2.2f).b(1.2f).a(new j<DanmuViewHolder>() {
            public DanmuViewHolder onCreateViewHolder(int i) {
                return new DanmuViewHolder(LivePusherInfoView.this, View.inflate(LivePusherInfoView.this.mContext, R.layout.fq_layout_live_msg_danmu_view, null), null);
            }

            public void onBindViewHolder(int i, final DanmuViewHolder danmuViewHolder, zf zfVar, master.flame.danmaku.danmaku.model.android.a.a aVar, TextPaint textPaint) {
                if (zfVar != null) {
                    Object obj = zfVar.d;
                    if (obj != null && (obj instanceof ChatEntity)) {
                        final ChatEntity chatEntity = (ChatEntity) obj;
                        danmuViewHolder.tvContent.setText(chatEntity.getMsgText());
                        danmuViewHolder.tvNickName.setText(u.a(chatEntity.getMsgSendName(), 5));
                        danmuViewHolder.tvContent.setBackground(ContextCompat.getDrawable(LivePusherInfoView.this.mContext, chatEntity.getGuardType() == p.a("3") ? R.drawable.fq_shape_danmu_guard_year_text_bg : R.drawable.fq_shape_danmu_guard_month_text_bg));
                        danmuViewHolder.ivGuardType.setImageResource(chatEntity.getGuardType() == p.a("3") ? R.drawable.fq_ic_live_msg_year_guard : R.drawable.fq_ic_live_msg_mouth_guard);
                        danmuViewHolder.ivAvatarBg.setImageResource(chatEntity.getGuardType() == p.a("3") ? R.drawable.fq_ic_guard_year_avatar_bg : R.drawable.fq_ic_guard_month_avatar_bg);
                        danmuViewHolder.initUserGrade(danmuViewHolder.tvGrade, p.a(chatEntity.getExpGrade()));
                        if (LivePusherInfoView.this.mContext instanceof Activity) {
                            ((Activity) LivePusherInfoView.this.mContext).runOnUiThread(new Runnable() {
                                public void run() {
                                    i.a(LivePusherInfoView.this.mContext, danmuViewHolder.ivAvatar, chatEntity.getUserAvatar(), R.drawable.fq_ic_placeholder_avatar);
                                }
                            });
                        }
                    }
                }
            }

            public void releaseResource(zf zfVar) {
                super.releaseResource(zfVar);
                if (zfVar != null) {
                    zfVar.a(null);
                }
            }
        }, null).a(hashMap).b(hashMap2);
        this.danmakuView.setCallback(new yv.a() {
            public void danmakuShown(zf zfVar) {
            }

            public void drawingFinished() {
            }

            public void updateTimer(zh zhVar) {
            }

            public void prepared() {
                LivePusherInfoView.this.danmakuView.m();
            }
        });
        this.danmakuView.a(new zy() {
            /* Access modifiers changed, original: protected */
            public zn parse() {
                return new e();
            }
        }, this.danmakuContext);
        this.danmakuView.a(false);
        this.danmakuView.b(true);
    }
}
