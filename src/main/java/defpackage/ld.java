package defpackage;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.util.a;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

/* compiled from: TtmlNode */
/* renamed from: ld */
final class ld {
    public final String a;
    public final String b;
    public final boolean c;
    public final long d;
    public final long e;
    public final lg f;
    public final String g;
    private final String[] h;
    private final HashMap<String, Integer> i;
    private final HashMap<String, Integer> j;
    private List<ld> k;

    public static ld a(String str) {
        return new ld(null, lf.a(str), -9223372036854775807L, -9223372036854775807L, null, null, "");
    }

    public static ld a(String str, long j, long j2, lg lgVar, String[] strArr, String str2) {
        return new ld(str, null, j, j2, lgVar, strArr, str2);
    }

    private ld(String str, String str2, long j, long j2, lg lgVar, String[] strArr, String str3) {
        this.a = str;
        this.b = str2;
        this.f = lgVar;
        this.h = strArr;
        this.c = str2 != null;
        this.d = j;
        this.e = j2;
        this.g = (String) a.a(str3);
        this.i = new HashMap();
        this.j = new HashMap();
    }

    public boolean a(long j) {
        return (this.d == -9223372036854775807L && this.e == -9223372036854775807L) || ((this.d <= j && this.e == -9223372036854775807L) || ((this.d == -9223372036854775807L && j < this.e) || (this.d <= j && j < this.e)));
    }

    public void a(ld ldVar) {
        if (this.k == null) {
            this.k = new ArrayList();
        }
        this.k.add(ldVar);
    }

    public ld a(int i) {
        if (this.k != null) {
            return (ld) this.k.get(i);
        }
        throw new IndexOutOfBoundsException();
    }

    public int a() {
        return this.k == null ? 0 : this.k.size();
    }

    public long[] b() {
        TreeSet treeSet = new TreeSet();
        int i = 0;
        a(treeSet, false);
        long[] jArr = new long[treeSet.size()];
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            int i2 = i + 1;
            jArr[i] = ((Long) it.next()).longValue();
            i = i2;
        }
        return jArr;
    }

    private void a(TreeSet<Long> treeSet, boolean z) {
        boolean equals = "p".equals(this.a);
        if (z || equals) {
            if (this.d != -9223372036854775807L) {
                treeSet.add(Long.valueOf(this.d));
            }
            if (this.e != -9223372036854775807L) {
                treeSet.add(Long.valueOf(this.e));
            }
        }
        if (this.k != null) {
            for (int i = 0; i < this.k.size(); i++) {
                ld ldVar = (ld) this.k.get(i);
                boolean z2 = z || equals;
                ldVar.a((TreeSet) treeSet, z2);
            }
        }
    }

    public List<b> a(long j, Map<String, lg> map, Map<String, le> map2) {
        TreeMap treeMap = new TreeMap();
        a(j, false, this.g, (Map) treeMap);
        b(j, map, treeMap);
        ArrayList arrayList = new ArrayList();
        for (Entry entry : treeMap.entrySet()) {
            le leVar = (le) map2.get(entry.getKey());
            arrayList.add(new b(a((SpannableStringBuilder) entry.getValue()), null, leVar.c, leVar.d, leVar.e, leVar.b, CheckView.UNCHECKED, leVar.f, leVar.g, leVar.h));
        }
        return arrayList;
    }

    private void a(long j, boolean z, String str, Map<String, SpannableStringBuilder> map) {
        this.i.clear();
        this.j.clear();
        if (!"metadata".equals(this.a)) {
            if (!"".equals(this.g)) {
                str = this.g;
            }
            if (this.c && z) {
                ld.a(str, (Map) map).append(this.b);
            } else if ("br".equals(this.a) && z) {
                ld.a(str, (Map) map).append(10);
            } else if (a(j)) {
                for (Entry entry : map.entrySet()) {
                    this.i.put(entry.getKey(), Integer.valueOf(((SpannableStringBuilder) entry.getValue()).length()));
                }
                boolean equals = "p".equals(this.a);
                for (int i = 0; i < a(); i++) {
                    ld a = a(i);
                    boolean z2 = z || equals;
                    a.a(j, z2, str, (Map) map);
                }
                if (equals) {
                    lf.a(ld.a(str, (Map) map));
                }
                for (Entry entry2 : map.entrySet()) {
                    this.j.put(entry2.getKey(), Integer.valueOf(((SpannableStringBuilder) entry2.getValue()).length()));
                }
            }
        }
    }

    private static SpannableStringBuilder a(String str, Map<String, SpannableStringBuilder> map) {
        if (!map.containsKey(str)) {
            map.put(str, new SpannableStringBuilder());
        }
        return (SpannableStringBuilder) map.get(str);
    }

    private void b(long j, Map<String, lg> map, Map<String, SpannableStringBuilder> map2) {
        if (a(j)) {
            int i;
            Iterator it = this.j.entrySet().iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                Entry entry = (Entry) it.next();
                String str = (String) entry.getKey();
                if (this.i.containsKey(str)) {
                    i = ((Integer) this.i.get(str)).intValue();
                }
                int intValue = ((Integer) entry.getValue()).intValue();
                if (i != intValue) {
                    a((Map) map, (SpannableStringBuilder) map2.get(str), i, intValue);
                }
            }
            while (i < a()) {
                a(i).b(j, map, map2);
                i++;
            }
        }
    }

    private void a(Map<String, lg> map, SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        lg a = lf.a(this.f, this.h, map);
        if (a != null) {
            lf.a(spannableStringBuilder, i, i2, a);
        }
    }

    private SpannableStringBuilder a(SpannableStringBuilder spannableStringBuilder) {
        int i;
        int i2;
        int i3 = 0;
        int length = spannableStringBuilder.length();
        for (i = 0; i < length; i++) {
            if (spannableStringBuilder.charAt(i) == ' ') {
                i2 = i + 1;
                int i4 = i2;
                while (i4 < spannableStringBuilder.length() && spannableStringBuilder.charAt(i4) == ' ') {
                    i4++;
                }
                i4 -= i2;
                if (i4 > 0) {
                    spannableStringBuilder.delete(i, i + i4);
                    length -= i4;
                }
            }
        }
        if (length > 0 && spannableStringBuilder.charAt(0) == ' ') {
            spannableStringBuilder.delete(0, 1);
            length--;
        }
        i = 0;
        while (true) {
            i2 = length - 1;
            if (i >= i2) {
                break;
            }
            if (spannableStringBuilder.charAt(i) == 10) {
                i2 = i + 1;
                if (spannableStringBuilder.charAt(i2) == ' ') {
                    spannableStringBuilder.delete(i2, i + 2);
                    length--;
                }
            }
            i++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i2) == ' ') {
            spannableStringBuilder.delete(i2, length);
            length--;
        }
        while (true) {
            i = length - 1;
            if (i3 >= i) {
                break;
            }
            if (spannableStringBuilder.charAt(i3) == ' ') {
                i = i3 + 1;
                if (spannableStringBuilder.charAt(i) == 10) {
                    spannableStringBuilder.delete(i3, i);
                    length--;
                }
            }
            i3++;
        }
        if (length > 0 && spannableStringBuilder.charAt(i) == 10) {
            spannableStringBuilder.delete(i, length);
        }
        return spannableStringBuilder;
    }
}
