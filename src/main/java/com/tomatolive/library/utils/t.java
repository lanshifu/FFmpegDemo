package com.tomatolive.library.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.IBinder;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

/* compiled from: SoftKeyboardUtils */
public class t {
    public static void a(Activity activity) {
        t tVar = new t(activity, null);
    }

    public static void b(Activity activity) {
        if (activity != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            }
        }
    }

    public static void a(View view) {
        if (view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
        }
    }

    public static void a(Dialog dialog) {
        if (dialog != null) {
            View currentFocus = dialog.getCurrentFocus();
            if (currentFocus != null) {
                ((InputMethodManager) dialog.getContext().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            }
        }
    }

    private t(final Activity activity, ViewGroup viewGroup) {
        if (viewGroup == null) {
            viewGroup = (ViewGroup) activity.findViewById(16908290);
        }
        a(viewGroup, activity);
        viewGroup.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                t.this.a(activity, motionEvent);
                return false;
            }
        });
    }

    private void a(ViewGroup viewGroup, final Activity activity) {
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ScrollView) {
                    ((ScrollView) childAt).setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            t.this.a(activity, motionEvent);
                            return false;
                        }
                    });
                } else if (childAt instanceof AbsListView) {
                    ((AbsListView) childAt).setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            t.this.a(activity, motionEvent);
                            return false;
                        }
                    });
                } else if (childAt instanceof RecyclerView) {
                    ((RecyclerView) childAt).setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            t.this.a(activity, motionEvent);
                            return false;
                        }
                    });
                } else if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt, activity);
                }
                if (childAt.isClickable() && (childAt instanceof TextView) && !(childAt instanceof EditText)) {
                    childAt.setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            t.this.a(activity, motionEvent);
                            return false;
                        }
                    });
                }
            }
        }
    }

    public boolean a(Activity activity, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus != null && a(currentFocus, motionEvent)) {
                a(activity, currentFocus.getWindowToken());
            }
        }
        return false;
    }

    private boolean a(View view, MotionEvent motionEvent) {
        if (view instanceof EditText) {
            Rect rect = new Rect();
            view.getHitRect(rect);
            if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return true;
    }

    private void a(Activity activity, IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }
}
