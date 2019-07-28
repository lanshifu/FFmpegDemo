package defpackage;

import android.content.Context;
import java.util.Calendar;

/* compiled from: TimePickerBuilder */
/* renamed from: dh */
public class dh {
    private di a = new di(2);

    public dh(Context context, dp dpVar) {
        this.a.Q = context;
        this.a.b = dpVar;
    }

    public dh a(boolean[] zArr) {
        this.a.t = zArr;
        return this;
    }

    public dh a(String str) {
        this.a.R = str;
        return this;
    }

    public dh b(String str) {
        this.a.S = str;
        return this;
    }

    public dh a(int i) {
        this.a.U = i;
        return this;
    }

    public dh b(int i) {
        this.a.V = i;
        return this;
    }

    public dh c(int i) {
        this.a.X = i;
        return this;
    }

    public dh d(int i) {
        this.a.Y = i;
        return this;
    }

    public dh e(int i) {
        this.a.W = i;
        return this;
    }

    public dh f(int i) {
        this.a.ab = i;
        return this;
    }

    public dh a(Calendar calendar) {
        this.a.u = calendar;
        return this;
    }

    public dh a(Calendar calendar, Calendar calendar2) {
        this.a.v = calendar;
        this.a.w = calendar2;
        return this;
    }

    public dh a(boolean z) {
        this.a.z = z;
        return this;
    }

    public dh b(boolean z) {
        this.a.ai = z;
        return this;
    }

    public dh a(String str, String str2, String str3, String str4, String str5, String str6) {
        this.a.B = str;
        this.a.C = str2;
        this.a.D = str3;
        this.a.E = str4;
        this.a.F = str5;
        this.a.G = str6;
        return this;
    }

    public dv a() {
        return new dv(this.a);
    }
}
