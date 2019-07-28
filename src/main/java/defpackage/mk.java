package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import io.reactivex.r;

/* compiled from: TextViewTextObservable */
/* renamed from: mk */
final class mk extends mg<CharSequence> {
    private final TextView a;

    /* compiled from: TextViewTextObservable */
    /* renamed from: mk$a */
    static final class a extends wb implements TextWatcher {
        private final TextView a;
        private final r<? super CharSequence> b;

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        a(TextView textView, r<? super CharSequence> rVar) {
            this.a = textView;
            this.b = rVar;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.b.onNext(charSequence);
            }
        }

        /* Access modifiers changed, original: protected */
        public void onDispose() {
            this.a.removeTextChangedListener(this);
        }
    }

    mk(TextView textView) {
        this.a = textView;
    }

    /* Access modifiers changed, original: protected */
    public void a(r<? super CharSequence> rVar) {
        a aVar = new a(this.a, rVar);
        rVar.onSubscribe(aVar);
        this.a.addTextChangedListener(aVar);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public CharSequence a() {
        return this.a.getText();
    }
}
