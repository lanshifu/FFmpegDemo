package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface IHomeHotView extends b {
    void onAnchorAuthSuccess(AnchorEntity anchorEntity);

    void onBannerListFail();

    void onBannerListSuccess(List<BannerEntity> list);

    void onLiveListFail();

    void onLiveListSuccess(List<LiveEntity> list, boolean z, boolean z2);

    void onTopListFail();

    void onTopListSuccess(List<AnchorEntity> list);
}
