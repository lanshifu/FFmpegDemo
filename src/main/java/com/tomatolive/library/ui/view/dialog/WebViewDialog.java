package com.tomatolive.library.ui.view.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class WebViewDialog extends BaseBottomDialogFragment {
    private FrameLayout flRetryView;
    private boolean isLoadError = false;
    private ProgressBar progressBar;
    private WebView wvContent;

    public float getDimAmount() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    @SuppressLint({"ValidFragment"})
    private WebViewDialog() {
    }

    public static WebViewDialog newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        WebViewDialog webViewDialog = new WebViewDialog();
        bundle.putString("title", str);
        bundle.putString(OnNativeInvokeListener.ARG_URL, str2);
        webViewDialog.setArguments(bundle);
        return webViewDialog;
    }

    public int getLayoutRes() {
        return R.layout.fq_dialog_bottom_webview;
    }

    public void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        this.wvContent = (WebView) view.findViewById(R.id.wv_content);
        this.progressBar = (ProgressBar) view.findViewById(R.id.pb);
        this.flRetryView = (FrameLayout) view.findViewById(R.id.fl_retry_view);
        String argumentsString = getArgumentsString("title");
        final String argumentsString2 = getArgumentsString(OnNativeInvokeListener.ARG_URL);
        textView.setText(argumentsString);
        initWebView();
        this.wvContent.loadUrl(argumentsString2);
        view.findViewById(R.id.tv_btn_reload).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WebViewDialog.this.wvContent.loadUrl(argumentsString2);
            }
        });
    }

    public double getHeightScale() {
        return this.maxHeightScale;
    }

    private void initWebView() {
        this.wvContent.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.wvContent.getSettings().setJavaScriptEnabled(true);
        this.wvContent.getSettings().setSupportZoom(false);
        this.wvContent.getSettings().setBuiltInZoomControls(false);
        this.wvContent.getSettings().setUseWideViewPort(true);
        this.wvContent.getSettings().setLoadWithOverviewMode(true);
        this.wvContent.getSettings().setAppCacheEnabled(false);
        this.wvContent.getSettings().setDomStorageEnabled(true);
        this.wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("pt://")) {
                    try {
                        WebViewDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                        WebViewDialog.this.dismiss();
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
                WebViewDialog.this.isLoadError = true;
                WebViewDialog.this.initContentView(false);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (!WebViewDialog.this.isLoadError) {
                    WebViewDialog.this.initContentView(true);
                }
            }
        });
        this.wvContent.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                try {
                    WebViewDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.wvContent.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i == 100) {
                    WebViewDialog.this.progressBar.setVisibility(8);
                } else {
                    if (WebViewDialog.this.progressBar.getVisibility() == 8) {
                        WebViewDialog.this.progressBar.setVisibility(0);
                    }
                    WebViewDialog.this.progressBar.setProgress(i);
                }
                super.onProgressChanged(webView, i);
            }
        });
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
