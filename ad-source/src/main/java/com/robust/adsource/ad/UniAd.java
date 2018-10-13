package com.robust.adsource.ad;

import com.facebook.ads.InterstitialAd;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeBannerAd;
import com.google.android.gms.ads.AdView;
import com.robust.adsource.adsource.AdType;

/**
 * 统一的广告数据结构，类型请参考 {@link AdType}
 */
public class UniAd {

    public UniAd(AdType adType, Object object) {
        mAdType = adType;
        if (mAdType == AdType.FB_NATIVE) {
            mFbNativeAd = (NativeAd) object;
        } else if (mAdType == AdType.FB_NATIVE_BANNER) {
            mFbNativeBannerAd = (NativeBannerAd) object;
        } else if (mAdType == AdType.FB_INTERSTITIAL) {
            mFbInterstitialAd = (InterstitialAd) object;
        } else if (AdType.isAdmobType(mAdType)) {
            if (mAdType == AdType.ADMOB_INTERSTITIAL) {
                mAdmobInterstitialAd = (com.google.android.gms.ads.InterstitialAd) object;
            } else {
                mAdmobAdView = (AdView) object;
            }
        }
    }

    private AdType mAdType;

    /**
     * fb 插屏广告
     */
    private com.facebook.ads.InterstitialAd mFbInterstitialAd;

    /**
     * fb native banner ad
     */
    private NativeBannerAd mFbNativeBannerAd;

    /**
     * fb native ad
     */
    private NativeAd mFbNativeAd;

    /**
     * Admob adView
     */
    private AdView mAdmobAdView;

    /**
     * Admob 插屏广告
     */
    private com.google.android.gms.ads.InterstitialAd mAdmobInterstitialAd;


    public AdType getAdType() {
        return mAdType;
    }

    public InterstitialAd getFbInterstitialAd() {
        return mFbInterstitialAd;
    }

    public NativeBannerAd getFbNativeBannerAd() {
        return mFbNativeBannerAd;
    }

    public NativeAd getFbNativeAd() {
        return mFbNativeAd;
    }

    public AdView getAdmobAdView() {
        return mAdmobAdView;
    }

    public com.google.android.gms.ads.InterstitialAd getAdmobInterstitialAd() {
        return mAdmobInterstitialAd;
    }
}
