package kotlin.collections;

import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.jvm.internal.f;

/* compiled from: _ArraysJvm.kt */
class g extends f {

    /* compiled from: _ArraysJvm.kt */
    public static final class a extends b<Byte> implements RandomAccess {
        final /* synthetic */ byte[] b;

        a(byte[] bArr) {
            this.b = bArr;
        }

        public final boolean contains(Object obj) {
            return obj instanceof Byte ? a(((Number) obj).byteValue()) : false;
        }

        public final int indexOf(Object obj) {
            return obj instanceof Byte ? b(((Number) obj).byteValue()) : -1;
        }

        public final int lastIndexOf(Object obj) {
            return obj instanceof Byte ? c(((Number) obj).byteValue()) : -1;
        }

        public int a() {
            return this.b.length;
        }

        public boolean isEmpty() {
            return this.b.length == 0;
        }

        public boolean a(byte b) {
            return h.a(this.b, b);
        }

        /* renamed from: a */
        public Byte get(int i) {
            return Byte.valueOf(this.b[i]);
        }

        public int b(byte b) {
            return h.b(this.b, b);
        }

        public int c(byte b) {
            return h.c(this.b, b);
        }
    }

    public static final <T> List<T> a(T[] tArr) {
        f.b(tArr, "$this$asList");
        Object a = i.a(tArr);
        f.a(a, "ArraysUtilJVM.asList(this)");
        return a;
    }

    public static final List<Byte> a(byte[] bArr) {
        f.b(bArr, "$this$asList");
        return new a(bArr);
    }

    public static final byte[] a(byte[] bArr, int i, int i2) {
        f.b(bArr, "$this$copyOfRangeImpl");
        e.a(i2, bArr.length);
        Object copyOfRange = Arrays.copyOfRange(bArr, i, i2);
        f.a(copyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return copyOfRange;
    }
}
