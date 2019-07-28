package defpackage;

import com.one.tomato.thirdpart.video.player.PostVideoView;

/* compiled from: PostVideoViewManager */
/* renamed from: pp */
public class pp {
    private static pp b;
    private PostVideoView a;

    private pp() {
    }

    public static pp a() {
        if (b == null) {
            synchronized (pp.class) {
                if (b == null) {
                    b = new pp();
                }
            }
        }
        return b;
    }

    public void a(PostVideoView postVideoView) {
        this.a = postVideoView;
    }

    public void b() {
        if (this.a != null) {
            this.a.release();
            this.a = null;
        }
    }
}
