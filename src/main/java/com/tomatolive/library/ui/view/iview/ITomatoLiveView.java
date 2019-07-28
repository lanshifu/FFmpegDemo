package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.GiftDownloadItemEntity;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.model.LiveEndEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.LiveInitInfoEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import java.util.List;

public interface ITomatoLiveView extends b {
    void onAnchorInfoFail(int i, String str);

    void onAnchorInfoSuccess(AnchorEntity anchorEntity);

    void onAttentionSuccess();

    void onGiftBoxListSuccess(List<GiftBoxEntity> list);

    void onGiftListFail();

    void onGiftListSuccess(List<GiftDownloadItemEntity> list);

    void onLiveAdListFail(String str);

    void onLiveAdListSuccess(String str, List<BannerEntity> list);

    void onLiveAdNoticeSuccess(BannerEntity bannerEntity);

    void onLiveAudiencesSuccess(OnLineUsersEntity onLineUsersEntity);

    void onLiveEndInfoFail();

    void onLiveEndInfoSuccess(LiveEndEntity liveEndEntity);

    void onLiveInitInfoFail(int i);

    void onLiveInitInfoSuccess(LiveInitInfoEntity liveInitInfoEntity);

    void onLivePopularitySuccess(long j);

    void onPersonalGuardInfoFail();

    void onPersonalGuardInfoSuccess(GuardItemEntity guardItemEntity);

    void onTaskChangeFail(TaskBoxEntity taskBoxEntity);

    void onTaskChangeSuccess(TaskBoxEntity taskBoxEntity);

    void onTaskListFail();

    void onTaskListSuccess(List<TaskBoxEntity> list, boolean z);

    void onTaskTakeFail();

    void onTaskTakeSuccess(TaskBoxEntity taskBoxEntity);

    void onUserOverFail();

    void onUserOverSuccess(String str);

    void onWebSocketAddressFail();

    void onWebSocketAddressSuccess(LiveEntity liveEntity);
}
