package com.tencent.liteav.basic.g;

import android.media.MediaCodec.BufferInfo;
import java.nio.ByteBuffer;

/* compiled from: TXSNALPacket */
public class b {
    public ByteBuffer buffer = null;
    public int codecId = 0;
    public long dts = 0;
    public long frameIndex = 0;
    public long gopFrameIndex = 0;
    public long gopIndex = 0;
    public BufferInfo info = null;
    public byte[] nalData = null;
    public int nalType = -1;
    public long pts = 0;
    public long refFremeIndex = 0;
}
