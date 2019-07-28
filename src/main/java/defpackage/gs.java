package defpackage;

import java.nio.ByteBuffer;

/* compiled from: DecoderInputBuffer */
/* renamed from: gs */
public class gs extends go {
    public final gp a = new gp();
    public ByteBuffer b;
    public long c;
    private final int d;

    public static gs e() {
        return new gs(0);
    }

    public gs(int i) {
        this.d = i;
    }

    public void e(int i) throws IllegalStateException {
        if (this.b == null) {
            this.b = f(i);
            return;
        }
        int capacity = this.b.capacity();
        int position = this.b.position();
        i += position;
        if (capacity < i) {
            ByteBuffer f = f(i);
            if (position > 0) {
                this.b.position(0);
                this.b.limit(position);
                f.put(this.b);
            }
            this.b = f;
        }
    }

    public final boolean f() {
        return this.b == null && this.d == 0;
    }

    public final boolean g() {
        return d(1073741824);
    }

    public final void h() {
        this.b.flip();
    }

    public void a() {
        super.a();
        if (this.b != null) {
            this.b.clear();
        }
    }

    private ByteBuffer f(int i) {
        if (this.d == 1) {
            return ByteBuffer.allocate(i);
        }
        if (this.d == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        int capacity = this.b == null ? 0 : this.b.capacity();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Buffer too small (");
        stringBuilder.append(capacity);
        stringBuilder.append(" < ");
        stringBuilder.append(i);
        stringBuilder.append(")");
        throw new IllegalStateException(stringBuilder.toString());
    }
}
