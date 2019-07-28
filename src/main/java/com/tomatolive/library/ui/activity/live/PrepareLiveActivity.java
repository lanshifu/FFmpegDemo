package com.tomatolive.library.ui.activity.live;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.blankj.utilcode.util.h;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.rtmp.TXLivePusher;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.faceunity.BeautyControlView;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.model.GiftIndexEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.MenuEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.SendMessageEntity;
import com.tomatolive.library.model.SocketMessageEvent;
import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import com.tomatolive.library.model.event.AttentionEvent;
import com.tomatolive.library.model.event.StartLiveEvent;
import com.tomatolive.library.service.TokenDialogService;
import com.tomatolive.library.ui.interfaces.impl.SimpleAnimatorListener;
import com.tomatolive.library.ui.interfaces.impl.SimpleLivePusherInfoCallback;
import com.tomatolive.library.ui.interfaces.impl.SimpleSVGACallBack;
import com.tomatolive.library.ui.presenter.PrePareLivePresenter;
import com.tomatolive.library.ui.view.custom.AnchorLiveEndView;
import com.tomatolive.library.ui.view.custom.GiftBoxView;
import com.tomatolive.library.ui.view.custom.GiftBoxView.OnSendGiftBoxMsgListener;
import com.tomatolive.library.ui.view.custom.GuardOpenDanmuView.OnAnimPlayListener;
import com.tomatolive.library.ui.view.custom.LiveAnimationView;
import com.tomatolive.library.ui.view.custom.LiveAnimationView.OnGiftNotifyCallback;
import com.tomatolive.library.ui.view.custom.LiveChatMsgView;
import com.tomatolive.library.ui.view.custom.LivePusherInfoView;
import com.tomatolive.library.ui.view.custom.PreStartLiveView;
import com.tomatolive.library.ui.view.custom.PreStartLiveView.OnPreStartLiveCallback;
import com.tomatolive.library.ui.view.custom.StickerAddView;
import com.tomatolive.library.ui.view.dialog.AnchorAvatarDialog;
import com.tomatolive.library.ui.view.dialog.BeautyDialog;
import com.tomatolive.library.ui.view.dialog.BeautyDialog.BeautyParams;
import com.tomatolive.library.ui.view.dialog.BeautyDialog.OnBeautyParamsChangeListener;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils.BottomPromptMenuListener;
import com.tomatolive.library.ui.view.dialog.CoverSettingDialog;
import com.tomatolive.library.ui.view.dialog.GiftBoxPresenterDialog;
import com.tomatolive.library.ui.view.dialog.GiftRecordDialog;
import com.tomatolive.library.ui.view.dialog.InputTextMsgDialog;
import com.tomatolive.library.ui.view.dialog.InputTextMsgDialog.OnTextSendListener;
import com.tomatolive.library.ui.view.dialog.LiveActionBottomDialog;
import com.tomatolive.library.ui.view.dialog.NetworkPromptDialog;
import com.tomatolive.library.ui.view.dialog.PermissionDialog;
import com.tomatolive.library.ui.view.dialog.TransDialog;
import com.tomatolive.library.ui.view.dialog.UpdateTitleDialog;
import com.tomatolive.library.ui.view.dialog.UserAvatarDialog;
import com.tomatolive.library.ui.view.dialog.UserGuardAvatarDialog;
import com.tomatolive.library.ui.view.dialog.UserSuperAvatarDialog;
import com.tomatolive.library.ui.view.dialog.WarnDialog;
import com.tomatolive.library.ui.view.gift.GiftAnimModel;
import com.tomatolive.library.ui.view.gift.GiftFrameLayout.BarrageEndAnimationListener;
import com.tomatolive.library.ui.view.iview.IPrepareLiveView;
import com.tomatolive.library.utils.e;
import com.tomatolive.library.utils.emoji.EmojiParser;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.k;
import com.tomatolive.library.utils.m;
import com.tomatolive.library.utils.o;
import com.tomatolive.library.utils.p;
import com.tomatolive.library.utils.q;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.utils.v;
import com.tomatolive.library.utils.y;
import com.tomatolive.library.utils.z;
import com.tomatolive.library.websocket.interfaces.BackgroundSocketCallBack;
import com.tomatolive.library.websocket.interfaces.OnWebSocketStatusListener;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import com.tomatolive.library.websocket.nvwebsocket.MessageHelper;
import com.tomatolive.library.websocket.nvwebsocket.WsManager;
import com.tomatolive.library.websocket.nvwebsocket.WsStatus;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sg;
import defpackage.sg.a;
import defpackage.sh;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.disposables.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.greenrobot.eventbus.c;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class PrepareLiveActivity extends BaseActivity<PrePareLivePresenter> implements OnTextSendListener, BarrageEndAnimationListener, IPrepareLiveView, BackgroundSocketCallBack {
    private static final long DURATION_GET_MESSAGE = 1000;
    private static final int MAX_GET_ITEM_NUM_ONCE = 5;
    private static final int MAX_ITEM_NUM = 100;
    private static final long PUASE_TIME = 80;
    private AnchorAvatarDialog anchorAvatarDialog;
    private AnchorLiveEndView anchorLiveEndView;
    private boolean asleep = true;
    private volatile List<String> banedPostList = new ArrayList();
    private BeautyDialog beautyDialog;
    private Dialog bottomMoreDialog;
    private volatile boolean canShowEnterMsg = true;
    private volatile boolean canShowGiftNotice = true;
    private volatile boolean canShowGuardEnterMsg = true;
    private volatile boolean canShowGuardOPenMsg = true;
    private volatile boolean canShowSysNotice = true;
    private volatile boolean carFullAnimFinish = true;
    private String cdTime = "1";
    private String chatContent;
    private AnimatorSet countDownAnimSet;
    private String defaultSpeakLevel;
    private DisplayMetrics dm;
    private WarnDialog endWarnDialog;
    private ConcurrentLinkedQueue<ResultData> enterMsgQueue = new ConcurrentLinkedQueue();
    private final String enterType = "1";
    private String entryNoticeLevelThreshold = "10";
    private RelativeLayout faceRoot;
    private ConcurrentLinkedQueue<ResultData> giftNoticeQueue = new ConcurrentLinkedQueue();
    private long giftTrumpetPlayPeriod = 9000;
    private ConcurrentLinkedQueue<ResultData> guardEnterMsgQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<ResultData> guardOpenMsgQueue = new ConcurrentLinkedQueue();
    private int heartTime = 30000;
    private boolean isBanGroup = false;
    private boolean isBanPostAll;
    private boolean isBtnStartClick;
    private boolean isCameraOpenFail;
    private boolean isCameraPermissions;
    private boolean isConnectingChatService = true;
    private boolean isFontCamera = true;
    private volatile boolean isLiving;
    private boolean isMicPermissions;
    private boolean isNoNetEvent;
    private volatile boolean isPushInBackground;
    private boolean isPushSuc;
    private boolean isReConnectStatus;
    private boolean isSocketClose = true;
    private boolean isSocketError = true;
    private boolean isStartGetAnchorInfo;
    private boolean isStartGiftUpdate;
    private boolean isSuperBan;
    private volatile boolean isTranOpen;
    private boolean isWarn;
    private ImageView ivTrans;
    private int lastBeautyProgress;
    private boolean lastMirrorOpen;
    private int lastRuddyProgress;
    private int lastWhiteProgress;
    private LiveAnimationView liveAnimationView;
    private RelativeLayout liveBottomBar;
    private LiveChatMsgView liveChatMsgView;
    private int liveCount;
    private String liveId;
    private LiveEntity liveInfoItem;
    private LinearLayout llChatRoot;
    private ImageView mAnchorCoverImg;
    private BeautyControlView mFaceUnityControlView;
    private GiftBoxView mGiftBoxView;
    private InputTextMsgDialog mInputTextMsgDialog;
    private LivePusherInfoView mLivePusherInfoView;
    private PreStartLiveView mPreStartLiveView;
    private String mPushUrl;
    private StickerAddView mStickerAddView;
    private Handler mainHandler;
    private AtomicLong onLineCount = new AtomicLong(0);
    private b pauseTimer;
    private sg pushManager;
    private boolean reConnectCountOver;
    private int reCount = 3;
    private Map<String, Map<String, GiftIndexEntity>> receiveGiftMap = new HashMap();
    private ConcurrentLinkedQueue<ChatEntity> receiveMsgQueue = new ConcurrentLinkedQueue();
    private Map<String, Map<String, GiftIndexEntity>> receivePropMap = new HashMap();
    private RelativeLayout rlLiveRoot;
    private List<String> shieldedList = new ArrayList();
    private RelativeLayout shootContainer;
    private String socketUrl;
    private String speakLevel = "1";
    private WarnDialog startWarnDialog;
    private String streamName;
    private v swipeAnimationController;
    private ConcurrentLinkedQueue<ResultData> sysNoticeQueue = new ConcurrentLinkedQueue();
    private BaseQuickAdapter<MenuEntity, BaseViewHolder> tempAdapter;
    private long tempAmount;
    private MenuEntity tempFlashMenuEntity;
    private int tempPos;
    private TransDialog transDialog;
    private TextView tvCloseBigSize;
    private TextView tvCountDown;
    private TextView tvTitle;
    private UserAvatarDialog userAvatarDialog;
    private String userGrade;
    private UserGuardAvatarDialog userGuardAvatarDialog;
    private String userId;
    private UserSuperAvatarDialog userSuperAvatarDialog;
    private Callback workCallBack = new -$$Lambda$PrepareLiveActivity$7DdPGCGw2pr0WJYRO6YjPqpdgC4(this);
    private Handler workHandler;
    private WsManager wsManager;

    public void onClickDM(boolean z) {
    }

    public void onEndAnimation(GiftAnimModel giftAnimModel) {
    }

    public void onResultError(int i) {
    }

    public void onStartAnimation(GiftAnimModel giftAnimModel) {
    }

    public void onTagListFail() {
    }

    /* Access modifiers changed, original: protected */
    public PrePareLivePresenter createPresenter() {
        return new PrePareLivePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_prepare_live;
    }

    /* Access modifiers changed, original: protected */
    public void initImmersionBar() {
        super.initImmersionBar();
        this.mImmersionBar.a(false).b();
    }

    public void initView(Bundle bundle) {
        this.workHandler = k.a().a(getClass().getName(), this.workCallBack);
        this.mainHandler = new Handler(Looper.getMainLooper());
        getLastSettingFormSp();
        initPrepareView();
        initPermission();
        initPushConfig();
        showPrepareView();
        initLiveView();
        getGiftRes();
        ((PrePareLivePresenter) this.mPresenter).getTagList();
        ((PrePareLivePresenter) this.mPresenter).getPreStartLiveInfo(true);
    }

    private void initPrepareView() {
        this.mFaceUnityControlView = (BeautyControlView) findViewById(R.id.faceunity_control);
        this.mAnchorCoverImg = (ImageView) findViewById(R.id.iv_anchor_cover);
        this.shootContainer = (RelativeLayout) findViewById(R.id.rl_prepare_live_root);
        this.faceRoot = (RelativeLayout) findViewById(R.id.fq_face_root);
        this.rlLiveRoot = (RelativeLayout) findViewById(R.id.rl_start_live_root);
        this.tvTitle = (TextView) findViewById(R.id.tv_live_title);
        this.tvCountDown = (TextView) findViewById(R.id.tv_down_count);
        this.mLivePusherInfoView = (LivePusherInfoView) findViewById(R.id.ll_pusher_info);
        this.anchorLiveEndView = (AnchorLiveEndView) findViewById(R.id.anchor_end_view);
        this.ivTrans = (ImageView) findViewById(R.id.iv_trans);
        this.mPreStartLiveView = (PreStartLiveView) findViewById(R.id.pre_start_live_view);
        this.ivTrans.setImageResource(R.drawable.fq_icon_translate_deflaut);
        this.mPreStartLiveView.setTMirrorDrawableTop(this.lastMirrorOpen ? R.drawable.fq_ic_anchor_mirror_selected : R.drawable.fq_ic_anchor_mirror);
        this.transDialog = TransDialog.newInstance(new -$$Lambda$PrepareLiveActivity$n7uPOMQ7mrXpvVj5kTfvzOYziY4(this));
    }

    public static /* synthetic */ void lambda$initPrepareView$0(PrepareLiveActivity prepareLiveActivity, View view) {
        prepareLiveActivity.isTranOpen = true;
        prepareLiveActivity.ivTrans.setImageResource(R.drawable.fq_icon_translate_selected);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initLiveView() {
        this.mGiftBoxView = (GiftBoxView) findViewById(R.id.gift_box_view);
        this.liveAnimationView = (LiveAnimationView) findViewById(R.id.live_anim_view);
        this.llChatRoot = (LinearLayout) findViewById(R.id.ll_live_chat_msg);
        this.tvCloseBigSize = (TextView) findViewById(R.id.tv_close_big_size);
        this.tvCloseBigSize.setOnClickListener(new -$$Lambda$PrepareLiveActivity$2rUCAkJ3gpdH5ZgE2V3UW4t7uSY(this));
        this.liveBottomBar = (RelativeLayout) findViewById(R.id.rl_live_bottom_bar);
        this.mStickerAddView = (StickerAddView) findViewById(R.id.sticker_add_view);
        this.swipeAnimationController = new v(this);
        this.swipeAnimationController.a(this.rlLiveRoot);
        this.shootContainer.setOnTouchListener(new -$$Lambda$PrepareLiveActivity$t9Su7VpkkPCFXQy-sUf0NEFhUgs(this));
        this.mStickerAddView.initData(getSupportFragmentManager());
        initChatList();
        initInputChat();
        initDownCountAnim();
    }

    public void initListener() {
        this.liveAnimationView.setAnimationCallback(new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                PrepareLiveActivity.this.liveAnimationView.setGiftAnimViewVisibility(4);
                PrepareLiveActivity.this.wsManagerNotifyBigAnim();
            }
        }, new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                PrepareLiveActivity.this.liveAnimationView.setGuardEnterAnimViewVisibility(4);
                PrepareLiveActivity.this.canShowGuardEnterMsg = true;
            }
        }, new OnGiftNotifyCallback() {
            public void onGiftDeleteListener(GiftAnimModel giftAnimModel) {
            }

            public void onGiftNotifyListener() {
                PrepareLiveActivity.this.wsManagerNotifyBigAnim();
            }
        }, new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                PrepareLiveActivity.this.carFullAnimFinish = true;
            }
        });
        this.anchorLiveEndView.setOnLiveEndClosedListener(new -$$Lambda$PrepareLiveActivity$qpYuRzJMXxY04co-sjTwAfsAqks(this));
        this.mLivePusherInfoView.initLivePusherInfoCallback(1, getSupportFragmentManager(), new SimpleLivePusherInfoCallback() {
            public void onClickAnchorAvatarListener(View view) {
                if (!PrepareLiveActivity.this.isStartGetAnchorInfo) {
                    ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).getAnchorInfo(z.a().c());
                    PrepareLiveActivity.this.isStartGetAnchorInfo = true;
                }
            }

            public void onClickUserAvatarListener(UserEntity userEntity) {
                if (!TextUtils.equals(userEntity.getUserId(), z.a().c())) {
                    PrepareLiveActivity.this.showUserAvatarDialog(userEntity.getAvatar(), userEntity.getName(), userEntity.getUserId(), userEntity.getSex(), "", userEntity.getExpGrade(), userEntity.getRole(), userEntity.getGuardType());
                } else if (!PrepareLiveActivity.this.isStartGetAnchorInfo) {
                    ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).getAnchorInfo(userEntity.getUserId());
                    PrepareLiveActivity.this.isStartGetAnchorInfo = true;
                }
            }
        });
        this.mLivePusherInfoView.setGiftAnimListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                PrepareLiveActivity.this.canShowGiftNotice = false;
            }

            public void onAnimationEnd(Animator animator) {
                PrepareLiveActivity.this.canShowGiftNotice = true;
            }

            public void onAnimationCancel(Animator animator) {
                PrepareLiveActivity.this.canShowGiftNotice = true;
            }
        });
        this.mLivePusherInfoView.setSysNoticeAnimListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                PrepareLiveActivity.this.canShowSysNotice = false;
            }

            public void onAnimationEnd(Animator animator) {
                PrepareLiveActivity.this.canShowSysNotice = true;
            }

            public void onAnimationCancel(Animator animator) {
                PrepareLiveActivity.this.canShowSysNotice = true;
            }
        });
        this.mPreStartLiveView.setOnPreStartLiveCallback(new OnPreStartLiveCallback() {
            public void onClickClosedListener() {
                PrepareLiveActivity.this.onBackPressed();
            }

            public void onClickCameraListener() {
                PrepareLiveActivity.this.clickCamera();
            }

            public void onClickBeautyListener() {
                PrepareLiveActivity.this.clickBeauty();
            }

            public void onClickMirrorListener() {
                PrepareLiveActivity.this.clickMirror();
            }

            public void onClickStartLiveListener(String str, String str2) {
                if (PrepareLiveActivity.this.isBtnStartClick) {
                    PrepareLiveActivity.this.showToast(R.string.fq_dont_start_live_repeat);
                    return;
                }
                ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).getPreStartLiveInfo(false);
                PrepareLiveActivity.this.isBtnStartClick = true;
            }
        });
        this.ivTrans.setOnClickListener(new -$$Lambda$PrepareLiveActivity$_Ev3fZAW16EdWgAaNQctC0G_zDA(this));
        r.a().a(findViewById(R.id.iv_input), 800, new -$$Lambda$PrepareLiveActivity$k0nzcl1UAa3QNEEcNfOuk6c8Qh8(this));
        r.a().a(findViewById(R.id.iv_sw_camera), 800, new -$$Lambda$PrepareLiveActivity$NCqzl-vokC5IriNLVwZxBwX2-Tw(this));
        r.a().a(findViewById(R.id.iv_setting), 800, new -$$Lambda$PrepareLiveActivity$dNNseC2pklXkRpGfwwqYNxvjxGs(this));
        r.a().a(findViewById(R.id.iv_back), 800, new -$$Lambda$PrepareLiveActivity$8gof_9Re7HTQXrr-DeG2FU39afQ(this));
        r.a().a(findViewById(R.id.iv_turnover), 800, new -$$Lambda$PrepareLiveActivity$A7bHv-CwOOSw3_YTEUeE_CQhF-g(this));
        this.mStickerAddView.setOnAddStickerCallback(new -$$Lambda$PrepareLiveActivity$yu4R1ArUdTF_Bqg7OTwQbqSgcTw(this));
        this.mGiftBoxView.setOnSendGiftBoxMsgListener(new OnSendGiftBoxMsgListener() {
            public void onSendGiftBoxMsg(GiftBoxEntity giftBoxEntity) {
                PrepareLiveActivity.this.wsManager.sendGrabGiftBoxMessage(MessageHelper.convertToGrabGiftBoxMsg(giftBoxEntity.giftBoxUniqueCode));
            }

            public void onShowDialog(GiftBoxEntity giftBoxEntity) {
                GiftBoxPresenterDialog.newInstance(giftBoxEntity.presenterAvatar, giftBoxEntity.presenterName).show(PrepareLiveActivity.this.getSupportFragmentManager());
            }
        });
    }

    public static /* synthetic */ void lambda$initListener$3(PrepareLiveActivity prepareLiveActivity) {
        prepareLiveActivity.isLiving = false;
        prepareLiveActivity.onBackPressed();
    }

    public static /* synthetic */ void lambda$initListener$4(PrepareLiveActivity prepareLiveActivity, View view) {
        if (prepareLiveActivity.isTranOpen) {
            prepareLiveActivity.isTranOpen = false;
            prepareLiveActivity.ivTrans.setImageResource(R.drawable.fq_icon_translate_deflaut);
            prepareLiveActivity.showToast(R.string.fq_close_tran);
        } else if (prepareLiveActivity.transDialog != null) {
            prepareLiveActivity.transDialog.show(prepareLiveActivity.getSupportFragmentManager());
        }
    }

    public static /* synthetic */ void lambda$initListener$5(PrepareLiveActivity prepareLiveActivity, Object obj) {
        if (!com.tomatolive.library.utils.b.f()) {
            return;
        }
        if (prepareLiveActivity.isConnectingChatService) {
            prepareLiveActivity.showToast(R.string.fq_start_connect_socket);
        } else if (prepareLiveActivity.reConnectCountOver) {
            if (prepareLiveActivity.wsManager != null) {
                prepareLiveActivity.wsManager.resetCount();
                prepareLiveActivity.wsManager.reconnect();
            }
        } else {
            prepareLiveActivity.showInputMsgDialog();
            double d = (double) prepareLiveActivity.dm.heightPixels;
            Double.isNaN(d);
            prepareLiveActivity.inputChangeStatus((int) (d * 0.42d), 4);
        }
    }

    public static /* synthetic */ void lambda$initListener$7(PrepareLiveActivity prepareLiveActivity, Object obj) {
        if (prepareLiveActivity.bottomMoreDialog != null && !prepareLiveActivity.bottomMoreDialog.isShowing()) {
            prepareLiveActivity.bottomMoreDialog.show();
        }
    }

    public static /* synthetic */ void lambda$initListener$11(PrepareLiveActivity prepareLiveActivity) {
        prepareLiveActivity.pushManager.c();
        prepareLiveActivity.handlerMainPost(new -$$Lambda$PrepareLiveActivity$-WGi2fVC6yerfMY3xRZD4bvtrKA(prepareLiveActivity));
    }

    public void onLiveAdNoticeSuccess(BannerEntity bannerEntity) {
        if (!TextUtils.isEmpty(bannerEntity.content)) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgType(10);
            chatEntity.setMsgText(bannerEntity.content);
            this.liveChatMsgView.addChatMsg(chatEntity);
        }
    }

    public void onStartLiveSuccess(LiveEntity liveEntity) {
        if (liveEntity == null) {
            showToast(R.string.fq_start_live_error);
            return;
        }
        c.a().d(new StartLiveEvent());
        this.userGrade = liveEntity.audienceExpGrade;
        this.liveCount = p.a(liveEntity.liveCount);
        this.liveInfoItem = liveEntity;
        this.tvTitle.setText(getString(R.string.fq_text_live_title, new Object[]{liveEntity.topic}));
        this.mPushUrl = liveEntity.pushStreamUrl;
        this.liveId = liveEntity.liveId;
        this.userId = liveEntity.userId;
        this.socketUrl = com.tomatolive.library.utils.b.a(this.liveInfoItem.wsServerAddress, this.liveId, this.userId, "1", liveEntity.k);
        this.streamName = liveEntity.streamName;
        liveEntity.anchorId = liveEntity.userId;
        startStream();
        showLiveView();
        ((PrePareLivePresenter) this.mPresenter).getLiveAdNoticeList();
        initSocket();
        startPlayAnim();
        ((PrePareLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(this.liveId, "enter");
        ((PrePareLivePresenter) this.mPresenter).getGiftBoxList(this.liveId);
    }

    public void onStartLiveFail() {
        this.mLivePusherInfoView.setVisibility(4);
        showToast(R.string.fq_start_live_error);
    }

    public void onTagListSuccess(List<LabelEntity> list) {
        this.mPreStartLiveView.onTagListSuccess(list);
    }

    /* JADX WARNING: Missing block: B:16:0x0035, code skipped:
            if (r4.equals("-1") != false) goto L_0x004d;
     */
    public void onPreStartLiveInfoSuccess(com.tomatolive.library.model.CoverEntity r4, boolean r5) {
        /*
        r3 = this;
        r0 = 0;
        r3.isBtnStartClick = r0;
        if (r4 != 0) goto L_0x000b;
    L_0x0005:
        r4 = com.tomatolive.library.R.string.fq_cover_error;
        r3.showToast(r4);
        return;
    L_0x000b:
        r1 = r3.mPreStartLiveView;
        r2 = r4.liveCoverUrl;
        r1.updatePreStartLiveInfo(r2);
        if (r5 == 0) goto L_0x0015;
    L_0x0014:
        return;
    L_0x0015:
        r5 = r3.isLiving;
        if (r5 == 0) goto L_0x001a;
    L_0x0019:
        return;
    L_0x001a:
        r4 = r4.isChecked;
        r5 = -1;
        r1 = r4.hashCode();
        switch(r1) {
            case 48: goto L_0x0042;
            case 49: goto L_0x0038;
            case 1444: goto L_0x002f;
            case 1445: goto L_0x0025;
            default: goto L_0x0024;
        };
    L_0x0024:
        goto L_0x004c;
    L_0x0025:
        r0 = "-2";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x004c;
    L_0x002d:
        r0 = 1;
        goto L_0x004d;
    L_0x002f:
        r1 = "-1";
        r4 = r4.equals(r1);
        if (r4 == 0) goto L_0x004c;
    L_0x0037:
        goto L_0x004d;
    L_0x0038:
        r0 = "1";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x004c;
    L_0x0040:
        r0 = 3;
        goto L_0x004d;
    L_0x0042:
        r0 = "0";
        r4 = r4.equals(r0);
        if (r4 == 0) goto L_0x004c;
    L_0x004a:
        r0 = 2;
        goto L_0x004d;
    L_0x004c:
        r0 = -1;
    L_0x004d:
        switch(r0) {
            case 0: goto L_0x0075;
            case 1: goto L_0x0065;
            case 2: goto L_0x0055;
            case 3: goto L_0x0051;
            default: goto L_0x0050;
        };
    L_0x0050:
        goto L_0x0084;
    L_0x0051:
        r3.startLive();
        goto L_0x0084;
    L_0x0055:
        r4 = com.tomatolive.library.R.string.fq_cover_wait_check;
        r4 = r3.getString(r4);
        r5 = com.tomatolive.library.R.string.fq_cover_upload_orno;
        r5 = r3.getString(r5);
        r3.showCoverDialog(r4, r5);
        goto L_0x0084;
    L_0x0065:
        r4 = com.tomatolive.library.R.string.fq_cover_no_upload;
        r4 = r3.getString(r4);
        r5 = com.tomatolive.library.R.string.fq_cover_upload_orno;
        r5 = r3.getString(r5);
        r3.showCoverDialog(r4, r5);
        goto L_0x0084;
    L_0x0075:
        r4 = com.tomatolive.library.R.string.fq_cover_nopass;
        r4 = r3.getString(r4);
        r5 = com.tomatolive.library.R.string.fq_cover_retry_upload;
        r5 = r3.getString(r5);
        r3.showCoverDialog(r4, r5);
    L_0x0084:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.live.PrepareLiveActivity.onPreStartLiveInfoSuccess(com.tomatolive.library.model.CoverEntity, boolean):void");
    }

    public void onPreStartLiveInfoFail() {
        this.isBtnStartClick = false;
        showToast(R.string.fq_net_poor_live);
    }

    public void onAnchorInfoSuccess(AnchorEntity anchorEntity) {
        this.anchorAvatarDialog = AnchorAvatarDialog.newInstance(anchorEntity, 1, new -$$Lambda$PrepareLiveActivity$B12Ud_7r2HuxGJ59MZ8GKnGNqbQ(this));
        this.anchorAvatarDialog.show(getSupportFragmentManager());
        this.isStartGetAnchorInfo = false;
    }

    public void onLiveEndSuccess(int i, LiveEntity liveEntity) {
        this.anchorLiveEndView.initData(i, liveEntity);
        if (i == 2) {
            WarnDialog.newInstance(WarnDialog.BAN_TIP).show(getSupportFragmentManager());
        }
        this.isLiving = false;
    }

    public void onLiveEndFail() {
        ((PrePareLivePresenter) this.mPresenter).getLiveEndInfo(this.liveId, this.streamName, 1);
    }

    public void onAttentionSuccess() {
        c.a().d(new AttentionEvent());
    }

    public void onGiftListSuccess(List<GiftDownloadItemEntity> list) {
        if (this.workHandler != null) {
            this.workHandler.post(new -$$Lambda$PrepareLiveActivity$HQSNTFOnLqhgNMNWIbF4c16uPYs(list));
        }
        this.isStartGiftUpdate = false;
    }

    public void onGiftListFail() {
        this.isStartGiftUpdate = false;
        showToast(R.string.fq_gift_fail);
    }

    public void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity) {
        if (onLineUsersEntity != null && onLineUsersEntity.getUserEntityList() != null) {
            this.mLivePusherInfoView.replaceData(onLineUsersEntity.getUserEntityList());
        }
    }

    public void onLiveInitInfoSuccess(LiveEntity liveEntity) {
        if (liveEntity != null) {
            dealInit(liveEntity);
        }
    }

    public void onAnchorInfoFail(int i, String str) {
        this.isStartGetAnchorInfo = false;
    }

    @SuppressLint({"CheckResult"})
    private void initPermission() {
        new RxPermissions(this).requestEach(new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO"}).compose(bindToLifecycle()).subscribe(new -$$Lambda$PrepareLiveActivity$_vDMalt0lENqJvitxXYE9sJRB1I(this));
    }

    public static /* synthetic */ void lambda$initPermission$14(PrepareLiveActivity prepareLiveActivity, Permission permission) throws Exception {
        if (permission.name.equals("android.permission.CAMERA")) {
            if (permission.granted) {
                prepareLiveActivity.isCameraPermissions = true;
            } else {
                PermissionDialog.newInstance(PermissionDialog.CAMERA_TIP).show(prepareLiveActivity.getSupportFragmentManager());
                prepareLiveActivity.isCameraPermissions = false;
            }
        }
        if (permission.name.equals("android.permission.RECORD_AUDIO")) {
            prepareLiveActivity.isMicPermissions = permission.granted;
        }
    }

    private void showPrepareView() {
        this.rlLiveRoot.setVisibility(4);
        this.tvCountDown.setVisibility(4);
        this.mPreStartLiveView.setVisibility(0);
    }

    private void showCoverDialog(String str, String str2) {
        CoverSettingDialog.newInstance(str, str2, new -$$Lambda$PrepareLiveActivity$BUauzO5XyrlXY0oUkOQy04Fbt80(this), new -$$Lambda$PrepareLiveActivity$90BcJac4XdMdt9BDc-GYP4sD-yA(this)).show(getSupportFragmentManager());
    }

    private void goToUploadCover() {
        this.mPreStartLiveView.goToUploadCover();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 69) {
            switch (i) {
                case 908:
                    if (i2 == -1) {
                        this.mPreStartLiveView.onCameraActivityResult();
                        return;
                    }
                    return;
                case 909:
                    if (i2 == -1) {
                        this.mPreStartLiveView.onAlbumActivityResult(intent);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (i2 == -1) {
            this.mPreStartLiveView.picCompression();
        } else if (i2 == 96) {
            showToast(R.string.fq_pic_cut_fail);
        }
    }

    private void getGiftRes() {
        List localDownloadList = GiftDownLoadManager.getInstance().getLocalDownloadList();
        if ((localDownloadList == null || localDownloadList.size() == 0) && !this.isStartGiftUpdate) {
            this.isStartGiftUpdate = true;
            ((PrePareLivePresenter) this.mPresenter).getGiftList(-1);
        }
    }

    private void getLastSettingFormSp() {
        this.dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.dm);
        this.lastBeautyProgress = com.blankj.utilcode.util.k.a().c("BIGEYE_KEY", -1);
        this.lastRuddyProgress = com.blankj.utilcode.util.k.a().c("SHORTENFACE_KEY", -1);
        this.lastWhiteProgress = com.blankj.utilcode.util.k.a().c("WHITE_KEY", -1);
        this.lastMirrorOpen = com.blankj.utilcode.util.k.a().b("LIVE_MIRROR_KEY", false);
    }

    private void stopStream() {
        this.pushManager.g();
    }

    private void startStream() {
        this.isLiving = true;
        this.pushManager.a(this.mPushUrl);
    }

    private boolean isOpenFuRender() {
        return com.blankj.utilcode.util.k.a().b("isOpenBeauty", false);
    }

    private void initPushConfig() {
        this.pushManager = new sg(this);
        this.pushManager.a(this.shootContainer);
        enableBeautyFilter();
        this.pushManager.setOnPushListener(new a() {
            public void onPushSuccess() {
                PrepareLiveActivity.this.isPushSuc = true;
                PrepareLiveActivity.this.reCount = 3;
            }

            public void onMicError() {
                ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).setEnterOrLeaveLiveRoomMsg(PrepareLiveActivity.this.liveId, ConnectSocketParams.MESSAGE_LEAVE_TYPE);
                PermissionDialog.newInstance(PermissionDialog.MIC_TIP).show(PrepareLiveActivity.this.getSupportFragmentManager());
                if (o.a() && PrepareLiveActivity.this.wsManager != null) {
                    PrepareLiveActivity.this.wsManager.sendLeaveMessage(new SendMessageEntity());
                    m.b("onMicError==> sendLeaveMessage");
                }
            }

            public void onCameraError() {
                PrepareLiveActivity.this.isCameraOpenFail = true;
                PermissionDialog.newInstance(PermissionDialog.CAMERA_TIP).show(PrepareLiveActivity.this.getSupportFragmentManager());
                if (o.a() && PrepareLiveActivity.this.wsManager != null) {
                    PrepareLiveActivity.this.wsManager.sendLeaveMessage(new SendMessageEntity());
                    m.b("onCameraError==> sendLeaveMessage");
                }
            }

            public void onRePush() {
                if (PrepareLiveActivity.this.reCount > 0) {
                    PrepareLiveActivity.this.isPushSuc = false;
                    if (PrepareLiveActivity.this.pushManager != null) {
                        PrepareLiveActivity.this.pushManager.h();
                    }
                } else if (PrepareLiveActivity.this.reCount == 0) {
                    q.a().c(PrepareLiveActivity.this.mContext, 5, TimeUnit.SECONDS, new -$$Lambda$PrepareLiveActivity$10$RMI24EfT5ETLuFnmX2CV9MdG5vg(this));
                }
                PrepareLiveActivity.this.reCount = PrepareLiveActivity.this.reCount - 1;
            }

            public static /* synthetic */ void lambda$onRePush$0(AnonymousClass10 anonymousClass10, long j) {
                if (!PrepareLiveActivity.this.isPushSuc && o.a()) {
                    ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).uploadErrorReport(PrepareLiveActivity.this.liveId);
                    PrepareLiveActivity.this.finishLive();
                    m.b("uploadErrorReport。。。。");
                }
            }
        });
        showWaterLogo();
    }

    @SuppressLint({"CheckResult"})
    private void showWaterLogo() {
        Bitmap a = this.pushManager.a();
        if (a != null) {
            this.mAnchorCoverImg.setImageBitmap(a);
            this.mAnchorCoverImg.setVisibility(0);
        }
    }

    private void enableBeautyFilter() {
        if (isOpenFuRender()) {
            this.pushManager.a(this.mFaceUnityControlView);
            this.faceRoot.setOnClickListener(new -$$Lambda$PrepareLiveActivity$Baw_Lzz-utuYBxn0vHkjCtsXGNc(this));
            return;
        }
        final TXLivePusher d = this.pushManager.d();
        this.beautyDialog = BeautyDialog.newInstance(this.lastRuddyProgress, this.lastBeautyProgress, this.lastWhiteProgress);
        this.beautyDialog.setBeautyParamsListener(new OnBeautyParamsChangeListener() {
            public void onBeautyParamsChange(BeautyParams beautyParams, int i) {
                if (d != null) {
                    d.setBeautyFilter(0, beautyParams.beauty, beautyParams.whiten, beautyParams.ruddy);
                    if (i == 3) {
                        com.blankj.utilcode.util.k.a().b("BIGEYE_KEY", beautyParams.beauty);
                    } else if (i == 2) {
                        com.blankj.utilcode.util.k.a().b("SHORTENFACE_KEY", beautyParams.ruddy);
                    } else if (i == 1) {
                        com.blankj.utilcode.util.k.a().b("WHITE_KEY", beautyParams.whiten);
                    }
                }
            }

            public void onDismiss() {
                if (!PrepareLiveActivity.this.isLiving) {
                    PrepareLiveActivity.this.mPreStartLiveView.setVisibility(0);
                }
            }
        });
    }

    public static /* synthetic */ void lambda$enableBeautyFilter$17(PrepareLiveActivity prepareLiveActivity, View view) {
        prepareLiveActivity.faceRoot.setVisibility(4);
        if (!prepareLiveActivity.isLiving) {
            prepareLiveActivity.mPreStartLiveView.setVisibility(0);
        }
    }

    private void setFlash(BaseQuickAdapter<MenuEntity, BaseViewHolder> baseQuickAdapter, MenuEntity menuEntity, int i) {
        if (this.isFontCamera) {
            menuEntity.isSelected ^= 1;
            baseQuickAdapter.setData(i, menuEntity);
            showToast(R.string.fq_dont_use_flash);
        } else {
            this.pushManager.b(menuEntity.isSelected);
        }
        this.tempFlashMenuEntity = menuEntity;
        this.tempAdapter = baseQuickAdapter;
        this.tempPos = i;
    }

    private void setMic(MenuEntity menuEntity) {
        showToast(menuEntity.isSelected ? R.string.fq_close_mic : R.string.fq_open_mic);
        this.pushManager.c(menuEntity.isSelected);
    }

    private void setMirror() {
        if (this.isFontCamera) {
            this.pushManager.a(this.lastMirrorOpen ^ 1);
            this.lastMirrorOpen ^= 1;
            if (this.lastMirrorOpen) {
                showToast(R.string.fq_the_same_picture);
                this.mPreStartLiveView.setTMirrorDrawableTop(R.drawable.fq_ic_anchor_mirror_selected);
            } else {
                showToast(R.string.fq_the_diff_picture);
                this.mPreStartLiveView.setTMirrorDrawableTop(R.drawable.fq_ic_anchor_mirror);
            }
            com.blankj.utilcode.util.k.a().a("LIVE_MIRROR_KEY", this.lastMirrorOpen);
            return;
        }
        showToast(R.string.fq_back_camera_no_mirror);
    }

    private void switchCamera() {
        this.pushManager.f();
        this.isFontCamera ^= 1;
        if (this.tempFlashMenuEntity != null && this.tempAdapter != null) {
            this.tempFlashMenuEntity.isSelected = false;
            this.tempAdapter.setData(this.tempPos, this.tempFlashMenuEntity);
        }
    }

    private void clickBeautyDialog() {
        this.mPreStartLiveView.setVisibility(4);
        if (isOpenFuRender()) {
            this.faceRoot.setVisibility(0);
        } else if (this.beautyDialog == null) {
        } else {
            if (this.beautyDialog.isAdded()) {
                this.beautyDialog.dismiss();
            } else {
                this.beautyDialog.show(getFragmentManager(), "beautyDialog");
            }
        }
    }

    private void initChatList() {
        this.liveChatMsgView = (LiveChatMsgView) findViewById(R.id.live_chat_msg_view);
        this.liveChatMsgView.setOnChatMsgItemClickListener(new -$$Lambda$PrepareLiveActivity$N9L1NbXshh_YTk-u7h4vI9Eesrs(this));
    }

    public static /* synthetic */ void lambda$initChatList$18(PrepareLiveActivity prepareLiveActivity, ChatEntity chatEntity) {
        if (chatEntity.getMsgType() == 2) {
            if (prepareLiveActivity.wsManager != null) {
                prepareLiveActivity.wsManager.resetCount();
                prepareLiveActivity.wsManager.reconnect();
            }
            return;
        }
        if (com.tomatolive.library.utils.b.a(chatEntity)) {
            if (!TextUtils.equals(chatEntity.getUid(), z.a().c())) {
                prepareLiveActivity.showUserAvatarDialog(chatEntity.getUserAvatar(), chatEntity.getMsgSendName(), chatEntity.getUid(), chatEntity.getSex(), "", chatEntity.getExpGrade(), chatEntity.getRole(), chatEntity.getGuardType());
            } else if (!prepareLiveActivity.isStartGetAnchorInfo) {
                ((PrePareLivePresenter) prepareLiveActivity.mPresenter).getAnchorInfo(chatEntity.getUid());
                prepareLiveActivity.isStartGetAnchorInfo = true;
            }
        }
    }

    private void initInputChat() {
        this.mInputTextMsgDialog = new InputTextMsgDialog(this.mContext, this);
        this.mInputTextMsgDialog.hideDanmu();
        this.mInputTextMsgDialog.setOnDismissListener(new -$$Lambda$PrepareLiveActivity$eoaQ7NMh66bKi2qhnFVDAVFiA80(this));
    }

    private void showInputTitleDialog() {
        UpdateTitleDialog.newInstance(new -$$Lambda$PrepareLiveActivity$ef0qXoyG-d41_IIdgWoITCvonmM(this)).show(getSupportFragmentManager());
    }

    public static /* synthetic */ void lambda$showInputTitleDialog$20(PrepareLiveActivity prepareLiveActivity, String str) {
        if (e.a(str)) {
            prepareLiveActivity.showToast(R.string.fq_no_emoji);
            return;
        }
        prepareLiveActivity.tvTitle.setText(prepareLiveActivity.getString(R.string.fq_text_live_title, new Object[]{EmojiParser.a(str)}));
        prepareLiveActivity.tvTitle.setVisibility(0);
        if (prepareLiveActivity.wsManager != null) {
            prepareLiveActivity.wsManager.sendNotifyMessage(MessageHelper.convertToNotifyMsg(prepareLiveActivity.liveId, str));
        }
    }

    private void showInputMsgDialog() {
        if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.show();
        }
    }

    private void inputChangeStatus(int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) this.llChatRoot.getLayoutParams();
        layoutParams.bottomMargin = i;
        this.llChatRoot.setLayoutParams(layoutParams);
        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        livePusherInfoView.setTranslationY(i2 == 0 ? CropImageView.DEFAULT_ASPECT_RATIO : -(((float) this.dm.heightPixels) / 4.0f));
        this.liveAnimationView.setTranslationY(i2 == 0 ? CropImageView.DEFAULT_ASPECT_RATIO : -(((float) this.dm.heightPixels) / 3.0f));
        GiftBoxView giftBoxView = this.mGiftBoxView;
        if (i2 != 0) {
            f = -(((float) this.dm.heightPixels) / 3.0f);
        }
        giftBoxView.setTranslationY(f);
    }

    public void onTextSend(String str) {
        if (isSocketClose()) {
            showToast(R.string.fq_text_network_error_chat);
        } else if (this.isBanGroup) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgSendName(z.a().f());
            chatEntity.setMsgText(str);
            chatEntity.setMsgType(3);
            chatEntity.setSex(z.a().h());
            chatEntity.setRole("1");
            chatEntity.setUid(z.a().c());
            chatEntity.setUserAvatar(z.a().g());
            chatEntity.setExpGrade(com.tomatolive.library.utils.b.h(this.userGrade));
            this.liveChatMsgView.addChatMsg(chatEntity);
        } else {
            if (this.wsManager != null) {
                this.wsManager.sendChatMessage(MessageHelper.convertToChatMsg("1", this.liveId, str, this.userGrade, false, "0"));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        this.pushManager.a(this.isCameraPermissions, this.isLiving);
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.onResume();
        }
        if (this.isLiving) {
            if (!this.isNoNetEvent) {
                cancelPausedTimer();
            }
            this.isPushInBackground = false;
            if (this.isWarn) {
                if (this.endWarnDialog != null && this.endWarnDialog.isAdded()) {
                    this.endWarnDialog.dismiss();
                    this.endWarnDialog = null;
                }
                this.startWarnDialog = WarnDialog.newInstance(WarnDialog.WARN_TIP);
                this.startWarnDialog.show(getSupportFragmentManager());
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        this.pushManager.e();
        if (this.isLiving) {
            this.isPushInBackground = true;
            this.isNoNetEvent = false;
            startPausedTimer();
        }
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.onPause();
        }
    }

    private void dealInit(LiveEntity liveEntity) {
        this.isSuperBan = liveEntity.isBanBySuperManager();
        if (!this.isSuperBan) {
            this.mInputTextMsgDialog.cancelBandPost();
        } else if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.setBandPostBySuperManager();
        }
        this.speakLevel = liveEntity.speakLevel;
        this.defaultSpeakLevel = liveEntity.speakLevel;
        this.userGrade = liveEntity.audienceExpGrade;
        this.entryNoticeLevelThreshold = com.tomatolive.library.utils.b.e(liveEntity);
        this.giftTrumpetPlayPeriod = com.tomatolive.library.utils.b.c(liveEntity);
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.setVisibility(0);
            this.mLivePusherInfoView.initData(liveEntity);
            this.mLivePusherInfoView.setLiveRankConfig(liveEntity.liveRankConfig);
            this.mLivePusherInfoView.initAnchorGuard(liveEntity);
        }
        if (!(this.shieldedList == null || liveEntity.shieldUserList == null)) {
            this.shieldedList.clear();
            this.shieldedList.addAll(liveEntity.shieldUserList);
        }
        if (!(this.banedPostList == null || liveEntity.banPostList == null)) {
            this.banedPostList.clear();
            this.banedPostList.addAll(liveEntity.banPostList);
        }
        this.isBanPostAll = TextUtils.equals("1", liveEntity.banPostAllStatus);
        this.isBanGroup = liveEntity.isBanGroup();
        io.reactivex.k.interval(0, com.tomatolive.library.utils.b.b(liveEntity), TimeUnit.SECONDS).observeOn(xl.b()).subscribeOn(xl.b()).compose(bindToLifecycle()).subscribe(new sh<Long>() {
            public void accept(Long l) {
                ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).getCurrentOnlineUserList(PrepareLiveActivity.this.liveId);
            }
        });
        io.reactivex.k.interval(0, 5, TimeUnit.SECONDS).subscribeOn(xl.b()).observeOn(xl.b()).compose(bindToLifecycle()).subscribe(new sh<Long>() {
            public void accept(Long l) {
                ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).getLivePopularity(PrepareLiveActivity.this.liveId);
            }
        });
    }

    private void showGiftListRecord() {
        GiftRecordDialog.newInstance(this.liveCount, new -$$Lambda$PrepareLiveActivity$chfL_J4KZiZzC6Sfrp3pTYo0AN4(this)).show(getSupportFragmentManager());
    }

    private void initBottomMoreDialog() {
        this.bottomMoreDialog = BottomDialogUtils.getLiveBottomDialog(this.mContext, this.lastMirrorOpen, new -$$Lambda$PrepareLiveActivity$HvzTXtuYtayDYcdlCdNUlMvn0BU(this));
    }

    public static /* synthetic */ void lambda$initBottomMoreDialog$22(PrepareLiveActivity prepareLiveActivity, BaseQuickAdapter baseQuickAdapter, MenuEntity menuEntity, int i) {
        switch (i) {
            case 0:
                prepareLiveActivity.clickBeautyDialog();
                return;
            case 1:
                prepareLiveActivity.setMic(menuEntity);
                return;
            case 2:
                prepareLiveActivity.setFlash(baseQuickAdapter, menuEntity, i);
                return;
            case 3:
                prepareLiveActivity.setBigSize(true);
                return;
            case 4:
                if (!prepareLiveActivity.isFontCamera) {
                    menuEntity.isSelected = 1 ^ menuEntity.isSelected;
                    baseQuickAdapter.setData(i, menuEntity);
                }
                prepareLiveActivity.setMirror();
                return;
            case 5:
                prepareLiveActivity.showSpeakDialog();
                return;
            case 6:
                prepareLiveActivity.showInputTitleDialog();
                return;
            case 7:
                prepareLiveActivity.mStickerAddView.setVisibility(0);
                prepareLiveActivity.mAnchorCoverImg.setVisibility(4);
                return;
            default:
                return;
        }
    }

    private void setBigSize(boolean z) {
        showBigSizeTip(z);
        LayoutParams layoutParams = (LayoutParams) this.llChatRoot.getLayoutParams();
        layoutParams.height = z ? this.dm.heightPixels / 2 : com.blankj.utilcode.util.m.a(220.0f);
        this.llChatRoot.setLayoutParams(layoutParams);
        this.liveChatMsgView.setChatMsgBigSize(this.dm, z);
    }

    private void showLiveView() {
        initBottomMoreDialog();
        this.rlLiveRoot.setVisibility(0);
        this.tvCountDown.setVisibility(0);
        this.mPreStartLiveView.setVisibility(4);
    }

    private void startLive() {
        if (this.isCameraOpenFail || !this.isCameraPermissions) {
            PermissionDialog.newInstance(PermissionDialog.CAMERA_TIP).show(getSupportFragmentManager());
        } else if (this.isMicPermissions) {
            String liveTitle = this.mPreStartLiveView.getLiveTitle();
            String liveTag = this.mPreStartLiveView.getLiveTag();
            ((PrePareLivePresenter) this.mPresenter).startLive(EmojiParser.a(liveTitle), "1", liveTag, "");
        } else {
            PermissionDialog.newInstance(PermissionDialog.MIC_TIP).show(getSupportFragmentManager());
        }
    }

    private void showSpeakDialog() {
        BottomDialogUtils.showBottomSpeakSettingDialog(this, this.cdTime, this.speakLevel, this.isBanPostAll, new -$$Lambda$PrepareLiveActivity$CJCIeLFsqBsYmOC29aowSbIfJac(this));
    }

    public static /* synthetic */ void lambda$showSpeakDialog$23(PrepareLiveActivity prepareLiveActivity, boolean z, String str, String str2) {
        if (!TextUtils.equals(prepareLiveActivity.speakLevel, str2)) {
            if (p.a(prepareLiveActivity.defaultSpeakLevel) > p.a(str2)) {
                prepareLiveActivity.showToast(R.string.fq_speak_level_too_low);
            } else {
                prepareLiveActivity.speakLevel = str2;
                if (prepareLiveActivity.wsManager != null) {
                    prepareLiveActivity.wsManager.sendSpeakLevelMessage(MessageHelper.convertToSpeakLevelMsg(prepareLiveActivity.liveId, str2));
                }
            }
        }
        if (prepareLiveActivity.isBanPostAll != z) {
            prepareLiveActivity.isBanPostAll = z;
            if (prepareLiveActivity.wsManager != null) {
                prepareLiveActivity.wsManager.sendBannedAllMessage(MessageHelper.convertToAllBanMsg(prepareLiveActivity.liveId, z));
            }
        }
        if (!TextUtils.equals(prepareLiveActivity.cdTime, str)) {
            prepareLiveActivity.cdTime = str;
            if (prepareLiveActivity.wsManager != null) {
                prepareLiveActivity.wsManager.sendPostIntervalMessage(MessageHelper.convertToPostInterval(prepareLiveActivity.liveId, str));
            }
        }
    }

    private void clickMirror() {
        if (this.isCameraOpenFail) {
            PermissionDialog.newInstance(PermissionDialog.CAMERA_TIP).show(getSupportFragmentManager());
        } else {
            setMirror();
        }
    }

    private void clickBeauty() {
        if (this.isCameraOpenFail) {
            PermissionDialog.newInstance(PermissionDialog.CAMERA_TIP).show(getSupportFragmentManager());
        } else {
            clickBeautyDialog();
        }
    }

    private void clickCamera() {
        if (this.isCameraOpenFail) {
            PermissionDialog.newInstance(PermissionDialog.CAMERA_TIP).show(getSupportFragmentManager());
        } else {
            switchCamera();
        }
    }

    private void showBigSizeTip(boolean z) {
        int i = 0;
        this.liveBottomBar.setVisibility(z ? 4 : 0);
        TextView textView = this.tvCloseBigSize;
        if (!z) {
            i = 4;
        }
        textView.setVisibility(i);
    }

    private void initDownCountAnim() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.tvCountDown, "scaleX", new float[]{1.0f, 5.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.tvCountDown, "scaleY", new float[]{1.0f, 5.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.tvCountDown, "alpha", new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        this.countDownAnimSet = new AnimatorSet();
        this.countDownAnimSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        this.countDownAnimSet.setDuration(800);
    }

    private void startPlayAnim() {
        this.tvCountDown.setVisibility(0);
        io.reactivex.k.interval(1, TimeUnit.SECONDS).take(4).map(-$$Lambda$PrepareLiveActivity$V5OoepHGwdKtxFgT4smpRPnB2UM.INSTANCE).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new sh<Long>() {
            public void accept(Long l) {
                PrepareLiveActivity.this.tvCountDown.setText(String.valueOf(l));
                if (PrepareLiveActivity.this.countDownAnimSet != null) {
                    PrepareLiveActivity.this.countDownAnimSet.start();
                }
            }

            public void onComplete() {
                PrepareLiveActivity.this.tvCountDown.setVisibility(8);
            }
        });
    }

    private boolean isSocketClose() {
        return this.isSocketClose || this.isSocketError;
    }

    private void initSocket() {
        addSocketTipMsg(R.string.fq_start_connect_socket);
        this.wsManager = WsManager.getInstance();
        this.wsManager.init(this, this.socketUrl, (long) this.heartTime);
        this.wsManager.setOnWebSocketListener(new OnWebSocketStatusListener() {
            public void onOpen(boolean z) {
                PrepareLiveActivity.this.isReConnectStatus = z;
                PrepareLiveActivity.this.isConnectingChatService = false;
                PrepareLiveActivity.this.reConnectCountOver = false;
                if (z) {
                    PrepareLiveActivity.this.addSocketTipMsg(R.string.fq_reconnect_suc);
                } else {
                    PrepareLiveActivity.this.addSocketTipMsg(R.string.fq_connect_suc);
                }
                PrepareLiveActivity.this.isSocketClose = false;
                PrepareLiveActivity.this.isSocketError = false;
                if (!PrepareLiveActivity.this.isPushSuc && PrepareLiveActivity.this.reCount <= 0) {
                    m.b("socket重启成功，开始重启推流。。");
                    PrepareLiveActivity.this.pushManager.h();
                }
            }

            public void onClose() {
                PrepareLiveActivity.this.isSocketClose = true;
            }

            public void onError(boolean z, String str) {
                if (str == null || !str.contains("HTTP/1.1 403")) {
                    PrepareLiveActivity.this.addSocketTipMsg(R.string.fq_connect_fail);
                    PrepareLiveActivity.this.isSocketError = true;
                    return;
                }
                PrepareLiveActivity.this.startTokenDialogService();
                PrepareLiveActivity.this.isLiving = false;
                PrepareLiveActivity.this.onBackPressed();
            }

            public void reConnecting() {
                PrepareLiveActivity.this.isConnectingChatService = true;
                PrepareLiveActivity.this.addSocketTipMsg(R.string.fq_start_reconnect_socket);
                ((PrePareLivePresenter) PrepareLiveActivity.this.mPresenter).getWebSocketAddress(PrepareLiveActivity.this.liveId, "1", "1");
            }

            public void reConnectCountOver() {
                PrepareLiveActivity.this.isConnectingChatService = false;
                PrepareLiveActivity.this.reConnectCountOver = true;
                PrepareLiveActivity.this.addSocketReconnectMsg();
            }
        });
    }

    public void onWebSocketAddressSuccess(LiveEntity liveEntity) {
        this.socketUrl = com.tomatolive.library.utils.b.a(liveEntity.wsServerAddress, this.liveId, this.userId, "1", liveEntity.k);
        if (this.wsManager != null) {
            this.wsManager.reconnect(this.socketUrl);
        }
    }

    public void onWebSocketAddressFail() {
        if (h.a()) {
            ((PrePareLivePresenter) this.mPresenter).getWebSocketAddress(this.liveId, "1", "1");
        } else if (this.wsManager != null) {
            this.wsManager.resetCount();
            this.wsManager.setStatus(WsStatus.CONNECT_FAIL);
            this.isConnectingChatService = false;
            this.reConnectCountOver = true;
        }
    }

    public void onLivePopularitySuccess(long j) {
        this.onLineCount.set(j);
        this.mLivePusherInfoView.setRoomPerson(this.onLineCount.get());
    }

    public void onGiftBoxListSuccess(List<GiftBoxEntity> list) {
        if (this.mGiftBoxView != null) {
            this.mGiftBoxView.showBoxList(list, this.liveId);
        }
    }

    private void stopSocket() {
        if (this.wsManager != null) {
            this.wsManager.setOnBackgroundSocketCallBack(null);
            this.wsManager.stopService();
            this.wsManager = null;
        }
    }

    private void addSocketTipMsg(int i) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgText(getString(i));
        chatEntity.setMsgType(7);
        handlerMainPost(new -$$Lambda$PrepareLiveActivity$fkb_pNtFV9cIKVR-dT-9jkQQYNE(this, chatEntity));
    }

    private void addSocketReconnectMsg() {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgText("reconnect");
        chatEntity.setMsgType(2);
        handlerMainPost(new -$$Lambda$PrepareLiveActivity$asY9upytdfNB5v4RAvb-6GbP9uc(this, chatEntity));
    }

    public void onBackThreadReceiveMessage(SocketMessageEvent socketMessageEvent) {
        if (com.tomatolive.library.utils.b.n(socketMessageEvent.code)) {
            ResultData resultData = socketMessageEvent.resultData;
            if (resultData != null && !isFinishing() && this.workHandler != null && this.mainHandler != null) {
                String str = socketMessageEvent.messageType;
                Object obj = -1;
                switch (str.hashCode()) {
                    case -1598856750:
                        if (str.equals(ConnectSocketParams.MESSAGE_BANPOSTALL_TYPE)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case -1302490523:
                        if (str.equals(ConnectSocketParams.MESSAGE_CONSUME_NOTIFY_TYPE)) {
                            obj = 16;
                            break;
                        }
                        break;
                    case -1256385881:
                        if (str.equals(ConnectSocketParams.MESSAGE_TOKEN_INVALID_NOTIFY_TYPE)) {
                            obj = 13;
                            break;
                        }
                        break;
                    case -1039689911:
                        if (str.equals(ConnectSocketParams.MESSAGE_NOTIFY_TYPE)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case -993690229:
                        if (str.equals(ConnectSocketParams.MESSAGE_PROP_SEND_TYPE)) {
                            obj = 21;
                            break;
                        }
                        break;
                    case -992867598:
                        if (str.equals(ConnectSocketParams.MESSAGE_GRAB_GIFTBOX_BROADCAST_TYPE)) {
                            obj = 20;
                            break;
                        }
                        break;
                    case -941691210:
                        if (str.equals(ConnectSocketParams.MESSAGE_UNIVERSAL_BROADCAST_TYPE)) {
                            obj = 15;
                            break;
                        }
                        break;
                    case -634778976:
                        if (str.equals(ConnectSocketParams.MESSAGE_FORBID_LIVE_TYPE)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case -337843889:
                        if (str.equals(ConnectSocketParams.MESSAGE_BAN_POST_TYPE)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case -236148015:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVECONTROL_TYPE)) {
                            obj = 12;
                            break;
                        }
                        break;
                    case 3052376:
                        if (str.equals(ConnectSocketParams.MESSAGE_CHAT_TYPE)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 3172656:
                        if (str.equals(ConnectSocketParams.MESSAGE_GIFT_TYPE)) {
                            obj = null;
                            break;
                        }
                        break;
                    case 3641990:
                        if (str.equals(ConnectSocketParams.MESSAGE_WARN_TYPE)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 96667762:
                        if (str.equals(ConnectSocketParams.MESSAGE_ENTER_TYPE)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 98509126:
                        if (str.equals(ConnectSocketParams.MESSAGE_GOOUT_TYPE)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case 99368259:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVEADMIN_GOOUT_TYPE)) {
                            obj = 10;
                            break;
                        }
                        break;
                    case 102846135:
                        if (str.equals(ConnectSocketParams.MESSAGE_LEAVE_TYPE)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 441119852:
                        if (str.equals(ConnectSocketParams.MESSAGE_PUT_GIFT_BOX_TYPE)) {
                            obj = 18;
                            break;
                        }
                        break;
                    case 487782924:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVEADMIN_BANPOST_TYPE)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 798249924:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVE_SETTING_TYPE)) {
                            obj = 17;
                            break;
                        }
                        break;
                    case 1680327801:
                        if (str.equals(ConnectSocketParams.MESSAGE_GIFT_TRUMPET_TYPE)) {
                            obj = 14;
                            break;
                        }
                        break;
                    case 2021199175:
                        if (str.equals(ConnectSocketParams.MESSAGE_GRAB_GIFTBOX_NOTIFIED_TYPE)) {
                            obj = 19;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        str = com.tomatolive.library.utils.b.a(resultData);
                        this.tempAmount += p.b(str);
                        handlerMainPost(new -$$Lambda$PrepareLiveActivity$mRcHirHpYU4IKSjD3i5afCkh7cg(this, str));
                        playReceiveAnimOnMainThread(resultData);
                        break;
                    case 1:
                        dealEnterMsgFromSocket(resultData);
                        break;
                    case 2:
                        dealChatMsgFromSocket(resultData);
                        break;
                    case 3:
                        dealWarnMsgFromSocket(resultData);
                        break;
                    case 4:
                        dealLeaveMsgFromSocket(resultData);
                        break;
                    case 5:
                        dealSuperBanPostMsgFromSocket(resultData);
                        break;
                    case 6:
                        dealBanPostMsgFromSocket(resultData);
                        break;
                    case 7:
                        int i;
                        if (TextUtils.equals("1", resultData.banPostAllStatus)) {
                            i = R.string.fq_anchor_start_banned;
                        } else {
                            i = R.string.fq_anchor_cancel_banned;
                        }
                        showReceiveMsgOnChatList(resultData, getString(i), 5);
                        break;
                    case 8:
                        dealNotifyMsgFromSocket(resultData);
                        break;
                    case 9:
                        handlerMainPost(new -$$Lambda$PrepareLiveActivity$4yvOUl6FYimrJ_T21K7LQhRhYPU(this));
                        break;
                    case 10:
                    case 11:
                        dealKickOutMsgFromSocket(resultData);
                        break;
                    case 12:
                        handlerMainPost(new -$$Lambda$PrepareLiveActivity$rvpC9eJEQxdFpBzfp_rN9AAS9Jk(this, resultData));
                        if (TextUtils.equals(resultData.action, "1")) {
                            str = getString(R.string.fq_appoint_house_manage);
                        } else {
                            str = getString(R.string.fq_cancel_appoint_house_manage);
                        }
                        showReceiveMsgOnChatList(resultData, str, 6);
                        break;
                    case 13:
                        handlerMainPost(new -$$Lambda$PrepareLiveActivity$n85-lZc3StdsI8l2nvAufU6vfSg(this));
                        break;
                    case 14:
                        synchronized (PrepareLiveActivity.class) {
                            if (this.giftNoticeQueue == null) {
                                this.giftNoticeQueue = new ConcurrentLinkedQueue();
                            }
                            if (this.giftNoticeQueue.size() == 99) {
                                this.giftNoticeQueue.poll();
                            }
                            this.giftNoticeQueue.offer(resultData);
                            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START);
                        }
                        break;
                    case 15:
                        synchronized (PrepareLiveActivity.class) {
                            if (this.sysNoticeQueue == null) {
                                this.sysNoticeQueue = new ConcurrentLinkedQueue();
                            }
                            if (this.sysNoticeQueue.size() == 99) {
                                this.sysNoticeQueue.poll();
                            }
                            this.sysNoticeQueue.offer(resultData);
                            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_OPEN_INPUT);
                        }
                        break;
                    case 16:
                        if (TextUtils.equals(resultData.type, "openGuard")) {
                            handlerMainPost(new -$$Lambda$PrepareLiveActivity$rZczlfkeWK7CtvUAAiIRXP8FIj0(this, resultData));
                            synchronized (PrepareLiveActivity.class) {
                                if (this.guardOpenMsgQueue == null) {
                                    this.guardOpenMsgQueue = new ConcurrentLinkedQueue();
                                }
                                if (this.guardOpenMsgQueue.size() == 99) {
                                    this.guardOpenMsgQueue.poll();
                                }
                                this.guardOpenMsgQueue.offer(resultData);
                            }
                            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO);
                            break;
                        }
                        break;
                    case 17:
                        dealLiveSettingMsgFromSocket(resultData);
                        break;
                    case 18:
                        dealGiftBoxPutMsgFromSocket(resultData);
                        break;
                    case 19:
                        showToast(getString(R.string.fq_gift_box_toast_tips, new Object[]{resultData.presenterName, resultData.propNum, resultData.propName}));
                        break;
                    case 20:
                        dealGiftBoxBroadcastMsg(resultData);
                        break;
                    case 21:
                        dealReceivePropMsgFromSocket(resultData);
                        break;
                }
                return;
            }
            return;
        }
        showToast(socketMessageEvent.message);
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$27(PrepareLiveActivity prepareLiveActivity, String str) {
        if (prepareLiveActivity.mLivePusherInfoView != null) {
            prepareLiveActivity.mLivePusherInfoView.setAnchorContribution(str);
        }
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$28(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        prepareLiveActivity.liveChatMsgView.updateRoleItemDataByUid(resultData.targetId, TextUtils.equals(resultData.action, "1") ? "3" : ConnectSocketParams.EFFECT_TYPE_BIG);
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$29(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        if (prepareLiveActivity.mLivePusherInfoView != null) {
            prepareLiveActivity.mLivePusherInfoView.updateOpenGuardCount(resultData.anchorGuardCount);
        }
    }

    private void dealReceivePropMsgFromSocket(ResultData resultData) {
        long currentTimeMillis = System.currentTimeMillis();
        GiftItemEntity giftItemEntity = new GiftItemEntity();
        giftItemEntity.isProp = true;
        giftItemEntity.animalUrl = resultData.animalUrl;
        giftItemEntity.duration = p.a(resultData.duration);
        giftItemEntity.activeTime = p.a(resultData.activeTime);
        GiftIndexEntity giftIndexEntity;
        if (this.receivePropMap.containsKey(resultData.userId)) {
            Map map = (Map) this.receivePropMap.get(resultData.userId);
            giftIndexEntity = (GiftIndexEntity) map.get(resultData.propId);
            if (giftIndexEntity == null) {
                giftIndexEntity = new GiftIndexEntity();
                giftIndexEntity.createTime = currentTimeMillis;
                giftIndexEntity.sendIndex = 1;
                map.put(resultData.propId, giftIndexEntity);
            } else {
                if (currentTimeMillis - giftIndexEntity.createTime < ((long) p.a(giftItemEntity.activeTime + giftItemEntity.duration))) {
                    giftIndexEntity.sendIndex++;
                } else {
                    giftIndexEntity.sendIndex = 1;
                }
                giftIndexEntity.createTime = currentTimeMillis;
            }
            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
        } else {
            HashMap hashMap = new HashMap();
            giftIndexEntity = new GiftIndexEntity();
            giftIndexEntity.createTime = currentTimeMillis;
            giftIndexEntity.sendIndex = 1;
            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
            hashMap.put(resultData.propId, giftIndexEntity);
            this.receivePropMap.put(resultData.userId, hashMap);
        }
        giftItemEntity.sendUserName = resultData.userName;
        giftItemEntity.userId = resultData.userId;
        giftItemEntity.effect_type = p.a(resultData.animalType);
        giftItemEntity.animalType = giftItemEntity.effect_type;
        giftItemEntity.avatar = resultData.avatar;
        giftItemEntity.role = resultData.role;
        giftItemEntity.sex = resultData.sex;
        giftItemEntity.imgurl = resultData.coverUrl;
        giftItemEntity.name = resultData.propName;
        giftItemEntity.id = resultData.propId;
        giftItemEntity.typeid = resultData.propId;
        resultData.giftName = resultData.propName;
        resultData.giftId = resultData.propId;
        playReceiveAnimGift(giftItemEntity);
        StringBuilder stringBuilder;
        if (giftItemEntity.isBigProp()) {
            if (this.wsManager != null) {
                this.wsManager.addReceiveBigAnim(giftItemEntity);
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_give_to_anchor));
            stringBuilder.append(resultData.propName);
            stringBuilder.append(getString(R.string.fq_text_gift_multiple));
            stringBuilder.append(giftItemEntity.sendIndex);
            showReceiveMsgOnChatList(resultData, stringBuilder.toString(), 1);
        } else if (giftItemEntity.sendIndex == 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_give_to_anchor));
            stringBuilder.append(resultData.propName);
            showReceiveMsgOnChatList(resultData, stringBuilder.toString(), 1);
        } else if (giftItemEntity.sendIndex != 0 && giftItemEntity.sendIndex % 10 == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_give_to_anchor));
            stringBuilder.append(resultData.propName);
            stringBuilder.append(getString(R.string.fq_text_gift_multiple));
            stringBuilder.append(giftItemEntity.sendIndex);
            showReceiveMsgOnChatList(resultData, stringBuilder.toString(), 1);
        }
        wsManagerNotifyAnim();
    }

    private void dealGiftBoxPutMsgFromSocket(ResultData resultData) {
        handlerMainPost(new -$$Lambda$PrepareLiveActivity$zpoHeALcjTdE8iDPsZLMXPSCmME(this, resultData));
    }

    public static /* synthetic */ void lambda$dealGiftBoxPutMsgFromSocket$30(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        GiftBoxEntity giftBoxEntity = new GiftBoxEntity();
        giftBoxEntity.expirationTime = p.b(resultData.expirationTime);
        giftBoxEntity.openTime = p.b(resultData.openTime);
        giftBoxEntity.giftBoxUniqueCode = resultData.giftBoxUniqueCode;
        giftBoxEntity.presenterAvatar = resultData.presenterAvatar;
        giftBoxEntity.presenterId = resultData.presenterId;
        giftBoxEntity.presenterName = resultData.presenterName;
        giftBoxEntity.liveId = prepareLiveActivity.liveId;
        giftBoxEntity.userId = prepareLiveActivity.userId;
        prepareLiveActivity.mGiftBoxView.addOneBox(giftBoxEntity);
    }

    private void dealLiveSettingMsgFromSocket(ResultData resultData) {
        String str = resultData.changeType;
        Object obj = (str.hashCode() == 1559674578 && str.equals(ConnectSocketParams.MESSAGE_SPEAKLEVEL_TYPE)) ? null : -1;
        if (obj == null) {
            this.speakLevel = resultData.changeValue;
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgText(getString(R.string.fq_speak_level_tip_for_socket, new Object[]{this.speakLevel}));
            chatEntity.setExpGrade(this.speakLevel);
            chatEntity.setMsgType(11);
            handlerMainPost(new -$$Lambda$PrepareLiveActivity$r-XrPDaDBaMS2bHg0VT4plWQRgw(this, chatEntity));
        }
    }

    private void dealSuperBanPostMsgFromSocket(ResultData resultData) {
        showReceiveMsgOnChatList(resultData, getString(R.string.fq_banned_forever, new Object[]{resultData.userName}), 4);
    }

    private void dealKickOutMsgFromSocket(ResultData resultData) {
        if (TextUtils.equals(z.a().c(), resultData.userId)) {
            resultData.userName = getString(R.string.fq_anchor);
        }
        showReceiveMsgOnChatList(resultData, getString(com.tomatolive.library.utils.b.k(resultData.role) ? R.string.fq_warn_content_out_room_forever : R.string.fq_warn_content_out_room, new Object[]{resultData.userName}), 9);
    }

    private void dealEnterMsgFromSocket(ResultData resultData) {
        addEnterMsgToQueue(resultData);
        if (TextUtils.equals(this.userId, resultData.userId)) {
            ((PrePareLivePresenter) this.mPresenter).getLiveInitInfo(this.liveId, "1", this.isReConnectStatus ? "1" : "0");
        }
    }

    private void dealChatMsgFromSocket(ResultData resultData) {
        if (!this.shieldedList.contains(resultData.userId) && !this.banedPostList.contains(resultData.userId)) {
            this.chatContent = resultData.content;
            if (this.isTranOpen) {
                y.a(this.chatContent, new -$$Lambda$PrepareLiveActivity$zNtbE37tag_Mg_2HgRKMSt6gCFk(this, resultData));
            } else {
                showReceiveMsgOnChatList(resultData, this.chatContent, 3);
            }
        }
    }

    private void dealWarnMsgFromSocket(ResultData resultData) {
        if (this.isPushInBackground) {
            this.isWarn = TextUtils.equals("1", resultData.action);
        } else {
            handlerMainPost(new -$$Lambda$PrepareLiveActivity$aoFocZTDQKYm4SkQZJuxMgSYASU(this, resultData));
        }
    }

    public static /* synthetic */ void lambda$dealWarnMsgFromSocket$33(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        if (TextUtils.equals("1", resultData.action)) {
            if (prepareLiveActivity.endWarnDialog != null && prepareLiveActivity.endWarnDialog.isAdded()) {
                prepareLiveActivity.endWarnDialog.dismiss();
                prepareLiveActivity.endWarnDialog = null;
            }
            prepareLiveActivity.startWarnDialog = WarnDialog.newInstance(WarnDialog.WARN_TIP);
            prepareLiveActivity.startWarnDialog.show(prepareLiveActivity.getSupportFragmentManager());
            return;
        }
        if (prepareLiveActivity.startWarnDialog != null && prepareLiveActivity.startWarnDialog.isAdded()) {
            prepareLiveActivity.startWarnDialog.dismiss();
            prepareLiveActivity.startWarnDialog = null;
        }
        prepareLiveActivity.endWarnDialog = WarnDialog.newInstance(WarnDialog.STOP_WARN_TIP);
        prepareLiveActivity.endWarnDialog.show(prepareLiveActivity.getSupportFragmentManager());
    }

    private void dealLeaveMsgFromSocket(ResultData resultData) {
        if (!TextUtils.equals(resultData.userId, z.a().c())) {
            handlerMainPost(new -$$Lambda$PrepareLiveActivity$uNqeHxjC-1x834PLls7qNQeBS28(this, resultData));
        }
    }

    public static /* synthetic */ void lambda$dealLeaveMsgFromSocket$34(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        prepareLiveActivity.mLivePusherInfoView.removeUserItemById(resultData.userId);
        prepareLiveActivity.mLivePusherInfoView.setRoomPerson(prepareLiveActivity.onLineCount.get());
    }

    private void dealBanPostMsgFromSocket(ResultData resultData) {
        String string;
        boolean isSomeoneBanPost = resultData.isSomeoneBanPost();
        if (isSomeoneBanPost) {
            this.banedPostList.add(resultData.targetId);
        } else {
            this.banedPostList.remove(resultData.targetId);
        }
        if (TextUtils.equals(getString(R.string.fq_system), resultData.userName) || TextUtils.equals(this.userId, resultData.userId)) {
            resultData.userName = getString(R.string.fq_anchor);
        }
        if (isSomeoneBanPost) {
            string = getString(R.string.fq_banned, new Object[]{resultData.userName});
        } else {
            string = getString(R.string.fq_banned_cancel, new Object[]{resultData.userName});
        }
        showReceiveMsgOnChatList(resultData, string, 4);
    }

    private void dealTokenInvalidMsgFromSocket() {
        ((PrePareLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(this.liveId, ConnectSocketParams.MESSAGE_LEAVE_TYPE);
        startTokenDialogService();
        if (o.a() && this.wsManager != null) {
            this.wsManager.sendLeaveMessage(new SendMessageEntity());
            m.b("dealTokenInvalidMsgFromSocket==> sendLeaveMessage");
        }
        closePusher();
        this.isLiving = false;
        q.a().c(this, 300, TimeUnit.MILLISECONDS, new -$$Lambda$PrepareLiveActivity$iILJCRrNLE6LZqXEDtRYU_FzmCU(this));
    }

    private void dealForbidMsgFromSocket() {
        if (this.endWarnDialog != null && this.endWarnDialog.isAdded()) {
            this.endWarnDialog.dismiss();
            this.endWarnDialog = null;
        }
        if (this.startWarnDialog != null && this.startWarnDialog.isAdded()) {
            this.startWarnDialog.dismiss();
            this.startWarnDialog = null;
        }
        prepareFinish();
        ((PrePareLivePresenter) this.mPresenter).getLiveEndInfo(this.liveId, this.streamName, 2);
    }

    private void dealNotifyMsgFromSocket(ResultData resultData) {
        String str = resultData.type;
        if (!TextUtils.isEmpty(str)) {
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != 50) {
                if (hashCode == 1568 && str.equals("11")) {
                    obj = 1;
                }
            } else if (str.equals(ConnectSocketParams.EFFECT_TYPE_BIG)) {
                obj = null;
            }
            switch (obj) {
                case null:
                    handlerMainPost(new -$$Lambda$PrepareLiveActivity$jCsoTWfidN7OaJJNMFm1qcIFfk0(this, resultData));
                    break;
                case 1:
                    this.isBanGroup = true;
                    break;
                default:
                    return;
            }
        }
    }

    public static /* synthetic */ void lambda$dealNotifyMsgFromSocket$36(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgType(8);
        chatEntity.setMsgText(resultData.typeMsg);
        prepareLiveActivity.liveChatMsgView.addChatMsg(chatEntity);
    }

    public void onBackThreadReceiveBigAnimMsg(GiftItemEntity giftItemEntity) {
        if (giftItemEntity.isProp) {
            this.liveAnimationView.loadPropAnimation(giftItemEntity.animalUrl);
        } else if (TextUtils.isEmpty(giftItemEntity.giftDirPath) || !g.b(com.tomatolive.library.utils.b.a(giftItemEntity.giftDirPath, giftItemEntity.jsonname))) {
            this.liveAnimationView.loadGiftAnimation(giftItemEntity);
        } else {
            this.liveAnimationView.loadGiftAnimation(giftItemEntity.giftDirPath, giftItemEntity.jsonname);
        }
    }

    private void playReceiveAnimOnMainThread(ResultData resultData) {
        long currentTimeMillis = System.currentTimeMillis();
        GiftItemEntity giftItemEntity = GiftDownLoadManager.getInstance().getGiftItemEntity(resultData.giftId);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("接受别人的礼物：");
        stringBuilder.append(giftItemEntity);
        m.a(stringBuilder.toString());
        if (giftItemEntity == null) {
            if (!this.isStartGiftUpdate) {
                this.isStartGiftUpdate = true;
                ((PrePareLivePresenter) this.mPresenter).getGiftList(-1);
            }
            return;
        }
        GiftIndexEntity giftIndexEntity;
        if (this.receiveGiftMap.containsKey(resultData.userId)) {
            Map map = (Map) this.receiveGiftMap.get(resultData.userId);
            giftIndexEntity = (GiftIndexEntity) map.get(giftItemEntity.typeid);
            if (giftIndexEntity == null) {
                giftIndexEntity = new GiftIndexEntity();
                giftIndexEntity.createTime = currentTimeMillis;
                giftIndexEntity.sendIndex = 1;
                map.put(giftItemEntity.typeid, giftIndexEntity);
            } else {
                if (currentTimeMillis - giftIndexEntity.createTime < ((long) p.a(giftItemEntity.active_time + giftItemEntity.duration))) {
                    giftIndexEntity.sendIndex++;
                } else {
                    giftIndexEntity.sendIndex = 1;
                }
                giftIndexEntity.createTime = currentTimeMillis;
            }
            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
        } else {
            HashMap hashMap = new HashMap();
            giftIndexEntity = new GiftIndexEntity();
            giftIndexEntity.createTime = currentTimeMillis;
            giftIndexEntity.sendIndex = 1;
            hashMap.put(giftItemEntity.typeid, giftIndexEntity);
            this.receiveGiftMap.put(resultData.userId, hashMap);
            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
        }
        giftItemEntity.sendUserName = resultData.userName;
        giftItemEntity.userId = resultData.userId;
        giftItemEntity.effect_type = p.a(resultData.effectType);
        giftItemEntity.avatar = resultData.avatar;
        giftItemEntity.role = resultData.role;
        giftItemEntity.sex = resultData.sex;
        playReceiveAnimGift(giftItemEntity);
        StringBuilder stringBuilder2;
        if (giftItemEntity.isBigAnim()) {
            if (this.wsManager != null) {
                this.wsManager.addReceiveBigAnim(giftItemEntity);
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getString(R.string.fq_give_to_anchor));
            stringBuilder2.append(resultData.giftName);
            stringBuilder2.append(getString(R.string.fq_text_gift_multiple));
            stringBuilder2.append(giftItemEntity.sendIndex);
            showReceiveMsgOnChatList(resultData, stringBuilder2.toString(), 1);
        } else if (giftItemEntity.sendIndex == 1) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getString(R.string.fq_give_to_anchor));
            stringBuilder2.append(resultData.giftName);
            showReceiveMsgOnChatList(resultData, stringBuilder2.toString(), 1);
        } else if (giftItemEntity.sendIndex != 0 && giftItemEntity.sendIndex % 10 == 0) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(getString(R.string.fq_give_to_anchor));
            stringBuilder2.append(resultData.giftName);
            stringBuilder2.append(getString(R.string.fq_text_gift_multiple));
            stringBuilder2.append(giftItemEntity.sendIndex);
            showReceiveMsgOnChatList(resultData, stringBuilder2.toString(), 1);
        }
        wsManagerNotifyAnim();
    }

    private void playReceiveAnimGift(GiftItemEntity giftItemEntity) {
        if (giftItemEntity != null) {
            GiftAnimModel giftAnimModel = new GiftAnimModel();
            GiftAnimModel onLineUrl = giftAnimModel.setGiftId(giftItemEntity.id).setProp(giftItemEntity.isProp).setOnLineUrl(giftItemEntity.isProp ? giftItemEntity.animalUrl : giftItemEntity.onlineUrl);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(giftItemEntity.effect_type);
            stringBuilder.append("");
            onLineUrl.setEffectType(stringBuilder.toString()).setGiftTypeId(giftItemEntity.typeid).setGiftName(giftItemEntity.name).setGiftCount(1).setGiftPic(giftItemEntity.imgurl).setGiftPrice(giftItemEntity.price).setSendUserId(giftItemEntity.userId).setSendUserName(giftItemEntity.sendUserName).setSendGiftTime(Long.valueOf(System.currentTimeMillis())).setCurrentStart(true).setGiftDirPath(giftItemEntity.isBigAnim() ? "" : giftItemEntity.giftDirPath).setJsonName(giftItemEntity.jsonname).setGiftShowTime(p.a(giftItemEntity.duration)).setSendIndex(giftItemEntity.sendIndex).setSendUserPic(giftItemEntity.avatar).setAnimationListener(this);
            giftAnimModel.setJumpCombo(giftItemEntity.sendIndex);
            handlerMainPost(new -$$Lambda$PrepareLiveActivity$22bb9Lncpvu8LbyIFf2PmyRRagw(this, giftAnimModel, giftItemEntity));
        }
    }

    private void wsManagerNotifyBigAnim() {
        if (this.wsManager != null) {
            this.wsManager.notifyBigAnim();
        }
    }

    private void wsManagerNotifyAnim() {
        if (this.wsManager != null) {
            this.wsManager.notifyAnim();
        }
    }

    private void showReceiveMsgOnChatList(ResultData resultData, String str, int i) {
        String str2 = resultData.role;
        if (TextUtils.equals(resultData.userId, z.a().c()) && TextUtils.equals(str2, "5")) {
            str2 = "1";
        }
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgSendName(resultData.userName);
        chatEntity.setMsgText(str);
        chatEntity.setMsgType(i);
        chatEntity.setSex(resultData.sex);
        chatEntity.setUid(resultData.userId);
        chatEntity.setGiftId(resultData.giftId);
        chatEntity.setRole(str2);
        chatEntity.setGiftName(resultData.giftName);
        chatEntity.setUserAvatar(resultData.avatar);
        chatEntity.setTargetAvatar(resultData.targetAvatar);
        chatEntity.setTargetName(resultData.targetName);
        chatEntity.setTargetId(resultData.targetId);
        chatEntity.setExpGrade(com.tomatolive.library.utils.b.h(resultData.expGrade));
        chatEntity.setGuardType(p.a(resultData.guardType));
        addMsgToQueue(chatEntity);
        if (i == 3 && resultData.isOpenDanmu() && com.tomatolive.library.utils.b.c(p.a(resultData.guardType))) {
            handlerMainPost(new -$$Lambda$PrepareLiveActivity$mpXiYIKO4lcZ8mNP9x_aI3wsXPk(this, chatEntity));
        }
    }

    private void addMsgToQueue(ChatEntity chatEntity) {
        synchronized (PrepareLiveActivity.class) {
            if (this.receiveMsgQueue == null) {
                this.receiveMsgQueue = new ConcurrentLinkedQueue();
            }
            if (this.receiveMsgQueue.size() == 99) {
                this.receiveMsgQueue.poll();
            }
            this.receiveMsgQueue.offer(chatEntity);
            if (this.asleep) {
                this.asleep = false;
                sendWorkHandlerEmptyMessage(10001);
            }
        }
    }

    private void addEnterMsgToQueue(ResultData resultData) {
        synchronized (PrepareLiveActivity.class) {
            if (this.enterMsgQueue == null) {
                this.enterMsgQueue = new ConcurrentLinkedQueue();
            }
            if (this.enterMsgQueue.size() == 99) {
                this.enterMsgQueue.poll();
            }
            this.enterMsgQueue.offer(resultData);
            sendWorkHandlerEmptyMessage(10002);
        }
        if (resultData.isEnterGuardType() || com.tomatolive.library.utils.b.p(resultData.carId)) {
            synchronized (PrepareLiveActivity.class) {
                if (this.guardEnterMsgQueue == null) {
                    this.guardEnterMsgQueue = new ConcurrentLinkedQueue();
                }
                if (this.guardEnterMsgQueue.size() == 99) {
                    this.guardEnterMsgQueue.poll();
                }
                this.guardEnterMsgQueue.offer(resultData);
                sendWorkHandlerEmptyMessage(10003);
            }
        }
    }

    public static /* synthetic */ boolean lambda$new$39(PrepareLiveActivity prepareLiveActivity, Message message) {
        switch (message.what) {
            case 10001:
                prepareLiveActivity.dealChatMsg();
                break;
            case 10002:
                prepareLiveActivity.dealEnterMsg();
                break;
            case 10003:
                prepareLiveActivity.dealGuardEnterMsg();
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START /*10004*/:
                prepareLiveActivity.dealGiftNotice();
                break;
            case IMediaPlayer.MEDIA_INFO_OPEN_INPUT /*10005*/:
                prepareLiveActivity.dealSysNotice();
                break;
            case IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO /*10006*/:
                prepareLiveActivity.dealGuardOpenMsg();
                break;
        }
        return true;
    }

    private void dealGuardOpenMsg() {
        if (this.canShowGuardOPenMsg) {
            ResultData resultData = (ResultData) this.guardOpenMsgQueue.poll();
            if (resultData != null) {
                this.canShowGuardOPenMsg = false;
                handlerMainPost(new -$$Lambda$PrepareLiveActivity$Z2p5eXS2HTBaB8-6VgzWTom49pw(this, resultData));
            }
        }
        if (this.guardOpenMsgQueue != null && this.guardOpenMsgQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO);
        }
    }

    public static /* synthetic */ void lambda$dealGuardOpenMsg$40(PrepareLiveActivity prepareLiveActivity, ResultData resultData) {
        prepareLiveActivity.liveAnimationView.loadGuardOpenAnimation(resultData, new OnAnimPlayListener() {
            public void onStart() {
                PrepareLiveActivity.this.canShowGuardOPenMsg = false;
            }

            public void onEnd() {
                PrepareLiveActivity.this.canShowGuardOPenMsg = true;
                PrepareLiveActivity.this.liveAnimationView.removeGuardOpenAllViews();
            }
        });
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgType(12);
        chatEntity.setMsgSendName(resultData.userName);
        chatEntity.setExpGrade(resultData.expGrade);
        chatEntity.setGuardType(p.a(resultData.guardType));
        chatEntity.setMsgText(prepareLiveActivity.getString(R.string.fq_open_guard_tip, new Object[]{com.tomatolive.library.utils.b.b(prepareLiveActivity.mContext, resultData.guardType)}));
        chatEntity.setRole(resultData.role);
        prepareLiveActivity.liveChatMsgView.addChatMsg(chatEntity);
        prepareLiveActivity.liveChatMsgView.updateGuardTypeItemDataByUid(resultData.userId, p.a(resultData.guardType));
        prepareLiveActivity.mLivePusherInfoView.sortUserList(resultData.userId, resultData.guardType, resultData.expGrade);
    }

    private void dealGuardEnterMsg() {
        if (this.canShowGuardEnterMsg && this.carFullAnimFinish) {
            ResultData resultData = (ResultData) this.guardEnterMsgQueue.poll();
            if (resultData != null) {
                this.canShowGuardEnterMsg = false;
                handlerMainPost(new -$$Lambda$PrepareLiveActivity$v7Kaq-J9kzOyfFTLQrxsKK5q5Z8(this, resultData, com.tomatolive.library.utils.b.h(resultData.expGrade)));
            }
        }
        if (this.guardEnterMsgQueue != null && this.guardEnterMsgQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(10003);
        }
    }

    public static /* synthetic */ void lambda$dealGuardEnterMsg$41(PrepareLiveActivity prepareLiveActivity, ResultData resultData, String str) {
        if (resultData.isEnterGuardType() && com.tomatolive.library.utils.b.p(resultData.carId)) {
            prepareLiveActivity.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, i.a(prepareLiveActivity.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
            if (resultData.isOnPlayCarAnim()) {
                prepareLiveActivity.carFullAnimFinish = false;
                prepareLiveActivity.liveAnimationView.loadCarJoinAnimation(resultData, false);
            }
        } else if (resultData.isEnterGuardType()) {
            prepareLiveActivity.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, i.a(prepareLiveActivity.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
        } else if (resultData.isOnPlayCarAnim()) {
            prepareLiveActivity.carFullAnimFinish = false;
            prepareLiveActivity.liveAnimationView.loadCarJoinAnimation(resultData, true);
        } else {
            prepareLiveActivity.canShowGuardEnterMsg = true;
        }
    }

    private void dealGiftNotice() {
        if (this.canShowGiftNotice) {
            ResultData resultData = (ResultData) this.giftNoticeQueue.poll();
            if (resultData != null) {
                this.canShowGiftNotice = false;
                handlerMainPost(new -$$Lambda$PrepareLiveActivity$RvyZ1RmViDLx7E-f6svCbkl7AZw(this, resultData));
            }
        }
        if (this.giftNoticeQueue != null && this.giftNoticeQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START);
        }
    }

    private void dealSysNotice() {
        if (this.canShowSysNotice) {
            ResultData resultData = (ResultData) this.sysNoticeQueue.poll();
            if (resultData != null) {
                this.canShowSysNotice = false;
                handlerMainPost(new -$$Lambda$PrepareLiveActivity$40mumaNP8OCCSfwcyHHdWz_MchY(this, resultData));
            }
        }
        if (this.sysNoticeQueue != null && this.sysNoticeQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_OPEN_INPUT);
        }
    }

    private void dealEnterMsg() {
        if (this.canShowEnterMsg) {
            ResultData resultData = (ResultData) this.enterMsgQueue.poll();
            if (resultData != null) {
                this.canShowEnterMsg = false;
                String h = com.tomatolive.library.utils.b.h(resultData.expGrade);
                if (!TextUtils.isEmpty(h)) {
                    handlerMainPost(new -$$Lambda$PrepareLiveActivity$Wtmp6lieTld064QBboWJAAy469Q(this, resultData, h));
                }
            }
        }
        if (this.enterMsgQueue != null && this.enterMsgQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(10002);
        }
    }

    public static /* synthetic */ void lambda$dealEnterMsg$45(PrepareLiveActivity prepareLiveActivity, ResultData resultData, String str) {
        prepareLiveActivity.updateUserList(resultData);
        if (p.a(resultData.guardType) > 0 || p.a(str) >= p.a(prepareLiveActivity.entryNoticeLevelThreshold) || com.tomatolive.library.utils.b.p(resultData.carId) || com.tomatolive.library.utils.b.k(resultData.role)) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgType(0);
            chatEntity.setMsgSendName(resultData.userName);
            chatEntity.setExpGrade(str);
            chatEntity.setGuardType(p.a(resultData.guardType));
            chatEntity.setMsgText(prepareLiveActivity.getString(R.string.fq_live_joinroom_notify));
            chatEntity.setUserAvatar(resultData.avatar);
            chatEntity.setUid(resultData.userId);
            chatEntity.setRole(resultData.role);
            chatEntity.setSex(resultData.sex);
            if (com.tomatolive.library.utils.b.p(resultData.carId)) {
                String str2;
                CarDownloadEntity carItemEntity = CarDownLoadManager.getInstance().getCarItemEntity(resultData.carId);
                if (carItemEntity == null) {
                    str2 = "";
                } else {
                    str2 = carItemEntity.imgUrl;
                }
                chatEntity.setCarIcon(str2);
            }
            prepareLiveActivity.liveChatMsgView.addChatMsg(chatEntity);
        } else {
            prepareLiveActivity.liveChatMsgView.setUserGradeInfo(str, resultData.userName);
        }
        q.a().b(prepareLiveActivity.mContext, DURATION_GET_MESSAGE, TimeUnit.MILLISECONDS, new -$$Lambda$PrepareLiveActivity$MRvrcgts4v0R5V1_-Cr94xYfvVQ(prepareLiveActivity));
    }

    /* JADX WARNING: Missing block: B:15:0x002a, code skipped:
            handlerMainPost(new com.tomatolive.library.ui.activity.live.-$$Lambda$PrepareLiveActivity$WexsRlm5pU6uK-2JwaAGWgcPHqs(r4, r0));
     */
    /* JADX WARNING: Missing block: B:16:0x0032, code skipped:
            return;
     */
    private void dealChatMsg() {
        /*
        r4 = this;
        r0 = new java.util.LinkedList;
        r0.<init>();
        r1 = com.tomatolive.library.ui.activity.live.PrepareLiveActivity.class;
        monitor-enter(r1);
        r2 = r4.receiveMsgQueue;	 Catch:{ all -> 0x0038 }
        if (r2 == 0) goto L_0x0033;
    L_0x000c:
        r2 = r4.receiveMsgQueue;	 Catch:{ all -> 0x0038 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0038 }
        if (r2 == 0) goto L_0x0015;
    L_0x0014:
        goto L_0x0033;
    L_0x0015:
        r2 = 0;
    L_0x0016:
        r3 = 5;
        if (r2 >= r3) goto L_0x0029;
    L_0x0019:
        r3 = r4.receiveMsgQueue;	 Catch:{ all -> 0x0038 }
        r3 = r3.poll();	 Catch:{ all -> 0x0038 }
        r3 = (com.tomatolive.library.model.ChatEntity) r3;	 Catch:{ all -> 0x0038 }
        if (r3 == 0) goto L_0x0029;
    L_0x0023:
        r0.add(r3);	 Catch:{ all -> 0x0038 }
        r2 = r2 + 1;
        goto L_0x0016;
    L_0x0029:
        monitor-exit(r1);	 Catch:{ all -> 0x0038 }
        r1 = new com.tomatolive.library.ui.activity.live.-$$Lambda$PrepareLiveActivity$WexsRlm5pU6uK-2JwaAGWgcPHqs;
        r1.<init>(r4, r0);
        r4.handlerMainPost(r1);
        return;
    L_0x0033:
        r0 = 1;
        r4.asleep = r0;	 Catch:{ all -> 0x0038 }
        monitor-exit(r1);	 Catch:{ all -> 0x0038 }
        return;
    L_0x0038:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0038 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.live.PrepareLiveActivity.dealChatMsg():void");
    }

    public static /* synthetic */ void lambda$dealChatMsg$46(PrepareLiveActivity prepareLiveActivity, List list) {
        prepareLiveActivity.liveChatMsgView.addChatMsgList(list);
        if (prepareLiveActivity.workHandler != null) {
            prepareLiveActivity.workHandler.sendEmptyMessageDelayed(10001, DURATION_GET_MESSAGE);
        }
    }

    private void dealGiftBoxBroadcastMsg(ResultData resultData) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgSendName(resultData.userName);
        chatEntity.setMsgText(getString(R.string.fq_giftbox_tips));
        chatEntity.setMsgType(13);
        chatEntity.setTargetName(resultData.presenterName);
        chatEntity.setPropId(resultData.propId);
        chatEntity.setPropName(resultData.propName);
        chatEntity.setPropNum(resultData.propNum);
        addMsgToQueue(chatEntity);
    }

    private void updateUserList(ResultData resultData) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAvatar(resultData.avatar);
        userEntity.setUserId(resultData.userId);
        userEntity.setName(resultData.userName);
        userEntity.setExpGrade(com.tomatolive.library.utils.b.h(resultData.expGrade));
        userEntity.setGuardType(p.a(resultData.guardType));
        userEntity.setRole(resultData.role);
        if (!this.mLivePusherInfoView.contains(resultData.userId)) {
            this.mLivePusherInfoView.addUserItem(userEntity);
            this.mLivePusherInfoView.setRoomPerson(this.onLineCount.get());
        }
    }

    private void showUserAvatarDialog(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i) {
        String str8 = str3;
        boolean contains = this.banedPostList.contains(str8);
        boolean j = com.tomatolive.library.utils.b.j(str7);
        boolean contains2 = this.shieldedList.contains(str8);
        if (com.tomatolive.library.utils.b.k(str7)) {
            this.userSuperAvatarDialog = UserSuperAvatarDialog.newInstance(str3, str, str2, str4, str5, str6, i);
            this.userSuperAvatarDialog.show(getSupportFragmentManager());
        } else if (com.tomatolive.library.utils.b.c(i)) {
            this.userGuardAvatarDialog = UserGuardAvatarDialog.newInstance(str3, str, str2, str4, str5, true, str7, str6, i, new -$$Lambda$PrepareLiveActivity$n0q8i3FdhMLfE1MRikajGsLukII(this, contains, j, contains2, str2, str3));
            this.userGuardAvatarDialog.show(getSupportFragmentManager());
        } else {
            this.userAvatarDialog = UserAvatarDialog.newInstance(str3, str, str2, str4, str5, true, str7, str6, new -$$Lambda$PrepareLiveActivity$DHxcNhI19P5eSyhUxejzgQzdAUI(this, contains, j, contains2, str2, str3), new -$$Lambda$PrepareLiveActivity$KMwq4W5yu_RoWFKQbbkGoWNLTV4(this, str8));
            this.userAvatarDialog.show(getSupportFragmentManager());
        }
    }

    public static /* synthetic */ void lambda$showUserAvatarDialog$49(PrepareLiveActivity prepareLiveActivity, String str, View view) {
        if (com.tomatolive.library.utils.b.a(prepareLiveActivity.mContext, str)) {
            int i = 1;
            int isSelected = view.isSelected() ^ 1;
            view.setSelected(isSelected);
            prepareLiveActivity.showToast(isSelected != 0 ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
            PrePareLivePresenter prePareLivePresenter = (PrePareLivePresenter) prepareLiveActivity.mPresenter;
            if (isSelected == 0) {
                i = 0;
            }
            prePareLivePresenter.attentionAnchor(str, i);
            if (view instanceof TextView) {
                ((TextView) view).setText(isSelected != 0 ? R.string.fq_home_btn_attention_yes : R.string.fq_home_btn_attention);
            }
        }
    }

    private void dismissUserAvatarDialog() {
        if (this.userAvatarDialog != null && this.userAvatarDialog.isAdded()) {
            this.userAvatarDialog.dismiss();
        }
        if (this.userSuperAvatarDialog != null && this.userSuperAvatarDialog.isAdded()) {
            this.userSuperAvatarDialog.dismiss();
        }
        if (this.userGuardAvatarDialog != null && this.userGuardAvatarDialog.isAdded()) {
            this.userGuardAvatarDialog.dismiss();
        }
    }

    private void userAvatarDialogManager(boolean z, boolean z2, boolean z3, String str, String str2) {
        LiveActionBottomDialog.create("1", z2, z3, z, new -$$Lambda$PrepareLiveActivity$I6h1JerKwdsmHwaZaBGlIPQvjxY(this, str2, str)).show(getSupportFragmentManager());
    }

    public static /* synthetic */ void lambda$userAvatarDialogManager$51(PrepareLiveActivity prepareLiveActivity, String str, String str2, int i, boolean z) {
        prepareLiveActivity.dismissUserAvatarDialog();
        switch (i) {
            case 1:
                if (prepareLiveActivity.wsManager != null) {
                    prepareLiveActivity.wsManager.sendCtrlMessage(MessageHelper.convertToCtrlMsg(prepareLiveActivity.liveId, str, str2, z));
                    return;
                }
                return;
            case 2:
                if (z) {
                    BottomDialogUtils.showBannedDialog(prepareLiveActivity.mContext, new -$$Lambda$PrepareLiveActivity$iVmM-v3LJquoCwjtHsAC6hHdKP8(prepareLiveActivity, str, str2));
                    return;
                } else if (prepareLiveActivity.wsManager != null) {
                    prepareLiveActivity.wsManager.sendBannedMessage(MessageHelper.convertToBanMsg(prepareLiveActivity.liveId, str, str2, "-1", "1", ConnectSocketParams.EFFECT_TYPE_BIG));
                    return;
                } else {
                    return;
                }
            case 3:
                if (z) {
                    prepareLiveActivity.shieldedList.add(str);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(prepareLiveActivity.getString(R.string.fq_shielded));
                    stringBuilder.append(str2);
                    prepareLiveActivity.showToast(stringBuilder.toString());
                } else {
                    prepareLiveActivity.shieldedList.remove(str);
                    prepareLiveActivity.showToast(prepareLiveActivity.getString(R.string.fq_cancel_shielded, new Object[]{str2}));
                }
                if (prepareLiveActivity.wsManager != null) {
                    prepareLiveActivity.wsManager.sendShieldMessage(MessageHelper.convertToShieldMsg(prepareLiveActivity.liveId, str, z));
                    return;
                }
                return;
            case 4:
                if (prepareLiveActivity.wsManager != null) {
                    prepareLiveActivity.wsManager.sendKickOutMessage(MessageHelper.convertToKickOutMsg(prepareLiveActivity.liveId, str, str2));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public static /* synthetic */ void lambda$null$50(PrepareLiveActivity prepareLiveActivity, String str, String str2, long j) {
        if (prepareLiveActivity.wsManager != null) {
            prepareLiveActivity.wsManager.sendBannedMessage(MessageHelper.convertToBanMsg(prepareLiveActivity.liveId, str, str2, String.valueOf(j), "1", "1"));
        }
    }

    public static /* synthetic */ void lambda$onNetChangeListener$52(PrepareLiveActivity prepareLiveActivity, long j) {
        if (o.a()) {
            m.b("网络变化：无网络==>2s后，网络已经恢复");
            return;
        }
        prepareLiveActivity.showToast(R.string.fq_text_no_network);
        prepareLiveActivity.isNoNetEvent = true;
        if (prepareLiveActivity.isLiving) {
            m.b("开播状态，断网了。。");
            prepareLiveActivity.startPausedTimer();
        }
    }

    public void onNetChangeListener(int i) {
        switch (i) {
            case -1:
                m.b("网络变化：无网络");
                q.a().c(this.mContext, 2, TimeUnit.SECONDS, new -$$Lambda$PrepareLiveActivity$YIvTsP3jDhSRi3PR_MtKn3ftT1o(this));
                return;
            case 0:
                m.b("网络变化：4g");
                if (this.isNoNetEvent && this.isLiving) {
                    m.b("开播状态，恢复4g");
                    cancelPausedTimer();
                }
                if (!this.isPushInBackground) {
                    showMobileNetDialog();
                    return;
                }
                return;
            case 1:
                if (this.isNoNetEvent && this.isLiving) {
                    cancelPausedTimer();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void showMobileNetDialog() {
        if (!com.blankj.utilcode.util.k.a().b("SHOW_MOBIE_TIP", false)) {
            NetworkPromptDialog.newInstance(getResources().getString(R.string.fq_text_mobile_net_live), getResources().getString(R.string.fq_text_go_on_live), getResources().getString(R.string.fq_text_stop_live), -$$Lambda$PrepareLiveActivity$tUr31D3kocaLw5HzSkVG92HtAwk.INSTANCE, new -$$Lambda$PrepareLiveActivity$LxgGmAONEzGBj2CaItqY4-2iunA(this)).show(getSupportFragmentManager());
        } else if (!this.hasRemindTraffic) {
            this.hasRemindTraffic = true;
            showToast(R.string.fq_mobile_tip);
        }
    }

    public static /* synthetic */ void lambda$showMobileNetDialog$54(PrepareLiveActivity prepareLiveActivity, View view) {
        prepareLiveActivity.isLiving = false;
        prepareLiveActivity.finishLive();
    }

    private void cancelPausedTimer() {
        if (this.pauseTimer != null && !this.pauseTimer.isDisposed()) {
            m.b("取消倒计时");
            this.pauseTimer.dispose();
            this.pauseTimer = null;
        }
    }

    private void startPausedTimer() {
        if (this.pauseTimer == null) {
            m.b("启动倒计时。。。");
            this.pauseTimer = io.reactivex.k.timer(PUASE_TIME, TimeUnit.SECONDS).observeOn(wd.a()).subscribe(new -$$Lambda$PrepareLiveActivity$w9b9dU8Zqb8PxTixORfb9sOmKFg(this));
        }
    }

    public static /* synthetic */ void lambda$startPausedTimer$55(PrepareLiveActivity prepareLiveActivity, Long l) throws Exception {
        prepareLiveActivity.isLiving = false;
        if (o.a()) {
            prepareLiveActivity.finishLive();
        } else {
            prepareLiveActivity.finishLiveOnError();
        }
    }

    private void finishLiveOnError() {
        prepareFinish();
        LiveEntity liveEntity = new LiveEntity();
        liveEntity.avatar = z.a().g();
        liveEntity.userId = z.a().c();
        liveEntity.nickname = z.a().f();
        liveEntity.onlineUserCountPeekValue = String.valueOf(this.onLineCount.get());
        liveEntity.giftIncomeCurrentLive = String.valueOf(this.tempAmount);
        liveEntity.liveId = this.liveId;
        this.anchorLiveEndView.initData(3, liveEntity);
        if (this.wsManager != null) {
            this.wsManager.stopService();
            this.wsManager = null;
        }
    }

    private void setAnchorCoverImg() {
        this.mAnchorCoverImg.setVisibility(0);
        if (this.liveInfoItem == null) {
            i.b(this.mContext, this.mAnchorCoverImg, R.drawable.fq_shape_default_cover_bg);
        } else {
            i.c(this.mContext, this.mAnchorCoverImg, TextUtils.isEmpty(this.liveInfoItem.liveCoverUrl) ? this.liveInfoItem.avatar : this.liveInfoItem.liveCoverUrl, R.drawable.fq_shape_default_cover_bg);
        }
    }

    private void startTokenDialogService() {
        com.tomatolive.library.utils.b.a(this.mContext, TokenDialogService.class);
    }

    private void closePusher() {
        if (this.mInputTextMsgDialog != null && this.mInputTextMsgDialog.isShowing()) {
            this.mInputTextMsgDialog.dismiss();
        }
        stopStream();
    }

    private void prepareFinish() {
        dismissDialogs();
        setAnchorCoverImg();
        this.anchorLiveEndView.setVisibility(0);
        if (this.pushManager != null) {
            this.pushManager.b();
        }
        this.rlLiveRoot.setVisibility(4);
        this.mStickerAddView.setVisibility(4);
        this.mPreStartLiveView.setVisibility(4);
        closePusher();
    }

    private void finishLive() {
        if (o.a() && this.wsManager != null) {
            this.wsManager.sendLeaveMessage(new SendMessageEntity());
            m.b("finishLive==> sendLeaveMessage");
        }
        ((PrePareLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(this.liveId, ConnectSocketParams.MESSAGE_LEAVE_TYPE);
        prepareFinish();
        ((PrePareLivePresenter) this.mPresenter).getLiveEndInfo(this.liveId, this.streamName, 1);
    }

    private void dismissDialogs() {
        dismissUserAvatarDialog();
        if (this.anchorAvatarDialog != null && this.anchorAvatarDialog.isAdded()) {
            this.anchorAvatarDialog.dismiss();
        }
        if (this.endWarnDialog != null && this.endWarnDialog.isAdded()) {
            this.endWarnDialog.dismiss();
        }
        if (this.startWarnDialog != null && this.startWarnDialog.isAdded()) {
            this.startWarnDialog.dismiss();
        }
        if (this.bottomMoreDialog != null && this.bottomMoreDialog.isShowing()) {
            this.bottomMoreDialog.dismiss();
        }
        if (this.beautyDialog != null) {
            if (this.beautyDialog.isResumed()) {
                this.beautyDialog.dismiss();
            }
            this.beautyDialog = null;
        }
    }

    public void onBackPressed() {
        if (this.isLiving) {
            BottomDialogUtils.showBottomPromptDialog(this.mContext, getString(R.string.fq_sure_finish_live), new BottomPromptMenuListener() {
                public void onCancel() {
                }

                public void onSure() {
                    PrepareLiveActivity.this.finishLive();
                }
            });
        } else {
            finish();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        cancelPausedTimer();
        k.a().b();
        stopSocket();
        clearAllMapData();
        if (this.pushManager != null) {
            this.pushManager.i();
            this.pushManager = null;
        }
        if (this.liveAnimationView != null) {
            this.liveAnimationView.onDestroy();
        }
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.onDestroy();
        }
        if (this.countDownAnimSet != null) {
            this.countDownAnimSet.cancel();
        }
        if (this.mainHandler != null) {
            this.mainHandler.removeCallbacksAndMessages(null);
            this.mainHandler = null;
        }
        if (this.workHandler != null) {
            this.workHandler.removeCallbacksAndMessages(null);
            this.workHandler = null;
        }
        if (this.mGiftBoxView != null) {
            this.mGiftBoxView.release();
        }
        if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.onDestroy();
            this.mInputTextMsgDialog = null;
        }
        super.onDestroy();
    }

    private void clearAllMapData() {
        if (this.banedPostList != null) {
            this.banedPostList.clear();
            this.banedPostList = null;
        }
        if (this.shieldedList != null) {
            this.shieldedList.clear();
            this.shieldedList = null;
        }
        if (this.receiveGiftMap != null) {
            this.receiveGiftMap.clear();
            this.receiveGiftMap = null;
        }
        if (this.receiveMsgQueue != null) {
            this.receiveMsgQueue.clear();
            this.receiveMsgQueue = null;
        }
        if (this.enterMsgQueue != null) {
            this.enterMsgQueue.clear();
            this.enterMsgQueue = null;
        }
        if (this.giftNoticeQueue != null) {
            this.giftNoticeQueue.clear();
            this.giftNoticeQueue = null;
        }
        if (this.sysNoticeQueue != null) {
            this.sysNoticeQueue.clear();
            this.sysNoticeQueue = null;
        }
        if (this.receivePropMap != null) {
            this.receivePropMap.clear();
        }
    }

    private void sendWorkHandlerEmptyMessage(int i) {
        if (this.workHandler != null && !this.workHandler.hasMessages(i)) {
            this.workHandler.sendEmptyMessage(i);
        }
    }

    private void handlerMainPost(Runnable runnable) {
        if (this.mainHandler != null && runnable != null) {
            this.mainHandler.post(runnable);
        }
    }
}
