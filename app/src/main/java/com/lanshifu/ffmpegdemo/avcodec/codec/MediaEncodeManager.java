package com.lanshifu.ffmpegdemo.avcodec.codec;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.opengl.EGLContext;
import android.os.Build;
import android.util.Log;
import android.view.Surface;


import com.lanshifu.ffmpegdemo.avcodec.codec.thread.AudioCodecThread;
import com.lanshifu.ffmpegdemo.avcodec.codec.thread.EglRenderThread;
import com.lanshifu.ffmpegdemo.avcodec.codec.thread.VideoCodecThread;
import com.lanshifu.ffmpegdemo.avcodec.surface.EglSurfaceView;
import com.lanshifu.ffmpegdemo.push_live.LivePushHandle;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/**
 * 编解码管理类
 */
public class MediaEncodeManager {
    private static final String TAG = "lxb-MediaEncodeManager";

    //开始合成
    public static final int MUXER_START = 1;
    //结束合成
    public static final int MUXER_STOP = 2;

    public EglSurfaceView.Render mEglSurfaceRender;
    public Surface mSurface;

    public MediaMuxer mMediaMuxer;

    public MediaCodec mAudioCodec;

    public MediaCodec mVideoCodec;

    private int mSampleRate;
    private int mChannelCount;
    private int mAudioFormat;
    private int mSurfaceWidth;
    private int mSurfaceHeight;

    //时间戳
    private long mPresentationTimeUs;

    private AudioCodecThread mAudioCodecThread;
    private VideoCodecThread mVideoCodecThread;
    private EglRenderThread mEglRenderThread;

    //用于标记是否已经添加视频轨道
    public boolean mVideoTrackReady = false;
    //用于标记是否已经添加音频轨道
    public boolean mAudioTrackReady = false;
    //标记MediaMuxer是否开启
    public boolean mEncodeStart;

    //标记EGLSurface 开始创建
    public boolean mSurfaceCreate;
    //标记EGLSurface 内容发生变化
    public boolean mSurfaceChange;
    //标记MediaCodec -- audioCodec 对象是否退出释放
    public boolean mAudioStop;

    //标记MediaCodec -- videoCodec 对象是否退出释放
    public boolean mVideoStop;

    private MediaMuxerChangeListener mMediaMuxerChangeListener;

    public LivePushHandle mLivePush = new LivePushHandle();

    public MediaEncodeManager(EglSurfaceView.Render eglSurfaceRender) {
        this.mEglSurfaceRender = eglSurfaceRender;
    }

    public void initMediaCodec(String filePath, int mediaFormat, String audioType, int sampleRate,
                               int channelCount, int audioFormat, String videoType, int width, int height) {
        initMediaMuxer(filePath, mediaFormat);
        initVideoCodec(videoType, width, height);
        initAudioCodec(audioType, sampleRate, channelCount);
        this.mSampleRate = sampleRate;
        this.mChannelCount = channelCount;
        this.mAudioFormat = audioFormat;
        this.mSurfaceWidth = width;
        this.mSurfaceHeight = height;
    }

    public void initThread(MediaMuxerChangeListener listener, EGLContext eglContext, int renderMode) {
        //传入视频解码器的surface
        mEglRenderThread = new EglRenderThread(new WeakReference<>(this), eglContext, renderMode, mSurfaceWidth, mSurfaceHeight);
        mVideoCodecThread = new VideoCodecThread(new WeakReference<>(this));
        mAudioCodecThread = new AudioCodecThread(new WeakReference<>(this));

        this.mMediaMuxerChangeListener = listener;
    }

    private void initMediaMuxer(String filePath, int mediaFormat) {
        try {
            mMediaMuxer = new MediaMuxer(filePath, mediaFormat);
        } catch (IOException e) {
            Log.e(TAG, "initMediaMuxer: 文件打开失败,path=" + filePath);
        }
    }

    private void initAudioCodec(String audioType, int sampleRate, int channels) {
        try {
            mAudioCodec = MediaCodec.createEncoderByType(audioType);
            MediaFormat audioFormat = MediaFormat.createAudioFormat(audioType, sampleRate, channels);
            int BIT_RATE = 96000;
            audioFormat.setInteger(MediaFormat.KEY_BIT_RATE, BIT_RATE);
            audioFormat.setInteger(MediaFormat.KEY_AAC_PROFILE,
                    MediaCodecInfo.CodecProfileLevel.AACObjectLC);
            int MAX_INOUT_SIZE = 8192;
            audioFormat.setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, MAX_INOUT_SIZE);
            mAudioCodec.configure(audioFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        } catch (IOException e) {
            Log.e(TAG, "initAudioCodec: 音频类型无效");
        }
    }

    private void initVideoCodec(String videoType, int width, int height) {
        try {
            mVideoCodec = MediaCodec.createEncoderByType(videoType);
            MediaFormat videoFormat = MediaFormat.createVideoFormat(videoType, width, height);

            // 设置颜色格式
            videoFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface);
            //MediaFormat.KEY_FRAME_RATE -- 可通过Camera#Parameters#getSupportedPreviewFpsRange获取
            videoFormat.setInteger(MediaFormat.KEY_FRAME_RATE, 24);
            //mSurfaceWidth*mSurfaceHeight*N  N标识码率低、中、高，类似可设置成1，3，5，码率越高视频越大，也越清晰
            videoFormat.setInteger(MediaFormat.KEY_BIT_RATE, width * height * 4);
            // 设置 I 帧的间隔时间
            videoFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                videoFormat.setInteger(MediaFormat.KEY_PROFILE, MediaCodecInfo.CodecProfileLevel.AVCProfileHigh);
                videoFormat.setInteger(MediaFormat.KEY_LEVEL, MediaCodecInfo.CodecProfileLevel.AVCLevel31);
            }

            // 创建编码器
            mVideoCodec.configure(videoFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
            //注意这里，获取视频解码器的surface，之后要将opengl输出到这个surface中
            mSurface = mVideoCodec.createInputSurface();
        } catch (IOException e) {
            Log.e(TAG, "initVideoCodec: 视频类型无效");
        }
    }

    public void setPcmSource(byte[] pcmBuffer, int buffSize) {
        if (mAudioCodec == null) {
            return;
        }
        try {
            int buffIndex = mAudioCodec.dequeueInputBuffer(0);
            if (buffIndex < 0) {
                return;
            }
            ByteBuffer byteBuffer;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                byteBuffer = mAudioCodec.getInputBuffer(buffIndex);
            } else {
                byteBuffer = mAudioCodec.getInputBuffers()[buffIndex];
            }
            if (byteBuffer == null) {
                return;
            }
            byteBuffer.clear();
            byteBuffer.put(pcmBuffer);

            //mPresentationTimeUs = 1000000L * (buffSize / 2) / mSampleRate
            //一帧音频帧大小 int size = 采样率 x 位宽 x 采样时间 x 通道数
            // 1s时间戳计算公式  mPresentationTimeUs = 1000000L * (totalBytes / mSampleRate/ mAudioFormat / mChannelCount / 8 )
            //totalBytes : 传入编码器的总大小
            //1000 000L : 单位为 微秒，换算后 = 1s,
            //除以8     : pcm原始单位是bit, 1 byte = 8 bit, 1 short = 16 bit, 用 Byte[]、Short[] 承载则需要进行换算
            mPresentationTimeUs += (long) (1.0 * buffSize / (mSampleRate * mChannelCount * (mAudioFormat / 8)) * 1000000.0);
            Log.d(TAG, "pcm一帧时间戳 = " + mPresentationTimeUs / 1000000.0f);
            mAudioCodec.queueInputBuffer(buffIndex, 0, buffSize, mPresentationTimeUs, 0);
        } catch (IllegalStateException e) {
            //mAudioCodec 线程对象已释放MediaCodec对象
            Log.d(TAG, "setPcmSource: " + "MediaCodec对象已释放");
        }
    }

    /**
     * 添加音轨后调用
     */
    public void startMediaMuxer() {
        if (mVideoTrackReady && mAudioTrackReady) {
            mMediaMuxer.start();
            mEncodeStart = true;
            if (mMediaMuxerChangeListener != null) {
                mMediaMuxerChangeListener.onMediaMuxerChangeListener(MUXER_START);
            }
        }
    }

    public void startEncode() {
        if (mSurface == null) {
            Log.e(TAG, "startEncode: createInputSurface创建失败");
            return;
        }
        mEncodeStart = false;
        mEglRenderThread.start();
        mVideoCodecThread.start();
        mAudioCodecThread.start();

        mSurfaceCreate = true;
        mSurfaceChange = true;

        mAudioStop = false;
        mVideoStop = false;

    }

    public void stopEncode() {
        if (mAudioCodecThread != null){
            mAudioCodecThread.stopAudioCodec();
            mAudioCodecThread = null;
        }
        if (mVideoCodecThread != null){
            mVideoCodecThread.stopVideoCodec();
            mVideoCodecThread = null;
        }
        if (mEglRenderThread != null) {
            mEglRenderThread.stopEglRender();
            mEglRenderThread = null;
        }

        mEncodeStart = false;
        mSurfaceCreate = false;
        mSurfaceChange = false;
        mVideoTrackReady = false;
        mAudioTrackReady = false;
    }


    public void audioStop() {
        if (mAudioCodec != null) {
            mAudioCodec.stop();
            mAudioCodec.release();
            mAudioCodec = null;
        }
        mAudioStop = true;
        stopMediaMuxer();
    }

    public void videoStop() {
        if (mVideoCodec != null) {
            mVideoCodec.stop();
            mVideoCodec.release();
            mVideoCodec = null;
        }
        mVideoStop = true;
        stopMediaMuxer();
        stopPush();
    }

    private void stopMediaMuxer() {
        if (mMediaMuxer == null) {
            return;
        }
        mMediaMuxer.stop();
        mMediaMuxer.release();
        mMediaMuxer = null;
        if (mMediaMuxerChangeListener != null) {
            mMediaMuxerChangeListener.onMediaMuxerChangeListener(MediaEncodeManager.MUXER_STOP);
        }
    }

    public void onRecordTimeCallBack(int time){
        if (mMediaMuxerChangeListener != null){
            mMediaMuxerChangeListener.onMediaInfoListener(time);
        }
    }

    public void startPush(){
        mLivePush.setOnConnectListener(new LivePushHandle.ConnectListener() {
            @Override
            public void connectError(int errorCode, String errorMsg) {
                Log.d(TAG, "connectError: ");
            }

            @Override
            public void connectSuccess() {
                Log.d(TAG, "connectSuccess: ");
                startEncode();
            }

            @Override
            public void onInfo(long pts, long dts, long duration, long index) {

            }
        });
        mLivePush.initConnect();
    }

    public void stopPush(){
        mLivePush.stop();
    }
}
