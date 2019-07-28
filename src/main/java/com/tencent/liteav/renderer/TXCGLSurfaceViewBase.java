package com.tencent.liteav.renderer;

import android.content.Context;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.tencent.liteav.basic.log.TXCLog;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

public class TXCGLSurfaceViewBase extends SurfaceView implements Callback {
    private static final j a = new j();
    protected boolean b = false;
    protected boolean c = false;
    protected final WeakReference<TXCGLSurfaceViewBase> d = new WeakReference(this);
    protected boolean e;
    protected boolean f;
    private i g;
    private Renderer h;
    private boolean i;
    private e j;
    private f k;
    private g l;
    private k m;
    private int n;
    private int o;
    private boolean p;

    public interface e {
        EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface f {
        EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface g {
        EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public static class h {
        EGL10 a;
        EGLDisplay b;
        EGLSurface c;
        EGLConfig d;
        EGLContext e;
        private WeakReference<TXCGLSurfaceViewBase> f;

        public h(WeakReference<TXCGLSurfaceViewBase> weakReference) {
            this.f = weakReference;
        }

        public void a() {
            this.a = (EGL10) EGLContext.getEGL();
            this.b = this.a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.b != EGL10.EGL_NO_DISPLAY) {
                if (this.a.eglInitialize(this.b, new int[2])) {
                    TXCGLSurfaceViewBase tXCGLSurfaceViewBase = (TXCGLSurfaceViewBase) this.f.get();
                    if (tXCGLSurfaceViewBase == null) {
                        this.d = null;
                        this.e = null;
                    } else {
                        this.d = tXCGLSurfaceViewBase.j.a(this.a, this.b);
                        this.e = tXCGLSurfaceViewBase.k.a(this.a, this.b, this.d);
                    }
                    if (this.e == null || this.e == EGL10.EGL_NO_CONTEXT) {
                        this.e = null;
                        a("createContext");
                    }
                    if (tXCGLSurfaceViewBase != null) {
                        tXCGLSurfaceViewBase.f = true;
                    }
                    this.c = null;
                    return;
                }
                throw new RuntimeException("eglInitialize failed");
            }
            throw new RuntimeException("eglGetDisplay failed");
        }

        public boolean b() {
            if (this.a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.d != null) {
                h();
                TXCGLSurfaceViewBase tXCGLSurfaceViewBase = (TXCGLSurfaceViewBase) this.f.get();
                if (tXCGLSurfaceViewBase != null) {
                    this.c = tXCGLSurfaceViewBase.l.a(this.a, this.b, this.d, tXCGLSurfaceViewBase.getHolder());
                } else {
                    this.c = null;
                }
                if (this.c == null || this.c == EGL10.EGL_NO_SURFACE) {
                    if (this.a.eglGetError() == 12299) {
                        TXCLog.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                    }
                    return false;
                } else if (this.a.eglMakeCurrent(this.b, this.c, this.c, this.e)) {
                    if (tXCGLSurfaceViewBase != null) {
                        tXCGLSurfaceViewBase.e = true;
                    }
                    return true;
                } else {
                    a("EGLHelper", "eglMakeCurrent", this.a.eglGetError());
                    return false;
                }
            } else {
                throw new RuntimeException("mEglConfig not initialized");
            }
        }

        public int c() {
            return e();
        }

        /* Access modifiers changed, original: 0000 */
        public GL d() {
            GL gl = this.e.getGL();
            TXCGLSurfaceViewBase tXCGLSurfaceViewBase = (TXCGLSurfaceViewBase) this.f.get();
            if (tXCGLSurfaceViewBase == null) {
                return gl;
            }
            if (tXCGLSurfaceViewBase.m != null) {
                gl = tXCGLSurfaceViewBase.m.a(gl);
            }
            if ((tXCGLSurfaceViewBase.n & 3) == 0) {
                return gl;
            }
            int i = 0;
            Writer writer = null;
            if ((tXCGLSurfaceViewBase.n & 1) != 0) {
                i = 1;
            }
            if ((tXCGLSurfaceViewBase.n & 2) != 0) {
                writer = new l();
            }
            return GLDebugHelper.wrap(gl, i, writer);
        }

        public int e() {
            return !this.a.eglSwapBuffers(this.b, this.c) ? this.a.eglGetError() : 12288;
        }

        public void f() {
            h();
        }

        private void h() {
            if (this.c != null && this.c != EGL10.EGL_NO_SURFACE) {
                this.a.eglMakeCurrent(this.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                TXCGLSurfaceViewBase tXCGLSurfaceViewBase = (TXCGLSurfaceViewBase) this.f.get();
                if (tXCGLSurfaceViewBase != null) {
                    tXCGLSurfaceViewBase.l.a(this.a, this.b, this.c);
                    tXCGLSurfaceViewBase.e = false;
                }
                this.c = null;
            }
        }

        public void g() {
            if (this.e != null) {
                TXCGLSurfaceViewBase tXCGLSurfaceViewBase = (TXCGLSurfaceViewBase) this.f.get();
                if (tXCGLSurfaceViewBase != null) {
                    tXCGLSurfaceViewBase.k.a(this.a, this.b, this.e);
                }
                this.e = null;
            }
            if (this.b != null) {
                this.a.eglTerminate(this.b);
                this.b = null;
            }
        }

        private void a(String str) {
            a(str, this.a.eglGetError());
        }

        public static void a(String str, int i) {
            throw new RuntimeException(b(str, i));
        }

        public static void a(String str, String str2, int i) {
            TXCLog.w(str, b(str2, i));
        }

        public static String b(String str, int i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" failed: ");
            stringBuilder.append(i);
            return stringBuilder.toString();
        }
    }

    static class i extends Thread {
        private boolean a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private int l = 0;
        private int m = 0;
        private int n = 1;
        private boolean o = true;
        private boolean p;
        private ArrayList<Runnable> q = new ArrayList();
        private boolean r = true;
        private h s;
        private WeakReference<TXCGLSurfaceViewBase> t;

        i(WeakReference<TXCGLSurfaceViewBase> weakReference) {
            this.t = weakReference;
        }

        public void run() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("GLThread ");
            stringBuilder.append(getId());
            setName(stringBuilder.toString());
            try {
                i();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                TXCGLSurfaceViewBase.a.a(this);
            }
            TXCGLSurfaceViewBase.a.a(this);
        }

        public int a() {
            return this.s.c();
        }

        public h b() {
            return this.s;
        }

        /* JADX WARNING: Missing block: B:94:0x0153, code skipped:
            if (r9 == null) goto L_0x015c;
     */
        /* JADX WARNING: Missing block: B:96:?, code skipped:
            r9.run();
            r9 = null;
     */
        /* JADX WARNING: Missing block: B:97:0x015c, code skipped:
            if (r11 == null) goto L_0x0192;
     */
        /* JADX WARNING: Missing block: B:99:0x0164, code skipped:
            if (r1.s.b() == false) goto L_0x017b;
     */
        /* JADX WARNING: Missing block: B:100:0x0166, code skipped:
            r11 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e();
     */
        /* JADX WARNING: Missing block: B:101:0x016a, code skipped:
            monitor-enter(r11);
     */
        /* JADX WARNING: Missing block: B:104:?, code skipped:
            r1.j = true;
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e().notifyAll();
     */
        /* JADX WARNING: Missing block: B:105:0x0175, code skipped:
            monitor-exit(r11);
     */
        /* JADX WARNING: Missing block: B:106:0x0176, code skipped:
            r11 = null;
     */
        /* JADX WARNING: Missing block: B:111:0x017b, code skipped:
            r15 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e();
     */
        /* JADX WARNING: Missing block: B:112:0x017f, code skipped:
            monitor-enter(r15);
     */
        /* JADX WARNING: Missing block: B:115:?, code skipped:
            r1.j = true;
            r1.f = true;
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e().notifyAll();
     */
        /* JADX WARNING: Missing block: B:116:0x018c, code skipped:
            monitor-exit(r15);
     */
        /* JADX WARNING: Missing block: B:122:0x0192, code skipped:
            if (r12 == null) goto L_0x01a5;
     */
        /* JADX WARNING: Missing block: B:123:0x0194, code skipped:
            r0 = (javax.microedition.khronos.opengles.GL10) r1.s.d();
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e().a(r0);
            r6 = r0;
            r12 = null;
     */
        /* JADX WARNING: Missing block: B:124:0x01a5, code skipped:
            if (r10 == null) goto L_0x01bd;
     */
        /* JADX WARNING: Missing block: B:125:0x01a7, code skipped:
            r0 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r1.t.get();
     */
        /* JADX WARNING: Missing block: B:126:0x01af, code skipped:
            if (r0 == null) goto L_0x01bc;
     */
        /* JADX WARNING: Missing block: B:127:0x01b1, code skipped:
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.b(r0).onSurfaceCreated(r6, r1.s.d);
     */
        /* JADX WARNING: Missing block: B:128:0x01bc, code skipped:
            r10 = null;
     */
        /* JADX WARNING: Missing block: B:129:0x01bd, code skipped:
            if (r13 == null) goto L_0x01d1;
     */
        /* JADX WARNING: Missing block: B:130:0x01bf, code skipped:
            r0 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r1.t.get();
     */
        /* JADX WARNING: Missing block: B:131:0x01c7, code skipped:
            if (r0 == null) goto L_0x01d0;
     */
        /* JADX WARNING: Missing block: B:132:0x01c9, code skipped:
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.b(r0).onSurfaceChanged(r6, r7, r8);
     */
        /* JADX WARNING: Missing block: B:133:0x01d0, code skipped:
            r13 = null;
     */
        /* JADX WARNING: Missing block: B:134:0x01d1, code skipped:
            r0 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r1.t.get();
     */
        /* JADX WARNING: Missing block: B:135:0x01db, code skipped:
            if (r0 == null) goto L_0x01e9;
     */
        /* JADX WARNING: Missing block: B:136:0x01dd, code skipped:
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.b(r0).onDrawFrame(r6);
            r0 = r0.c();
     */
        /* JADX WARNING: Missing block: B:137:0x01e9, code skipped:
            r0 = 12288;
     */
        /* JADX WARNING: Missing block: B:138:0x01eb, code skipped:
            if (r0 == 12288) goto L_0x020f;
     */
        /* JADX WARNING: Missing block: B:140:0x01ef, code skipped:
            if (r0 == 12302) goto L_0x020c;
     */
        /* JADX WARNING: Missing block: B:141:0x01f1, code skipped:
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.h.a("GLThread", "eglSwapBuffers", r0);
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e();
     */
        /* JADX WARNING: Missing block: B:142:0x01fc, code skipped:
            monitor-enter(r2);
     */
        /* JADX WARNING: Missing block: B:145:?, code skipped:
            r1.f = true;
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.e().notifyAll();
     */
        /* JADX WARNING: Missing block: B:146:0x0207, code skipped:
            monitor-exit(r2);
     */
        /* JADX WARNING: Missing block: B:152:0x020c, code skipped:
            r5 = 1;
     */
        /* JADX WARNING: Missing block: B:154:0x0210, code skipped:
            if (r14 == null) goto L_0x0213;
     */
        /* JADX WARNING: Missing block: B:155:0x0212, code skipped:
            r3 = 1;
     */
        private void i() throws java.lang.InterruptedException {
            /*
            r16 = this;
            r1 = r16;
            r0 = new com.tencent.liteav.renderer.TXCGLSurfaceViewBase$h;
            r2 = r1.t;
            r0.<init>(r2);
            r1.s = r0;
            r0 = 0;
            r1.h = r0;
            r1.i = r0;
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r6 = 0;
            r7 = 0;
            r8 = 0;
            r9 = 0;
            r10 = 0;
            r11 = 0;
            r12 = 0;
            r13 = 0;
            r14 = 0;
        L_0x001c:
            r15 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0224 }
            monitor-enter(r15);	 Catch:{ all -> 0x0224 }
        L_0x0021:
            r2 = r1.a;	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x0036;
        L_0x0025:
            monitor-exit(r15);	 Catch:{ all -> 0x0221 }
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;
            monitor-enter(r2);
            r16.j();	 Catch:{ all -> 0x0033 }
            r16.k();	 Catch:{ all -> 0x0033 }
            monitor-exit(r2);	 Catch:{ all -> 0x0033 }
            return;
        L_0x0033:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0033 }
            throw r0;
        L_0x0036:
            r2 = r1.q;	 Catch:{ all -> 0x0221 }
            r2 = r2.isEmpty();	 Catch:{ all -> 0x0221 }
            if (r2 != 0) goto L_0x004b;
        L_0x003e:
            r2 = r1.q;	 Catch:{ all -> 0x0221 }
            r9 = 0;
            r2 = r2.remove(r9);	 Catch:{ all -> 0x0221 }
            r2 = (java.lang.Runnable) r2;	 Catch:{ all -> 0x0221 }
            r9 = r2;
            r2 = 0;
            goto L_0x0152;
        L_0x004b:
            r2 = r1.d;	 Catch:{ all -> 0x0221 }
            r0 = r1.c;	 Catch:{ all -> 0x0221 }
            if (r2 == r0) goto L_0x005f;
        L_0x0051:
            r0 = r1.c;	 Catch:{ all -> 0x0221 }
            r2 = r1.c;	 Catch:{ all -> 0x0221 }
            r1.d = r2;	 Catch:{ all -> 0x0221 }
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r2.notifyAll();	 Catch:{ all -> 0x0221 }
            goto L_0x0060;
        L_0x005f:
            r0 = 0;
        L_0x0060:
            r2 = r1.k;	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x006e;
        L_0x0064:
            r16.j();	 Catch:{ all -> 0x0221 }
            r16.k();	 Catch:{ all -> 0x0221 }
            r2 = 0;
            r1.k = r2;	 Catch:{ all -> 0x0221 }
            r4 = 1;
        L_0x006e:
            if (r5 == 0) goto L_0x0077;
        L_0x0070:
            r16.j();	 Catch:{ all -> 0x0221 }
            r16.k();	 Catch:{ all -> 0x0221 }
            r5 = 0;
        L_0x0077:
            if (r0 == 0) goto L_0x0080;
        L_0x0079:
            r2 = r1.i;	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x0080;
        L_0x007d:
            r16.j();	 Catch:{ all -> 0x0221 }
        L_0x0080:
            if (r0 == 0) goto L_0x00a5;
        L_0x0082:
            r2 = r1.h;	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x00a5;
        L_0x0086:
            r2 = r1.t;	 Catch:{ all -> 0x0221 }
            r2 = r2.get();	 Catch:{ all -> 0x0221 }
            r2 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r2;	 Catch:{ all -> 0x0221 }
            if (r2 != 0) goto L_0x0092;
        L_0x0090:
            r2 = 0;
            goto L_0x0096;
        L_0x0092:
            r2 = r2.p;	 Catch:{ all -> 0x0221 }
        L_0x0096:
            if (r2 == 0) goto L_0x00a2;
        L_0x0098:
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r2 = r2.a();	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x00a5;
        L_0x00a2:
            r16.k();	 Catch:{ all -> 0x0221 }
        L_0x00a5:
            if (r0 == 0) goto L_0x00b6;
        L_0x00a7:
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0 = r0.b();	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x00b6;
        L_0x00b1:
            r0 = r1.s;	 Catch:{ all -> 0x0221 }
            r0.g();	 Catch:{ all -> 0x0221 }
        L_0x00b6:
            r0 = r1.e;	 Catch:{ all -> 0x0221 }
            if (r0 != 0) goto L_0x00d2;
        L_0x00ba:
            r0 = r1.g;	 Catch:{ all -> 0x0221 }
            if (r0 != 0) goto L_0x00d2;
        L_0x00be:
            r0 = r1.i;	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x00c5;
        L_0x00c2:
            r16.j();	 Catch:{ all -> 0x0221 }
        L_0x00c5:
            r0 = 1;
            r1.g = r0;	 Catch:{ all -> 0x0221 }
            r0 = 0;
            r1.f = r0;	 Catch:{ all -> 0x0221 }
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0.notifyAll();	 Catch:{ all -> 0x0221 }
        L_0x00d2:
            r0 = r1.e;	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x00e4;
        L_0x00d6:
            r0 = r1.g;	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x00e4;
        L_0x00da:
            r0 = 0;
            r1.g = r0;	 Catch:{ all -> 0x0221 }
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0.notifyAll();	 Catch:{ all -> 0x0221 }
        L_0x00e4:
            if (r3 == 0) goto L_0x00f2;
        L_0x00e6:
            r0 = 1;
            r1.p = r0;	 Catch:{ all -> 0x0221 }
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0.notifyAll();	 Catch:{ all -> 0x0221 }
            r3 = 0;
            r14 = 0;
        L_0x00f2:
            r0 = r16.l();	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x0217;
        L_0x00f8:
            r0 = r1.h;	 Catch:{ all -> 0x0221 }
            if (r0 != 0) goto L_0x0124;
        L_0x00fc:
            if (r4 == 0) goto L_0x0100;
        L_0x00fe:
            r4 = 0;
            goto L_0x0124;
        L_0x0100:
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0 = r0.b(r1);	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x0124;
        L_0x010a:
            r0 = r1.s;	 Catch:{ RuntimeException -> 0x011b }
            r0.a();	 Catch:{ RuntimeException -> 0x011b }
            r0 = 1;
            r1.h = r0;	 Catch:{ all -> 0x0221 }
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0.notifyAll();	 Catch:{ all -> 0x0221 }
            r10 = 1;
            goto L_0x0124;
        L_0x011b:
            r0 = move-exception;
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r2.c(r1);	 Catch:{ all -> 0x0221 }
            throw r0;	 Catch:{ all -> 0x0221 }
        L_0x0124:
            r0 = r1.h;	 Catch:{ all -> 0x0221 }
            if (r0 == 0) goto L_0x0133;
        L_0x0128:
            r0 = r1.i;	 Catch:{ all -> 0x0221 }
            if (r0 != 0) goto L_0x0133;
        L_0x012c:
            r0 = 1;
            r1.i = r0;	 Catch:{ all -> 0x0221 }
            r0 = 1;
            r12 = 1;
            r13 = 1;
            goto L_0x0134;
        L_0x0133:
            r0 = r11;
        L_0x0134:
            r2 = r1.i;	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x0216;
        L_0x0138:
            r2 = r1.r;	 Catch:{ all -> 0x0221 }
            if (r2 == 0) goto L_0x0147;
        L_0x013c:
            r7 = r1.l;	 Catch:{ all -> 0x0221 }
            r8 = r1.m;	 Catch:{ all -> 0x0221 }
            r2 = 0;
            r1.r = r2;	 Catch:{ all -> 0x0221 }
            r0 = 1;
            r13 = 1;
            r14 = 1;
            goto L_0x0148;
        L_0x0147:
            r2 = 0;
        L_0x0148:
            r1.o = r2;	 Catch:{ all -> 0x0221 }
            r11 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r11.notifyAll();	 Catch:{ all -> 0x0221 }
            r11 = r0;
        L_0x0152:
            monitor-exit(r15);	 Catch:{ all -> 0x0221 }
            if (r9 == 0) goto L_0x015c;
        L_0x0155:
            r9.run();	 Catch:{ all -> 0x0224 }
            r0 = 0;
            r9 = 0;
            goto L_0x001c;
        L_0x015c:
            if (r11 == 0) goto L_0x0192;
        L_0x015e:
            r0 = r1.s;	 Catch:{ all -> 0x0224 }
            r0 = r0.b();	 Catch:{ all -> 0x0224 }
            if (r0 == 0) goto L_0x017b;
        L_0x0166:
            r11 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0224 }
            monitor-enter(r11);	 Catch:{ all -> 0x0224 }
            r0 = 1;
            r1.j = r0;	 Catch:{ all -> 0x0178 }
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0178 }
            r0.notifyAll();	 Catch:{ all -> 0x0178 }
            monitor-exit(r11);	 Catch:{ all -> 0x0178 }
            r11 = 0;
            goto L_0x0192;
        L_0x0178:
            r0 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0178 }
            throw r0;	 Catch:{ all -> 0x0224 }
        L_0x017b:
            r15 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0224 }
            monitor-enter(r15);	 Catch:{ all -> 0x0224 }
            r0 = 1;
            r1.j = r0;	 Catch:{ all -> 0x018f }
            r1.f = r0;	 Catch:{ all -> 0x018f }
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x018f }
            r0.notifyAll();	 Catch:{ all -> 0x018f }
            monitor-exit(r15);	 Catch:{ all -> 0x018f }
            goto L_0x0213;
        L_0x018f:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x018f }
            throw r0;	 Catch:{ all -> 0x0224 }
        L_0x0192:
            if (r12 == 0) goto L_0x01a5;
        L_0x0194:
            r0 = r1.s;	 Catch:{ all -> 0x0224 }
            r0 = r0.d();	 Catch:{ all -> 0x0224 }
            r0 = (javax.microedition.khronos.opengles.GL10) r0;	 Catch:{ all -> 0x0224 }
            r6 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0224 }
            r6.a(r0);	 Catch:{ all -> 0x0224 }
            r6 = r0;
            r12 = 0;
        L_0x01a5:
            if (r10 == 0) goto L_0x01bd;
        L_0x01a7:
            r0 = r1.t;	 Catch:{ all -> 0x0224 }
            r0 = r0.get();	 Catch:{ all -> 0x0224 }
            r0 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r0;	 Catch:{ all -> 0x0224 }
            if (r0 == 0) goto L_0x01bc;
        L_0x01b1:
            r0 = r0.h;	 Catch:{ all -> 0x0224 }
            r10 = r1.s;	 Catch:{ all -> 0x0224 }
            r10 = r10.d;	 Catch:{ all -> 0x0224 }
            r0.onSurfaceCreated(r6, r10);	 Catch:{ all -> 0x0224 }
        L_0x01bc:
            r10 = 0;
        L_0x01bd:
            if (r13 == 0) goto L_0x01d1;
        L_0x01bf:
            r0 = r1.t;	 Catch:{ all -> 0x0224 }
            r0 = r0.get();	 Catch:{ all -> 0x0224 }
            r0 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r0;	 Catch:{ all -> 0x0224 }
            if (r0 == 0) goto L_0x01d0;
        L_0x01c9:
            r0 = r0.h;	 Catch:{ all -> 0x0224 }
            r0.onSurfaceChanged(r6, r7, r8);	 Catch:{ all -> 0x0224 }
        L_0x01d0:
            r13 = 0;
        L_0x01d1:
            r0 = r1.t;	 Catch:{ all -> 0x0224 }
            r0 = r0.get();	 Catch:{ all -> 0x0224 }
            r0 = (com.tencent.liteav.renderer.TXCGLSurfaceViewBase) r0;	 Catch:{ all -> 0x0224 }
            r15 = 12288; // 0x3000 float:1.7219E-41 double:6.071E-320;
            if (r0 == 0) goto L_0x01e9;
        L_0x01dd:
            r2 = r0.h;	 Catch:{ all -> 0x0224 }
            r2.onDrawFrame(r6);	 Catch:{ all -> 0x0224 }
            r0 = r0.c();	 Catch:{ all -> 0x0224 }
            goto L_0x01eb;
        L_0x01e9:
            r0 = 12288; // 0x3000 float:1.7219E-41 double:6.071E-320;
        L_0x01eb:
            if (r0 == r15) goto L_0x020f;
        L_0x01ed:
            r2 = 12302; // 0x300e float:1.7239E-41 double:6.078E-320;
            if (r0 == r2) goto L_0x020c;
        L_0x01f1:
            r2 = "GLThread";
            r15 = "eglSwapBuffers";
            com.tencent.liteav.renderer.TXCGLSurfaceViewBase.h.a(r2, r15, r0);	 Catch:{ all -> 0x0224 }
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0224 }
            monitor-enter(r2);	 Catch:{ all -> 0x0224 }
            r0 = 1;
            r1.f = r0;	 Catch:{ all -> 0x0209 }
            r15 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0209 }
            r15.notifyAll();	 Catch:{ all -> 0x0209 }
            monitor-exit(r2);	 Catch:{ all -> 0x0209 }
            goto L_0x0210;
        L_0x0209:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0209 }
            throw r0;	 Catch:{ all -> 0x0224 }
        L_0x020c:
            r0 = 1;
            r5 = 1;
            goto L_0x0210;
        L_0x020f:
            r0 = 1;
        L_0x0210:
            if (r14 == 0) goto L_0x0213;
        L_0x0212:
            r3 = 1;
        L_0x0213:
            r0 = 0;
            goto L_0x001c;
        L_0x0216:
            r11 = r0;
        L_0x0217:
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0221 }
            r0.wait();	 Catch:{ all -> 0x0221 }
            r0 = 0;
            goto L_0x0021;
        L_0x0221:
            r0 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x0221 }
            throw r0;	 Catch:{ all -> 0x0224 }
        L_0x0224:
            r0 = move-exception;
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;
            monitor-enter(r2);
            r16.j();	 Catch:{ all -> 0x0232 }
            r16.k();	 Catch:{ all -> 0x0232 }
            monitor-exit(r2);	 Catch:{ all -> 0x0232 }
            throw r0;
        L_0x0232:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0232 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceViewBase$i.i():void");
        }

        private void j() {
            if (this.i) {
                this.i = false;
                this.s.f();
            }
        }

        private void k() {
            if (this.h) {
                this.s.g();
                this.h = false;
                TXCGLSurfaceViewBase tXCGLSurfaceViewBase = (TXCGLSurfaceViewBase) this.t.get();
                if (tXCGLSurfaceViewBase != null) {
                    tXCGLSurfaceViewBase.f = false;
                }
                TXCGLSurfaceViewBase.a.c(this);
            }
        }

        public boolean c() {
            return this.h && this.i && l();
        }

        private boolean l() {
            return !this.d && this.e && !this.f && this.l > 0 && this.m > 0 && (this.o || this.n == 1);
        }

        public void a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (TXCGLSurfaceViewBase.a) {
                this.n = i;
                TXCGLSurfaceViewBase.a.notifyAll();
            }
        }

        public int d() {
            int i;
            synchronized (TXCGLSurfaceViewBase.a) {
                i = this.n;
            }
            return i;
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0026 */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:14|15|26) */
        /* JADX WARNING: Missing block: B:15:?, code skipped:
            java.lang.Thread.currentThread().interrupt();
     */
        public void e() {
            /*
            r2 = this;
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;
            monitor-enter(r0);
            r1 = 1;
            r2.e = r1;	 Catch:{ all -> 0x0030 }
            r1 = 0;
            r2.j = r1;	 Catch:{ all -> 0x0030 }
            r1 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0030 }
            r1.notifyAll();	 Catch:{ all -> 0x0030 }
        L_0x0012:
            r1 = r2.g;	 Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x002e;
        L_0x0016:
            r1 = r2.j;	 Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x002e;
        L_0x001a:
            r1 = r2.b;	 Catch:{ all -> 0x0030 }
            if (r1 != 0) goto L_0x002e;
        L_0x001e:
            r1 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ InterruptedException -> 0x0026 }
            r1.wait();	 Catch:{ InterruptedException -> 0x0026 }
            goto L_0x0012;
        L_0x0026:
            r1 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0030 }
            r1.interrupt();	 Catch:{ all -> 0x0030 }
            goto L_0x0012;
        L_0x002e:
            monitor-exit(r0);	 Catch:{ all -> 0x0030 }
            return;
        L_0x0030:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0030 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceViewBase$i.e():void");
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x001f */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:12|13|23) */
        /* JADX WARNING: Missing block: B:13:?, code skipped:
            java.lang.Thread.currentThread().interrupt();
     */
        public void f() {
            /*
            r2 = this;
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;
            monitor-enter(r0);
            r1 = 0;
            r2.e = r1;	 Catch:{ all -> 0x0029 }
            r1 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0029 }
            r1.notifyAll();	 Catch:{ all -> 0x0029 }
        L_0x000f:
            r1 = r2.g;	 Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x0027;
        L_0x0013:
            r1 = r2.b;	 Catch:{ all -> 0x0029 }
            if (r1 != 0) goto L_0x0027;
        L_0x0017:
            r1 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ InterruptedException -> 0x001f }
            r1.wait();	 Catch:{ InterruptedException -> 0x001f }
            goto L_0x000f;
        L_0x001f:
            r1 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0029 }
            r1.interrupt();	 Catch:{ all -> 0x0029 }
            goto L_0x000f;
        L_0x0027:
            monitor-exit(r0);	 Catch:{ all -> 0x0029 }
            return;
        L_0x0029:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0029 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceViewBase$i.f():void");
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0032 */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|28) */
        /* JADX WARNING: Missing block: B:16:?, code skipped:
            java.lang.Thread.currentThread().interrupt();
     */
        public void a(int r2, int r3) {
            /*
            r1 = this;
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;
            monitor-enter(r0);
            r1.l = r2;	 Catch:{ all -> 0x003c }
            r1.m = r3;	 Catch:{ all -> 0x003c }
            r2 = 1;
            r1.r = r2;	 Catch:{ all -> 0x003c }
            r1.o = r2;	 Catch:{ all -> 0x003c }
            r2 = 0;
            r1.p = r2;	 Catch:{ all -> 0x003c }
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x003c }
            r2.notifyAll();	 Catch:{ all -> 0x003c }
        L_0x0018:
            r2 = r1.b;	 Catch:{ all -> 0x003c }
            if (r2 != 0) goto L_0x003a;
        L_0x001c:
            r2 = r1.d;	 Catch:{ all -> 0x003c }
            if (r2 != 0) goto L_0x003a;
        L_0x0020:
            r2 = r1.p;	 Catch:{ all -> 0x003c }
            if (r2 != 0) goto L_0x003a;
        L_0x0024:
            r2 = r1.c();	 Catch:{ all -> 0x003c }
            if (r2 == 0) goto L_0x003a;
        L_0x002a:
            r2 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ InterruptedException -> 0x0032 }
            r2.wait();	 Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0018;
        L_0x0032:
            r2 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x003c }
            r2.interrupt();	 Catch:{ all -> 0x003c }
            goto L_0x0018;
        L_0x003a:
            monitor-exit(r0);	 Catch:{ all -> 0x003c }
            return;
        L_0x003c:
            r2 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x003c }
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceViewBase$i.a(int, int):void");
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001b */
        /* JADX WARNING: Can't wrap try/catch for region: R(3:10|11|20) */
        /* JADX WARNING: Missing block: B:11:?, code skipped:
            java.lang.Thread.currentThread().interrupt();
     */
        public void g() {
            /*
            r2 = this;
            r0 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;
            monitor-enter(r0);
            r1 = 1;
            r2.a = r1;	 Catch:{ all -> 0x0025 }
            r1 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ all -> 0x0025 }
            r1.notifyAll();	 Catch:{ all -> 0x0025 }
        L_0x000f:
            r1 = r2.b;	 Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023;
        L_0x0013:
            r1 = com.tencent.liteav.renderer.TXCGLSurfaceViewBase.a;	 Catch:{ InterruptedException -> 0x001b }
            r1.wait();	 Catch:{ InterruptedException -> 0x001b }
            goto L_0x000f;
        L_0x001b:
            r1 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0025 }
            r1.interrupt();	 Catch:{ all -> 0x0025 }
            goto L_0x000f;
        L_0x0023:
            monitor-exit(r0);	 Catch:{ all -> 0x0025 }
            return;
        L_0x0025:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0025 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.renderer.TXCGLSurfaceViewBase$i.g():void");
        }

        public void h() {
            this.k = true;
            TXCGLSurfaceViewBase.a.notifyAll();
        }

        public void a(Runnable runnable) {
            if (runnable != null) {
                synchronized (TXCGLSurfaceViewBase.a) {
                    this.q.add(runnable);
                    TXCGLSurfaceViewBase.a.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("r must not be null");
        }
    }

    private static class j {
        private static String a = "GLThreadManager";
        private boolean b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private i g;

        private j() {
        }

        /* synthetic */ j(AnonymousClass1 anonymousClass1) {
            this();
        }

        public synchronized void a(i iVar) {
            iVar.b = true;
            if (this.g == iVar) {
                this.g = null;
            }
            notifyAll();
        }

        public boolean b(i iVar) {
            if (this.g == iVar || this.g == null) {
                this.g = iVar;
                notifyAll();
                return true;
            }
            c();
            if (this.e) {
                return true;
            }
            if (this.g != null) {
                this.g.h();
            }
            return false;
        }

        public void c(i iVar) {
            if (this.g == iVar) {
                this.g = null;
            }
            notifyAll();
        }

        public synchronized boolean a() {
            return this.f;
        }

        public synchronized boolean b() {
            c();
            return this.e ^ 1;
        }

        public synchronized void a(GL10 gl10) {
            if (!this.d) {
                c();
                String glGetString = gl10.glGetString(7937);
                if (this.c < 131072) {
                    this.e = glGetString.startsWith("Q3Dimension MSM7500 ") ^ 1;
                    notifyAll();
                }
                this.f = this.e ^ 1;
                this.d = true;
            }
        }

        private void c() {
            this.c = 131072;
            this.e = true;
            this.b = true;
        }
    }

    public interface k {
        GL a(GL gl);
    }

    static class l extends Writer {
        private StringBuilder a = new StringBuilder();

        l() {
        }

        public void close() {
            a();
        }

        public void flush() {
            a();
        }

        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == 10) {
                    a();
                } else {
                    this.a.append(c);
                }
            }
        }

        private void a() {
            if (this.a.length() > 0) {
                TXCLog.v("TXCGLSurfaceViewBase", this.a.toString());
                this.a.delete(0, this.a.length());
            }
        }
    }

    private abstract class a implements e {
        protected int[] a;

        public abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public a(int[] iArr) {
            this.a = a(iArr);
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.a, null, 0, iArr)) {
                int i = iArr[0];
                if (i > 0) {
                    EGLConfig[] eGLConfigArr = new EGLConfig[i];
                    if (egl10.eglChooseConfig(eGLDisplay, this.a, eGLConfigArr, i, iArr)) {
                        EGLConfig a = a(egl10, eGLDisplay, eGLConfigArr);
                        if (a != null) {
                            return a;
                        }
                        throw new IllegalArgumentException("No config chosen");
                    }
                    throw new IllegalArgumentException("eglChooseConfig#2 failed");
                }
                throw new IllegalArgumentException("No configs match configSpec");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        private int[] a(int[] iArr) {
            if (TXCGLSurfaceViewBase.this.o != 2) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[(length + 2)];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            iArr2[length] = 4;
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    private class c implements f {
        private int b;

        private c() {
            this.b = 12440;
        }

        /* synthetic */ c(TXCGLSurfaceViewBase tXCGLSurfaceViewBase, AnonymousClass1 anonymousClass1) {
            this();
        }

        public EGLContext a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{this.b, TXCGLSurfaceViewBase.this.o, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (TXCGLSurfaceViewBase.this.o == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("display:");
                stringBuilder.append(eGLDisplay);
                stringBuilder.append(" context: ");
                stringBuilder.append(eGLContext);
                TXCLog.e("DefaultContextFactory", stringBuilder.toString());
                h.a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    private static class d implements g {
        private d() {
        }

        /* synthetic */ d(AnonymousClass1 anonymousClass1) {
            this();
        }

        public EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                TXCLog.e("TXCGLSurfaceViewBase", "eglCreateWindowSurface");
                TXCLog.e("TXCGLSurfaceViewBase", e.toString());
                return null;
            }
        }

        public void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    private class b extends a {
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        private int[] j = new int[1];

        public b(int i, int i2, int i3, int i4, int i5, int i6) {
            super(new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                EGL10 egl102 = egl10;
                EGLDisplay eGLDisplay2 = eGLDisplay;
                EGLConfig eGLConfig2 = eGLConfig;
                int a = a(egl102, eGLDisplay2, eGLConfig2, 12325, 0);
                int a2 = a(egl102, eGLDisplay2, eGLConfig2, 12326, 0);
                if (a >= this.g && a2 >= this.h) {
                    egl102 = egl10;
                    eGLDisplay2 = eGLDisplay;
                    eGLConfig2 = eGLConfig;
                    a = a(egl102, eGLDisplay2, eGLConfig2, 12324, 0);
                    int a3 = a(egl102, eGLDisplay2, eGLConfig2, 12323, 0);
                    int a4 = a(egl102, eGLDisplay2, eGLConfig2, 12322, 0);
                    a2 = a(egl102, eGLDisplay2, eGLConfig2, 12321, 0);
                    if (a == this.c && a3 == this.d && a4 == this.e && a2 == this.f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.j) ? this.j[0] : i2;
        }
    }

    private class m extends b {
        public m(boolean z) {
            super(8, 8, 8, 0, z ? 16 : 0, 0);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
    }

    /* Access modifiers changed, original: protected */
    public int c() {
        return 0;
    }

    public TXCGLSurfaceViewBase(Context context) {
        super(context);
        a();
    }

    public TXCGLSurfaceViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        try {
            if (this.g != null) {
                this.g.g();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    private void a() {
        getHolder().addCallback(this);
    }

    public void setGLWrapper(k kVar) {
        this.m = kVar;
    }

    public void setDebugFlags(int i) {
        this.n = i;
    }

    public int getDebugFlags() {
        return this.n;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.p = z;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.p;
    }

    public void setRenderer(Renderer renderer) {
        f();
        if (this.j == null) {
            this.j = new m(true);
        }
        if (this.k == null) {
            this.k = new c(this, null);
        }
        if (this.l == null) {
            this.l = new d();
        }
        this.h = renderer;
        this.g = new i(this.d);
        this.g.start();
    }

    public void setEGLContextFactory(f fVar) {
        f();
        this.k = fVar;
    }

    public void setEGLWindowSurfaceFactory(g gVar) {
        f();
        this.l = gVar;
    }

    public void setEGLConfigChooser(e eVar) {
        f();
        this.j = eVar;
    }

    public void setEGLConfigChooser(boolean z) {
        setEGLConfigChooser(new m(z));
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        setEGLConfigChooser(new b(i, i2, i3, i4, i5, i6));
    }

    public void setEGLContextClientVersion(int i) {
        f();
        this.o = i;
    }

    public void setRenderMode(int i) {
        this.g.a(i);
    }

    public int getRenderMode() {
        return this.g.d();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.g.e();
        setRunInBackground(false);
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        setRunInBackground(true);
        if (!this.b) {
            this.g.a(new Runnable() {
                public void run() {
                    TXCGLSurfaceViewBase.this.b();
                }
            });
            this.g.f();
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.g.a(i2, i3);
    }

    public void c(boolean z) {
        this.b = z;
        if (!this.b && this.c && this.g != null) {
            TXCLog.w("TXCGLSurfaceViewBase", "background capture destroy surface when not enable background run");
            this.g.a(new Runnable() {
                public void run() {
                    TXCGLSurfaceViewBase.this.b();
                }
            });
            this.g.f();
        }
    }

    /* Access modifiers changed, original: protected */
    public void setRunInBackground(boolean z) {
        this.c = z;
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.i && this.h != null) {
            int d = this.g != null ? this.g.d() : 1;
            this.g = new i(this.d);
            if (d != 1) {
                this.g.a(d);
            }
            this.g.start();
        }
        this.i = false;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        if (this.g != null) {
            this.g.g();
        }
        this.i = true;
        super.onDetachedFromWindow();
    }

    private void f() {
        if (this.g != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public int d() {
        return this.g.a();
    }

    public h getEGLHelper() {
        return this.g.b();
    }
}
