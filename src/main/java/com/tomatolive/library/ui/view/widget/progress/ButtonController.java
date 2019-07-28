package com.tomatolive.library.ui.view.widget.progress;

public interface ButtonController {
    boolean enableGradient();

    boolean enablePress();

    int getDarkerColor(int i);

    int getLighterColor(int i);

    int getPressedColor(int i);
}
