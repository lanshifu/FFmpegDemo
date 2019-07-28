package com.tomatolive.library.ui.activity.live;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.blankj.utilcode.util.h;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BackpackItemEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.BaseGiftBackpackEntity;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.model.GiftIndexEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.model.LiveEndEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.LiveInitInfoEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.SocketMessageEvent;
import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import com.tomatolive.library.model.db.WatchRecordEntity;
import com.tomatolive.library.model.event.AttentionEvent;
import com.tomatolive.library.model.event.BaseEvent;
import com.tomatolive.library.model.event.LiveTopAttentionEvent;
import com.tomatolive.library.model.event.UpdateBalanceEvent;
import com.tomatolive.library.service.KickDialogService;
import com.tomatolive.library.service.TokenDialogService;
import com.tomatolive.library.ui.interfaces.impl.SimpleAnimatorListener;
import com.tomatolive.library.ui.interfaces.impl.SimpleLivePusherInfoCallback;
import com.tomatolive.library.ui.interfaces.impl.SimpleSVGACallBack;
import com.tomatolive.library.ui.presenter.TomatoLivePresenter;
import com.tomatolive.library.ui.view.custom.GiftBoxView;
import com.tomatolive.library.ui.view.custom.GiftBoxView.OnSendGiftBoxMsgListener;
import com.tomatolive.library.ui.view.custom.GuardOpenDanmuView.OnAnimPlayListener;
import com.tomatolive.library.ui.view.custom.LiveAnimationView;
import com.tomatolive.library.ui.view.custom.LiveAnimationView.OnGiftNotifyCallback;
import com.tomatolive.library.ui.view.custom.LiveChatMsgView;
import com.tomatolive.library.ui.view.custom.LiveEndInfoView;
import com.tomatolive.library.ui.view.custom.LiveEndInfoView.LiveEndClickListener;
import com.tomatolive.library.ui.view.custom.LiveLoadingView;
import com.tomatolive.library.ui.view.custom.LiveLoadingView.OnLiveLoadingListener;
import com.tomatolive.library.ui.view.custom.LivePusherInfoView;
import com.tomatolive.library.ui.view.custom.TaskBoxView;
import com.tomatolive.library.ui.view.custom.TaskBoxView.OnRefreshTaskListener;
import com.tomatolive.library.ui.view.dialog.AnchorAvatarDialog;
import com.tomatolive.library.ui.view.dialog.BottomDialogUtils;
import com.tomatolive.library.ui.view.dialog.ChatTipDialog;
import com.tomatolive.library.ui.view.dialog.GiftBackpackDialog;
import com.tomatolive.library.ui.view.dialog.GiftBackpackDialog.SendClickListener;
import com.tomatolive.library.ui.view.dialog.GiftBoxPresenterDialog;
import com.tomatolive.library.ui.view.dialog.GuardOpenContentDialog.OnOpenGuardCallbackListener;
import com.tomatolive.library.ui.view.dialog.GuardOpenTipsDialog;
import com.tomatolive.library.ui.view.dialog.InputTextMsgDialog;
import com.tomatolive.library.ui.view.dialog.InputTextMsgDialog.OnTextSendListener;
import com.tomatolive.library.ui.view.dialog.LiveActionBottomDialog;
import com.tomatolive.library.ui.view.dialog.NetworkPromptDialog;
import com.tomatolive.library.ui.view.dialog.RechargeDialog;
import com.tomatolive.library.ui.view.dialog.SureCancelDialog;
import com.tomatolive.library.ui.view.dialog.TaskBottomDialog;
import com.tomatolive.library.ui.view.dialog.TransDialog;
import com.tomatolive.library.ui.view.dialog.UserAvatarDialog;
import com.tomatolive.library.ui.view.dialog.UserGuardAvatarDialog;
import com.tomatolive.library.ui.view.dialog.UserSuperAvatarDialog;
import com.tomatolive.library.ui.view.dialog.WarnDialog;
import com.tomatolive.library.ui.view.dialog.WebViewDialog;
import com.tomatolive.library.ui.view.gift.GiftAnimModel;
import com.tomatolive.library.ui.view.gift.GiftFrameLayout.BarrageEndAnimationListener;
import com.tomatolive.library.ui.view.iview.ITomatoLiveView;
import com.tomatolive.library.ui.view.task.TaskConstance;
import com.tomatolive.library.utils.d;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.k;
import com.tomatolive.library.utils.m;
import com.tomatolive.library.utils.o;
import com.tomatolive.library.utils.p;
import com.tomatolive.library.utils.q;
import com.tomatolive.library.utils.r;
import com.tomatolive.library.utils.v;
import com.tomatolive.library.utils.x;
import com.tomatolive.library.utils.y;
import com.tomatolive.library.utils.z;
import com.tomatolive.library.websocket.interfaces.BackgroundSocketCallBack;
import com.tomatolive.library.websocket.interfaces.OnWebSocketStatusListener;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import com.tomatolive.library.websocket.nvwebsocket.MessageHelper;
import com.tomatolive.library.websocket.nvwebsocket.WsManager;
import com.tomatolive.library.websocket.nvwebsocket.WsStatus;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sf;
import defpackage.sf.a;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.greenrobot.eventbus.c;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class TomatoLiveActivity extends BaseActivity<TomatoLivePresenter> implements OnOpenGuardCallbackListener, OnTextSendListener, BarrageEndAnimationListener, ITomatoLiveView, BackgroundSocketCallBack {
    private static final long DURATION_GET_MESSAGE = 1000;
    private static final int MAX_GET_ITEM_NUM_ONCE = 5;
    private static final int MAX_ITEM_NUM = 100;
    private static final int POST_INTERVAL_BASE = 3;
    private AnchorAvatarDialog anchorAvatarDialog;
    private String anchorId;
    private String anchorName;
    private double appUserOver = 0.0d;
    private volatile boolean asleep = true;
    private String banPostTimeLeft;
    private volatile List<String> bannedList = new ArrayList();
    private volatile boolean canShowEnterMsg = true;
    private volatile boolean canShowGiftNotice = true;
    private volatile boolean canShowGuardEnterMsg = true;
    private volatile boolean canShowGuardOPenMsg = true;
    private volatile boolean canShowSysNotice = true;
    private volatile boolean carFullAnimFinish = true;
    private String chatContent;
    private LinearLayout chatMsgRoot;
    private AtomicInteger clickCount = new AtomicInteger(0);
    private volatile long countDownTime = ((long) (this.postIntervalTimes * 3));
    private String curBigAnimSendUserId;
    private String enableTranslationLevel = "1";
    private ConcurrentLinkedQueue<ResultData> enterMsgQueue = new ConcurrentLinkedQueue();
    private String entryNoticeLevelThreshold = "10";
    private boolean firstLiveStatus;
    private GiftBackpackDialog giftBottomDialog;
    private View giftButton;
    private ConcurrentLinkedQueue<ResultData> giftNoticeQueue = new ConcurrentLinkedQueue();
    private long giftTrumpetPlayPeriod = 9000;
    private ConcurrentLinkedQueue<ResultData> guardEnterMsgQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<ResultData> guardOpenMsgQueue = new ConcurrentLinkedQueue();
    private int guardType = 0;
    private int heartTime = 30000;
    private volatile boolean isAllBan;
    private boolean isBanGroup;
    private volatile boolean isConnectingChatService = true;
    private boolean isContinue;
    private boolean isFirstEnter = true;
    private boolean isFirstLoadTask = true;
    private String isFollowed;
    private boolean isGetGiftListFail;
    private boolean isGiftUpdate;
    private boolean isLiveEnd;
    private boolean isNoLiveRoom = true;
    private volatile boolean isNormalBan;
    private boolean isOpenDanmu;
    private boolean isPausing;
    private volatile boolean isReConnectStatus;
    private boolean isShowRetryRoomInfo;
    private volatile boolean isSocketClose = true;
    private volatile boolean isSocketError = true;
    private boolean isStartGetAnchorInfo;
    private volatile boolean isStartGetRoomInfo;
    private volatile boolean isSuperBan;
    private boolean isTaskSocket = false;
    private boolean isTranOpen;
    private boolean isUserOverFail;
    private ImageView ivTrans;
    private ImageView ivWarnCover;
    private LiveEndEntity lastLiveEndEntity;
    private String lastMsg;
    private LiveAnimationView liveAnimationView;
    private String liveCount;
    private String liveId;
    private LiveEntity liveInfoItem;
    private ImageView mAnchorCoverImg;
    private DisplayMetrics mDisplayMetrics;
    private GiftBoxView mGiftBoxView;
    private InputTextMsgDialog mInputTextMsgDialog;
    private LiveChatMsgView mLiveChatMsgView;
    private LiveEndInfoView mLiveEndInfoView;
    private LiveLoadingView mLiveLoadingView;
    private LivePusherInfoView mLivePusherInfoView;
    private RelativeLayout mRlControllerView;
    private TaskBottomDialog mTaskBottomDialog;
    private TaskBoxView mTaskBoxView;
    private Handler mainHandler;
    private volatile Map<String, GiftIndexEntity> myGiftIndexMap = new HashMap();
    private volatile Map<String, GiftIndexEntity> myPropIndexMap = new HashMap();
    private String myRole = ConnectSocketParams.EFFECT_TYPE_BIG;
    private AtomicLong onLineCount = new AtomicLong(0);
    private String otherLiveId = "";
    private sf playManager;
    private int postIntervalTimes = 1;
    private List<String> pullStreamUrlList = new ArrayList();
    private b pullTimeOutTimer;
    private volatile boolean reConnectCountOver;
    private AtomicInteger reCount = new AtomicInteger(3);
    private volatile Map<String, Map<String, GiftIndexEntity>> receiveGiftMap = new HashMap();
    private ConcurrentLinkedQueue<ChatEntity> receiveMsgQueue = new ConcurrentLinkedQueue();
    private Map<String, Map<String, GiftIndexEntity>> receivePropMap = new HashMap();
    private RelativeLayout rlBottomGift;
    private RelativeLayout rootView;
    private String rtmpUrl;
    private volatile List<String> shieldedList = new ArrayList();
    private String socketUrl;
    private String speakLevel = "1";
    private boolean startGetGiftListInfo;
    private v swipeAnimationController;
    private ConcurrentLinkedQueue<ResultData> sysNoticeQueue = new ConcurrentLinkedQueue();
    private ResultData tempSysNoticeResultData;
    private TransDialog transDialog;
    private TextView tvInput;
    private TextView tvLiveTitle;
    private UserAvatarDialog userAvatarDialog;
    private String userGrade = "1";
    private UserGuardAvatarDialog userGuardAvatarDialog;
    private String userId;
    private UserSuperAvatarDialog userSuperAvatarDialog;
    private Callback workCallBack = new -$$Lambda$TomatoLiveActivity$zAd2szjTy9u9XrfNuf6oxnHtiQQ(this);
    private Handler workHandler;
    private WsManager wsManager;

    public void OnOpenGuardFail() {
    }

    public void onLiveAdListFail(String str) {
    }

    public void onPersonalGuardInfoFail() {
    }

    public void onResultError(int i) {
    }

    public void onStartAnimation(GiftAnimModel giftAnimModel) {
    }

    public void onTaskListFail() {
    }

    public void onTaskTakeFail() {
    }

    /* Access modifiers changed, original: protected */
    public TomatoLivePresenter createPresenter() {
        return new TomatoLivePresenter(this.mContext, this);
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_tomato_live;
    }

    /* Access modifiers changed, original: protected */
    public void initImmersionBar() {
        super.initImmersionBar();
        this.mImmersionBar.a(false).b();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.hideNoticeViews();
        }
        onReleaseViewData();
        resetAllField();
        this.liveInfoItem = (LiveEntity) intent.getSerializableExtra("resultItem");
        initView(intent.getExtras());
        initListener();
        super.onNewIntent(intent);
    }

    private void resetAllField() {
        this.firstLiveStatus = false;
        this.canShowGiftNotice = true;
        this.canShowEnterMsg = true;
        this.canShowGuardEnterMsg = true;
        this.canShowGuardOPenMsg = true;
        this.canShowSysNotice = true;
        this.carFullAnimFinish = true;
        this.isPausing = false;
        this.isLiveEnd = false;
        this.isNoLiveRoom = true;
        this.isNormalBan = false;
        this.isAllBan = false;
        this.isSuperBan = false;
        this.reConnectCountOver = false;
        this.isStartGetAnchorInfo = false;
        this.isStartGetRoomInfo = false;
        this.isGiftUpdate = false;
        this.isUserOverFail = false;
        this.isGetGiftListFail = false;
        this.startGetGiftListInfo = false;
        this.isFirstEnter = true;
        this.isSocketClose = true;
        this.isSocketError = true;
        this.isContinue = false;
        this.isReConnectStatus = false;
        if (this.mLiveChatMsgView != null) {
            this.mLiveChatMsgView.clearChatMsg();
        }
        dismissDialogs();
    }

    public void onCreate(@Nullable Bundle bundle) {
        this.liveInfoItem = (LiveEntity) getIntent().getSerializableExtra("resultItem");
        super.onCreate(bundle);
    }

    public void initView(Bundle bundle) {
        this.workHandler = k.a().a(getClass().getName(), this.workCallBack);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.mDisplayMetrics);
        initControlView();
        initChatList();
        initInputChat();
        initGiftDownloadData();
        if (this.liveInfoItem != null) {
            this.isStartGetRoomInfo = true;
            ((TomatoLivePresenter) this.mPresenter).getLiveInitInfo(this.liveInfoItem.liveId, ConnectSocketParams.EFFECT_TYPE_BIG, "0");
        }
    }

    private void initControlView() {
        this.rootView = (RelativeLayout) findViewById(R.id.rl_play_root);
        this.ivTrans = (ImageView) findViewById(R.id.iv_trans);
        this.tvLiveTitle = (TextView) findViewById(R.id.tv_live_title);
        this.mLivePusherInfoView = (LivePusherInfoView) findViewById(R.id.ll_pusher_info);
        this.mRlControllerView = (RelativeLayout) findViewById(R.id.rl_control_layout);
        this.mAnchorCoverImg = (ImageView) findViewById(R.id.iv_anchor_cover);
        this.ivWarnCover = (ImageView) findViewById(R.id.iv_warn_push);
        this.mLiveLoadingView = (LiveLoadingView) findViewById(R.id.live_loading_view);
        this.mLiveEndInfoView = (LiveEndInfoView) findViewById(R.id.live_end_view);
        this.rlBottomGift = (RelativeLayout) findViewById(R.id.rl_bottom_gift);
        this.liveAnimationView = (LiveAnimationView) findViewById(R.id.live_anim_view);
        this.ivTrans.setImageResource(R.drawable.fq_icon_translate_deflaut);
        this.giftButton = findViewById(R.id.iv_gift);
        this.mTaskBoxView = (TaskBoxView) findViewById(R.id.task_box_view);
        this.mGiftBoxView = (GiftBoxView) findViewById(R.id.gift_box_view);
        this.playManager = new sf(this.mContext, this.rootView);
        showLoadingAnim();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void initListener() {
        if (this.playManager != null) {
            this.playManager.setOnPlayListener(new a() {
                public void onStartBuffering() {
                    TomatoLiveActivity.this.showLoadingAnim();
                    TomatoLiveActivity.this.startPullTimeOut();
                }

                public void onEndBuffering() {
                    TomatoLiveActivity.this.cancelPullTimeOut();
                    TomatoLiveActivity.this.reCount.set(3);
                    TomatoLiveActivity.this.showLiveLoadingView(4);
                    TomatoLiveActivity.this.mAnchorCoverImg.setVisibility(4);
                }

                public void onPlaySuccess() {
                    TomatoLiveActivity.this.hideLoadingAnim();
                    TomatoLiveActivity.this.mAnchorCoverImg.setVisibility(4);
                }

                public void onPlayError() {
                    TomatoLiveActivity.this.dealPlayError();
                }

                public void onNetError() {
                    if (TomatoLiveActivity.this.reCount.get() > 0) {
                        TomatoLiveActivity.this.reCount.decrementAndGet();
                        return;
                    }
                    TomatoLiveActivity.this.reCount.set(3);
                    TomatoLiveActivity.this.dealPlayError();
                }
            });
        }
        this.rootView.setOnTouchListener(new -$$Lambda$TomatoLiveActivity$YykXof6XYrGY433w4T9LTJzssoE(this));
        this.mLivePusherInfoView.setGiftAnimListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                TomatoLiveActivity.this.canShowGiftNotice = false;
            }

            public void onAnimationEnd(Animator animator) {
                TomatoLiveActivity.this.canShowGiftNotice = true;
            }

            public void onAnimationCancel(Animator animator) {
                TomatoLiveActivity.this.canShowGiftNotice = true;
            }
        });
        this.mLivePusherInfoView.setSysNoticeAnimListener(new SimpleAnimatorListener() {
            public void onAnimationStart(Animator animator) {
                TomatoLiveActivity.this.canShowSysNotice = false;
            }

            public void onAnimationEnd(Animator animator) {
                TomatoLiveActivity.this.canShowSysNotice = true;
            }

            public void onAnimationCancel(Animator animator) {
                TomatoLiveActivity.this.canShowSysNotice = true;
            }
        });
        this.mLivePusherInfoView.initLivePusherInfoCallback(2, getSupportFragmentManager(), new SimpleLivePusherInfoCallback() {
            public void onClickAnchorAvatarListener(View view) {
                if (!TomatoLiveActivity.this.isStartGetAnchorInfo) {
                    ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getAnchorInfo(TomatoLiveActivity.this.anchorId);
                    TomatoLiveActivity.this.isStartGetAnchorInfo = true;
                }
            }

            public void onClickGiftNoticeListener(View view) {
                TomatoLiveActivity.this.startActivityById(TomatoLiveActivity.this.otherLiveId);
            }

            public void onClickSysNoticeListener(View view) {
                if (TomatoLiveActivity.this.tempSysNoticeResultData != null) {
                    String str = TomatoLiveActivity.this.tempSysNoticeResultData.id;
                    String str2 = TomatoLiveActivity.this.tempSysNoticeResultData.clickEvent;
                    String str3 = TomatoLiveActivity.this.tempSysNoticeResultData.forwardText;
                    Object obj = -1;
                    int hashCode = str2.hashCode();
                    if (hashCode != -867683742) {
                        if (hashCode != -370076372) {
                            if (hashCode == -289024721 && str2.equals("forwardToURL")) {
                                obj = 1;
                            }
                        } else if (str2.equals("forwardToLive")) {
                            obj = null;
                        }
                    } else if (str2.equals("readOnly")) {
                        obj = 2;
                    }
                    switch (obj) {
                        case null:
                            ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getBroadcastClick(str);
                            q.a().a(TomatoLiveActivity.this.mContext, 500, TimeUnit.MILLISECONDS, new -$$Lambda$TomatoLiveActivity$4$qjY6JkVT6p6w4sSlXUxy9-vUzsc(this, str3));
                            break;
                        case 1:
                            WebViewDialog.newInstance("", str3).show(TomatoLiveActivity.this.getSupportFragmentManager());
                            ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getBroadcastClick(str);
                            break;
                    }
                }
            }

            public void onFollowAnchorListener(View view) {
                if (com.tomatolive.library.utils.b.a(TomatoLiveActivity.this.mContext, TomatoLiveActivity.this.anchorId)) {
                    int i = 1;
                    int isSelected = view.isSelected() ^ 1;
                    view.setSelected(isSelected);
                    TomatoLiveActivity.this.isFollowed = isSelected != 0 ? "1" : "0";
                    TomatoLiveActivity.this.showToast(isSelected != 0 ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
                    TomatoLivePresenter tomatoLivePresenter = (TomatoLivePresenter) TomatoLiveActivity.this.mPresenter;
                    String access$1100 = TomatoLiveActivity.this.anchorId;
                    if (isSelected == 0) {
                        i = 0;
                    }
                    tomatoLivePresenter.attentionAnchor(access$1100, i);
                }
            }

            public void onClickUserAvatarListener(UserEntity userEntity) {
                if (TextUtils.equals(userEntity.getUserId(), z.a().c())) {
                    TomatoLiveActivity.this.showUserAvatarDialog(userEntity.getAvatar(), userEntity.getName(), userEntity.getUserId(), userEntity.getSex(), "", userEntity.getExpGrade(), TomatoLiveActivity.this.guardType, userEntity.getRole(), false);
                    return;
                }
                if (!TextUtils.equals(userEntity.getUserId(), TomatoLiveActivity.this.anchorId)) {
                    TomatoLiveActivity.this.showUserAvatarDialog(userEntity.getAvatar(), userEntity.getName(), userEntity.getUserId(), userEntity.getSex(), "", userEntity.getExpGrade(), userEntity.getGuardType(), userEntity.getRole(), true);
                } else if (!TomatoLiveActivity.this.isStartGetAnchorInfo) {
                    ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getAnchorInfo(userEntity.getUserId());
                    TomatoLiveActivity.this.isStartGetAnchorInfo = true;
                }
            }
        });
        this.mLivePusherInfoView.setOpenGuardCallbackListener(this);
        this.swipeAnimationController = new v(this);
        this.swipeAnimationController.a(this.mRlControllerView);
        this.transDialog = TransDialog.newInstance(new -$$Lambda$TomatoLiveActivity$zJk1JjWLhx6usCrAhtvMcfXD1Lg(this));
        r.a().a(this.ivTrans, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$TomatoLiveActivity$iVW3uRbILX5IMDwXrL2yPHfXTYM(this));
        r.a().a(this.giftButton, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$TomatoLiveActivity$RNUMZewChDqBPsc5bVXCkqXu07s(this));
        r.a().a(findViewById(R.id.iv_shrink), CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$TomatoLiveActivity$4bmYLSe-i9OMi-A_-NUZX_8Ss5s(this));
        this.mLiveLoadingView.setOnLiveLoadingListener(new OnLiveLoadingListener() {
            public void onReloadClickListener(int i) {
                switch (i) {
                    case 1:
                        if (o.c() != -1) {
                            TomatoLiveActivity.this.showLoadingAnim();
                            TomatoLiveActivity.this.isStartGetRoomInfo = true;
                            ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getLiveInitInfo(TomatoLiveActivity.this.liveInfoItem.liveId, ConnectSocketParams.EFFECT_TYPE_BIG, "0");
                            break;
                        }
                        TomatoLiveActivity.this.showToast(TomatoLiveActivity.this.getResources().getString(R.string.fq_text_no_network));
                        return;
                    case 2:
                        TomatoLiveActivity.this.changeLineReloadLoading(-1);
                        break;
                }
            }

            public void onChangeLineClickListener(int i) {
                switch (i) {
                    case 1:
                        TomatoLiveActivity.this.changeLineReloadLoading(0);
                        return;
                    case 2:
                        TomatoLiveActivity.this.changeLineReloadLoading(1);
                        return;
                    case 3:
                        TomatoLiveActivity.this.changeLineReloadLoading(2);
                        return;
                    default:
                        return;
                }
            }
        });
        this.liveAnimationView.setAnimationCallback(new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                TomatoLiveActivity.this.liveAnimationView.setGiftAnimViewVisibility(4);
                TomatoLiveActivity.this.wsManagerNotifyBigAnim();
            }
        }, new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                TomatoLiveActivity.this.liveAnimationView.setGuardEnterAnimViewVisibility(4);
                TomatoLiveActivity.this.canShowGuardEnterMsg = true;
            }
        }, new OnGiftNotifyCallback() {
            public void onGiftNotifyListener() {
                TomatoLiveActivity.this.wsManagerNotifyBigAnim();
            }

            public void onGiftDeleteListener(GiftAnimModel giftAnimModel) {
                if (!TextUtils.equals(giftAnimModel.getSendUserId(), TomatoLiveActivity.this.userId)) {
                    return;
                }
                if (giftAnimModel.isProp) {
                    ((GiftIndexEntity) TomatoLiveActivity.this.myPropIndexMap.get(giftAnimModel.getGiftTypeId())).countDownStartTime = System.currentTimeMillis();
                    return;
                }
                ((GiftIndexEntity) TomatoLiveActivity.this.myGiftIndexMap.get(giftAnimModel.getGiftTypeId())).countDownStartTime = System.currentTimeMillis();
            }
        }, new SimpleSVGACallBack() {
            public void onFinished() {
                super.onFinished();
                TomatoLiveActivity.this.carFullAnimFinish = true;
            }
        });
        this.mLiveEndInfoView.setLiveEndClickListener(new LiveEndClickListener() {
            public void onAttentionClick(View view) {
                if (com.tomatolive.library.utils.b.a(TomatoLiveActivity.this.mContext, TomatoLiveActivity.this.anchorId)) {
                    int i = 1;
                    int isSelected = view.isSelected() ^ 1;
                    view.setSelected(isSelected);
                    TomatoLiveActivity.this.showToast(isSelected != 0 ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
                    if (TomatoLiveActivity.this.mLiveEndInfoView != null) {
                        TomatoLiveActivity.this.mLiveEndInfoView.setTvAttentionText(isSelected);
                    }
                    TomatoLiveActivity.this.isFollowed = isSelected != 0 ? "1" : "0";
                    TomatoLivePresenter tomatoLivePresenter = (TomatoLivePresenter) TomatoLiveActivity.this.mPresenter;
                    String access$1100 = TomatoLiveActivity.this.anchorId;
                    if (isSelected == 0) {
                        i = 0;
                    }
                    tomatoLivePresenter.attentionAnchor(access$1100, i);
                }
            }

            public void onGoHomeClick() {
                TomatoLiveActivity.this.stopSocket();
                TomatoLiveActivity.this.finish();
            }

            public void onNavBackClick() {
                TomatoLiveActivity.this.mLiveEndInfoView.setVisibility(4);
                TomatoLiveActivity.this.mRlControllerView.setVisibility(0);
                if (!TomatoLiveActivity.this.isNoLiveRoom) {
                    TomatoLiveActivity.this.initPlayer();
                } else if (TomatoLiveActivity.this.liveInfoItem != null) {
                    TomatoLiveActivity.this.goToLive();
                }
            }
        });
        this.mGiftBoxView.setOnSendGiftBoxMsgListener(new OnSendGiftBoxMsgListener() {
            public void onSendGiftBoxMsg(GiftBoxEntity giftBoxEntity) {
                if (TomatoLiveActivity.this.wsManager != null && TomatoLiveActivity.this.wsManager.getSocketStatus() == WsStatus.CONNECT_SUCCESS) {
                    TomatoLiveActivity.this.wsManager.sendGrabGiftBoxMessage(MessageHelper.convertToGrabGiftBoxMsg(giftBoxEntity.giftBoxUniqueCode));
                }
            }

            public void onShowDialog(GiftBoxEntity giftBoxEntity) {
                GiftBoxPresenterDialog.newInstance(giftBoxEntity.presenterAvatar, giftBoxEntity.presenterName).show(TomatoLiveActivity.this.getSupportFragmentManager());
            }
        });
        this.mTaskBoxView.setOnRefreshTaskListener(new OnRefreshTaskListener() {
            public void onRefreshTask(final TaskBoxEntity taskBoxEntity, Long l) {
                String access$4500 = TomatoLiveActivity.this.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("aLong2 = ");
                stringBuilder.append(l);
                Log.i(access$4500, stringBuilder.toString());
                TomatoLiveActivity.this.mTaskBottomDialog.updateSingleData(taskBoxEntity);
                if (l.longValue() != 0) {
                    return;
                }
                if (TomatoLiveActivity.this.isSocketError) {
                    TomatoLiveActivity.this.mTaskBoxView.release();
                    TomatoLiveActivity.this.isTaskSocket = true;
                    io.reactivex.k.timer(3, TimeUnit.SECONDS).compose(TomatoLiveActivity.this.bindToLifecycle()).observeOn(wd.a()).subscribe(new io.reactivex.r<Long>() {
                        public void onError(Throwable th) {
                        }

                        public void onNext(Long l) {
                        }

                        public void onSubscribe(b bVar) {
                        }

                        public void onComplete() {
                            TomatoLiveActivity.this.taskChangeFail(taskBoxEntity);
                        }
                    });
                    return;
                }
                TomatoLiveActivity.this.isTaskSocket = false;
                ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).changeTaskState(taskBoxEntity);
            }

            public void onShowDialog() {
                TomatoLiveActivity.this.showTaskPanel();
            }
        });
    }

    public static /* synthetic */ void lambda$initListener$1(TomatoLiveActivity tomatoLiveActivity, View view) {
        tomatoLiveActivity.isTranOpen = true;
        tomatoLiveActivity.ivTrans.setImageResource(R.drawable.fq_icon_translate_selected);
    }

    private void goToLive() {
        if (!(this.rlBottomGift == null || this.rlBottomGift.getVisibility() == 0)) {
            this.rlBottomGift.setVisibility(0);
        }
        this.anchorName = this.liveInfoItem.nickname;
        this.userGrade = this.liveInfoItem.audienceExpGrade;
        this.speakLevel = this.liveInfoItem.speakLevel;
        this.userId = z.a().c();
        this.liveId = this.liveInfoItem.liveId;
        this.isBanGroup = this.liveInfoItem.isBanGroup();
        this.tvLiveTitle.setText(getString(R.string.fq_text_live_title, new Object[]{this.liveInfoItem.topic}));
        startHideTitleTimer();
        this.liveInfoItem.isFollowed = this.isFollowed;
        this.mLivePusherInfoView.setVisibility(0);
        this.mLivePusherInfoView.initData(this.liveInfoItem);
        this.mLivePusherInfoView.setFollowed(this.liveInfoItem.isAttention());
        setWarnStatus(this.liveInfoItem.warnStatus);
        this.myRole = this.liveInfoItem.role;
        if (TextUtils.equals(this.userId, this.anchorId)) {
            this.myRole = "1";
        }
        this.onLineCount.set(this.liveInfoItem == null ? 0 : p.b(this.liveInfoItem.popularity));
        if (this.liveInfoItem.banPostList != null) {
            this.bannedList.clear();
            this.bannedList.addAll(this.liveInfoItem.banPostList);
        }
        if (this.liveInfoItem.shieldUserList != null) {
            this.shieldedList.clear();
            this.shieldedList.addAll(this.liveInfoItem.shieldUserList);
        }
        int a = p.a(this.liveInfoItem.postIntervalTimes);
        if (a >= 0) {
            this.postIntervalTimes = a;
        }
        this.isAllBan = this.liveInfoItem.isBanAll();
        this.isSuperBan = this.liveInfoItem.isBanBySuperManager();
        this.isNormalBan = this.liveInfoItem.isBanStatus();
        this.banPostTimeLeft = this.liveInfoItem.banPostTimeLeft;
        if (this.isAllBan && com.tomatolive.library.utils.b.l(this.myRole)) {
            if (this.mInputTextMsgDialog != null) {
                this.mInputTextMsgDialog.setBanedAllPost(getString(R.string.fq_text_input_banned_hint));
                showReceiveMsgOnChatList(new ResultData(), getString(R.string.fq_anchor_start_banned), 5);
            }
        } else if (this.isSuperBan) {
            if (this.mInputTextMsgDialog != null) {
                this.mInputTextMsgDialog.setBandPostBySuperManager();
            }
        } else if (this.isNormalBan) {
            if (this.mInputTextMsgDialog != null) {
                this.mInputTextMsgDialog.setBandPost(d.c(this.banPostTimeLeft));
            }
        } else if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.cancelBandPost();
        }
        if (!this.isReConnectStatus) {
            sendAdImageRequest();
            ((TomatoLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg("enter");
            this.rtmpUrl = this.liveInfoItem.getDefPullStreamUrlStr();
            this.pullStreamUrlList = this.liveInfoItem.getPullStreamUrlList();
            setAnchorCoverImg();
            initPlayer();
            this.socketUrl = com.tomatolive.library.utils.b.a(this.liveInfoItem.wsServerAddress, this.liveId, this.userId, ConnectSocketParams.EFFECT_TYPE_BIG, this.liveInfoItem.k);
            if (TextUtils.isEmpty(this.liveInfoItem.socketHeartBeatInterval)) {
                a = this.heartTime;
            } else {
                a = p.a(this.liveInfoItem.socketHeartBeatInterval) * 1000;
            }
            this.heartTime = a;
            initSocket();
            io.reactivex.k.interval(0, com.tomatolive.library.utils.b.b(this.liveInfoItem), TimeUnit.SECONDS).subscribeOn(xl.b()).observeOn(xl.b()).compose(bindToLifecycle()).subscribe(new sh<Long>() {
                public void accept(Long l) {
                    ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getCurrentOnlineUserList(TomatoLiveActivity.this.liveId);
                }
            });
            io.reactivex.k.interval(0, 5, TimeUnit.SECONDS).subscribeOn(xl.b()).observeOn(xl.b()).compose(bindToLifecycle()).subscribe(new sh<Long>() {
                public void accept(Long l) {
                    ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getLivePopularity(TomatoLiveActivity.this.liveId);
                }
            });
            ((TomatoLivePresenter) this.mPresenter).getGiftBoxList(this.liveId);
        }
    }

    private void goToEnd() {
        dismissDialogs();
        stopPlay();
        this.isLiveEnd = true;
        if (this.mRlControllerView != null) {
            this.mRlControllerView.setVisibility(4);
        }
        this.mTaskBoxView.setVisibility(4);
        showLiveLoadingView(4);
        this.ivWarnCover.setVisibility(4);
        this.mLiveEndInfoView.setVisibility(0);
        setAnchorCoverImg();
        if (this.isNoLiveRoom) {
            this.mLiveEndInfoView.initData(this.lastLiveEndEntity);
        } else {
            ((TomatoLivePresenter) this.mPresenter).getLiveEndInfo(z.a().c(), this.liveId, this.liveCount);
        }
    }

    private void setWarnStatus(String str) {
        if (TextUtils.equals(str, "1")) {
            i.a(this.mContext, this.ivWarnCover, R.drawable.fq_ic_placeholder_anchor_warn);
            this.ivWarnCover.setVisibility(0);
            return;
        }
        this.ivWarnCover.setVisibility(8);
    }

    private void startHideTitleTimer() {
        this.tvLiveTitle.setVisibility(0);
        q.a().c(this.mContext, 10, TimeUnit.SECONDS, new -$$Lambda$TomatoLiveActivity$u1gupsSEU0xS8BZYSp6gCyPg6so(this));
    }

    private void startKickDialogService() {
        com.tomatolive.library.utils.b.a(this.mContext, KickDialogService.class);
    }

    private void startTokenDialogService() {
        com.tomatolive.library.utils.b.a(this.mContext, TokenDialogService.class);
    }

    private void startActivityById(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.equals(str, this.liveId)) {
                showToast(R.string.fq_already_in_room);
                return;
            }
            LiveEntity liveEntity = new LiveEntity();
            liveEntity.liveId = str;
            Intent intent = new Intent(this.mContext, TomatoLiveActivity.class);
            intent.putExtra("resultItem", liveEntity);
            startActivity(intent);
        }
    }

    private void showLoadingAnim() {
        showLiveLoadingView(0);
        this.mLiveLoadingView.showLoadingView();
    }

    private void showReloadButton() {
        showLiveLoadingView(0);
        this.mLiveLoadingView.showReloadView(2);
    }

    private void showRoomInfoReload() {
        this.isShowRetryRoomInfo = true;
        showLiveLoadingView(0);
        this.mLiveLoadingView.showReloadView(1);
    }

    private void hideLoadingAnim() {
        this.mLiveLoadingView.stopLoadingViewAnimation();
    }

    private void showLiveLoadingView(int i) {
        this.mLiveLoadingView.setVisibility(i);
        if (i == 4 || i == 8) {
            hideLoadingAnim();
        }
    }

    private void setAnchorCoverImg() {
        if (this.mAnchorCoverImg.getVisibility() != 0) {
            this.mAnchorCoverImg.setVisibility(0);
        }
        if (this.liveInfoItem == null) {
            if (this.lastLiveEndEntity != null) {
                i.c(this.mContext, this.mAnchorCoverImg, this.lastLiveEndEntity.avatar, R.drawable.fq_shape_default_cover_bg);
            } else {
                i.b(this.mContext, this.mAnchorCoverImg, R.drawable.fq_shape_default_cover_bg);
            }
            return;
        }
        String str = TextUtils.isEmpty(this.liveInfoItem.liveCoverUrl) ? this.liveInfoItem.avatar : this.liveInfoItem.liveCoverUrl;
        if (this.lastLiveEndEntity != null && TextUtils.isEmpty(str)) {
            str = this.lastLiveEndEntity.avatar;
        }
        i.c(this.mContext, this.mAnchorCoverImg, str, R.drawable.fq_shape_default_cover_bg);
    }

    private void goToRecharge() {
        if (com.tomatolive.library.a.a().a != null) {
            SureCancelDialog.newInstance(getString(R.string.fq_text_whether_recharge), new -$$Lambda$TomatoLiveActivity$pwR7EPWQJNOy3atBDktGs5926AA(this)).show(getSupportFragmentManager());
        }
    }

    public static /* synthetic */ void lambda$goToRecharge$6(TomatoLiveActivity tomatoLiveActivity, View view) {
        if (com.tomatolive.library.a.a().a != null) {
            com.tomatolive.library.a.a().a.a(tomatoLiveActivity.mContext);
        }
    }

    public void onGiftListSuccess(List<GiftDownloadItemEntity> list) {
        if (this.workHandler != null) {
            this.workHandler.post(new -$$Lambda$TomatoLiveActivity$qcGVXE0xpElFSGji1vH0waKbwcI(list));
        }
        this.isGiftUpdate = false;
        this.isGetGiftListFail = false;
        this.startGetGiftListInfo = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("直播间接口返回礼物列表：");
        stringBuilder.append(list);
        m.a(stringBuilder.toString());
        initGiftDialog(list);
    }

    public void onGiftListFail() {
        showToast(R.string.fq_gift_fail);
        this.isGetGiftListFail = true;
        this.isGiftUpdate = false;
        this.startGetGiftListInfo = false;
    }

    public void onLiveEndInfoSuccess(LiveEndEntity liveEndEntity) {
        this.lastLiveEndEntity = liveEndEntity;
        if (TextUtils.isEmpty(this.lastLiveEndEntity.nickname)) {
            this.lastLiveEndEntity.nickname = this.anchorName;
        }
        this.lastLiveEndEntity.isFollowed = this.isFollowed;
        this.mLiveEndInfoView.initData(this.lastLiveEndEntity);
    }

    public void onLiveEndInfoFail() {
        if (!this.isNoLiveRoom) {
            ((TomatoLivePresenter) this.mPresenter).getLiveEndInfo(z.a().c(), this.liveId, this.liveCount);
        }
    }

    public void onAttentionSuccess() {
        c.a().d(new AttentionEvent());
        c.a().d(new LiveTopAttentionEvent());
    }

    public void onAnchorInfoSuccess(AnchorEntity anchorEntity) {
        anchorEntity.followStatus = this.mLivePusherInfoView.isFollowed() ? "1" : "0";
        this.anchorAvatarDialog = AnchorAvatarDialog.newInstance(anchorEntity, 2, new -$$Lambda$TomatoLiveActivity$vyWfcslIWtyk5cNq3OERk8qdN1c(this, anchorEntity));
        this.anchorAvatarDialog.show(getSupportFragmentManager());
        this.isStartGetAnchorInfo = false;
    }

    public static /* synthetic */ void lambda$onAnchorInfoSuccess$8(TomatoLiveActivity tomatoLiveActivity, AnchorEntity anchorEntity, View view) {
        if (com.tomatolive.library.utils.b.a(tomatoLiveActivity.mContext, anchorEntity.userId)) {
            int i = 1;
            int isSelected = view.isSelected() ^ 1;
            view.setSelected(isSelected);
            tomatoLiveActivity.showToast(isSelected != 0 ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
            tomatoLiveActivity.mLivePusherInfoView.setFollowed(isSelected);
            anchorEntity.followStatus = isSelected != 0 ? "1" : "0";
            tomatoLiveActivity.isFollowed = anchorEntity.followStatus;
            TomatoLivePresenter tomatoLivePresenter = (TomatoLivePresenter) tomatoLiveActivity.mPresenter;
            String str = tomatoLiveActivity.anchorId;
            if (isSelected == 0) {
                i = 0;
            }
            tomatoLivePresenter.attentionAnchor(str, i);
        }
    }

    public void onAnchorInfoFail(int i, String str) {
        this.isStartGetAnchorInfo = false;
    }

    public void onUserOverSuccess(String str) {
        this.isUserOverFail = false;
        if (!TextUtils.isEmpty(str)) {
            this.appUserOver = p.c(str);
            if (this.giftBottomDialog != null) {
                this.giftBottomDialog.setUserBalance(this.appUserOver);
            }
        }
    }

    public void onUserOverFail() {
        this.isUserOverFail = true;
        if (this.giftBottomDialog != null) {
            this.giftBottomDialog.setUserBalanceTip(R.string.fq_userover_loading_fail);
        }
    }

    public void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity) {
        if (onLineUsersEntity != null && onLineUsersEntity.getUserEntityList() != null) {
            this.mLivePusherInfoView.replaceData(onLineUsersEntity.getUserEntityList());
        }
    }

    public void onPersonalGuardInfoSuccess(GuardItemEntity guardItemEntity) {
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.updateOpenGuardInfo(guardItemEntity);
        }
    }

    public void onWebSocketAddressSuccess(LiveEntity liveEntity) {
        this.socketUrl = com.tomatolive.library.utils.b.a(liveEntity.wsServerAddress, this.liveId, this.userId, ConnectSocketParams.EFFECT_TYPE_BIG, liveEntity.k);
        if (this.wsManager != null) {
            this.wsManager.reconnect(this.socketUrl);
        }
    }

    public void onWebSocketAddressFail() {
        if (h.a()) {
            ((TomatoLivePresenter) this.mPresenter).getWebSocketAddress(this.liveId, ConnectSocketParams.EFFECT_TYPE_BIG, "1");
        } else if (this.wsManager != null) {
            this.wsManager.resetCount();
            this.wsManager.setStatus(WsStatus.CONNECT_FAIL);
            this.isConnectingChatService = false;
            this.reConnectCountOver = true;
            if (this.tvInput != null) {
                this.tvInput.setText(R.string.fq_click_reconnect);
            }
        }
    }

    public void onLivePopularitySuccess(long j) {
        this.onLineCount.set(j);
        this.mLivePusherInfoView.setRoomPerson(this.onLineCount.get());
    }

    public void onTaskListSuccess(List<TaskBoxEntity> list, boolean z) {
        TaskConstance.getInstance().clearList();
        TaskConstance.getInstance().setmData(list);
        this.mTaskBottomDialog.setmData(list);
        this.mTaskBoxView.refreshTaskButton();
    }

    public void onTaskTakeSuccess(TaskBoxEntity taskBoxEntity) {
        taskBoxEntity.setStatus(2);
        this.mTaskBoxView.changeRedCount(false);
        this.mTaskBoxView.checkToCountdown();
        this.mTaskBottomDialog.updateSingleData(taskBoxEntity);
    }

    public void onTaskChangeSuccess(TaskBoxEntity taskBoxEntity) {
        this.mTaskBoxView.changeRedCount(true);
        taskBoxEntity.setStatus(1);
        this.mTaskBottomDialog.updateSingleData(taskBoxEntity);
        List list = TaskConstance.getInstance().getmData();
        int indexOf = list.indexOf(taskBoxEntity);
        int i = indexOf + 1;
        if (i < list.size()) {
            ((TaskBoxEntity) list.get(i)).setStatus(0);
            this.mTaskBottomDialog.updateSingleData((TaskBoxEntity) list.get(indexOf));
        }
        this.mTaskBoxView.checkToCountdown();
    }

    public void onTaskChangeFail(TaskBoxEntity taskBoxEntity) {
        taskChangeFail(taskBoxEntity);
    }

    private void taskChangeFail(TaskBoxEntity taskBoxEntity) {
        taskBoxEntity.setOpenTime(TaskConstance.getInstance().getOpentime());
        this.mTaskBoxView.checkToCountdown();
    }

    public void onGiftBoxListSuccess(List<GiftBoxEntity> list) {
        if (this.mGiftBoxView != null) {
            this.mGiftBoxView.showBoxList(list, this.liveId);
        }
    }

    public void OnOpenGuardSuccess(GuardItemEntity guardItemEntity) {
        this.appUserOver = p.c(guardItemEntity.totResult);
        if (this.giftBottomDialog != null) {
            this.giftBottomDialog.setUserBalance(this.appUserOver);
        }
        ((TomatoLivePresenter) this.mPresenter).getPersonalGuardInfo(this.anchorId);
    }

    public void onLiveInitInfoSuccess(LiveInitInfoEntity liveInitInfoEntity) {
        this.isStartGetRoomInfo = false;
        this.tvLiveTitle.setVisibility(0);
        this.mLivePusherInfoView.setVisibility(0);
        if (liveInitInfoEntity != null) {
            initRoomInfo(liveInitInfoEntity);
        }
    }

    public void onLiveInitInfoFail(int i) {
        this.isStartGetRoomInfo = false;
        this.tvLiveTitle.setVisibility(4);
        this.mLivePusherInfoView.setVisibility(4);
        if (i == 200023) {
            startKickDialogService();
            stopSocket();
            finish();
            return;
        }
        showToast(R.string.fq_live_room_loading_fail_tips);
        setAnchorCoverImg();
        showRoomInfoReload();
    }

    private void sendAdImageRequest() {
        ((TomatoLivePresenter) this.mPresenter).getAdImageList(ConnectSocketParams.EFFECT_TYPE_BIG);
        ((TomatoLivePresenter) this.mPresenter).getAdImageList("3");
        ((TomatoLivePresenter) this.mPresenter).getLiveAdNoticeList();
    }

    public void onLiveAdListSuccess(String str, List<BannerEntity> list) {
        if (TextUtils.equals(str, ConnectSocketParams.EFFECT_TYPE_BIG) && this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.initAdBannerImages(list);
        }
        if (TextUtils.equals(str, "3") && this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.initVerticalAdImage(list);
        }
    }

    public void onLiveAdNoticeSuccess(BannerEntity bannerEntity) {
        if (!TextUtils.isEmpty(bannerEntity.content)) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgType(10);
            chatEntity.setMsgText(bannerEntity.content);
            this.mLiveChatMsgView.addChatMsg(chatEntity);
        }
    }

    private void initRoomInfo(LiveInitInfoEntity liveInitInfoEntity) {
        this.isFollowed = liveInitInfoEntity.isFollowed;
        this.liveInfoItem = liveInitInfoEntity.liveInitInfo;
        this.liveInfoItem.isFollowed = this.isFollowed;
        this.liveCount = this.liveInfoItem.liveCount;
        this.anchorId = this.liveInfoItem.anchorId;
        this.enableTranslationLevel = com.tomatolive.library.utils.b.d(this.liveInfoItem);
        this.entryNoticeLevelThreshold = com.tomatolive.library.utils.b.e(this.liveInfoItem);
        this.mLivePusherInfoView.setLiveRankConfig(this.liveInfoItem.liveRankConfig);
        this.guardType = p.a(this.liveInfoItem.userGuardType);
        this.giftTrumpetPlayPeriod = com.tomatolive.library.utils.b.c(this.liveInfoItem);
        boolean z = this.guardType > 0 && com.blankj.utilcode.util.k.a().d("last_danmu");
        this.isOpenDanmu = z;
        if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.initDanmuOpen(this.isOpenDanmu);
        }
        showLiveLoadingView(4);
        this.firstLiveStatus = com.tomatolive.library.utils.b.a(this.liveInfoItem);
        if (this.firstLiveStatus || this.isReConnectStatus) {
            if (!TextUtils.equals(z.a().c(), this.anchorId) && this.isFirstLoadTask) {
                this.isFirstLoadTask = false;
                this.mTaskBoxView.setVisibility(0);
                initTaskDialog();
            }
            this.isNoLiveRoom = false;
            goToLive();
        } else {
            this.lastLiveEndEntity = liveInitInfoEntity.lastLiveData;
            this.lastLiveEndEntity.isFollowed = this.isFollowed;
            this.isNoLiveRoom = true;
            goToEnd();
        }
        WatchRecordEntity watchRecordEntity = new WatchRecordEntity();
        watchRecordEntity.userId = z.a().c();
        watchRecordEntity.liveId = this.liveInfoItem.liveId;
        watchRecordEntity.coverUrl = this.liveInfoItem.liveCoverUrl;
        watchRecordEntity.label = this.liveInfoItem.tag;
        watchRecordEntity.title = this.liveInfoItem.topic;
        watchRecordEntity.anchorNickname = this.liveInfoItem.nickname;
        watchRecordEntity.liveTime = p.b(this.liveInfoItem.startTime);
        com.tomatolive.library.utils.c.a(watchRecordEntity);
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        rePlay();
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.onResume();
        }
        if (TaskConstance.getInstance().isPushInBackground && this.mTaskBottomDialog != null) {
            this.mTaskBoxView.checkToCountdown();
            TaskConstance.getInstance().isPushInBackground = false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        pausePlay();
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.onPause();
        }
        if (x.a(this)) {
            TaskConstance.getInstance().isPushInBackground = true;
            this.mTaskBoxView.release();
        }
    }

    private void changeLineReloadLoading(int i) {
        if (i != -1) {
            String pullStreamUrl = getPullStreamUrl(i);
            if (!TextUtils.isEmpty(pullStreamUrl)) {
                this.rtmpUrl = pullStreamUrl;
            }
        }
        if (o.c() == -1) {
            showToast(getResources().getString(R.string.fq_text_no_network));
            return;
        }
        showLoadingAnim();
        initPlayer();
    }

    private String getPullStreamUrl(int i) {
        try {
            return (String) this.pullStreamUrlList.get(i);
        } catch (Exception unused) {
            return "";
        }
    }

    private void initPlayer() {
        if (this.playManager != null) {
            this.playManager.a(this.rtmpUrl);
        }
    }

    private void rePlay() {
        if (this.playManager != null) {
            this.playManager.a(this.isPausing);
            this.isPausing = false;
        }
    }

    private void pausePlay() {
        if (this.playManager != null) {
            this.playManager.b();
            this.isPausing = true;
        }
    }

    private void stopPlay() {
        if (this.playManager != null) {
            this.playManager.c();
        }
    }

    private void startPullTimeOut() {
        this.pullTimeOutTimer = io.reactivex.k.timer(10, TimeUnit.SECONDS).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new -$$Lambda$TomatoLiveActivity$nHcyh5xp5HvQlr_lvhgTL7kG004(this));
    }

    public static /* synthetic */ void lambda$startPullTimeOut$9(TomatoLiveActivity tomatoLiveActivity, Long l) throws Exception {
        if (tomatoLiveActivity.playManager != null && tomatoLiveActivity.playManager.a() != null) {
            tomatoLiveActivity.playManager.a().onNetError();
        }
    }

    private void cancelPullTimeOut() {
        if (this.pullTimeOutTimer != null && !this.pullTimeOutTimer.isDisposed()) {
            this.pullTimeOutTimer.dispose();
            this.pullTimeOutTimer = null;
        }
    }

    private void dealPlayError() {
        cancelPullTimeOut();
        stopPlay();
        if (!this.isLiveEnd) {
            showReloadButton();
        }
    }

    public static /* synthetic */ void lambda$onNetChangeListener$10(TomatoLiveActivity tomatoLiveActivity, long j) {
        if (!(o.a() || tomatoLiveActivity.isShowRetryRoomInfo || tomatoLiveActivity.isStartGetRoomInfo)) {
            tomatoLiveActivity.dealPlayError();
            tomatoLiveActivity.showToast(R.string.fq_text_no_network);
        }
    }

    public void onNetChangeListener(int i) {
        switch (i) {
            case -1:
                q.a().c(this, 2, TimeUnit.SECONDS, new -$$Lambda$TomatoLiveActivity$ExHOoxmTcgF_IpB7zg5AUfv4ODM(this));
                return;
            case 0:
                showMobileNetDialog();
                return;
            default:
                return;
        }
    }

    private void showMobileNetDialog() {
        if (!com.blankj.utilcode.util.k.a().b("SHOW_MOBIE_TIP", false)) {
            NetworkPromptDialog.newInstance(getResources().getString(R.string.fq_text_mobile_net), getResources().getString(R.string.fq_text_go_on), getResources().getString(R.string.fq_text_stop), -$$Lambda$TomatoLiveActivity$f9sX27-HbnxD2Az0-dsQN-jhGKY.INSTANCE, new -$$Lambda$TomatoLiveActivity$GffpXx7tm7G2cZ6VWER-d6u5bvw(this)).show(getSupportFragmentManager());
        } else if (!this.hasRemindTraffic) {
            this.hasRemindTraffic = true;
            showToast(R.string.fq_mobile_tip);
        }
    }

    public static /* synthetic */ void lambda$showMobileNetDialog$12(TomatoLiveActivity tomatoLiveActivity, View view) {
        tomatoLiveActivity.stopSocket();
        tomatoLiveActivity.finish();
    }

    public void onEndAnimation(GiftAnimModel giftAnimModel) {
        if (!TextUtils.equals(giftAnimModel.getSendUserId(), this.userId)) {
            return;
        }
        if (giftAnimModel.isProp) {
            ((GiftIndexEntity) this.myPropIndexMap.get(giftAnimModel.getGiftTypeId())).countDownStartTime = System.currentTimeMillis();
            return;
        }
        ((GiftIndexEntity) this.myGiftIndexMap.get(giftAnimModel.getGiftTypeId())).countDownStartTime = System.currentTimeMillis();
    }

    private void playBigAnim(GiftItemEntity giftItemEntity) {
        if (giftItemEntity.isProp) {
            this.liveAnimationView.loadPropAnimation(giftItemEntity.animalUrl);
        } else if (TextUtils.isEmpty(giftItemEntity.giftDirPath) || !g.b(com.tomatolive.library.utils.b.a(giftItemEntity.giftDirPath, giftItemEntity.jsonname))) {
            this.liveAnimationView.loadGiftAnimation(giftItemEntity);
        } else {
            this.liveAnimationView.loadGiftAnimation(giftItemEntity.giftDirPath, giftItemEntity.jsonname);
        }
    }

    public void onBackThreadReceiveBigAnimMsg(GiftItemEntity giftItemEntity) {
        this.curBigAnimSendUserId = giftItemEntity.userId;
        playBigAnim(giftItemEntity);
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

    private void initGiftDownloadData() {
        List localDownloadList = GiftDownLoadManager.getInstance().getLocalDownloadList();
        if (localDownloadList == null || localDownloadList.size() == 0) {
            this.isGetGiftListFail = true;
            return;
        }
        this.isGetGiftListFail = false;
        initGiftDialog(localDownloadList);
    }

    private void initGiftDialog(List<GiftDownloadItemEntity> list) {
        if (this.giftBottomDialog == null) {
            this.giftBottomDialog = GiftBackpackDialog.create(getSupportFragmentManager(), formatGiftList(list), this.appUserOver, new SendClickListener() {
                public void onSendCallback(boolean z, BaseGiftBackpackEntity baseGiftBackpackEntity) {
                    if (z) {
                        if (baseGiftBackpackEntity instanceof GiftDownloadItemEntity) {
                            TomatoLiveActivity.this.sendGift((GiftDownloadItemEntity) baseGiftBackpackEntity);
                        }
                    } else if (baseGiftBackpackEntity instanceof BackpackItemEntity) {
                        TomatoLiveActivity.this.sendProp((BackpackItemEntity) baseGiftBackpackEntity);
                    }
                }

                public void onRechargeCallback(View view) {
                    TomatoLiveActivity.this.goToRecharge();
                }
            });
        } else {
            this.giftBottomDialog.updateGiftList(formatGiftList(list));
        }
    }

    private void sendProp(BackpackItemEntity backpackItemEntity) {
        if (!o.a() || isSocketClose()) {
            showToast(R.string.fq_text_no_network_send_prop);
        } else if (backpackItemEntity == null) {
            showToast(R.string.fq_please_choose_prop);
        } else {
            if (this.wsManager != null) {
                this.wsManager.sendPropSendMessage(MessageHelper.convertToPropSend("1", backpackItemEntity.id));
            }
        }
    }

    private void initTaskDialog() {
        if (this.mTaskBottomDialog == null) {
            this.mTaskBottomDialog = TaskBottomDialog.create(getSupportFragmentManager(), new -$$Lambda$TomatoLiveActivity$aFwehETXn-scIam2YhsP8oNjfdE(this));
        }
        ((TomatoLivePresenter) this.mPresenter).getTaskList(false);
    }

    public static /* synthetic */ void lambda$initTaskDialog$13(TomatoLiveActivity tomatoLiveActivity, TaskBoxEntity taskBoxEntity) {
        if (taskBoxEntity.getStatus() == 1 && !tomatoLiveActivity.isSocketError) {
            ((TomatoLivePresenter) tomatoLiveActivity.mPresenter).getTaskTake(taskBoxEntity);
        }
    }

    private void sendGift(GiftDownloadItemEntity giftDownloadItemEntity) {
        GiftItemEntity formatGiftItemEntity = GiftDownLoadManager.getInstance().formatGiftItemEntity(giftDownloadItemEntity);
        if (!o.a()) {
            showToast(R.string.fq_text_no_network_send_gift);
        } else if (isSocketClose()) {
            showToast(R.string.fq_text_network_error_send_gift);
        } else if (this.isUserOverFail) {
            showToast(R.string.fq_userover_loading);
        } else if (formatGiftItemEntity == null) {
            showToast(R.string.fq_please_choose_gift);
        } else if (!isSendGift(formatGiftItemEntity)) {
            RechargeDialog.newInstance(new -$$Lambda$TomatoLiveActivity$jKzoHnd7TDjk0VCPuE8rfjFtpDo(this)).show(getSupportFragmentManager());
        } else if (GiftDownLoadManager.getInstance().checkGiftExist(formatGiftItemEntity)) {
            String a = h.a(true);
            formatGiftItemEntity.num = 1;
            formatGiftItemEntity.userId = this.userId;
            formatGiftItemEntity.avatar = z.a().g();
            formatGiftItemEntity.role = this.myRole;
            formatGiftItemEntity.anchorId = this.anchorId;
            formatGiftItemEntity.anchorName = this.anchorName;
            formatGiftItemEntity.liveId = this.liveId;
            formatGiftItemEntity.expGrade = this.userGrade;
            formatGiftItemEntity.liveCount = this.liveCount;
            if (TextUtils.isEmpty(a)) {
                a = "172.19.24.10";
            }
            formatGiftItemEntity.clientIp = a;
            formatGiftItemEntity.guardType = this.guardType;
            this.appUserOver -= p.a((double) this.giftBottomDialog.getGiftNum(), p.c(formatGiftItemEntity.tomatoPrice));
            if (this.appUserOver < 0.0d) {
                this.appUserOver = 0.0d;
            }
            this.giftBottomDialog.setUserBalance(this.appUserOver);
            if (this.wsManager != null) {
                this.wsManager.sendGiftMessage(MessageHelper.convertToGiftMsg(formatGiftItemEntity));
            }
        } else {
            showToast(R.string.fq_no_find_anim_file);
            if (!this.isGiftUpdate) {
                this.isGiftUpdate = true;
                ((TomatoLivePresenter) this.mPresenter).getGiftList(-1);
            }
        }
    }

    public static /* synthetic */ void lambda$sendGift$14(TomatoLiveActivity tomatoLiveActivity, View view) {
        if (com.tomatolive.library.a.a().a != null) {
            com.tomatolive.library.a.a().a.a(tomatoLiveActivity.mContext);
        }
    }

    private boolean isSendGift(GiftItemEntity giftItemEntity) {
        if (this.appUserOver >= p.a((double) (this.giftBottomDialog.getGiftNum() == 0 ? 1 : this.giftBottomDialog.getGiftNum()), p.c(giftItemEntity.tomatoPrice))) {
            return true;
        }
        return false;
    }

    private List<GiftDownloadItemEntity> formatGiftList(List<GiftDownloadItemEntity> list) {
        ArrayList arrayList = new ArrayList(list);
        int i = 0;
        if (arrayList.isEmpty()) {
            while (i < 8) {
                arrayList.add(new GiftDownloadItemEntity(getString(R.string.fq_come_soon), "0", true));
                i++;
            }
            return arrayList;
        }
        int size = arrayList.size() % 8;
        if (size <= 0) {
            return arrayList;
        }
        int i2 = 8 - size;
        while (i < i2) {
            arrayList.add(new GiftDownloadItemEntity(getString(R.string.fq_come_soon), "0", true));
            i++;
        }
        return arrayList;
    }

    private void showGiftPanel() {
        com.tomatolive.library.utils.a.a(this.giftButton);
        if (TextUtils.equals(this.userId, this.anchorId)) {
            showToast(R.string.fq_no_send_gift_myself);
        } else if (this.isGetGiftListFail) {
            showToast(R.string.fq_gift_loading);
            if (!this.startGetGiftListInfo) {
                this.startGetGiftListInfo = true;
                ((TomatoLivePresenter) this.mPresenter).getGiftList(-1);
            }
        } else if (this.isGiftUpdate) {
            showToast(R.string.fq_gift_res_update);
        } else if (this.giftBottomDialog != null) {
            if (this.isFirstEnter) {
                this.isFirstEnter = false;
                this.giftBottomDialog.setUserBalanceTip(R.string.fq_userover_loading);
                ((TomatoLivePresenter) this.mPresenter).getUserOver();
            } else if (this.isUserOverFail) {
                this.giftBottomDialog.setUserBalanceTip(R.string.fq_userover_loading);
                ((TomatoLivePresenter) this.mPresenter).getUserOver();
            }
            if (!(this.giftBottomDialog == null || this.giftBottomDialog.isAdded())) {
                this.giftBottomDialog.show();
            }
        }
    }

    private void showTaskPanel() {
        if (this.mTaskBottomDialog != null && !this.mTaskBottomDialog.isAdded()) {
            this.mTaskBottomDialog.show();
        }
    }

    private void playMySelfAnimOnMainThread(ResultData resultData) {
        GiftItemEntity giftItemEntity = GiftDownLoadManager.getInstance().getGiftItemEntity(resultData.giftId);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("接受礼物：");
        stringBuilder.append(giftItemEntity);
        m.a(stringBuilder.toString());
        if (giftItemEntity == null) {
            if (!this.isGiftUpdate) {
                this.isGiftUpdate = true;
                ((TomatoLivePresenter) this.mPresenter).getGiftList(-1);
            }
            return;
        }
        GiftIndexEntity giftIndexEntity = (GiftIndexEntity) this.myGiftIndexMap.get(giftItemEntity.typeid);
        if (giftIndexEntity == null) {
            giftIndexEntity = new GiftIndexEntity();
            giftIndexEntity.sendIndex++;
            this.isContinue = false;
            this.myGiftIndexMap.put(giftItemEntity.typeid, giftIndexEntity);
        } else if (giftIndexEntity.countDownStartTime == 0) {
            giftIndexEntity.sendIndex++;
            this.isContinue = false;
        } else if (System.currentTimeMillis() - giftIndexEntity.countDownStartTime > ((long) p.a(giftItemEntity.active_time))) {
            giftIndexEntity.sendIndex = 1;
            giftIndexEntity.countDownStartTime = 0;
            this.isContinue = false;
        } else {
            giftIndexEntity.sendIndex++;
            this.isContinue = true;
            giftIndexEntity.countDownStartTime = 0;
        }
        giftItemEntity.sendUserName = resultData.userName;
        giftItemEntity.userId = resultData.userId;
        giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
        giftItemEntity.effect_type = p.a(resultData.effectType);
        giftItemEntity.avatar = resultData.avatar;
        giftItemEntity.role = resultData.role;
        giftItemEntity.sex = resultData.sex;
        giftItemEntity.expGrade = com.tomatolive.library.utils.b.h(resultData.expGrade);
        giftItemEntity.guardType = p.a(resultData.guardType);
        if (giftItemEntity.isBigAnim()) {
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$CevRifC5orQQQcn91X1NQc8Wi58(this));
            if (this.wsManager != null) {
                this.wsManager.addLocalAnim(giftItemEntity);
            }
        }
        playMySelfAnimGift(giftItemEntity);
        showSelfGiftMsgOnChatList(giftItemEntity);
    }

    public static /* synthetic */ void lambda$playMySelfAnimOnMainThread$15(TomatoLiveActivity tomatoLiveActivity) {
        if (tomatoLiveActivity.liveAnimationView.isGiftAnimating() && !TextUtils.equals(tomatoLiveActivity.userId, tomatoLiveActivity.curBigAnimSendUserId)) {
            tomatoLiveActivity.liveAnimationView.stopGiftAnimating();
        }
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
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$2BY9XFiKSEY3LZtXax_WqRw22LY(this, giftAnimModel, giftItemEntity));
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
            if (!this.isGiftUpdate) {
                this.isGiftUpdate = true;
                ((TomatoLivePresenter) this.mPresenter).getGiftList(-1);
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
        giftItemEntity.expGrade = com.tomatolive.library.utils.b.h(resultData.expGrade);
        giftItemEntity.sex = resultData.sex;
        playReceiveAnimGift(giftItemEntity);
        StringBuilder stringBuilder2;
        if (giftItemEntity.isBigAnim()) {
            if (this.wsManager != null) {
                this.wsManager.addReceiveBigAnim(giftItemEntity);
            }
            if (giftItemEntity.sendIndex == 0) {
                giftItemEntity.sendIndex = 1;
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

    private void playMySelfAnimGift(GiftItemEntity giftItemEntity) {
        if (giftItemEntity != null) {
            GiftAnimModel giftAnimModel = new GiftAnimModel();
            GiftAnimModel onLineUrl = giftAnimModel.setGiftId(giftItemEntity.id).setProp(giftItemEntity.isProp).setOnLineUrl(giftItemEntity.isProp ? giftItemEntity.animalUrl : giftItemEntity.onlineUrl);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(giftItemEntity.effect_type);
            stringBuilder.append("");
            onLineUrl.setEffectType(stringBuilder.toString()).setGiftTypeId(giftItemEntity.typeid).setGiftName(giftItemEntity.name).setGiftCount(1).setGiftPic(giftItemEntity.imgurl).setGiftPrice(giftItemEntity.price).setSendUserId(this.userId).setSendUserName(z.a().f()).setSendGiftTime(Long.valueOf(System.currentTimeMillis())).setCurrentStart(true).setGiftDirPath(giftItemEntity.isBigAnim() ? "" : giftItemEntity.giftDirPath).setJsonName(giftItemEntity.jsonname).setGiftShowTime(p.a(giftItemEntity.duration)).setSendIndex(giftItemEntity.sendIndex).setSendUserPic(giftItemEntity.avatar).setAnimationListener(this);
            if (this.isContinue) {
                giftAnimModel.setJumpCombo(giftItemEntity.sendIndex);
            } else {
                giftAnimModel.setJumpCombo(0);
            }
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$FZyRjw_CJ-zAH6k8sw2uS_Pwlwo(this, giftAnimModel, giftItemEntity));
        }
    }

    private boolean isSocketClose() {
        return this.isSocketClose || this.isSocketError;
    }

    private void initSocket() {
        if (this.wsManager == null) {
            addSocketTipMsg(R.string.fq_start_connect_socket);
            this.wsManager = WsManager.getInstance();
            this.wsManager.init(this, this.socketUrl, (long) this.heartTime);
            this.wsManager.setOnWebSocketListener(new OnWebSocketStatusListener() {
                public void onOpen(boolean z) {
                    if (TomatoLiveActivity.this.isTaskSocket) {
                        TomatoLiveActivity.this.isTaskSocket = false;
                    }
                    TomatoLiveActivity.this.isReConnectStatus = z;
                    TomatoLiveActivity.this.handlerMainPost(new -$$Lambda$TomatoLiveActivity$16$B25YYzkuLjj4-u2-ApUk3NHjuyQ(this));
                    TomatoLiveActivity.this.isConnectingChatService = false;
                    TomatoLiveActivity.this.reConnectCountOver = false;
                    if (z) {
                        TomatoLiveActivity.this.addSocketTipMsg(R.string.fq_reconnect_suc);
                        TomatoLiveActivity.this.isStartGetRoomInfo = true;
                        ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getLiveInitInfo(TomatoLiveActivity.this.liveId, ConnectSocketParams.EFFECT_TYPE_BIG, "1");
                    } else {
                        TomatoLiveActivity.this.addSocketTipMsg(R.string.fq_connect_suc);
                    }
                    TomatoLiveActivity.this.isSocketClose = false;
                    TomatoLiveActivity.this.isSocketError = false;
                }

                public void onClose() {
                    TomatoLiveActivity.this.isSocketClose = true;
                }

                public void onError(boolean z, String str) {
                    if (str == null || !str.contains("HTTP/1.1 403")) {
                        TomatoLiveActivity.this.isSocketError = true;
                        TomatoLiveActivity.this.addSocketTipMsg(R.string.fq_connect_fail);
                        return;
                    }
                    TomatoLiveActivity.this.startTokenDialogService();
                    TomatoLiveActivity.this.stopSocket();
                    TomatoLiveActivity.this.finish();
                }

                public void reConnecting() {
                    TomatoLiveActivity.this.isConnectingChatService = true;
                    TomatoLiveActivity.this.addSocketTipMsg(R.string.fq_start_reconnect_socket);
                    ((TomatoLivePresenter) TomatoLiveActivity.this.mPresenter).getWebSocketAddress(TomatoLiveActivity.this.liveId, ConnectSocketParams.EFFECT_TYPE_BIG, "1");
                }

                public void reConnectCountOver() {
                    TomatoLiveActivity.this.isConnectingChatService = false;
                    TomatoLiveActivity.this.reConnectCountOver = true;
                    TomatoLiveActivity.this.handlerMainPost(new -$$Lambda$TomatoLiveActivity$16$nr3l2ug5sS6FnoULdg_Ik87FAYU(this));
                }
            });
        }
    }

    private void addSocketTipMsg(@StringRes int i) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgText(getString(i));
        chatEntity.setMsgType(7);
        handlerMainPost(new -$$Lambda$TomatoLiveActivity$kzeWgzX7dijuL5UyfR4YuXLCN8g(this, chatEntity));
    }

    private void stopSocket() {
        if (this.wsManager != null) {
            this.wsManager.stopService();
            this.wsManager = null;
        }
    }

    public void onBackThreadReceiveMessage(SocketMessageEvent socketMessageEvent) {
        int i = -1;
        if (com.tomatolive.library.utils.b.n(socketMessageEvent.code)) {
            ResultData resultData = socketMessageEvent.resultData;
            if (resultData != null && !isFinishing() && this.mainHandler != null && this.workHandler != null) {
                String str = socketMessageEvent.messageType;
                switch (str.hashCode()) {
                    case -1598856750:
                        if (str.equals(ConnectSocketParams.MESSAGE_BANPOSTALL_TYPE)) {
                            i = 9;
                            break;
                        }
                        break;
                    case -1302490523:
                        if (str.equals(ConnectSocketParams.MESSAGE_CONSUME_NOTIFY_TYPE)) {
                            i = 20;
                            break;
                        }
                        break;
                    case -1256385881:
                        if (str.equals(ConnectSocketParams.MESSAGE_TOKEN_INVALID_NOTIFY_TYPE)) {
                            i = 16;
                            break;
                        }
                        break;
                    case -1039689911:
                        if (str.equals(ConnectSocketParams.MESSAGE_NOTIFY_TYPE)) {
                            i = 12;
                            break;
                        }
                        break;
                    case -993690229:
                        if (str.equals(ConnectSocketParams.MESSAGE_PROP_SEND_TYPE)) {
                            i = 24;
                            break;
                        }
                        break;
                    case -992867598:
                        if (str.equals(ConnectSocketParams.MESSAGE_GRAB_GIFTBOX_BROADCAST_TYPE)) {
                            i = 23;
                            break;
                        }
                        break;
                    case -941691210:
                        if (str.equals(ConnectSocketParams.MESSAGE_UNIVERSAL_BROADCAST_TYPE)) {
                            i = 19;
                            break;
                        }
                        break;
                    case -634778976:
                        if (str.equals(ConnectSocketParams.MESSAGE_FORBID_LIVE_TYPE)) {
                            i = 14;
                            break;
                        }
                        break;
                    case -339185956:
                        if (str.equals(ConnectSocketParams.MESSAGE_BALANCE_TYPE)) {
                            i = 3;
                            break;
                        }
                        break;
                    case -337843889:
                        if (str.equals(ConnectSocketParams.MESSAGE_BAN_POST_TYPE)) {
                            i = 8;
                            break;
                        }
                        break;
                    case -236148015:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVECONTROL_TYPE)) {
                            i = 13;
                            break;
                        }
                        break;
                    case -21216891:
                        if (str.equals(ConnectSocketParams.MESSAGE_POSTINTERVAL_TYPE)) {
                            i = 4;
                            break;
                        }
                        break;
                    case 3052376:
                        if (str.equals(ConnectSocketParams.MESSAGE_CHAT_TYPE)) {
                            i = 2;
                            break;
                        }
                        break;
                    case 3172656:
                        if (str.equals(ConnectSocketParams.MESSAGE_GIFT_TYPE)) {
                            i = 0;
                            break;
                        }
                        break;
                    case 3641990:
                        if (str.equals(ConnectSocketParams.MESSAGE_WARN_TYPE)) {
                            i = 5;
                            break;
                        }
                        break;
                    case 96667762:
                        if (str.equals(ConnectSocketParams.MESSAGE_ENTER_TYPE)) {
                            i = 1;
                            break;
                        }
                        break;
                    case 98509126:
                        if (str.equals(ConnectSocketParams.MESSAGE_GOOUT_TYPE)) {
                            i = 11;
                            break;
                        }
                        break;
                    case 99368259:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVEADMIN_GOOUT_TYPE)) {
                            i = 10;
                            break;
                        }
                        break;
                    case 102846135:
                        if (str.equals(ConnectSocketParams.MESSAGE_LEAVE_TYPE)) {
                            i = 6;
                            break;
                        }
                        break;
                    case 317295308:
                        if (str.equals(ConnectSocketParams.MESSAGE_USER_GRADE_TYPE)) {
                            i = 17;
                            break;
                        }
                        break;
                    case 441119852:
                        if (str.equals(ConnectSocketParams.MESSAGE_PUT_GIFT_BOX_TYPE)) {
                            i = 21;
                            break;
                        }
                        break;
                    case 487782924:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVEADMIN_BANPOST_TYPE)) {
                            i = 7;
                            break;
                        }
                        break;
                    case 798249924:
                        if (str.equals(ConnectSocketParams.MESSAGE_LIVE_SETTING_TYPE)) {
                            i = 15;
                            break;
                        }
                        break;
                    case 1680327801:
                        if (str.equals(ConnectSocketParams.MESSAGE_GIFT_TRUMPET_TYPE)) {
                            i = 18;
                            break;
                        }
                        break;
                    case 2021199175:
                        if (str.equals(ConnectSocketParams.MESSAGE_GRAB_GIFTBOX_NOTIFIED_TYPE)) {
                            i = 22;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case 0:
                        dealGiftMsgFormSocket(resultData);
                        break;
                    case 1:
                        dealEnterMsgFromSocket(resultData);
                        break;
                    case 2:
                        dealChatMsgFormSocket(resultData);
                        break;
                    case 3:
                        dealUserBalanceMsgFromSocket(resultData);
                        break;
                    case 4:
                        int a = p.a(resultData.postIntervalTimes);
                        if (a >= 0) {
                            this.postIntervalTimes = a;
                            break;
                        }
                        break;
                    case 5:
                        handlerMainPost(new -$$Lambda$TomatoLiveActivity$F3jAPbvfPbs9v9mFTaJK3NxX7EU(this, resultData));
                        break;
                    case 6:
                        handlerMainPost(new -$$Lambda$TomatoLiveActivity$nvA4R4qkKA7hZcLuLBFCnp12Fsg(this, resultData));
                        break;
                    case 7:
                        dealSuperBanPostMsgFromSocket(resultData);
                        break;
                    case 8:
                        dealBanPostMsgFromSocket(resultData);
                        break;
                    case 9:
                        dealBannedAllPostMsgFormSocket(resultData);
                        break;
                    case 10:
                    case 11:
                        dealKickOutMsgFromSocket(resultData);
                        break;
                    case 12:
                        dealNotifyMsgFromSocket(resultData);
                        break;
                    case 13:
                        handlerMainPost(new -$$Lambda$TomatoLiveActivity$aH4fbu5zEZov2A7Fbv1_QfVp6tw(this, resultData));
                        break;
                    case 14:
                        handlerMainPost(new -$$Lambda$TomatoLiveActivity$v4wd3fm-WASHZiJCxT3Z08VKqAk(this));
                        break;
                    case 15:
                        dealLiveSettingMsgFromSocket(resultData);
                        break;
                    case 16:
                        startTokenDialogService();
                        stopSocket();
                        finish();
                        break;
                    case 17:
                        this.userGrade = resultData.afterGrade;
                        handlerMainPost(new -$$Lambda$TomatoLiveActivity$xf7HWaoltJU6EX26BogfoiSxtBk(this));
                        break;
                    case 18:
                        synchronized (TomatoLiveActivity.class) {
                            if (this.giftNoticeQueue == null) {
                                this.giftNoticeQueue = new ConcurrentLinkedQueue();
                            }
                            if (this.giftNoticeQueue.size() == 99) {
                                this.giftNoticeQueue.poll();
                            }
                            this.giftNoticeQueue.offer(resultData);
                        }
                        sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START);
                        break;
                    case 19:
                        synchronized (TomatoLiveActivity.class) {
                            if (this.sysNoticeQueue == null) {
                                this.sysNoticeQueue = new ConcurrentLinkedQueue();
                            }
                            if (this.sysNoticeQueue.size() == 99) {
                                this.sysNoticeQueue.poll();
                            }
                            this.sysNoticeQueue.offer(resultData);
                        }
                        sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_OPEN_INPUT);
                        break;
                    case 20:
                        if (TextUtils.equals(resultData.type, "openGuard")) {
                            handlerMainPost(new -$$Lambda$TomatoLiveActivity$cJDpkGfNbgBXKwLi5c8Jl66V8YA(this, resultData));
                            synchronized (TomatoLiveActivity.class) {
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
                    case 21:
                        handlerMainPost(new -$$Lambda$TomatoLiveActivity$dAWAwYgVv7wZF6dJktiXt_2lIRI(this, resultData));
                        break;
                    case 22:
                        showToast(getString(R.string.fq_gift_box_toast_tips, new Object[]{resultData.presenterName, resultData.propNum, resultData.propName}));
                        break;
                    case 23:
                        dealGiftBoxMsg(resultData);
                        break;
                    case 24:
                        dealPropMsgFormSocket(resultData);
                        break;
                    default:
                        return;
                }
                return;
            }
            return;
        }
        if (com.tomatolive.library.utils.b.o(socketMessageEvent.code)) {
            showToast(R.string.fq_gift_res_update);
            this.isGiftUpdate = true;
            ((TomatoLivePresenter) this.mPresenter).getGiftList(-1);
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$xeQ9pSetuQbOFWf0lxSdmiHkZe0(this));
        } else {
            showToast(socketMessageEvent.message);
        }
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$19(TomatoLiveActivity tomatoLiveActivity) {
        if (tomatoLiveActivity.giftBottomDialog != null && tomatoLiveActivity.giftBottomDialog.isAdded()) {
            tomatoLiveActivity.giftBottomDialog.dismiss();
        }
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$23(TomatoLiveActivity tomatoLiveActivity) {
        if (tomatoLiveActivity.mLivePusherInfoView != null) {
            tomatoLiveActivity.mLivePusherInfoView.updateUserGradeInfo(tomatoLiveActivity.userGrade);
        }
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$24(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        if (tomatoLiveActivity.mLivePusherInfoView != null) {
            tomatoLiveActivity.mLivePusherInfoView.updateOpenGuardCount(resultData.anchorGuardCount);
        }
    }

    public static /* synthetic */ void lambda$onBackThreadReceiveMessage$25(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        GiftBoxEntity giftBoxEntity = new GiftBoxEntity();
        giftBoxEntity.expirationTime = p.b(resultData.expirationTime);
        giftBoxEntity.openTime = p.b(resultData.openTime);
        giftBoxEntity.giftBoxUniqueCode = resultData.giftBoxUniqueCode;
        giftBoxEntity.presenterAvatar = resultData.presenterAvatar;
        giftBoxEntity.presenterId = resultData.presenterId;
        giftBoxEntity.presenterName = resultData.presenterName;
        giftBoxEntity.userId = z.a().c();
        giftBoxEntity.liveId = tomatoLiveActivity.liveId;
        tomatoLiveActivity.mGiftBoxView.addOneBox(giftBoxEntity);
    }

    private void dealMyPropMsgFormSocket(ResultData resultData) {
        GiftItemEntity giftItemEntity = new GiftItemEntity();
        giftItemEntity.isProp = true;
        giftItemEntity.duration = p.a(resultData.duration);
        giftItemEntity.activeTime = p.a(resultData.activeTime);
        GiftIndexEntity giftIndexEntity = (GiftIndexEntity) this.myPropIndexMap.get(resultData.propId);
        if (giftIndexEntity == null) {
            giftIndexEntity = new GiftIndexEntity();
            giftIndexEntity.sendIndex++;
            this.isContinue = false;
            this.myPropIndexMap.put(resultData.propId, giftIndexEntity);
        } else if (giftIndexEntity.countDownStartTime == 0) {
            giftIndexEntity.sendIndex++;
            this.isContinue = false;
        } else if (System.currentTimeMillis() - giftIndexEntity.countDownStartTime > ((long) p.a(giftItemEntity.activeTime))) {
            giftIndexEntity.sendIndex = 1;
            giftIndexEntity.countDownStartTime = 0;
            this.isContinue = false;
        } else {
            giftIndexEntity.sendIndex++;
            this.isContinue = true;
            giftIndexEntity.countDownStartTime = 0;
        }
        giftItemEntity.sendUserName = resultData.userName;
        giftItemEntity.userId = resultData.userId;
        giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
        giftItemEntity.effect_type = p.a(resultData.animalType);
        giftItemEntity.animalType = giftItemEntity.effect_type;
        giftItemEntity.avatar = resultData.avatar;
        giftItemEntity.role = resultData.role;
        giftItemEntity.sex = resultData.sex;
        giftItemEntity.imgurl = resultData.coverUrl;
        giftItemEntity.expGrade = com.tomatolive.library.utils.b.h(resultData.expGrade);
        giftItemEntity.guardType = p.a(resultData.guardType);
        giftItemEntity.animalUrl = resultData.animalUrl;
        giftItemEntity.id = resultData.propId;
        giftItemEntity.typeid = resultData.propId;
        giftItemEntity.name = resultData.propName;
        if (giftItemEntity.isBigProp()) {
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$3EC7_i8I9C4b2-LTyOHMMXXEZak(this));
            if (this.wsManager != null) {
                this.wsManager.addLocalAnim(giftItemEntity);
            }
        }
        playMySelfAnimGift(giftItemEntity);
        showSelfGiftMsgOnChatList(giftItemEntity);
    }

    public static /* synthetic */ void lambda$dealMyPropMsgFormSocket$26(TomatoLiveActivity tomatoLiveActivity) {
        if (tomatoLiveActivity.liveAnimationView.isGiftAnimating() && !TextUtils.equals(tomatoLiveActivity.userId, tomatoLiveActivity.curBigAnimSendUserId)) {
            tomatoLiveActivity.liveAnimationView.stopGiftAnimating();
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
            hashMap.put(resultData.propId, giftIndexEntity);
            this.receivePropMap.put(resultData.userId, hashMap);
            giftItemEntity.sendIndex = giftIndexEntity.sendIndex;
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

    private void dealLiveSettingMsgFromSocket(ResultData resultData) {
        String str = resultData.changeType;
        Object obj = (str.hashCode() == 1559674578 && str.equals(ConnectSocketParams.MESSAGE_SPEAKLEVEL_TYPE)) ? null : -1;
        if (obj == null) {
            this.speakLevel = resultData.changeValue;
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgText(getString(R.string.fq_speak_level_tip_for_socket, new Object[]{this.speakLevel}));
            chatEntity.setExpGrade(this.speakLevel);
            chatEntity.setMsgType(11);
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$QnLuOsEGEHB-irqm7SWloV-nNQE(this, chatEntity));
        }
    }

    private void dealKickOutMsgFromSocket(ResultData resultData) {
        if (TextUtils.equals(this.anchorId, resultData.userId)) {
            resultData.userName = getString(R.string.fq_anchor);
        }
        showReceiveMsgOnChatList(resultData, getString(com.tomatolive.library.utils.b.k(resultData.role) ? R.string.fq_warn_content_out_room_forever : R.string.fq_warn_content_out_room, new Object[]{resultData.userName}), 9);
        if (TextUtils.equals(this.userId, resultData.targetId)) {
            startKickDialogService();
            stopSocket();
            finish();
        }
    }

    private void dealGiftMsgFormSocket(ResultData resultData) {
        if (this.mLivePusherInfoView != null) {
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$Sn1nC6_yVirByl3tLeaYfqQJpvY(this, com.tomatolive.library.utils.b.a(resultData)));
        }
        if (TextUtils.equals(z.a().c(), resultData.userId)) {
            playMySelfAnimOnMainThread(resultData);
        } else {
            playReceiveAnimOnMainThread(resultData);
        }
    }

    private void dealPropMsgFormSocket(ResultData resultData) {
        if (TextUtils.equals(z.a().c(), resultData.userId)) {
            dealMyPropMsgFormSocket(resultData);
        } else {
            dealReceivePropMsgFromSocket(resultData);
        }
    }

    private void dealEnterMsgFromSocket(ResultData resultData) {
        synchronized (TomatoLiveActivity.class) {
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
            synchronized (TomatoLiveActivity.class) {
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

    private void dealChatMsgFormSocket(ResultData resultData) {
        if (!this.shieldedList.contains(resultData.userId) && !this.bannedList.contains(resultData.userId)) {
            this.chatContent = resultData.content;
            if (this.isTranOpen) {
                y.a(this.chatContent, new -$$Lambda$TomatoLiveActivity$b0Xk6MDyne999K5Doj5UCtIu7Hw(this, resultData));
            } else {
                showReceiveMsgOnChatList(resultData, this.chatContent, 3);
            }
        }
    }

    private void dealUserBalanceMsgFromSocket(ResultData resultData) {
        handlerMainPost(new -$$Lambda$TomatoLiveActivity$92mX0y8KRmATCtZFq_4g1UJ3v38(this, resultData));
    }

    public static /* synthetic */ void lambda$dealUserBalanceMsgFromSocket$30(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        tomatoLiveActivity.appUserOver = p.c(resultData.balance);
        if (tomatoLiveActivity.appUserOver < 0.0d) {
            tomatoLiveActivity.appUserOver = 0.0d;
        }
        if (tomatoLiveActivity.giftBottomDialog != null) {
            tomatoLiveActivity.giftBottomDialog.setUserBalance(tomatoLiveActivity.appUserOver);
        }
    }

    private void dealLiveControlMsgFromSocket(ResultData resultData) {
        boolean isManager = resultData.isManager();
        if (isManager) {
            showReceiveMsgOnChatList(resultData, getString(R.string.fq_appoint_house_manage), 6);
            if (TextUtils.equals(this.userId, resultData.targetId)) {
                this.myRole = "3";
                if (this.isSuperBan) {
                    if (this.mInputTextMsgDialog != null) {
                        this.mInputTextMsgDialog.setBandPostBySuperManager();
                    }
                } else if (this.isNormalBan) {
                    if (this.mInputTextMsgDialog != null) {
                        this.mInputTextMsgDialog.setBandPost(d.c(this.banPostTimeLeft));
                    }
                } else if (this.isAllBan && this.mInputTextMsgDialog != null) {
                    this.mInputTextMsgDialog.cancelBandPost();
                }
            }
        } else if (TextUtils.equals(this.userId, resultData.targetId)) {
            this.myRole = ConnectSocketParams.EFFECT_TYPE_BIG;
            if (this.isAllBan && this.mInputTextMsgDialog != null) {
                this.mInputTextMsgDialog.setBanedAllPost(getString(R.string.fq_text_input_banned_hint));
            }
        }
        this.mLiveChatMsgView.updateRoleItemDataByUid(resultData.targetId, isManager ? "3" : ConnectSocketParams.EFFECT_TYPE_BIG);
    }

    private void dealNotifyMsgFromSocket(ResultData resultData) {
        String str = resultData.type;
        if (!TextUtils.isEmpty(str)) {
            String str2 = resultData.typeMsg;
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
                    handlerMainPost(new -$$Lambda$TomatoLiveActivity$oCLS3IfYBWIwfhOn6iK6OOJ_B5s(this, str2));
                    break;
                case 1:
                    this.isBanGroup = true;
                    break;
                default:
                    return;
            }
        }
    }

    public static /* synthetic */ void lambda$dealNotifyMsgFromSocket$31(TomatoLiveActivity tomatoLiveActivity, String str) {
        tomatoLiveActivity.startHideTitleTimer();
        tomatoLiveActivity.tvLiveTitle.setText(tomatoLiveActivity.getString(R.string.fq_text_live_title, new Object[]{str}));
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgType(8);
        chatEntity.setMsgText(str);
        tomatoLiveActivity.mLiveChatMsgView.addChatMsg(chatEntity);
    }

    private void dealBannedAllPostMsgFormSocket(ResultData resultData) {
        this.isAllBan = resultData.isBanAll();
        handlerMainPost(new -$$Lambda$TomatoLiveActivity$FMnfevLIbSD2QM6I6BgI5DoqujU(this));
        showReceiveMsgOnChatList(resultData, getString(this.isAllBan ? R.string.fq_text_input_banned_hint : R.string.fq_anchor_cancel_banned), 5);
    }

    public static /* synthetic */ void lambda$dealBannedAllPostMsgFormSocket$32(TomatoLiveActivity tomatoLiveActivity) {
        if (tomatoLiveActivity.isAllBan) {
            if (com.tomatolive.library.utils.b.l(tomatoLiveActivity.myRole) && tomatoLiveActivity.mInputTextMsgDialog != null) {
                tomatoLiveActivity.mInputTextMsgDialog.setBanedAllPost(tomatoLiveActivity.getString(R.string.fq_text_input_banned_hint));
            }
        } else if (tomatoLiveActivity.isSuperBan) {
            if (tomatoLiveActivity.mInputTextMsgDialog != null) {
                tomatoLiveActivity.mInputTextMsgDialog.setBandPostBySuperManager();
            }
        } else if (tomatoLiveActivity.isNormalBan) {
            if (tomatoLiveActivity.mInputTextMsgDialog != null) {
                tomatoLiveActivity.mInputTextMsgDialog.setBandPost(d.c(tomatoLiveActivity.banPostTimeLeft));
            }
        } else if (tomatoLiveActivity.mInputTextMsgDialog != null) {
            tomatoLiveActivity.mInputTextMsgDialog.cancelBandPost();
        }
    }

    private void dealBanPostMsgFromSocket(ResultData resultData) {
        String string;
        boolean isSomeoneBanPost = resultData.isSomeoneBanPost();
        if (isSomeoneBanPost) {
            this.bannedList.add(resultData.targetId);
        } else {
            this.bannedList.remove(resultData.targetId);
        }
        if (TextUtils.equals(this.userId, resultData.targetId)) {
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$SlSHMgyNBM9hhz0cH7O8K5NBY7I(this, isSomeoneBanPost, resultData));
        }
        if (TextUtils.equals(getString(R.string.fq_system), resultData.userName) || TextUtils.equals(this.anchorId, resultData.userId)) {
            resultData.userName = getString(R.string.fq_anchor);
        }
        if (isSomeoneBanPost) {
            string = getString(R.string.fq_banned, new Object[]{resultData.userName});
        } else {
            string = getString(R.string.fq_banned_cancel, new Object[]{resultData.userName});
        }
        showReceiveMsgOnChatList(resultData, string, 4);
    }

    public static /* synthetic */ void lambda$dealBanPostMsgFromSocket$33(TomatoLiveActivity tomatoLiveActivity, boolean z, ResultData resultData) {
        if (z) {
            tomatoLiveActivity.executeMyNormalBan(resultData.duration);
        } else {
            tomatoLiveActivity.clearMyNormalBan();
        }
    }

    private void dealSuperBanPostMsgFromSocket(ResultData resultData) {
        if (TextUtils.equals(this.userId, resultData.targetId)) {
            this.isSuperBan = true;
            if (!(this.isAllBan || this.mInputTextMsgDialog == null)) {
                handlerMainPost(new -$$Lambda$TomatoLiveActivity$kdF_XpThlG_ZYtjCLaq0gUlTu3k(this));
            }
        }
        showReceiveMsgOnChatList(resultData, getString(R.string.fq_banned_forever, new Object[]{resultData.userName}), 4);
    }

    private void executeMyNormalBan(String str) {
        this.isNormalBan = true;
        if (this.mInputTextMsgDialog != null) {
            this.banPostTimeLeft = str;
            if (!(this.isAllBan || this.isSuperBan)) {
                this.mInputTextMsgDialog.setBandPost(d.c(this.banPostTimeLeft));
            }
            dismissInputMsgDialog();
        }
    }

    private void clearMyNormalBan() {
        this.isNormalBan = false;
        if (this.isAllBan && com.tomatolive.library.utils.b.l(this.myRole)) {
            if (this.mInputTextMsgDialog != null) {
                this.mInputTextMsgDialog.setBanedAllPost(getString(R.string.fq_text_input_banned_hint));
            }
        } else if (this.isSuperBan) {
            if (this.mInputTextMsgDialog != null) {
                this.mInputTextMsgDialog.setBandPostBySuperManager();
            }
        } else if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.cancelBandPost();
        }
    }

    private void dealLeaveMsgFromSocket(ResultData resultData) {
        if (com.tomatolive.library.utils.b.m(resultData.role)) {
            goToEnd();
            return;
        }
        this.mLivePusherInfoView.removeUserItemById(resultData.userId);
        this.mLivePusherInfoView.setRoomPerson(this.onLineCount.get());
    }

    private void showUserCard(ChatEntity chatEntity) {
        if (!com.tomatolive.library.utils.b.a(chatEntity)) {
            return;
        }
        if (TextUtils.equals(this.userId, chatEntity.getUid())) {
            showUserAvatarDialog(chatEntity.getUserAvatar(), chatEntity.getMsgSendName(), chatEntity.getUid(), chatEntity.getSex(), "", chatEntity.getExpGrade(), chatEntity.getGuardType(), chatEntity.getRole(), false);
        } else if (!TextUtils.equals(chatEntity.getUid(), this.anchorId)) {
            showUserAvatarDialog(chatEntity.getUserAvatar(), chatEntity.getMsgSendName(), chatEntity.getUid(), chatEntity.getSex(), "", chatEntity.getExpGrade(), chatEntity.getGuardType(), chatEntity.getRole(), true);
        } else if (!this.isStartGetAnchorInfo) {
            ((TomatoLivePresenter) this.mPresenter).getAnchorInfo(chatEntity.getUid());
            this.isStartGetAnchorInfo = true;
        }
    }

    private void showUserAvatarDialog(String str, String str2, String str3, String str4, String str5, String str6, int i, String str7, boolean z) {
        String str8 = str3;
        boolean contains = this.bannedList.contains(str8);
        boolean contains2 = this.shieldedList.contains(str8);
        boolean j = com.tomatolive.library.utils.b.j(str7);
        boolean d = com.tomatolive.library.utils.b.d(i);
        boolean z2 = (j && com.tomatolive.library.utils.b.l(this.myRole)) ? false : z;
        if (com.tomatolive.library.utils.b.k(str7)) {
            this.userSuperAvatarDialog = UserSuperAvatarDialog.newInstance(str3, str, str2, str4, str5, str6, i);
            this.userSuperAvatarDialog.show(getSupportFragmentManager());
        } else if (com.tomatolive.library.utils.b.c(i)) {
            this.userGuardAvatarDialog = UserGuardAvatarDialog.newInstance(str3, str, str2, str4, str5, z2, str7, str6, i, new -$$Lambda$TomatoLiveActivity$cT7Ogw7-xt5iUJIfGY9doJmtI5U(this, j, contains, contains2, d, str2, str3));
            this.userGuardAvatarDialog.show(getSupportFragmentManager());
        } else {
            this.userAvatarDialog = UserAvatarDialog.newInstance(str3, str, str2, str4, str5, z2, str7, str6, new -$$Lambda$TomatoLiveActivity$_DFl9PX7mUM-Lgea3IO32ZNhNnA(this, j, contains, contains2, d, str2, str3), new -$$Lambda$TomatoLiveActivity$CrURFJ_xJWWMrS87z4s75VbJQYE(this, str8));
            this.userAvatarDialog.show(getSupportFragmentManager());
        }
    }

    public static /* synthetic */ void lambda$showUserAvatarDialog$37(TomatoLiveActivity tomatoLiveActivity, String str, View view) {
        if (com.tomatolive.library.utils.b.a(tomatoLiveActivity.mContext, str)) {
            int i = 1;
            int isSelected = view.isSelected() ^ 1;
            view.setSelected(isSelected);
            tomatoLiveActivity.showToast(isSelected != 0 ? R.string.fq_text_attention_success : R.string.fq_text_attention_cancel_success);
            TomatoLivePresenter tomatoLivePresenter = (TomatoLivePresenter) tomatoLiveActivity.mPresenter;
            if (isSelected == 0) {
                i = 0;
            }
            tomatoLivePresenter.attentionAnchor(str, i);
            tomatoLiveActivity.isFollowed = isSelected != 0 ? "1" : "0";
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

    private void userAvatarDialogManager(boolean r7, boolean r8, boolean r9, boolean r10, java.lang.String r11, java.lang.String r12) {
        /*
        r6 = this;
        r0 = r6.myRole;
        r1 = r0.hashCode();
        switch(r1) {
            case 49: goto L_0x0028;
            case 50: goto L_0x001e;
            case 51: goto L_0x0014;
            case 52: goto L_0x0009;
            case 53: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0032;
    L_0x000a:
        r1 = "5";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0012:
        r0 = 3;
        goto L_0x0033;
    L_0x0014:
        r1 = "3";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0032;
    L_0x001c:
        r0 = 2;
        goto L_0x0033;
    L_0x001e:
        r1 = "2";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0026:
        r0 = 1;
        goto L_0x0033;
    L_0x0028:
        r1 = "1";
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0032;
    L_0x0030:
        r0 = 0;
        goto L_0x0033;
    L_0x0032:
        r0 = -1;
    L_0x0033:
        switch(r0) {
            case 0: goto L_0x004c;
            case 1: goto L_0x0048;
            case 2: goto L_0x003b;
            case 3: goto L_0x0037;
            default: goto L_0x0036;
        };
    L_0x0036:
        return;
    L_0x0037:
        r6.showSuperControlPermissionDialog(r12, r11);
        goto L_0x0055;
    L_0x003b:
        if (r7 != 0) goto L_0x0044;
    L_0x003d:
        if (r10 == 0) goto L_0x0040;
    L_0x003f:
        goto L_0x0044;
    L_0x0040:
        r6.showControlPermissionDialog(r9, r8, r12, r11);
        goto L_0x0055;
    L_0x0044:
        r6.showAudiencePermissionDialog(r9, r12, r11);
        goto L_0x0055;
    L_0x0048:
        r6.showAudiencePermissionDialog(r9, r12, r11);
        goto L_0x0055;
    L_0x004c:
        r0 = r6;
        r1 = r7;
        r2 = r8;
        r3 = r9;
        r4 = r12;
        r5 = r11;
        r0.showAnchorPermissionDialog(r1, r2, r3, r4, r5);
    L_0x0055:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.live.TomatoLiveActivity.userAvatarDialogManager(boolean, boolean, boolean, boolean, java.lang.String, java.lang.String):void");
    }

    private void showAudiencePermissionDialog(boolean z, String str, String str2) {
        LiveActionBottomDialog.create(ConnectSocketParams.EFFECT_TYPE_BIG, z, new -$$Lambda$TomatoLiveActivity$iZKpB9YwfS-DcduW4bGajp73l8A(this, str, str2)).show(getSupportFragmentManager());
    }

    public static /* synthetic */ void lambda$showAudiencePermissionDialog$38(TomatoLiveActivity tomatoLiveActivity, String str, String str2, int i, boolean z) {
        if (i == 3) {
            tomatoLiveActivity.dismissUserAvatarDialog();
            tomatoLiveActivity.clickShielded(z, str, str2);
        }
    }

    private void showControlPermissionDialog(boolean z, boolean z2, String str, String str2) {
        LiveActionBottomDialog.create("3", z, z2, new -$$Lambda$TomatoLiveActivity$3gnnTy1tQ_p3SC03U2dBiCPvVYQ(this, str, str2)).show(getSupportFragmentManager());
    }

    public static /* synthetic */ void lambda$showControlPermissionDialog$39(TomatoLiveActivity tomatoLiveActivity, String str, String str2, int i, boolean z) {
        tomatoLiveActivity.dismissUserAvatarDialog();
        switch (i) {
            case 2:
                tomatoLiveActivity.clickBanned(z, str, str2);
                return;
            case 3:
                tomatoLiveActivity.clickShielded(z, str, str2);
                return;
            case 4:
                tomatoLiveActivity.clickKickOut(str, str2);
                return;
            default:
                return;
        }
    }

    private void showSuperControlPermissionDialog(String str, String str2) {
        LiveActionBottomDialog.create("5", new -$$Lambda$TomatoLiveActivity$jSfoaK8olAXqL6Ict-vSMRZa-iw(this, str, str2)).show(getSupportFragmentManager());
    }

    public static /* synthetic */ void lambda$showSuperControlPermissionDialog$40(TomatoLiveActivity tomatoLiveActivity, String str, String str2, int i, boolean z) {
        tomatoLiveActivity.dismissUserAvatarDialog();
        if (i != 2) {
            if (i == 4 && tomatoLiveActivity.wsManager != null) {
                tomatoLiveActivity.wsManager.sendSuperGoOutMessage(MessageHelper.convertToSuperGoOutMsg(tomatoLiveActivity.liveId, str, str2));
            }
        } else if (tomatoLiveActivity.wsManager != null) {
            tomatoLiveActivity.wsManager.sendSuperBannedMessage(MessageHelper.convertToSuperBanMsg(tomatoLiveActivity.liveId, str, str2));
        }
    }

    private void clickBanned(boolean z, String str, String str2) {
        if (z) {
            BottomDialogUtils.showBannedDialog(this.mContext, new -$$Lambda$TomatoLiveActivity$c5y_aQjxp6BSpcSP2o_petnz0QY(this, str, str2));
        } else if (this.wsManager != null) {
            this.wsManager.sendBannedMessage(MessageHelper.convertToBanMsg(this.liveId, str, str2, "-1", this.myRole, ConnectSocketParams.EFFECT_TYPE_BIG));
        }
    }

    public static /* synthetic */ void lambda$clickBanned$41(TomatoLiveActivity tomatoLiveActivity, String str, String str2, long j) {
        if (tomatoLiveActivity.wsManager != null) {
            tomatoLiveActivity.wsManager.sendBannedMessage(MessageHelper.convertToBanMsg(tomatoLiveActivity.liveId, str, str2, String.valueOf(j), tomatoLiveActivity.myRole, "1"));
        }
    }

    private void showAnchorPermissionDialog(boolean z, boolean z2, boolean z3, String str, String str2) {
        LiveActionBottomDialog.create("1", z, z3, z2, new -$$Lambda$TomatoLiveActivity$sIdWApXn7yLbofSQJX2mlMlmmxI(this, str, str2)).show(getSupportFragmentManager());
    }

    public static /* synthetic */ void lambda$showAnchorPermissionDialog$42(TomatoLiveActivity tomatoLiveActivity, String str, String str2, int i, boolean z) {
        tomatoLiveActivity.dismissUserAvatarDialog();
        switch (i) {
            case 1:
                tomatoLiveActivity.clickCtrl(z, str, str2);
                return;
            case 2:
                tomatoLiveActivity.clickBanned(z, str, str2);
                return;
            case 3:
                tomatoLiveActivity.clickShielded(z, str, str2);
                return;
            case 4:
                tomatoLiveActivity.clickKickOut(str, str2);
                return;
            default:
                return;
        }
    }

    private void clickKickOut(String str, String str2) {
        if (this.wsManager != null) {
            this.wsManager.sendKickOutMessage(MessageHelper.convertToKickOutMsg(this.liveId, str, str2));
        }
    }

    private void clickCtrl(boolean z, String str, String str2) {
        if (this.wsManager != null) {
            this.wsManager.sendCtrlMessage(MessageHelper.convertToCtrlMsg(this.liveId, str, str2, z));
        }
    }

    private void clickShielded(boolean z, String str, String str2) {
        if (z) {
            this.shieldedList.add(str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_shielded));
            stringBuilder.append(str2);
            showToast(stringBuilder.toString());
        } else {
            this.shieldedList.remove(str);
            showToast(getString(R.string.fq_cancel_shielded, new Object[]{str2}));
        }
        if (this.wsManager != null) {
            this.wsManager.sendShieldMessage(MessageHelper.convertToShieldMsg(this.liveId, str, z));
        }
    }

    private void initChatList() {
        this.chatMsgRoot = (LinearLayout) findViewById(R.id.ll_live_chat_msg);
        this.mLiveChatMsgView = (LiveChatMsgView) findViewById(R.id.live_chat_msg_view);
        this.mLiveChatMsgView.setOnChatMsgItemClickListener(new -$$Lambda$TomatoLiveActivity$QbZkUQ20HFouQXNA0_qSEA2LVNQ(this));
    }

    private void initInputChat() {
        this.mInputTextMsgDialog = new InputTextMsgDialog(this.mContext, this);
        this.mInputTextMsgDialog.setOnDismissListener(new -$$Lambda$TomatoLiveActivity$OwBwK_YTPh6S9DtTuZx9iKv0rWk(this));
        this.tvInput = (TextView) findViewById(R.id.iv_input);
        r.a().a(this.tvInput, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, new -$$Lambda$TomatoLiveActivity$WWLH0i3FZsCvAi9CpjA5QFkbwoc(this));
    }

    private void toggleTrans() {
        if (p.a(this.userGrade) < p.a(this.enableTranslationLevel, 1) && !com.tomatolive.library.utils.b.k(this.myRole)) {
            WarnDialog.newInstance(WarnDialog.TRANSLATION_TIP, getString(R.string.fq_enable_translation_level_tips, new Object[]{this.enableTranslationLevel})).show(getSupportFragmentManager());
        } else if (this.isTranOpen) {
            this.isTranOpen = false;
            this.ivTrans.setImageResource(R.drawable.fq_icon_translate_deflaut);
            showToast(R.string.fq_close_tran);
        } else if (this.transDialog != null) {
            this.transDialog.show(getSupportFragmentManager());
        }
    }

    public void onClickDM(boolean z) {
        if (z) {
            this.isOpenDanmu = false;
            this.mInputTextMsgDialog.toggleDanmu();
        } else if (this.guardType > 0) {
            this.isOpenDanmu = true;
            this.mInputTextMsgDialog.toggleDanmu();
        } else {
            this.mInputTextMsgDialog.dismiss();
            this.isOpenDanmu = false;
            GuardOpenTipsDialog.newInstance(11, formatAnchorGuardInfo(this.liveInfoItem), (OnOpenGuardCallbackListener) this).show(getSupportFragmentManager());
        }
        com.blankj.utilcode.util.k.a().a("last_danmu", this.isOpenDanmu);
    }

    private LiveEntity formatAnchorGuardInfo(LiveEntity liveEntity) {
        LiveEntity liveEntity2 = new LiveEntity();
        liveEntity2.anchorId = liveEntity.anchorId;
        liveEntity2.anchorGuardCount = liveEntity.anchorGuardCount;
        liveEntity2.userGuardType = liveEntity.userGuardType;
        liveEntity2.userGuardExpireTime = liveEntity.userGuardExpireTime;
        liveEntity2.isOpenWeekGuard = liveEntity.isOpenWeekGuard;
        liveEntity2.nickname = liveEntity.nickname;
        liveEntity2.expGrade = this.userGrade;
        return liveEntity2;
    }

    public void onTextSend(String str) {
        if (isSocketClose()) {
            showToast(R.string.fq_text_network_error_chat);
            return;
        }
        if (TextUtils.equals(this.userId, this.anchorId)) {
            this.myRole = "1";
        }
        if (com.tomatolive.library.utils.b.l(this.myRole)) {
            if (p.a(this.speakLevel) > p.a(this.userGrade)) {
                ChatTipDialog.newInstance(getString(R.string.fq_banned_speak_level_tip)).show(getSupportFragmentManager(), ConnectSocketParams.MESSAGE_SPEAKLEVEL_TYPE);
                return;
            } else if (str.length() > 53) {
                ChatTipDialog.newInstance(getString(R.string.fq_text_length)).show(getSupportFragmentManager(), "length_limit");
                return;
            } else {
                this.clickCount.incrementAndGet();
                if (this.postIntervalTimes == 0) {
                    this.clickCount.getAndSet(2);
                } else {
                    if (this.clickCount.get() == 3) {
                        startCDCountDown(this.postIntervalTimes * 3);
                    }
                    if (this.clickCount.get() > 3) {
                        ChatTipDialog.newInstance(String.format(getString(R.string.fq_text_CD), new Object[]{Long.valueOf(this.countDownTime)})).show(getSupportFragmentManager(), "CD");
                        return;
                    }
                }
                if (TextUtils.equals(str, this.lastMsg)) {
                    ChatTipDialog.newInstance(getString(R.string.fq_text_same_content)).show(getSupportFragmentManager(), "same_content");
                    return;
                }
                this.lastMsg = str;
            }
        }
        if (this.isBanGroup) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgSendName(z.a().f());
            chatEntity.setMsgText(str);
            chatEntity.setMsgType(3);
            chatEntity.setSex(z.a().h());
            chatEntity.setRole(this.myRole);
            chatEntity.setUid(this.userId);
            chatEntity.setUserAvatar(z.a().g());
            chatEntity.setExpGrade(this.userGrade);
            chatEntity.setGuardType(this.guardType);
            this.mLiveChatMsgView.addChatMsg(chatEntity);
            if (this.isOpenDanmu) {
                this.mLivePusherInfoView.addDanmuMsg(chatEntity);
            }
            return;
        }
        if (this.wsManager != null) {
            this.wsManager.sendChatMessage(MessageHelper.convertToChatMsg(this.myRole, this.liveId, str, this.userGrade, this.isOpenDanmu, String.valueOf(this.guardType)));
        }
    }

    private void startCDCountDown(int i) {
        io.reactivex.k.interval(1, TimeUnit.SECONDS).map(new -$$Lambda$TomatoLiveActivity$jK6bCWL63PNuXQ0o_vS0h2CWn34(i)).take(((long) i) + 1).subscribeOn(xl.b()).observeOn(xl.b()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new sh<Long>() {
            public void accept(Long l) {
                TomatoLiveActivity.this.countDownTime = l.longValue();
                if (TomatoLiveActivity.this.countDownTime == 0) {
                    TomatoLiveActivity.this.clickCount.getAndSet(2);
                }
            }
        });
    }

    private void showChatFrames() {
        if (!com.tomatolive.library.utils.b.f()) {
            return;
        }
        if (this.isConnectingChatService) {
            showToast(R.string.fq_start_connect_socket);
        } else if (this.reConnectCountOver) {
            if (this.wsManager != null) {
                this.wsManager.resetCount();
                this.wsManager.reconnect();
            }
        } else {
            showInputMsgDialog();
            double d = (double) this.mDisplayMetrics.heightPixels;
            Double.isNaN(d);
            inputChangeStatus((int) (d * 0.42d), 4);
        }
    }

    private void showInputMsgDialog() {
        if (this.mInputTextMsgDialog != null && !this.mInputTextMsgDialog.isShowing()) {
            this.mInputTextMsgDialog.show();
        }
    }

    private void inputChangeStatus(int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) this.chatMsgRoot.getLayoutParams();
        layoutParams.bottomMargin = i;
        this.chatMsgRoot.setLayoutParams(layoutParams);
        LivePusherInfoView livePusherInfoView = this.mLivePusherInfoView;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        livePusherInfoView.setTranslationY(i2 == 0 ? CropImageView.DEFAULT_ASPECT_RATIO : -(((float) this.mDisplayMetrics.heightPixels) / 4.0f));
        this.liveAnimationView.setTranslationY(i2 == 0 ? CropImageView.DEFAULT_ASPECT_RATIO : -(((float) this.mDisplayMetrics.heightPixels) / 3.0f));
        GiftBoxView giftBoxView = this.mGiftBoxView;
        if (i2 != 0) {
            f = -(((float) this.mDisplayMetrics.heightPixels) / 3.0f);
        }
        giftBoxView.setTranslationY(f);
    }

    private void dismissInputMsgDialog() {
        if (this.mInputTextMsgDialog != null && this.mInputTextMsgDialog.isShowing()) {
            this.mInputTextMsgDialog.dismiss();
        }
    }

    private void addMsgToQueue(ChatEntity chatEntity) {
        synchronized (TomatoLiveActivity.class) {
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

    private void showReceiveMsgOnChatList(ResultData resultData, String str, int i) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgSendName(resultData.userName);
        chatEntity.setMsgText(str);
        chatEntity.setMsgType(i);
        chatEntity.setSex(resultData.sex);
        chatEntity.setRole(resultData.role);
        chatEntity.setUid(resultData.userId);
        chatEntity.setGiftId(resultData.giftId);
        chatEntity.setGiftName(resultData.giftName);
        chatEntity.setUserAvatar(resultData.avatar);
        chatEntity.setTargetAvatar(resultData.targetAvatar);
        chatEntity.setTargetName(resultData.targetName);
        chatEntity.setTargetId(resultData.targetId);
        chatEntity.setExpGrade(com.tomatolive.library.utils.b.h(resultData.expGrade));
        chatEntity.setGuardType(p.a(resultData.guardType));
        addMsgToQueue(chatEntity);
        if (i == 3 && resultData.isOpenDanmu() && com.tomatolive.library.utils.b.c(p.a(resultData.guardType))) {
            handlerMainPost(new -$$Lambda$TomatoLiveActivity$7xE-4V0AXCLMcscutEEIbYSuyfY(this, chatEntity));
        }
    }

    private void showSelfGiftMsgOnChatList(GiftItemEntity giftItemEntity) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgSendName(z.a().f());
        chatEntity.setUid(this.userId);
        chatEntity.setMsgType(1);
        chatEntity.setGiftName(giftItemEntity.name);
        chatEntity.setGiftId(giftItemEntity.typeid);
        chatEntity.setUserAvatar(giftItemEntity.avatar);
        chatEntity.setRole(giftItemEntity.role);
        chatEntity.setExpGrade(com.tomatolive.library.utils.b.h(giftItemEntity.expGrade));
        chatEntity.setGuardType(giftItemEntity.guardType);
        chatEntity.setSex(giftItemEntity.sex);
        StringBuilder stringBuilder;
        if (giftItemEntity.isBigAnim()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_give_to_anchor));
            stringBuilder.append(giftItemEntity.name);
            stringBuilder.append(getString(R.string.fq_text_gift_multiple));
            stringBuilder.append(giftItemEntity.sendIndex);
            chatEntity.setMsgText(stringBuilder.toString());
        } else if (giftItemEntity.sendIndex == 1) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_give_to_anchor));
            stringBuilder.append(giftItemEntity.name);
            chatEntity.setMsgText(stringBuilder.toString());
        } else if (giftItemEntity.sendIndex != 0 && giftItemEntity.sendIndex % 10 == 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.fq_give_to_anchor));
            stringBuilder.append(giftItemEntity.name);
            stringBuilder.append(getString(R.string.fq_text_gift_multiple));
            stringBuilder.append(giftItemEntity.sendIndex);
            chatEntity.setMsgText(stringBuilder.toString());
        } else {
            return;
        }
        addMsgToQueue(chatEntity);
    }

    public static /* synthetic */ boolean lambda$new$47(TomatoLiveActivity tomatoLiveActivity, Message message) {
        switch (message.what) {
            case 10001:
                tomatoLiveActivity.dealChatMsg();
                break;
            case 10002:
                tomatoLiveActivity.dealEnterMsg();
                break;
            case 10003:
                tomatoLiveActivity.dealGuardEnterMsg();
                break;
            case IMediaPlayer.MEDIA_INFO_VIDEO_DECODED_START /*10004*/:
                tomatoLiveActivity.dealGiftNotice();
                break;
            case IMediaPlayer.MEDIA_INFO_OPEN_INPUT /*10005*/:
                tomatoLiveActivity.dealSysNotice();
                break;
            case IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO /*10006*/:
                tomatoLiveActivity.dealGuardOpenMsg();
                break;
        }
        return true;
    }

    private void dealGuardOpenMsg() {
        if (this.canShowGuardOPenMsg) {
            ResultData resultData = (ResultData) this.guardOpenMsgQueue.poll();
            if (resultData != null) {
                this.canShowGuardOPenMsg = false;
                handlerMainPost(new -$$Lambda$TomatoLiveActivity$_iVlOBB9JTbJcYglREbCyC8Clxo(this, resultData));
            }
        }
        if (this.guardOpenMsgQueue != null && this.guardOpenMsgQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_FIND_STREAM_INFO);
        }
    }

    public static /* synthetic */ void lambda$dealGuardOpenMsg$48(TomatoLiveActivity tomatoLiveActivity, ResultData resultData) {
        tomatoLiveActivity.liveAnimationView.loadGuardOpenAnimation(resultData, new OnAnimPlayListener() {
            public void onStart() {
                TomatoLiveActivity.this.canShowGuardOPenMsg = false;
            }

            public void onEnd() {
                TomatoLiveActivity.this.canShowGuardOPenMsg = true;
                TomatoLiveActivity.this.liveAnimationView.removeGuardOpenAllViews();
            }
        });
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setMsgType(12);
        chatEntity.setMsgSendName(resultData.userName);
        chatEntity.setExpGrade(resultData.expGrade);
        chatEntity.setGuardType(p.a(resultData.guardType));
        chatEntity.setMsgText(tomatoLiveActivity.getString(R.string.fq_open_guard_tip, new Object[]{com.tomatolive.library.utils.b.b(tomatoLiveActivity.mContext, resultData.guardType)}));
        tomatoLiveActivity.mLiveChatMsgView.addChatMsg(chatEntity);
        tomatoLiveActivity.mLiveChatMsgView.updateGuardTypeItemDataByUid(resultData.userId, p.a(resultData.guardType));
        tomatoLiveActivity.mLivePusherInfoView.sortUserList(resultData.userId, resultData.guardType, resultData.expGrade);
        if (TextUtils.equals(resultData.userId, z.a().c())) {
            tomatoLiveActivity.guardType = p.a(resultData.guardType);
        }
    }

    private void dealGuardEnterMsg() {
        if (this.canShowGuardEnterMsg && this.carFullAnimFinish) {
            ResultData resultData = (ResultData) this.guardEnterMsgQueue.poll();
            if (resultData != null) {
                this.canShowGuardEnterMsg = false;
                handlerMainPost(new -$$Lambda$TomatoLiveActivity$xvAr8qomOBrvqjC0R5cVKdewkLs(this, resultData, com.tomatolive.library.utils.b.h(resultData.expGrade)));
            }
        }
        if (this.guardEnterMsgQueue != null && this.guardEnterMsgQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(10003);
        }
    }

    public static /* synthetic */ void lambda$dealGuardEnterMsg$49(TomatoLiveActivity tomatoLiveActivity, ResultData resultData, String str) {
        if (resultData.isEnterGuardType() && com.tomatolive.library.utils.b.p(resultData.carId)) {
            if (TextUtils.equals(tomatoLiveActivity.userId, resultData.userId)) {
                tomatoLiveActivity.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, i.a(tomatoLiveActivity.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
                tomatoLiveActivity.liveAnimationView.loadCarJoinAnimation(resultData, false);
                tomatoLiveActivity.carFullAnimFinish = false;
                return;
            }
            tomatoLiveActivity.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, i.a(tomatoLiveActivity.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
            if (resultData.isOnPlayCarAnim()) {
                tomatoLiveActivity.carFullAnimFinish = false;
                tomatoLiveActivity.liveAnimationView.loadCarJoinAnimation(resultData, false);
            }
        } else if (resultData.isEnterGuardType()) {
            tomatoLiveActivity.liveAnimationView.loadLiveEnterAnimation(resultData.guardType, i.a(tomatoLiveActivity.mContext, resultData.avatar, resultData.userName, str, resultData.guardType));
        } else if (TextUtils.equals(tomatoLiveActivity.userId, resultData.userId)) {
            tomatoLiveActivity.carFullAnimFinish = false;
            tomatoLiveActivity.liveAnimationView.loadCarJoinAnimation(resultData, true);
        } else if (resultData.isOnPlayCarAnim()) {
            tomatoLiveActivity.carFullAnimFinish = false;
            tomatoLiveActivity.liveAnimationView.loadCarJoinAnimation(resultData, true);
        } else {
            tomatoLiveActivity.canShowGuardEnterMsg = true;
        }
    }

    private void dealGiftNotice() {
        if (this.canShowGiftNotice) {
            ResultData resultData = (ResultData) this.giftNoticeQueue.poll();
            if (resultData != null) {
                this.canShowGiftNotice = false;
                this.otherLiveId = resultData.liveId;
                handlerMainPost(new -$$Lambda$TomatoLiveActivity$5zmTbVNebgVz9iOgBTrV7_nnExY(this, resultData));
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
                this.tempSysNoticeResultData = resultData;
                handlerMainPost(new -$$Lambda$TomatoLiveActivity$mos0TB-uhVAuCZi8paPvu3ctWV0(this, resultData));
            }
        }
        if (this.sysNoticeQueue != null && this.sysNoticeQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(IMediaPlayer.MEDIA_INFO_OPEN_INPUT);
        }
    }

    /* JADX WARNING: Missing block: B:15:0x002a, code skipped:
            handlerMainPost(new com.tomatolive.library.ui.activity.live.-$$Lambda$TomatoLiveActivity$Wikx_wyc0nTedb_I0sfzi5wgPv4(r4, r0));
     */
    /* JADX WARNING: Missing block: B:16:0x0032, code skipped:
            return;
     */
    private void dealChatMsg() {
        /*
        r4 = this;
        r0 = new java.util.LinkedList;
        r0.<init>();
        r1 = com.tomatolive.library.ui.activity.live.TomatoLiveActivity.class;
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
        r1 = new com.tomatolive.library.ui.activity.live.-$$Lambda$TomatoLiveActivity$Wikx_wyc0nTedb_I0sfzi5wgPv4;
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
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.activity.live.TomatoLiveActivity.dealChatMsg():void");
    }

    public static /* synthetic */ void lambda$dealChatMsg$52(TomatoLiveActivity tomatoLiveActivity, List list) {
        tomatoLiveActivity.mLiveChatMsgView.addChatMsgList(list);
        if (tomatoLiveActivity.workHandler != null) {
            tomatoLiveActivity.workHandler.sendEmptyMessageDelayed(10001, DURATION_GET_MESSAGE);
        }
    }

    private void dealEnterMsg() {
        if (this.canShowEnterMsg) {
            ResultData resultData = (ResultData) this.enterMsgQueue.poll();
            if (resultData != null) {
                this.canShowEnterMsg = false;
                String h = com.tomatolive.library.utils.b.h(resultData.expGrade);
                if (!TextUtils.isEmpty(h)) {
                    handlerMainPost(new -$$Lambda$TomatoLiveActivity$5lJrSmoGejhXzAHUT5QK_EqE5_I(this, resultData, h));
                }
            }
        }
        if (this.enterMsgQueue != null && this.enterMsgQueue.size() > 0) {
            sendWorkHandlerEmptyMessage(10002);
        }
    }

    public static /* synthetic */ void lambda$dealEnterMsg$54(TomatoLiveActivity tomatoLiveActivity, ResultData resultData, String str) {
        tomatoLiveActivity.updateUserList(resultData);
        if (p.a(resultData.guardType) > 0 || p.a(str) >= p.a(tomatoLiveActivity.entryNoticeLevelThreshold) || com.tomatolive.library.utils.b.p(resultData.carId) || com.tomatolive.library.utils.b.k(resultData.role)) {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.setMsgType(0);
            chatEntity.setMsgSendName(resultData.userName);
            chatEntity.setExpGrade(str);
            chatEntity.setRole(resultData.role);
            chatEntity.setGuardType(p.a(resultData.guardType));
            chatEntity.setMsgText(tomatoLiveActivity.getString(R.string.fq_live_joinroom_notify));
            chatEntity.setUserAvatar(resultData.avatar);
            chatEntity.setUid(resultData.userId);
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
            tomatoLiveActivity.mLiveChatMsgView.addChatMsg(chatEntity);
        } else {
            tomatoLiveActivity.mLiveChatMsgView.setUserGradeInfo(str, resultData.userName);
        }
        q.a().b(tomatoLiveActivity.mContext, DURATION_GET_MESSAGE, TimeUnit.MILLISECONDS, new -$$Lambda$TomatoLiveActivity$pd-vP4b4uarh0qmgUQa3Xhr6tLw(tomatoLiveActivity));
    }

    private void updateUserList(ResultData resultData) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAvatar(resultData.avatar);
        userEntity.setUserId(resultData.userId);
        userEntity.setName(resultData.userName);
        userEntity.setSex(resultData.sex);
        userEntity.setExpGrade(com.tomatolive.library.utils.b.h(resultData.expGrade));
        userEntity.setGuardType(p.a(resultData.guardType));
        userEntity.setRole(resultData.role);
        if (!this.mLivePusherInfoView.contains(resultData.userId)) {
            this.mLivePusherInfoView.addUserItem(userEntity);
            this.mLivePusherInfoView.setRoomPerson(this.onLineCount.get());
        }
    }

    private void dealGiftBoxMsg(ResultData resultData) {
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

    public void onBackPressed() {
        stopPlay();
        stopSocket();
        super.onBackPressed();
    }

    private void dismissDialogs() {
        if (this.anchorAvatarDialog != null && this.anchorAvatarDialog.isAdded()) {
            this.anchorAvatarDialog.dismiss();
        }
        dismissUserAvatarDialog();
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.dismissDedicateTopDialog();
        }
        dismissInputMsgDialog();
        if (this.giftBottomDialog != null && this.giftBottomDialog.isAdded()) {
            this.giftBottomDialog.dismiss();
        }
        if (this.mTaskBottomDialog != null && this.mTaskBottomDialog.isAdded()) {
            this.mTaskBottomDialog.dismiss();
        }
    }

    private void clearAllMapData() {
        if (this.receiveMsgQueue != null) {
            this.receiveMsgQueue.clear();
        }
        if (this.enterMsgQueue != null) {
            this.enterMsgQueue.clear();
        }
        if (this.guardEnterMsgQueue != null) {
            this.guardEnterMsgQueue.clear();
        }
        if (this.guardOpenMsgQueue != null) {
            this.guardOpenMsgQueue.clear();
        }
        if (this.giftNoticeQueue != null) {
            this.giftNoticeQueue.clear();
        }
        if (this.sysNoticeQueue != null) {
            this.sysNoticeQueue.clear();
        }
        if (this.myGiftIndexMap != null) {
            this.myGiftIndexMap.clear();
        }
        if (this.myPropIndexMap != null) {
            this.myPropIndexMap.clear();
        }
        if (this.receivePropMap != null) {
            this.receivePropMap.clear();
        }
        if (this.receiveGiftMap != null) {
            this.receiveGiftMap.clear();
        }
        if (this.shieldedList != null) {
            this.shieldedList.clear();
        }
        if (this.bannedList != null) {
            this.bannedList.clear();
        }
    }

    public void onDestroy() {
        onReleaseViewData();
        onDestroyViewData();
        super.onDestroy();
    }

    private void onReleaseViewData() {
        if (this.firstLiveStatus) {
            ((TomatoLivePresenter) this.mPresenter).setEnterOrLeaveLiveRoomMsg(ConnectSocketParams.MESSAGE_LEAVE_TYPE);
        }
        k.a().b();
        stopSocket();
        stopPlay();
        clearAllMapData();
        if (this.playManager != null) {
            this.playManager.d();
            this.playManager = null;
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
        if (this.mTaskBoxView != null) {
            this.isFirstLoadTask = true;
            this.mTaskBoxView.release();
            this.mTaskBoxView = null;
            TaskConstance.getInstance().clear();
        }
        if (this.mTaskBottomDialog != null) {
            this.mTaskBottomDialog.onDestroy();
            this.mTaskBottomDialog = null;
        }
    }

    public void onDestroyViewData() {
        if (this.mInputTextMsgDialog != null) {
            this.mInputTextMsgDialog.onDestroy();
            this.mInputTextMsgDialog = null;
        }
        if (this.mLivePusherInfoView != null) {
            this.mLivePusherInfoView.onDestroy();
        }
        if (this.liveAnimationView != null) {
            this.liveAnimationView.onDestroy();
        }
        if (this.giftBottomDialog != null) {
            this.giftBottomDialog.release();
        }
        if (this.mLiveLoadingView != null) {
            this.mLiveLoadingView.onDestroy();
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

    public void onEventMainThread(BaseEvent baseEvent) {
        super.onEventMainThread(baseEvent);
        if (baseEvent instanceof UpdateBalanceEvent) {
            ((TomatoLivePresenter) this.mPresenter).getUserOver();
        }
    }
}
