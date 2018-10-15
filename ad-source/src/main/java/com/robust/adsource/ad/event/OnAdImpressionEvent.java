package com.robust.adsource.ad.event;

import com.robust.adsource.ad.UniAd;

public class OnAdImpressionEvent {
    private final UniAd mUniAd;
    private int mPosition;

    public OnAdImpressionEvent(int position, UniAd uniAd) {
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
