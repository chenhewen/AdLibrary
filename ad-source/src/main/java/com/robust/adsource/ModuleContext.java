package com.robust.adsource;

import android.content.Context;

import com.robust.adsource.adsource.id.IAdSourceId;
import com.robust.base.LogType;
import com.robust.base.RobustLogInstance;

/**
 * Created by chenhewen on 2018/8/26.
 */

public class ModuleContext {

    private static Context sContext;
    private static boolean sDebug;
    private static IAdSourceId sIAdSourceId;
    private static RobustLogInstance sRobustLogInstance;

    public static void init(Context context, boolean debug) {
        sContext = context;
        sDebug = debug;

        sRobustLogInstance = new RobustLogInstance(context, "ad-source", LogType.SHOW_TYPE_LOG | LogType.SHOW_TYPE_TOAST);
        sRobustLogInstance.setDebug(debug);
    }

    public static void setAdSourceId(IAdSourceId iAdSourceId) {
        if (iAdSourceId == null) {
            throw new IllegalArgumentException("IAdSourceId should not be null");
        }
        sIAdSourceId = iAdSourceId;
    }

    public static IAdSourceId getIAdSourceId() {
        return sIAdSourceId;
    }

    public static Context getContext() {
        return sContext;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static RobustLogInstance getLogger() {
        return sRobustLogInstance;
    }
}
