package org.greenrobot.eventbus;

import android.util.Log;
import com.yalantis.ucrop.view.CropImageView;
import java.io.PrintStream;
import java.util.logging.Level;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* compiled from: Logger */
public interface f {

    /* compiled from: Logger */
    public static class a implements f {
        static final boolean a;
        private final String b;

        static {
            boolean z = false;
            try {
                if (Class.forName("android.util.Log") != null) {
                    z = true;
                }
            } catch (ClassNotFoundException unused) {
            }
            a = z;
        }

        public static boolean a() {
            return a;
        }

        public a(String str) {
            this.b = str;
        }

        public void a(Level level, String str) {
            if (level != Level.OFF) {
                Log.println(a(level), this.b, str);
            }
        }

        public void a(Level level, String str, Throwable th) {
            if (level != Level.OFF) {
                int a = a(level);
                String str2 = this.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("\n");
                stringBuilder.append(Log.getStackTraceString(th));
                Log.println(a, str2, stringBuilder.toString());
            }
        }

        /* Access modifiers changed, original: protected */
        public int a(Level level) {
            int intValue = level.intValue();
            if (intValue < 800) {
                return intValue < CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION ? 2 : 3;
            } else {
                if (intValue < IMediaPlayer.MEDIA_INFO_TIMED_TEXT_ERROR) {
                    return 4;
                }
                return intValue < 1000 ? 5 : 6;
            }
        }
    }

    /* compiled from: Logger */
    public static class b implements f {
        public void a(Level level, String str) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(level);
            stringBuilder.append("] ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
        }

        public void a(Level level, String str, Throwable th) {
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(level);
            stringBuilder.append("] ");
            stringBuilder.append(str);
            printStream.println(stringBuilder.toString());
            th.printStackTrace(System.out);
        }
    }

    void a(Level level, String str);

    void a(Level level, String str, Throwable th);
}
