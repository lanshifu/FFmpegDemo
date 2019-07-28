package com.tencent.liteav.beauty.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.util.TypedValue;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.j;
import com.tencent.liteav.basic.e.k;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.beauty.b.q.a;
import com.yalantis.ucrop.view.CropImageView;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: TXCGPURenderer */
public class w implements PreviewCallback, Renderer {
    static final float[] a = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    public final Object b;
    private g c;
    private SurfaceTexture d;
    private final FloatBuffer e;
    private final FloatBuffer f;
    private IntBuffer g;
    private int h;
    private int i;
    private int j;
    private int k;
    private final Queue<Runnable> l;
    private final Queue<Runnable> m;
    private j n;
    private boolean o;
    private boolean p;
    private a q;
    private float r;
    private float s;
    private float t;
    private Context u;
    private int v;
    private String w;
    private FileOutputStream x;
    private int y;
    private int z;

    private float a(float f, float f2) {
        return f == CropImageView.DEFAULT_ASPECT_RATIO ? f2 : 1.0f - f2;
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(this.r, this.s, this.t, 1.0f);
        GLES20.glDisable(2929);
        this.c.c();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.h = i;
        this.i = i2;
        GLES20.glViewport(0, 0, i, i2);
        GLES20.glUseProgram(this.c.q());
        this.c.a(i, i2);
        a();
        synchronized (this.b) {
            this.b.notifyAll();
        }
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        a(this.l);
        if (this.z == -1) {
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            this.z = iArr[0];
            GLES20.glBindTexture(3553, this.z);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            TypedValue typedValue = new TypedValue();
            Options options = new Options();
            options.inTargetDensity = typedValue.density;
            Bitmap decodeResource = BitmapFactory.decodeResource(this.u.getResources(), this.v, options);
            GLES20.glBindTexture(3553, this.z);
            GLUtils.texImage2D(3553, 0, decodeResource, 0);
        }
        this.c.a(this.z, this.e, this.f);
        Buffer allocate = IntBuffer.allocate(250000);
        GLES20.glReadPixels(0, 0, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION, 6408, 5121, allocate);
        int i = this.y;
        this.y = i + 1;
        if (i == 50) {
            try {
                if (this.x == null) {
                    this.x = new FileOutputStream(new File("/mnt/sdcard/", "rgbBuffer"));
                }
                String byteOrder = allocate.order().toString();
                int[] array = allocate.array();
                byte[] bArr = new byte[1000000];
                int i2;
                if (byteOrder.compareTo("LITTLE_ENDIAN") != 0) {
                    for (i = 0; i < 250000; i++) {
                        i2 = i * 4;
                        bArr[i2 + 3] = (byte) ((array[i] >> 24) & 255);
                        bArr[i2 + 2] = (byte) ((array[i] >> 16) & 255);
                        bArr[i2 + 1] = (byte) ((array[i] >> 8) & 255);
                        bArr[i2] = (byte) (array[i] & 255);
                    }
                } else {
                    for (i = 0; i < 250000; i++) {
                        i2 = i * 4;
                        bArr[i2] = (byte) ((array[i] >> 24) & 255);
                        bArr[i2 + 1] = (byte) ((array[i] >> 16) & 255);
                        bArr[i2 + 2] = (byte) ((array[i] >> 8) & 255);
                        bArr[i2 + 3] = (byte) (array[i] & 255);
                    }
                }
                this.x.write(bArr, 0, bArr.length);
                this.x.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(this.y);
            TXCLog.e("check1", stringBuilder.toString());
        }
        a(this.m);
        if (this.d != null) {
            this.d.updateTexImage();
        }
    }

    private void a(Queue<Runnable> queue) {
        if (queue == null) {
            TXCLog.e(this.w, "runAll queue is null!");
            return;
        }
        synchronized (queue) {
            while (!queue.isEmpty()) {
                ((Runnable) queue.poll()).run();
            }
        }
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        Size previewSize = camera.getParameters().getPreviewSize();
        if (this.g == null && previewSize != null) {
            this.g = IntBuffer.allocate(previewSize.width * previewSize.height);
        }
        if (this.l.isEmpty()) {
            a(new Runnable() {
                public void run() {
                }
            });
        }
    }

    private void a() {
        float f = (float) this.h;
        float f2 = (float) this.i;
        if (this.n == j.ROTATION_270 || this.n == j.ROTATION_90) {
            f = (float) this.i;
            f2 = (float) this.h;
        }
        float max = Math.max(f / ((float) this.j), f2 / ((float) this.k));
        float round = ((float) Math.round(((float) this.j) * max)) / f;
        f = ((float) Math.round(((float) this.k) * max)) / f2;
        float[] fArr = a;
        float[] a = k.a(this.n, this.o, this.p);
        if (this.q == a.CENTER_CROP) {
            round = (1.0f - (1.0f / round)) / 2.0f;
            f = (1.0f - (1.0f / f)) / 2.0f;
            a = new float[]{a(a[0], round), a(a[1], f), a(a[2], round), a(a[3], f), a(a[4], round), a(a[5], f), a(a[6], round), a(a[7], f)};
        } else {
            fArr = new float[]{a[0] / f, a[1] / round, a[2] / f, a[3] / round, a[4] / f, a[5] / round, a[6] / f, a[7] / round};
        }
        a[0] = CropImageView.DEFAULT_ASPECT_RATIO;
        a[1] = 1.0f;
        a[2] = 1.0f;
        a[3] = 1.0f;
        a[4] = CropImageView.DEFAULT_ASPECT_RATIO;
        a[5] = CropImageView.DEFAULT_ASPECT_RATIO;
        a[6] = 1.0f;
        a[7] = CropImageView.DEFAULT_ASPECT_RATIO;
        this.e.clear();
        this.e.put(fArr).position(0);
        this.f.clear();
        this.f.put(a).position(0);
    }

    /* Access modifiers changed, original: protected */
    public void a(Runnable runnable) {
        synchronized (this.l) {
            this.l.add(runnable);
        }
    }
}
