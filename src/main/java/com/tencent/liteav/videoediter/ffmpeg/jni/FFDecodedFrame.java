package com.tencent.liteav.videoediter.ffmpeg.jni;

public class FFDecodedFrame {
    public byte[] data;
    public int flags;
    public long pts;
    public int sampleRate;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FFDecodedFrame{data size=");
        stringBuilder.append(this.data.length);
        stringBuilder.append(", pts=");
        stringBuilder.append(this.pts);
        stringBuilder.append(", flags=");
        stringBuilder.append(this.flags);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
