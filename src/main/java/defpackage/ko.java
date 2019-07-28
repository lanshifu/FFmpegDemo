package defpackage;

import android.support.annotation.NonNull;
import android.text.Layout.Alignment;
import com.google.android.exoplayer2.text.b;

/* compiled from: Cea708Cue */
/* renamed from: ko */
final class ko extends b implements Comparable<ko> {
    public final int o;

    public ko(CharSequence charSequence, Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4, int i5) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3, z, i4);
        this.o = i5;
    }

    /* renamed from: a */
    public int compareTo(@NonNull ko koVar) {
        if (koVar.o < this.o) {
            return -1;
        }
        return koVar.o > this.o ? 1 : 0;
    }
}
