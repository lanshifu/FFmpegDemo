package com.tencent.liteav.beauty.b;

import android.opengl.GLES20;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.k;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TXCGPUFilterGroup */
public class j extends g {
    protected List<g> r;
    protected List<g> s;
    private int[] t;
    private int[] u;
    private g v;
    private final FloatBuffer w;
    private final FloatBuffer x;
    private final FloatBuffer y;

    public j() {
        this(null);
        this.o = true;
    }

    public j(List<g> list) {
        this.s = new ArrayList();
        this.v = new g();
        this.o = true;
        this.r = list;
        if (this.r == null) {
            this.r = new ArrayList();
        } else {
            s();
        }
        this.w = ByteBuffer.allocateDirect(w.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.w.put(w.a).position(0);
        this.x = ByteBuffer.allocateDirect(k.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.x.put(k.a).position(0);
        float[] a = k.a(com.tencent.liteav.basic.e.j.NORMAL, false, true);
        this.y = ByteBuffer.allocateDirect(a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.y.put(a).position(0);
    }

    public void a(g gVar) {
        if (gVar != null) {
            this.r.add(gVar);
            s();
        }
    }

    public boolean a() {
        boolean a = super.a();
        if (a) {
            for (g gVar : this.r) {
                gVar.c();
                if (!gVar.n()) {
                    break;
                }
            }
            a = this.v.c();
        }
        return a && GLES20.glGetError() == 0;
    }

    public void b() {
        super.b();
        for (g e : this.r) {
            e.e();
        }
    }

    public void f() {
        super.f();
        if (this.u != null) {
            GLES20.glDeleteTextures(2, this.u, 0);
            this.u = null;
        }
        if (this.t != null) {
            GLES20.glDeleteFramebuffers(2, this.t, 0);
            this.t = null;
        }
    }

    public void a(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (this.e != i3 || this.f != i4) {
            if (this.t != null) {
                f();
            }
            super.a(i, i2);
            int size = this.s.size();
            for (int i5 = 0; i5 < size; i5++) {
                ((g) this.s.get(i5)).a(i3, i4);
            }
            this.v.a(i3, i4);
            if (this.s != null && this.s.size() > 0) {
                this.s.size();
                this.t = new int[2];
                this.u = new int[2];
                for (int i6 = 0; i6 < 2; i6++) {
                    GLES20.glGenFramebuffers(1, this.t, i6);
                    GLES20.glGenTextures(1, this.u, i6);
                    GLES20.glBindTexture(3553, this.u[i6]);
                    GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
                    GLES20.glTexParameterf(3553, 10240, 9729.0f);
                    GLES20.glTexParameterf(3553, 10241, 9729.0f);
                    GLES20.glTexParameterf(3553, 10242, 33071.0f);
                    GLES20.glTexParameterf(3553, 10243, 33071.0f);
                    GLES20.glBindFramebuffer(36160, this.t[i6]);
                    GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.u[i6], 0);
                    GLES20.glBindTexture(3553, 0);
                    GLES20.glBindFramebuffer(36160, 0);
                }
            }
        }
    }

    public int a(int i, int i2, int i3) {
        int size = this.s.size();
        k();
        int i4 = i;
        int i5 = 0;
        for (i = 0; i < size; i++) {
            g gVar = (g) this.s.get(i);
            if (i5 != 0) {
                i4 = gVar.a(i4, i2, i3);
            } else {
                i4 = gVar.a(i4, this.t[0], this.u[0]);
            }
            i5 ^= 1;
        }
        if (i5 != 0) {
            this.v.a(i4, i2, i3);
        }
        return i3;
    }

    public List<g> r() {
        return this.s;
    }

    public void s() {
        if (this.r != null) {
            this.s.clear();
            for (g gVar : this.r) {
                if (gVar instanceof j) {
                    j jVar = (j) gVar;
                    jVar.s();
                    List r = jVar.r();
                    if (r != null) {
                        if (!r.isEmpty()) {
                            this.s.addAll(r);
                        }
                    }
                } else {
                    this.s.add(gVar);
                }
            }
        }
    }
}
