package org.xutils.http.cookie;

import android.text.TextUtils;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;

public enum DbCookieStore implements CookieStore {
    INSTANCE;
    
    private final DbManager db;
    private long lastTrimTime;
    private final Executor trimExecutor;

    public void add(URI uri, HttpCookie httpCookie) {
        if (httpCookie != null) {
            try {
                this.db.replace(new a(a(uri), httpCookie));
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
            a();
        }
    }

    public List<HttpCookie> get(URI uri) {
        if (uri != null) {
            uri = a(uri);
            ArrayList arrayList = new ArrayList();
            try {
                WhereBuilder or;
                int indexOf;
                Selector selector = this.db.selector(a.class);
                WhereBuilder b = WhereBuilder.b();
                String host = uri.getHost();
                if (!TextUtils.isEmpty(host)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(".");
                    stringBuilder.append(host);
                    or = WhereBuilder.b("domain", "=", host).or("domain", "=", stringBuilder.toString());
                    indexOf = host.indexOf(".");
                    int lastIndexOf = host.lastIndexOf(".");
                    if (indexOf > 0 && lastIndexOf > indexOf) {
                        host = host.substring(indexOf, host.length());
                        if (!TextUtils.isEmpty(host)) {
                            or.or("domain", "=", host);
                        }
                    }
                    b.and(or);
                }
                host = uri.getPath();
                if (!TextUtils.isEmpty(host)) {
                    or = WhereBuilder.b("path", "=", host).or("path", "=", "/").or("path", "=", null);
                    indexOf = host.lastIndexOf("/");
                    while (indexOf > 0) {
                        host = host.substring(0, indexOf);
                        or.or("path", "=", host);
                        indexOf = host.lastIndexOf("/");
                    }
                    b.and(or);
                }
                b.or("uri", "=", uri.toString());
                List<a> findAll = selector.where(b).findAll();
                if (findAll != null) {
                    for (a aVar : findAll) {
                        if (!aVar.isExpired()) {
                            arrayList.add(aVar.toHttpCookie());
                        }
                    }
                }
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
            return arrayList;
        }
        throw new NullPointerException("uri is null");
    }

    public List<HttpCookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        try {
            List<a> findAll = this.db.findAll(a.class);
            if (findAll != null) {
                for (a aVar : findAll) {
                    if (!aVar.isExpired()) {
                        arrayList.add(aVar.toHttpCookie());
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return arrayList;
    }

    public List<URI> getURIs() {
        ArrayList arrayList = new ArrayList();
        String string;
        try {
            List<DbModel> findAll = this.db.selector(a.class).select("uri").findAll();
            if (findAll != null) {
                for (DbModel string2 : findAll) {
                    string = string2.getString("uri");
                    if (!TextUtils.isEmpty(string)) {
                        arrayList.add(new URI(string));
                    }
                }
            }
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return arrayList;
    }

    public boolean remove(URI uri, HttpCookie httpCookie) {
        boolean z = true;
        if (httpCookie == null) {
            return true;
        }
        try {
            WhereBuilder b = WhereBuilder.b("name", "=", httpCookie.getName());
            String domain = httpCookie.getDomain();
            if (!TextUtils.isEmpty(domain)) {
                b.and("domain", "=", domain);
            }
            Object path = httpCookie.getPath();
            if (!TextUtils.isEmpty(path)) {
                if (path.length() > 1 && path.endsWith("/")) {
                    path = path.substring(0, path.length() - 1);
                }
                b.and("path", "=", path);
            }
            this.db.delete(a.class, b);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
            z = false;
        }
        return z;
    }

    public boolean removeAll() {
        try {
            this.db.delete(a.class);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return true;
    }

    private void a() {
        this.trimExecutor.execute(new Runnable() {
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - DbCookieStore.this.lastTrimTime >= 1000) {
                    DbCookieStore.this.lastTrimTime = currentTimeMillis;
                    try {
                        DbCookieStore.this.db.delete(a.class, WhereBuilder.b("expiry", "<", Long.valueOf(System.currentTimeMillis())).and("expiry", "!=", Long.valueOf(-1)));
                    } catch (Throwable th) {
                        LogUtil.e(th.getMessage(), th);
                    }
                    try {
                        int count = (int) DbCookieStore.this.db.selector(a.class).count();
                        if (count > 5010) {
                            Object findAll = DbCookieStore.this.db.selector(a.class).where("expiry", "!=", Long.valueOf(-1)).orderBy("expiry", false).limit(count - 5000).findAll();
                            if (findAll != null) {
                                DbCookieStore.this.db.delete(findAll);
                            }
                        }
                    } catch (Throwable th2) {
                        LogUtil.e(th2.getMessage(), th2);
                    }
                }
            }
        });
    }

    private URI a(URI uri) {
        try {
            return new URI("http", uri.getHost(), uri.getPath(), null, null);
        } catch (Throwable unused) {
            return uri;
        }
    }
}
