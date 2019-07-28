package com.zzhoujay.richtext;

public enum CacheType {
    none(0),
    layout(1),
    all(2);
    
    int value;

    private CacheType(int i) {
        this.value = i;
    }

    public int intValue() {
        return this.value;
    }
}
