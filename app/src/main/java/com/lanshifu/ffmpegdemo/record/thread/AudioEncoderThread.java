package com.lanshifu.ffmpegdemo.record.thread;

import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.util.Log;


import com.lanshifu.ffmpegdemo.record.BaseVideoRecorder;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.concurrent.CyclicBarrier;

public class AudioEncoderThread extends Thread {

    private final MediaMuxer mMediaMuxer;
    private WeakReference<BaseVideoRecorder> mVideoRecorderWr;
    private volatile boolean mShouldExit = false;
    private MediaCodec mAudioCodec;
    private MediaCodec.BufferInfo mBufferInfo;
    private int mAudioTrackIndex = -1;
    private long mAudioPts = 0;
    private final CyclicBarrier mStartCb, mDestroyCb;

    public AudioEncoderThread(WeakReference<BaseVideoRecorder> videoRecorderWr) {
        this.mVideoRecorderWr = videoRecorderWr;
        mAudioCodec = videoRecorderWr.get().mAudioCodec;
        mMediaMuxer = videoRecorderWr.get().mMediaMuxer;
        mBufferInfo = new MediaCodec.BufferInfo();
        mStartCb = videoRecorderWr.get().mStartCb;
        mDestroyCb = videoRecorderWr.get().mDestroyCb;
    }

    @Override
    public void run() {
        Log.d("lxb", "run: AudioEncoderThread");
        try {
            while (true) {
                if (mShouldExit) {
                    return;
                }

                BaseVideoRecorder videoRecorder = mVideoRecorderWr.get();
                if (videoRecorder == null) {
                    return;
                }

                // 获取音频数据，那这个音频数据从哪里来？音乐播放器里面来，pcm 数据
                int outputBufferIndex = mAudioCodec.dequeueOutputBuffer(mBufferInfo, 0);
                if (outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                    mAudioTrackIndex = mMediaMuxer.addTrack(mAudioCodec.getOutputFormat());
                    Log.d("lxb", "run: AudioEncoderThread await");
                    mStartCb.await();
                    // mMediaMuxer.start();
                } else {
                    while (outputBufferIndex >= 0) {
                        // 获取数据
                        ByteBuffer outBuffer = mAudioCodec.getOutputBuffers()[outputBufferIndex];
                        outBuffer.position(mBufferInfo.offset);
                        outBuffer.limit(mBufferInfo.offset + mBufferInfo.size);

                        // 修改 pts
                        if (mAudioPts == 0) {
                            mAudioPts = mBufferInfo.presentationTimeUs;
                        }
                        mBufferInfo.presentationTimeUs -= mAudioPts;

                        // 写入数据
                        mMediaMuxer.writeSampleData(mAudioTrackIndex, outBuffer, mBufferInfo);
                        Log.d("lxb", "run: 音频写入混合器");

                        mAudioCodec.releaseOutputBuffer(outputBufferIndex, false);
                        outputBufferIndex = mAudioCodec.dequeueOutputBuffer(mBufferInfo, 0);
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
            //调用stop()来生成文件，和release()释放资源。
            mAudioCodec.stop();
            mAudioCodec.release();
            mDestroyCb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestExit() {
        mShouldExit = true;
    }
}
