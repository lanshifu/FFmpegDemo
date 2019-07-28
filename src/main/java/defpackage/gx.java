package defpackage;

import com.google.android.exoplayer2.extractor.flv.b;
import com.google.android.exoplayer2.extractor.mp4.d;
import com.google.android.exoplayer2.extractor.mp4.f;
import java.lang.reflect.Constructor;

/* compiled from: DefaultExtractorsFactory */
/* renamed from: gx */
public final class gx implements hc {
    private static final Constructor<? extends gz> a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f = 1;
    private int g;

    static {
        Constructor constructor;
        try {
            constructor = Class.forName("com.google.android.exoplayer2.ext.flac.FlacExtractor").asSubclass(gz.class).getConstructor(new Class[0]);
        } catch (ClassNotFoundException unused) {
            constructor = null;
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating FLAC extension", e);
        }
        a = constructor;
    }

    public synchronized gz[] a() {
        gz[] gzVarArr;
        gzVarArr = new gz[(a == null ? 12 : 13)];
        gzVarArr[0] = new ho(this.b);
        gzVarArr[1] = new d(this.d);
        gzVarArr[2] = new f(this.c);
        gzVarArr[3] = new hs(this.e);
        gzVarArr[4] = new ij();
        gzVarArr[5] = new ih();
        gzVarArr[6] = new jc(this.f, this.g);
        gzVarArr[7] = new b();
        gzVarArr[8] = new hx();
        gzVarArr[9] = new ix();
        gzVarArr[10] = new je();
        gzVarArr[11] = new hk();
        if (a != null) {
            try {
                gzVarArr[12] = (gz) a.newInstance(new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("Unexpected error creating FLAC extractor", e);
            }
        }
        return gzVarArr;
    }
}
