package org.greenrobot.eventbus.util;

import android.os.Bundle;
import de.greenrobot.event.util.ErrorDialogManager;

/* compiled from: ErrorDialogFragmentFactory */
public abstract class b<T> {
    protected final a a;

    public abstract T a(d dVar, Bundle bundle);

    /* Access modifiers changed, original: protected */
    public T a(d dVar, boolean z, Bundle bundle) {
        if (dVar.a()) {
            return null;
        }
        if (bundle != null) {
            bundle = (Bundle) bundle.clone();
        } else {
            bundle = new Bundle();
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_TITLE)) {
            bundle.putString(ErrorDialogManager.KEY_TITLE, b(dVar, bundle));
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_MESSAGE)) {
            bundle.putString(ErrorDialogManager.KEY_MESSAGE, c(dVar, bundle));
        }
        if (!bundle.containsKey(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG)) {
            bundle.putBoolean(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG, z);
        }
        if (!(bundle.containsKey(ErrorDialogManager.KEY_EVENT_TYPE_ON_CLOSE) || this.a.i == null)) {
            bundle.putSerializable(ErrorDialogManager.KEY_EVENT_TYPE_ON_CLOSE, this.a.i);
        }
        if (!(bundle.containsKey(ErrorDialogManager.KEY_ICON_ID) || this.a.h == 0)) {
            bundle.putInt(ErrorDialogManager.KEY_ICON_ID, this.a.h);
        }
        return a(dVar, bundle);
    }

    /* Access modifiers changed, original: protected */
    public String b(d dVar, Bundle bundle) {
        return this.a.a.getString(this.a.b);
    }

    /* Access modifiers changed, original: protected */
    public String c(d dVar, Bundle bundle) {
        return this.a.a.getString(this.a.a(dVar.a));
    }
}
