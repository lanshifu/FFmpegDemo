package com.tomatolive.library.ui.view.widget.tagview;

import android.graphics.Color;

public class ColorFactory {
    public static final String AMBER = "FFC107";
    public static final String BD_COLOR_ALPHA = "88";
    public static final String BG_COLOR_ALPHA = "33";
    public static final String BLUE = "2196F3";
    private static final String[] COLORS = new String[]{RED, LIGHTBLUE, AMBER, ORANGE, YELLOW, LIME, BLUE, INDIGO, LIGHTGREEN, GREY, DEEPPURPLE, TEAL, CYAN};
    public static final String CYAN = "00BCD4";
    public static final String DEEPPURPLE = "673AB7";
    public static final String GREY = "9E9E9E";
    public static final String INDIGO = "3F51B5";
    public static final String LIGHTBLUE = "03A9F4";
    public static final String LIGHTGREEN = "8BC34A";
    public static final String LIME = "CDDC39";
    public static final int NONE = -1;
    public static final String ORANGE = "FF9800";
    public static final int PURE_CYAN = 1;
    public static final int PURE_TEAL = 2;
    public static final int RANDOM = 0;
    public static final String RED = "F44336";
    public static final int SHARP666666 = Color.parseColor("#FF666666");
    public static final int SHARP727272 = Color.parseColor("#FF727272");
    public static final String TEAL = "009688";
    public static final String YELLOW = "FFEB3B";

    public enum PURE_COLOR {
        CYAN,
        TEAL
    }

    public static int[] onRandomBuild() {
        double random = Math.random();
        double length = (double) COLORS.length;
        Double.isNaN(length);
        int i = (int) (random * length);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#33");
        stringBuilder.append(COLORS[i]);
        int parseColor = Color.parseColor(stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("#88");
        stringBuilder2.append(COLORS[i]);
        i = Color.parseColor(stringBuilder2.toString());
        int i2 = SHARP666666;
        int i3 = SHARP727272;
        return new int[]{parseColor, i, i2, i3};
    }

    public static int[] onPureBuild(PURE_COLOR pure_color) {
        String str = pure_color == PURE_COLOR.CYAN ? CYAN : TEAL;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#33");
        stringBuilder.append(str);
        int parseColor = Color.parseColor(stringBuilder.toString());
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("#88");
        stringBuilder2.append(str);
        int parseColor2 = Color.parseColor(stringBuilder2.toString());
        int i = SHARP727272;
        int i2 = SHARP666666;
        return new int[]{parseColor, parseColor2, i, i2};
    }
}
