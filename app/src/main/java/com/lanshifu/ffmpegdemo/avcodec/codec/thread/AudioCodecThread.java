package com.lanshifu.ffmpegdemo.avcodec.codec.thread;

import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.os.SystemClock;
import android.util.Log;

import com.lanshifu.ffmpegdemo.avcodec.codec.MediaEncodeManager;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/**
 * 音频写入thread
 */
public class AudioCodecThread extends Thread {

    private static final String TAG = "AudioCodecThread";

    private MediaEncodeManager mMediaEncodeManager;
    private MediaCodec audioCodec;
    private MediaCodec.BufferInfo bufferInfo;
    private MediaMuxer mediaMuxer;

    //标记track下标
    private  int mAudioTrackIndex;
    private boolean mIsStop;
    private long mPresentationTimeUs;


    public AudioCodecThread(WeakReference<MediaEncodeManager> mediaEncodeManagerWeakReference) {
        this.mMediaEncodeManager = mediaEncodeManagerWeakReference.get();
        this.audioCodec = mMediaEncodeManager.mAudioCodec;
        this.mediaMuxer = mMediaEncodeManager.mMediaMuxer;
        mPresentationTimeUs = 0;
        mAudioTrackIndex = -1;

        this.bufferInfo = new MediaCodec.BufferInfo();
    }

    @Override
    public void run() {
        mIsStop = false;
        audioCodec.start();
        while (true) {
            if (mMediaEncodeManager == null) {
                Log.e(TAG, "run: mediaEncodeManagerWeakReference == null");
                return;
            }
            if (mIsStop) {
                mMediaEncodeManager.audioStop();
                return;
            }

            //获取一帧解码完成的数据到bufferInfo，没有数据就阻塞
            int outputBufferIndex = audioCodec.dequeueOutputBuffer(bufferInfo, 0);
            //第一次会返回-2，在这时候添加音轨
            if (outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                mAudioTrackIndex = mediaMuxer.addTrack(audioCodec.getOutputFormat());
                mMediaEncodeManager.mAudioTrackReady  = true;
                Log.d(TAG, "run: 添加音轨 mAudioTrackIndex= " + mAudioTrackIndex);
                mMediaEncodeManager.startMediaMuxer();
            } else {
                while (outputBufferIndex >= 0) {
                    if (!mMediaEncodeManager.mEncodeStart) {
                        Log.d(TAG, "run: 混合器还没开始，线程延迟");
                        SystemClock.sleep(10);
                        continue;
                    }

                    ByteBuffer outputBuffer = audioCodec.getOutputBuffers()[outputBufferIndex];
                    outputBuffer.position(bufferInfo.offset);
                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);

                    if (mPresentationTimeUs == 0) {
                        mPresentationTimeUs = bufferInfo.presentationTimeUs;
                    }
                    bufferInfo.presentationTimeUs = bufferInfo.presentationTimeUs - mPresentationTimeUs;
                    mediaMuxer.writeSampleData(mAudioTrackIndex, outputBuffer, bufferInfo);

                    audioCodec.releaseOutputBuffer(outputBufferIndex, false);
                    outputBufferIndex = audioCodec.dequeueOutputBuffer(bufferInfo, 0);
                }
            }
        }
    }

    public void stopAudioCodec() {
        mIsStop = true;
    }
}
