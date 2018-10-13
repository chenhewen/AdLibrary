package com.robust.adsource.ad.loader.admob;

import com.google.android.gms.ads.InterstitialAd;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.admob.AdmobInterstitialRequest;

import java.util.HashMap;
import java.util.Map;

public class AdmobInterstitialLoader extends AdmobAbsLoader<InterstitialAd>{

    private static Map<Integer, Long> sPositionOnFlight = new HashMap<>();

    private static SinglePosCache<InterstitialAd> sSinglePosCache = new SinglePosCache<>();

    private final AdmobInterstitialRequest mAdmobInterstitialRequest;



    public AdmobInterstitialLoader(boolean autoRequestWhenCacheEmpty) {
        super(AdType.ADMOB_INTERSTITIAL, autoRequestWhenCacheEmpty, sSinglePosCache);
        mAdmobInterstitialRequest = new AdmobInterstitialRequest();
    }

    @Override
    public void load(int position, int substitutePosition) {
        superLoad(position, substitutePosition, mAdmobInterstitialRequest, sSinglePosCache, sPositionOnFlight);
    }

    @Override
    public void load(int position) {
        load(position, position);
    }
}
