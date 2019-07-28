package defpackage;

import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.exceptions.ImageDownloadTaskAddFailureException;
import com.zzhoujay.richtext.exceptions.ImageLoadCancelledException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ImageDownloaderManager */
/* renamed from: uv */
class uv {
    private final HashMap<String, d> a;
    private final b b;

    /* compiled from: ImageDownloaderManager */
    /* renamed from: uv$a */
    private static class a {
        private static final ExecutorService a = Executors.newCachedThreadPool();
    }

    /* compiled from: ImageDownloaderManager */
    /* renamed from: uv$b */
    public interface b {
        void a(String str);
    }

    /* compiled from: ImageDownloaderManager */
    /* renamed from: uv$c */
    private static class c {
        private static final uv a = new uv();
    }

    /* compiled from: ImageDownloaderManager */
    /* renamed from: uv$d */
    private static class d implements Runnable {
        private final String a;
        private final String b;
        private final uu c;
        private volatile int d;
        private final Object e = new Object();
        private final ArrayList<up> f;
        private final b g;

        d(String str, String str2, uu uuVar, b bVar) {
            this.b = str;
            this.c = uuVar;
            this.g = bVar;
            this.d = 0;
            this.f = new ArrayList();
            this.a = str2;
        }

        public void run() {
            synchronized (this.e) {
                this.d = 1;
            }
            Exception e = null;
            try {
                to a = this.c.a(this.b);
                tm.a().a(this.a, a.a());
                a.b();
            } catch (Exception e2) {
                e = e2;
            }
            synchronized (this.e) {
                this.g.a(this.a);
                if (this.d != 1) {
                    return;
                }
                this.d = 2;
                synchronized (this.f) {
                    Iterator it = this.f.iterator();
                    while (it.hasNext()) {
                        try {
                            ((up) it.next()).a(this.a, e);
                        } catch (Throwable th) {
                            ug.a(th);
                        }
                    }
                }
                this.d = 3;
            }
        }

        private void a(up upVar) {
            synchronized (this.f) {
                this.f.remove(upVar);
            }
        }

        private uq a(ExecutorService executorService, up upVar) {
            uq eVar;
            synchronized (this.e) {
                if (this.d == 1) {
                    synchronized (this.f) {
                        this.f.add(upVar);
                        eVar = new e(this, upVar);
                    }
                } else {
                    eVar = null;
                }
                if (this.d == 0) {
                    this.d = 1;
                    executorService.submit(this);
                    synchronized (this.f) {
                        this.f.add(upVar);
                        eVar = new e(this, upVar);
                    }
                }
            }
            if (eVar == null) {
                upVar.a((Exception) new ImageDownloadTaskAddFailureException());
            }
            return eVar;
        }
    }

    /* compiled from: ImageDownloaderManager */
    /* renamed from: uv$1 */
    class 1 implements b {
        1() {
        }

        public void a(String str) {
            synchronized (uv.this.a) {
                uv.this.a.remove(str);
            }
        }
    }

    /* compiled from: ImageDownloaderManager */
    /* renamed from: uv$e */
    private static class e implements uq {
        private WeakReference<d> a;
        private WeakReference<up> b;

        e(d dVar, up upVar) {
            this.a = new WeakReference(dVar);
            this.b = new WeakReference(upVar);
        }

        public void a() {
            d dVar = (d) this.a.get();
            if (dVar != null) {
                up upVar = (up) this.b.get();
                if (upVar != null) {
                    dVar.a(upVar);
                    upVar.a((Exception) new ImageLoadCancelledException());
                }
            }
        }
    }

    /* synthetic */ uv(1 1) {
        this();
    }

    private uv() {
        this.b = new 1();
        this.a = new HashMap();
    }

    static uv a() {
        return c.a;
    }

    /* Access modifiers changed, original: 0000 */
    public uq a(ImageHolder imageHolder, uu uuVar, up upVar) {
        uq a;
        String a2 = imageHolder.a();
        synchronized (this.a) {
            d dVar = (d) this.a.get(a2);
            if (dVar == null) {
                dVar = new d(imageHolder.d(), a2, uuVar, this.b);
                this.a.put(a2, dVar);
            }
            a = dVar.a(uv.b(), upVar);
        }
        return a;
    }

    private static ExecutorService b() {
        return a.a;
    }
}
