package com.lanshifu.ffmpegdemo.audio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lanshifu.ffmpegdemo.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioRecordActivity extends AppCompatActivity {

    /**
     * 需要申请的运行时权限
     */
    private String[] permissions = new String[]{
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /**
     * 被用户拒绝的权限列表
     */
    private List<String> mPermissionList = new ArrayList<>();

    private AudioRecordThread mAudioRecordThread;

    String pcmPath = Environment.getExternalStorageDirectory()+ "/test.pcm";

    /**
     * 采样率，现在能够保证在所有设备上使用的采样率是44100Hz, 但是其他的采样率（22050, 16000, 11025）在一些设备上也可以使用。
     */
    public static final int SAMPLE_RATE_INHZ = 44100;

    /**
     * 声道数。CHANNEL_IN_MONO and CHANNEL_IN_STEREO. 其中CHANNEL_IN_MONO是可以保证在所有设备能够使用的。
     */
    public static final int CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO;
    /**
     * 返回的音频数据的格式。 ENCODING_PCM_8BIT, ENCODING_PCM_16BIT, and ENCODING_PCM_FLOAT.
     */
    public static final int AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        checkPermissions();
    }

    private void checkPermissions() {
        // Marshmallow开始才用申请运行时权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                        PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissions[i]);
                }
            }
            if (!mPermissionList.isEmpty()) {
                String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }

    }


    public void startRecord(View view) {

        if (mAudioRecordThread != null){
            mAudioRecordThread.stopRecord();
            mAudioRecordThread = null;
        }
        
        mAudioRecordThread = new AudioRecordThread();
        mAudioRecordThread.start();
    }


    public void stopRecord(View view) {
        if (mAudioRecordThread != null){
            mAudioRecordThread.stopRecord();
            mAudioRecordThread = null;
        }
    }

    public void pcmTowav(View view) {
        PcmToWavUtil pcmToWavUtil = new PcmToWavUtil(SAMPLE_RATE_INHZ, CHANNEL_CONFIG, AUDIO_FORMAT);
        File pcmFile = new File(Environment.getExternalStorageDirectory(), "test.pcm");
        File wavFile = new File(Environment.getExternalStorageDirectory(), "test.wav");
        if (!wavFile.mkdirs()) {
            Log.e("lxb", "wavFile Directory not created");
        }
        if (wavFile.exists()) {
            wavFile.delete();
        }
        pcmToWavUtil.pcmToWav(pcmFile.getAbsolutePath(), wavFile.getAbsolutePath());
    }


    class AudioRecordThread extends Thread {


        //指定音频源 这个和MediaRecorder是相同的 MediaRecorder.AudioSource.MIC指的是麦克风
        private static final int mAudioSource = MediaRecorder.AudioSource.MIC;
        //指定采样率 （MediaRecoder 的采样率通常是8000Hz AAC的通常是44100Hz。 设置采样率为44100，目前为常用的采样率，官方文档表示这个值可以兼容所有的设置）
        private static final int mSampleRateInHz = 44100;
        private static final int AUDIO_CHANNELS = 2;

        //设置采样数据格式，默认16比特PCM
        private static final int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;

        //指定缓冲区大小。调用AudioRecord类的getMinBufferSize方法可以获得。
        private int mMinBufferSize;
        // 这里是 pcm 数据
        private byte[] mAudioData;

        private AudioRecord mAudioRecord = null;

        private int mAudioInputBufferIndex;

        private volatile boolean mShouldExit = false;
        private long mAudioPts = 0;

        public AudioRecordThread() {

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

            FileOutputStream os = null;



            final File pcmFile = new File(pcmPath);
            Log.d("lxb", "pcmPath= " + pcmPath);
            if (!pcmFile.mkdirs()) {
                Log.e("lxb", "Directory not created");
            }
            if (pcmFile.exists()) {
                pcmFile.delete();
            }
            try {
                os = new FileOutputStream(pcmFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


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


                    while (!mShouldExit) {

                        // 不断读取 pcm 数据
                        int readResult = mAudioRecord.read(mAudioData, 0, mMinBufferSize);
                        if (readResult == AudioRecord.ERROR_INVALID_OPERATION || readResult == AudioRecord.ERROR_BAD_VALUE) {
                            continue;
                        }
                        
                        //mAudioData保存到文件
                        try {
                            os.write(mAudioData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                } catch (Throwable t) {
                    t.printStackTrace();
                    Log.e("lxb", "Recording Failed:" + t.getMessage());
                    stopRecord();
                }finally {
                    try {
                        if (os != null){
                            os.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private void stopRecord() {
            requestExit();
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


        public void requestExit() {
            mShouldExit = true;
        }
    }

}
