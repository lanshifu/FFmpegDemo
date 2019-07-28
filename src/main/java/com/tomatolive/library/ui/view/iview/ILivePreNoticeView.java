package com.tomatolive.library.ui.view.iview;

import com.tomatolive.library.base.b;
import com.tomatolive.library.model.LivePreNoticeEntity;

public interface ILivePreNoticeView extends b {
    void onDataSuccess(LivePreNoticeEntity livePreNoticeEntity);

    void onSaveSuccess();
}
