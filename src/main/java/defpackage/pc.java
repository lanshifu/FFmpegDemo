package defpackage;

import android.text.TextUtils;
import com.one.tomato.entity.DomainJsonBean;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.mvp.base.okhttp.d;
import com.one.tomato.mvp.base.okhttp.d.a;
import com.one.tomato.mvp.base.okhttp.e;
import com.one.tomato.mvp.base.okhttp.interceptor.logging.Level;
import com.one.tomato.mvp.base.okhttp.interceptor.logging.c;
import com.one.tomato.utils.k;
import com.one.tomato.utils.o;
import io.reactivex.disposables.b;
import io.reactivex.r;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/* compiled from: DomainRequest */
/* renamed from: pc */
public class pc {
    private static pc h;
    private Map<String, ArrayList<String>> a = new HashMap();
    private Map<String, Integer> b = new HashMap();
    private Map<String, String> c = new HashMap();
    private pe d;
    private ArrayList<String> e = new ArrayList();
    private int f = 0;
    private int g = 0;

    /* compiled from: DomainRequest */
    /* renamed from: pc$1 */
    class 1 extends Factory {

        /* compiled from: DomainRequest */
        /* renamed from: pc$1$1 */
        class 1 implements Converter<ResponseBody, String> {
            1() {
            }

            /* renamed from: a */
            public String convert(ResponseBody responseBody) throws IOException {
                return responseBody.string();
            }
        }

        1() {
        }

        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
            return new 1();
        }
    }

    /* compiled from: DomainRequest */
    /* renamed from: pc$2 */
    class 2 implements r<String> {
        2() {
        }

        public void onSubscribe(b bVar) {
            o.b("博客：onSubscribe");
        }

        /* renamed from: a */
        public void onNext(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("博客请求成功：");
            stringBuilder.append(str);
            o.b(stringBuilder.toString());
            pc.this.d(str);
        }

        public void onError(Throwable th) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("博客请求失败：");
            stringBuilder.append(th.toString());
            o.b(stringBuilder.toString());
            pc.this.c();
        }

        public void onComplete() {
            o.b("博客：onComplete");
        }
    }

    private String d() {
        String str = "";
        switch (3) {
            case 1:
                return "domainTest";
            case 2:
                return "domainPre";
            case 3:
                return "domainOnline";
            default:
                return str;
        }
    }

    public static pc a() {
        if (h == null) {
            synchronized (pc.class) {
                if (h == null) {
                    h = new pc();
                }
            }
        }
        return h;
    }

    private pc() {
        for (int i = 0; i < pa.a.length; i++) {
            this.c.put(pa.a[i], pa.b[i]);
            c(pa.a[i]);
            this.b.put(pa.a[i], Integer.valueOf(0));
        }
        this.e.add("server");
        this.e.add("backDomain");
        this.e.add("backIP");
        this.e.add("backBlog");
    }

    public synchronized String b() {
        if (this.f >= this.e.size()) {
            this.f = 0;
        }
        return a((String) this.e.get(this.f));
    }

    public synchronized String a(String str) {
        Object obj = (Integer) this.b.get(str);
        ArrayList arrayList = (ArrayList) this.a.get(str);
        if (arrayList == null) {
            arrayList = c(str);
            if (arrayList == null || arrayList.isEmpty()) {
                return "http://www.google.com";
            }
        }
        if (obj.intValue() >= arrayList.size()) {
            obj = Integer.valueOf(0);
        }
        this.b.put(str, obj);
        return (String) arrayList.get(obj.intValue());
    }

    private synchronized ArrayList<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        String d = d();
        String str2 = (String) this.c.get(str);
        int i = 0;
        File file = new File(k.a(d, false), str2);
        if (!file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(d);
            stringBuffer.append(File.separator);
            stringBuffer.append(str2);
            k.a(file.getPath(), k.b(stringBuffer.toString()));
        }
        d = k.c(file.getPath());
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        try {
            d = pt.b(d, "WTSecret81234512");
            if (TextUtils.isEmpty(d)) {
                return null;
            }
            String[] split = d.split(",");
            int length = split.length;
            while (i < length) {
                String a = px.a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgbJOBAheQBA2CYoJVpdYGEtGt6vBb+msn6kub/21HS/CipN+uRUZkTVdex/amFmYLYqQQTK3AziBVBnWsz05jlyimzKrNvZprVsFyNOm6ULH1x/lzR/3Tj/5UcrN1gTE8eONWtYQ9nriy1+9RrMkem4aps4rWOhuy3mXq8PpfOwIDAQAB", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOBsk4ECF5AEDYJiglWl1gYS0a3q8Fv6ayfqS5v/bUdL8KKk365FRmRNV17H9qYWZgtipBBMrcDOIFUGdazPTmOXKKbMqs29mmtWwXI06bpQsfXH+XNH/dOP/lRys3WBMTx441a1hD2euLLX71GsyR6bhqmzitY6G7LeZerw+l87AgMBAAECgYA4HY4IbQB9RzYliwIx7kSEwkHhreQp64TNtzzupcCqWieyU22GwtWmENyu22sl/mXHpQOG+9VaZ3AYMoRMEI31yvUEFgQhqKVmQiYzhLP0eZFPZIrVf5SPmPcbU3+vNCQTEB6eO1XvORLWUGoEgdtVPmBSTX6/KiHuKWGvCS4FmQJBAPzaYqGUfmWOmRgAfBE7w1qRIDyq2evlDLzdqguGTpo0NkR6nxEYihGNb5zYypd6JpERvtf+Qycb++ZygzAY2McCQQDjN50OysQvdX2shfo73u/0XcbYlhHYyrHGAnanLhwMMirl6awxJHCoRcBwNrXjne/v/+WghySwp/Hn8MY0/9ntAkEA89QsVKB7mrd+DlU5Tu0Qn19fdOFUsFP6io4/Ekn7tlwvEK4mgjflvLNlNB0ikBws4Kv6GxOH8kjcCwfWViU/tQJBAJyFgQHhmEgBLbOdD4YSy0WRHBuzNVQcPV5j8Ay2bMfR/08mK2Im8hxZAHnMlnvYHqM7qplsv0+aQcA/UqrL3PkCQFqjDjFS9v9EBEkrD3MZxhQABeF2bxChmdGVsxrfRMWzQuIIxv95F9GforezYJ0jC/8EJNicNl3xKmAJAkfCmDU=", split[i]);
                if (!TextUtils.isEmpty(a)) {
                    arrayList.add(a);
                }
                i++;
            }
            this.a.put(str, arrayList);
            return arrayList;
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Missing block: B:18:0x004c, code skipped:
            return;
     */
    public synchronized void b(java.lang.String r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ all -> 0x004d }
        r0 = r0.get(r5);	 Catch:{ all -> 0x004d }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x004d }
        r1 = r4.b;	 Catch:{ all -> 0x004d }
        r1 = r1.get(r5);	 Catch:{ all -> 0x004d }
        r1 = (java.lang.Integer) r1;	 Catch:{ all -> 0x004d }
        if (r0 == 0) goto L_0x004b;
    L_0x0013:
        r2 = r0.isEmpty();	 Catch:{ all -> 0x004d }
        if (r2 == 0) goto L_0x001a;
    L_0x0019:
        goto L_0x004b;
    L_0x001a:
        r1 = r1.intValue();	 Catch:{ all -> 0x004d }
        r2 = 1;
        r1 = r1 + r2;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ all -> 0x004d }
        r3 = r1.intValue();	 Catch:{ all -> 0x004d }
        r0 = r0.size();	 Catch:{ all -> 0x004d }
        if (r3 < r0) goto L_0x0044;
    L_0x002e:
        r0 = 0;
        r1 = java.lang.Integer.valueOf(r0);	 Catch:{ all -> 0x004d }
        r3 = "server";
        r3 = r3.equals(r5);	 Catch:{ all -> 0x004d }
        if (r3 == 0) goto L_0x0044;
    L_0x003b:
        r3 = r4.g;	 Catch:{ all -> 0x004d }
        if (r3 == r2) goto L_0x0044;
    L_0x003f:
        r2 = "server";
        r4.a(r2, r0);	 Catch:{ all -> 0x004d }
    L_0x0044:
        r0 = r4.b;	 Catch:{ all -> 0x004d }
        r0.put(r5, r1);	 Catch:{ all -> 0x004d }
        monitor-exit(r4);
        return;
    L_0x004b:
        monitor-exit(r4);
        return;
    L_0x004d:
        r5 = move-exception;
        monitor-exit(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pc.b(java.lang.String):void");
    }

    public synchronized void a(String str, final boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求域名的type = ");
        stringBuilder.append(str);
        o.b(stringBuilder.toString());
        this.g = 1;
        if (this.d != null) {
            this.d.a(this.g);
        }
        if ("backBlog".equals(str)) {
            a a = d.a();
            Retrofit build = new Builder().client(new OkHttpClient.Builder().addInterceptor(new od(false)).sslSocketFactory(a.a, a.b).addInterceptor(new oc()).addInterceptor(new c.a().b(false).a(Level.BASIC).a(4).a("Request").b("Response").a("log-header", "I am the log request header.").a("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").e()).connectTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS)).build()).addConverterFactory(new 1()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).baseUrl("http://www.google.com/").build();
            ((pf) build.create(pf.class)).a(pc.a().a("backBlog")).subscribeOn(xl.b()).unsubscribeOn(xl.b()).observeOn(xl.b()).subscribe(new 2());
        } else {
            ((pf) e.a().a(pf.class)).a().subscribeOn(xl.b()).unsubscribeOn(xl.b()).observeOn(xl.b()).subscribe(new com.one.tomato.mvp.base.okhttp.a<DomainJsonBean>() {
                public void a(DomainJsonBean domainJsonBean) {
                    pc.this.a(domainJsonBean);
                }

                public void a(ResponseThrowable responseThrowable) {
                    if (!z) {
                        pc.this.c();
                    }
                }
            });
        }
    }

    private synchronized void a(DomainJsonBean domainJsonBean) {
        for (int i = 0; i < pa.a.length; i++) {
            Object obj = pa.a[i];
            String str = (String) this.c.get(obj);
            File file = new File(k.a(d(), false), str);
            String str2 = "";
            try {
                str2 = (String) domainJsonBean.getClass().getField(obj).get(domainJsonBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
            k.a(file.getPath(), str2);
            c(obj);
            this.b.put(pa.a[i], Integer.valueOf(0));
        }
        this.f = 0;
        this.g = 2;
        if (this.d != null) {
            this.d.a(this.g);
        }
    }

    /* JADX WARNING: Missing block: B:31:0x00ca, code skipped:
            return;
     */
    private synchronized void d(java.lang.String r8) {
        /*
        r7 = this;
        monitor-enter(r7);
        r0 = android.text.TextUtils.isEmpty(r8);	 Catch:{ all -> 0x00cb }
        if (r0 == 0) goto L_0x000c;
    L_0x0007:
        r7.c();	 Catch:{ all -> 0x00cb }
        monitor-exit(r7);
        return;
    L_0x000c:
        r0 = "Winter//:";
        r1 = "://Winter";
        r2 = r8.indexOf(r0);	 Catch:{ all -> 0x00cb }
        r1 = r8.indexOf(r1);	 Catch:{ all -> 0x00cb }
        r0 = r0.length();	 Catch:{ all -> 0x00cb }
        r3 = -1;
        if (r2 == r3) goto L_0x00c9;
    L_0x001f:
        if (r1 == r3) goto L_0x00c9;
    L_0x0021:
        r2 = r2 + r0;
        r8 = r8.substring(r2, r1);	 Catch:{ all -> 0x00cb }
        r0 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        r0.<init>();	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        r1 = "博客加密字符串：";
        r0.append(r1);	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        r0.append(r8);	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        r0 = r0.toString();	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        com.one.tomato.utils.o.b(r0);	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        r0 = "WTSecret81234512";
        r8 = defpackage.pt.b(r8, r0);	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        r0 = android.text.TextUtils.isEmpty(r8);	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        if (r0 == 0) goto L_0x004b;
    L_0x0046:
        r7.c();	 Catch:{ UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0, UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x00c0 }
        monitor-exit(r7);
        return;
    L_0x004b:
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r0.<init>();	 Catch:{ all -> 0x00cb }
        r1 = "博客解密字符串：";
        r0.append(r1);	 Catch:{ all -> 0x00cb }
        r0.append(r8);	 Catch:{ all -> 0x00cb }
        r0 = r0.toString();	 Catch:{ all -> 0x00cb }
        com.one.tomato.utils.o.b(r0);	 Catch:{ all -> 0x00cb }
        r0 = ",";
        r8 = r8.split(r0);	 Catch:{ all -> 0x00cb }
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x00cb }
        r0.<init>();	 Catch:{ all -> 0x00cb }
        r1 = r8.length;	 Catch:{ all -> 0x00cb }
        r2 = 0;
        r3 = 0;
    L_0x006d:
        if (r3 >= r1) goto L_0x0085;
    L_0x006f:
        r4 = r8[r3];	 Catch:{ all -> 0x00cb }
        r5 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgbJOBAheQBA2CYoJVpdYGEtGt6vBb+msn6kub/21HS/CipN+uRUZkTVdex/amFmYLYqQQTK3AziBVBnWsz05jlyimzKrNvZprVsFyNOm6ULH1x/lzR/3Tj/5UcrN1gTE8eONWtYQ9nriy1+9RrMkem4aps4rWOhuy3mXq8PpfOwIDAQAB";
        r6 = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOBsk4ECF5AEDYJiglWl1gYS0a3q8Fv6ayfqS5v/bUdL8KKk365FRmRNV17H9qYWZgtipBBMrcDOIFUGdazPTmOXKKbMqs29mmtWwXI06bpQsfXH+XNH/dOP/lRys3WBMTx441a1hD2euLLX71GsyR6bhqmzitY6G7LeZerw+l87AgMBAAECgYA4HY4IbQB9RzYliwIx7kSEwkHhreQp64TNtzzupcCqWieyU22GwtWmENyu22sl/mXHpQOG+9VaZ3AYMoRMEI31yvUEFgQhqKVmQiYzhLP0eZFPZIrVf5SPmPcbU3+vNCQTEB6eO1XvORLWUGoEgdtVPmBSTX6/KiHuKWGvCS4FmQJBAPzaYqGUfmWOmRgAfBE7w1qRIDyq2evlDLzdqguGTpo0NkR6nxEYihGNb5zYypd6JpERvtf+Qycb++ZygzAY2McCQQDjN50OysQvdX2shfo73u/0XcbYlhHYyrHGAnanLhwMMirl6awxJHCoRcBwNrXjne/v/+WghySwp/Hn8MY0/9ntAkEA89QsVKB7mrd+DlU5Tu0Qn19fdOFUsFP6io4/Ekn7tlwvEK4mgjflvLNlNB0ikBws4Kv6GxOH8kjcCwfWViU/tQJBAJyFgQHhmEgBLbOdD4YSy0WRHBuzNVQcPV5j8Ay2bMfR/08mK2Im8hxZAHnMlnvYHqM7qplsv0+aQcA/UqrL3PkCQFqjDjFS9v9EBEkrD3MZxhQABeF2bxChmdGVsxrfRMWzQuIIxv95F9GforezYJ0jC/8EJNicNl3xKmAJAkfCmDU=";
        r4 = defpackage.px.a(r5, r6, r4);	 Catch:{ all -> 0x00cb }
        r5 = android.text.TextUtils.isEmpty(r4);	 Catch:{ all -> 0x00cb }
        if (r5 != 0) goto L_0x0082;
    L_0x007f:
        r0.add(r4);	 Catch:{ all -> 0x00cb }
    L_0x0082:
        r3 = r3 + 1;
        goto L_0x006d;
    L_0x0085:
        r8 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00cb }
        r8.<init>();	 Catch:{ all -> 0x00cb }
        r1 = "博客解析地址：";
        r8.append(r1);	 Catch:{ all -> 0x00cb }
        r1 = r0.toString();	 Catch:{ all -> 0x00cb }
        r8.append(r1);	 Catch:{ all -> 0x00cb }
        r8 = r8.toString();	 Catch:{ all -> 0x00cb }
        com.one.tomato.utils.o.b(r8);	 Catch:{ all -> 0x00cb }
        r8 = r7.a;	 Catch:{ all -> 0x00cb }
        r1 = "server";
        r8.put(r1, r0);	 Catch:{ all -> 0x00cb }
        r8 = r7.b;	 Catch:{ all -> 0x00cb }
        r0 = "server";
        r1 = java.lang.Integer.valueOf(r2);	 Catch:{ all -> 0x00cb }
        r8.put(r0, r1);	 Catch:{ all -> 0x00cb }
        r7.f = r2;	 Catch:{ all -> 0x00cb }
        r8 = r7.e;	 Catch:{ all -> 0x00cb }
        r0 = r7.f;	 Catch:{ all -> 0x00cb }
        r8 = r8.get(r0);	 Catch:{ all -> 0x00cb }
        r8 = (java.lang.String) r8;	 Catch:{ all -> 0x00cb }
        r0 = 1;
        r7.a(r8, r0);	 Catch:{ all -> 0x00cb }
        goto L_0x00c9;
    L_0x00c0:
        r8 = move-exception;
        r8.printStackTrace();	 Catch:{ all -> 0x00cb }
        r7.c();	 Catch:{ all -> 0x00cb }
        monitor-exit(r7);
        return;
    L_0x00c9:
        monitor-exit(r7);
        return;
    L_0x00cb:
        r8 = move-exception;
        monitor-exit(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pc.d(java.lang.String):void");
    }

    private synchronized void c() {
        this.f++;
        if (this.f < this.e.size()) {
            a((String) this.e.get(this.f), false);
        } else {
            this.f = 0;
            for (Object put : pa.a) {
                this.b.put(put, Integer.valueOf(0));
            }
            this.g = 3;
            if (this.d != null) {
                this.d.a(this.g);
            }
        }
    }

    public void a(pe peVar) {
        this.d = peVar;
    }
}
