package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.internal.Internal;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;

public class CacheStrategy$Factory {
    private int ageSeconds = -1;
    final Response cacheResponse;
    private String etag;
    private Date expires;
    private Date lastModified;
    private String lastModifiedString;
    final long nowMillis;
    private long receivedResponseMillis;
    final Request request;
    private long sentRequestMillis;
    private Date servedDate;
    private String servedDateString;

    public CacheStrategy$Factory(long j, Request request, Response response) {
        this.nowMillis = j;
        this.request = request;
        this.cacheResponse = response;
        if (response != null) {
            this.sentRequestMillis = response.sentRequestAtMillis();
            this.receivedResponseMillis = response.receivedResponseAtMillis();
            Headers headers = response.headers();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String name = headers.name(i);
                String value = headers.value(i);
                if ("Date".equalsIgnoreCase(name)) {
                    this.servedDate = HttpDate.parse(value);
                    this.servedDateString = value;
                } else if ("Expires".equalsIgnoreCase(name)) {
                    this.expires = HttpDate.parse(value);
                } else if ("Last-Modified".equalsIgnoreCase(name)) {
                    this.lastModified = HttpDate.parse(value);
                    this.lastModifiedString = value;
                } else if ("ETag".equalsIgnoreCase(name)) {
                    this.etag = value;
                } else if ("Age".equalsIgnoreCase(name)) {
                    this.ageSeconds = HttpHeaders.parseSeconds(value, -1);
                }
            }
        }
    }

    public CacheStrategy get() {
        CacheStrategy candidate = getCandidate();
        return (candidate.networkRequest == null || !this.request.cacheControl().onlyIfCached()) ? candidate : new CacheStrategy(null, null);
    }

    private CacheStrategy getCandidate() {
        if (this.cacheResponse == null) {
            return new CacheStrategy(this.request, null);
        }
        if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
            return new CacheStrategy(this.request, null);
        }
        if (!CacheStrategy.isCacheable(this.cacheResponse, this.request)) {
            return new CacheStrategy(this.request, null);
        }
        CacheControl cacheControl = this.request.cacheControl();
        if (cacheControl.noCache() || hasConditions(this.request)) {
            return new CacheStrategy(this.request, null);
        }
        CacheControl cacheControl2 = this.cacheResponse.cacheControl();
        if (cacheControl2.immutable()) {
            return new CacheStrategy(null, this.cacheResponse);
        }
        String str;
        String str2;
        long cacheResponseAge = cacheResponseAge();
        long computeFreshnessLifetime = computeFreshnessLifetime();
        if (cacheControl.maxAgeSeconds() != -1) {
            computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds()));
        }
        long j = 0;
        long toMillis = cacheControl.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis((long) cacheControl.minFreshSeconds()) : 0;
        if (!(cacheControl2.mustRevalidate() || cacheControl.maxStaleSeconds() == -1)) {
            j = TimeUnit.SECONDS.toMillis((long) cacheControl.maxStaleSeconds());
        }
        if (!cacheControl2.noCache()) {
            toMillis += cacheResponseAge;
            if (toMillis < j + computeFreshnessLifetime) {
                Builder newBuilder = this.cacheResponse.newBuilder();
                if (toMillis >= computeFreshnessLifetime) {
                    newBuilder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                }
                if (cacheResponseAge > 86400000 && isFreshnessLifetimeHeuristic()) {
                    newBuilder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                }
                return new CacheStrategy(null, newBuilder.build());
            }
        }
        if (this.etag != null) {
            str = "If-None-Match";
            str2 = this.etag;
        } else if (this.lastModified != null) {
            str = "If-Modified-Since";
            str2 = this.lastModifiedString;
        } else if (this.servedDate == null) {
            return new CacheStrategy(this.request, null);
        } else {
            str = "If-Modified-Since";
            str2 = this.servedDateString;
        }
        Headers$Builder newBuilder2 = this.request.headers().newBuilder();
        Internal.instance.addLenient(newBuilder2, str, str2);
        return new CacheStrategy(this.request.newBuilder().headers(newBuilder2.build()).build(), this.cacheResponse);
    }

    private long computeFreshnessLifetime() {
        CacheControl cacheControl = this.cacheResponse.cacheControl();
        if (cacheControl.maxAgeSeconds() != -1) {
            return TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds());
        }
        long j = 0;
        long time;
        if (this.expires != null) {
            if (this.servedDate != null) {
                time = this.servedDate.getTime();
            } else {
                time = this.receivedResponseMillis;
            }
            time = this.expires.getTime() - time;
            if (time > 0) {
                j = time;
            }
            return j;
        } else if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
            return 0;
        } else {
            if (this.servedDate != null) {
                time = this.servedDate.getTime();
            } else {
                time = this.sentRequestMillis;
            }
            time -= this.lastModified.getTime();
            if (time > 0) {
                j = time / 10;
            }
            return j;
        }
    }

    private long cacheResponseAge() {
        long j = 0;
        if (this.servedDate != null) {
            j = Math.max(0, this.receivedResponseMillis - this.servedDate.getTime());
        }
        if (this.ageSeconds != -1) {
            j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.ageSeconds));
        }
        return (j + (this.receivedResponseMillis - this.sentRequestMillis)) + (this.nowMillis - this.receivedResponseMillis);
    }

    private boolean isFreshnessLifetimeHeuristic() {
        return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
    }

    private static boolean hasConditions(Request request) {
        return (request.header("If-Modified-Since") == null && request.header("If-None-Match") == null) ? false : true;
    }
}
