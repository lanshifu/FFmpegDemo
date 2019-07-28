package com.tomatolive.library.ui.view.sticker.core.homing;

public class IMGHoming {
    public float rotate;
    public float scale;
    public float x;
    public float y;

    public IMGHoming(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.scale = f3;
        this.rotate = f4;
    }

    public void set(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.scale = f3;
        this.rotate = f4;
    }

    public void concat(IMGHoming iMGHoming) {
        this.scale *= iMGHoming.scale;
        this.x += iMGHoming.x;
        this.y += iMGHoming.y;
    }

    public void rConcat(IMGHoming iMGHoming) {
        this.scale *= iMGHoming.scale;
        this.x -= iMGHoming.x;
        this.y -= iMGHoming.y;
    }

    public static boolean isRotate(IMGHoming iMGHoming, IMGHoming iMGHoming2) {
        return Float.compare(iMGHoming.rotate, iMGHoming2.rotate) != 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IMGHoming{x=");
        stringBuilder.append(this.x);
        stringBuilder.append(", y=");
        stringBuilder.append(this.y);
        stringBuilder.append(", scale=");
        stringBuilder.append(this.scale);
        stringBuilder.append(", rotate=");
        stringBuilder.append(this.rotate);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
