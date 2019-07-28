package com.tencent.liteav.e;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* compiled from: TXIAudioEncoderListener */
public interface r {
    void a();

    void a(MediaFormat mediaFormat);

    void a(ByteBuffer byteBuffer, BufferInfo bufferInfo);
}
