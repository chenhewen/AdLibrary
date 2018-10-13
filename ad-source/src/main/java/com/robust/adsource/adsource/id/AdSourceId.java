package com.robust.adsource.adsource.id;

import com.robust.adsource.ModuleContext;
import com.robust.adsource.adsource.AdType;

/**
 * 将具体的的position转化成真实广告id
 * Created by chenhewen on 2018/8/21.
 */

public class AdSourceId {

    /**
     * 获取对应的真实广告源id
     *
     * @return 真实广告源id
     */
    public static String getSourceId(int position, AdType adType) {
        return ModuleContext.getIAdSourceId().getSourceId(position, adType);
    }
}
