package com.robust.adsource.ad;

/**
 * Created by chenhewen on 2018/8/17.
 */

public interface IAdLoader {

    void load(int position);

    void load(int position, int substitutePosition);
}
