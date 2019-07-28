package com.zhy.view.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.HashSet;
import java.util.Set;

public class TagFlowLayout extends FlowLayout implements a {
    private a d;
    private int e;
    private Set<Integer> f;
    private a g;
    private b h;

    public interface a {
        void a(Set<Integer> set);
    }

    public interface b {
        boolean a(View view, int i, FlowLayout flowLayout);
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = -1;
        this.f = new HashSet();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.e = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_select, -1);
        obtainStyledAttributes.recycle();
    }

    public TagFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            TagView tagView = (TagView) getChildAt(i3);
            if (tagView.getVisibility() != 8 && tagView.getTagView().getVisibility() == 8) {
                tagView.setVisibility(8);
            }
        }
        super.onMeasure(i, i2);
    }

    public void setOnSelectListener(a aVar) {
        this.g = aVar;
    }

    public void setOnTagClickListener(b bVar) {
        this.h = bVar;
    }

    public void setAdapter(a aVar) {
        this.d = aVar;
        this.d.setOnDataChangedListener(this);
        this.f.clear();
        b();
    }

    private void b() {
        removeAllViews();
        a aVar = this.d;
        HashSet b = this.d.b();
        for (int i = 0; i < aVar.a(); i++) {
            View a = aVar.a(this, i, aVar.b(i));
            final TagView tagView = new TagView(getContext());
            a.setDuplicateParentStateEnabled(true);
            if (a.getLayoutParams() != null) {
                tagView.setLayoutParams(a.getLayoutParams());
            } else {
                MarginLayoutParams marginLayoutParams = new MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(a(getContext(), 5.0f), a(getContext(), 5.0f), a(getContext(), 5.0f), a(getContext(), 5.0f));
                tagView.setLayoutParams(marginLayoutParams);
            }
            a.setLayoutParams(new LayoutParams(-1, -1));
            tagView.addView(a);
            addView(tagView);
            if (b.contains(Integer.valueOf(i))) {
                a(i, tagView);
            }
            if (this.d.a(i, aVar.b(i))) {
                a(i, tagView);
            }
            a.setClickable(false);
            tagView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    TagFlowLayout.this.a(tagView, i);
                    if (TagFlowLayout.this.h != null) {
                        TagFlowLayout.this.h.a(tagView, i, TagFlowLayout.this);
                    }
                }
            });
        }
        this.f.addAll(b);
    }

    public void setMaxSelectCount(int i) {
        if (this.f.size() > i) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("you has already select more than ");
            stringBuilder.append(i);
            stringBuilder.append(" views , so it will be clear .");
            Log.w("TagFlowLayout", stringBuilder.toString());
            this.f.clear();
        }
        this.e = i;
    }

    public Set<Integer> getSelectedList() {
        return new HashSet(this.f);
    }

    private void a(int i, TagView tagView) {
        tagView.setChecked(true);
        this.d.a(i, tagView.getTagView());
    }

    private void b(int i, TagView tagView) {
        tagView.setChecked(false);
        this.d.b(i, tagView.getTagView());
    }

    private void a(TagView tagView, int i) {
        if (tagView.isChecked()) {
            b(i, tagView);
            this.f.remove(Integer.valueOf(i));
        } else if (this.e == 1 && this.f.size() == 1) {
            Integer num = (Integer) this.f.iterator().next();
            b(num.intValue(), (TagView) getChildAt(num.intValue()));
            a(i, tagView);
            this.f.remove(num);
            this.f.add(Integer.valueOf(i));
        } else if (this.e <= 0 || this.f.size() < this.e) {
            a(i, tagView);
            this.f.add(Integer.valueOf(i));
        } else {
            return;
        }
        if (this.g != null) {
            this.g.a(new HashSet(this.f));
        }
    }

    public a getAdapter() {
        return this.d;
    }

    /* Access modifiers changed, original: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("key_default", super.onSaveInstanceState());
        String str = "";
        if (this.f.size() > 0) {
            for (Integer intValue : this.f) {
                int intValue2 = intValue.intValue();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(intValue2);
                stringBuilder.append("|");
                str = stringBuilder.toString();
            }
            str = str.substring(0, str.length() - 1);
        }
        bundle.putString("key_choose_pos", str);
        return bundle;
    }

    /* Access modifiers changed, original: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            String string = bundle.getString("key_choose_pos");
            if (!TextUtils.isEmpty(string)) {
                for (String parseInt : string.split("\\|")) {
                    int parseInt2 = Integer.parseInt(parseInt);
                    this.f.add(Integer.valueOf(parseInt2));
                    TagView tagView = (TagView) getChildAt(parseInt2);
                    if (tagView != null) {
                        a(parseInt2, tagView);
                    }
                }
            }
            super.onRestoreInstanceState(bundle.getParcelable("key_default"));
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void a() {
        this.f.clear();
        b();
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
