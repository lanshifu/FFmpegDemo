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
import java.util.List;

/* compiled from: OptionsPickerView */
/* renamed from: du */
public class du<T> extends dt implements OnClickListener {
    private dw e;

    public du(di diVar) {
        super(diVar.Q);
        this.b = diVar;
        a(diVar.Q);
    }

    private void a(Context context) {
        h();
        a();
        b();
        c();
        if (this.b.f == null) {
            LayoutInflater.from(context).inflate(this.b.N, this.a);
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
        LinearLayout linearLayout = (LinearLayout) a(R.id.optionspicker);
        linearLayout.setBackgroundColor(this.b.X);
        this.e = new dw(linearLayout, this.b.s);
        if (this.b.e != null) {
            this.e.setOptionsSelectChangeListener(this.b.e);
        }
        this.e.a(this.b.ab);
        this.e.a(this.b.g, this.b.h, this.b.i);
        this.e.a(this.b.m, this.b.n, this.b.o);
        this.e.a(this.b.p, this.b.q, this.b.r);
        this.e.a(this.b.ak);
        b(this.b.ai);
        this.e.b(this.b.ae);
        this.e.a(this.b.al);
        this.e.a(this.b.ag);
        this.e.d(this.b.ac);
        this.e.c(this.b.ad);
        this.e.a(this.b.aj);
    }

    private void m() {
        if (this.e != null) {
            this.e.b(this.b.j, this.b.k, this.b.l);
        }
    }

    public void a(List<T> list) {
        a(list, null, null);
    }

    public void a(List<T> list, List<List<T>> list2, List<List<List<T>>> list3) {
        this.e.a((List) list, (List) list2, (List) list3);
        m();
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
        if (this.b.a != null) {
            int[] a = this.e.a();
            this.b.a.a(a[0], a[1], a[2], this.d);
        }
    }

    public boolean k() {
        return this.b.ah;
    }
}
