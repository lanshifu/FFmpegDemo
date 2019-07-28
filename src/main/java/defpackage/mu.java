package defpackage;

import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.entity.LocalMediaFolder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ImagesObservable */
/* renamed from: mu */
public class mu {
    private static mu e;
    private List<Object> a = new ArrayList();
    private List<LocalMediaFolder> b = new ArrayList();
    private List<LocalMedia> c = new ArrayList();
    private List<LocalMedia> d = new ArrayList();

    private mu() {
    }

    public static mu a() {
        if (e == null) {
            synchronized (mu.class) {
                if (e == null) {
                    e = new mu();
                }
            }
        }
        return e;
    }

    public void a(List<LocalMedia> list) {
        this.c = list;
    }

    public List<LocalMedia> b() {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        return this.c;
    }

    public void c() {
        if (this.c != null) {
            this.c.clear();
        }
    }
}
