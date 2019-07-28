package com.tomato.ucrop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.one.tomato.ucrop.R;
import defpackage.rq;
import defpackage.rr;

public class UCropView extends FrameLayout {
    private final GestureCropImageView a;
    private final OverlayView b;

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public UCropView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public UCropView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.tomato_ucrop_view, this, true);
        this.a = (GestureCropImageView) findViewById(R.id.image_view_crop);
        this.b = (OverlayView) findViewById(R.id.view_overlay);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ucrop_UCropView);
        this.b.a(obtainStyledAttributes);
        this.a.a(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        this.a.setCropBoundsChangeListener(new rq() {
            public void a(float f) {
                UCropView.this.b.setTargetAspectRatio(f);
            }
        });
        this.b.setOverlayViewChangeListener(new rr() {
            public void a(RectF rectF) {
                UCropView.this.a.setCropRect(rectF);
            }
        });
    }

    @NonNull
    public GestureCropImageView getCropImageView() {
        return this.a;
    }

    @NonNull
    public OverlayView getOverlayView() {
        return this.b;
    }
}
