package defpackage;

import com.one.tomato.thirdpart.video.player.PapaVideoView;

/* compiled from: PapaVideoViewManager */
/* renamed from: po */
public class po {
    private static po b;
    private PapaVideoView a;

    private po() {
    }

    public static po a() {
        if (b == null) {
            synchronized (po.class) {
                if (b == null) {
                    b = new po();
                }
            }
        }
        return b;
    }

    public void a(PapaVideoView papaVideoView) {
        this.a = papaVideoView;
    }

    public void b() {
        if (this.a != null) {
            this.a.release();
            this.a = null;
        }
    }
}
