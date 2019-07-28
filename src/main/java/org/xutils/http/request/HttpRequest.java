package org.xutils.http.request;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;
import org.xutils.http.BaseParams.Header;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.ProgressBody;
import org.xutils.http.body.RequestBody;
import org.xutils.http.cookie.DbCookieStore;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

public class HttpRequest extends UriRequest {
    private static final CookieManager f = new CookieManager(DbCookieStore.INSTANCE, CookiePolicy.ACCEPT_ALL);
    private String a = null;
    private boolean b = false;
    private InputStream c = null;
    private HttpURLConnection d = null;
    private int e = 0;

    HttpRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    /* Access modifiers changed, original: protected */
    public String buildQueryUrl(RequestParams requestParams) {
        String uri = requestParams.getUri();
        StringBuilder stringBuilder = new StringBuilder(uri);
        if (!uri.contains("?")) {
            stringBuilder.append("?");
        } else if (!uri.endsWith("?")) {
            stringBuilder.append("&");
        }
        List<KeyValue> queryStringParams = requestParams.getQueryStringParams();
        if (queryStringParams != null) {
            for (KeyValue keyValue : queryStringParams) {
                String str = keyValue.key;
                String valueStr = keyValue.getValueStr();
                if (!(TextUtils.isEmpty(str) || valueStr == null)) {
                    stringBuilder.append(Uri.encode(str, requestParams.getCharset()));
                    stringBuilder.append("=");
                    stringBuilder.append(Uri.encode(valueStr, requestParams.getCharset()));
                    stringBuilder.append("&");
                }
            }
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == '&') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == '?') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public String getRequestUri() {
        String str = this.queryUrl;
        if (this.d == null) {
            return str;
        }
        URL url = this.d.getURL();
        return url != null ? url.toString() : str;
    }

    @TargetApi(19)
    public void sendRequest() throws Throwable {
        this.b = false;
        this.e = 0;
        URL url = new URL(this.queryUrl);
        Proxy proxy = this.params.getProxy();
        if (proxy != null) {
            this.d = (HttpURLConnection) url.openConnection(proxy);
        } else {
            this.d = (HttpURLConnection) url.openConnection();
        }
        if (VERSION.SDK_INT < 19) {
            this.d.setRequestProperty("Connection", "close");
        }
        this.d.setReadTimeout(this.params.getReadTimeout());
        this.d.setConnectTimeout(this.params.getConnectTimeout());
        this.d.setInstanceFollowRedirects(this.params.getRedirectHandler() == null);
        if (this.d instanceof HttpsURLConnection) {
            SSLSocketFactory sslSocketFactory = this.params.getSslSocketFactory();
            if (sslSocketFactory != null) {
                ((HttpsURLConnection) this.d).setSSLSocketFactory(sslSocketFactory);
            }
        }
        if (this.params.isUseCookie()) {
            try {
                List list = (List) f.get(url.toURI(), new HashMap(0)).get("Cookie");
                if (list != null) {
                    this.d.setRequestProperty("Cookie", TextUtils.join(";", list));
                }
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
        }
        List<Header> headers = this.params.getHeaders();
        if (headers != null) {
            for (Header header : headers) {
                String str = header.key;
                String valueStr = header.getValueStr();
                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(valueStr))) {
                    if (header.setHeader) {
                        this.d.setRequestProperty(str, valueStr);
                    } else {
                        this.d.addRequestProperty(str, valueStr);
                    }
                }
            }
        }
        if (this.requestInterceptListener != null) {
            this.requestInterceptListener.beforeRequest(this);
        }
        HttpMethod method = this.params.getMethod();
        try {
            this.d.setRequestMethod(method.toString());
        } catch (ProtocolException e) {
            Field declaredField = HttpURLConnection.class.getDeclaredField("method");
            declaredField.setAccessible(true);
            declaredField.set(this.d, method.toString());
        }
        if (HttpMethod.permitsRequestBody(method)) {
            RequestBody requestBody = this.params.getRequestBody();
            if (requestBody != null) {
                if (requestBody instanceof ProgressBody) {
                    ((ProgressBody) requestBody).setProgressHandler(this.progressHandler);
                }
                String contentType = requestBody.getContentType();
                if (!TextUtils.isEmpty(contentType)) {
                    this.d.setRequestProperty("Content-Type", contentType);
                }
                long contentLength = requestBody.getContentLength();
                if (contentLength < 0) {
                    this.d.setChunkedStreamingMode(262144);
                } else if (contentLength < 2147483647L) {
                    this.d.setFixedLengthStreamingMode((int) contentLength);
                } else if (VERSION.SDK_INT >= 19) {
                    this.d.setFixedLengthStreamingMode(contentLength);
                } else {
                    this.d.setChunkedStreamingMode(262144);
                }
                this.d.setRequestProperty("Content-Length", String.valueOf(contentLength));
                this.d.setDoOutput(true);
                requestBody.writeTo(this.d.getOutputStream());
            }
        }
        if (this.params.isUseCookie()) {
            try {
                Map headerFields = this.d.getHeaderFields();
                if (headerFields != null) {
                    f.put(url.toURI(), headerFields);
                }
            } catch (Throwable th2) {
                LogUtil.e(th2.getMessage(), th2);
            }
        }
        this.e = this.d.getResponseCode();
        if (this.requestInterceptListener != null) {
            this.requestInterceptListener.afterRequest(this);
        }
        if (this.e == 204 || this.e == 205) {
            throw new HttpException(this.e, getResponseMessage());
        } else if (this.e < IjkMediaCodecInfo.RANK_SECURE) {
            this.b = true;
        } else {
            HttpException httpException = new HttpException(this.e, getResponseMessage());
            try {
                httpException.setResult(IOUtil.readStr(getInputStream(), this.params.getCharset()));
            } catch (Throwable unused) {
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(httpException.toString());
            stringBuilder.append(", url: ");
            stringBuilder.append(this.queryUrl);
            LogUtil.e(stringBuilder.toString());
            throw httpException;
        }
    }

    public boolean isLoading() {
        return this.b;
    }

    public String getCacheKey() {
        if (this.a == null) {
            this.a = this.params.getCacheKey();
            if (TextUtils.isEmpty(this.a)) {
                this.a = this.params.toString();
            }
        }
        return this.a;
    }

    public Object loadResult() throws Throwable {
        this.b = true;
        return super.loadResult();
    }

    public Object loadResultFromCache() throws Throwable {
        this.b = true;
        DiskCacheEntity diskCacheEntity = LruDiskCache.getDiskCache(this.params.getCacheDirName()).setMaxSize(this.params.getCacheSize()).get(getCacheKey());
        if (diskCacheEntity == null) {
            return null;
        }
        if (HttpMethod.permitsCache(this.params.getMethod())) {
            Date lastModify = diskCacheEntity.getLastModify();
            if (lastModify.getTime() > 0) {
                this.params.setHeader("If-Modified-Since", a(lastModify));
            }
            String etag = diskCacheEntity.getEtag();
            if (!TextUtils.isEmpty(etag)) {
                this.params.setHeader("If-None-Match", etag);
            }
        }
        return this.loader.loadFromCache(diskCacheEntity);
    }

    public void clearCacheHeader() {
        this.params.setHeader("If-Modified-Since", null);
        this.params.setHeader("If-None-Match", null);
    }

    public InputStream getInputStream() throws IOException {
        if (this.d != null && this.c == null) {
            this.c = this.d.getResponseCode() >= 400 ? this.d.getErrorStream() : this.d.getInputStream();
        }
        return this.c;
    }

    public void close() throws IOException {
        if (this.c != null) {
            IOUtil.closeQuietly(this.c);
            this.c = null;
        }
        if (this.d != null) {
            this.d.disconnect();
        }
    }

    public long getContentLength() {
        long j = 0;
        if (this.d == null) {
            return (long) getInputStream().available();
        }
        try {
            j = (long) this.d.getContentLength();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        if (j >= 1) {
            return j;
        }
        try {
            return (long) getInputStream().available();
        } catch (Throwable unused) {
            return j;
        }
    }

    public int getResponseCode() throws IOException {
        if (this.d != null) {
            return this.e;
        }
        return getInputStream() != null ? 200 : 404;
    }

    public String getResponseMessage() throws IOException {
        return this.d != null ? URLDecoder.decode(this.d.getResponseMessage(), this.params.getCharset()) : null;
    }

    public long getExpiration() {
        long j = -1;
        if (this.d == null) {
            return -1;
        }
        String headerField = this.d.getHeaderField("Cache-Control");
        if (!TextUtils.isEmpty(headerField)) {
            StringTokenizer stringTokenizer = new StringTokenizer(headerField, ",");
            while (stringTokenizer.hasMoreTokens()) {
                headerField = stringTokenizer.nextToken().trim().toLowerCase();
                if (headerField.startsWith("max-age")) {
                    int indexOf = headerField.indexOf(61);
                    if (indexOf > 0) {
                        try {
                            long parseLong = Long.parseLong(headerField.substring(indexOf + 1).trim());
                            if (parseLong > 0) {
                                j = System.currentTimeMillis() + (parseLong * 1000);
                            }
                        } catch (Throwable th) {
                            LogUtil.e(th.getMessage(), th);
                        }
                    }
                }
            }
        }
        if (j <= 0) {
            j = this.d.getExpiration();
        }
        long currentTimeMillis = (j > 0 || this.params.getCacheMaxAge() <= 0) ? j : System.currentTimeMillis() + this.params.getCacheMaxAge();
        if (currentTimeMillis <= 0) {
            currentTimeMillis = Long.MAX_VALUE;
        }
        return currentTimeMillis;
    }

    public long getLastModified() {
        return getHeaderFieldDate("Last-Modified", System.currentTimeMillis());
    }

    public String getETag() {
        if (this.d == null) {
            return null;
        }
        return this.d.getHeaderField("ETag");
    }

    public String getResponseHeader(String str) {
        if (this.d == null) {
            return null;
        }
        return this.d.getHeaderField(str);
    }

    public Map<String, List<String>> getResponseHeaders() {
        if (this.d == null) {
            return null;
        }
        return this.d.getHeaderFields();
    }

    public long getHeaderFieldDate(String str, long j) {
        if (this.d == null) {
            return j;
        }
        return this.d.getHeaderFieldDate(str, j);
    }

    private static String a(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM y HH:mm:ss 'GMT'", Locale.US);
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        simpleDateFormat.setTimeZone(timeZone);
        new GregorianCalendar(timeZone).setTimeInMillis(date.getTime());
        return simpleDateFormat.format(date);
    }
}
