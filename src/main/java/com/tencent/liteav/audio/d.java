package com.tencent.liteav.audio;

/* compiled from: TXIAudioRecordListener */
public interface d {
    void onRecordEncData(byte[] bArr, long j, int i, int i2, int i3);

    void onRecordError(int i, String str);

    void onRecordPcmData(byte[] bArr, long j, int i, int i2, int i3);

    void onRecordRawPcmData(byte[] bArr, long j, int i, int i2, int i3, boolean z);
}
