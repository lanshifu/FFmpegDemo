package com.lanshifu.ffmpegdemo.record.thread;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaMuxer;
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
    //指定捕获音频的声道数目。在AudioFormat类中指定用于此的常量
    private static final int mChannelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO; //单声道
    //指定音频量化位数 ,在AudioFormaat类中指定了以下各种可能的常量。通常我们选择ENCODING_PCM_16BIT和ENCODING_PCM_8BIT PCM代表的是脉冲编码调制，它实际上是原始音频样本。
    //因此可以设置每个样本的分辨率为16位或者8位，16位将占用更多的空间和处理能力,表示的音频也更加接近真实。
    private static final int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    //指定缓冲区大小。调用AudioRecord类的getMinBufferSize方法可以获得。
    private int mBufferSizeInBytes;

    private boolean isRecording = false; //true表示正在录音
    private AudioRecord mAudioRecord = null;
    //缓冲区中数据写入到数据，因为需要使用IO操作，因此读取数据的过程应该在子线程中执行。
    private Thread mThread;


    private int mAudioInputBufferIndex;
    private long audioAbsolutePtsUs;


    private MediaMuxer mMediaMuxer;
    private WeakReference<BaseVideoRecorder> mVideoRecorderWr;
    private volatile boolean mShouldExit = false;
    private MediaCodec mAudioCodec;
    private MediaCodec.BufferInfo mBufferInfo;
    private int mAudioTrackIndex = -1;
    private long mAudioPts = 0;
    private CyclicBarrier mStartCb, mDestroyCb;

    public AudioRecordThread(WeakReference<BaseVideoRecorder> videoRecorderWr) {
        this.mVideoRecorderWr = videoRecorderWr;
        mAudioCodec = videoRecorderWr.get().mAudioCodec;
        mMediaMuxer = videoRecorderWr.get().mMediaMuxer;
        mBufferInfo = new MediaCodec.BufferInfo();
        mStartCb = videoRecorderWr.get().mStartCb;
        mDestroyCb = videoRecorderWr.get().mDestroyCb;

        initAudioRecord();


    }

    private void initAudioRecord() {
        mBufferSizeInBytes = AudioRecord.getMinBufferSize(mSampleRateInHz, mChannelConfig, mAudioFormat);//计算最小缓冲区
        mAudioRecord = new AudioRecord(mAudioSource, mSampleRateInHz, mChannelConfig,
                mAudioFormat, mBufferSizeInBytes);//创建AudioRecorder对象
    }


    @Override
    public void run() {
        Log.d("lxb", "run: AudioRecordThread");
        //AudioRecord.getMinBufferSize的参数是否支持当前的硬件设备
        if (AudioRecord.ERROR_BAD_VALUE == mBufferSizeInBytes || AudioRecord.ERROR == mBufferSizeInBytes) {
            throw new RuntimeException("Unable to getMinBufferSize");
        } else {
            isRecording = true;
            Log.d("lxb", "run: AudioRecord 线程开启");
            //标记为开始采集状态
            isRecording = true;

            try {
                //获取到文件的数据流
                byte[] buffer = new byte[mBufferSizeInBytes];

                //判断AudioRecord未初始化，停止录音的时候释放了，状态就为STATE_UNINITIALIZED
                if (mAudioRecord.getState() == mAudioRecord.STATE_UNINITIALIZED) {
                    initAudioRecord();
                }

                mAudioRecord.startRecording();//开始录音

                while (true) {
                    if (mShouldExit) {
                        return;
                    }

                    BaseVideoRecorder videoRecorder = mVideoRecorderWr.get();
                    if (videoRecorder == null) {
                        return;
                    }

                    //getRecordingState获取当前AudioReroding是否正在采集数据的状态
                    if (mAudioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
                        int readSize = mAudioRecord.read(buffer, 0, mBufferSizeInBytes);
                        if (readSize == AudioRecord.ERROR_INVALID_OPERATION ||
                                readSize == AudioRecord.ERROR_BAD_VALUE) {
                            continue;
                        }
                        //这个 buffer 就是 pcm数据，获取pcm之后交给编码器编码
                        if (readSize > 0) {
                            //获取输入buffer的index 内部有同步机制
                            mAudioInputBufferIndex = mAudioCodec.dequeueInputBuffer(-1);
                            if (mAudioInputBufferIndex >= 0) {
                                ByteBuffer inputBuffer = mAudioCodec.getInputBuffer(mAudioInputBufferIndex);
                                if (inputBuffer != null) {
                                    inputBuffer.put(buffer);
                                    audioAbsolutePtsUs = (System.nanoTime()) / 1000L;
                                    //压入编码栈中
                                    mAudioCodec.queueInputBuffer(mAudioInputBufferIndex, 0, mBufferSizeInBytes, audioAbsolutePtsUs, 0);
//                                            mAudioEncoder.getCodec().queueInputBuffer(mAudioInputBufferIndex, 0, mBufferSize, audioAbsolutePtsUs, 0);
                                    Log.d("lxb", "压入编码栈中");
                                }
                            }

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
        isRecording = false;
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
        isRecording = false;
    }
}
