package com.lanshifu.ffmpegdemo.avcodec;

import android.content.Context;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.lanshifu.ffmpegdemo.R;
import com.lanshifu.ffmpegdemo.avcodec.audio.AudioManager;
import com.lanshifu.ffmpegdemo.avcodec.codec.MediaEncodeManager;
import com.lanshifu.ffmpegdemo.avcodec.codec.MediaMuxerChangeListener;
import com.lanshifu.ffmpegdemo.avcodec.codec.VideoEncodeRender;
import com.lanshifu.ffmpegdemo.avcodec.surface.CameraSurfaceView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 音视频编解码
 */
public class AvCodecActivity extends AppCompatActivity implements View.OnClickListener, MediaMuxerChangeListener {

    private static final String TAG = "lxb";
    //录音
    private AudioManager mAudioManager;
    boolean mIsRecording = false;
    private Button mBtnRecord;
    private CameraSurfaceView mCameraSurface;

    private MediaEncodeManager mediaEncodeManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avcodec);

        mCameraSurface = findViewById(R.id.mCameraSurface);
        mBtnRecord = findViewById(R.id.btnRecord);
        mBtnRecord.setOnClickListener(this);

        init();
    }

    private void init() {
        mAudioManager = new AudioManager();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRecord:
                mIsRecording = !mIsRecording;
                if (mIsRecording){
                    mBtnRecord.setText("停止");

                    initMediaCodec();
                    mediaEncodeManager.startEncode();
                    mAudioManager.start();

                }else {
                    mBtnRecord.setText("录音");
                    mediaEncodeManager.stopEncode();
                    mAudioManager.stop();
                }
                break;
        }
    }

    private void initMediaCodec() {
        String currentDate = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.CHINA).format(new Date());
        String fileName = "/VID_".concat(currentDate).concat(".mp4");
        String filePath = getDiskCachePath(this) + fileName;
        Log.d(TAG, "mp4路径 = " + filePath);
        int mediaFormat = MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4;
        String audioType = MediaFormat.MIMETYPE_AUDIO_AAC;
        String videoType = MediaFormat.MIMETYPE_VIDEO_AVC;
//        String videoType = "video/avc";
        int sampleRate = 44100;
        int channels = 2;//单声道 channelCount=1 , 双声道  channelCount=2
        //AudioCapture.class类中采集音频采用的位宽：AudioFormat.ENCODING_PCM_16BIT ，此处应传入16bit，
        // 用作计算pcm一帧的时间戳
        int audioFormat = 16;
        //预览
        int width = mCameraSurface.getCameraPreviewHeight();
        int height = mCameraSurface.getCameraPreviewWidth();

        mediaEncodeManager = new MediaEncodeManager(new VideoEncodeRender(this,
                mCameraSurface.getTextureId(), mCameraSurface.getType(), mCameraSurface.getColor()));

        mediaEncodeManager.initMediaCodec(filePath, mediaFormat, audioType, sampleRate,
                channels, audioFormat, videoType, width, height);

        mediaEncodeManager.initThread(this, mCameraSurface.getEglContext(),
                GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    //录音线程数据回调
    private void setPcmRecordListener() {
        if (mAudioManager.getCaptureListener() == null)
            mAudioManager.setCaptureListener(new AudioManager.AudioCaptureListener() {
                @Override
                public void onCaptureListener(byte[] audioSource, int audioReadSize) {
                    Log.d(TAG, "录音回调: audioReadSize = " + audioReadSize);
                    if (mediaEncodeManager.mAudioStop || mediaEncodeManager.mVideoStop) {
                        return;
                    }
                    mediaEncodeManager.setPcmSource(audioSource, audioReadSize);
                }
            });
    }


    /**
     * 读取缓存目录
     *
     * @param context :context
     * @return String
     */
    public static String getDiskCachePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            if (context.getExternalCacheDir() == null) {
                return "";
            }
            return context.getExternalCacheDir().getPath();
        } else {
            return context.getCacheDir().getPath();
        }
    }

    @Override
    public void onMediaMuxerChangeListener(int type) {
        if (type == MediaEncodeManager.MUXER_START) {
            Log.d(TAG, "onMediaMuxerChangeListener --- " + "视频录制开始了");
            setPcmRecordListener();
        }
    }

    @Override
    public void onMediaInfoListener(int time) {
        Log.d(TAG, "视频录制时长回调 --- " + time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaEncodeManager != null) {
            mediaEncodeManager.stopEncode();
        }
    }
}
