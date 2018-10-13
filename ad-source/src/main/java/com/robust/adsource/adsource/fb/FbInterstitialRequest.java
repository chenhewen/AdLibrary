package com.robust.adsource.adsource.fb;

import android.content.Context;

import com.facebook.ads.InterstitialAd;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.adsource.id.AdSourceId;
import com.robust.adsource.report.AdReport;

/**
 * Created by chenhewen on 2018/8/27.
 */

public class FbInterstitialRequest extends FbRequestBase<InterstitialAd> implements IAdRequest {

    public FbInterstitialRequest() {

    }

    @Override
    public void requestAd(Context context, int position) {
        AdType adType = AdType.FB_INTERSTITIAL;
        String sourceId = AdSourceId.getSourceId(position, adType);

        InterstitialAd interstitialAd = new InterstitialAd(context, sourceId);
        interstitialAd.setAdListener(createListener(position, adType));

        interstitialAd.loadAd();
        // 统计
        AdReport.adRequest(position, adType);
    }
}
