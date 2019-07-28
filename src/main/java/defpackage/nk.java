package defpackage;

/* compiled from: IntEvaluator */
/* renamed from: nk */
public class nk implements no<Integer> {
    public Integer a(float f, Integer num, Integer num2) {
        int intValue = num.intValue();
        return Integer.valueOf((int) (((float) intValue) + (f * ((float) (num2.intValue() - intValue)))));
    }
}
