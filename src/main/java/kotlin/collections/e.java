package kotlin.collections;

/* compiled from: ArraysJVM.kt */
class e {
    public static final void a(int i, int i2) {
        if (i > i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("toIndex (");
            stringBuilder.append(i);
            stringBuilder.append(") is greater than size (");
            stringBuilder.append(i2);
            stringBuilder.append(").");
            throw new IndexOutOfBoundsException(stringBuilder.toString());
        }
    }
}
