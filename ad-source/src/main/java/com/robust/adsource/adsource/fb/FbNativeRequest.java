package com.robust.adsource.adsource.fb;

import android.content.Context;

import com.facebook.ads.NativeAd;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.adsource.id.AdSourceId;
import com.robust.adsource.report.AdReport;

/**
 * Created by chenhewen on 2018/8/21.
 */

public class  FbNativeRequest extends FbRequestBase<NativeAd> implements IAdRequest {

    public FbNativeRequest() {

    }

    @Override
    public void requestAd(Context context, int position) {
        AdType adType = AdType.FB_NATIVE;
        String sourceId = AdSourceId.getSourceId(position, adType);

        NativeAd nativeAd = new NativeAd(context, sourceId);
        nativeAd.setAdListener(createListener(position, adType));

        // Request an ad
        nativeAd.loadAd();
        // 统计
        AdReport.adRequest(position, adType);
    }
}
