package defpackage;

import com.one.tomato.thirdpart.m3u8.download.entity.M3U8Task;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DownloadQueue */
/* renamed from: pg */
public class pg {
    private List<M3U8Task> a = new ArrayList();

    public void a(M3U8Task m3U8Task) {
        this.a.add(m3U8Task);
    }

    public M3U8Task a() {
        if (this.a.size() >= 2) {
            this.a.remove(0);
            return (M3U8Task) this.a.get(0);
        }
        if (this.a.size() == 1) {
            this.a.remove(0);
        }
        return null;
    }

    public M3U8Task b() {
        return this.a.size() >= 1 ? (M3U8Task) this.a.get(0) : null;
    }

    public boolean b(M3U8Task m3U8Task) {
        return c(m3U8Task) ? this.a.remove(m3U8Task) : false;
    }

    public boolean c(M3U8Task m3U8Task) {
        return this.a.contains(m3U8Task);
    }

    public M3U8Task a(String str) {
        M3U8Task m3U8Task = new M3U8Task(str);
        for (int i = 0; i < this.a.size(); i++) {
            if (((M3U8Task) this.a.get(i)).equals(m3U8Task)) {
                return (M3U8Task) this.a.get(i);
            }
        }
        return null;
    }

    public int c() {
        return this.a.size();
    }

    public boolean d() {
        return c() == 0;
    }

    public boolean d(M3U8Task m3U8Task) {
        return m3U8Task.equals(b());
    }

    public void e() {
        this.a.clear();
    }
}
