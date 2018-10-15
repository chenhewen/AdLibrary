package com.robust.adsource.adsource.fb;

import com.facebook.ads.Ad;

/**
 * Created by chenhewen on 2018/8/21.
 */

public interface FbRequestListener<T extends Ad> {

    void onAdLoaded(T ad);

    void onLoadFailed(int errorCode, String errorMsg);

    void onAdShow();

    void onAdClicked(T ad);

    void onMediaDownloaded(T ad);

    void onInterstitialDisplayed(T ad);

    void onInterstitialDismiss(T ad);
}
