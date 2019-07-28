package com.lanshifu.ffmpegdemo;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lanshifu.ffmpegdemo.camera.widget.CameraFocusView;
import com.lanshifu.ffmpegdemo.camera.widget.CameraView;
import com.lanshifu.ffmpegdemo.record.BaseVideoRecorder;
import com.lanshifu.ffmpegdemo.record.DefaultVideoRecorder;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class CameraActivity extends AppCompatActivity implements BaseVideoRecorder.RecordListener{

    private CameraView mCameraView;
    private CameraFocusView mFocusView;

    private Button mBtnRecord;
    private boolean mIsRecornding = false;


    private DefaultVideoRecorder mVideoRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mCameraView = findViewById(R.id.camera_view);
        mFocusView = findViewById(R.id.camera_focus_view);
        mBtnRecord = findViewById(R.id.record_button);

        mCameraView.setOnFocusListener(new CameraView.FocusListener() {
            @Override
            public void beginFocus(int x, int y) {
                mFocusView.beginFocus(x, y);
            }

            @Override
            public void endFocus(boolean success) {
                mFocusView.endFocus(success);
            }
        });

        initListener();



    }

    private void initListener() {
        mBtnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsRecornding){
                    stopRecord();
                    mBtnRecord.setText("录制");
                }else {
                    startRecord();
                    mBtnRecord.setText("停止");
                }

                mIsRecornding = !mIsRecornding;

            }
        });
    }

    private void startRecord() {
        Log.d("lxb", "开始录制");
        mVideoRecorder = new DefaultVideoRecorder(this,
                mCameraView.getEglContext(), mCameraView.getTextureId());
        mVideoRecorder.initVideo(Environment.getExternalStorageDirectory().getAbsolutePath() + "/input.mp3",
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/live_push.mp4",
                720, 1280);
        mVideoRecorder.setOnRecordListener(this);
        mVideoRecorder.startRecord();

    }

    private void stopRecord() {
        Log.d("lxb", "结束录制");
        mVideoRecorder.stopRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraView.onDestroy();
        stopRecord();
    }

    @Override
    public void onTime(long times) {

        Log.d("lxb", "onTime: " + times);
    }
}
