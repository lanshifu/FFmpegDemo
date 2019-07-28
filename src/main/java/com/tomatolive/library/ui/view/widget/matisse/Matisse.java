package com.tomatolive.library.ui.view.widget.matisse;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.tomatolive.library.ui.view.widget.matisse.ui.MatisseActivity;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

public final class Matisse {
    private final WeakReference<Activity> mContext;
    private final WeakReference<Fragment> mFragment;

    private Matisse(Activity activity) {
        this(activity, null);
    }

    private Matisse(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private Matisse(Activity activity, Fragment fragment) {
        this.mContext = new WeakReference(activity);
        this.mFragment = new WeakReference(fragment);
    }

    public static Matisse from(Activity activity) {
        return new Matisse(activity);
    }

    public static Matisse from(Fragment fragment) {
        return new Matisse(fragment);
    }

    public static List<Uri> obtainResult(Intent intent) {
        return intent.getParcelableArrayListExtra(MatisseActivity.EXTRA_RESULT_SELECTION);
    }

    public static List<String> obtainPathResult(Intent intent) {
        return intent.getStringArrayListExtra(MatisseActivity.EXTRA_RESULT_SELECTION_PATH);
    }

    public static boolean obtainOriginalState(Intent intent) {
        return intent.getBooleanExtra("extra_result_original_enable", false);
    }

    public SelectionCreator choose(Set<MimeType> set) {
        return choose(set, true);
    }

    public SelectionCreator choose(Set<MimeType> set, boolean z) {
        return new SelectionCreator(this, set, z);
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public Activity getActivity() {
        return (Activity) this.mContext.get();
    }

    /* Access modifiers changed, original: 0000 */
    @Nullable
    public Fragment getFragment() {
        return this.mFragment != null ? (Fragment) this.mFragment.get() : null;
    }
}
