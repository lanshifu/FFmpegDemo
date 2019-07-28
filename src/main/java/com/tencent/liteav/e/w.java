package com.tencent.liteav.e;

import android.os.HandlerThread;
import com.tencent.liteav.basic.log.TXCLog;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: VideoDecAndDemuxGenerateGivenTimes */
public class w extends d {
    private final String V;

    public void a(boolean z) {
    }

    public w() {
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
        a(this.g.get());
        this.y.sendEmptyMessage(5);
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
        this.y.sendEmptyMessage(7);
    }

    public synchronized void p() {
        this.U.set(true);
    }

    public void k() {
        if (this.z != null) {
            this.z.quit();
        }
    }
}
