package com.robust.adsource.ad.loader.fb;

import com.facebook.ads.NativeBannerAd;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.fb.FbNativeBannerRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by chenhewen on 2018/7/14.
 */

public class FbNativeBannerLoader extends FbAbsAdLoader<NativeBannerAd> {

    private static Map<Integer, Long> sPositionOnFlight = new HashMap<>();

    private static SinglePosCache<NativeBannerAd> sSinglePosCache = new SinglePosCache<>();

    private final FbNativeBannerRequest mFBNativeBannerRequest;

    public FbNativeBannerLoader(boolean autoRequestWhenCacheEmpty) {
        super(AdType.FB_NATIVE_BANNER, autoRequestWhenCacheEmpty, sSinglePosCache);
        mFBNativeBannerRequest = new FbNativeBannerRequest();
    }

    @Override
    public void load(int position, int substitutePosition) {
        superLoad(position, substitutePosition, mFBNativeBannerRequest, sSinglePosCache, sPositionOnFlight);
    }

    @Override
    public void load(final int position) {
        load(position, position);
    }
}
