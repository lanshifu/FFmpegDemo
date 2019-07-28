package io.fabric.sdk.android.services.common;

/* compiled from: AdvertisingInfo */
class b {
    public final String a;
    public final boolean b;

    b(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.b != bVar.b) {
            return false;
        }
        return this.a == null ? bVar.a == null : this.a.equals(bVar.a);
    }

    public int hashCode() {
        return ((this.a != null ? this.a.hashCode() : 0) * 31) + this.b;
    }
}
