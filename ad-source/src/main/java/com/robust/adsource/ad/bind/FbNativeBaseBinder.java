package com.robust.adsource.ad.bind;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdIconView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeBannerAd;

import java.util.ArrayList;


/**
 * Created by chenhewen on 2018/7/14.
 */

public class FbNativeBaseBinder extends AdBinder {

    public void bindProtocol(NativeAdBase nativeAd, View adView) {
        if (nativeAd == null || adView == null) {
            return;
        }
        AdIconView adIconView = adView.findViewWithTag("ad_icon");
        TextView titleView = adView.findViewWithTag("ad_title");
        TextView descView = adView.findViewWithTag("ad_desc");
        MediaView mediaView = adView.findViewWithTag("ad_media");
        TextView btnView = adView.findViewWithTag("ad_btn");
        ViewGroup adChoiceContainer = adView.findViewWithTag("ad_choices_container");

        bindTitle(nativeAd, titleView);
        bindDesc(nativeAd, descView);
        bindBtnText(nativeAd, btnView);
        bindAdChoice(nativeAd, adChoiceContainer);
        bindClick(nativeAd, adView, mediaView, adIconView, titleView, descView, btnView);
    }

    public void bindTitle(NativeAdBase nativeAd, TextView titleView) {
        String advertiserName = nativeAd.getAdvertiserName();
        titleView.setText(advertiserName);
    }

    public void bindDesc(NativeAdBase nativeAd, TextView descView) {
        String adBodyText = nativeAd.getAdBodyText();
        descView.setText(adBodyText);
    }

    public void bindBtnText(NativeAdBase nativeAd, TextView btnView) {
        if (nativeAd.hasCallToAction()) {
            String adCallToAction = nativeAd.getAdCallToAction();
            btnView.setText(adCallToAction);
        }
        //TODO: btnView 默认文字或者隐藏
    }

    public void bindAdChoice(NativeAdBase nativeAd, ViewGroup adChoicesContainer) {
        AdChoicesView adChoicesView = new AdChoicesView(getContext(), nativeAd, true);
        adChoicesContainer.addView(adChoicesView, 0);
    }

    public void bindClick(NativeAdBase nativeAdBase, View contentView, MediaView mediaView, AdIconView adIconView, View... clickView) {
        nativeAdBase.unregisterView();

        ArrayList<View> views = new ArrayList<>();
        for (View view : clickView) {
            views.add(view);
        }
        MediaView mediaViewNotNull = mediaView == null ? new MediaView(getContext()) : mediaView;
        if (nativeAdBase instanceof NativeAd) {
            ((NativeAd) nativeAdBase).registerViewForInteraction(contentView, mediaViewNotNull, adIconView, views);
        } else if (nativeAdBase instanceof NativeBannerAd) {
            ((NativeBannerAd) nativeAdBase).registerViewForInteraction(contentView, adIconView, views);
        }
    }
}
