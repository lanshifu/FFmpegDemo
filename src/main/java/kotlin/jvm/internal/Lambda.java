package kotlin.jvm.internal;

import java.io.Serializable;

/* compiled from: Lambda.kt */
public abstract class Lambda<R> implements Serializable, e<R> {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    public int getArity() {
        return this.arity;
    }

    public String toString() {
        Object a = g.a(this);
        f.a(a, "Reflection.renderLambdaToString(this)");
        return a;
    }
}
