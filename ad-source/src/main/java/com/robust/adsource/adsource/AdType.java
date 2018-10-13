package com.robust.adsource.adsource;

/**
 * Admob, fb 广告类型
 * https://developers.facebook.com/docs/audience-network/android-native
 * https://developers.google.com/admob/android/banner?hl=zh-CN
 * Created by chenhewen on 2018/8/17.
 */

public enum AdType {

    ADMOB_BANNER,
    ADMOB_LARGE_BANNER,
    ADMOB_MEDIUM_RECTANGLE,
    ADMOB_FULL_BANNER,
    ADMOB_LEADERBOARD,
    ADMOB_SMART_BANNER,
    ADMOB_INTERSTITIAL,


    FB_NATIVE,
    FB_NATIVE_BANNER,
    FB_INTERSTITIAL;

    public static boolean isAdmobType(AdType adType) {
        return adType == AdType.ADMOB_BANNER
                || adType == AdType.ADMOB_LARGE_BANNER
                || adType == AdType.ADMOB_MEDIUM_RECTANGLE
                || adType == AdType.ADMOB_FULL_BANNER
                || adType == AdType.ADMOB_LEADERBOARD
                || adType == AdType.ADMOB_SMART_BANNER
                || adType == AdType.ADMOB_INTERSTITIAL;
    }

    public static boolean isFbType(AdType adType) {
        return adType == AdType.FB_NATIVE
                || adType == AdType.FB_NATIVE_BANNER
                || adType == AdType.FB_INTERSTITIAL;
    }
}
