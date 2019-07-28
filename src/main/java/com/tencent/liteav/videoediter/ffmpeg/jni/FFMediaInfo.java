package com.tencent.liteav.videoediter.ffmpeg.jni;

public class FFMediaInfo {
    public long audioBitrate;
    public long audioDuration;
    public int channels;
    public float fps;
    public int height;
    public int rotation;
    public int sampleRate;
    public long videoBitrate;
    public long videoDuration;
    public int width;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("FFMediaInfo{rotation=");
        stringBuilder.append(this.rotation);
        stringBuilder.append(", width=");
        stringBuilder.append(this.width);
        stringBuilder.append(", height=");
        stringBuilder.append(this.height);
        stringBuilder.append(", fps=");
        stringBuilder.append(this.fps);
        stringBuilder.append(", videoBitrate=");
        stringBuilder.append(this.videoBitrate);
        stringBuilder.append(", videoDuration=");
        stringBuilder.append(this.videoDuration);
        stringBuilder.append(", sampleRate=");
        stringBuilder.append(this.sampleRate);
        stringBuilder.append(", channels=");
        stringBuilder.append(this.channels);
        stringBuilder.append(", audioBitrate=");
        stringBuilder.append(this.audioBitrate);
        stringBuilder.append(", audioDuration=");
        stringBuilder.append(this.audioDuration);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
