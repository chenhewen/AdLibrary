package com.robust.analysis;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.facebook.appevents.AppEventsLogger;

/**
 * User flurry to send reports
 * Created by chenhewen on 2017/10/9.
 */

public class FacebookReport {

    private static final String CATEGORY = "category";
    private static final String ENTRY = "entry";
    private static final String DESC = "desc";
    private static final String ARG0 = "arg0";
    private static final String ARG1 = "arg1";
    private static final String ARG2 = "arg2";

    private static final boolean DEBUG = false;

    public static void reportEvent(String operation) {

        if (DEBUG) {
            return;
        }

        if (TextUtils.isEmpty(operation)) {
            throw new IllegalArgumentException("event operation should not be null");
        }

        Context context = ModuleContext.getContext();
        AppEventsLogger appEventsLogger = AppEventsLogger.newLogger(context);
        appEventsLogger.logEvent(operation);
    }

    public static void reportEvent(String operation, String entry) {
        reportEvent(operation, entry, null, null, null, null, null);
    }

    public static void reportEvent(String operation, String entry, String category) {
        reportEvent(operation, entry, category, null, null, null, null);
    }

    public static void reportEvent(String operation, String entry, String category, String desc) {
        reportEvent(operation, entry, category, desc, null, null, null);
    }

    public static void reportEvent(String operation, String entry, String category, String desc, String arg0, String arg1, String arg2) {

        if (DEBUG) {
            return;
        }

        if (TextUtils.isEmpty(operation)) {
            throw new IllegalArgumentException("event operation should not be null");
        }

        Bundle bundle = new Bundle();
        bundle.putString(ENTRY, getEmptyStrWhenNull(entry));
        bundle.putString(CATEGORY, getEmptyStrWhenNull(category));
        bundle.putString(DESC, getEmptyStrWhenNull(desc));
        bundle.putString(ARG0, getEmptyStrWhenNull(arg0));
        bundle.putString(ARG1, getEmptyStrWhenNull(arg1));
        bundle.putString(ARG2, getEmptyStrWhenNull(arg2));

        Context context = ModuleContext.getContext();
        AppEventsLogger appEventsLogger = AppEventsLogger.newLogger(context);
        appEventsLogger.logEvent(operation, bundle);
    }

    private static String getEmptyStrWhenNull(String str) {
        return str == null ? "" : str;
    }
}
