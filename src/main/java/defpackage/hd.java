package defpackage;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.exoplayer2.metadata.id3.a.a;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: GaplessInfoHolder */
/* renamed from: hd */
public final class hd {
    public static final a a = new 1();
    private static final Pattern d = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int b = -1;
    public int c = -1;

    /* compiled from: GaplessInfoHolder */
    /* renamed from: hd$1 */
    static class 1 implements a {
        public boolean a(int i, int i2, int i3, int i4, int i5) {
            return i2 == 67 && i3 == 79 && i4 == 77 && (i5 == 77 || i == 2);
        }

        1() {
        }
    }

    public boolean a(int i) {
        int i2 = i >> 12;
        i &= 4095;
        if (i2 <= 0 && i <= 0) {
            return false;
        }
        this.b = i2;
        this.c = i;
        return true;
    }

    public boolean a(Metadata metadata) {
        for (int i = 0; i < metadata.a(); i++) {
            Entry a = metadata.a(i);
            if (a instanceof CommentFrame) {
                CommentFrame commentFrame = (CommentFrame) a;
                if ("iTunSMPB".equals(commentFrame.b) && a(commentFrame.c)) {
                    return true;
                }
            } else if (a instanceof InternalFrame) {
                InternalFrame internalFrame = (InternalFrame) a;
                if ("com.apple.iTunes".equals(internalFrame.a) && "iTunSMPB".equals(internalFrame.b) && a(internalFrame.c)) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private boolean a(String str) {
        Matcher matcher = d.matcher(str);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1), 16);
                int parseInt2 = Integer.parseInt(matcher.group(2), 16);
                if (parseInt > 0 || parseInt2 > 0) {
                    this.b = parseInt;
                    this.c = parseInt2;
                    return true;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    public boolean a() {
        return (this.b == -1 || this.c == -1) ? false : true;
    }
}
