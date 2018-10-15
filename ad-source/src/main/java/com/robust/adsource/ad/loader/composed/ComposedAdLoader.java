package com.robust.adsource.ad.loader.composed;

import com.robust.adsource.ad.AbsAdLoader;
import com.robust.adsource.ad.AdListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhewen on 2018/8/17.
 */

public class ComposedAdLoader {

    private final List<AbsAdLoader> mAdLoaderList;

    public ComposedAdLoader(List<AbsAdLoader> adLoaderList) {
        checkLoaderList(adLoaderList);
        mAdLoaderList = adLoaderList;
    }

    public void load(int position, boolean cacheFirst) {
        load(position, position, cacheFirst);
    }

    public void load(int position, int substitution, boolean cacheFirst) {
        if (mAdLoaderList.size() == 1) {
            mAdLoaderList.get(0).load(position, substitution);
        } else {
            if (cacheFirst) {
                loadCacheFirst(position, substitution);
            } else {
                loadList(position, substitution);
            }
        }
    }


    private void loadList(int position, int substitution) {
        loadRecursively(mAdLoaderList, position, substitution, 0);
    }

    private void loadCacheFirst(int position, int substitution) {
        List<AbsAdLoader> list = new ArrayList<>();
        int index = 0;
        for (AbsAdLoader absAdLoader : mAdLoaderList) {
            if (absAdLoader.hasCacheOrSubstitution(position, substitution)) {
                // 任意两个都有或者都没有缓存，保持两者相对顺序不变。
                list.add(index, absAdLoader);
                index++;
            } else {
                list.add(absAdLoader);
            }
        }

        loadRecursively(list, position, substitution, 0);
    }

    private void loadRecursively(final List<AbsAdLoader> absAdLoaderList, final int position, final int substitution, final int loaderIndex) {
        if (loaderIndex < 0 || loaderIndex >= absAdLoaderList.size()) {
            return;
        }

        final AbsAdLoader absAdLoader = absAdLoaderList.get(loaderIndex);
        absAdLoader.setEventMute(new AdListener() {
            @Override
            public void onLoadSuccess(Object object) {
                absAdLoader.notifyLoadSuccess(position, object);
            }

            @Override
            public void onLoadFailed(int errorCode, String errorStr) {
                // 递归列表中的下一个
                loadRecursively(absAdLoaderList, position, substitution, loaderIndex + 1);
            }

            @Override
            public void onImpression(Object object) {
                absAdLoader.notifyAdImpression(position, object);
            }

            @Override
            public void onClicked(Object object) {
                absAdLoader.notifyAdClick(position, object);
            }
        });
        absAdLoader.load(position, substitution);
    }

    private void checkLoaderList(List<AbsAdLoader> adLoaderList) {
        if (adLoaderList == null || adLoaderList.isEmpty()) {
            throw new IllegalArgumentException("adLoaderList should not be null or empty.");
        }
    }
}
