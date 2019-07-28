package defpackage;

import com.one.tomato.mvp.base.okhttp.download.a;
import com.one.tomato.mvp.base.okhttp.download.c;
import com.one.tomato.thirdpart.m3u8.download.entity.M3U8;
import com.one.tomato.thirdpart.m3u8.download.entity.M3U8Ts;
import com.one.tomato.utils.o;
import io.reactivex.disposables.b;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.n;
import io.reactivex.r;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: M3U8DownloadTask */
/* renamed from: pi */
public class pi {
    private pk a;
    private volatile int b = 0;
    private volatile int c = 0;
    private volatile long d;
    private volatile boolean e = true;
    private boolean f = false;
    private List<String> g = new ArrayList();

    /* compiled from: M3U8DownloadTask */
    /* renamed from: pi$1 */
    class 1 implements r<M3U8> {
        public void onComplete() {
        }

        public void onError(Throwable th) {
        }

        public void onSubscribe(b bVar) {
        }

        1() {
        }

        /* renamed from: a */
        public void onNext(M3U8 m3u8) {
            pi.this.a(m3u8);
        }
    }

    public void a(String str, pk pkVar) {
        this.a = pkVar;
        if (!this.f) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(pd.a().m());
            stringBuilder.append(str);
            b(stringBuilder.toString());
        }
    }

    private void b(final String str) {
        try {
            k.create(new n<M3U8>() {
                public void subscribe(m<M3U8> mVar) throws Exception {
                    mVar.onNext(pl.a(str));
                    mVar.onComplete();
                }
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new 1());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(M3U8 m3u8) {
        a("新任务下载前，停止旧的任务");
        this.c = m3u8.getTsList().size();
        this.b = 1;
        this.d = 0;
        this.f = true;
        this.e = true;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("单个任务正式开始下载，ts文件一共有");
        stringBuilder.append(this.c);
        stringBuilder.append("个");
        o.a("Download", stringBuilder.toString());
        if (!b(m3u8)) {
            c(m3u8);
        }
    }

    private boolean b(M3U8 m3u8) {
        List tsList = m3u8.getTsList();
        for (int i = 0; i < tsList.size(); i++) {
            M3U8Ts m3U8Ts = (M3U8Ts) tsList.get(i);
            File file = new File(m3u8.getDirFilePath(), m3U8Ts.getUrl());
            if (file.exists()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(m3U8Ts.getUrl());
                stringBuilder.append("文件已存在");
                o.b("Download", stringBuilder.toString());
                long length = file.length();
                m3U8Ts.setFileSize(length);
                this.d += length;
                this.a.b(m3u8, this.d, this.c, this.b);
                this.b++;
            }
        }
        if (this.b != this.c + 1) {
            return false;
        }
        this.f = false;
        this.a.a(m3u8);
        return true;
    }

    private void c(M3U8 m3u8) {
        List tsList = m3u8.getTsList();
        if (this.b < this.c + 1) {
            a((M3U8Ts) tsList.get(this.b - 1), m3u8);
        }
    }

    private void a(M3U8Ts m3U8Ts, M3U8 m3u8) {
        String url = m3U8Ts.getUrl();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append(".download");
        String stringBuilder2 = stringBuilder.toString();
        final File file = new File(m3u8.getDirFilePath(), url);
        final File file2 = new File(m3u8.getDirFilePath(), stringBuilder2);
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("单个ts信息如下：tsName = ");
        stringBuilder3.append(url);
        stringBuilder3.append(",文件夹地址：");
        stringBuilder3.append(m3u8.getDirFilePath());
        o.a("Download", stringBuilder3.toString());
        this.g.clear();
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(m3u8.getBaseUrl());
        stringBuilder4.append(File.separator);
        stringBuilder4.append(m3U8Ts.getUrl());
        url = stringBuilder4.toString();
        this.g.add(url);
        final M3U8 m3u82 = m3u8;
        final M3U8Ts m3U8Ts2 = m3U8Ts;
        final String str = url;
        a.a().b(url, new c(m3u8.getDirFilePath(), stringBuilder2) {
            public void a() {
                super.a();
                if (pi.this.e) {
                    pi.this.e = false;
                    pi.this.a.a(m3u82, pi.this.d, pi.this.c, pi.this.b);
                }
            }

            public void a(long j, long j2) {
                pi.this.d = pi.this.d + j;
                pi.this.a.b(m3u82, pi.this.d, pi.this.c, pi.this.b);
            }

            public void a(Object obj) {
                file2.renameTo(file);
                pi.this.a(file, m3U8Ts2, m3u82);
                if (pi.this.g.contains(str)) {
                    pi.this.g.remove(str);
                }
            }

            public void a(Throwable th) {
                pi.this.a("文件下载异常");
                pi.this.a.a(m3u82, th);
            }
        });
    }

    private void a(File file, M3U8Ts m3U8Ts, M3U8 m3u8) {
        m3U8Ts.setFileSize(file.length());
        if (this.b == this.c) {
            this.f = false;
            this.a.a(m3u8);
            return;
        }
        this.a.b(m3u8, this.d, this.c, this.b);
        this.b++;
        c(m3u8);
    }

    public boolean a() {
        return this.f;
    }

    public void a(String str) {
        if (!this.g.isEmpty()) {
            for (String a : this.g) {
                a.a().a(a);
            }
            this.g.clear();
        }
        this.c = 0;
        this.f = false;
        this.e = false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stop：");
        stringBuilder.append(str);
        o.b("Download", stringBuilder.toString());
    }
}
