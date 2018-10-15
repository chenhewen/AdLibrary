package com.robust.adsource.ad.loader.fb;

import com.facebook.ads.Ad;
import com.robust.adsource.ad.AbsAdLoader;
import com.robust.adsource.ad.AdUtil;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.fb.FbRequestBase;
import com.robust.adsource.adsource.fb.FbRequestSimpleListener;

import java.util.Map;

/**
 * Created by chenhewen on 2018/8/17.
 */

public abstract class FbAbsAdLoader<T extends Ad> extends AbsAdLoader<T> {

    public FbAbsAdLoader(AdType adType, boolean autoRequestWhenCacheEmpty, SinglePosCache<T> cachePool) {
        super(adType, autoRequestWhenCacheEmpty, cachePool);
    }

    @Override
    protected boolean isAdValid(T ad) {
        return !ad.isAdInvalidated();
    }

    protected void superLoad(final int position,
                             final int substitutePosition,
                             FbRequestBase<T> request,
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
        request.setListener(new FbRequestSimpleListener<T>() {
            @Override
            public void onAdLoaded(T ad) {
                superOnAdLoaded(position, ad, cachePool, requestOnFlightMap);
            }

            @Override
            public void onLoadFailed(int errorCode, String errorMsg) {
                superOnAdError(position, errorCode, errorMsg, requestOnFlightMap);
            }

            @Override
            public void onAdShow() {
                superOnAdShow(position, cachePool);
            }

            @Override
            public void onAdClicked(T ad) {
                superOnAdClicked(position, ad);
            }

            @Override
            public void onInterstitialDisplayed(T ad) {
                superOnAdShow(position, cachePool);
            }
        });

        superRequestAd(position, request, requestOnFlightMap);
    }
}
