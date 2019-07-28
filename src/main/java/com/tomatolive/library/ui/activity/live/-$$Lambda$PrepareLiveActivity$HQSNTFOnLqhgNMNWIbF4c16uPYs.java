package com.tomatolive.library.ui.activity.live;

import com.tomatolive.library.download.GiftDownLoadManager;
import java.util.List;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$PrepareLiveActivity$HQSNTFOnLqhgNMNWIbF4c16uPYs implements Runnable {
    private final /* synthetic */ List f$0;

    public /* synthetic */ -$$Lambda$PrepareLiveActivity$HQSNTFOnLqhgNMNWIbF4c16uPYs(List list) {
        this.f$0 = list;
    }

    public final void run() {
        GiftDownLoadManager.getInstance().updateLocalDownloadList(this.f$0);
    }
}
