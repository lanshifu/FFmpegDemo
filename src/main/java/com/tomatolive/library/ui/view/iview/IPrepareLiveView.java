package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.CoverEntity;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import java.util.List;

public interface IPrepareLiveView extends b {
    void onAnchorInfoFail(int i, String str);

    void onAnchorInfoSuccess(AnchorEntity anchorEntity);

    void onAttentionSuccess();

    void onGiftBoxListSuccess(List<GiftBoxEntity> list);

    void onGiftListFail();

    void onGiftListSuccess(List<GiftDownloadItemEntity> list);

    void onLiveAdNoticeSuccess(BannerEntity bannerEntity);

    void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity);

    void onLiveEndFail();

    void onLiveEndSuccess(int i, LiveEntity liveEntity);

    void onLiveInitInfoSuccess(LiveEntity liveEntity);

    void onLivePopularitySuccess(long j);

    void onPreStartLiveInfoFail();

    void onPreStartLiveInfoSuccess(CoverEntity coverEntity, boolean z);

    void onStartLiveFail();

    void onStartLiveSuccess(LiveEntity liveEntity);

    void onTagListFail();

    void onTagListSuccess(List<LabelEntity> list);

    void onWebSocketAddressFail();

    void onWebSocketAddressSuccess(LiveEntity liveEntity);
}
