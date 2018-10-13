package com.robust.adsource.ad.loader.fb;

import com.facebook.ads.InterstitialAd;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.fb.FbInterstitialRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhewen on 2018/8/27.
 */

public class FbInterstitialLoader extends FbAbsAdLoader<InterstitialAd> {

    private static Map<Integer, Long> sPositionOnFlight = new HashMap<>();

    private static SinglePosCache<InterstitialAd> sSinglePosCache = new SinglePosCache<>();

    private final FbInterstitialRequest mFbInterstitialRequest;

    public FbInterstitialLoader(boolean autoRequestWhenCacheEmpty) {
        super(AdType.FB_INTERSTITIAL, autoRequestWhenCacheEmpty, sSinglePosCache);
        mFbInterstitialRequest = new FbInterstitialRequest();
    }

    @Override
    public void load(final int position, final int substitutePosition) {
        superLoad(position, substitutePosition, mFbInterstitialRequest, sSinglePosCache, sPositionOnFlight);
    }

    @Override
    public void load(int position) {
        load(position, position);
    }
}
