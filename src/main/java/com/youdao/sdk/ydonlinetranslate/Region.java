package com.youdao.sdk.ydonlinetranslate;

import com.youdao.sdk.ydonlinetranslate.other.d;

public class Region {
    private d boundingBox;
    private String context;
    private int lineheight;
    private int linesCount;
    private String tranContent;

    public String getContext() {
        return this.context;
    }

    public void setContext(String str) {
        this.context = str;
    }

    public String getTranContent() {
        return this.tranContent;
    }

    public void setTranContent(String str) {
        this.tranContent = str;
    }

    public int getLinesCount() {
        return this.linesCount;
    }

    public void setLinesCount(int i) {
        this.linesCount = i;
    }

    public int getLineheight() {
        return this.lineheight;
    }

    public void setLineheight(int i) {
        this.lineheight = i;
    }

    public d getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(d dVar) {
        this.boundingBox = dVar;
    }
}
