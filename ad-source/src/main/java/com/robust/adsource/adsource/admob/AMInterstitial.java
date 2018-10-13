package com.robust.adsource.adsource.admob;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.report.AdReport;
import com.robust.base.RobustLog;

/**
 * admob 插屏广告中转类，套一下接口中转下而已
 * 方便扩展，避免往后admob SDK改接口要改很多地方
 */
public class AMInterstitial {
    private InterstitialAd mInterstitialAd;
    private Context mContext;

    private AMInterstitialListener mAdListener;

    // 自己app定义的id
    private int mPositionId;

    public AMInterstitial(Context context, String adId, int positionId) {
        mContext = context;
        mPositionId = positionId;
        mInterstitialAd = new InterstitialAd(mContext);
        mInterstitialAd.setAdUnitId(adId);
    }

    public void requestAd(AMInterstitialListener listener) {
        if (listener == null) {
            throw new RuntimeException("adListener can't be null");
        }

        mAdListener = listener;

        final int position = mPositionId;
        final AdType adType = AdType.ADMOB_INTERSTITIAL;
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mAdListener.onAdLoaded();
                // 统计
                AdReport.adFill(position, adType);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                mAdListener.onLoadFailed();
                // 统计
                AdReport.adFail(position, adType, errorCode);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                mAdListener.onAdShow();
                // 统计
                AdReport.adImpression(position, adType);
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                mAdListener.onAdClick();
                // 统计
                AdReport.adClick(position, adType);
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                mAdListener.onAdClose();
            }
        });

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        // 统计
        AdReport.adRequest(position, adType);
    }

    public void showAd() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            RobustLog.log("The interstitial wasn't loaded yet.");
        }
    }


    public interface AMInterstitialListener {
        void onAdLoaded();

        void onLoadFailed();

        void onAdShow();

        void onAdClick();

        void onAdClose();
    }
}
