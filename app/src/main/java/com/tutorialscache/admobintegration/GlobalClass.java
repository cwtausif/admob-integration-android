package com.tutorialscache.admobintegration;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;


public class GlobalClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this);
    }
}
