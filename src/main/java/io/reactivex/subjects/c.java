package io.reactivex.subjects;

import io.reactivex.k;
import io.reactivex.r;

/* compiled from: Subject */
public abstract class c<T> extends k<T> implements r<T> {
    public final c<T> b() {
        if (this instanceof b) {
            return this;
        }
        return new b(this);
    }
}
