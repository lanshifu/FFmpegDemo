package com.tomatolive.library.model;

import java.io.Serializable;
import java.util.List;

public class GiftDownloadListEntity implements Serializable {
    public List<GiftDownloadItemEntity> list;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("GiftDownloadListEntity{list=");
        stringBuilder.append(this.list);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
