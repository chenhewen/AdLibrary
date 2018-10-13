package com.robust.adsource.adsource.admob;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.adsource.id.AdSourceId;
import com.robust.adsource.report.AdReport;

public class AdmobInterstitialRequest extends AdmobRequestBase<InterstitialAd> implements IAdRequest {

    @Override
    public void requestAd(Context context, final int position) {
        final AdType adType = AdType.ADMOB_INTERSTITIAL;
        String sourceId = AdSourceId.getSourceId(position, adType);

        final InterstitialAd interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(sourceId);
        interstitialAd.setAdListener(createListener(position, adType, interstitialAd));

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        // 统计
        AdReport.adRequest(position, adType);
    }
}
