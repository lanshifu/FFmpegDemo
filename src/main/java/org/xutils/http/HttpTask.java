package org.xutils.http;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback.CacheCallback;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.Callback.PrepareCallback;
import org.xutils.common.Callback.ProgressCallback;
import org.xutils.common.Callback.TypedCallback;
import org.xutils.common.task.AbsTask;
import org.xutils.common.task.Priority;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.ParameterizedTypeUtil;
import org.xutils.http.app.RequestInterceptListener;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.request.UriRequest;
import org.xutils.http.request.UriRequestFactory;
import org.xutils.x;

public class HttpTask<ResultType> extends AbsTask<ResultType> implements ProgressHandler {
    static final /* synthetic */ boolean a = (HttpTask.class.desiredAssertionStatus() ^ 1);
    private static final AtomicInteger q = new AtomicInteger(0);
    private static final HashMap<String, WeakReference<HttpTask<?>>> r = new HashMap(1);
    private static final PriorityExecutor s = new PriorityExecutor(5, true);
    private static final PriorityExecutor t = new PriorityExecutor(5, true);
    private RequestParams b;
    private UriRequest c;
    private a d;
    private final Executor e;
    private volatile boolean f = false;
    private final CommonCallback<ResultType> g;
    private Object h = null;
    private volatile Boolean i = null;
    private final Object j = new Object();
    private CacheCallback<ResultType> k;
    private PrepareCallback l;
    private ProgressCallback m;
    private RequestInterceptListener n;
    private RequestTracker o;
    private Type p;
    private long u;
    private long v = 300;

    private final class a {
        Object a;
        Throwable b;

        /* synthetic */ a(HttpTask httpTask, AnonymousClass1 anonymousClass1) {
            this();
        }

        private a() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:6:0x0010 A:{LOOP_START, SYNTHETIC, LOOP:0: B:6:0x0010->B:98:0x0010} */
        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x0110 */
        /* JADX WARNING: Can't wrap try/catch for region: R(7:11|12|13|100|98|6|7) */
        /* JADX WARNING: Can't wrap try/catch for region: R(2:73|74) */
        /* JADX WARNING: Missing block: B:14:0x002d, code skipped:
            r0 = 1;
     */
        /* JADX WARNING: Missing block: B:74:?, code skipped:
            r5.b = r0;
     */
        public void a() {
            /*
            r5 = this;
            r0 = 0;
            r1 = java.io.File.class;
            r2 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x00af }
            r2 = r2.p;	 Catch:{ Throwable -> 0x00af }
            if (r1 != r2) goto L_0x003a;
        L_0x000b:
            r1 = org.xutils.http.HttpTask.q;	 Catch:{ Throwable -> 0x00af }
            monitor-enter(r1);	 Catch:{ Throwable -> 0x00af }
        L_0x0010:
            r2 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0037 }
            r2 = r2.get();	 Catch:{ all -> 0x0037 }
            r3 = 3;
            if (r2 < r3) goto L_0x002e;
        L_0x001b:
            r2 = org.xutils.http.HttpTask.this;	 Catch:{ all -> 0x0037 }
            r2 = r2.isCancelled();	 Catch:{ all -> 0x0037 }
            if (r2 != 0) goto L_0x002e;
        L_0x0023:
            r2 = org.xutils.http.HttpTask.q;	 Catch:{ InterruptedException -> 0x002d, Throwable -> 0x0010 }
            r3 = 10;
            r2.wait(r3);	 Catch:{ InterruptedException -> 0x002d, Throwable -> 0x0010 }
            goto L_0x0010;
        L_0x002d:
            r0 = 1;
        L_0x002e:
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            r1 = org.xutils.http.HttpTask.q;	 Catch:{ Throwable -> 0x00af }
            r1.incrementAndGet();	 Catch:{ Throwable -> 0x00af }
            goto L_0x003a;
        L_0x0037:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0037 }
            throw r0;	 Catch:{ Throwable -> 0x00af }
        L_0x003a:
            if (r0 != 0) goto L_0x008e;
        L_0x003c:
            r1 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x00af }
            r1 = r1.isCancelled();	 Catch:{ Throwable -> 0x00af }
            if (r1 == 0) goto L_0x0045;
        L_0x0044:
            goto L_0x008e;
        L_0x0045:
            r0 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0061 }
            r0 = r0.c;	 Catch:{ Throwable -> 0x0061 }
            r1 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0061 }
            r1 = r1.n;	 Catch:{ Throwable -> 0x0061 }
            r0.setRequestInterceptListener(r1);	 Catch:{ Throwable -> 0x0061 }
            r0 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0061 }
            r0 = r0.c;	 Catch:{ Throwable -> 0x0061 }
            r0 = r0.loadResult();	 Catch:{ Throwable -> 0x0061 }
            r5.a = r0;	 Catch:{ Throwable -> 0x0061 }
            goto L_0x0064;
        L_0x0061:
            r0 = move-exception;
            r5.b = r0;	 Catch:{ Throwable -> 0x00af }
        L_0x0064:
            r0 = r5.b;	 Catch:{ Throwable -> 0x00af }
            if (r0 != 0) goto L_0x008b;
        L_0x0068:
            r0 = java.io.File.class;
            r1 = org.xutils.http.HttpTask.this;
            r1 = r1.p;
            if (r0 != r1) goto L_0x0134;
        L_0x0072:
            r0 = org.xutils.http.HttpTask.q;
            monitor-enter(r0);
            r1 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0088 }
            r1.decrementAndGet();	 Catch:{ all -> 0x0088 }
            r1 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0088 }
            r1.notifyAll();	 Catch:{ all -> 0x0088 }
            monitor-exit(r0);	 Catch:{ all -> 0x0088 }
            goto L_0x0134;
        L_0x0088:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0088 }
            throw r1;
        L_0x008b:
            r0 = r5.b;	 Catch:{ Throwable -> 0x00af }
            throw r0;	 Catch:{ Throwable -> 0x00af }
        L_0x008e:
            r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ Throwable -> 0x00af }
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00af }
            r2.<init>();	 Catch:{ Throwable -> 0x00af }
            r3 = "cancelled before request";
            r2.append(r3);	 Catch:{ Throwable -> 0x00af }
            if (r0 == 0) goto L_0x009f;
        L_0x009c:
            r0 = "(interrupted)";
            goto L_0x00a1;
        L_0x009f:
            r0 = "";
        L_0x00a1:
            r2.append(r0);	 Catch:{ Throwable -> 0x00af }
            r0 = r2.toString();	 Catch:{ Throwable -> 0x00af }
            r1.<init>(r0);	 Catch:{ Throwable -> 0x00af }
            throw r1;	 Catch:{ Throwable -> 0x00af }
        L_0x00ac:
            r0 = move-exception;
            goto L_0x0135;
        L_0x00af:
            r0 = move-exception;
            r5.b = r0;	 Catch:{ all -> 0x00ac }
            r1 = r0 instanceof org.xutils.ex.HttpException;	 Catch:{ all -> 0x00ac }
            if (r1 == 0) goto L_0x0112;
        L_0x00b6:
            r1 = r0;
            r1 = (org.xutils.ex.HttpException) r1;	 Catch:{ all -> 0x00ac }
            r2 = r1.getCode();	 Catch:{ all -> 0x00ac }
            r3 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
            if (r2 == r3) goto L_0x00c5;
        L_0x00c1:
            r3 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
            if (r2 != r3) goto L_0x0112;
        L_0x00c5:
            r3 = org.xutils.http.HttpTask.this;	 Catch:{ all -> 0x00ac }
            r3 = r3.b;	 Catch:{ all -> 0x00ac }
            r3 = r3.getRedirectHandler();	 Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0112;
        L_0x00d1:
            r4 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0110 }
            r4 = r4.c;	 Catch:{ Throwable -> 0x0110 }
            r3 = r3.getRedirectParams(r4);	 Catch:{ Throwable -> 0x0110 }
            if (r3 == 0) goto L_0x0112;
        L_0x00dd:
            r4 = r3.getMethod();	 Catch:{ Throwable -> 0x0110 }
            if (r4 != 0) goto L_0x00f0;
        L_0x00e3:
            r4 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0110 }
            r4 = r4.b;	 Catch:{ Throwable -> 0x0110 }
            r4 = r4.getMethod();	 Catch:{ Throwable -> 0x0110 }
            r3.setMethod(r4);	 Catch:{ Throwable -> 0x0110 }
        L_0x00f0:
            r4 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0110 }
            r4.b = r3;	 Catch:{ Throwable -> 0x0110 }
            r3 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0110 }
            r4 = org.xutils.http.HttpTask.this;	 Catch:{ Throwable -> 0x0110 }
            r4 = r4.c();	 Catch:{ Throwable -> 0x0110 }
            r3.c = r4;	 Catch:{ Throwable -> 0x0110 }
            r3 = new org.xutils.ex.HttpRedirectException;	 Catch:{ Throwable -> 0x0110 }
            r4 = r1.getMessage();	 Catch:{ Throwable -> 0x0110 }
            r1 = r1.getResult();	 Catch:{ Throwable -> 0x0110 }
            r3.<init>(r2, r4, r1);	 Catch:{ Throwable -> 0x0110 }
            r5.b = r3;	 Catch:{ Throwable -> 0x0110 }
            goto L_0x0112;
        L_0x0110:
            r5.b = r0;	 Catch:{ all -> 0x00ac }
        L_0x0112:
            r0 = java.io.File.class;
            r1 = org.xutils.http.HttpTask.this;
            r1 = r1.p;
            if (r0 != r1) goto L_0x0134;
        L_0x011c:
            r0 = org.xutils.http.HttpTask.q;
            monitor-enter(r0);
            r1 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0131 }
            r1.decrementAndGet();	 Catch:{ all -> 0x0131 }
            r1 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0131 }
            r1.notifyAll();	 Catch:{ all -> 0x0131 }
            monitor-exit(r0);	 Catch:{ all -> 0x0131 }
            goto L_0x0134;
        L_0x0131:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0131 }
            throw r1;
        L_0x0134:
            return;
        L_0x0135:
            r1 = java.io.File.class;
            r2 = org.xutils.http.HttpTask.this;
            r2 = r2.p;
            if (r1 != r2) goto L_0x0157;
        L_0x013f:
            r1 = org.xutils.http.HttpTask.q;
            monitor-enter(r1);
            r2 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0154 }
            r2.decrementAndGet();	 Catch:{ all -> 0x0154 }
            r2 = org.xutils.http.HttpTask.q;	 Catch:{ all -> 0x0154 }
            r2.notifyAll();	 Catch:{ all -> 0x0154 }
            monitor-exit(r1);	 Catch:{ all -> 0x0154 }
            goto L_0x0157;
        L_0x0154:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0154 }
            throw r0;
        L_0x0157:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.HttpTask$a.a():void");
        }
    }

    public HttpTask(RequestParams requestParams, Cancelable cancelable, CommonCallback<ResultType> commonCallback) {
        super(cancelable);
        if (!a && requestParams == null) {
            throw new AssertionError();
        } else if (a || commonCallback != null) {
            this.b = requestParams;
            this.g = commonCallback;
            if (commonCallback instanceof CacheCallback) {
                this.k = (CacheCallback) commonCallback;
            }
            if (commonCallback instanceof PrepareCallback) {
                this.l = (PrepareCallback) commonCallback;
            }
            if (commonCallback instanceof ProgressCallback) {
                this.m = (ProgressCallback) commonCallback;
            }
            if (commonCallback instanceof RequestInterceptListener) {
                this.n = (RequestInterceptListener) commonCallback;
            }
            RequestTracker requestTracker = requestParams.getRequestTracker();
            if (requestTracker == null) {
                if (commonCallback instanceof RequestTracker) {
                    requestTracker = (RequestTracker) commonCallback;
                } else {
                    requestTracker = UriRequestFactory.getDefaultTracker();
                }
            }
            if (requestTracker != null) {
                this.o = new b(requestTracker);
            }
            if (requestParams.getExecutor() != null) {
                this.e = requestParams.getExecutor();
            } else if (this.k != null) {
                this.e = t;
            } else {
                this.e = s;
            }
        } else {
            throw new AssertionError();
        }
    }

    private void b() {
        Class cls = this.g.getClass();
        if (this.g instanceof TypedCallback) {
            this.p = ((TypedCallback) this.g).getLoadType();
        } else if (this.g instanceof PrepareCallback) {
            this.p = ParameterizedTypeUtil.getParameterizedType(cls, PrepareCallback.class, 0);
        } else {
            this.p = ParameterizedTypeUtil.getParameterizedType(cls, CommonCallback.class, 0);
        }
    }

    private UriRequest c() throws Throwable {
        this.b.init();
        UriRequest uriRequest = UriRequestFactory.getUriRequest(this.b, this.p);
        uriRequest.setCallingClassLoader(this.g.getClass().getClassLoader());
        uriRequest.setProgressHandler(this);
        this.v = (long) this.b.getLoadingUpdateMaxTimeSpan();
        update(1, uriRequest);
        return uriRequest;
    }

    private void d() {
        if (File.class == this.p) {
            synchronized (r) {
                WeakReference weakReference;
                String saveFilePath = this.b.getSaveFilePath();
                if (!TextUtils.isEmpty(saveFilePath)) {
                    weakReference = (WeakReference) r.get(saveFilePath);
                    if (weakReference != null) {
                        HttpTask httpTask = (HttpTask) weakReference.get();
                        if (httpTask != null) {
                            httpTask.cancel();
                            httpTask.f();
                        }
                        r.remove(saveFilePath);
                    }
                    r.put(saveFilePath, new WeakReference(this));
                }
                if (r.size() > 3) {
                    Iterator it = r.entrySet().iterator();
                    while (it.hasNext()) {
                        weakReference = (WeakReference) ((Entry) it.next()).getValue();
                        if (weakReference == null || weakReference.get() == null) {
                            it.remove();
                        }
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0108 A:{SYNTHETIC, Splitter:B:67:0x0108} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00b7 */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ad A:{LOOP_START, SYNTHETIC, LOOP:3: B:34:0x00ad->B:141:0x00ad, LOOP_LABEL: LOOP:0: B:34:0x00ad->B:141:0x00ad} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00ad */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:129:? A:{SYNTHETIC, ExcHandler: HttpRedirectException (unused org.xutils.ex.HttpRedirectException), Splitter:B:67:0x0108} */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x01e4 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01c5  */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:37|38|39) */
    /* JADX WARNING: Missing block: B:41:0x00be, code skipped:
            throw new org.xutils.common.Callback.CancelledException("cancelled before request");
     */
    public ResultType doBackground() throws java.lang.Throwable {
        /*
        r9 = this;
        r0 = r9.isCancelled();
        if (r0 != 0) goto L_0x021b;
    L_0x0006:
        r9.b();
        r0 = r9.c();
        r9.c = r0;
        r9.d();
        r0 = r9.b;
        r0 = r0.getHttpRetryHandler();
        if (r0 != 0) goto L_0x001f;
    L_0x001a:
        r0 = new org.xutils.http.app.HttpRetryHandler;
        r0.<init>();
    L_0x001f:
        r1 = r9.b;
        r1 = r1.getMaxRetryCount();
        r0.setMaxRetryCount(r1);
        r1 = r9.isCancelled();
        if (r1 != 0) goto L_0x0213;
    L_0x002e:
        r1 = r9.k;
        r2 = 0;
        r3 = 1;
        r4 = 0;
        if (r1 == 0) goto L_0x00df;
    L_0x0035:
        r1 = r9.b;
        r1 = r1.getMethod();
        r1 = org.xutils.http.HttpMethod.permitsCache(r1);
        if (r1 == 0) goto L_0x00df;
    L_0x0041:
        r9.e();	 Catch:{ Throwable -> 0x0067 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0067 }
        r1.<init>();	 Catch:{ Throwable -> 0x0067 }
        r5 = "load cache: ";
        r1.append(r5);	 Catch:{ Throwable -> 0x0067 }
        r5 = r9.c;	 Catch:{ Throwable -> 0x0067 }
        r5 = r5.getRequestUri();	 Catch:{ Throwable -> 0x0067 }
        r1.append(r5);	 Catch:{ Throwable -> 0x0067 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x0067 }
        org.xutils.common.util.LogUtil.d(r1);	 Catch:{ Throwable -> 0x0067 }
        r1 = r9.c;	 Catch:{ Throwable -> 0x0067 }
        r1 = r1.loadResultFromCache();	 Catch:{ Throwable -> 0x0067 }
        r9.h = r1;	 Catch:{ Throwable -> 0x0067 }
        goto L_0x006d;
    L_0x0067:
        r1 = move-exception;
        r5 = "load disk cache error";
        org.xutils.common.util.LogUtil.w(r5, r1);
    L_0x006d:
        r1 = r9.isCancelled();
        if (r1 != 0) goto L_0x00d4;
    L_0x0073:
        r1 = r9.h;
        if (r1 == 0) goto L_0x00df;
    L_0x0077:
        r1 = r9.l;
        if (r1 == 0) goto L_0x0098;
    L_0x007b:
        r1 = r9.l;	 Catch:{ Throwable -> 0x0089 }
        r5 = r9.h;	 Catch:{ Throwable -> 0x0089 }
        r1 = r1.prepare(r5);	 Catch:{ Throwable -> 0x0089 }
        r9.e();
        goto L_0x009a;
    L_0x0087:
        r0 = move-exception;
        goto L_0x0094;
    L_0x0089:
        r1 = move-exception;
        r5 = "prepare disk cache error";
        org.xutils.common.util.LogUtil.w(r5, r1);	 Catch:{ all -> 0x0087 }
        r9.e();
        r1 = r4;
        goto L_0x009a;
    L_0x0094:
        r9.e();
        throw r0;
    L_0x0098:
        r1 = r9.h;
    L_0x009a:
        r5 = r9.isCancelled();
        if (r5 != 0) goto L_0x00cc;
    L_0x00a0:
        if (r1 == 0) goto L_0x00e0;
    L_0x00a2:
        r5 = 2;
        r6 = new java.lang.Object[r3];
        r6[r2] = r1;
        r9.update(r5, r6);
        r5 = r9.j;
        monitor-enter(r5);
    L_0x00ad:
        r6 = r9.i;	 Catch:{ all -> 0x00c9 }
        if (r6 != 0) goto L_0x00bf;
    L_0x00b1:
        r6 = r9.j;	 Catch:{ InterruptedException -> 0x00b7, Throwable -> 0x00ad }
        r6.wait();	 Catch:{ InterruptedException -> 0x00b7, Throwable -> 0x00ad }
        goto L_0x00ad;
    L_0x00b7:
        r0 = new org.xutils.common.Callback$CancelledException;	 Catch:{ all -> 0x00c9 }
        r1 = "cancelled before request";
        r0.<init>(r1);	 Catch:{ all -> 0x00c9 }
        throw r0;	 Catch:{ all -> 0x00c9 }
    L_0x00bf:
        monitor-exit(r5);	 Catch:{ all -> 0x00c9 }
        r5 = r9.i;
        r5 = r5.booleanValue();
        if (r5 == 0) goto L_0x00e0;
    L_0x00c8:
        return r4;
    L_0x00c9:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00c9 }
        throw r0;
    L_0x00cc:
        r0 = new org.xutils.common.Callback$CancelledException;
        r1 = "cancelled before request";
        r0.<init>(r1);
        throw r0;
    L_0x00d4:
        r9.e();
        r0 = new org.xutils.common.Callback$CancelledException;
        r1 = "cancelled before request";
        r0.<init>(r1);
        throw r0;
    L_0x00df:
        r1 = r4;
    L_0x00e0:
        r5 = r9.i;
        if (r5 != 0) goto L_0x00ea;
    L_0x00e4:
        r5 = java.lang.Boolean.valueOf(r2);
        r9.i = r5;
    L_0x00ea:
        if (r1 != 0) goto L_0x00f1;
    L_0x00ec:
        r1 = r9.c;
        r1.clearCacheHeader();
    L_0x00f1:
        r1 = r9.g;
        r1 = r1 instanceof org.xutils.common.Callback.ProxyCacheCallback;
        if (r1 == 0) goto L_0x0102;
    L_0x00f7:
        r1 = r9.g;
        r1 = (org.xutils.common.Callback.ProxyCacheCallback) r1;
        r1 = r1.onlyCache();
        if (r1 == 0) goto L_0x0102;
    L_0x0101:
        return r4;
    L_0x0102:
        r5 = r4;
        r6 = r5;
        r1 = 1;
        r7 = 0;
    L_0x0106:
        if (r1 == 0) goto L_0x0202;
    L_0x0108:
        r1 = r9.isCancelled();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r1 != 0) goto L_0x01b2;
    L_0x010e:
        r1 = r9.c;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r1.close();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r9.e();	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1.<init>();	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r8 = "load: ";
        r1.append(r8);	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r8 = r9.c;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r8 = r8.getRequestUri();	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1.append(r8);	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        org.xutils.common.util.LogUtil.d(r1);	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = new org.xutils.http.HttpTask$a;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1.<init>(r9, r4);	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r9.d = r1;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r9.d;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1.a();	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r9.d;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r1.b;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        if (r1 != 0) goto L_0x019a;
    L_0x0142:
        r1 = r9.d;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r1.a;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r9.h = r1;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r9.l;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r1 == 0) goto L_0x0171;
    L_0x014c:
        r1 = r9.isCancelled();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r1 != 0) goto L_0x0169;
    L_0x0152:
        r1 = r9.l;	 Catch:{ all -> 0x0164 }
        r8 = r9.h;	 Catch:{ all -> 0x0164 }
        r1 = r1.prepare(r8);	 Catch:{ all -> 0x0164 }
        r9.e();	 Catch:{ HttpRedirectException -> 0x0161, Throwable -> 0x015e }
        goto L_0x0173;
    L_0x015e:
        r5 = move-exception;
        r6 = r1;
        goto L_0x01bb;
    L_0x0161:
        r6 = r1;
        goto L_0x01e5;
    L_0x0164:
        r1 = move-exception;
        r9.e();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        throw r1;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x0169:
        r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r8 = "cancelled before request";
        r1.<init>(r8);	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        throw r1;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x0171:
        r1 = r9.h;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x0173:
        r6 = r1;
        r1 = r9.k;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r1 == 0) goto L_0x0189;
    L_0x0178:
        r1 = r9.b;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r1 = r1.getMethod();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r1 = org.xutils.http.HttpMethod.permitsCache(r1);	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r1 == 0) goto L_0x0189;
    L_0x0184:
        r1 = r9.c;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r1.save2Cache();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x0189:
        r1 = r9.isCancelled();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r1 != 0) goto L_0x0192;
    L_0x018f:
        r1 = 0;
        goto L_0x0106;
    L_0x0192:
        r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r8 = "cancelled after request";
        r1.<init>(r8);	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        throw r1;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x019a:
        r1 = r9.d;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        r1 = r1.b;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
        throw r1;	 Catch:{ Throwable -> 0x019f, HttpRedirectException -> 0x01e5 }
    L_0x019f:
        r1 = move-exception;
        r9.e();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r8 = r9.isCancelled();	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        if (r8 == 0) goto L_0x01b1;
    L_0x01a9:
        r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r8 = "cancelled during request";
        r1.<init>(r8);	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        throw r1;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x01b1:
        throw r1;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x01b2:
        r1 = new org.xutils.common.Callback$CancelledException;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        r8 = "cancelled before request";
        r1.<init>(r8);	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
        throw r1;	 Catch:{ HttpRedirectException -> 0x01e5, Throwable -> 0x01ba }
    L_0x01ba:
        r5 = move-exception;
    L_0x01bb:
        r1 = r9.c;
        r1 = r1.getResponseCode();
        r8 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r1 == r8) goto L_0x01e4;
    L_0x01c5:
        switch(r1) {
            case 204: goto L_0x01e4;
            case 205: goto L_0x01e4;
            default: goto L_0x01c8;
        };
    L_0x01c8:
        r1 = r9.isCancelled();
        if (r1 == 0) goto L_0x01da;
    L_0x01ce:
        r1 = r5 instanceof org.xutils.common.Callback.CancelledException;
        if (r1 != 0) goto L_0x01da;
    L_0x01d2:
        r1 = new org.xutils.common.Callback$CancelledException;
        r5 = "canceled by user";
        r1.<init>(r5);
        r5 = r1;
    L_0x01da:
        r1 = r9.c;
        r7 = r7 + 1;
        r1 = r0.canRetry(r1, r5, r7);
        goto L_0x0106;
    L_0x01e4:
        return r4;
    L_0x01e5:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r8 = "Http Redirect:";
        r1.append(r8);
        r8 = r9.b;
        r8 = r8.getUri();
        r1.append(r8);
        r1 = r1.toString();
        org.xutils.common.util.LogUtil.w(r1);
        r1 = 1;
        goto L_0x0106;
    L_0x0202:
        if (r5 == 0) goto L_0x0212;
    L_0x0204:
        if (r6 != 0) goto L_0x0212;
    L_0x0206:
        r0 = r9.i;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x020f;
    L_0x020e:
        goto L_0x0212;
    L_0x020f:
        r9.f = r3;
        throw r5;
    L_0x0212:
        return r6;
    L_0x0213:
        r0 = new org.xutils.common.Callback$CancelledException;
        r1 = "cancelled before request";
        r0.<init>(r1);
        throw r0;
    L_0x021b:
        r0 = new org.xutils.common.Callback$CancelledException;
        r1 = "cancelled before request";
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.HttpTask.doBackground():java.lang.Object");
    }

    /* Access modifiers changed, original: protected|varargs */
    public void onUpdate(int i, Object... objArr) {
        switch (i) {
            case 1:
                if (this.o != null) {
                    this.o.onRequestCreated((UriRequest) objArr[0]);
                    return;
                }
                return;
            case 2:
                synchronized (this.j) {
                    Object obj;
                    try {
                        obj = objArr[0];
                        if (this.o != null) {
                            this.o.onCache(this.c, obj);
                        }
                        this.i = Boolean.valueOf(this.k.onCache(obj));
                        obj = this.j;
                    } catch (Throwable th) {
                        try {
                            this.i = Boolean.valueOf(false);
                            this.g.onError(th, true);
                            obj = this.j;
                        } catch (Throwable th2) {
                            this.j.notifyAll();
                        }
                    }
                    obj.notifyAll();
                }
                return;
            case 3:
                if (this.m != null && objArr.length == 3) {
                    try {
                        this.m.onLoading(((Number) objArr[0]).longValue(), ((Number) objArr[1]).longValue(), ((Boolean) objArr[2]).booleanValue());
                        return;
                    } catch (Throwable th3) {
                        this.g.onError(th3, true);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onWaiting() {
        if (this.o != null) {
            this.o.onWaiting(this.b);
        }
        if (this.m != null) {
            this.m.onWaiting();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onStarted() {
        if (this.o != null) {
            this.o.onStart(this.b);
        }
        if (this.m != null) {
            this.m.onStarted();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSuccess(ResultType resultType) {
        if (!this.f) {
            if (this.o != null) {
                this.o.onSuccess(this.c, resultType);
            }
            this.g.onSuccess(resultType);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onError(Throwable th, boolean z) {
        if (this.o != null) {
            this.o.onError(this.c, th, z);
        }
        this.g.onError(th, z);
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled(CancelledException cancelledException) {
        if (this.o != null) {
            this.o.onCancelled(this.c);
        }
        this.g.onCancelled(cancelledException);
    }

    /* Access modifiers changed, original: protected */
    public void onFinished() {
        if (this.o != null) {
            this.o.onFinished(this.c);
        }
        x.task().run(new Runnable() {
            public void run() {
                HttpTask.this.f();
            }
        });
        this.g.onFinished();
    }

    private void e() {
        if (this.h instanceof Closeable) {
            IOUtil.closeQuietly((Closeable) this.h);
        }
        this.h = null;
    }

    /* Access modifiers changed, original: protected */
    public void cancelWorks() {
        x.task().run(new Runnable() {
            public void run() {
                HttpTask.this.f();
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public boolean isCancelFast() {
        return this.b.isCancelFast();
    }

    private void f() {
        e();
        IOUtil.closeQuietly(this.c);
    }

    public Executor getExecutor() {
        return this.e;
    }

    public Priority getPriority() {
        return this.b.getPriority();
    }

    public boolean updateProgress(long j, long j2, boolean z) {
        boolean z2 = false;
        if (isCancelled() || isFinished()) {
            return false;
        }
        if (!(this.m == null || this.c == null || j <= 0)) {
            if (j < j2) {
                j = j2;
            }
            if (z) {
                this.u = System.currentTimeMillis();
                update(3, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(this.c.isLoading()));
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.u >= this.v) {
                    this.u = currentTimeMillis;
                    update(3, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(this.c.isLoading()));
                }
            }
        }
        if (!(isCancelled() || isFinished())) {
            z2 = true;
        }
        return z2;
    }

    public String toString() {
        return this.b.toString();
    }
}
