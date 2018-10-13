package com.robust.adsource.ad;

import com.robust.adsource.ModuleContext;
import com.robust.base.LogType;
import com.robust.base.RobustLog;
import com.robust.base.RobustLogInstance;
import com.robust.base.util.TimeUtil;

import java.util.Map;

/**
 * Created by chenhewen on 2018/7/23.
 */

public class AdUtil {

    private static final long TIME_OUT = TimeUtil.MINUTE * 3;

    public static void logWithAdPos(int position, String log) {
        String prefix = "[AdPos-" + position + "] ";
        ModuleContext.getLogger().log(prefix + log);
    }

    public static void markRequestOnFlight(Map<Integer, Long> positionOnFlight, int position) {
        positionOnFlight.put(position, System.currentTimeMillis());
    }

    public static void removeRequestOnFlight(Map<Integer, Long> positionOnFlight, int position) {
        positionOnFlight.remove(position);
    }

    public static boolean isRequestOnFlight(Map<Integer, Long> sPositionOnFlight, int position) {
        boolean onFlight = sPositionOnFlight.containsKey(position);
        if (!onFlight) {
            return false;
        }

        Long markMillis = sPositionOnFlight.get(position);
        // 这里我们设置超时3min，避免广告请求没有回调
        boolean expired = Math.abs(System.currentTimeMillis() - markMillis)
                > TIME_OUT;

        if (expired) {
            return false;
        }

        return true;
    }
}
