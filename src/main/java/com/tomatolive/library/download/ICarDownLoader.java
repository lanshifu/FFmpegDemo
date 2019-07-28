package com.tomatolive.library.download;

import com.tomatolive.library.model.CarDownloadEntity;
import java.util.List;

public interface ICarDownLoader {
    void startDownLoad(List<CarDownloadEntity> list);
}
