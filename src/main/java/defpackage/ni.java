package defpackage;

/* compiled from: FloatEvaluator */
/* renamed from: ni */
public class ni implements no<Number> {
    public Float a(float f, Number number, Number number2) {
        float floatValue = number.floatValue();
        return Float.valueOf(floatValue + (f * (number2.floatValue() - floatValue)));
    }
}
