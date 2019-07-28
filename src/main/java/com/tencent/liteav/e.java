package com.tencent.liteav;

import android.opengl.GLES20;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.beauty.b.ac;

/* compiled from: TXCGPUIllusionFilter */
public class e extends ac {
    private int[] r = null;
    private int[] s = null;
    private g t;

    public e() {
        super("precision mediump float;  \nvarying vec2 textureCoordinate;  \nuniform sampler2D inputImageTexture;  \nuniform sampler2D inputImageTexture2;  \nvoid main() {   \n\tgl_FragColor = vec4(mix(texture2D(inputImageTexture2, textureCoordinate).rgb, texture2D(inputImageTexture, textureCoordinate).rgb, vec3(0.06,0.21,0.6)),1.0);   \n}  \n");
    }

    public boolean a() {
        this.b = GLES20.glGetAttribLocation(this.a, "position");
        this.c = GLES20.glGetUniformLocation(this.a, "inputImageTexture");
        this.d = GLES20.glGetAttribLocation(this.a, "inputTextureCoordinate");
        this.v = GLES20.glGetUniformLocation(q(), "inputImageTexture2");
        return true;
    }

    public int a(int i) {
        if (this.t == null) {
            this.t = new g();
            this.t.a(true);
            this.t.c();
            this.t.a(this.e, this.f);
            this.t.a(i, this.t.m(), this.t.l());
        }
        i = c(i, this.t.l());
        this.t.a(i, this.t.m(), this.t.l());
        return i;
    }

    public void b() {
        super.b();
        if (this.t != null) {
            this.t.e();
            this.t = null;
        }
        if (this.s != null) {
            GLES20.glDeleteFramebuffers(1, this.s, 0);
            this.s = null;
        }
        if (this.r != null) {
            GLES20.glDeleteTextures(1, this.r, 0);
            this.r = null;
        }
    }
}
