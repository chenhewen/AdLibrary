package com.robust.adsource;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class AdSourceUtil {

    public static boolean isAppInstall(String packageName) {
        Context context = ModuleContext.getContext();
        ApplicationInfo applicationInfo = null;
        PackageManager pm = context.getPackageManager();
        try {
            applicationInfo = pm.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return applicationInfo != null;
    }

    public static boolean isFacebookOrLiteInstalled() {
        return isAppInstall(AdConst.PackageName.FACEBOOK) ||
                isAppInstall(AdConst.PackageName.FACEBOOK_LITE);
    }
}
