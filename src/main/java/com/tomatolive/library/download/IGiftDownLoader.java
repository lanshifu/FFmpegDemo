package com.tomatolive.library.download;

import com.tomatolive.library.model.GiftDownloadItemEntity;
import java.util.List;

public interface IGiftDownLoader {
    void startDownLoad(List<GiftDownloadItemEntity> list);
}
