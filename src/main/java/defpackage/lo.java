package defpackage;

import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.util.Log;
import com.google.android.exoplayer2.text.b;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;

/* compiled from: WebvttCue */
/* renamed from: lo */
public final class lo extends b {
    public final long o;
    public final long p;

    /* compiled from: WebvttCue */
    /* renamed from: lo$1 */
    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a = new int[Alignment.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        static {
            /*
            r0 = android.text.Layout.Alignment.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = android.text.Layout.Alignment.ALIGN_NORMAL;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = android.text.Layout.Alignment.ALIGN_CENTER;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = android.text.Layout.Alignment.ALIGN_OPPOSITE;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.lo$1.<clinit>():void");
        }
    }

    /* compiled from: WebvttCue */
    /* renamed from: lo$a */
    public static class a {
        private long a;
        private long b;
        private SpannableStringBuilder c;
        private Alignment d;
        private float e;
        private int f;
        private int g;
        private float h;
        private int i;
        private float j;

        public a() {
            a();
        }

        public void a() {
            this.a = 0;
            this.b = 0;
            this.c = null;
            this.d = null;
            this.e = Float.MIN_VALUE;
            this.f = CheckView.UNCHECKED;
            this.g = CheckView.UNCHECKED;
            this.h = Float.MIN_VALUE;
            this.i = CheckView.UNCHECKED;
            this.j = Float.MIN_VALUE;
        }

        public lo b() {
            if (this.h != Float.MIN_VALUE && this.i == CheckView.UNCHECKED) {
                c();
            }
            return new lo(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j);
        }

        public a a(long j) {
            this.a = j;
            return this;
        }

        public a b(long j) {
            this.b = j;
            return this;
        }

        public a a(SpannableStringBuilder spannableStringBuilder) {
            this.c = spannableStringBuilder;
            return this;
        }

        public a a(Alignment alignment) {
            this.d = alignment;
            return this;
        }

        public a a(float f) {
            this.e = f;
            return this;
        }

        public a a(int i) {
            this.f = i;
            return this;
        }

        public a b(int i) {
            this.g = i;
            return this;
        }

        public a b(float f) {
            this.h = f;
            return this;
        }

        public a c(int i) {
            this.i = i;
            return this;
        }

        public a c(float f) {
            this.j = f;
            return this;
        }

        private a c() {
            if (this.d != null) {
                switch (1.a[this.d.ordinal()]) {
                    case 1:
                        this.i = 0;
                        break;
                    case 2:
                        this.i = 1;
                        break;
                    case 3:
                        this.i = 2;
                        break;
                    default:
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unrecognized alignment: ");
                        stringBuilder.append(this.d);
                        Log.w("WebvttCueBuilder", stringBuilder.toString());
                        this.i = 0;
                        break;
                }
            }
            this.i = CheckView.UNCHECKED;
            return this;
        }
    }

    public lo(CharSequence charSequence) {
        this(0, 0, charSequence);
    }

    public lo(long j, long j2, CharSequence charSequence) {
        this(j, j2, charSequence, null, Float.MIN_VALUE, CheckView.UNCHECKED, CheckView.UNCHECKED, Float.MIN_VALUE, CheckView.UNCHECKED, Float.MIN_VALUE);
    }

    public lo(long j, long j2, CharSequence charSequence, Alignment alignment, float f, int i, int i2, float f2, int i3, float f3) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3);
        this.o = j;
        this.p = j2;
    }

    public boolean a() {
        return this.d == Float.MIN_VALUE && this.g == Float.MIN_VALUE;
    }
}
