package com.lanshifu.ffmpegdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        findViewById(R.id.btn_camera).setOnClickListener(this);
        findViewById(R.id.btn_player).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_player:
                startActivity(new Intent(SelectActivity.this,MainActivity.class));
                break;

            case R.id.btn_camera:
                startActivity(new Intent(SelectActivity.this,CameraActivity.class));
                break;


        }

    }
}
