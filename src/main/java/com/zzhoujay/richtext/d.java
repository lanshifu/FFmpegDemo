package com.zzhoujay.richtext;

import android.support.v4.util.LruCache;
import android.text.SpannableStringBuilder;
import com.zzhoujay.richtext.spans.b;
import defpackage.uk;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.WeakHashMap;

/* compiled from: RichTextPool */
class d {
    private final LruCache<String, SoftReference<SpannableStringBuilder>> a;
    private final WeakHashMap<Object, HashSet<WeakReference<b>>> b;

    /* compiled from: RichTextPool */
    private static class a {
        private static final d a = new d();
    }

    private d() {
        this.a = new LruCache(50);
        this.b = new WeakHashMap();
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, SpannableStringBuilder spannableStringBuilder) {
        spannableStringBuilder = a(new SpannableStringBuilder(spannableStringBuilder));
        spannableStringBuilder.setSpan(new defpackage.va.a(), 0, spannableStringBuilder.length(), 33);
        this.a.put(uk.a(str), new SoftReference(spannableStringBuilder));
    }

    /* Access modifiers changed, original: 0000 */
    public SpannableStringBuilder a(String str) {
        CharSequence charSequence;
        SoftReference softReference = (SoftReference) this.a.get(uk.a(str));
        if (softReference == null) {
            charSequence = null;
        } else {
            charSequence = (SpannableStringBuilder) softReference.get();
        }
        if (charSequence != null) {
            return new SpannableStringBuilder(charSequence);
        }
        return null;
    }

    /* Access modifiers changed, original: 0000 */
    public SpannableStringBuilder a(SpannableStringBuilder spannableStringBuilder) {
        int i = 0;
        b[] bVarArr = (b[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), b.class);
        if (bVarArr == null || bVarArr.length <= 0) {
            return spannableStringBuilder;
        }
        int length = bVarArr.length;
        while (i < length) {
            Object obj = bVarArr[i];
            int spanStart = spannableStringBuilder.getSpanStart(obj);
            int spanEnd = spannableStringBuilder.getSpanEnd(obj);
            int spanFlags = spannableStringBuilder.getSpanFlags(obj);
            b a = obj.a();
            spannableStringBuilder.removeSpan(obj);
            spannableStringBuilder.setSpan(a, spanStart, spanEnd, spanFlags);
            i++;
        }
        return spannableStringBuilder;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Object obj, b bVar) {
        HashSet hashSet = (HashSet) this.b.get(obj);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.b.put(obj, hashSet);
        }
        hashSet.add(new WeakReference(bVar));
    }

    public static d a() {
        return a.a;
    }

    public void b() {
        this.a.evictAll();
    }
}
