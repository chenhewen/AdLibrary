package com.robust.adsource.ad.loader.composed;

/**
 * Created by chenhewen on 2018/8/17.
 */

public enum ComposedAdStrategy {
    /**
     * 仅仅fb
     */
    FB_ONLY,

    /**
     * 仅仅Admob
     */
    ADMOB_ONLY,

    /**
     * 先请求fb, 若失败，再请求admob
     */
    FB_THEN_ADMOB,

    /**
     * 先请求admob, 若失败，再请求fb
     */
    ADMOB_THEN_FB,

    /**
     * 帮我决定
     */
    AUTO_DECIDE,
}
