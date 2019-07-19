package com.tutorialscache.admobintegration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    AdView bannerAdView;
    AdRequest adRequestObj;
    Context context;
    Button interstitialAdBtn,videoRewardAdBtn;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);

        //link views from activity_main.xml
        getViews();

        //ad request object
         adRequestObj = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Device Id for testing from logcat : screenshot is added below
                .addTestDevice("F9B3B4B772E94A45CE7AECF1E0EE24C7")
                .build();
         bannerAdView.setAdListener(new AdListener(){
             @Override
             public void onAdLoaded() {
                 super.onAdLoaded();
             }

             @Override
             public void onAdClosed() {
                 super.onAdClosed();
                 Toast.makeText(context,"Ad closed",Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAdClicked() {
                 super.onAdClicked();
             }

             @Override
             public void onAdFailedToLoad(int i) {
                 super.onAdFailedToLoad(i);
                 Toast.makeText(context,"Failed to load ad: Error"+i,Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAdImpression() {
                 super.onAdImpression();
             }

             @Override
             public void onAdLeftApplication() {
                 super.onAdLeftApplication();
             }

             @Override
             public void onAdOpened() {
                 super.onAdOpened();
             }
         });
        bannerAdView.loadAd(adRequestObj);
    }

    private void getViews() {
        bannerAdView = findViewById(R.id.bannerAdView);
        interstitialAdBtn = findViewById(R.id.interstitialAdBtn);
        interstitialAdBtn.setOnClickListener(this);
        videoRewardAdBtn = findViewById(R.id.videoRewardAdBtn);
        videoRewardAdBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.interstitialAdBtn:
                showInterstitialAd();
                break;
            case R.id.videoRewardAdBtn:
                startActivity(new Intent(context,VideoRewardAdActivity.class));
                break;
        }
    }

    private void showInterstitialAd() {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(getString(R.string.Interstitial));

        AdRequest interstitialAdRequest = new AdRequest.Builder()
                .build();
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                //Ads loaded
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                //Ads closed
                //open new activity / perform action
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                //Ads couldn't loaded
            }
        });

        // Load ads into Interstitial Ads
        interstitialAd.loadAd(interstitialAdRequest);

        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });
    }

    @Override
    public void onPause() {
        if (bannerAdView != null) {
            bannerAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bannerAdView != null) {
            bannerAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (bannerAdView != null) {
            bannerAdView.destroy();
        }
        super.onDestroy();
    }


}
