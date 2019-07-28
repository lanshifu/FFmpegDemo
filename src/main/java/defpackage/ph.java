package defpackage;

import android.text.TextUtils;
import com.one.tomato.entity.db.VideoDownload;
import com.one.tomato.thirdpart.m3u8.download.entity.M3U8;
import com.one.tomato.thirdpart.m3u8.download.entity.M3U8Task;
import com.one.tomato.utils.g;
import com.one.tomato.utils.k;
import com.one.tomato.utils.o;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: M3U8DownloadManager */
/* renamed from: ph */
public class ph {
    private long a;
    private M3U8Task b;
    private pg c;
    private pi d;
    private pj e;
    private pk f;

    /* compiled from: M3U8DownloadManager */
    /* renamed from: ph$1 */
    class 1 implements Runnable {
        final /* synthetic */ String a;

        public void run() {
            k.a(pl.c(this.a));
        }
    }

    /* compiled from: M3U8DownloadManager */
    /* renamed from: ph$a */
    private static class a {
        static ph a = new ph();
    }

    /* compiled from: M3U8DownloadManager */
    /* renamed from: ph$3 */
    class 3 implements pk {
        private long b;

        3() {
        }

        public void a(M3U8 m3u8, long j, int i, int i2) {
            float f = (((float) i2) * 1.0f) / ((float) i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onStartDownload：cachedSize = ");
            stringBuilder.append(j);
            stringBuilder.append(",downloadProgress = ");
            stringBuilder.append(f);
            stringBuilder.append(",totalTs = ");
            stringBuilder.append(i);
            stringBuilder.append(",curTs = ");
            stringBuilder.append(i2);
            o.a("Download", stringBuilder.toString());
            ph.this.b.setProgress(f);
            ph.this.b.setM3U8(m3u8);
            ph.this.b.setState(1);
            ph.this.c(ph.this.b);
            if (ph.this.e != null) {
                ph.this.e.b(ph.this.b);
            }
        }

        public void b(M3U8 m3u8, long j, int i, int i2) {
            if (ph.this.d.a() && j - this.b > 0) {
                if (!ph.this.a(300)) {
                    float f = (((float) i2) * 1.0f) / ((float) i);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("onProgress：cachedSize = ");
                    stringBuilder.append(j);
                    stringBuilder.append(",lastLength = ");
                    stringBuilder.append(this.b);
                    stringBuilder.append(",downloadProgress = ");
                    stringBuilder.append(f);
                    stringBuilder.append(",totalTs = ");
                    stringBuilder.append(i);
                    stringBuilder.append(",curTs = ");
                    stringBuilder.append(i2);
                    o.a("Download", stringBuilder.toString());
                    ph.this.b.setProgress(f);
                    ph.this.b.setSpeed(j - this.b);
                    ph.this.b.setM3U8(m3u8);
                    ph.this.b.setState(2);
                    ph.this.c(ph.this.b);
                    if (ph.this.e != null) {
                        ph.this.e.c(ph.this.b);
                    }
                }
                this.b = j;
            }
        }

        public void a(M3U8 m3u8) {
            o.a("Download", "onSuccess");
            ph.this.d.a("下载成功，停止任务");
            ph.this.b.setM3U8(m3u8);
            ph.this.b.setState(3);
            ph.this.c(ph.this.b);
            if (ph.this.e != null) {
                ph.this.e.d(ph.this.b);
            }
            ph.this.b();
        }

        public void a(M3U8 m3u8, Throwable th) {
            o.a("Download", "onError");
            ph.this.b.setM3U8(m3u8);
            ph.this.b.setState(4);
            ph.this.c(ph.this.b);
            if (ph.this.e != null) {
                ph.this.e.a(ph.this.b, th);
            }
            ph.this.b();
        }
    }

    /* synthetic */ ph(1 1) {
        this();
    }

    private ph() {
        this.f = new 3();
        this.c = new pg();
        this.d = new pi();
    }

    public static ph a() {
        return a.a;
    }

    public void a(pj pjVar) {
        this.e = pjVar;
    }

    private boolean a(long j) {
        boolean z = System.currentTimeMillis() - this.a <= j;
        this.a = System.currentTimeMillis();
        return z;
    }

    private void a(M3U8Task m3U8Task) {
        m3U8Task.setState(-1);
        if (this.e != null) {
            this.e.a(m3U8Task);
        }
    }

    private void b() {
        o.a("Download", "下载下一个任务");
        b(this.c.a());
    }

    public void a(ArrayList<VideoDownload> arrayList) {
        this.d.a("未完成的队列开始前，停止任务");
        this.c.e();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            VideoDownload videoDownload = (VideoDownload) it.next();
            if (videoDownload.getState() != 3) {
                M3U8Task m3U8Task = new M3U8Task(videoDownload.getUrl());
                m3U8Task.setState(0);
                this.c.a(m3U8Task);
            }
        }
        if (this.c.d()) {
            o.a("Download", "当前没有未完成的队列");
            return;
        }
        o.a("Download", "开始下载未完成的队列");
        a(this.c.b().getUrl());
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            o.b("Download", "url为空");
        } else if (pl.d(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("本地已经缓存过该视频，url为：");
            stringBuilder.append(str);
            o.b("Download", stringBuilder.toString());
            VideoDownload e = g.e(str);
            if (e != null) {
                e.setState(3);
                e.setSize(k.a(k.a(new File(pl.c(str)))));
                g.a(e);
            }
        } else {
            M3U8Task m3U8Task = new M3U8Task(str);
            if (this.c.c(m3U8Task)) {
                M3U8Task a = this.c.a(str);
                if (a.getState() != 3) {
                    b(a);
                }
            } else {
                this.c.a(m3U8Task);
                b(m3U8Task);
            }
        }
    }

    private void b(M3U8Task m3U8Task) {
        o.a("Download", "startDownloadTask");
        if (m3U8Task != null) {
            a(m3U8Task);
            if (this.c.d(m3U8Task) && m3U8Task.getState() != 5) {
                this.b = m3U8Task;
                this.d.a(m3U8Task.getUrl(), this.f);
            }
        }
    }

    public void a(String str, boolean z) {
        o.a("Download", "pause");
        if (!TextUtils.isEmpty(str)) {
            M3U8Task a = this.c.a(str);
            if (!(a == null || z)) {
                a.setState(5);
                c(a);
                if (this.e != null) {
                    this.e.e(a);
                }
                if (a.equals(this.b)) {
                    this.d.a("暂停当前任务，准备开始下载下一个任务");
                }
                this.c.b(a);
            }
            b();
            if (z) {
                g.d(str);
            }
        }
    }

    public void a(List<String> list, boolean z) {
        o.a("Download", "pause");
        if (list != null && list.size() != 0) {
            for (String str : list) {
                if (this.c.c(new M3U8Task(str))) {
                    M3U8Task a = this.c.a(str);
                    if (!(a == null || z)) {
                        a.setState(5);
                        c(a);
                        if (this.e != null) {
                            this.e.e(a);
                        }
                        if (a.equals(this.b)) {
                            this.d.a("暂停当前任务，准备开始下载下一个任务");
                        }
                        this.c.b(a);
                    }
                }
                if (z) {
                    g.d(str);
                }
            }
            b();
        }
    }

    public void a(final List<String> list) {
        o.a("Download", "cancelAndDelete");
        a((List) list, true);
        new Thread(new Runnable() {
            public void run() {
                for (String c : list) {
                    k.a(pl.c(c));
                }
            }
        }).start();
    }

    private void c(M3U8Task m3U8Task) {
        VideoDownload e = g.e(m3U8Task.getUrl());
        if (e != null) {
            e.setState(m3U8Task.getState());
            e.setSpeed(m3U8Task.getSpeed());
            e.setProgress(m3U8Task.getProgress());
            e.setSize(m3U8Task.getFormatTotalSize());
            g.a(e);
        }
    }
}
