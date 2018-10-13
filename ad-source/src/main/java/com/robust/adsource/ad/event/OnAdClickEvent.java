package com.robust.adsource.ad.event;

/**
 * Created by chenhewen on 2018/7/14.
 */

public class OnAdClickEvent {

    private int mPosition;

    public OnAdClickEvent(int position) {
        mPosition = position;
    }

    public int getPosition() {
        return mPosition;
    }
}
