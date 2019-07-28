package top.zibin.luban;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: Luban */
public class d implements Callback {
    private String a;
    private boolean b;
    private int c;
    private f d;
    private e e;
    private a f;
    private List<c> g;
    private Handler h;

    /* compiled from: Luban */
    public static class a {
        private Context a;
        private String b;
        private int c = 100;
        private f d;
        private e e;
        private a f;
        private List<c> g;

        a(Context context) {
            this.a = context;
            this.g = new ArrayList();
        }

        private d b() {
            return new d(this, null);
        }

        public a a(final File file) {
            this.g.add(new c() {
                public InputStream a() throws IOException {
                    return new FileInputStream(file);
                }

                public String b() {
                    return file.getAbsolutePath();
                }
            });
            return this;
        }

        public a a(e eVar) {
            this.e = eVar;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a a(int i) {
            this.c = i;
            return this;
        }

        public void a() {
            b().c(this.a);
        }
    }

    /* synthetic */ d(a aVar, AnonymousClass1 anonymousClass1) {
        this(aVar);
    }

    private d(a aVar) {
        this.a = aVar.b;
        this.d = aVar.d;
        this.g = aVar.g;
        this.e = aVar.e;
        this.c = aVar.c;
        this.f = aVar.f;
        this.h = new Handler(Looper.getMainLooper(), this);
    }

    public static a a(Context context) {
        return new a(context);
    }

    private File a(Context context, String str) {
        if (TextUtils.isEmpty(this.a)) {
            this.a = b(context).getAbsolutePath();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("/");
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append((int) (Math.random() * 1000.0d));
        if (TextUtils.isEmpty(str)) {
            str = ".jpg";
        }
        stringBuilder.append(str);
        return new File(stringBuilder.toString());
    }

    private File b(Context context, String str) {
        if (TextUtils.isEmpty(this.a)) {
            this.a = b(context).getAbsolutePath();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append("/");
        stringBuilder.append(str);
        return new File(stringBuilder.toString());
    }

    private File b(Context context) {
        return c(context, "luban_disk_cache");
    }

    private static File c(Context context, String str) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            File file = new File(externalCacheDir, str);
            if (file.mkdirs() || (file.exists() && file.isDirectory())) {
                return file;
            }
            return null;
        }
        if (Log.isLoggable("Luban", 6)) {
            Log.e("Luban", "default disk cache dir is null");
        }
        return null;
    }

    private void c(final Context context) {
        if (this.g == null || (this.g.size() == 0 && this.e != null)) {
            this.e.onError(new NullPointerException("image file cannot be null"));
        }
        Iterator it = this.g.iterator();
        while (it.hasNext()) {
            final c cVar = (c) it.next();
            AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {
                public void run() {
                    try {
                        d.this.h.sendMessage(d.this.h.obtainMessage(1));
                        d.this.h.sendMessage(d.this.h.obtainMessage(0, d.this.a(context, cVar)));
                    } catch (IOException e) {
                        d.this.h.sendMessage(d.this.h.obtainMessage(2, e));
                    }
                }
            });
            it.remove();
        }
    }

    private File a(Context context, c cVar) throws IOException {
        File a = a(context, Checker.SINGLE.extSuffix(cVar));
        if (this.d != null) {
            a = b(context, this.d.a(cVar.b()));
        }
        if (this.f != null) {
            if (this.f.a(cVar.b()) && Checker.SINGLE.needCompress(this.c, cVar.b())) {
                return new b(cVar, a, this.b).a();
            }
            return new File(cVar.b());
        } else if (Checker.SINGLE.needCompress(this.c, cVar.b())) {
            return new b(cVar, a, this.b).a();
        } else {
            return new File(cVar.b());
        }
    }

    public boolean handleMessage(Message message) {
        if (this.e == null) {
            return false;
        }
        switch (message.what) {
            case 0:
                this.e.onSuccess((File) message.obj);
                break;
            case 1:
                this.e.onStart();
                break;
            case 2:
                this.e.onError((Throwable) message.obj);
                break;
        }
        return false;
    }
}
