package com.robust.adsource.ad.loader.admob;

import com.robust.adsource.ad.AbsAdLoader;
import com.robust.adsource.ad.AdListener;
import com.robust.adsource.ad.AdUtil;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.admob.AdmobRequestBase;
import com.robust.adsource.adsource.admob.AdmobRequestListener;

import java.util.Map;

public abstract class AdmobAbsLoader<T> extends AbsAdLoader<T> {

    public AdmobAbsLoader(AdType adType, boolean autoRequestWhenCacheEmpty, SinglePosCache<T> cachePool) {
        super(adType, autoRequestWhenCacheEmpty, cachePool);
    }

    void superLoad(final int position,
                   final int substitutePosition,
                   AdmobRequestBase<T> request,
                   final SinglePosCache<T> cachePool,
                   final Map<Integer, Long> requestOnFlightMap) {

        setAutoLoadOnce(position, cachePool);

        // 请求是否已经发出了
        if (AdUtil.isRequestOnFlight(requestOnFlightMap, position)) {
            AdUtil.logWithAdPos(position, "request is already on the flight.");
            return;
        }

        // 缓存是否已经存在了
        if (hitCache(position, getAdType(), cachePool)) {
            return;
        }

        // 替补广告是否存在
        if (hitSubstitute(position, substitutePosition, cachePool)) {
            return;
        }

        // 请求前设置回调接口
        request.setListener(new AdmobRequestListener<T>() {
            @Override
            public void onAdLoaded(T ad) {
                superOnAdLoaded(position, ad, cachePool, requestOnFlightMap);
            }

            @Override
            public void onLoadFailed(int errorCode) {
                superOnAdError(position, errorCode, AdListener.NO_ERROR_STR, requestOnFlightMap);
            }

            @Override
            public void onAdShown() {
                superOnAdShow(position, cachePool);
            }

            @Override
            public void onAdClick(T ad) {
                superOnAdClicked(position, ad);
            }
        });

        superRequestAd(position, request, requestOnFlightMap);
    }

    @Override
    protected boolean isAdValid(T ad) {
        //TODO
        return true;
    }
}
