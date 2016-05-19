package com.ifoodie.prarthnasl.ifoodiedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by prarthnasl on 5/1/2016.
 */
public class IFoodieApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getContext() {
        return applicationContext;
    }
}

