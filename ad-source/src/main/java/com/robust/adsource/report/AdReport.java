package com.robust.adsource.report;

import com.robust.adsource.adsource.AdType;
import com.robust.analysis.FacebookReport;

/**
 * Created by chenhewen on 2018/8/26.
 */

public class AdReport {

    private static String getPositionStr(int position) {
        return String.valueOf(position);
    }

    public static void adRequest(int position, AdType adType) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_REQUEST, getPositionStr(position), adType.toString());
    }

    public static void adFill(int position, AdType adType) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_FILL, getPositionStr(position), adType.toString());
    }

    public static void adImpression(int position, AdType adType) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_IMPRESSION, getPositionStr(position), adType.toString());
    }

    public static void adClick(int position, AdType adType) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_CLICK, getPositionStr(position), adType.toString());
    }

    public static void adFail(int position, AdType adType, int errorCode) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_ERROR, getPositionStr(position), adType.toString(), String.valueOf(errorCode));
    }

    public static void adHitCache(int position, AdType adType) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_HIT_CACHE, getPositionStr(position), adType.toString());
    }

    public static void adHitSubstitute(int position, AdType adType) {
        FacebookReport.reportEvent(AdReportEvent.EVENT_AD_HIT_SUBSTITUTE, getPositionStr(position), adType.toString());
    }
}
