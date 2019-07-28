package defpackage;

/* compiled from: UpdateThread */
/* renamed from: zb */
public class zb extends Thread {
    volatile boolean b;

    public zb(String str) {
        super(str);
    }

    public void a() {
        this.b = true;
    }

    public boolean b() {
        return this.b;
    }

    public void run() {
        if (!this.b) {
        }
    }
}
