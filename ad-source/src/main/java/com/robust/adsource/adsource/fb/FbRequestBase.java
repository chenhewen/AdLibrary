package com.robust.adsource.adsource.fb;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdListener;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.report.AdReport;

/**
 * Created by chenhewen on 2018/8/21.
 */

public abstract class FbRequestBase<T extends Ad> implements IAdRequest{

    private FbRequestListener<T> mFbRequestListener;

    public void setListener(FbRequestListener<T> fbRequestListener) {
        mFbRequestListener = fbRequestListener;
    }

    public UniListener createListener(final int position, final AdType adType) {
        return new UniListener(position, adType);
    }

    private class UniListener implements NativeAdListener, InterstitialAdListener {

        private int mPosition;

        private AdType mAdType;

        public UniListener(int position, AdType adType) {
            mPosition = position;
            mAdType = adType;
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onAdLoaded(Ad ad) {
            // Native ad is loaded and ready to be displayed
            if (mFbRequestListener != null) {
                mFbRequestListener.onAdLoaded((T) ad);
                // 统计
                AdReport.adFill(mPosition, mAdType);
            }
        }

        @Override
        public void onError(Ad ad, AdError adError) {
            // Native ad failed to load
            if (mFbRequestListener != null) {
                int errorCode = adError.getErrorCode();
                String errorMessage = adError.getErrorMessage();
                mFbRequestListener.onLoadFailed(errorCode, errorMessage);
                // 统计
                AdReport.adFail(mPosition, mAdType, errorCode);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onAdClicked(Ad ad) {
            // Native ad clicked
            if (mFbRequestListener != null) {
                mFbRequestListener.onAdClicked((T) ad);
                // 统计
                AdReport.adClick(mPosition, mAdType);
            }
        }

        @Override
        public void onLoggingImpression(Ad ad) {
            // Native ad impression
            if (mFbRequestListener != null) {
                mFbRequestListener.onAdShow();
                // 统计
                AdReport.adImpression(mPosition, mAdType);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onMediaDownloaded(Ad ad) {
            // Native ad finished downloading all assets
            if (mFbRequestListener != null) {
                mFbRequestListener.onMediaDownloaded((T) ad);
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onInterstitialDisplayed(Ad ad) {
//            if (mFbRequestListener != null) {
//                mFbRequestListener.onInterstitialDisplayed((T) ad);
//                // 统计
//                AdReport.adImpression(mPosition, mAdType);
//            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void onInterstitialDismissed(Ad ad) {
            if (mFbRequestListener != null) {
                mFbRequestListener.onInterstitialDismiss((T) ad);
            }
        }
    }
}
