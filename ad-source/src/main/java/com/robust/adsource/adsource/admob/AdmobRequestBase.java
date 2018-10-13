package com.robust.adsource.adsource.admob;

import com.google.android.gms.ads.AdListener;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.report.AdReport;

public abstract class AdmobRequestBase<T> implements IAdRequest {


    private AdmobRequestListener<T> mAdmobBannerRequestListener;

    public void setListener(AdmobRequestListener<T> admobBannerRequestListener) {
        mAdmobBannerRequestListener = admobBannerRequestListener;
    }

    protected AdmobRequestListener<T> getAdmobBannerRequestListener() {
        return mAdmobBannerRequestListener;
    }

    protected AdListener createListener(int position, AdType adType, Object ad) {
        return new AdmobUniListener(position, adType, ad);
    }

    private class AdmobUniListener extends AdListener {

        private int mPosition;

        private AdType mAdType;

        private Object mAd;

        public AdmobUniListener(int position, AdType adType, Object ad) {
            mPosition = position;
            mAdType = adType;
            mAd = ad;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onAdLoaded() {
            if (getAdmobBannerRequestListener() != null) {
                getAdmobBannerRequestListener().onAdLoaded((T) mAd);
                // 统计
                AdReport.adFill(mPosition, mAdType);
            }
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            if (getAdmobBannerRequestListener() != null) {
                getAdmobBannerRequestListener().onLoadFailed(errorCode);
                // 统计
                AdReport.adFail(mPosition, mAdType, errorCode);
            }
        }

        @Override
        public void onAdOpened() {
            // Code to be executed when the ad is displayed.
            if (getAdmobBannerRequestListener() != null) {
                getAdmobBannerRequestListener().onAdShown();
                // 统计
                AdReport.adImpression(mPosition, mAdType);
            }
        }

        @Override
        public void onAdLeftApplication() {
            if (getAdmobBannerRequestListener() != null) {
                getAdmobBannerRequestListener().onAdClick();
                // 统计
                AdReport.adClick(mPosition, mAdType);
            }
        }

        @Override
        public void onAdClosed() {
            // Code to be executed when when the interstitial ad is closed.
        }
    }
}
