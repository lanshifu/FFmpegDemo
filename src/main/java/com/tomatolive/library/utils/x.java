package com.tomatolive.library.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import java.util.List;

/* compiled from: ToolUtils */
public class x {
    public static boolean a(Context context) {
        List runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        if (!(runningTasks == null || runningTasks.isEmpty())) {
            ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
            if (componentName == null || componentName.getPackageName().equals(context.getPackageName())) {
                return false;
            }
            return true;
        }
        return false;
    }
}
