package com.tomatolive.library.ui.presenter;

import android.content.Context;
import com.tomatolive.library.base.a;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.CoverEntity;
import com.tomatolive.library.model.GiftDownloadListEntity;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.LiveInitInfoEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import com.tomatolive.library.ui.view.iview.IPrepareLiveView;
import com.tomatolive.library.utils.p;
import java.util.List;

public class PrePareLivePresenter extends a<IPrepareLiveView> {
    public PrePareLivePresenter(Context context, IPrepareLiveView iPrepareLiveView) {
        super(context, iPrepareLiveView);
    }

    public void startLive(String str, String str2, String str3, String str4) {
        addMapSubscription(this.mApiService.getStartLiveService(new RequestParams().getStartLiveParams(str, str2, str3, str4)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEntity>() {
            public void onSuccess(LiveEntity liveEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onStartLiveSuccess(liveEntity);
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onStartLiveFail();
            }
        }, true));
    }

    public void getTagList() {
        addMapSubscription(this.mApiService.getLabelListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<List<LabelEntity>>() {
            public void onSuccess(List<LabelEntity> list) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onTagListSuccess(list);
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onTagListFail();
            }
        }, false));
    }

    public void getPreStartLiveInfo(final boolean z) {
        addMapSubscription(this.mApiService.getPreStartLiveInfoService(new RequestParams().getPreStartLiveInfoParams()), new HttpRxObserver(getContext(), new ResultCallBack<CoverEntity>() {
            public void onSuccess(CoverEntity coverEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onPreStartLiveInfoSuccess(coverEntity, z);
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onPreStartLiveInfoFail();
            }
        }));
    }

    public void getAnchorInfo(String str) {
        addMapSubscription(this.mApiService.getAnchorInfoService(new RequestParams().getAnchorInfoParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<AnchorEntity>() {
            public void onSuccess(AnchorEntity anchorEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onAnchorInfoSuccess(anchorEntity);
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onAnchorInfoFail(i, str);
            }
        }, false));
    }

    public void getLiveEndInfo(String str, String str2, final int i) {
        addMapSubscription(this.mApiService.getAnchorLiveDetailService(new RequestParams().getAnchorLiveInfoParams(str, str2)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEntity>() {
            public void onSuccess(LiveEntity liveEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onLiveEndSuccess(i, liveEntity);
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onLiveEndFail();
            }
        }, false));
    }

    public void attentionAnchor(String str, int i) {
        addMapSubscription(this.mApiService.getAttentionAnchorService(new RequestParams().getAttentionAnchorParams(str, i)), new HttpRxObserver(getContext(), new ResultCallBack<Object>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onAttentionSuccess();
            }
        }, false));
    }

    public void getGiftList(int i) {
        addMapSubscription(this.mApiService.giftList(new RequestParams().getGiftListParams(i)), new HttpRxObserver(getContext(), new ResultCallBack<GiftDownloadListEntity>() {
            public void onSuccess(GiftDownloadListEntity giftDownloadListEntity) {
                if (giftDownloadListEntity != null) {
                    ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onGiftListSuccess(giftDownloadListEntity.list);
                }
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onGiftListFail();
            }
        }));
    }

    public void getCurrentOnlineUserList(String str) {
        addMapSubscription(this.mApiService.getCurrentOnlineUserListService(new RequestParams().getCurrentOnlineUserList(str)), new HttpRxObserver(getContext(), new ResultCallBack<OnLineUsersEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(OnLineUsersEntity onLineUsersEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onLiveAudiencesSuccess(onLineUsersEntity);
            }
        }, false));
    }

    public void getLiveInitInfo(String str, String str2, String str3) {
        addMapSubscription(this.mApiService.getLiveInitInfoService(new RequestParams().getLiveInitInfoParams(str, str2, str3)), new HttpRxObserver(getContext(), new ResultCallBack<LiveInitInfoEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(LiveInitInfoEntity liveInitInfoEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onLiveInitInfoSuccess(liveInitInfoEntity == null ? null : liveInitInfoEntity.liveInitInfo);
            }
        }, false));
    }

    public void uploadErrorReport(String str) {
        addMapSubscription(this.mApiService.getUploadErrorReportService(new RequestParams().getErrorReportParams(str)), new HttpRxObserver(getContext(), new ResultCallBack() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
            }
        }));
    }

    public void setEnterOrLeaveLiveRoomMsg(String str, String str2) {
        addMapSubscription(this.mApiService.getLiveActionService(new RequestParams().getEnterLiveRoomParams(str, str2)), new HttpRxObserver(getContext(), new ResultCallBack() {
            public void onError(int i, String str) {
            }

            public void onSuccess(Object obj) {
            }
        }, false));
    }

    public void getLiveAdNoticeList() {
        addMapSubscription(this.mApiService.getNoticeListService(new RequestParams().getDefaultParams()), new HttpRxObserver(getContext(), new ResultCallBack<BannerEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(BannerEntity bannerEntity) {
                if (bannerEntity != null && PrePareLivePresenter.this.getView() != null) {
                    ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onLiveAdNoticeSuccess(bannerEntity);
                }
            }
        }));
    }

    public void getWebSocketAddress(String str, String str2, String str3) {
        addMapSubscription(this.mApiService.getWebSocketAddressService(new RequestParams().getWebSocketAddressParams(str, str2, str3)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEntity>() {
            public void onSuccess(LiveEntity liveEntity) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onWebSocketAddressSuccess(liveEntity);
            }

            public void onError(int i, String str) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onWebSocketAddressFail();
            }
        }));
    }

    public void getLivePopularity(String str) {
        addMapSubscription(this.mApiService.getLivePopularityService(new RequestParams().getCurrentOnlineUserList(str)), new HttpRxObserver(getContext(), new ResultCallBack<LiveEntity>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(LiveEntity liveEntity) {
                if (liveEntity != null) {
                    ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onLivePopularitySuccess(p.b(liveEntity.popularity));
                }
            }
        }));
    }

    public void getGiftBoxList(String str) {
        addMapSubscription(this.mApiService.getGiftBoxListService(new RequestParams().getGiftBoxListParams(str)), new HttpRxObserver(getContext(), new ResultCallBack<List<GiftBoxEntity>>() {
            public void onError(int i, String str) {
            }

            public void onSuccess(List<GiftBoxEntity> list) {
                ((IPrepareLiveView) PrePareLivePresenter.this.getView()).onGiftBoxListSuccess(list);
            }
        }));
    }
}
