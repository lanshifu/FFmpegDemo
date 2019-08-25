package com.lanshifu.ffmpegdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lanshifu.ffmpegdemo.audio.AudioRecordActivity;
import com.lanshifu.ffmpegdemo.avcodec.AvCodecActivity;
import com.lanshifu.ffmpegdemo.push.PushActivity;
import com.lanshifu.ffmpegdemo.push_live.LivePushActivity;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.btn_auio_record).setOnClickListener(this);
        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_player).setOnClickListener(this);
        findViewById(R.id.btn_avcodec).setOnClickListener(this);
        findViewById(R.id.btn_mp4_push).setOnClickListener(this);
        findViewById(R.id.live_push).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_player:
                startActivity(new Intent(SelectActivity.this, MainActivity.class));
                break;

            case R.id.btn_auio_record:
                startActivity(new Intent(SelectActivity.this, AudioRecordActivity.class));
                break;

            case R.id.btn_camera:
                startActivity(new Intent(SelectActivity.this, CameraActivity.class));
                break;

            case R.id.btn_avcodec:
                startActivity(new Intent(SelectActivity.this, AvCodecActivity.class));
                break;

            case R.id.btn_mp4_push:
                startActivity(new Intent(SelectActivity.this, PushActivity.class));
                break;


            case R.id.live_push:
                startActivity(new Intent(SelectActivity.this, LivePushActivity.class));
                break;


        }

    }
}
