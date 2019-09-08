package com.lanshifu.ffmpegdemo.push_live;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lanshifu.ffmpegdemo.R;
import com.lanshifu.ffmpegdemo.camera.widget.CameraFocusView;
import com.lanshifu.ffmpegdemo.camera.widget.CameraView;
import com.lanshifu.ffmpegdemo.utils.ShaderManager;

public class LivePushActivity extends AppCompatActivity {

    private CameraView mCameraView;
    private CameraFocusView mFocusView;
    private BaseVideoPush mVideoPush;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livepush);

        mCameraView = findViewById(R.id.camera_view);
        mFocusView = findViewById(R.id.camera_focus_view);
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

        findViewById(R.id.live_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLivePush();
            }
        });
    }

    private void startLivePush() {
        if (mVideoPush == null) {
            Log.e("TAG", "开始推流");
            mVideoPush = new DefaultVideoPush(LivePushActivity.this,
                    mCameraView.getEglContext(), mCameraView.getTextureId());

            mVideoPush.initVideo(720, 1280);

            mVideoPush.setOnConnectListener(new LivePushHandle.ConnectListener() {
                @Override
                public void connectError(int errorCode, String errorMsg) {
                    Log.e("TAG", "errorCode:" + errorCode);
                    Log.e("TAG", "errorMsg:" + errorMsg);
                }

                @Override
                public void connectSuccess() {
                    Log.e("TAG", "connectSuccess");
                }

                @Override
                public void onInfo(long pts, long dts, long duration, long index) {

                }
            });
            mVideoPush.startPush();
        } else {
            mVideoPush.stopPush();
            mVideoPush = null;
        }
    }

    @Override
    protected void onDestroy() {
        if (mVideoPush != null) {
            mVideoPush.stopPush();
        }
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.mDefault:
                mCameraView.setFilter(ShaderManager.CAMERA_BASE_SHADER);
                break;
            case R.id.mGray:
                mCameraView.setFilter(ShaderManager.CAMERA_GRAY_SHADER);
                break;
            case R.id.mCool:
                mCameraView.setFilter(ShaderManager.CAMERA_COOL_SHADER);
                break;
            case R.id.mWarm:
                mCameraView.setFilter(ShaderManager.CAMERA_WARM_SHADER);
                break;
            case R.id.mBlur:
                mCameraView.setFilter(ShaderManager.CAMERA_BUZZ_BEAUTY);
                break;
            case R.id.mFour:
                mCameraView.setFilter(ShaderManager.CAMERA_FOUR_SHADER);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
