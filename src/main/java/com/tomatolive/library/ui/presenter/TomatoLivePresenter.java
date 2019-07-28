package com.tomatolive.library.ui.presenter;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.util.k;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.GiftDownloadListEntity;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.model.LiveEndEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.LiveInitInfoEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import com.tomatolive.library.ui.view.iview.ITomatoLiveView;
import com.tomatolive.library.utils.p;
import java.util.List;

public class TomatoLivePresenter extends a<ITomatoLiveView> {
    public TomatoLivePresenter(Context context, ITomatoLiveView iTomatoLiveView) {
        super(context, iTomatoLiveView);
    }

    public void setEnterOrLeaveLiveRoomMsg(String str) {
        if (TextUtils.equals("enter", str)) {
            k.a().a("enter_time", System.currentTimeMillis());
        } else {
            if (((int) (((System.currentTimeMillis() - k.a().c("enter_time")) / 1000) / 60)) < 10) {
                return;
            }
        }
        addMapSubscription(this.mApiService.getLiveActionService(new RequestParams().getLeaveLiveRoomParams(str)), new HttpRxObserver(getContext(), new ResultCallBack() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
            }
        }, false));
    }

    public void getGiftList(int i) {
        addMapSubscription(this.mApiService.giftList(new RequestParams().getGiftListParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<GiftDownloadListEntity>() {
            public void onSuccess(GiftDownloadListEntity giftDownloadListEntity) {
                if (giftDownloadListEntity != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onGiftListSuccess(giftDownloadListEntity.list);
                }
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onGiftListFail();
            }
        }));
    }

    public void getLiveEndInfo(String str, String str2, String str3) {
        addMapSubscription(this.mApiService.getLiveEndInfoService(new RequestParams().getLiveEndInfoParams(str, str2, str3)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEndEntity>() {
            public void onSuccess(LiveEndEntity liveEndEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveEndInfoSuccess(liveEndEntity);
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveEndInfoFail();
            }
        }, false));
    }

    public void attentionAnchor(String str, int i) {
        addMapSubscription(this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onAttentionSuccess();
            }
        }, false));
    }

    public void getAnchorInfo(String str) {
        addMapSubscription(this.mApiService.getAnchorInfoService(new RequestParams().getAnchorInfoParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onSuccess(AnchorEntity anchorEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onAnchorInfoSuccess(anchorEntity);
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onAnchorInfoFail(i, str);
            }
        }, false));
    }

    public void getUserOver() {
        addMapSubscription(this.mApiService.getUserOverService(new RequestParams().getUserOverParams()), new HttpRxObserver(getContext(), new ResultCallBack<UserEntity>() {
            public void onSuccess(UserEntity userEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onUserOverSuccess(userEntity == null ? "0" : userEntity.getTotResult());
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onUserOverFail();
            }
        }));
    }

    public void getCurrentOnlineUserList(String str) {
        addMapSubscription(this.mApiService.getCurrentOnlineUserListService(new RequestParams().getCurrentOnlineUserList(str)), new HttpRxObserver(getContext(), new ResultCallBack<OnLineUsersEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(OnLineUsersEntity onLineUsersEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveAudiencesSuccess(onLineUsersEntity);
            }
        }, false));
    }

    public void getLiveInitInfo(String str, String str2, String str3) {
        addMapSubscription(this.mApiService.getLiveInitInfoService(new RequestParams().getLiveInitInfoParams(str, str2, str3)), new HttpRxObserver(getContext(), new ResultCallBack<LiveInitInfoEntity>() {
            public void onSuccess(LiveInitInfoEntity liveInitInfoEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveInitInfoSuccess(liveInitInfoEntity);
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveInitInfoFail(i);
            }
        }), 3, 3);
    }

    public void getAdImageList(final String str) {
        addMapSubscription(this.mApiService.getBannerListService(new RequestParams().getBannerListParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<BannerEntity>>() {
            public void onSuccess(List<BannerEntity> list) {
                if (list != null && TomatoLivePresenter.this.getView() != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveAdListSuccess(str, list);
                }
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveAdListFail(str);
            }
        }));
    }

    public void getLiveAdNoticeList() {
        addMapSubscription(this.mApiService.getNoticeListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<BannerEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(BannerEntity bannerEntity) {
                if (bannerEntity != null && TomatoLivePresenter.this.getView() != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLiveAdNoticeSuccess(bannerEntity);
                }
            }
        }));
    }

    public void getBroadcastClick(String str) {
        addMapSubscription(this.mApiService.broadcastClickCountUpdate(new RequestParams().getBroadcastClickParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
            }
        }));
    }

    public void getPersonalGuardInfo(String str) {
        addMapSubscription(this.mApiService.getMyGuardInfoService(new RequestParams().getPersonalGuardInfoParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<GuardItemEntity>() {
            public void onSuccess(GuardItemEntity guardItemEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onPersonalGuardInfoSuccess(guardItemEntity);
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onPersonalGuardInfoFail();
            }
        }));
    }

    public void getWebSocketAddress(String str, String str2, String str3) {
        addMapSubscription(this.mApiService.getWebSocketAddressService(new RequestParams().getWebSocketAddressParams(str, str2, str3)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEntity>() {
            public void onSuccess(LiveEntity liveEntity) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onWebSocketAddressSuccess(liveEntity);
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onWebSocketAddressFail();
            }
        }));
    }

    public void getLivePopularity(String str) {
        addMapSubscription(this.mApiService.getLivePopularityService(new RequestParams().getCurrentOnlineUserList(str)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(LiveEntity liveEntity) {
                if (liveEntity != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onLivePopularitySuccess(p.b(liveEntity.popularity));
                }
            }
        }));
    }

    public void getGiftBoxList(String str) {
        addMapSubscription(this.mApiService.getGiftBoxListService(new RequestParams().getGiftBoxListParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<GiftBoxEntity>>() {
            public void onSuccess(List<GiftBoxEntity> list) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onGiftBoxListSuccess(list);
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskListFail();
            }
        }));
    }

    public void getTaskList(final boolean z) {
        addMapSubscription(this.mApiService.getTaskBoxList(new RequestParams().getTaskBoxListParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<TaskBoxEntity>>() {
            public void onSuccess(List<TaskBoxEntity> list) {
                if (list != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskListSuccess(list, z);
                }
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskListFail();
            }
        }));
    }

    public void getTaskTake(final TaskBoxEntity taskBoxEntity) {
        addMapSubscription(this.mApiService.getTaskBoxTake(new RequestParams().getTaskChangeParams(taskBoxEntity.getId())), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onSuccess(Object obj) {
                if (obj != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskTakeSuccess(taskBoxEntity);
                }
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskTakeFail();
            }
        }));
    }

    public void changeTaskState(final TaskBoxEntity taskBoxEntity) {
        addMapSubscription(this.mApiService.changeTaskState(new RequestParams().getTaskChangeParams(taskBoxEntity.getId())), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onSuccess(Object obj) {
                if (obj != null) {
                    ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskChangeSuccess(taskBoxEntity);
                }
            }

            public void onError(int i, String str) {
                ((ITomatoLiveView) TomatoLivePresenter.this.getView()).onTaskChangeFail(taskBoxEntity);
            }
        }), 3, 1);
    }
}
