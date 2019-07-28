package com.tomatolive.library.ui.view.widget.titlebar;

import android.view.View;
import android.view.View.OnClickListener;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

public abstract class BGAOnNoDoubleClickListener implements OnClickListener {
    private long mLastClickTime = 0;
    private int mThrottleFirstTime = IjkMediaCodecInfo.RANK_LAST_CHANCE;

    public abstract void onNoDoubleClick(View view);

    public BGAOnNoDoubleClickListener(int i) {
        this.mThrottleFirstTime = i;
    }

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastClickTime > ((long) this.mThrottleFirstTime)) {
            this.mLastClickTime = currentTimeMillis;
            onNoDoubleClick(view);
        }
    }
}
