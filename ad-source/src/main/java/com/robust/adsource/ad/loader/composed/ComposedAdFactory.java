package com.robust.adsource.ad.loader.composed;

import com.robust.adsource.AdSourceUtil;
import com.robust.adsource.ad.AbsAdLoader;
import com.robust.adsource.ad.loader.admob.AdmobBannerLoader;
import com.robust.adsource.ad.loader.admob.AdmobInterstitialLoader;
import com.robust.adsource.ad.loader.fb.FbInterstitialLoader;
import com.robust.adsource.ad.loader.fb.FbNativeBannerLoader;
import com.robust.adsource.ad.loader.fb.FbNativeLoader;
import com.robust.adsource.adsource.AdType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chenhewen on 2018/8/17.
 */

public class ComposedAdFactory {

    /**
     * 创建banner 类型"组合广告"
     *
     * @param composedAdStrategy
     * @return
     */
    public static ComposedAdLoader createBanner(ComposedAdStrategy composedAdStrategy) {
        List<AbsAdLoader> list = new ArrayList<>();
        if (composedAdStrategy == ComposedAdStrategy.ADMOB_ONLY) {
            list.add(new AdmobBannerLoader(AdType.ADMOB_BANNER, false));

        } else if (composedAdStrategy == ComposedAdStrategy.FB_ONLY) {
            list.add(new FbNativeBannerLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.FB_THEN_ADMOB) {
            list.add(new FbNativeBannerLoader(false));
            list.add(new AdmobBannerLoader(AdType.ADMOB_BANNER, false));

        } else if (composedAdStrategy == ComposedAdStrategy.ADMOB_THEN_FB) {
            list.add(new AdmobBannerLoader(AdType.ADMOB_BANNER, false));
            list.add(new FbNativeBannerLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.AUTO_DECIDE) {
            if (AdSourceUtil.isFacebookOrLiteInstalled()) {
                list.add(new FbNativeBannerLoader(false));
            }
            list.add(new AdmobBannerLoader(AdType.ADMOB_BANNER, false));
        }

        return new ComposedAdLoader(list);
    }

    /**
     * 创建interstitial 类型"组合广告"
     *
     * @param composedAdStrategy
     * @return
     */
    public static ComposedAdLoader createInterstitial(ComposedAdStrategy composedAdStrategy) {
        List<AbsAdLoader> list = new ArrayList<>();
        if (composedAdStrategy == ComposedAdStrategy.ADMOB_ONLY) {
            list.add(new AdmobInterstitialLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.FB_ONLY) {
            list.add(new FbInterstitialLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.FB_THEN_ADMOB) {
            list.add(new FbInterstitialLoader(false));
            list.add(new AdmobInterstitialLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.ADMOB_THEN_FB) {
            list.add(new AdmobInterstitialLoader(false));
            list.add(new FbInterstitialLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.AUTO_DECIDE) {
            if (AdSourceUtil.isFacebookOrLiteInstalled()) {
                list.add(new FbInterstitialLoader(false));
            }
            list.add(new AdmobInterstitialLoader(false));
        }

        return new ComposedAdLoader(list);
    }

    /**
     * 创建native 类型"组合广告"
     * [注意] 目前admob的native是由{@link AdType#ADMOB_MEDIUM_RECTANGLE}类型代替的
     * @param composedAdStrategy
     * @return
     */
    public static ComposedAdLoader createNative(ComposedAdStrategy composedAdStrategy) {
        List<AbsAdLoader> list = new ArrayList<>();
        if (composedAdStrategy == ComposedAdStrategy.ADMOB_ONLY) {
            list.add(new AdmobBannerLoader(AdType.ADMOB_MEDIUM_RECTANGLE, false));

        } else if (composedAdStrategy == ComposedAdStrategy.FB_ONLY) {
            list.add(new FbNativeLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.FB_THEN_ADMOB) {
            list.add(new FbNativeLoader(false));
            list.add(new AdmobBannerLoader(AdType.ADMOB_MEDIUM_RECTANGLE, false));

        } else if (composedAdStrategy == ComposedAdStrategy.ADMOB_THEN_FB) {
            list.add(new AdmobBannerLoader(AdType.ADMOB_MEDIUM_RECTANGLE, false));
            list.add(new FbNativeLoader(false));

        } else if (composedAdStrategy == ComposedAdStrategy.AUTO_DECIDE) {
            if (AdSourceUtil.isFacebookOrLiteInstalled()) {
                list.add(new FbNativeLoader(false));
            }
            list.add(new AdmobBannerLoader(AdType.ADMOB_MEDIUM_RECTANGLE, false));
        }

        return new ComposedAdLoader(list);
    }
}
