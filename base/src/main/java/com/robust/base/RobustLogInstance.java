package com.robust.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class RobustLogInstance {

    private static String mTag = "Robust";

    private Context mAppContext;

    private int mType = LogType.SHOW_TYPE_LOG;
    private boolean mDebug;
    private long mMainThreadId;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public RobustLogInstance(Context context, String tag, int type) {
        mAppContext = context;
        mTag = tag;
        mType = type;
        mMainThreadId = Thread.currentThread().getId();
    }

    public void setDebug(boolean debug) {
        mDebug = debug;
    }

    public void log(int log) {
        log(String.valueOf(log));
    }

    public void log(double log) {
        log(String.valueOf(log));
    }

    public void log(float log) {
        log(String.valueOf(log));
    }

    public void log(boolean log) {
        log(String.valueOf(log));
    }

    public void log(final String log) {

        if (!mDebug) {
            return;
        }

        if (log == null) {
            throw new IllegalArgumentException("log should not be null");
        }


        if ((mType & LogType.SHOW_TYPE_LOG) != 0) {
            Log.i(mTag, log);
        }
        if ((mType & LogType.SHOW_TYPE_TOAST) != 0) {
            long curThreadId = Thread.currentThread().getId();
            if (curThreadId == mMainThreadId) {
                Toast.makeText(mAppContext, log, Toast.LENGTH_SHORT).show();
            } else {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mAppContext, log, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    public void logMethod() {
        logMethodWithPrivate("", "logMethod");
    }

    public void logMethodWith(int log) {
        logMethodWithPrivate(String.valueOf(log), "logMethodWith");
    }

    public void logMethodWith(float log) {
        logMethodWithPrivate(String.valueOf(log), "logMethodWith");
    }

    public void logMethodWith(double log) {
        logMethodWithPrivate(String.valueOf(log), "logMethodWith");
    }

    public void logMethodWith(boolean log) {
        logMethodWithPrivate(String.valueOf(log), "logMethodWith");
    }

    public void logMethodWith(CharSequence log) {
        logMethodWithPrivate(String.valueOf(log), "logMethodWith");
    }

    private void logMethodWithPrivate(String log, String methode) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length > 0) {
            for (int i = 0; i < stackTrace.length; i++) {
                String methodName = stackTrace[i].getMethodName();
                if (TextUtils.equals(methodName, methode)) {
                    StackTraceElement next = stackTrace[i + 1];
                    if (next != null && next.getMethodName() != null) {
                        log(next.getClassName() + "# [" + next.getMethodName() + "] " + log);
                    }
                }
            }
        } else {
            if (mDebug) {
                throw new RuntimeException("log method failed");
            }
        }
    }
}
