package com.robust.adsource.ad;

/**
 * Created by chenhewen on 2018/8/17.
 */

public interface AdListener {

    String NO_ERROR_STR = "";

    void onLoadSuccess(Object object);

    void onLoadFailed(int errorCode, String errorStr);

    void onClicked();
}
