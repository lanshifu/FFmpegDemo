package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.o;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.ui.activity.home.WebViewActivity;
import com.tomatolive.library.ui.view.custom.GuardChangeTitleView;
import com.tomatolive.library.ui.view.widget.StateView;
import com.tomatolive.library.ui.view.widget.StateView.OnRetryClickListener;
import com.tomatolive.library.ui.view.widget.easypop.EasyPopup;
import com.tomatolive.library.utils.g;
import com.tomatolive.library.utils.i;
import com.tomatolive.library.utils.p;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import defpackage.wd;
import defpackage.xl;
import java.util.List;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation.CornerType;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class GuardOpenContentDialog extends BaseBottomDialogFragment {
    private static final String ANCHORID_KEY = "anchorId_key";
    private static final String SER_ITEM = "serItem";
    private String anchorId = "";
    private GuardItemEntity currentSelectGuardItem;
    private FrameLayout flAvatarBg;
    private EasyPopup giftPop;
    private LiveEntity guardItem;
    private EasyPopup identityPop;
    private ImageView ivCover;
    private ImageView ivGuardType;
    private EasyPopup joinPop;
    private StateView mStateView;
    private OnOpenGuardCallbackListener openGuardCallbackListener;
    private EasyPopup privilegePop;
    private TextView tvGift;
    private TextView tvIdentity;
    private TextView tvMoney;
    private GuardChangeTitleView tvMonth;
    private TextView tvOpen;
    private TextView tvPrivilege;
    private GuardChangeTitleView tvWeek;
    private GuardChangeTitleView tvYear;
    private String userOver = "0";

    public interface OnOpenGuardCallbackListener {
        void OnOpenGuardFail();

        void OnOpenGuardSuccess(GuardItemEntity guardItemEntity);
    }

    public double getHeightScale() {
        return -2.0d;
    }

    @SuppressLint({"ValidFragment"})
    private GuardOpenContentDialog() {
    }

    public static GuardOpenContentDialog newInstance(LiveEntity liveEntity, OnOpenGuardCallbackListener onOpenGuardCallbackListener) {
        Bundle bundle = new Bundle();
        GuardOpenContentDialog guardOpenContentDialog = new GuardOpenContentDialog();
        bundle.putSerializable(SER_ITEM, liveEntity);
        guardOpenContentDialog.setArguments(bundle);
        guardOpenContentDialog.setOpenGuardCallbackListener(onOpenGuardCallbackListener);
        return guardOpenContentDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_open_guard;
    }

    public void initView(View view) {
        this.guardItem = (LiveEntity) getArguments().getSerializable(SER_ITEM);
        if (this.guardItem != null) {
            this.anchorId = this.guardItem.anchorId;
        }
        this.ivCover = (ImageView) view.findViewById(R.id.iv_cover);
        this.tvWeek = (GuardChangeTitleView) view.findViewById(R.id.tv_week);
        this.tvMonth = (GuardChangeTitleView) view.findViewById(R.id.tv_month);
        this.tvYear = (GuardChangeTitleView) view.findViewById(R.id.tv_year);
        this.tvMoney = (TextView) view.findViewById(R.id.tv_money);
        this.tvIdentity = (TextView) view.findViewById(R.id.tv_identity);
        this.tvPrivilege = (TextView) view.findViewById(R.id.tv_privilege);
        this.tvGift = (TextView) view.findViewById(R.id.tv_gift);
        this.tvOpen = (TextView) view.findViewById(R.id.tv_open);
        this.ivGuardType = (ImageView) view.findViewById(R.id.iv_guard_type);
        this.flAvatarBg = (FrameLayout) view.findViewById(R.id.fl_avatar_bg);
        this.mStateView = StateView.inject((ViewGroup) view.findViewById(R.id.fl_content_view));
        setImgCover(R.drawable.fq_ic_guard_top_bg_1);
        initPop();
        getUserOver();
        sendRequest();
    }

    /* Access modifiers changed, original: protected */
    public void initListener(View view) {
        super.initListener(view);
        this.tvWeek.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GuardOpenContentDialog.this.initGuardViewInfo("1");
            }
        });
        this.tvMonth.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GuardOpenContentDialog.this.initGuardViewInfo(ConnectSocketParams.EFFECT_TYPE_BIG);
            }
        });
        this.tvYear.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GuardOpenContentDialog.this.initGuardViewInfo("3");
            }
        });
        this.tvIdentity.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (GuardOpenContentDialog.this.identityPop != null) {
                    GuardOpenContentDialog.this.identityPop.showAtAnchorView(GuardOpenContentDialog.this.tvIdentity, 1, 1);
                }
            }
        });
        view.findViewById(R.id.tv_join).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (GuardOpenContentDialog.this.joinPop != null) {
                    GuardOpenContentDialog.this.joinPop.showAtAnchorView(GuardOpenContentDialog.this.tvPrivilege, 1, 0);
                }
            }
        });
        this.tvPrivilege.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (GuardOpenContentDialog.this.privilegePop != null) {
                    GuardOpenContentDialog.this.privilegePop.showAtAnchorView(GuardOpenContentDialog.this.tvPrivilege, 1, 0);
                }
            }
        });
        this.tvGift.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (GuardOpenContentDialog.this.giftPop != null) {
                    GuardOpenContentDialog.this.giftPop.showAtAnchorView(GuardOpenContentDialog.this.tvGift, 1, 1);
                }
            }
        });
        this.tvOpen.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (GuardOpenContentDialog.this.tvWeek.isSelected()) {
                    GuardOpenContentDialog.this.currentSelectGuardItem = GuardOpenContentDialog.this.tvWeek.getGuardItemEntity();
                } else if (GuardOpenContentDialog.this.tvMonth.isSelected()) {
                    GuardOpenContentDialog.this.currentSelectGuardItem = GuardOpenContentDialog.this.tvMonth.getGuardItemEntity();
                } else if (GuardOpenContentDialog.this.tvYear.isSelected()) {
                    GuardOpenContentDialog.this.currentSelectGuardItem = GuardOpenContentDialog.this.tvYear.getGuardItemEntity();
                }
                if (GuardOpenContentDialog.this.guardItem == null || GuardOpenContentDialog.this.currentSelectGuardItem == null) {
                    o.a(R.string.fq_guard_select_type);
                } else if (p.c(GuardOpenContentDialog.this.currentSelectGuardItem.tomatoPrice) > p.c(GuardOpenContentDialog.this.userOver)) {
                    RechargeDialog.newInstance(GuardOpenContentDialog.this.mContext.getString(R.string.fq_guard_money_not_enough), new OnClickListener() {
                        public void onClick(View view) {
                            if (a.a().a != null) {
                                a.a().a.a(GuardOpenContentDialog.this.mContext);
                            }
                        }
                    }).show(GuardOpenContentDialog.this.getFragmentManager());
                } else {
                    GuardOpenContentDialog.this.guardItem.amount = GuardOpenContentDialog.this.currentSelectGuardItem.tomatoPrice;
                    GuardOpenContentDialog.this.guardItem.label = GuardOpenContentDialog.this.getGuardTypeStr(GuardOpenContentDialog.this.currentSelectGuardItem.name);
                    GuardOpenContentDialog.this.currentSelectGuardItem.expGrade = GuardOpenContentDialog.this.guardItem.expGrade;
                    int a = p.a(GuardOpenContentDialog.this.guardItem.userGuardType);
                    int a2 = p.a(GuardOpenContentDialog.this.currentSelectGuardItem.type);
                    if (a == 0) {
                        GuardOpenTipsDialog.newInstance(12, GuardOpenContentDialog.this.guardItem, new OnClickListener() {
                            public void onClick(View view) {
                                GuardOpenContentDialog.this.sendOpenRequest(GuardOpenContentDialog.this.currentSelectGuardItem);
                            }
                        }).show(GuardOpenContentDialog.this.getFragmentManager());
                    } else if (a == a2) {
                        GuardOpenTipsDialog.newInstance(13, GuardOpenContentDialog.this.guardItem, new OnClickListener() {
                            public void onClick(View view) {
                                GuardOpenContentDialog.this.sendOpenRequest(GuardOpenContentDialog.this.currentSelectGuardItem);
                            }
                        }).show(GuardOpenContentDialog.this.getFragmentManager());
                    } else if (a < a2) {
                        GuardOpenTipsDialog.newInstance(15, GuardOpenContentDialog.this.guardItem, new OnClickListener() {
                            public void onClick(View view) {
                                GuardOpenContentDialog.this.sendOpenRequest(GuardOpenContentDialog.this.currentSelectGuardItem);
                            }
                        }).show(GuardOpenContentDialog.this.getFragmentManager());
                    } else {
                        if (a > a2) {
                            GuardOpenTipsDialog.newInstance(14).show(GuardOpenContentDialog.this.getFragmentManager());
                        }
                    }
                }
            }
        });
        view.findViewById(R.id.tv_guard_rule).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GuardOpenContentDialog.this.mContext, WebViewActivity.class);
                intent.putExtra(OnNativeInvokeListener.ARG_URL, g.i("guard_rule.html"));
                intent.putExtra("title", GuardOpenContentDialog.this.getString(R.string.fq_guard_rule));
                GuardOpenContentDialog.this.mContext.startActivity(intent);
            }
        });
        this.tvMoney.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GuardOpenContentDialog.this.getUserOver();
            }
        });
        this.mStateView.setOnRetryClickListener(new OnRetryClickListener() {
            public void onRetryClick() {
                GuardOpenContentDialog.this.sendRequest();
            }
        });
    }

    private void setImgCover(@DrawableRes int i) {
        i.a(this.mContext, this.ivCover, i, 10, CornerType.TOP);
    }

    private void initPop() {
        this.identityPop = (EasyPopup) ((EasyPopup) ((EasyPopup) EasyPopup.create().setContentView(this.mContext, R.layout.fq_dialog_pop_guard_identity)).setFocusAndOutsideEnable(true)).apply();
        this.joinPop = (EasyPopup) ((EasyPopup) ((EasyPopup) EasyPopup.create().setContentView(this.mContext, R.layout.fq_dialog_pop_guard_join)).setFocusAndOutsideEnable(true)).apply();
        this.privilegePop = (EasyPopup) ((EasyPopup) ((EasyPopup) EasyPopup.create().setContentView(this.mContext, R.layout.fq_dialog_pop_guard_privilege)).setFocusAndOutsideEnable(true)).apply();
        this.giftPop = (EasyPopup) ((EasyPopup) ((EasyPopup) EasyPopup.create().setContentView(this.mContext, R.layout.fq_dialog_pop_guard_gift)).setFocusAndOutsideEnable(true)).apply();
    }

    private void sendRequest() {
        ApiRetrofit.getInstance().getApiService().getGuardListService(new RequestParams().getAppIdParams()).map(new ServerResultFunction<List<GuardItemEntity>>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<List<GuardItemEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<GuardItemEntity> list) {
                if (list != null) {
                    GuardOpenContentDialog.this.initGuardChangeTitleView(list);
                }
            }
        }, this.mStateView, true));
    }

    private void sendOpenRequest(GuardItemEntity guardItemEntity) {
        ApiRetrofit.getInstance().getApiService().getOpenGuardService(new RequestParams().getOpenGuardParams(guardItemEntity)).map(new ServerResultFunction<GuardItemEntity>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<GuardItemEntity>() {
            public void onSuccess(GuardItemEntity guardItemEntity) {
                if (GuardOpenContentDialog.this.openGuardCallbackListener != null) {
                    GuardOpenContentDialog.this.dismiss();
                    if (guardItemEntity == null || TextUtils.isEmpty(guardItemEntity.totResult)) {
                        GuardOpenContentDialog.this.openGuardCallbackListener.OnOpenGuardFail();
                        return;
                    }
                    GuardOpenContentDialog.this.openGuardCallbackListener.OnOpenGuardSuccess(guardItemEntity);
                }
            }

            public void onError(int i, String str) {
                if (GuardOpenContentDialog.this.openGuardCallbackListener != null) {
                    GuardOpenContentDialog.this.dismiss();
                    o.a(str);
                    GuardOpenContentDialog.this.openGuardCallbackListener.OnOpenGuardFail();
                }
            }
        }, true));
    }

    private void getUserOver() {
        this.tvMoney.setText(getString(R.string.fq_userover_loading));
        ApiRetrofit.getInstance().getApiService().getUserOverService(new RequestParams().getUserOverParams()).map(new ServerResultFunction<UserEntity>() {
        }).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a()).compose(bindToLifecycle()).subscribe(new HttpRxObserver(this.mContext, new ResultCallBack<UserEntity>() {
            public void onSuccess(UserEntity userEntity) {
                GuardOpenContentDialog.this.userOver = userEntity == null ? "0" : userEntity.getTotResult();
                GuardOpenContentDialog.this.tvMoney.setText(Html.fromHtml(GuardOpenContentDialog.this.mContext.getString(R.string.fq_my_money_str, new Object[]{GuardOpenContentDialog.this.userOver})));
            }

            public void onError(int i, String str) {
                GuardOpenContentDialog.this.tvMoney.setText(Html.fromHtml(GuardOpenContentDialog.this.mContext.getString(R.string.fq_my_money_str, new Object[]{GuardOpenContentDialog.this.getString(R.string.fq_userover_loading_fail)})));
            }
        }));
    }

    private void initGuardChangeTitleView(List<GuardItemEntity> list) {
        for (GuardItemEntity guardItemEntity : list) {
            String str = guardItemEntity.type;
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
                    if (!this.guardItem.isOnOpenWeekGuard()) {
                        this.tvWeek.setVisibility(0);
                        this.tvWeek.initData(guardItemEntity, this.anchorId);
                        initGuardViewInfo(this.guardItem.userGuardType);
                        break;
                    }
                    break;
                case 1:
                    this.tvMonth.setVisibility(0);
                    this.tvMonth.initData(guardItemEntity, this.anchorId);
                    initGuardViewInfo(this.guardItem.userGuardType);
                    break;
                case 2:
                    this.tvYear.setVisibility(0);
                    this.tvYear.initData(guardItemEntity, this.anchorId);
                    initGuardViewInfo(this.guardItem.userGuardType);
                    break;
                default:
                    break;
            }
        }
    }

    private void initBtnOpen(GuardItemEntity guardItemEntity) {
        if (guardItemEntity != null) {
            this.tvOpen.setText(p.a(this.guardItem.userGuardType) == p.a(guardItemEntity.type) ? R.string.fq_guard_renewal_fee : R.string.fq_guard_open);
        }
    }

    /* JADX WARNING: Missing block: B:27:0x0093, code skipped:
            if (r5.equals("1") != false) goto L_0x0097;
     */
    private void initGuardViewInfo(java.lang.String r5) {
        /*
        r4 = this;
        r0 = "3";
        r0 = android.text.TextUtils.equals(r5, r0);
        if (r0 == 0) goto L_0x000b;
    L_0x0008:
        r0 = com.tomatolive.library.R.drawable.fq_ic_guard_top_bg_2;
        goto L_0x000d;
    L_0x000b:
        r0 = com.tomatolive.library.R.drawable.fq_ic_guard_top_bg_1;
    L_0x000d:
        r4.setImgCover(r0);
        r0 = r4.flAvatarBg;
        r1 = "3";
        r1 = android.text.TextUtils.equals(r5, r1);
        if (r1 == 0) goto L_0x001d;
    L_0x001a:
        r1 = com.tomatolive.library.R.drawable.fq_shape_guard_avatar_bg_circle_2;
        goto L_0x001f;
    L_0x001d:
        r1 = com.tomatolive.library.R.drawable.fq_shape_guard_avatar_bg_circle_1;
    L_0x001f:
        r0.setBackgroundResource(r1);
        r0 = r4.ivGuardType;
        r1 = "3";
        r1 = android.text.TextUtils.equals(r5, r1);
        if (r1 == 0) goto L_0x002f;
    L_0x002c:
        r1 = com.tomatolive.library.R.drawable.fq_ic_live_msg_year_guard;
        goto L_0x0031;
    L_0x002f:
        r1 = com.tomatolive.library.R.drawable.fq_ic_live_msg_mouth_guard;
    L_0x0031:
        r0.setImageResource(r1);
        r0 = r4.tvWeek;
        r1 = "0";
        r1 = android.text.TextUtils.equals(r5, r1);
        r2 = 0;
        r3 = 1;
        if (r1 != 0) goto L_0x004b;
    L_0x0040:
        r1 = "1";
        r1 = android.text.TextUtils.equals(r5, r1);
        if (r1 == 0) goto L_0x0049;
    L_0x0048:
        goto L_0x004b;
    L_0x0049:
        r1 = 0;
        goto L_0x004c;
    L_0x004b:
        r1 = 1;
    L_0x004c:
        r0.showArrow(r1);
        r0 = r4.tvMonth;
        r1 = "2";
        r1 = android.text.TextUtils.equals(r5, r1);
        r0.showArrow(r1);
        r0 = r4.tvYear;
        r1 = "3";
        r1 = android.text.TextUtils.equals(r5, r1);
        r0.showArrow(r1);
        r0 = r4.tvPrivilege;
        r1 = "3";
        r1 = android.text.TextUtils.equals(r5, r1);
        r0.setSelected(r1);
        r0 = -1;
        r1 = r5.hashCode();
        switch(r1) {
            case 49: goto L_0x008d;
            case 50: goto L_0x0083;
            case 51: goto L_0x0079;
            default: goto L_0x0078;
        };
    L_0x0078:
        goto L_0x0096;
    L_0x0079:
        r1 = "3";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0096;
    L_0x0081:
        r2 = 2;
        goto L_0x0097;
    L_0x0083:
        r1 = "2";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0096;
    L_0x008b:
        r2 = 1;
        goto L_0x0097;
    L_0x008d:
        r1 = "1";
        r5 = r5.equals(r1);
        if (r5 == 0) goto L_0x0096;
    L_0x0095:
        goto L_0x0097;
    L_0x0096:
        r2 = -1;
    L_0x0097:
        switch(r2) {
            case 0: goto L_0x00af;
            case 1: goto L_0x00a5;
            case 2: goto L_0x009b;
            default: goto L_0x009a;
        };
    L_0x009a:
        goto L_0x00b8;
    L_0x009b:
        r5 = r4.tvYear;
        r5 = r5.getGuardItemEntity();
        r4.initBtnOpen(r5);
        goto L_0x00b8;
    L_0x00a5:
        r5 = r4.tvMonth;
        r5 = r5.getGuardItemEntity();
        r4.initBtnOpen(r5);
        goto L_0x00b8;
    L_0x00af:
        r5 = r4.tvWeek;
        r5 = r5.getGuardItemEntity();
        r4.initBtnOpen(r5);
    L_0x00b8:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.ui.view.dialog.GuardOpenContentDialog.initGuardViewInfo(java.lang.String):void");
    }

    private void setOpenGuardCallbackListener(OnOpenGuardCallbackListener onOpenGuardCallbackListener) {
        this.openGuardCallbackListener = onOpenGuardCallbackListener;
    }

    private String getGuardTypeStr(String str) {
        if (this.currentSelectGuardItem == null) {
            return str;
        }
        String str2 = this.currentSelectGuardItem.type;
        Object obj = -1;
        switch (str2.hashCode()) {
            case 49:
                if (str2.equals("1")) {
                    obj = null;
                    break;
                }
                break;
            case 50:
                if (str2.equals(ConnectSocketParams.EFFECT_TYPE_BIG)) {
                    obj = 1;
                    break;
                }
                break;
            case 51:
                if (str2.equals("3")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return this.mContext.getString(R.string.fq_guard_week_money);
            case 1:
                return this.mContext.getString(R.string.fq_guard_month_money);
            case 2:
                return this.mContext.getString(R.string.fq_guard_year_money);
            default:
                return str;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.openGuardCallbackListener != null) {
            this.openGuardCallbackListener = null;
        }
    }
}
