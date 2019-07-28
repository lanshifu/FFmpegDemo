package defpackage;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.RemoteViews;
import com.one.tomato.mvp.base.BaseApplication;
import com.one.tomato.mvp.base.okhttp.download.a;
import com.one.tomato.utils.ad;
import com.one.tomato.utils.c;
import com.one.tomato.utils.i;
import com.one.tomato.utils.k;
import com.one.tomato.utils.o;
import java.io.File;
import okhttp3.ResponseBody;

/* compiled from: AdApkDownloadUtil */
/* renamed from: pr */
public class pr {
    private static pr a;
    private boolean b = false;
    private Context c = BaseApplication.a();
    private NotificationManager d = ((NotificationManager) this.c.getSystemService("notification"));
    private Notification e;
    private RemoteViews f = new RemoteViews(this.c.getPackageName(), 2131493027);
    private int g;

    private pr() {
    }

    public static pr a() {
        if (a == null) {
            a = new pr();
        }
        return a;
    }

    public void a(String str) {
        String path = k.d().getPath();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pu.a(str));
        stringBuilder.append(".apk");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(".tmp");
        String stringBuilder4 = stringBuilder3.toString();
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(path);
        stringBuilder3.append(File.separator);
        stringBuilder3.append(stringBuilder2);
        final String stringBuilder5 = stringBuilder3.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(path);
        stringBuilder.append(File.separator);
        stringBuilder.append(stringBuilder4);
        stringBuilder2 = stringBuilder.toString();
        final File file = new File(stringBuilder5);
        final File file2 = new File(stringBuilder2);
        if (file.exists()) {
            c.a(stringBuilder5);
        } else if (this.b) {
            ad.a(2131755212);
        } else {
            this.b = true;
            stringBuilder = new StringBuilder();
            stringBuilder.append("广告下载地址：");
            stringBuilder.append(str);
            o.b(stringBuilder.toString());
            c();
            a.a().a(str, new com.one.tomato.mvp.base.okhttp.download.c<ResponseBody>(path, stringBuilder4) {
                /* renamed from: b */
                public void a(ResponseBody responseBody) {
                    pr.this.b();
                    if (file2 == null || file2.length() != responseBody.contentLength()) {
                        o.c("下载文件不完整");
                        pr.this.b();
                    } else if (file2.renameTo(file)) {
                        c.a(stringBuilder5);
                    }
                }

                public void a(long j, long j2) {
                    double d = (double) j;
                    Double.isNaN(d);
                    d *= 1.0d;
                    double d2 = (double) j2;
                    Double.isNaN(d2);
                    pr.this.a((int) ((d / d2) * 100.0d));
                }

                public void a(Throwable th) {
                    pr.this.b();
                }
            });
        }
    }

    private void c() {
        if (i.e()) {
            this.d.createNotificationChannel(new NotificationChannel("ad_download_channel_id", "ad_download_channel_name", 4));
            Builder builder = new Builder(this.c, "ad_download_channel_id");
            builder.setChannelId("ad_download_channel_id");
            builder.setSmallIcon(2131231628).setContentTitle(c.a(2131756566)).setCustomContentView(this.f).setOnlyAlertOnce(true).setOngoing(false).setAutoCancel(false).setNumber(1);
            this.e = builder.build();
            this.d.notify(3, this.e);
            return;
        }
        this.e = new Notification();
        this.e.icon = 2131231628;
        this.e.tickerText = c.a(2131756566);
        this.e.contentView = this.f;
        this.d.notify(3, this.e);
    }

    private void a(int i) {
        if (this.e == null) {
            o.b("Notification出现为空的情况");
            c();
        }
        if (((double) (i - this.g)) > 0.5d) {
            this.g = i;
            RemoteViews remoteViews = this.e.contentView;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(c.a(2131756549, new Object[]{Integer.valueOf(this.g)}));
            stringBuilder.append("%");
            remoteViews.setTextViewText(2131297968, stringBuilder.toString());
            this.e.contentView.setProgressBar(2131297185, 100, this.g, false);
            this.d.notify(3, this.e);
        }
    }

    public void b() {
        this.b = false;
        this.g = 0;
        if (this.d != null) {
            this.d.cancelAll();
        }
        this.e = null;
    }
}
