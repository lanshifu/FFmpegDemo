package net.lucode.hackware.magicindicator;

import android.annotation.TargetApi;
import java.util.List;

@TargetApi(11)
/* compiled from: FragmentContainerHelper */
public class a {
    public static aan a(List<aan> list, int i) {
        if (i >= 0 && i <= list.size() - 1) {
            return (aan) list.get(i);
        }
        aan aan;
        aan aan2 = new aan();
        if (i < 0) {
            aan = (aan) list.get(0);
        } else {
            i = (i - list.size()) + 1;
            aan = (aan) list.get(list.size() - 1);
        }
        aan2.a = aan.a + (aan.a() * i);
        aan2.b = aan.b;
        aan2.c = aan.c + (aan.a() * i);
        aan2.d = aan.d;
        aan2.e = aan.e + (aan.a() * i);
        aan2.f = aan.f;
        aan2.g = aan.g + (i * aan.a());
        aan2.h = aan.h;
        return aan2;
    }
}
