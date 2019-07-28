package com.lanshifu.ffmpegdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lanshifu.ffmpegdemo.player.MusicPlayer;
import com.lanshifu.ffmpegdemo.player.VideoView;
import com.lanshifu.ffmpegdemo.player.listener.MusicPlayListener;
import com.lanshifu.ffmpegdemo.player.listener.MusicPrepareListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_PERMISSION_SD_CODE = 10;
    private static final String TAG = "lxb";
    File mMusicFile = new File(Environment.getExternalStorageDirectory(), "input1.mp3");
    File mVideoFile = new File(Environment.getExternalStorageDirectory(), "input1.mp4");
    private VideoView mVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        requestPermission();

        mVideoView = findViewById(R.id.video_view);

        findViewById(R.id.btn_play_mp3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMp3();

            }
        });
        findViewById(R.id.btn_play_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

    }

    private void playVideo() {

        mVideoView.play(mVideoFile.getAbsolutePath());
    }

    private void playMp3() {
        final MusicPlayer musicPlayer = new MusicPlayer();
        musicPlayer.setDataSource(mMusicFile.getAbsolutePath());
        musicPlayer.setMusicPlayListener(new MusicPlayListener() {
            @Override
            public void onProgress(int progress) {
                Log.d(TAG, "onProgress: ");
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: ");

            }

            @Override
            public void onPlayStar() {
                Log.d(TAG, "onPlayStar: ");

            }

            @Override
            public void onPlayEnd() {
                Log.d(TAG, "onPlayEnd: ");

            }
        });
        musicPlayer.setMusicPrepareListener(new MusicPrepareListener() {
            @Override
            public void onPrepare() {
                Log.e(TAG, "onPrepare: ");
                musicPlayer.play();
            }
        });

        musicPlayer.prepareAsync();
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //当前系统大于等于6.0
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                afterCheckPermission();
            } else {
                //需要进行权限申请
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_SD_CODE);
            }
        } else {
            //当前系统小于6.0，直接调用拍照
            afterCheckPermission();
        }
    }

    private void afterCheckPermission() {



    }

}
