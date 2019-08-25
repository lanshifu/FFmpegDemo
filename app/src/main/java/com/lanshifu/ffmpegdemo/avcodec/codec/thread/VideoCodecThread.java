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
    private static final String TAG = "lxb-VideoCodecThread";
    private MediaEncodeManager mMediaEncodeManager;
    private MediaCodec videoCodec;
    private MediaCodec.BufferInfo bufferInfo;
    private MediaMuxer mediaMuxer;
    private int mVideoTrackIndex;

    private boolean mIsStop;
    private long mPresentationTimeUs;

    //推流相关
    public byte[] mVideoSps, mVideoPps;


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

                // 推流要获取 sps 和 pps。 ”csd-0” （sps） ，”csd-1”（pps）
                ByteBuffer byteBuffer = videoCodec.getOutputFormat().getByteBuffer("csd-0");
                mVideoSps = new byte[byteBuffer.remaining()];
                byteBuffer.get(mVideoSps, 0, mVideoSps.length);
                byteBuffer = videoCodec.getOutputFormat().getByteBuffer("csd-1");
                mVideoPps = new byte[byteBuffer.remaining()];
                byteBuffer.get(mVideoPps, 0, mVideoPps.length);
                Log.d(TAG, " 成功获取sps和pps ");
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

                    //1、 在关键帧前先把 sps 和 pps 推到流媒体服务器
                    if (bufferInfo.flags == MediaCodec.BUFFER_FLAG_KEY_FRAME) {
                        mMediaEncodeManager.mLivePush.pushSpsPps(mVideoSps,
                                mVideoSps.length, mVideoPps, mVideoPps.length);
                        Log.d(TAG, "推送关键帧sps和pps");
                    }

                    //2、推送每一帧
                    byte[] data = new byte[outputBuffer.remaining()];
                    outputBuffer.get(data, 0, data.length);
                    mMediaEncodeManager.mLivePush.pushVideo(data, data.length,
                            bufferInfo.flags == MediaCodec.BUFFER_FLAG_KEY_FRAME);


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
