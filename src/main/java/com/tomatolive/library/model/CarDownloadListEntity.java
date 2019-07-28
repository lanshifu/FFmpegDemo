package com.tomatolive.library.model;

import java.io.Serializable;
import java.util.List;

public class CarDownloadListEntity implements Serializable {
    public List<CarDownloadEntity> carList;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CarDownloadListEntity{carList=");
        stringBuilder.append(this.carList);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
