package com.tomatolive.library.ui.activity.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.tomatolive.library.R;
import com.tomatolive.library.base.BaseActivity;
import com.tomatolive.library.base.a;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class WebViewActivity extends BaseActivity {
    private FrameLayout flRetryView;
    private volatile boolean isLoadError = false;
    private ProgressBar mPb;
    private WebView wvContent;

    /* Access modifiers changed, original: protected */
    public a createPresenter() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public int getLayoutId() {
        return R.layout.fq_activity_web_view;
    }

    public void initView(Bundle bundle) {
        String stringExtra = getIntent().getStringExtra(OnNativeInvokeListener.ARG_URL);
        setActivityTitle(getIntent().getStringExtra("title"));
        this.wvContent = (WebView) findViewById(R.id.wv_content);
        this.mPb = (ProgressBar) findViewById(R.id.pb);
        this.flRetryView = (FrameLayout) findViewById(R.id.fl_retry_view);
        initWebView();
        this.wvContent.loadUrl(stringExtra);
        findViewById(R.id.tv_btn_reload).setOnClickListener(new -$$Lambda$WebViewActivity$gQxCVCAZuHzCNohC56OWZUFjLBc(this, stringExtra));
    }

    public void initListener() {
        super.initListener();
        this.wvContent.setOnKeyListener(new -$$Lambda$WebViewActivity$z_VrxY6zVRrGTp2-mix7VhIIMOY(this));
    }

    public static /* synthetic */ boolean lambda$initListener$1(WebViewActivity webViewActivity, View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4 || !webViewActivity.wvContent.canGoBack()) {
            return false;
        }
        webViewActivity.wvContent.goBack();
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        setResult(-1);
    }

    private void initWebView() {
        this.wvContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.wvContent.getSettings().setJavaScriptEnabled(true);
        this.wvContent.getSettings().setSupportZoom(false);
        this.wvContent.getSettings().setBuiltInZoomControls(true);
        this.wvContent.getSettings().setUseWideViewPort(true);
        this.wvContent.getSettings().setLoadWithOverviewMode(true);
        this.wvContent.getSettings().setAppCacheEnabled(true);
        this.wvContent.getSettings().setDomStorageEnabled(true);
        this.wvContent.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    WebViewActivity.this.mPb.setVisibility(8);
                } else {
                    if (WebViewActivity.this.mPb.getVisibility() == 8) {
                        WebViewActivity.this.mPb.setVisibility(0);
                    }
                    WebViewActivity.this.mPb.setProgress(i);
                }
                super.onProgressChanged(webView, i);
            }
        });
        this.wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("pt://")) {
                    try {
                        WebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    } catch (Exception unused) {
                        webView.loadUrl("https://www.potato.im/p/androids");
                    }
                    return false;
                }
                webView.loadUrl(str);
                return true;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                WebViewActivity.this.isLoadError = true;
                WebViewActivity.this.initContentView(false);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (!WebViewActivity.this.isLoadError) {
                    WebViewActivity.this.initContentView(true);
                }
            }
        });
        this.wvContent.setDownloadListener(new -$$Lambda$WebViewActivity$TAqA-QuW7_geIjwNIcENzKkoVxk(this));
    }

    public static /* synthetic */ void lambda$initWebView$2(WebViewActivity webViewActivity, String str, String str2, String str3, String str4, long j) {
        try {
            webViewActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initContentView(boolean z) {
        int i = 0;
        this.flRetryView.setVisibility(z ? 4 : 0);
        WebView webView = this.wvContent;
        if (!z) {
            i = 4;
        }
        webView.setVisibility(i);
    }
}
