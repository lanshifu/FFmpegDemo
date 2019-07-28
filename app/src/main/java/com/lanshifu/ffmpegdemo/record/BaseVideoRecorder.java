package com.lanshifu.ffmpegdemo.record;

import android.content.Context;
import android.media.AudioFormat;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.opengl.GLSurfaceView;
import android.view.Surface;


import com.lanshifu.ffmpegdemo.record.thread.AudioEncoderThread;
import com.lanshifu.ffmpegdemo.record.thread.AudioRecordThread;
import com.lanshifu.ffmpegdemo.record.thread.VideoEncoderThread;
import com.lanshifu.ffmpegdemo.record.thread.VideoRenderThread;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CyclicBarrier;

import javax.microedition.khronos.egl.EGLContext;

/**
 * Created by hcDarren on 2019/7/13.
 */

public abstract class BaseVideoRecorder {

    private WeakReference<BaseVideoRecorder> mVideoRecorderWr = new WeakReference<>(this);
    /**
     * 硬编码 MediaCodec 的 surface
     */
    public Surface mSurface;
    /**
     * 相机共享的 egl 上下文
     */
    public EGLContext mEglContext;
    private Context mContext;

    public GLSurfaceView.Renderer mRenderer;

    public MediaMuxer mMediaMuxer;
    private VideoRenderThread mVideoRenderThread;
    private VideoEncoderThread mVideoEncoderThread;
    private AudioEncoderThread mAudioEncoderThread;
    private AudioRecordThread mAudioRecordThread;

    public MediaCodec mVideoCodec;
    public MediaCodec mAudioCodec;
    public CyclicBarrier mStartCb = new CyclicBarrier(2);
    public CyclicBarrier mDestroyCb = new CyclicBarrier(2);

    private static final int mSampleRateInHz = 44100;
    //指定捕获音频的声道数目。在AudioFormat类中指定用于此的常量
    private static final int mChannelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO; //单声道

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        this.mRenderer = renderer;
        mVideoRenderThread = new VideoRenderThread(mVideoRecorderWr);

    }

    public BaseVideoRecorder(Context context, EGLContext eglContext) {
        this.mContext = context;
        this.mEglContext = eglContext;

        try {
            initAudioCodec(mSampleRateInHz, mChannelConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initAudioCodec(int sampleRate, int channels) throws IOException {
        MediaFormat audioFormat = MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, sampleRate, channels);
        audioFormat.setInteger(MediaFormat.KEY_BIT_RATE, 96000);
        audioFormat.setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC);
        audioFormat.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, sampleRate * channels * 2);
        // 创建音频编码器
        mAudioCodec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC);
        mAudioCodec.configure(audioFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        // 开启 start AudioCodec
        mAudioCodec.start();
    }

    /**
     * 初始化参数
     *
     * @param audioPath   背景音乐的地址
     * @param outPath     输出文件的路径
     * @param videoWidth  录制的宽度
     * @param videoHeight 录制的高度
     */
    public void initVideo(String audioPath, String outPath, int videoWidth, int videoHeight) {
        try {
            mVideoRenderThread.setSize(videoWidth, videoHeight);
            mMediaMuxer = new MediaMuxer(outPath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
            initVideoCodec(videoWidth, videoHeight);
//            mMusicPlayer.setDataSource(audioPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化视频的 MediaCodec
     *
     * @param width
     * @param height
     */
    private void initVideoCodec(int width, int height) throws IOException {
        MediaFormat videoFormat = MediaFormat.createVideoFormat(MediaFormat.MIMETYPE_VIDEO_AVC, width, height);
        // 设置颜色格式
        videoFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
        videoFormat.setInteger(MediaFormat.KEY_BIT_RATE, width * height * 4);
        // 设置帧率
        videoFormat.setInteger(MediaFormat.KEY_FRAME_RATE, 24);
        // 设置 I 帧的间隔时间
        videoFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);

        // 创建编码器
        mVideoCodec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
        mVideoCodec.configure(videoFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);

        mSurface = mVideoCodec.createInputSurface();
        // 开启一个编码采集 InputSurface 上的数据，合成视频
        mVideoEncoderThread = new VideoEncoderThread(mVideoRecorderWr);
    }


    public RecordListener mRecordListener;

    public void setOnRecordListener(RecordListener recordListener) {
        this.mRecordListener = recordListener;
    }

    public interface RecordListener {
        void onTime(long times);
    }


    public void startRecord() {

        // 开启一个编码采集 音乐播放器回调的 PCM 数据，合成视频
        if (mAudioEncoderThread == null){
            mAudioEncoderThread = new AudioEncoderThread(mVideoRecorderWr);
        }

        if (mAudioRecordThread == null){
            mAudioRecordThread = new AudioRecordThread(mVideoRecorderWr);
        }

        mVideoRenderThread.start();
        mVideoEncoderThread.start();

        mAudioEncoderThread.start();
        mAudioRecordThread.start();

    }

    public void stopRecord() {
        mVideoRenderThread.requestExit();
        mVideoEncoderThread.requestExit();

        mAudioEncoderThread.requestExit();
        mAudioRecordThread.requestExit();
    }

}
