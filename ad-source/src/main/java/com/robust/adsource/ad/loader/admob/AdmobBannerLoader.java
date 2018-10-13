package com.robust.adsource.ad.loader.admob;

import com.google.android.gms.ads.AdView;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.admob.AdmobBannerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhewen on 2018/7/15.
 */

public class AdmobBannerLoader extends AdmobAbsLoader<AdView> {

    private static Map<Integer, Long> sPositionOnFlight = new HashMap<>();

    private static SinglePosCache<AdView> sSinglePosCache = new SinglePosCache<>();

    private final AdmobBannerRequest mAdmobBannerRequest;

    public AdmobBannerLoader(AdType adType, boolean autoRequestWhenCacheEmpty) {
        super(adType, autoRequestWhenCacheEmpty, sSinglePosCache);
        mAdmobBannerRequest = new AdmobBannerRequest(adType);
    }

    @Override
    public void load(int position, int substitutePosition) {
        superLoad(position, substitutePosition, mAdmobBannerRequest, sSinglePosCache, sPositionOnFlight);
    }

    @Override
    public void load(int position) {
        load(position, position);
    }
}