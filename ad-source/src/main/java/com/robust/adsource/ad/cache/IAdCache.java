package com.robust.adsource.ad.cache;

/**
 * Created by chenhewen on 2018/7/14.
 */

public interface IAdCache<T> {

    void cache(int position, T t);

    boolean hasCache(int position);

    T getCache(int position);

    T remove(int position);

}
