package defpackage;

/* compiled from: LineQueue */
/* renamed from: te */
public class te {
    private td a;
    private td b;
    private td c;

    public te(td tdVar) {
        this.a = tdVar;
        this.b = tdVar;
        this.c = tdVar;
        while (this.c.i() != null) {
            this.c = this.c.i();
        }
    }

    private te(te teVar, td tdVar) {
        this.a = teVar.a;
        this.c = teVar.c;
        this.b = tdVar;
    }

    public td a() {
        return this.b.i();
    }

    public td b() {
        return this.b.j();
    }

    public td c() {
        return this.b;
    }

    public boolean d() {
        if (this.b.i() == null) {
            return false;
        }
        this.b = this.b.i();
        return true;
    }

    public void a(td tdVar) {
        this.c.b(tdVar);
        this.c = tdVar;
    }

    public td e() {
        td j;
        if (this.b == this.c) {
            j = this.c.j();
        } else {
            j = this.b.i();
            if (this.b == this.a) {
                this.a = j;
            }
        }
        this.b.m();
        td tdVar = this.b;
        this.b = j;
        return tdVar;
    }

    public void f() {
        this.b.n();
    }

    public void g() {
        if (this.a == this.b.j()) {
            this.a = this.b;
        }
        this.b.o();
    }

    public te h() {
        return new te(this, this.b);
    }

    public void i() {
        this.b = this.a;
    }

    public boolean j() {
        return this.b == null || this.a == null || this.c == null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (td tdVar = this.a; tdVar != null; tdVar = tdVar.i()) {
            stringBuilder.append(tdVar.toString());
            stringBuilder.append(",");
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("{");
        stringBuilder2.append(stringBuilder.toString());
        stringBuilder2.append("}");
        return stringBuilder2.toString();
    }
}
