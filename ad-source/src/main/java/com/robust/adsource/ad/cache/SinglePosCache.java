package com.robust.adsource.ad.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenhewen on 2018/7/14.
 */

public class SinglePosCache<E> implements IAdCache<E>{

    private Map<Integer, E> mCachePool = new HashMap<>();

    @Override
    public void cache(int position, E e) {
        mCachePool.put(position, e);
    }

    @Override
    public boolean hasCache(int position) {
        return mCachePool.containsKey(position);
    }

    @Override
    public E getCache(int position) {
        return mCachePool.get(position);
    }

    @Override
    public E remove(int position) {
        E removed = mCachePool.remove(position);
        if (removed != null) {
            notifyEmpty(position);
        }
        return removed;
    }

    private Map<Integer, OnCacheEmptyListener> mListenerMap = new HashMap<>();

    public interface OnCacheEmptyListener {
        void onCacheEmpty();
    }

    public void addOnCacheEmptyListener(int position, OnCacheEmptyListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("OnCacheEmptyListener should not be null");
        }
        if (!mListenerMap.containsKey(position)) {
            mListenerMap.put(position, listener);
        }
    }

    public OnCacheEmptyListener removeOnCacheEmptyListener(int position) {
        return mListenerMap.remove(position);
    }

    public void notifyEmpty(int position) {
        OnCacheEmptyListener onCacheEmptyListener = mListenerMap.get(position);
        if (onCacheEmptyListener != null) {
            onCacheEmptyListener.onCacheEmpty();
        }
    }
}
