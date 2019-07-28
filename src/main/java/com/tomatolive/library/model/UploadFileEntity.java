package com.tomatolive.library.model;

public class UploadFileEntity {
    private String filename;
    private int offset;

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }
}
