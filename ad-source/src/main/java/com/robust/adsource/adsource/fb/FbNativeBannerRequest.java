package com.robust.adsource.adsource.fb;

import android.content.Context;

import com.facebook.ads.NativeBannerAd;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.adsource.id.AdSourceId;
import com.robust.adsource.report.AdReport;

/**
 * Created by chenhewen on 2018/8/21.
 */

public class FbNativeBannerRequest extends FbRequestBase<NativeBannerAd> implements IAdRequest {

    public FbNativeBannerRequest() {

    }

    @Override
    public void requestAd(Context context, int position) {
        AdType adType = AdType.FB_NATIVE_BANNER;
        String sourceId = AdSourceId.getSourceId(position, adType);

        NativeBannerAd nativeBannerAd = new NativeBannerAd(context, sourceId);
        nativeBannerAd.setAdListener(createListener(position, adType));
        // load the ad
        nativeBannerAd.loadAd();
        // 统计
        AdReport.adRequest(position, adType);
    }
}
