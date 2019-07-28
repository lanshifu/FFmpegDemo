package org.xutils.view;

import android.app.Activity;
import android.view.View;

/* compiled from: ViewFinder */
final class a {
    private View a;
    private Activity b;

    public a(View view) {
        this.a = view;
    }

    public a(Activity activity) {
        this.b = activity;
    }

    public View a(int i) {
        if (this.a != null) {
            return this.a.findViewById(i);
        }
        return this.b != null ? this.b.findViewById(i) : null;
    }

    public View a(b bVar) {
        return a(bVar.a, bVar.b);
    }

    public View a(int i, int i2) {
        View a = i2 > 0 ? a(i2) : null;
        if (a != null) {
            return a.findViewById(i);
        }
        return a(i);
    }
}
