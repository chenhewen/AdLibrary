package com.robust.adsource.ad.loader.fb;

import com.facebook.ads.NativeAd;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.fb.FbNativeRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by chenhewen on 2018/7/14.
 */

public class FbNativeLoader extends FbAbsAdLoader<NativeAd> {

    private static Map<Integer, Long> sPositionOnFlight = new HashMap<>();

    private static SinglePosCache<NativeAd> sSinglePosCache = new SinglePosCache<>();

    private final FbNativeRequest mFbNativeRequest;

    public FbNativeLoader(boolean autoRequestWhenCacheEmpty) {
        super(AdType.FB_NATIVE, autoRequestWhenCacheEmpty, sSinglePosCache);
        mFbNativeRequest = new FbNativeRequest();
    }

    @Override
    public void load(final int position, final int substitutePosition) {
        superLoad(position, substitutePosition, mFbNativeRequest, sSinglePosCache, sPositionOnFlight);
    }

    @Override
    public void load(final int position) {
        load(position, position);
    }
}
