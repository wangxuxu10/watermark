package com.forone.watermark;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

/**
 * @author xuxu.wang01
 * @time 2019-04-02 15:32
 * @email chao.zhang12@ucarinc.com
 * @tel 2790
 * @desc
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Glide.init(this, new GlideBuilder());
    }
}
