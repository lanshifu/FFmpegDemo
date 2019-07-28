package com.zhy.view.flowlayout;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.List;

/* compiled from: TagAdapter */
public abstract class a<T> {
    private List<T> a;
    private a b;
    @Deprecated
    private HashSet<Integer> c = new HashSet();

    /* compiled from: TagAdapter */
    interface a {
        void a();
    }

    public abstract View a(FlowLayout flowLayout, int i, T t);

    public boolean a(int i, T t) {
        return false;
    }

    public a(List<T> list) {
        this.a = list;
    }

    /* Access modifiers changed, original: 0000 */
    public void setOnDataChangedListener(a aVar) {
        this.b = aVar;
    }

    /* Access modifiers changed, original: 0000 */
    @Deprecated
    public HashSet<Integer> b() {
        return this.c;
    }

    public int a() {
        return this.a == null ? 0 : this.a.size();
    }

    public void c() {
        if (this.b != null) {
            this.b.a();
        }
    }

    public T b(int i) {
        return this.a.get(i);
    }

    public void a(int i, View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onSelected ");
        stringBuilder.append(i);
        Log.d("zhy", stringBuilder.toString());
    }

    public void b(int i, View view) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unSelected ");
        stringBuilder.append(i);
        Log.d("zhy", stringBuilder.toString());
    }
}
