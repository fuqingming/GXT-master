package com.jy.jgcjj.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jy.jgcjj.huanxin.DemoHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.finalteam.rxgalleryfinal.utils.ModelUtils;

public class BaseApplication extends Application {

    /** 环信 */
    public static Context applicationContext;
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        MultiDex.install(this);/** 环信 */
        super.onCreate();
        Utils.init(this);/** 环信 */
        applicationContext = this;/** 环信 */
        instance = this;/** 环信 */

        //init demo helper
        DemoHelper.getInstance().init(applicationContext);/** 环信 */



        ModelUtils.setDebugModel(true);
        Fresco.initialize(this);
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoader.getInstance().init(config.build());
    }

    /** 环信 */
    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {;
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    /** 环信 */
}