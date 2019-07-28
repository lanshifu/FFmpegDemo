package defpackage;

import com.one.tomato.thirdpart.video.player.VideoDownloadView;

/* compiled from: VideoDownloadViewManager */
/* renamed from: pq */
public class pq {
    private static pq b;
    private VideoDownloadView a;

    private pq() {
    }

    public static pq a() {
        if (b == null) {
            synchronized (pq.class) {
                if (b == null) {
                    b = new pq();
                }
            }
        }
        return b;
    }

    public void a(VideoDownloadView videoDownloadView) {
        this.a = videoDownloadView;
    }

    public void b() {
        if (this.a != null) {
            this.a.release();
            this.a = null;
        }
    }
}
