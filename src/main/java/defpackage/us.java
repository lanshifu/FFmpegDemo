package defpackage;

import android.widget.TextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.R;
import com.zzhoujay.richtext.c;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: DefaultImageGetter */
/* renamed from: us */
public class us implements tt, tu {
    public static final String a = "us";
    private static final int b = R.id.zhou_default_image_tag_id;
    private final HashSet<uq> c = new HashSet();
    private final WeakHashMap<Object, uq> d = new WeakHashMap();
    private int e = 0;
    private tu f;

    /* compiled from: DefaultImageGetter */
    /* renamed from: us$a */
    private static class a {
        private static final ExecutorService a = Executors.newCachedThreadPool();
    }

    private void a(TextView textView) {
        synchronized (us.class) {
            HashSet hashSet = (HashSet) textView.getTag(b);
            if (hashSet != null) {
                if (hashSet == this.c) {
                    return;
                }
                HashSet hashSet2 = new HashSet(hashSet);
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    ((uq) it.next()).a();
                }
                hashSet2.clear();
                hashSet.clear();
            }
            textView.setTag(b, this.c);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x008d A:{SYNTHETIC, Splitter:B:17:0x008d} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0132  */
    public android.graphics.drawable.Drawable a(com.zzhoujay.richtext.ImageHolder r15, com.zzhoujay.richtext.c r16, android.widget.TextView r17) {
        /*
        r14 = this;
        r8 = r14;
        r9 = r15;
        r10 = r16;
        r11 = r17;
        r14.a(r11);
        r0 = defpackage.tm.a();
        r1 = r15.a();
        r2 = r0.b(r1);
        r3 = r0.a(r1);
        r4 = r10.g;
        r4 = r4.intValue();
        r5 = com.zzhoujay.richtext.CacheType.none;
        r5 = r5.intValue();
        if (r4 <= r5) goto L_0x0030;
    L_0x0027:
        if (r2 == 0) goto L_0x0030;
    L_0x0029:
        r4 = new uc;
        r4.<init>(r2);
        r12 = r4;
        goto L_0x0036;
    L_0x0030:
        r2 = new uc;
        r2.<init>(r15);
        r12 = r2;
    L_0x0036:
        r2 = r0.d(r1);
        r13 = 0;
        r4 = r10.g;	 Catch:{ Exception -> 0x011e }
        r4 = r4.intValue();	 Catch:{ Exception -> 0x011e }
        r5 = com.zzhoujay.richtext.CacheType.layout;	 Catch:{ Exception -> 0x011e }
        r5 = r5.intValue();	 Catch:{ Exception -> 0x011e }
        if (r4 <= r5) goto L_0x008a;
    L_0x0049:
        if (r3 == 0) goto L_0x0067;
    L_0x004b:
        r0 = new android.graphics.drawable.BitmapDrawable;	 Catch:{ Exception -> 0x011e }
        r1 = r17.getResources();	 Catch:{ Exception -> 0x011e }
        r0.<init>(r1, r3);	 Catch:{ Exception -> 0x011e }
        r1 = r3.getWidth();	 Catch:{ Exception -> 0x011e }
        r2 = r3.getHeight();	 Catch:{ Exception -> 0x011e }
        r3 = 0;
        r0.setBounds(r3, r3, r1, r2);	 Catch:{ Exception -> 0x011e }
        r12.a(r0);	 Catch:{ Exception -> 0x011e }
        r12.b();	 Catch:{ Exception -> 0x011e }
        return r12;
    L_0x0067:
        if (r2 == 0) goto L_0x008a;
    L_0x0069:
        r7 = r0.c(r1);	 Catch:{ Exception -> 0x011e }
        r0 = new ux;	 Catch:{ Exception -> 0x011e }
        r1 = r0;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r12;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x011e }
        r1 = defpackage.us.a();	 Catch:{ Exception -> 0x011e }
        r1 = r1.submit(r0);	 Catch:{ Exception -> 0x011e }
        r2 = new ut;	 Catch:{ Exception -> 0x011e }
        r2.<init>(r1);	 Catch:{ Exception -> 0x011e }
        r13 = r0;
        r7 = r2;
        goto L_0x008b;
    L_0x008a:
        r7 = r13;
    L_0x008b:
        if (r13 != 0) goto L_0x011b;
    L_0x008d:
        r0 = r15.d();	 Catch:{ Exception -> 0x0117 }
        r0 = defpackage.ue.a(r0);	 Catch:{ Exception -> 0x0117 }
        if (r0 == 0) goto L_0x00b3;
    L_0x0097:
        r0 = new uo;	 Catch:{ Exception -> 0x0117 }
        r1 = r0;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r12;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0117 }
        r1 = defpackage.us.a();	 Catch:{ Exception -> 0x0117 }
        r1 = r1.submit(r0);	 Catch:{ Exception -> 0x0117 }
        r2 = new ut;	 Catch:{ Exception -> 0x0117 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0117 }
        goto L_0x012d;
    L_0x00b3:
        r0 = r15.d();	 Catch:{ Exception -> 0x0117 }
        r0 = defpackage.ul.b(r0);	 Catch:{ Exception -> 0x0117 }
        if (r0 == 0) goto L_0x00d9;
    L_0x00bd:
        r0 = new un;	 Catch:{ Exception -> 0x0117 }
        r1 = r0;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r12;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0117 }
        r1 = defpackage.us.a();	 Catch:{ Exception -> 0x0117 }
        r1 = r1.submit(r0);	 Catch:{ Exception -> 0x0117 }
        r2 = new ut;	 Catch:{ Exception -> 0x0117 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0117 }
        goto L_0x012d;
    L_0x00d9:
        r0 = r15.d();	 Catch:{ Exception -> 0x0117 }
        r0 = defpackage.ul.a(r0);	 Catch:{ Exception -> 0x0117 }
        if (r0 == 0) goto L_0x00fe;
    L_0x00e3:
        r0 = new uy;	 Catch:{ Exception -> 0x0117 }
        r1 = r0;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r12;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0117 }
        r1 = defpackage.us.a();	 Catch:{ Exception -> 0x0117 }
        r1 = r1.submit(r0);	 Catch:{ Exception -> 0x0117 }
        r2 = new ut;	 Catch:{ Exception -> 0x0117 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x0117 }
        goto L_0x012d;
    L_0x00fe:
        r0 = new up;	 Catch:{ Exception -> 0x0117 }
        r1 = r0;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r12;
        r6 = r14;
        r1.<init>(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0117 }
        r1 = defpackage.uv.a();	 Catch:{ Exception -> 0x0117 }
        r2 = r10.v;	 Catch:{ Exception -> 0x0117 }
        r1 = r1.a(r15, r2, r0);	 Catch:{ Exception -> 0x0117 }
        r2 = r1;
        goto L_0x012d;
    L_0x0117:
        r0 = move-exception;
        r6 = r0;
        r0 = r13;
        goto L_0x0122;
    L_0x011b:
        r2 = r7;
        r0 = r13;
        goto L_0x012d;
    L_0x011e:
        r0 = move-exception;
        r6 = r0;
        r0 = r13;
        r7 = r0;
    L_0x0122:
        r1 = r14;
        r2 = r15;
        r3 = r16;
        r4 = r17;
        r5 = r12;
        r1.a(r2, r3, r4, r5, r6);
        r2 = r7;
    L_0x012d:
        r14.a(r11);
        if (r2 == 0) goto L_0x0135;
    L_0x0132:
        r14.a(r2, r0);
    L_0x0135:
        return r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.us.a(com.zzhoujay.richtext.ImageHolder, com.zzhoujay.richtext.c, android.widget.TextView):android.graphics.drawable.Drawable");
    }

    private void a(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, Exception exception) {
        new um<Object>(imageHolder, cVar, textView, ucVar, this, null) {
        }.a(exception);
    }

    private void a(uq uqVar, um umVar) {
        synchronized (us.class) {
            this.c.add(uqVar);
            this.d.put(umVar, uqVar);
        }
    }

    public void a(tu tuVar) {
        this.f = tuVar;
    }

    public void a(Object obj) {
        if (obj instanceof um) {
            um umVar = (um) obj;
            synchronized (us.class) {
                uq uqVar = (uq) this.d.get(umVar);
                if (uqVar != null) {
                    this.c.remove(uqVar);
                }
                this.d.remove(umVar);
                this.e++;
                if (this.f != null) {
                    this.f.a(Integer.valueOf(this.e));
                }
            }
        }
    }

    private static ExecutorService a() {
        return a.a;
    }
}
