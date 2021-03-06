package com.robust.adsource.ad.event;

import com.robust.adsource.ad.UniAd;

/**
 * Created by chenhewen on 2018/7/14.
 */

public class OnAdClickEvent {

    private final UniAd mUniAd;
    private int mPosition;

    public OnAdClickEvent(int position, UniAd uniAd) {
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
