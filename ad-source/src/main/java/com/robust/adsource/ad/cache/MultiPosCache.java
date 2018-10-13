package com.robust.adsource.ad.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenhewen on 2018/7/14.
 */

public class MultiPosCache<E> implements IAdCache<E> {

    private MultiKeyMap<Integer, E> mCacheProtocol = new MultiKeyMap<>();

    @Override
    public void cache(int position, E e) {
        mCacheProtocol.put(position, e);
    }

    @Override
    public boolean hasCache(int position) {
        return mCacheProtocol.contains(position);
    }

    @Override
    public E getCache(int position) {
        return mCacheProtocol.get(position);
    }

    @Override
    public E remove(int position) {
        return mCacheProtocol.remove(position);
    }

    /**
     * 多个Key对应同一个Value，内部使用HashMap做代理
     * @param <K>
     * @param <V>
     */
    public static class MultiKeyMap<K, V> {

        Map<K, V> map = new HashMap<>();

        public void put(K k, V v) {
            map.put(k, v);
        }

        public V get(K k) {
            return map.get(k);
        }

        public boolean contains(K k) {
            return map.containsKey(k);
        }

        public V remove(K k) {
            V v = get(k);
            Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, V> next = iterator.next();
                if (next.getValue() == v) {
                    iterator.remove();
                }
            }

            return v;
        }
    }
}
