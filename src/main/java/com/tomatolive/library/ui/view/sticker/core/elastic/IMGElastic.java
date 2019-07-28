package com.tomatolive.library.ui.view.sticker.core.elastic;

import android.graphics.PointF;

public class IMGElastic {
    private float height;
    private PointF pivot = new PointF();
    private float width;

    public IMGElastic(float f, float f2) {
        this.pivot.set(f, f2);
    }

    public IMGElastic(float f, float f2, float f3, float f4) {
        this.pivot.set(f, f2);
        this.width = f3;
        this.height = f4;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float f) {
        this.width = f;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float f) {
        this.height = f;
    }

    public float getX() {
        return this.pivot.x;
    }

    public void setX(float f) {
        this.pivot.x = f;
    }

    public float getY() {
        return this.pivot.y;
    }

    public void setY(float f) {
        this.pivot.y = f;
    }

    public void setXY(float f, float f2) {
        this.pivot.set(f, f2);
    }

    public PointF getPivot() {
        return this.pivot;
    }

    public void setSize(float f, float f2) {
        this.width = f;
        this.height = f2;
    }

    public void set(float f, float f2, float f3, float f4) {
        this.pivot.set(f, f2);
        this.width = f3;
        this.height = f4;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IMGElastic{width=");
        stringBuilder.append(this.width);
        stringBuilder.append(", height=");
        stringBuilder.append(this.height);
        stringBuilder.append(", pivot=");
        stringBuilder.append(this.pivot);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
