package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.LiveEntity;
import java.util.List;

public interface IHomeAttentionView extends b {
    void onAttentionListFail();

    void onAttentionListSuccess(List<LiveEntity> list, boolean z, boolean z2);

    void onAttentionSuccess();

    void onRecommendListFail();

    void onRecommendListSuccess(List<AnchorEntity> list);
}
