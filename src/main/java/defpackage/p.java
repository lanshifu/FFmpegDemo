package defpackage;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.graphics.Path.Op;
import android.os.Build.VERSION;
import com.airbnb.lottie.model.content.MergePaths;
import com.airbnb.lottie.model.content.MergePaths.MergePathsMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@TargetApi(19)
/* compiled from: MergePathsContent */
/* renamed from: p */
public class p implements n, q {
    private final Path a = new Path();
    private final Path b = new Path();
    private final Path c = new Path();
    private final String d;
    private final List<q> e = new ArrayList();
    private final MergePaths f;

    /* compiled from: MergePathsContent */
    /* renamed from: p$1 */
    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a = new int[MergePathsMode.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Missing block: B:13:?, code skipped:
            return;
     */
        static {
            /*
            r0 = com.airbnb.lottie.model.content.MergePaths.MergePathsMode.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.airbnb.lottie.model.content.MergePaths.MergePathsMode.Merge;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.airbnb.lottie.model.content.MergePaths.MergePathsMode.Add;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = com.airbnb.lottie.model.content.MergePaths.MergePathsMode.Subtract;	 Catch:{ NoSuchFieldError -> 0x002a }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r2 = 3;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = com.airbnb.lottie.model.content.MergePaths.MergePathsMode.Intersect;	 Catch:{ NoSuchFieldError -> 0x0035 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0035 }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0035 }
        L_0x0035:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = com.airbnb.lottie.model.content.MergePaths.MergePathsMode.ExcludeIntersections;	 Catch:{ NoSuchFieldError -> 0x0040 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0040 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0040 }
        L_0x0040:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.p$1.<clinit>():void");
        }
    }

    public p(MergePaths mergePaths) {
        if (VERSION.SDK_INT >= 19) {
            this.d = mergePaths.a();
            this.f = mergePaths;
            return;
        }
        throw new IllegalStateException("Merge paths are not supported pre-KitKat.");
    }

    public void a(ListIterator<g> listIterator) {
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        while (listIterator.hasPrevious()) {
            g gVar = (g) listIterator.previous();
            if (gVar instanceof q) {
                this.e.add((q) gVar);
                listIterator.remove();
            }
        }
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < this.e.size(); i++) {
            ((q) this.e.get(i)).a(list, list2);
        }
    }

    public Path e() {
        this.c.reset();
        switch (1.a[this.f.b().ordinal()]) {
            case 1:
                a();
                break;
            case 2:
                a(Op.UNION);
                break;
            case 3:
                a(Op.REVERSE_DIFFERENCE);
                break;
            case 4:
                a(Op.INTERSECT);
                break;
            case 5:
                a(Op.XOR);
                break;
        }
        return this.c;
    }

    public String b() {
        return this.d;
    }

    private void a() {
        for (int i = 0; i < this.e.size(); i++) {
            this.c.addPath(((q) this.e.get(i)).e());
        }
    }

    @TargetApi(19)
    private void a(Op op) {
        this.b.reset();
        this.a.reset();
        for (int size = this.e.size() - 1; size >= 1; size--) {
            q qVar = (q) this.e.get(size);
            if (qVar instanceof h) {
                h hVar = (h) qVar;
                List c = hVar.c();
                for (int size2 = c.size() - 1; size2 >= 0; size2--) {
                    Path e = ((q) c.get(size2)).e();
                    e.transform(hVar.d());
                    this.b.addPath(e);
                }
            } else {
                this.b.addPath(qVar.e());
            }
        }
        int i = 0;
        q qVar2 = (q) this.e.get(0);
        if (qVar2 instanceof h) {
            h hVar2 = (h) qVar2;
            List c2 = hVar2.c();
            while (i < c2.size()) {
                Path e2 = ((q) c2.get(i)).e();
                e2.transform(hVar2.d());
                this.a.addPath(e2);
                i++;
            }
        } else {
            this.a.set(qVar2.e());
        }
        this.c.op(this.a, this.b, op);
    }
}
