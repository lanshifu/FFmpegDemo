package com.tencent.liteav.e;

import android.os.HandlerThread;
import com.tencent.liteav.basic.log.TXCLog;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: VideoThumbnailGenerate */
public class af extends d {
    private final String V;
    private boolean W;

    public af() {
        this.V = "VideoDecAndDemuxGenerateGivenTimes";
        this.S = 0;
        this.T = 0;
        this.U = new AtomicBoolean(true);
        this.R = new ArrayList();
        this.z = new HandlerThread("video_handler_thread");
        this.z.start();
        this.y = new b(this.z.getLooper());
    }

    /* Access modifiers changed, original: protected */
    public void l() {
        if (this.x.get() == 2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("start ignore, state = ");
            stringBuilder.append(this.x.get());
            TXCLog.e("VideoDecAndDemuxGenerateGivenTimes", stringBuilder.toString());
            return;
        }
        if (this.y != null) {
            if (this.W) {
                this.y.sendEmptyMessage(5);
            } else {
                this.y.sendEmptyMessage(8);
            }
        }
        this.x.set(2);
    }

    /* Access modifiers changed, original: protected */
    public void m() {
        if (this.x.get() == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("stop ignore, mCurrentState = ");
            stringBuilder.append(this.x.get());
            TXCLog.e("VideoDecAndDemuxGenerateGivenTimes", stringBuilder.toString());
            return;
        }
        if (this.y != null) {
            this.y.sendEmptyMessage(7);
        }
    }

    public synchronized void p() {
        this.U.set(true);
    }

    public void k() {
        if (this.z != null) {
            this.z.quit();
            this.y = null;
        }
    }

    public void a(boolean z) {
        this.W = z;
    }
}
