package com.robust.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * 有关Log的工具类
 * Created by chenhewen on 2017/6/19.
 */

public class RobustLog {


    private static RobustLogInstance sRobustLogInstance;

    public static void init(Context context, String tag, int type) {
        sRobustLogInstance = new RobustLogInstance(context, tag, type);
    }

    public static void setDebug(boolean debug) {
        sRobustLogInstance.setDebug(debug);
    }

    public static void log(int log) {
        sRobustLogInstance.log(log);
    }

    public static void log(double log) {
        sRobustLogInstance.log(log);
    }

    public static void log(float log) {
        sRobustLogInstance.log(log);
    }

    public static void log(boolean log) {
        sRobustLogInstance.log(log);
    }

    public static void log(final String log) {
        sRobustLogInstance.log(log);
    }

    public static void logMethod() {
        sRobustLogInstance.logMethod();
    }

    public static void logMethodWith(int log) {
        sRobustLogInstance.logMethodWith(log);
    }

    public static void logMethodWith(float log) {
        sRobustLogInstance.logMethodWith(log);
    }

    public static void logMethodWith(double log) {
        sRobustLogInstance.logMethodWith(log);
    }

    public static void logMethodWith(boolean log) {
        sRobustLogInstance.logMethodWith(log);
    }

    public static void logMethodWith(CharSequence log) {
        sRobustLogInstance.logMethodWith(log);
    }
}
