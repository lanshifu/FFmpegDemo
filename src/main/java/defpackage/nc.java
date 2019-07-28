package defpackage;

import android.content.Context;
import android.media.SoundPool;
import android.os.Handler;
import com.luck.picture.lib.R;

/* compiled from: VoiceUtils */
/* renamed from: nc */
public class nc {
    private static SoundPool a;
    private static int b;

    public static void a(Context context, final boolean z) {
        if (a == null) {
            a = new SoundPool(1, 4, 0);
            b = a.load(context, R.raw.music, 1);
        }
        new Handler().postDelayed(new Runnable() {
            public void run() {
                nc.a(z, nc.a);
            }
        }, 20);
    }

    public static void a(boolean z, SoundPool soundPool) {
        if (z) {
            soundPool.play(b, 0.1f, 0.5f, 0, 1, 1.0f);
        }
    }
}
