package com.tomatolive.library.ui.view.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.LoadingView;

public class LiveLoadingView extends RelativeLayout implements OnClickListener {
    public static final int CHANGE_LINE_TYPE_1 = 1;
    public static final int CHANGE_LINE_TYPE_2 = 2;
    public static final int CHANGE_LINE_TYPE_3 = 3;
    public static final int RELOAD_TYPE_ROOM = 1;
    public static final int RELOAD_TYPE_VIDEO = 2;
    private LinearLayout llContentBg;
    private LinearLayout llLineReloadBg;
    private LinearLayout llReloadPartBg;
    private LoadingView loadingView;
    private OnLiveLoadingListener onLiveLoadingListener;
    private int reloadType = 1;
    private TextView tvFailTips;

    public interface OnLiveLoadingListener {
        void onChangeLineClickListener(int i);

        void onReloadClickListener(int i);
    }

    public LiveLoadingView(Context context) {
        super(context);
        initView(context);
    }

    public LiveLoadingView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.fq_layout_live_loading_view, this);
        this.loadingView = (LoadingView) findViewById(R.id.iv_play_loading);
        this.tvFailTips = (TextView) findViewById(R.id.tv_fail_tips);
        this.llContentBg = (LinearLayout) findViewById(R.id.ll_content_bg);
        this.llReloadPartBg = (LinearLayout) findViewById(R.id.ll_reload_part_bg);
        this.llLineReloadBg = (LinearLayout) findViewById(R.id.ll_line_reload_bg);
        findViewById(R.id.tv_reload_btn).setOnClickListener(this);
        findViewById(R.id.tv_line_1).setOnClickListener(this);
        findViewById(R.id.tv_line_2).setOnClickListener(this);
        findViewById(R.id.tv_line_3).setOnClickListener(this);
    }

    public void setOnLiveLoadingListener(OnLiveLoadingListener onLiveLoadingListener) {
        this.onLiveLoadingListener = onLiveLoadingListener;
    }

    public void showLoadingView() {
        this.loadingView.setVisibility(0);
        this.llContentBg.setVisibility(4);
        this.loadingView.showLoading();
    }

    public void showReloadView(int i) {
        this.reloadType = i;
        stopLoadingViewAnimation();
        int i2 = 0;
        this.llContentBg.setVisibility(0);
        this.llReloadPartBg.setVisibility(0);
        LinearLayout linearLayout = this.llLineReloadBg;
        if (i != 2) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
        this.tvFailTips.setText(i == 1 ? R.string.fq_room_info_fail : R.string.fq_video_load_fail);
    }

    public void showReloadLineView() {
        this.loadingView.setVisibility(4);
        this.llContentBg.setVisibility(0);
        this.llReloadPartBg.setVisibility(0);
        this.llLineReloadBg.setVisibility(0);
    }

    public void stopLoadingViewAnimation() {
        this.loadingView.setVisibility(4);
        this.loadingView.stopLoading();
    }

    public void onDestroy() {
        this.loadingView.release();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tv_reload_btn) {
            if (this.onLiveLoadingListener != null) {
                this.onLiveLoadingListener.onReloadClickListener(this.reloadType);
            }
        } else if (view.getId() == R.id.tv_line_1) {
            if (this.onLiveLoadingListener != null) {
                this.onLiveLoadingListener.onChangeLineClickListener(1);
            }
        } else if (view.getId() == R.id.tv_line_2) {
            if (this.onLiveLoadingListener != null) {
                this.onLiveLoadingListener.onChangeLineClickListener(2);
            }
        } else if (view.getId() == R.id.tv_line_3 && this.onLiveLoadingListener != null) {
            this.onLiveLoadingListener.onChangeLineClickListener(3);
        }
    }
}
