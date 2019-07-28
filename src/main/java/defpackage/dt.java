package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import com.bigkoo.pickerview.R;

/* compiled from: BasePickerView */
/* renamed from: dt */
public class dt {
    protected ViewGroup a;
    protected di b;
    protected int c = 80;
    protected View d;
    private Context e;
    private ViewGroup f;
    private ViewGroup g;
    private dl h;
    private boolean i;
    private Animation j;
    private Animation k;
    private boolean l;
    private Dialog m;
    private boolean n = true;
    private OnKeyListener o = new 4();
    private final OnTouchListener p = new 5();

    /* compiled from: BasePickerView */
    /* renamed from: dt$1 */
    class 1 implements OnClickListener {
        1() {
        }

        public void onClick(View view) {
            dt.this.f();
        }
    }

    /* compiled from: BasePickerView */
    /* renamed from: dt$2 */
    class 2 implements AnimationListener {
        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        2() {
        }

        public void onAnimationEnd(Animation animation) {
            dt.this.g();
        }
    }

    /* compiled from: BasePickerView */
    /* renamed from: dt$3 */
    class 3 implements Runnable {
        3() {
        }

        public void run() {
            dt.this.b.O.removeView(dt.this.f);
            dt.this.l = false;
            dt.this.i = false;
            if (dt.this.h != null) {
                dt.this.h.a(dt.this);
            }
        }
    }

    /* compiled from: BasePickerView */
    /* renamed from: dt$4 */
    class 4 implements OnKeyListener {
        4() {
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 0 || !dt.this.e()) {
                return false;
            }
            dt.this.f();
            return true;
        }
    }

    /* compiled from: BasePickerView */
    /* renamed from: dt$5 */
    class 5 implements OnTouchListener {
        5() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                dt.this.f();
            }
            return false;
        }
    }

    /* compiled from: BasePickerView */
    /* renamed from: dt$6 */
    class 6 implements OnDismissListener {
        6() {
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (dt.this.h != null) {
                dt.this.h.a(dt.this);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void c() {
    }

    public boolean k() {
        return false;
    }

    public dt(Context context) {
        this.e = context;
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        LayoutParams layoutParams = new LayoutParams(-1, -2, 80);
        LayoutInflater from = LayoutInflater.from(this.e);
        if (k()) {
            this.g = (ViewGroup) from.inflate(R.layout.layout_basepickerview, null, false);
            this.g.setBackgroundColor(0);
            this.a = (ViewGroup) this.g.findViewById(R.id.content_container);
            layoutParams.leftMargin = 30;
            layoutParams.rightMargin = 30;
            this.a.setLayoutParams(layoutParams);
            i();
            this.g.setOnClickListener(new 1());
        } else {
            if (this.b.O == null) {
                this.b.O = (ViewGroup) ((Activity) this.e).getWindow().getDecorView();
            }
            this.f = (ViewGroup) from.inflate(R.layout.layout_basepickerview, this.b.O, false);
            this.f.setLayoutParams(new LayoutParams(-1, -1));
            if (this.b.af != -1) {
                this.f.setBackgroundColor(this.b.af);
            }
            this.a = (ViewGroup) this.f.findViewById(R.id.content_container);
            this.a.setLayoutParams(layoutParams);
        }
        a(true);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.k = l();
        this.j = m();
    }

    public void d() {
        if (k()) {
            n();
        } else if (!e()) {
            this.l = true;
            a(this.f);
            this.f.requestFocus();
        }
    }

    private void a(View view) {
        this.b.O.addView(view);
        if (this.n) {
            this.a.startAnimation(this.k);
        }
    }

    public boolean e() {
        boolean z = false;
        if (k()) {
            return false;
        }
        if (this.f.getParent() != null || this.l) {
            z = true;
        }
        return z;
    }

    public void f() {
        if (k()) {
            o();
        } else if (!this.i) {
            if (this.n) {
                this.j.setAnimationListener(new 2());
                this.a.startAnimation(this.j);
            } else {
                g();
            }
            this.i = true;
        }
    }

    public void g() {
        this.b.O.post(new 3());
    }

    private Animation l() {
        return AnimationUtils.loadAnimation(this.e, ds.a(this.c, true));
    }

    private Animation m() {
        return AnimationUtils.loadAnimation(this.e, ds.a(this.c, false));
    }

    public void a(boolean z) {
        ViewGroup viewGroup;
        if (k()) {
            viewGroup = this.g;
        } else {
            viewGroup = this.f;
        }
        viewGroup.setFocusable(z);
        viewGroup.setFocusableInTouchMode(z);
        if (z) {
            viewGroup.setOnKeyListener(this.o);
        } else {
            viewGroup.setOnKeyListener(null);
        }
    }

    /* Access modifiers changed, original: protected */
    public dt b(boolean z) {
        if (this.f != null) {
            View findViewById = this.f.findViewById(R.id.outmost_container);
            if (z) {
                findViewById.setOnTouchListener(this.p);
            } else {
                findViewById.setOnTouchListener(null);
            }
        }
        return this;
    }

    public void h() {
        if (this.m != null) {
            this.m.setCancelable(this.b.ai);
        }
    }

    public View a(int i) {
        return this.a.findViewById(i);
    }

    public void i() {
        if (this.g != null) {
            this.m = new Dialog(this.e, R.style.custom_dialog2);
            this.m.setCancelable(this.b.ai);
            this.m.setContentView(this.g);
            Window window = this.m.getWindow();
            if (window != null) {
                window.setWindowAnimations(R.style.picker_view_scale_anim);
                window.setGravity(17);
            }
            this.m.setOnDismissListener(new 6());
        }
    }

    private void n() {
        if (this.m != null) {
            this.m.show();
        }
    }

    private void o() {
        if (this.m != null) {
            this.m.dismiss();
        }
    }

    public ViewGroup j() {
        return this.a;
    }
}
