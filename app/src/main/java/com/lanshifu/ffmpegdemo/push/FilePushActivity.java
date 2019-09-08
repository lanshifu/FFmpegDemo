package com.lanshifu.ffmpegdemo.push;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lanshifu.ffmpegdemo.R;

public class FilePushActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PushActivity";

    TextView tvPushInfo;

    PushHandle mPushHandle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_push);
        findViewById(R.id.btn_file_push).setOnClickListener(this);

        tvPushInfo = findViewById(R.id.tvPushInfo);

    }

    private void filePush() {
        new Thread() {
            @Override
            public void run() {
                filePushRunable();

            }
        }.start();


    }

    private void filePushRunable() {
        mPushHandle = new PushHandle();
        mPushHandle.setCallback(new PushCallback() {
            @Override
            public void onInfo(final long pts, final long dts, final long duration, final long index) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        sb.append("pts: ").append(pts).append("\n");
                        sb.append("dts: ").append(dts).append("\n");
                        sb.append("duration: ").append(duration).append("\n");
                        sb.append("index: ").append(index).append("\n");
                        tvPushInfo.setText(sb.toString());
                    }
                });

            }

            @Override
            public void onError(final int code, final String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvPushInfo.setText("失败，code=" + code + ",msg=" + msg);
                    }
                });
            }

            @Override
            public void onPushComplete() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvPushInfo.setText("推流完成");
                    }
                });

            }
        });

        String videoPath = "/sdcard/input1.mp4";
        mPushHandle.nPushRtmpFile(videoPath, "rtmp://192.168.43.144:1935/test/live");
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_file_push:
                filePush();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPushHandle != null) {
//            mPushHandle.nStopPush();
        }
    }
}
