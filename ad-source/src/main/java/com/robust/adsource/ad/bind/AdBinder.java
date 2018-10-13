package com.robust.adsource.ad.bind;

import android.content.Context;

import com.robust.adsource.ModuleContext;

/**
 * Created by chenhewen on 2018/7/14.
 */

public abstract class AdBinder {

    private final Context mContext;

    public AdBinder() {
        mContext = ModuleContext.getContext();
    }

    public Context getContext() {
        return mContext;
    }
}
