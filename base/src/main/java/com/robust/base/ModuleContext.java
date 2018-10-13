package com.robust.base;

import android.content.Context;

/**
 * Created by chenhewen on 2018/8/26.
 */

public class ModuleContext {

    private static Context sContext;

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }
}
