package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import org.greenrobot.eventbus.c;

public class ErrorDialogManager {
    public static b<?> a;

    @TargetApi(11)
    public static class HoneycombManagerFragment extends Fragment {
        private c a;

        public void onResume() {
            super.onResume();
            this.a = ErrorDialogManager.a.a.a();
            this.a.a((Object) this);
        }

        public void onPause() {
            this.a.b(this);
            super.onPause();
        }
    }

    public static class SupportManagerFragment extends android.support.v4.app.Fragment {
        protected boolean a;
        protected Bundle b;
        private c c;
        private boolean d;
        private Object e;

        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.c = ErrorDialogManager.a.a.a();
            this.c.a((Object) this);
            this.d = true;
        }

        public void onResume() {
            super.onResume();
            if (this.d) {
                this.d = false;
                return;
            }
            this.c = ErrorDialogManager.a.a.a();
            this.c.a((Object) this);
        }

        public void onPause() {
            this.c.b(this);
            super.onPause();
        }

        public void onEventMainThread(d dVar) {
            if (ErrorDialogManager.b(this.e, dVar)) {
                ErrorDialogManager.a(dVar);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.executePendingTransactions();
                DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                DialogFragment dialogFragment2 = (DialogFragment) ErrorDialogManager.a.a(dVar, this.a, this.b);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, "de.greenrobot.eventbus.error_dialog");
                }
            }
        }
    }

    protected static void a(d dVar) {
        if (a.a.f) {
            String str = a.a.g;
            if (str == null) {
                str = c.a;
            }
            Log.i(str, "Error dialog manager received exception", dVar.a);
        }
    }

    private static boolean b(Object obj, d dVar) {
        if (dVar != null) {
            Object b = dVar.b();
            if (!(b == null || b.equals(obj))) {
                return false;
            }
        }
        return true;
    }
}
