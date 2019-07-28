package com.tencent.liteav.videoencoder;

import android.media.MediaCodec.BufferInfo;
import android.media.MediaFormat;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.g.b;
import com.tencent.liteav.basic.module.a;
import java.nio.ByteBuffer;

/* compiled from: TXIVideoEncoder */
public class c extends a {
    protected g mEncodeFilter;
    private boolean mEncodeFirstGOP = false;
    protected Object mGLContextExternal = null;
    protected boolean mInit;
    protected g mInputFilter;
    protected int mInputHeight = 0;
    protected int mInputTextureID = -1;
    protected int mInputWidth = 0;
    protected d mListener = null;
    protected int mOutputHeight = 0;
    protected int mOutputWidth = 0;
    private long mVideoGOPEncode = 0;

    public long getRealBitrate() {
        return 0;
    }

    public long getRealFPS() {
        return 0;
    }

    public long pushVideoFrame(int i, int i2, int i3, long j) {
        return 10000002;
    }

    public long pushVideoFrameSync(int i, int i2, int i3, long j) {
        return 10000002;
    }

    public void setBitrate(int i) {
    }

    public void setFPS(int i) {
    }

    public void signalEOSAndFlush() {
    }

    public void stop() {
    }

    public int start(TXSVideoEncoderParam tXSVideoEncoderParam) {
        if (tXSVideoEncoderParam != null) {
            this.mOutputWidth = tXSVideoEncoderParam.width;
            this.mOutputHeight = tXSVideoEncoderParam.height;
            this.mInputWidth = tXSVideoEncoderParam.width;
            this.mInputHeight = tXSVideoEncoderParam.height;
            this.mGLContextExternal = tXSVideoEncoderParam.glContext;
        }
        this.mVideoGOPEncode = 0;
        this.mEncodeFirstGOP = false;
        return 10000002;
    }

    public void setListener(d dVar) {
        this.mListener = dVar;
    }

    public int getVideoWidth() {
        return this.mOutputWidth;
    }

    public int getVideoHeight() {
        return this.mOutputHeight;
    }

    /* Access modifiers changed, original: protected */
    public void callDelegate(int i) {
        callDelegate(null, 0, 0, 0, 0, 0, 0, 0, i, null, null);
    }

    /* Access modifiers changed, original: protected */
    public void callDelegate(byte[] bArr, int i, long j, long j2, long j3, long j4, long j5, long j6, int i2, ByteBuffer byteBuffer, BufferInfo bufferInfo) {
        int i3 = i;
        BufferInfo bufferInfo2 = bufferInfo;
        BufferInfo bufferInfo3 = null;
        ByteBuffer asReadOnlyBuffer = byteBuffer == null ? null : byteBuffer.asReadOnlyBuffer();
        if (bufferInfo2 != null) {
            bufferInfo3 = new BufferInfo();
        }
        if (bufferInfo3 != null) {
            bufferInfo3.set(bufferInfo2.offset, bufferInfo2.size, bufferInfo2.presentationTimeUs, bufferInfo2.flags);
        }
        d dVar = this.mListener;
        if (dVar != null) {
            b bVar = new b();
            bVar.nalData = bArr;
            bVar.nalType = i3;
            bVar.gopIndex = j;
            bVar.gopFrameIndex = j2;
            bVar.frameIndex = j3;
            bVar.refFremeIndex = j4;
            bVar.pts = j5;
            bVar.dts = j6;
            bVar.buffer = asReadOnlyBuffer;
            if (bufferInfo3 != null) {
                bVar.info = bufferInfo3;
            }
            dVar.onEncodeNAL(bVar, i2);
            setStatusValue(4002, Long.valueOf(getRealBitrate()));
            setStatusValue(4001, Double.valueOf((double) getRealFPS()));
            if (i3 == 0) {
                if (this.mVideoGOPEncode != 0) {
                    this.mEncodeFirstGOP = true;
                    setStatusValue(4003, Long.valueOf(this.mVideoGOPEncode));
                }
                this.mVideoGOPEncode = 1;
                return;
            }
            this.mVideoGOPEncode++;
            if (!this.mEncodeFirstGOP) {
                setStatusValue(4003, Long.valueOf(this.mVideoGOPEncode));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void callDelegate(MediaFormat mediaFormat) {
        if (this.mListener != null) {
            this.mListener.onEncodeFormat(mediaFormat);
        }
    }
}
