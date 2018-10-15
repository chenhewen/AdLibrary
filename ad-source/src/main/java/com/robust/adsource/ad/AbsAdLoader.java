package com.robust.adsource.ad;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.robust.adsource.ModuleContext;
import com.robust.adsource.ad.cache.SinglePosCache;
import com.robust.adsource.ad.event.OnAdClickEvent;
import com.robust.adsource.ad.event.OnAdErrorEvent;
import com.robust.adsource.ad.event.OnAdImpressionEvent;
import com.robust.adsource.ad.event.OnAdLoadedEvent;
import com.robust.adsource.adsource.AdType;
import com.robust.adsource.adsource.IAdRequest;
import com.robust.adsource.report.AdReport;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by chenhewen on 2018/8/27.
 */

public abstract class AbsAdLoader<T> implements IAdLoader{

    private final AdType mAdType;
    private final SinglePosCache<T> mCachePool;

    private AdListener mAdListener;

    private final boolean mAutoRequestWhenCacheEmpty;

    private Handler mHandler = new Handler(Looper.getMainLooper());


    public AbsAdLoader(AdType adType, boolean autoRequestWhenCacheEmpty, SinglePosCache<T> cachePool) {
        mAdType = adType;
        mAutoRequestWhenCacheEmpty = autoRequestWhenCacheEmpty;
        mCachePool = cachePool;
    }

    public AdType getAdType() {
        return mAdType;
    }

    protected abstract boolean isAdValid(T ad);

    public boolean isEventMute() {
        return mAdListener != null;
    }

    public void setEventMute(AdListener adListener) {
        mAdListener = adListener;
    }

    AdListener getAdListener() {
        return mAdListener;
    }

    boolean isAutoRequestWhenCacheEmpty() {
        return mAutoRequestWhenCacheEmpty;
    }

    protected void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    protected void postDelay(Runnable runnable, long millis) {
        mHandler.postDelayed(runnable, millis);
    }

    private UniAd buildUniAd(T t) {
        return new UniAd(mAdType, t);
    }

    public void notifyLoadSuccess(int position, T t) {
        UniAd uniAd = buildUniAd(t);
        EventBus.getDefault().post(new OnAdLoadedEvent(position, uniAd));
    }

    public void notifyLoadFailed(int position, int errorCode, String errorStr) {
        EventBus.getDefault().post(new OnAdErrorEvent(position, errorCode, errorStr));
    }

    public void notifyAdClick(int position, T t) {
        UniAd uniAd = buildUniAd(t);
        EventBus.getDefault().post(new OnAdClickEvent(position, uniAd));
    }

    public void notifyAdImpression(int position, T t) {
        UniAd uniAd = buildUniAd(t);
        EventBus.getDefault().post(new OnAdImpressionEvent(position, uniAd));
    }


    protected boolean hitCache(int position, AdType adType, SinglePosCache<T> cachePool) {
        if (cachePool == null) {
            return false;
        }

        if (cachePool.hasCache(position)) {
            // 我们这里的策略仅仅是未展示的缓存，展示后就直接删除，这里返回假设外部获取广告是为了马上展示
            T cache = cachePool.remove(position);
            // 缓存还有效
            if (isAdValid(cache)) {
                // log
                AdUtil.logWithAdPos(position, "hit cache " + adType);
                // 事件
                if (isEventMute()) {
                    getAdListener().onLoadSuccess(cache);
                } else {
                    notifyLoadSuccess(position, cache);
                }
                // 统计
                AdReport.adHitCache(position, getAdType());
                return true;
            }
        }

        return false;
    }

    protected boolean hitSubstitute(int position, int substitutePosition, SinglePosCache<T> cachePool) {
        if (cachePool == null) {
            return false;
        }

        if (position != substitutePosition) {
            if (cachePool.hasCache(substitutePosition)) {
                // 同上, 注意移除的是替补广告位
                T cache = cachePool.remove(substitutePosition);
                // 同上
                if (isAdValid(cache)) {
                    // log
                    AdUtil.logWithAdPos(position, "hit substitute position [adPos-" + substitutePosition + "]");
                    // 事件
                    if (isEventMute()) {
                        getAdListener().onLoadSuccess(cache);
                    } else {
                        notifyLoadSuccess(position, cache);
                    }
                    // 统计
                    AdReport.adHitSubstitute(position, getAdType());
                    return true;
                }
            }
        }

        return false;
    }

    protected void superOnAdLoaded(int position, T ad, SinglePosCache<T> cachePool, Map<Integer, Long> requestOnFlightMap) {
        if (isAdValid(ad)) {
            // log
            AdUtil.logWithAdPos(position, getAdType() + " loaded");
            // 缓存
            cachePool.cache(position, ad);
            // 事件
            if (isEventMute()) {
                getAdListener().onLoadSuccess(ad);
            } else {
                notifyLoadSuccess(position, ad);
            }
        }

        AdUtil.removeRequestOnFlight(requestOnFlightMap, position);
    }

    protected void superOnAdError(int position, int errorCode, String errorMsg, Map<Integer, Long> requestOnFlightMap) {
        // log
        AdUtil.logWithAdPos(position, "load " + getAdType() + " failed [code: " + errorCode + "]");
        // 事件
        if (isEventMute()) {
            getAdListener().onLoadFailed(errorCode, errorMsg);
        } else {
            notifyLoadFailed(position, errorCode, errorMsg);
        }
        AdUtil.removeRequestOnFlight(requestOnFlightMap, position);
    }

    protected void superOnAdShow(int position, SinglePosCache<T> cachePool) {
        if (cachePool == null) {
            return;
        }

        T ad = cachePool.remove(position);
        // 事件
        if (isEventMute()) {
            getAdListener().onImpression(ad);
        } else {
            notifyAdImpression(position, ad);
        }
    }

    protected void superOnAdClicked(int position, T ad) {
        // 事件
        if (isEventMute()) {
            getAdListener().onClicked(ad);
        } else {
            notifyAdClick(position, ad);
        }
    }

    protected void superRequestAd(int position, IAdRequest adRequest, Map<Integer, Long> requestOnFlightMap) {
        Context context = ModuleContext.getContext();
        // log
        AdUtil.logWithAdPos(position, "request " + getAdType() + " Ad");
        // 发出请求
        adRequest.requestAd(context, position);
        // 标记请求中
        AdUtil.markRequestOnFlight(requestOnFlightMap, position);
    }

    // addOnCacheEmptyListener 内部实现保证了多次调用没有额外的副作用
    protected void setAutoLoadOnce(final int position, SinglePosCache<T> cachePool) {
        // 判断是否是自请求广告
        if (isAutoRequestWhenCacheEmpty()) {
            cachePool.addOnCacheEmptyListener(position, new SinglePosCache.OnCacheEmptyListener() {
                @Override
                public void onCacheEmpty() {
                    // 我们等产生回调的方法链执行完毕后再再次发出请求
                    post(new Runnable() {
                        @Override
                        public void run() {
                            // log
                            AdUtil.logWithAdPos(position, "auto request " + getAdType() + " triggered");
                            // 再次加载广告
                            load(position);
                        }
                    });
                }
            });
        }
    }

    public boolean hasCacheOrSubstitution(int position, int substitutionPosition) {
        return mCachePool.hasCache(position) || mCachePool.hasCache(substitutionPosition);
    }
}
