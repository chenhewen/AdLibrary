package com.robust.adsource.report;

/**
 * 用来统计广告相关报告的常量
 * Created by chenhewen on 2018/8/26.
 */

public class AdReportEvent {

    /**
     * 广告请求
     */
    public static final String EVENT_AD_REQUEST = "event_ad_request";

    /**
     * 广告填充
     */
    public static final String EVENT_AD_FILL = "event_ad_fill";

    /**
     * 广告展示
     */
    public static final String EVENT_AD_IMPRESSION = "event_ad_impression";

    /**
     * 广告点击
     */
    public static final String EVENT_AD_CLICK = "event_ad_click";

    /**
     * 广告请求出错
     */
    public static final String EVENT_AD_ERROR = "event_ad_error";

    /**
     * 广告命中缓存
     */
    public static final String EVENT_AD_HIT_CACHE = "event_ad_hit_cache";

    /**
     * 广告命中替补
     */
    public static final String EVENT_AD_HIT_SUBSTITUTE = "event_ad_hit_substitute";
}
