package com.tencent.liteav.basic.e;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* compiled from: TXCOpenGlUtils */
public class i {
    public static FloatBuffer a = a(f);
    public static FloatBuffer b = a(g);
    public static FloatBuffer c = a(h);
    public static FloatBuffer d = a(i);
    public static FloatBuffer e = a(j);
    private static float[] f = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static float[] g = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, 1.0f};
    private static float[] h = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO};
    private static float[] i = new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
    private static float[] j = new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
    private static int k = 2;

    /* compiled from: TXCOpenGlUtils */
    public static class a {
        public int[] a = null;
        public int[] b = null;
        public int c = -1;
        public int d = -1;
    }

    /* compiled from: TXCOpenGlUtils */
    public interface b {
    }

    public static void a(int i) {
        k = i;
    }

    public static final int a() {
        return k;
    }

    public static void a(a[] aVarArr) {
        if (aVarArr != null) {
            for (a aVar : aVarArr) {
                if (aVar != null) {
                    a(aVar);
                }
            }
        }
    }

    public static a[] a(a[] aVarArr, int i, int i2, int i3) {
        if (aVarArr == null) {
            aVarArr = new a[i];
        }
        for (i = 0; i < aVarArr.length; i++) {
            aVarArr[i] = a(aVarArr[i], i2, i3);
        }
        return aVarArr;
    }

    public static a a(a aVar, int i, int i2) {
        if (aVar == null) {
            aVar = new a();
        }
        if (aVar.a == null) {
            aVar.a = new int[1];
        }
        if (aVar.b == null) {
            aVar.b = new int[1];
        }
        aVar.c = i;
        aVar.d = i2;
        a(aVar.a, aVar.b, aVar.c, aVar.d);
        return aVar;
    }

    public static a a(a aVar) {
        if (aVar == null) {
            return aVar;
        }
        if (aVar.a != null) {
            GLES20.glDeleteFramebuffers(1, aVar.a, 0);
            aVar.a = null;
        }
        if (aVar.b != null) {
            GLES20.glDeleteTextures(1, aVar.b, 0);
            aVar.b = null;
        }
        return null;
    }

    public static int a(int i, int i2, int i3, int i4, int[] iArr) {
        int[] iArr2 = iArr;
        GLES20.glGenTextures(1, iArr2, 0);
        GLES20.glBindTexture(3553, iArr2[0]);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glTexParameteri(3553, 10241, 9728);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, i4, 5121, null);
        return iArr2[0];
    }

    public static FloatBuffer a(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    public static int a(int i, int i2, int i3, int i4, IntBuffer intBuffer) {
        int c = c();
        GLES20.glBindTexture(3553, c);
        GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, i4, 5121, intBuffer);
        GLES20.glBindTexture(3553, 0);
        return c;
    }

    public static int a(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, i4, (IntBuffer) null);
    }

    public static int a(Bitmap bitmap, int i, boolean z) {
        int[] iArr = new int[1];
        if (i == -1) {
            GLES20.glGenTextures(1, iArr, 0);
            GLES20.glBindTexture(3553, iArr[0]);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLUtils.texImage2D(3553, 0, bitmap, 0);
        } else {
            GLES20.glBindTexture(3553, i);
            GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            iArr[0] = i;
        }
        if (z) {
            bitmap.recycle();
        }
        return iArr[0];
    }

    public static int a(int i, int i2, int[] iArr) {
        i = (i * i2) * 4;
        GLES30.glGenBuffers(1, iArr, 0);
        GLES30.glBindBuffer(35051, iArr[0]);
        GLES30.glBufferData(35051, i, null, 35049);
        GLES30.glBindBuffer(35051, 0);
        return iArr[0];
    }

    public static int b() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(36197, iArr[0]);
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        return iArr[0];
    }

    public static void a(int[] iArr, int[] iArr2, int i, int i2) {
        GLES20.glGenFramebuffers(1, iArr, 0);
        iArr2[0] = a(i, i2, 6408, 6408, iArr2);
        GLES20.glBindFramebuffer(36160, iArr[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr2[0], 0);
        GLES20.glBindFramebuffer(36160, 0);
    }

    public static int c() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glTexParameterf(3553, 10241, 9729.0f);
        GLES20.glTexParameterf(3553, 10240, 9729.0f);
        GLES20.glTexParameteri(3553, 10242, 33071);
        GLES20.glTexParameteri(3553, 10243, 33071);
        GLES20.glBindTexture(3553, 0);
        return iArr[0];
    }

    public static int a(String str, int i) {
        int[] iArr = new int[1];
        i = GLES20.glCreateShader(i);
        GLES20.glShaderSource(i, str);
        GLES20.glCompileShader(i);
        GLES20.glGetShaderiv(i, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return i;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Compilation\n");
        stringBuilder.append(GLES20.glGetShaderInfoLog(i));
        TXCLog.d("Load Shader Failed", stringBuilder.toString());
        return 0;
    }

    public static int a(String str, String str2) {
        int[] iArr = new int[1];
        int a = a(str, 35633);
        if (a == 0) {
            TXCLog.d("Load Program", "Vertex Shader Failed");
            return 0;
        }
        int a2 = a(str2, 35632);
        if (a2 == 0) {
            TXCLog.d("Load Program", "Fragment Shader Failed");
            return 0;
        }
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, a);
        GLES20.glAttachShader(glCreateProgram, a2);
        GLES20.glLinkProgram(glCreateProgram);
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] <= 0) {
            TXCLog.d("Load Program", "Linking Failed");
            return 0;
        }
        GLES20.glDeleteShader(a);
        GLES20.glDeleteShader(a2);
        return glCreateProgram;
    }
}
