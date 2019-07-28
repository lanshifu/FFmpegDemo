package com.tencent.liteav.basic.g;

/* compiled from: TXSAudioPacket */
public class a implements Cloneable {
    public byte[] audioData;
    public int bitsPerChannel;
    public int channelsPerSample;
    public int packetType;
    public int sampleRate;
    public long timestamp;

    public Object clone() {
        try {
            return (a) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
