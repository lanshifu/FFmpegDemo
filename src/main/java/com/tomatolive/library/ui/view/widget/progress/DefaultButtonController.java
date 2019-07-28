package com.tomatolive.library.ui.view.widget.progress;

import android.graphics.Color;

public class DefaultButtonController implements ButtonController {
    private boolean enableGradient;
    private boolean enablePress;

    public int getPressedColor(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] - 0.1f;
        return Color.HSVToColor(fArr);
    }

    public int getLighterColor(int i) {
        r0 = new float[3];
        Color.colorToHSV(i, r0);
        r0[1] = r0[1] - 0.3f;
        r0[2] = r0[2] + 0.3f;
        return Color.HSVToColor(r0);
    }

    public int getDarkerColor(int i) {
        r0 = new float[3];
        Color.colorToHSV(i, r0);
        r0[1] = r0[1] + 0.3f;
        r0[2] = r0[2] - 0.3f;
        return Color.HSVToColor(r0);
    }

    public boolean enablePress() {
        return this.enablePress;
    }

    public boolean enableGradient() {
        return this.enableGradient;
    }

    public DefaultButtonController setEnablePress(boolean z) {
        this.enablePress = z;
        return this;
    }

    public DefaultButtonController setEnableGradient(boolean z) {
        this.enableGradient = z;
        return this;
    }
}
