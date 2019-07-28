package com.tencent.liteav.f;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.slf4j.Marker;

/* compiled from: TXScaleFilter */
public class i {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private float[] e = new float[16];
    private float[] f = new float[16];
    private boolean g = false;
    private boolean h = true;
    private boolean i = true;
    private final float[] j = new float[]{-1.0f, -1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, -1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, -1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f};
    private FloatBuffer k;
    private float[] l = new float[16];
    private float[] m = new float[16];
    private int n;
    private int o = -12345;
    private int p = -12345;
    private int q;
    private int r;
    private int s;
    private int t;

    public void a(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (i3 != this.a || i4 != this.b) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Output resolution change: ");
            stringBuilder.append(this.a);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(this.b);
            stringBuilder.append(" -> ");
            stringBuilder.append(i3);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(i4);
            TXCLog.d("TXScaleFilter", stringBuilder.toString());
            this.a = i3;
            this.b = i4;
            if (i3 > i4) {
                Matrix.orthoM(this.e, 0, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
            } else {
                Matrix.orthoM(this.e, 0, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
            }
            this.g = true;
        }
    }

    public void b(int i, int i2) {
        if (i != this.c || i2 != this.d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Input resolution change: ");
            stringBuilder.append(this.c);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(this.d);
            stringBuilder.append(" -> ");
            stringBuilder.append(i);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(i2);
            TXCLog.d("TXScaleFilter", stringBuilder.toString());
            this.c = i;
            this.d = i2;
        }
    }

    private void a(float[] fArr) {
        if (this.b != 0 && this.a != 0) {
            int i = this.c;
            float f = (float) i;
            float f2 = (((float) this.a) * 1.0f) / f;
            float f3 = (float) this.d;
            float f4 = (((float) this.b) * 1.0f) / f3;
            if (f2 * f3 <= ((float) this.b)) {
                f2 = f4;
            }
            Matrix.setIdentityM(this.f, 0);
            if (this.i) {
                Matrix.scaleM(this.f, 0, ((f * f2) / ((float) this.a)) * 1.0f, ((f3 * f2) / ((float) this.b)) * 1.0f, 1.0f);
            } else {
                Matrix.scaleM(this.f, 0, ((f * f2) / ((float) this.a)) * 1.0f, ((f3 * f2) / ((float) this.b)) * 1.0f, 1.0f);
            }
            Matrix.multiplyMM(fArr, 0, this.e, 0, this.f, 0);
        }
    }

    public i(Boolean bool) {
        this.h = bool.booleanValue();
        this.k = ByteBuffer.allocateDirect(this.j.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.k.put(this.j).position(0);
        Matrix.setIdentityM(this.m, 0);
    }

    public void a(int i) {
        GLES20.glViewport(0, 0, this.a, this.b);
        GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.n);
        a("glUseProgram");
        if (this.h) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i);
        } else {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
        }
        this.k.position(0);
        GLES20.glVertexAttribPointer(this.s, 3, 5126, false, 20, this.k);
        a("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.s);
        a("glEnableVertexAttribArray maPositionHandle");
        this.k.position(3);
        GLES20.glVertexAttribPointer(this.t, 2, 5126, false, 20, this.k);
        a("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.t);
        a("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(this.l, 0);
        a(this.l);
        GLES20.glUniformMatrix4fv(this.q, 1, false, this.l, 0);
        GLES20.glUniformMatrix4fv(this.r, 1, false, this.m, 0);
        a("glDrawArrays");
        GLES20.glDrawArrays(5, 0, 4);
        a("glDrawArrays");
        if (this.h) {
            GLES20.glBindTexture(36197, 0);
        } else {
            GLES20.glBindTexture(3553, 0);
        }
    }

    public int b(int i) {
        c();
        if (this.p == -12345) {
            TXCLog.d("TXScaleFilter", "invalid frame buffer id");
            return i;
        }
        GLES20.glBindFramebuffer(36160, this.p);
        a(i);
        GLES20.glBindFramebuffer(36160, 0);
        return this.o;
    }

    public void a() {
        if (this.h) {
            this.n = a("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        } else {
            this.n = a("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "varying highp vec2 vTextureCoord;\n \nuniform sampler2D sTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(sTexture, vTextureCoord);\n}");
        }
        if (this.n != 0) {
            this.s = GLES20.glGetAttribLocation(this.n, "aPosition");
            a("glGetAttribLocation aPosition");
            if (this.s != -1) {
                this.t = GLES20.glGetAttribLocation(this.n, "aTextureCoord");
                a("glGetAttribLocation aTextureCoord");
                if (this.t != -1) {
                    this.q = GLES20.glGetUniformLocation(this.n, "uMVPMatrix");
                    a("glGetUniformLocation uMVPMatrix");
                    if (this.q != -1) {
                        this.r = GLES20.glGetUniformLocation(this.n, "uSTMatrix");
                        a("glGetUniformLocation uSTMatrix");
                        if (this.r == -1) {
                            throw new RuntimeException("Could not get attrib location for uSTMatrix");
                        }
                        return;
                    }
                    throw new RuntimeException("Could not get attrib location for uMVPMatrix");
                }
                throw new RuntimeException("Could not get attrib location for aTextureCoord");
            }
            throw new RuntimeException("Could not get attrib location for aPosition");
        }
        throw new RuntimeException("failed creating program");
    }

    private void c() {
        if (this.g) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("reloadFrameBuffer. size = ");
            stringBuilder.append(this.a);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(this.b);
            TXCLog.d("TXScaleFilter", stringBuilder.toString());
            d();
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glGenFramebuffers(1, iArr2, 0);
            this.o = iArr[0];
            this.p = iArr2[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append("frameBuffer id = ");
            stringBuilder.append(this.p);
            stringBuilder.append(", texture id = ");
            stringBuilder.append(this.o);
            TXCLog.d("TXScaleFilter", stringBuilder.toString());
            GLES20.glBindTexture(3553, this.o);
            a("glBindTexture mFrameBufferTextureID");
            GLES20.glTexImage2D(3553, 0, 6408, this.a, this.b, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            a("glTexParameter");
            GLES20.glBindFramebuffer(36160, this.p);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.o, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
            this.g = false;
        }
    }

    public void b() {
        GLES20.glDeleteProgram(this.n);
        d();
    }

    private void d() {
        if (this.p != -12345) {
            GLES20.glDeleteFramebuffers(1, new int[]{this.p}, 0);
            this.p = -12345;
        }
        if (this.o != -12345) {
            GLES20.glDeleteTextures(1, new int[]{this.o}, 0);
            this.o = -12345;
        }
    }

    private int a(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("glCreateShader type=");
        stringBuilder.append(i);
        a(stringBuilder.toString());
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Could not compile shader ");
        stringBuilder2.append(i);
        stringBuilder2.append(":");
        TXCLog.e("TXScaleFilter", stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" ");
        stringBuilder3.append(GLES20.glGetShaderInfoLog(glCreateShader));
        TXCLog.e("TXScaleFilter", stringBuilder3.toString());
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    private int a(String str, String str2) {
        int a = a(35633, str);
        int i = 0;
        if (a == 0) {
            return 0;
        }
        int a2 = a(35632, str2);
        if (a2 == 0) {
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        a("glCreateProgram");
        if (glCreateProgram == 0) {
            TXCLog.e("TXScaleFilter", "Could not create program");
        }
        GLES20.glAttachShader(glCreateProgram, a);
        a("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, a2);
        a("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            TXCLog.e("TXScaleFilter", "Could not link program: ");
            TXCLog.e("TXScaleFilter", GLES20.glGetProgramInfoLog(glCreateProgram));
            GLES20.glDeleteProgram(glCreateProgram);
        } else {
            i = glCreateProgram;
        }
        return i;
    }

    private void a(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(": glError ");
            stringBuilder.append(glGetError);
            TXCLog.e("TXScaleFilter", stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(": glError ");
            stringBuilder2.append(glGetError);
            throw new RuntimeException(stringBuilder2.toString());
        }
    }
}
