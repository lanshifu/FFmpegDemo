package com.tencent.liteav.videoencoder;

import android.media.MediaFormat;
import com.tencent.liteav.basic.g.b;

/* compiled from: TXIVideoEncoderListener */
public interface d {
    void onEncodeFormat(MediaFormat mediaFormat);

    void onEncodeNAL(b bVar, int i);
}
