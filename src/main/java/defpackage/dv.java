package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.R;
import java.text.ParseException;
import java.util.Calendar;

/* compiled from: TimePickerView */
/* renamed from: dv */
public class dv extends dt implements OnClickListener {
    private dx e;

    /* compiled from: TimePickerView */
    /* renamed from: dv$1 */
    class 1 implements dk {
        1() {
        }

        public void a() {
            try {
                dv.this.b.d.a(dx.a.parse(dv.this.e.a()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public dv(di diVar) {
        super(diVar.Q);
        this.b = diVar;
        a(diVar.Q);
    }

    private void a(Context context) {
        h();
        a();
        b();
        if (this.b.f == null) {
            LayoutInflater.from(context).inflate(R.layout.pickerview_time, this.a);
            TextView textView = (TextView) a(R.id.tvTitle);
            RelativeLayout relativeLayout = (RelativeLayout) a(R.id.rv_topbar);
            Button button = (Button) a(R.id.btnSubmit);
            Button button2 = (Button) a(R.id.btnCancel);
            button.setTag("submit");
            button2.setTag("cancel");
            button.setOnClickListener(this);
            button2.setOnClickListener(this);
            button.setText(TextUtils.isEmpty(this.b.R) ? context.getResources().getString(R.string.pickerview_submit) : this.b.R);
            button2.setText(TextUtils.isEmpty(this.b.S) ? context.getResources().getString(R.string.pickerview_cancel) : this.b.S);
            textView.setText(TextUtils.isEmpty(this.b.T) ? "" : this.b.T);
            button.setTextColor(this.b.U);
            button2.setTextColor(this.b.V);
            textView.setTextColor(this.b.W);
            relativeLayout.setBackgroundColor(this.b.Y);
            button.setTextSize((float) this.b.Z);
            button2.setTextSize((float) this.b.Z);
            textView.setTextSize((float) this.b.aa);
        } else {
            this.b.f.a(LayoutInflater.from(context).inflate(this.b.N, this.a));
        }
        LinearLayout linearLayout = (LinearLayout) a(R.id.timepicker);
        linearLayout.setBackgroundColor(this.b.X);
        a(linearLayout);
    }

    private void a(LinearLayout linearLayout) {
        this.e = new dx(linearLayout, this.b.t, this.b.P, this.b.ab);
        if (this.b.d != null) {
            this.e.a(new 1());
        }
        this.e.a(this.b.A);
        if (!(this.b.x == 0 || this.b.y == 0 || this.b.x > this.b.y)) {
            m();
        }
        if (this.b.v == null || this.b.w == null) {
            if (this.b.v != null) {
                if (this.b.v.get(1) >= 1900) {
                    n();
                } else {
                    throw new IllegalArgumentException("The startDate can not as early as 1900");
                }
            } else if (this.b.w == null) {
                n();
            } else if (this.b.w.get(1) <= 2100) {
                n();
            } else {
                throw new IllegalArgumentException("The endDate should not be later than 2100");
            }
        } else if (this.b.v.getTimeInMillis() <= this.b.w.getTimeInMillis()) {
            n();
        } else {
            throw new IllegalArgumentException("startDate can't be later than endDate");
        }
        p();
        this.e.a(this.b.B, this.b.C, this.b.D, this.b.E, this.b.F, this.b.G);
        this.e.b(this.b.H, this.b.I, this.b.J, this.b.K, this.b.L, this.b.M);
        b(this.b.ai);
        this.e.b(this.b.z);
        this.e.c(this.b.ae);
        this.e.a(this.b.al);
        this.e.a(this.b.ag);
        this.e.e(this.b.ac);
        this.e.d(this.b.ad);
        this.e.c(this.b.aj);
    }

    public void a(Calendar calendar) {
        this.b.u = calendar;
        p();
    }

    private void m() {
        this.e.a(this.b.x);
        this.e.b(this.b.y);
    }

    private void n() {
        this.e.a(this.b.v, this.b.w);
        o();
    }

    private void o() {
        if (this.b.v == null || this.b.w == null) {
            if (this.b.v != null) {
                this.b.u = this.b.v;
            } else if (this.b.w != null) {
                this.b.u = this.b.w;
            }
        } else if (this.b.u == null || this.b.u.getTimeInMillis() < this.b.v.getTimeInMillis() || this.b.u.getTimeInMillis() > this.b.w.getTimeInMillis()) {
            this.b.u = this.b.v;
        }
    }

    private void p() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Calendar instance = Calendar.getInstance();
        if (this.b.u == null) {
            instance.setTimeInMillis(System.currentTimeMillis());
            i = instance.get(1);
            i2 = instance.get(2);
            i3 = instance.get(5);
            i4 = instance.get(11);
            i5 = instance.get(12);
            i6 = instance.get(13);
        } else {
            i = this.b.u.get(1);
            i2 = this.b.u.get(2);
            i3 = this.b.u.get(5);
            i4 = this.b.u.get(11);
            i5 = this.b.u.get(12);
            i6 = this.b.u.get(13);
        }
        this.e.a(i, i2, i3, i4, i5, i6);
    }

    public void onClick(View view) {
        String str = (String) view.getTag();
        if (str.equals("submit")) {
            l();
        } else if (str.equals("cancel") && this.b.c != null) {
            this.b.c.onClick(view);
        }
        f();
    }

    public void l() {
        if (this.b.b != null) {
            try {
                this.b.b.onTimeSelect(dx.a.parse(this.e.a()), this.d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean k() {
        return this.b.ah;
    }
}
