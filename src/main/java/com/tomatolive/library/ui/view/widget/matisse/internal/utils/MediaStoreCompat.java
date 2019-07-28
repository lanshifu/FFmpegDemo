package com.tomatolive.library.ui.view.widget.matisse.internal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.os.EnvironmentCompat;
import com.tomatolive.library.ui.view.widget.matisse.internal.entity.CaptureStrategy;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MediaStoreCompat {
    private CaptureStrategy mCaptureStrategy;
    private final WeakReference<Activity> mContext;
    private String mCurrentPhotoPath;
    private Uri mCurrentPhotoUri;
    private final WeakReference<Fragment> mFragment;

    public MediaStoreCompat(Activity activity) {
        this.mContext = new WeakReference(activity);
        this.mFragment = null;
    }

    public MediaStoreCompat(Activity activity, Fragment fragment) {
        this.mContext = new WeakReference(activity);
        this.mFragment = new WeakReference(fragment);
    }

    public static boolean hasCameraFeature(Context context) {
        return context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public void setCaptureStrategy(CaptureStrategy captureStrategy) {
        this.mCaptureStrategy = captureStrategy;
    }

    public void dispatchCaptureIntent(Context context, int i) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            File file = null;
            try {
                file = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file != null) {
                this.mCurrentPhotoPath = file.getAbsolutePath();
                this.mCurrentPhotoUri = FileProvider.getUriForFile((Context) this.mContext.get(), this.mCaptureStrategy.authority, file);
                intent.putExtra("output", this.mCurrentPhotoUri);
                intent.addFlags(2);
                if (VERSION.SDK_INT < 21) {
                    for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
                        context.grantUriPermission(resolveInfo.activityInfo.packageName, this.mCurrentPhotoUri, 3);
                    }
                }
                if (this.mFragment != null) {
                    ((Fragment) this.mFragment.get()).startActivityForResult(intent, i);
                } else {
                    ((Activity) this.mContext.get()).startActivityForResult(intent, i);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        File externalStoragePublicDirectory;
        File file;
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        format = String.format("JPEG_%s.jpg", new Object[]{format});
        if (this.mCaptureStrategy.isPublic) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!externalStoragePublicDirectory.exists()) {
                externalStoragePublicDirectory.mkdirs();
            }
        } else {
            externalStoragePublicDirectory = ((Activity) this.mContext.get()).getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        if (this.mCaptureStrategy.directory != null) {
            file = new File(externalStoragePublicDirectory, this.mCaptureStrategy.directory);
            if (!file.exists()) {
                file.mkdirs();
            }
            externalStoragePublicDirectory = file;
        }
        file = new File(externalStoragePublicDirectory, format);
        return !"mounted".equals(EnvironmentCompat.getStorageState(file)) ? null : file;
    }

    public Uri getCurrentPhotoUri() {
        return this.mCurrentPhotoUri;
    }

    public String getCurrentPhotoPath() {
        return this.mCurrentPhotoPath;
    }
}
