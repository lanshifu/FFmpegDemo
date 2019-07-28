package org.xutils.image;

/* compiled from: MemCacheKey */
final class b {
    public final String a;
    public final ImageOptions b;

    public b(String str, ImageOptions imageOptions) {
        this.a = str;
        this.b = imageOptions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a.equals(bVar.a)) {
            return this.b.equals(bVar.b);
        }
        return false;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a);
        stringBuilder.append(this.b.toString());
        return stringBuilder.toString();
    }
}
