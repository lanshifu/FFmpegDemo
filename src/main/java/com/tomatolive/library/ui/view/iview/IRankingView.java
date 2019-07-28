package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.AnchorEntity;
import java.util.List;

public interface IRankingView extends b {
    void onAttentionSuccess();

    void onCharmTopListSuccess(List<AnchorEntity> list, int i, boolean z);

    void onRankConfigFail();

    void onRankConfigSuccess(List<String> list);

    void onStrengthTopListSuccess(List<AnchorEntity> list, int i, boolean z);
}
