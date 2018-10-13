package com.robust.base.util;

/**
 * Created by chenhewen on 2017/10/9.
 */

public class TimeUtil {
    public static final long SECOND = 1000L;
    public static final long MINUTE = SECOND * 60;
    public static final long HOUR = MINUTE * 60;
    public static final long DAY = HOUR * 24;

    public static long getCurrentMinute() {
        long millis = System.currentTimeMillis();
        return millis / 1000 / 60;
    }
}
