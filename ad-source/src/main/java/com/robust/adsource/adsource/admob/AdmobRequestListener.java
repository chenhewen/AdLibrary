package com.robust.adsource.adsource.admob;


public interface AdmobRequestListener<T> {
    void onAdLoaded(T ad);

    void onLoadFailed(int errorCode);

    void onAdShown();

    void onAdClick();
}
