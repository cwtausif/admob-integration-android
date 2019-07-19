package com.tutorialscache.admobintegration;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class VideoRewardAdActivity extends AppCompatActivity {
    RewardedVideoAd rewardedVideoAd;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_video_reward_ad);
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.loadAd(getString(R.string.rewarded_video),
                new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        // Device Id for testing from logcat
                        .addTestDevice("F9B3B4B772E94A45CE7AECF1E0EE24C7").build());
        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewarded(RewardItem rewardItem) {
                Log.d("response", "Type : " + rewardItem.getType() + "  Price: " +
                        rewardItem.getAmount());
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Log.d("response", "rewarded ad left app");
            }

            @Override
            public void onRewardedVideoAdClosed() {
                Log.d("response","video ad closed");
                finish();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
                Log.d("response","video ad failed to load"+errorCode);
            }

            @Override
            public void onRewardedVideoAdLoaded() {
                Log.d("response","video ad loaded");
                if (rewardedVideoAd.isLoaded()) {
                    rewardedVideoAd.show();
                }
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Log.d("response","video ad opened");
            }

            @Override
            public void onRewardedVideoStarted() {
                Log.d("response","rewarded video ad started");
            }
        });


    }

    @Override
    public void onResume() {
        rewardedVideoAd.resume(context);
        super.onResume();
    }

    @Override
    public void onPause() {
        rewardedVideoAd.pause(context);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        rewardedVideoAd.destroy(context);
        super.onDestroy();
    }
}
