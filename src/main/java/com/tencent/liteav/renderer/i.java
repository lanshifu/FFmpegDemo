package com.tencent.liteav.renderer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.slf4j.Marker;

/* compiled from: TXTweenFilter */
public class i {
    public static int a = 1;
    public static int b = 2;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = b;
    private int h = 0;
    private boolean i = false;
    private float[] j = new float[16];
    private float[] k = new float[16];
    private float l = 1.0f;
    private float m = 1.0f;
    private boolean n = false;
    private boolean o = true;
    private final float[] p = new float[]{-1.0f, -1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, -1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, -1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f};
    private FloatBuffer q;
    private float[] r = new float[16];
    private float[] s = new float[16];
    private int t;
    private int u = -12345;
    private int v = -12345;
    private int w;
    private int x;
    private int y;
    private int z;

    public void a(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (i3 != this.c || i4 != this.d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Output resolution change: ");
            stringBuilder.append(this.c);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(this.d);
            stringBuilder.append(" -> ");
            stringBuilder.append(i3);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(i4);
            TXCLog.d("TXTweenFilter", stringBuilder.toString());
            this.c = i3;
            this.d = i4;
            if (i3 > i4) {
                Matrix.orthoM(this.j, 0, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
                this.l = 1.0f;
                this.m = 1.0f;
            } else {
                Matrix.orthoM(this.j, 0, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
                this.l = 1.0f;
                this.m = 1.0f;
            }
            this.n = true;
        }
    }

    public void b(int i, int i2) {
        if (i != this.e || i2 != this.f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Input resolution change: ");
            stringBuilder.append(this.e);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(this.f);
            stringBuilder.append(" -> ");
            stringBuilder.append(i);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(i2);
            TXCLog.d("TXTweenFilter", stringBuilder.toString());
            this.e = i;
            this.f = i2;
        }
    }

    public boolean a() {
        return this.o;
    }

    public void a(int i) {
        this.g = i;
    }

    public void b(int i) {
        this.h = i;
    }

    private void b(float[] fArr) {
        if (this.d != 0 && this.c != 0) {
            int i = this.e;
            int i2 = this.f;
            if (this.h == 270 || this.h == 90) {
                i = this.f;
                i2 = this.e;
            }
            float f = (float) i;
            float f2 = (((float) this.c) * 1.0f) / f;
            float f3 = (float) i2;
            float f4 = (((float) this.d) * 1.0f) / f3;
            if (this.g != a ? f2 * f3 <= ((float) this.d) : f2 * f3 > ((float) this.d)) {
                f2 = f4;
            }
            Matrix.setIdentityM(this.k, 0);
            if (this.i) {
                if (this.h % TXLiveConstants.RENDER_ROTATION_180 == 0) {
                    Matrix.scaleM(this.k, 0, -1.0f, 1.0f, 1.0f);
                } else {
                    Matrix.scaleM(this.k, 0, 1.0f, -1.0f, 1.0f);
                }
            }
            Matrix.scaleM(this.k, 0, ((f * f2) / ((float) this.c)) * 1.0f, ((f3 * f2) / ((float) this.d)) * 1.0f, 1.0f);
            Matrix.rotateM(this.k, 0, (float) this.h, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -1.0f);
            Matrix.multiplyMM(fArr, 0, this.j, 0, this.k, 0);
        }
    }

    public i(Boolean bool) {
        this.o = bool.booleanValue();
        this.q = ByteBuffer.allocateDirect(this.p.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.q.put(this.p).position(0);
        Matrix.setIdentityM(this.s, 0);
    }

    public void a(float[] fArr) {
        this.s = fArr;
    }

    public void c(int i) {
        GLES20.glViewport(0, 0, this.c, this.d);
        GLES20.glClearColor(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        GLES20.glClear(16640);
        GLES20.glUseProgram(this.t);
        a("glUseProgram");
        if (this.o) {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(36197, i);
        } else {
            GLES20.glActiveTexture(33984);
            GLES20.glBindTexture(3553, i);
        }
        this.q.position(0);
        GLES20.glVertexAttribPointer(this.y, 3, 5126, false, 20, this.q);
        a("glVertexAttribPointer maPosition");
        GLES20.glEnableVertexAttribArray(this.y);
        a("glEnableVertexAttribArray maPositionHandle");
        this.q.position(3);
        GLES20.glVertexAttribPointer(this.z, 2, 5126, false, 20, this.q);
        a("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(this.z);
        a("glEnableVertexAttribArray maTextureHandle");
        Matrix.setIdentityM(this.r, 0);
        b(this.r);
        GLES20.glUniformMatrix4fv(this.w, 1, false, this.r, 0);
        GLES20.glUniformMatrix4fv(this.x, 1, false, this.s, 0);
        a("glDrawArrays");
        GLES20.glDrawArrays(5, 0, 4);
        a("glDrawArrays");
        if (this.o) {
            GLES20.glBindTexture(36197, 0);
        } else {
            GLES20.glBindTexture(3553, 0);
        }
    }

    public int d(int i) {
        d();
        if (this.v == -12345) {
            TXCLog.d("TXTweenFilter", "invalid frame buffer id");
            return i;
        }
        GLES20.glBindFramebuffer(36160, this.v);
        c(i);
        GLES20.glBindFramebuffer(36160, 0);
        return this.u;
    }

    public void b() {
        if (this.o) {
            this.t = a("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n");
        } else {
            this.t = a("uniform mat4 uMVPMatrix;\nuniform mat4 uSTMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n  gl_Position = uMVPMatrix * aPosition;\n  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n}\n", "varying highp vec2 vTextureCoord;\n \nuniform sampler2D sTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(sTexture, vTextureCoord);\n}");
        }
        if (this.t != 0) {
            this.y = GLES20.glGetAttribLocation(this.t, "aPosition");
            a("glGetAttribLocation aPosition");
            if (this.y != -1) {
                this.z = GLES20.glGetAttribLocation(this.t, "aTextureCoord");
                a("glGetAttribLocation aTextureCoord");
                if (this.z != -1) {
                    this.w = GLES20.glGetUniformLocation(this.t, "uMVPMatrix");
                    a("glGetUniformLocation uMVPMatrix");
                    if (this.w != -1) {
                        this.x = GLES20.glGetUniformLocation(this.t, "uSTMatrix");
                        a("glGetUniformLocation uSTMatrix");
                        if (this.x == -1) {
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

    private void d() {
        if (this.n) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("reloadFrameBuffer. size = ");
            stringBuilder.append(this.c);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(this.d);
            TXCLog.d("TXTweenFilter", stringBuilder.toString());
            e();
            int[] iArr = new int[1];
            int[] iArr2 = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glGenFramebuffers(1, iArr2, 0);
            this.u = iArr[0];
            this.v = iArr2[0];
            stringBuilder = new StringBuilder();
            stringBuilder.append("frameBuffer id = ");
            stringBuilder.append(this.v);
            stringBuilder.append(", texture id = ");
            stringBuilder.append(this.u);
            TXCLog.d("TXTweenFilter", stringBuilder.toString());
            GLES20.glBindTexture(3553, this.u);
            a("glBindTexture mFrameBufferTextureID");
            GLES20.glTexImage2D(3553, 0, 6408, this.c, this.d, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameteri(3553, 10242, 33071);
            GLES20.glTexParameteri(3553, 10243, 33071);
            a("glTexParameter");
            GLES20.glBindFramebuffer(36160, this.v);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.u, 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
            this.n = false;
        }
    }

    public void c() {
        GLES20.glDeleteProgram(this.t);
        e();
    }

    private void e() {
        if (this.v != -12345) {
            GLES20.glDeleteFramebuffers(1, new int[]{this.v}, 0);
            this.v = -12345;
        }
        if (this.u != -12345) {
            GLES20.glDeleteTextures(1, new int[]{this.u}, 0);
            this.u = -12345;
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
        TXCLog.e("TXTweenFilter", stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(" ");
        stringBuilder3.append(GLES20.glGetShaderInfoLog(glCreateShader));
        TXCLog.e("TXTweenFilter", stringBuilder3.toString());
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
            TXCLog.e("TXTweenFilter", "Could not create program");
        }
        GLES20.glAttachShader(glCreateProgram, a);
        a("glAttachShader");
        GLES20.glAttachShader(glCreateProgram, a2);
        a("glAttachShader");
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 1) {
            TXCLog.e("TXTweenFilter", "Could not link program: ");
            TXCLog.e("TXTweenFilter", GLES20.glGetProgramInfoLog(glCreateProgram));
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
            TXCLog.e("TXTweenFilter", stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(": glError ");
            stringBuilder2.append(glGetError);
            throw new RuntimeException(stringBuilder2.toString());
        }
    }
}
