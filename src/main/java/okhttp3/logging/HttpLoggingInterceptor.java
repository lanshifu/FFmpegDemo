package okhttp3.logging;

import java.io.EOFException;
import java.nio.charset.Charset;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.internal.platform.Platform;
import okio.c;

public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() {
            public void log(String str) {
                Platform.get().log(4, str, null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.level = Level.NONE;
        this.logger = logger;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level != null) {
            this.level = level;
            return this;
        }
        throw new NullPointerException("level == null. Use Level.NONE instead.");
    }

    public Level getLevel() {
        return this.level;
    }

    /* JADX WARNING: Removed duplicated region for block: B:92:0x0329  */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r17) throws java.io.IOException {
        /*
        r16 = this;
        r1 = r16;
        r0 = r17;
        r2 = r1.level;
        r3 = r17.request();
        r4 = okhttp3.logging.HttpLoggingInterceptor.Level.NONE;
        if (r2 != r4) goto L_0x0013;
    L_0x000e:
        r0 = r0.proceed(r3);
        return r0;
    L_0x0013:
        r4 = okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
        r6 = 1;
        if (r2 != r4) goto L_0x001a;
    L_0x0018:
        r4 = 1;
        goto L_0x001b;
    L_0x001a:
        r4 = 0;
    L_0x001b:
        if (r4 != 0) goto L_0x0024;
    L_0x001d:
        r7 = okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
        if (r2 != r7) goto L_0x0022;
    L_0x0021:
        goto L_0x0024;
    L_0x0022:
        r2 = 0;
        goto L_0x0025;
    L_0x0024:
        r2 = 1;
    L_0x0025:
        r7 = r3.body();
        if (r7 == 0) goto L_0x002c;
    L_0x002b:
        goto L_0x002d;
    L_0x002c:
        r6 = 0;
    L_0x002d:
        r8 = r17.connection();
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "--> ";
        r9.append(r10);
        r10 = r3.method();
        r9.append(r10);
        r10 = 32;
        r9.append(r10);
        r11 = r3.url();
        r9.append(r11);
        if (r8 == 0) goto L_0x0066;
    L_0x0050:
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = " ";
        r11.append(r12);
        r8 = r8.protocol();
        r11.append(r8);
        r8 = r11.toString();
        goto L_0x0068;
    L_0x0066:
        r8 = "";
    L_0x0068:
        r9.append(r8);
        r8 = r9.toString();
        if (r2 != 0) goto L_0x0090;
    L_0x0071:
        if (r6 == 0) goto L_0x0090;
    L_0x0073:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r9.append(r8);
        r8 = " (";
        r9.append(r8);
        r11 = r7.contentLength();
        r9.append(r11);
        r8 = "-byte body)";
        r9.append(r8);
        r8 = r9.toString();
    L_0x0090:
        r9 = r1.logger;
        r9.log(r8);
        r8 = -1;
        if (r2 == 0) goto L_0x01eb;
    L_0x0099:
        if (r6 == 0) goto L_0x00dd;
    L_0x009b:
        r11 = r7.contentType();
        if (r11 == 0) goto L_0x00bb;
    L_0x00a1:
        r11 = r1.logger;
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "Content-Type: ";
        r12.append(r13);
        r13 = r7.contentType();
        r12.append(r13);
        r12 = r12.toString();
        r11.log(r12);
    L_0x00bb:
        r11 = r7.contentLength();
        r13 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1));
        if (r13 == 0) goto L_0x00dd;
    L_0x00c3:
        r11 = r1.logger;
        r12 = new java.lang.StringBuilder;
        r12.<init>();
        r13 = "Content-Length: ";
        r12.append(r13);
        r13 = r7.contentLength();
        r12.append(r13);
        r12 = r12.toString();
        r11.log(r12);
    L_0x00dd:
        r11 = r3.headers();
        r12 = r11.size();
        r13 = 0;
    L_0x00e6:
        if (r13 >= r12) goto L_0x011c;
    L_0x00e8:
        r14 = r11.name(r13);
        r15 = "Content-Type";
        r15 = r15.equalsIgnoreCase(r14);
        if (r15 != 0) goto L_0x0119;
    L_0x00f4:
        r15 = "Content-Length";
        r15 = r15.equalsIgnoreCase(r14);
        if (r15 != 0) goto L_0x0119;
    L_0x00fc:
        r15 = r1.logger;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r5.append(r14);
        r14 = ": ";
        r5.append(r14);
        r14 = r11.value(r13);
        r5.append(r14);
        r5 = r5.toString();
        r15.log(r5);
    L_0x0119:
        r13 = r13 + 1;
        goto L_0x00e6;
    L_0x011c:
        if (r4 == 0) goto L_0x01d1;
    L_0x011e:
        if (r6 != 0) goto L_0x0122;
    L_0x0120:
        goto L_0x01d1;
    L_0x0122:
        r5 = r3.headers();
        r5 = r1.bodyHasUnknownEncoding(r5);
        if (r5 == 0) goto L_0x014d;
    L_0x012c:
        r5 = r1.logger;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "--> END ";
        r6.append(r7);
        r7 = r3.method();
        r6.append(r7);
        r7 = " (encoded body omitted)";
        r6.append(r7);
        r6 = r6.toString();
        r5.log(r6);
        goto L_0x01eb;
    L_0x014d:
        r5 = new okio.c;
        r5.<init>();
        r7.writeTo(r5);
        r6 = UTF8;
        r11 = r7.contentType();
        if (r11 == 0) goto L_0x0163;
    L_0x015d:
        r6 = UTF8;
        r6 = r11.charset(r6);
    L_0x0163:
        r11 = r1.logger;
        r12 = "";
        r11.log(r12);
        r11 = isPlaintext(r5);
        if (r11 == 0) goto L_0x01a5;
    L_0x0170:
        r11 = r1.logger;
        r5 = r5.a(r6);
        r11.log(r5);
        r5 = r1.logger;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r11 = "--> END ";
        r6.append(r11);
        r11 = r3.method();
        r6.append(r11);
        r11 = " (";
        r6.append(r11);
        r11 = r7.contentLength();
        r6.append(r11);
        r7 = "-byte body)";
        r6.append(r7);
        r6 = r6.toString();
        r5.log(r6);
        goto L_0x01eb;
    L_0x01a5:
        r5 = r1.logger;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r11 = "--> END ";
        r6.append(r11);
        r11 = r3.method();
        r6.append(r11);
        r11 = " (binary ";
        r6.append(r11);
        r11 = r7.contentLength();
        r6.append(r11);
        r7 = "-byte body omitted)";
        r6.append(r7);
        r6 = r6.toString();
        r5.log(r6);
        goto L_0x01eb;
    L_0x01d1:
        r5 = r1.logger;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "--> END ";
        r6.append(r7);
        r7 = r3.method();
        r6.append(r7);
        r6 = r6.toString();
        r5.log(r6);
    L_0x01eb:
        r5 = java.lang.System.nanoTime();
        r0 = r0.proceed(r3);	 Catch:{ Exception -> 0x03d5 }
        r3 = java.util.concurrent.TimeUnit.NANOSECONDS;
        r11 = java.lang.System.nanoTime();
        r11 = r11 - r5;
        r5 = r3.toMillis(r11);
        r3 = r0.body();
        r11 = r3.contentLength();
        r7 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1));
        if (r7 == 0) goto L_0x021c;
    L_0x020a:
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r7.append(r11);
        r8 = "-byte";
        r7.append(r8);
        r7 = r7.toString();
        goto L_0x021e;
    L_0x021c:
        r7 = "unknown-length";
    L_0x021e:
        r8 = r1.logger;
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r13 = "<-- ";
        r9.append(r13);
        r13 = r0.code();
        r9.append(r13);
        r13 = r0.message();
        r13 = r13.isEmpty();
        if (r13 == 0) goto L_0x023e;
    L_0x023b:
        r13 = "";
        goto L_0x0251;
    L_0x023e:
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r13.append(r10);
        r14 = r0.message();
        r13.append(r14);
        r13 = r13.toString();
    L_0x0251:
        r9.append(r13);
        r9.append(r10);
        r10 = r0.request();
        r10 = r10.url();
        r9.append(r10);
        r10 = " (";
        r9.append(r10);
        r9.append(r5);
        r5 = "ms";
        r9.append(r5);
        if (r2 != 0) goto L_0x0288;
    L_0x0271:
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = ", ";
        r5.append(r6);
        r5.append(r7);
        r6 = " body";
        r5.append(r6);
        r5 = r5.toString();
        goto L_0x028a;
    L_0x0288:
        r5 = "";
    L_0x028a:
        r9.append(r5);
        r5 = 41;
        r9.append(r5);
        r5 = r9.toString();
        r8.log(r5);
        if (r2 == 0) goto L_0x03d4;
    L_0x029b:
        r2 = r0.headers();
        r5 = r2.size();
        r6 = 0;
    L_0x02a4:
        if (r6 >= r5) goto L_0x02ca;
    L_0x02a6:
        r7 = r1.logger;
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = r2.name(r6);
        r8.append(r9);
        r9 = ": ";
        r8.append(r9);
        r9 = r2.value(r6);
        r8.append(r9);
        r8 = r8.toString();
        r7.log(r8);
        r6 = r6 + 1;
        goto L_0x02a4;
    L_0x02ca:
        if (r4 == 0) goto L_0x03cd;
    L_0x02cc:
        r4 = okhttp3.internal.http.HttpHeaders.hasBody(r0);
        if (r4 != 0) goto L_0x02d4;
    L_0x02d2:
        goto L_0x03cd;
    L_0x02d4:
        r4 = r0.headers();
        r4 = r1.bodyHasUnknownEncoding(r4);
        if (r4 == 0) goto L_0x02e7;
    L_0x02de:
        r2 = r1.logger;
        r3 = "<-- END HTTP (encoded body omitted)";
        r2.log(r3);
        goto L_0x03d4;
    L_0x02e7:
        r4 = r3.source();
        r5 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r4.b(r5);
        r4 = r4.b();
        r5 = "gzip";
        r6 = "Content-Encoding";
        r2 = r2.get(r6);
        r2 = r5.equalsIgnoreCase(r2);
        r5 = 0;
        if (r2 == 0) goto L_0x032d;
    L_0x0306:
        r6 = r4.a();
        r2 = java.lang.Long.valueOf(r6);
        r6 = new okio.j;	 Catch:{ all -> 0x0326 }
        r4 = r4.x();	 Catch:{ all -> 0x0326 }
        r6.<init>(r4);	 Catch:{ all -> 0x0326 }
        r4 = new okio.c;	 Catch:{ all -> 0x0323 }
        r4.<init>();	 Catch:{ all -> 0x0323 }
        r4.a(r6);	 Catch:{ all -> 0x0323 }
        r6.close();
        goto L_0x032e;
    L_0x0323:
        r0 = move-exception;
        r5 = r6;
        goto L_0x0327;
    L_0x0326:
        r0 = move-exception;
    L_0x0327:
        if (r5 == 0) goto L_0x032c;
    L_0x0329:
        r5.close();
    L_0x032c:
        throw r0;
    L_0x032d:
        r2 = r5;
    L_0x032e:
        r5 = UTF8;
        r3 = r3.contentType();
        if (r3 == 0) goto L_0x033c;
    L_0x0336:
        r5 = UTF8;
        r5 = r3.charset(r5);
    L_0x033c:
        r3 = isPlaintext(r4);
        if (r3 != 0) goto L_0x0369;
    L_0x0342:
        r2 = r1.logger;
        r3 = "";
        r2.log(r3);
        r2 = r1.logger;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "<-- END HTTP (binary ";
        r3.append(r5);
        r4 = r4.a();
        r3.append(r4);
        r4 = "-byte body omitted)";
        r3.append(r4);
        r3 = r3.toString();
        r2.log(r3);
        return r0;
    L_0x0369:
        r6 = 0;
        r3 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1));
        if (r3 == 0) goto L_0x0383;
    L_0x036f:
        r3 = r1.logger;
        r6 = "";
        r3.log(r6);
        r3 = r1.logger;
        r6 = r4.x();
        r5 = r6.a(r5);
        r3.log(r5);
    L_0x0383:
        if (r2 == 0) goto L_0x03ad;
    L_0x0385:
        r3 = r1.logger;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "<-- END HTTP (";
        r5.append(r6);
        r6 = r4.a();
        r5.append(r6);
        r4 = "-byte, ";
        r5.append(r4);
        r5.append(r2);
        r2 = "-gzipped-byte body)";
        r5.append(r2);
        r2 = r5.toString();
        r3.log(r2);
        goto L_0x03d4;
    L_0x03ad:
        r2 = r1.logger;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "<-- END HTTP (";
        r3.append(r5);
        r4 = r4.a();
        r3.append(r4);
        r4 = "-byte body)";
        r3.append(r4);
        r3 = r3.toString();
        r2.log(r3);
        goto L_0x03d4;
    L_0x03cd:
        r2 = r1.logger;
        r3 = "<-- END HTTP";
        r2.log(r3);
    L_0x03d4:
        return r0;
    L_0x03d5:
        r0 = move-exception;
        r2 = r0;
        r0 = r1.logger;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "<-- HTTP FAILED: ";
        r3.append(r4);
        r3.append(r2);
        r3 = r3.toString();
        r0.log(r3);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.logging.HttpLoggingInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    static boolean isPlaintext(c cVar) {
        try {
            c cVar2 = new c();
            cVar.a(cVar2, 0, cVar.a() < 64 ? cVar.a() : 64);
            for (int i = 0; i < 16; i++) {
                if (cVar2.f()) {
                    break;
                }
                int u = cVar2.u();
                if (Character.isISOControl(u) && !Character.isWhitespace(u)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get("Content-Encoding");
        return (str == null || str.equalsIgnoreCase("identity") || str.equalsIgnoreCase("gzip")) ? false : true;
    }
}
