package com.tomatolive.library.model;

import com.blankj.utilcode.util.e;
import com.tomatolive.library.utils.i;
import java.io.Serializable;

public class CarDownloadEntity implements Serializable {
    public String animLocalPath;
    public String id;
    public String imgUrl;
    public String name;
    public String onlineUrl;
    public String zipUrl;

    public String getCarFileName() {
        return e.c(this.zipUrl);
    }

    public String getOnlineUrl() {
        return i.c(this.onlineUrl);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CarDownloadEntity{name='");
        stringBuilder.append(this.name);
        stringBuilder.append('\'');
        stringBuilder.append(", animLocalPath='");
        stringBuilder.append(this.animLocalPath);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
