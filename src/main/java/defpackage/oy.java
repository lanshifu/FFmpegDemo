package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.one.tomato.adapter.g;
import com.one.tomato.entity.PostList;
import com.one.tomato.net.ResponseObserver;
import com.one.tomato.net.TomatoCallback;
import com.one.tomato.net.TomatoParams;
import com.one.tomato.utils.c;
import com.one.tomato.utils.j;
import com.one.tomato.widget.NoScrollGridView;
import com.tomatolive.library.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PostAdDeletePop */
/* renamed from: oy */
public class oy extends PopupWindow implements ResponseObserver {
    private final int a = 1;
    private Context b;
    private b c;
    private int d;
    private PostList e;
    private RelativeLayout f;
    private TextView g;
    private TextView h;
    private NoScrollGridView i;
    private a j;
    private List<String> k = new ArrayList();
    private List<String> l = new ArrayList();

    /* compiled from: PostAdDeletePop */
    /* renamed from: oy$1 */
    class 1 implements OnItemClickListener {
        1() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String str = (String) oy.this.k.get(i);
            if (oy.this.l.contains(str)) {
                oy.this.l.remove(str);
            } else {
                oy.this.l.add(str);
            }
            oy.this.j.notifyDataSetChanged();
            if (oy.this.l.size() > 0) {
                oy.this.g.setText(c.a(2131756187, new Object[]{Integer.valueOf(oy.this.l.size())}));
                oy.this.h.setText(c.a(2131755222));
                return;
            }
            oy.this.g.setText(2131756188);
            oy.this.h.setText(2131756183);
        }
    }

    /* compiled from: PostAdDeletePop */
    /* renamed from: oy$2 */
    class 2 implements OnClickListener {
        2() {
        }

        public void onClick(View view) {
            oy.this.b();
        }
    }

    /* compiled from: PostAdDeletePop */
    /* renamed from: oy$b */
    public interface b {
        void a(int i);

        void b(int i);

        void c(int i);
    }

    /* compiled from: PostAdDeletePop */
    /* renamed from: oy$a */
    class a extends com.one.tomato.adapter.a<String> {
        private Context f;
        private TextView g;

        public a(Context context, List<String> list, int i) {
            super(context, list, i);
            this.f = context;
        }

        public void a(g gVar, String str, int i) {
            this.g = (TextView) gVar.a(2131297980);
            this.g.setText(str);
            if (oy.this.l.size() <= 0 || !oy.this.l.contains(str)) {
                this.g.setTextColor(Color.parseColor("#FF666666"));
                this.g.setBackgroundResource(2131230994);
                return;
            }
            this.g.setTextColor(this.f.getResources().getColor(2131099707));
            this.g.setBackgroundResource(2131230961);
        }
    }

    public boolean handleRequestCancel(Message message) {
        return false;
    }

    public oy(Context context, b bVar) {
        super(context);
        this.b = context;
        this.c = bVar;
        View inflate = LayoutInflater.from(context).inflate(2131493362, null);
        setContentView(inflate);
        setWidth((int) (j.b() - 60.0f));
        setHeight((int) j.a(150.0f));
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        this.f = (RelativeLayout) inflate.findViewById(R.id.rl_root);
        this.g = (TextView) inflate.findViewById(R.id.tv_title);
        this.h = (TextView) inflate.findViewById(2131297674);
        this.i = (NoScrollGridView) inflate.findViewById(2131296615);
        this.k.add(c.a(2131756184));
        this.k.add(c.a(2131756185));
        this.k.add(c.a(2131756186));
        this.j = new a(context, this.k, 2131493242);
        this.i.setAdapter(this.j);
        this.i.setOnItemClickListener(new 1());
        this.h.setOnClickListener(new 2());
    }

    public void a(int i, PostList postList) {
        this.d = i;
        this.e = postList;
    }

    public void a(boolean z) {
        if (this.f != null) {
            if (z) {
                this.f.setBackgroundResource(2131231881);
            } else {
                this.f.setBackgroundResource(2131231880);
            }
        }
    }

    public void a() {
        this.e = null;
        this.l.clear();
        this.j.notifyDataSetChanged();
        this.g.setText(2131756189);
        this.h.setText(2131756183);
    }

    private void b() {
        if (this.e != null) {
            TomatoParams tomatoParams = new TomatoParams(pd.a().b(), "/app/advert/close");
            tomatoParams.addParameter("memberId", Integer.valueOf(com.one.tomato.utils.g.d()));
            tomatoParams.addParameter("articleId", Integer.valueOf(this.e.getId()));
            if (this.l.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < this.l.size(); i++) {
                    stringBuilder.append((String) this.l.get(i));
                    if (i < this.l.size() - 1) {
                        stringBuilder.append(";");
                    }
                }
                tomatoParams.addParameter("reason", stringBuilder.toString());
            }
            tomatoParams.post(new TomatoCallback(this, 1));
            if (this.c != null) {
                dismiss();
                this.c.a(this.d);
            }
        }
    }

    public void handleResponse(Message message) {
        if (message.what == 1 && this.c != null) {
            dismiss();
            this.c.b(this.d);
        }
    }

    public boolean handleResponseError(Message message) {
        if (message.what == 1 && this.c != null) {
            dismiss();
            this.c.c(this.d);
        }
        return false;
    }

    public boolean handleHttpRequestError(Message message) {
        if (message.what == 1 && this.c != null) {
            dismiss();
            this.c.c(this.d);
        }
        return false;
    }
}
