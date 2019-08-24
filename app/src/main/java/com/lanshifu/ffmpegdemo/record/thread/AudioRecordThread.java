package com.lanshifu.ffmpegdemo.record.thread;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaRecorder;
import android.util.Log;


import com.lanshifu.ffmpegdemo.record.BaseVideoRecorder;

import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.concurrent.CyclicBarrier;

/**
 * 音频录制线程
 */
public class AudioRecordThread extends Thread {


    //指定音频源 这个和MediaRecorder是相同的 MediaRecorder.AudioSource.MIC指的是麦克风
    private static final int mAudioSource = MediaRecorder.AudioSource.MIC;
    //指定采样率 （MediaRecoder 的采样率通常是8000Hz AAC的通常是44100Hz。 设置采样率为44100，目前为常用的采样率，官方文档表示这个值可以兼容所有的设置）
    private static final int mSampleRateInHz = 44100;
    private static final int AUDIO_CHANNELS = 2;

    //设置采样数据格式，默认16比特PCM
//    private static final int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    private static final int mAudioFormat = 16;


    //指定缓冲区大小。调用AudioRecord类的getMinBufferSize方法可以获得。
    private int mMinBufferSize;
    // 这里是 pcm 数据
    private byte[] mAudioData;

    private AudioRecord mAudioRecord = null;

    private int mAudioInputBufferIndex;

    private WeakReference<BaseVideoRecorder> mVideoRecorderWr;
    private volatile boolean mShouldExit = false;
    private MediaCodec mAudioCodec;
    private long mAudioPts = 0;
    private CyclicBarrier mStartCb, mDestroyCb;

    public AudioRecordThread(WeakReference<BaseVideoRecorder> videoRecorderWr) {
        this.mVideoRecorderWr = videoRecorderWr;
        mAudioCodec = videoRecorderWr.get().mAudioCodec;
        mStartCb = videoRecorderWr.get().mStartCb;
        mDestroyCb = videoRecorderWr.get().mDestroyCb;

        initAudioRecord();

    }

    private void initAudioRecord() {
        mMinBufferSize = AudioRecord.getMinBufferSize(
                mSampleRateInHz,
                AudioFormat.CHANNEL_IN_STEREO,
                AudioFormat.ENCODING_PCM_16BIT);//计算最小缓冲区

        mAudioRecord = new AudioRecord(
                mAudioSource,
                mSampleRateInHz,
                AudioFormat.CHANNEL_IN_STEREO,
                AudioFormat.ENCODING_PCM_16BIT,
                mMinBufferSize);//创建AudioRecorder对象

        mAudioData = new byte[mMinBufferSize];
    }


    @Override
    public void run() {
        Log.d("lxb", "run: AudioRecordThread");
        //AudioRecord.getMinBufferSize的参数是否支持当前的硬件设备
        if (AudioRecord.ERROR_BAD_VALUE == mMinBufferSize || AudioRecord.ERROR == mMinBufferSize) {
            throw new RuntimeException("Unable to getMinBufferSize");
        } else {
            Log.d("lxb", "run: AudioRecord 线程开启");
            //标记为开始采集状态
            try {

                //判断AudioRecord未初始化，停止录音的时候释放了，状态就为STATE_UNINITIALIZED
                if (mAudioRecord.getState() == mAudioRecord.STATE_UNINITIALIZED) {
                    initAudioRecord();
                }

                long startTime = System.nanoTime();

                while (true) {
                    if (mShouldExit) {
                        return;
                    }

                    BaseVideoRecorder videoRecorder = mVideoRecorderWr.get();
                    if (videoRecorder == null) {
                        return;
                    }

                    // 不断读取 pcm 数据
                    int readResult = mAudioRecord.read(mAudioData, 0, mMinBufferSize);
                    if (readResult == AudioRecord.ERROR_INVALID_OPERATION || readResult == AudioRecord.ERROR_BAD_VALUE) {
                        continue;
                    }

                    // 把数据写入到 mAudioCodec 的 InputBuffer
                    mAudioInputBufferIndex = mAudioCodec.dequeueInputBuffer(0);
                    if (mAudioInputBufferIndex >= 0) {
                        ByteBuffer byteBuffer = mAudioCodec.getInputBuffer(mAudioInputBufferIndex);
                        if (byteBuffer != null) {
                            byteBuffer.clear();
                            byteBuffer.put(mAudioData);

                            // pts  44100 * 2 *2
//                            mAudioPts += mMinBufferSize * 1000000 / mSampleRateInHz * AUDIO_CHANNELS * 2;

                            //mAudioPts是这个buffer被渲染的时间，微秒
                            //presentationTimeUs = 1000000L * (buffSize / 2) / sampleRate
                            //一帧音频帧大小 int size = 采样率 x 位宽 x 采样时间 x 通道数
                            // 1s时间戳计算公式  presentationTimeUs = 1000000L * (totalBytes / sampleRate/ audioFormat / channelCount / 8 )
                            //totalBytes : 传入编码器的总大小
                            //1000 000L : 单位为 微秒，换算后 = 1s,
                            //除以8     : pcm原始单位是bit, 1 byte = 8 bit, 1 short = 16 bit, 用 Byte[]、Short[] 承载则需要进行换算
                            mAudioPts += (long) (1.0 * readResult / (mSampleRateInHz * AUDIO_CHANNELS * (mAudioFormat / 8)) * 1000000.0);

                            //给指定索引的input buffer填充数据后，将其提交给codec组件
                            mAudioCodec.queueInputBuffer(mAudioInputBufferIndex, 0, mMinBufferSize, mAudioPts, 0);
                            Log.v("lxb", "压入编码栈中 mMinBufferSize="+mMinBufferSize + ",pcm一帧时间戳="+(mAudioPts / 1000000.0f));
                        }
                    }

                }

            } catch (Throwable t) {
                t.printStackTrace();
                Log.e("lxb", "Recording Failed:" + t.getMessage());
                stopRecord();
            }finally {
                onDestroy();
            }
        }
    }

    private void stopRecord() {
        //停止录音，回收AudioRecord对象，释放内存
        if (mAudioRecord != null) {
            if (mAudioRecord.getState() == AudioRecord.STATE_INITIALIZED) {//初始化成功
                mAudioRecord.stop();
            }
            if (mAudioRecord != null) {
                mAudioRecord.release();
            }
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
