package com.robust.adsource.adsource.admob;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.adsource.id.AdSourceId;
import com.robust.adsource.report.AdReport;


/**
 * Created by chenhewen on 2018/8/21.
 */

public class AdmobBannerRequest extends AdmobRequestBase<AdView> implements IAdRequest {

    private final AdType mAdType;

    public AdmobBannerRequest(AdType adType) {
        mAdType = adType;
    }

    @Override
    public void requestAd(Context context, final int position) {
        final AdType adType = mAdType;

        String sourceId = AdSourceId.getSourceId(position, adType);
        final AdView adView = new AdView(context);
        AdSize adSize = getAdSizeByType(mAdType);
        adView.setAdSize(adSize);
        adView.setAdUnitId(sourceId);
        AdListener listener = createListener(position, adType, adView);
        adView.setAdListener(listener);
//        addShowListenerCompat(adView, listener);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        // 统计
        AdReport.adRequest(position, adType);
    }

    /**
     * 弥补admob banner 类型广告展示不回调的问题
     * @param view
     */
    private void addShowListenerCompat(final View view, final AdListener adListener) {
        final ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                adListener.onAdOpened();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private static AdSize getAdSizeByType(AdType adType) {
        if (adType == AdType.ADMOB_BANNER) {
            return AdSize.BANNER;
        } else if (adType == AdType.ADMOB_LARGE_BANNER) {
            return AdSize.LARGE_BANNER;
        } else if (adType == AdType.ADMOB_MEDIUM_RECTANGLE) {
            return AdSize.MEDIUM_RECTANGLE;
        } else if (adType == AdType.ADMOB_FULL_BANNER) {
            return AdSize.FULL_BANNER;
        } else if (adType == AdType.ADMOB_LEADERBOARD) {
            return AdSize.LEADERBOARD;
        } else if (adType == AdType.ADMOB_SMART_BANNER) {
            return AdSize.SMART_BANNER;
        } else {
            throw new IllegalArgumentException("AdmobBannerRequest can not deal with type: " + adType);
        }
    }
}
