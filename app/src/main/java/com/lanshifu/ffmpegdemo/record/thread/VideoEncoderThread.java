package com.lanshifu.ffmpegdemo.record.thread;

import android.media.MediaCodec;
import android.media.MediaMuxer;


import com.lanshifu.ffmpegdemo.record.BaseVideoRecorder;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.concurrent.CyclicBarrier;

public class VideoEncoderThread extends Thread {

    private final MediaMuxer mMediaMuxer;
    private WeakReference<BaseVideoRecorder> mVideoRecorderWr;
    private volatile boolean mShouldExit = false;
    private MediaCodec mVideoCodec;
    private MediaCodec.BufferInfo mBufferInfo;
    private int mVideoTrackIndex = -1;
    private long mVideoPts = 0;
    private final CyclicBarrier mStartCb, mDestroyCb;

    public VideoEncoderThread(WeakReference<BaseVideoRecorder> videoRecorderWr) {
        this.mVideoRecorderWr = videoRecorderWr;
        mVideoCodec = videoRecorderWr.get().mVideoCodec;
        mMediaMuxer = videoRecorderWr.get().mMediaMuxer;
        mBufferInfo = new MediaCodec.BufferInfo();
        mStartCb = videoRecorderWr.get().mStartCb;
        mDestroyCb = videoRecorderWr.get().mDestroyCb;
    }

    @Override
    public void run() {
        try {
            mVideoCodec.start();

            while (true) {
                if (mShouldExit) {
                    return;
                }

                BaseVideoRecorder videoRecorder = mVideoRecorderWr.get();
                if (videoRecorder == null) {
                    return;
                }

                // 代码先不写，先测试，从 surface 上获取数据，编码成 h264 ,通过 MediaMuxer 合成 mp4
                int outputBufferIndex = mVideoCodec.dequeueOutputBuffer(mBufferInfo, 0);
                if (outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                    mVideoTrackIndex = mMediaMuxer.addTrack(mVideoCodec.getOutputFormat());
                    mMediaMuxer.start();
                    mStartCb.await();
                } else {
                    while (outputBufferIndex >= 0) {
                        // 获取数据
                        ByteBuffer outBuffer = mVideoCodec.getOutputBuffers()[outputBufferIndex];
                        outBuffer.position(mBufferInfo.offset);
                        outBuffer.limit(mBufferInfo.offset + mBufferInfo.size);

                        // 修改 pts
                        if (mVideoPts == 0) {
                            mVideoPts = mBufferInfo.presentationTimeUs;
                        }
                        mBufferInfo.presentationTimeUs -= mVideoPts;

                        // 写入数据
                        mMediaMuxer.writeSampleData(mVideoTrackIndex, outBuffer, mBufferInfo);

                        // 回调当前录制的时间
                        if (videoRecorder.mRecordListener != null) {
                            videoRecorder.mRecordListener.onTime(mBufferInfo.presentationTimeUs / 1000);
                        }

                        mVideoCodec.releaseOutputBuffer(outputBufferIndex, false);
                        outputBufferIndex = mVideoCodec.dequeueOutputBuffer(mBufferInfo, 0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            onDestroy();
        }
    }

    private void onDestroy() {
        try {
            mVideoCodec.stop();
            mVideoCodec.release();
            mDestroyCb.await();
            mMediaMuxer.stop();
            mMediaMuxer.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestExit() {
        mShouldExit = true;
    }
}
