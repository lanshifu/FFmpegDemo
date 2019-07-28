package com.tencent.liteav.network.a.b;

/* compiled from: BitSet */
public final class a {
    private int a = 0;

    public a a(int i) {
        this.a = (1 << i) | this.a;
        return this;
    }

    public int a() {
        return this.a;
    }
}
