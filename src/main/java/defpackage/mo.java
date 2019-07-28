package defpackage;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.luck.picture.lib.config.a;
import com.luck.picture.lib.entity.LocalMedia;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: Luban */
/* renamed from: mo */
public class mo implements Callback {
    private String a;
    private List<String> b;
    private List<LocalMedia> c;
    private int d;
    private mp e;
    private int f;
    private Handler g;
    private Context h;

    /* compiled from: Luban */
    /* renamed from: mo$a */
    public static class a {
        private Context a;
        private String b;
        private List<String> c;
        private List<LocalMedia> d;
        private int e = 100;
        private mp f;

        a(Context context) {
            this.a = context;
            this.c = new ArrayList();
        }

        private mo c() {
            return new mo(this, null);
        }

        public a a(List<LocalMedia> list) {
            if (list == null) {
                List list2 = new ArrayList();
            }
            this.d = list2;
            for (LocalMedia localMedia : list2) {
                this.c.add(localMedia.f() ? localMedia.d() : localMedia.b());
            }
            return this;
        }

        public a a(mp mpVar) {
            this.f = mpVar;
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        public void a() {
            c().c(this.a);
        }

        public List<File> b() throws IOException {
            return c().d(this.a);
        }
    }

    /* synthetic */ mo(a aVar, 1 1) {
        this(aVar);
    }

    private mo(a aVar) {
        this.f = -1;
        this.b = aVar.c;
        this.c = aVar.d;
        this.h = aVar.a;
        this.a = aVar.b;
        this.e = aVar.f;
        this.d = aVar.e;
        this.g = new Handler(Looper.getMainLooper(), this);
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

    @Nullable
    private File b(Context context) {
        return b(context, "luban_disk_cache");
    }

    @Nullable
    private File b(Context context, String str) {
        File file = new File(new File(my.a(context)), str);
        return (file.mkdirs() || (file.exists() && file.isDirectory())) ? file : null;
    }

    @UiThread
    private void c(final Context context) {
        if (this.b == null || (this.b.size() == 0 && this.e != null)) {
            this.e.a(new NullPointerException("image file cannot be null"));
        }
        Iterator it = this.b.iterator();
        this.f = -1;
        while (it.hasNext()) {
            final String str = (String) it.next();
            if (mm.a(str)) {
                AsyncTask.SERIAL_EXECUTOR.execute(new Runnable() {
                    public void run() {
                        try {
                            mo.this.f = mo.this.f + 1;
                            int i = 1;
                            mo.this.g.sendMessage(mo.this.g.obtainMessage(1));
                            File a = mm.a(mo.this.d, str) ? new mn(str, mo.this.a(context, mm.c(str))).a() : new File(str);
                            if (mo.this.c == null || mo.this.c.size() <= 0) {
                                mo.this.g.sendMessage(mo.this.g.obtainMessage(2, new IOException()));
                                return;
                            }
                            String str;
                            LocalMedia localMedia = (LocalMedia) mo.this.c.get(mo.this.f);
                            boolean d = a.d(a.getAbsolutePath());
                            localMedia.b(d ^ 1);
                            if (d) {
                                str = "";
                            } else {
                                str = a.getAbsolutePath();
                            }
                            localMedia.c(str);
                            if (mo.this.f != mo.this.c.size() - 1) {
                                i = 0;
                            }
                            if (i != 0) {
                                mo.this.g.sendMessage(mo.this.g.obtainMessage(3, mo.this.c));
                            }
                        } catch (IOException e) {
                            mo.this.g.sendMessage(mo.this.g.obtainMessage(2, e));
                        }
                    }
                });
            } else {
                mp mpVar = this.e;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("can not read the path : ");
                stringBuilder.append(str);
                mpVar.a(new IllegalArgumentException(stringBuilder.toString()));
            }
            it.remove();
        }
    }

    @WorkerThread
    private List<File> d(Context context) throws IOException {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (mm.a(str)) {
                arrayList.add(mm.a(this.d, str) ? new mn(str, a(context, mm.c(str))).a() : new File(str));
            }
            it.remove();
        }
        return arrayList;
    }

    public boolean handleMessage(Message message) {
        if (this.e == null) {
            return false;
        }
        switch (message.what) {
            case 1:
                this.e.a();
                break;
            case 2:
                this.e.a((Throwable) message.obj);
                break;
            case 3:
                this.e.a((List) message.obj);
                break;
        }
        return false;
    }
}
