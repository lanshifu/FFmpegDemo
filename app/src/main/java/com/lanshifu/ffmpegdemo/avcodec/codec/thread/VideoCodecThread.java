package com.lanshifu.ffmpegdemo.avcodec.codec.thread;

import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.os.SystemClock;
import android.util.Log;


import com.lanshifu.ffmpegdemo.avcodec.codec.MediaEncodeManager;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;


/**
 * 视频写入thread
 */
public class VideoCodecThread extends Thread {
    private static final String TAG = "VideoCodecThread";
    private MediaEncodeManager mMediaEncodeManager;
    private MediaCodec videoCodec;
    private MediaCodec.BufferInfo bufferInfo;
    private MediaMuxer mediaMuxer;
    private int mVideoTrackIndex;

    private boolean mIsStop;
    private long mPresentationTimeUs;


    public VideoCodecThread(WeakReference<MediaEncodeManager> mediaEncodeManagerWeakReference) {
        this.mMediaEncodeManager = mediaEncodeManagerWeakReference.get();
        this.videoCodec = mMediaEncodeManager.mVideoCodec;
        this.bufferInfo = new MediaCodec.BufferInfo();
        this.mediaMuxer = mMediaEncodeManager.mMediaMuxer;
        mPresentationTimeUs = 0;
        mVideoTrackIndex = -1;
    }

    @Override
    public void run() {
        mIsStop = false;
        videoCodec.start();
        while (true) {
            if (mMediaEncodeManager == null) {
                Log.e(TAG, "run: mMediaEncodeManager == null");
                return;
            }
            if (mIsStop) {
                mMediaEncodeManager.videoStop();
                return;
            }

            int outputBufferIndex = videoCodec.dequeueOutputBuffer(bufferInfo, 0);
            //第一次返回 -2，在这个时候添加音轨
            if (outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                mVideoTrackIndex = mediaMuxer.addTrack(videoCodec.getOutputFormat());
                Log.d(TAG, "添加视频轨道，mVideoTrackIndex = " + mVideoTrackIndex);
                mMediaEncodeManager.mVideoTrackReady = true;
                mMediaEncodeManager.startMediaMuxer();
            } else {
                while (outputBufferIndex >= 0) {
                    if (!mMediaEncodeManager.mEncodeStart) {
                        Log.d(TAG, "run: 混合器还没开始，线程延迟");
                        SystemClock.sleep(10);
                        continue;
                    }

                    ByteBuffer outputBuffer = videoCodec.getOutputBuffers()[outputBufferIndex];
                    outputBuffer.position(bufferInfo.offset);
                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);

                    if (mPresentationTimeUs == 0) {
                        mPresentationTimeUs = bufferInfo.presentationTimeUs;
                    }
                    bufferInfo.presentationTimeUs = bufferInfo.presentationTimeUs - mPresentationTimeUs;
                    mediaMuxer.writeSampleData(mVideoTrackIndex, outputBuffer, bufferInfo);
                    if (bufferInfo != null) {
                        mMediaEncodeManager.onRecordTimeCallBack((int) (bufferInfo.presentationTimeUs / 1000000));
                    }
                    videoCodec.releaseOutputBuffer(outputBufferIndex, false);
                    outputBufferIndex = videoCodec.dequeueOutputBuffer(bufferInfo, 0);
                }
            }
        }
    }

    public void stopVideoCodec() {
        mIsStop = true;
    }
}
