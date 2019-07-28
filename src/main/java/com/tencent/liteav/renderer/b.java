package com.tencent.liteav.renderer;

import android.graphics.SurfaceTexture;
import android.opengl.GLUtils;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

/* compiled from: TXCGLRenderThread */
class b extends Thread {
    private int a = 12440;
    private int b = 4;
    private WeakReference<a> c;
    private EGL10 d;
    private EGLContext e;
    private EGLDisplay f;
    private EGLSurface g;
    private EGLConfig h;
    private WeakReference<SurfaceTexture> i;
    private boolean j = false;
    private int k = 1280;
    private int l = 720;

    b(WeakReference<a> weakReference) {
        this.c = weakReference;
    }

    public void run() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("VRender");
        stringBuilder.append(getId());
        setName(stringBuilder.toString());
        try {
            this.j = true;
            h();
            c();
            f();
            while (this.j) {
                if (!(!e() || this.d == null || this.f == null || this.g == null)) {
                    a aVar = this.c == null ? null : (a) this.c.get();
                    if (!(aVar == null || aVar.f() == null)) {
                        this.d.eglSwapBuffers(this.f, this.g);
                    }
                }
            }
            g();
            d();
            i();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public EGLContext a() {
        return this.e;
    }

    public void b() {
        this.j = false;
    }

    private void c() {
        try {
            a aVar = (a) this.c.get();
            if (aVar != null) {
                aVar.c();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void d() {
        try {
            a aVar = (a) this.c.get();
            if (aVar != null) {
                aVar.d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean e() {
        try {
            Thread.sleep(10);
            if (this.c != null) {
                a aVar = (a) this.c.get();
                if (aVar != null) {
                    return aVar.e();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void f() {
        if (this.c != null) {
            a aVar = (a) this.c.get();
            if (aVar != null) {
                aVar.o();
            }
        }
    }

    private void g() {
        if (this.c != null) {
            a aVar = (a) this.c.get();
            if (aVar != null) {
                aVar.p();
            }
        }
    }

    private void h() {
        a aVar = (a) this.c.get();
        if (aVar != null) {
            this.d = (EGL10) EGLContext.getEGL();
            this.f = this.d.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.d.eglInitialize(this.f, new int[2]);
            this.h = j();
            SurfaceTexture f = aVar.f();
            if (f != null) {
                this.i = new WeakReference(f);
                this.g = this.d.eglCreateWindowSurface(this.f, this.h, f, null);
            } else {
                this.g = this.d.eglCreatePbufferSurface(this.f, this.h, new int[]{12375, this.k, 12374, this.l, 12344});
            }
            this.e = a(this.d, this.f, this.h, EGL10.EGL_NO_CONTEXT);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("vrender: init egl @context=");
            stringBuilder.append(this.e);
            stringBuilder.append(",surface=");
            stringBuilder.append(this.g);
            TXCLog.w("TXCVideoRenderThread", stringBuilder.toString());
            try {
                if (this.g == null || this.g == EGL10.EGL_NO_SURFACE) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("GL error:");
                    stringBuilder.append(GLUtils.getEGLErrorString(this.d.eglGetError()));
                    throw new RuntimeException(stringBuilder.toString());
                } else if (!this.d.eglMakeCurrent(this.f, this.g, this.g, this.e)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("GL Make current Error");
                    stringBuilder.append(GLUtils.getEGLErrorString(this.d.eglGetError()));
                    throw new RuntimeException(stringBuilder.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void i() {
        this.d.eglMakeCurrent(this.f, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.d.eglDestroyContext(this.f, this.e);
        this.d.eglDestroySurface(this.f, this.g);
        this.d.eglTerminate(this.f);
        this.i = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("vrender: uninit egl @context=");
        stringBuilder.append(this.e);
        stringBuilder.append(",surface=");
        stringBuilder.append(this.g);
        TXCLog.w("TXCVideoRenderThread", stringBuilder.toString());
    }

    private EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, EGLContext eGLContext) {
        return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, new int[]{this.a, 2, 12344});
    }

    private EGLConfig j() {
        int[] iArr = new int[1];
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (this.d.eglChooseConfig(this.f, k(), eGLConfigArr, 1, iArr)) {
            return iArr[0] > 0 ? eGLConfigArr[0] : null;
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to choose config:");
            stringBuilder.append(GLUtils.getEGLErrorString(this.d.eglGetError()));
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    private int[] k() {
        return new int[]{12352, this.b, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    }
}
