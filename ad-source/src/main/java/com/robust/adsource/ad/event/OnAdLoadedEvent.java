package com.robust.adsource.ad.event;

import com.robust.adsource.ad.UniAd;

/**
 * Created by chenhewen on 2018/8/27.
 */

public class OnAdLoadedEvent {

    private int mPosition;

    private UniAd mUniAd;

    public OnAdLoadedEvent(int position, UniAd uniAd) {
        mPosition = position;
        mUniAd = uniAd;
    }

    public int getPosition() {
        return mPosition;
    }

    public UniAd getUniAd() {
        return mUniAd;
    }
}
