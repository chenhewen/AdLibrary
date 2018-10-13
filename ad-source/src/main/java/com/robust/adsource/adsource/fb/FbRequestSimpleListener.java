package com.robust.adsource.adsource.fb;

import com.facebook.ads.Ad;

/**
 * Created by chenhewen on 2018/8/27.
 */

public abstract class FbRequestSimpleListener<T extends Ad> implements FbRequestListener<T> {

    @Override
    public void onInterstitialDisplayed(T ad) {

    }

    @Override
    public void onInterstitialDismiss(T ad) {

    }

    @Override
    public void onMediaDownloaded(T ad) {

    }
}
