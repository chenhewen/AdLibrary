package com.robust.adsource.ad.event;

/**
 * Created by chenhewen on 2018/7/14.
 */

public class OnAdErrorEvent {

    private int mPosition;

    private int mErrorCode;

    private String mDesc;

    public OnAdErrorEvent(int position, int errorCode, String desc) {
        mPosition = position;
        mErrorCode = errorCode;
        mDesc = desc;
    }

    public int getPosition() {
        return mPosition;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public String getDesc() {
        return mDesc;
    }
}
