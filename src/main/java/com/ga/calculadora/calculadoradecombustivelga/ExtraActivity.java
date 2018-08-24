package com.ga.calculadora.calculadoradecombustivelga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ExtraActivity extends AppCompatActivity {

    private TextView extraSimples, extraAvancado;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        MobileAds.initialize(this,
                "ca-app-pub-9268401392658071~6252691620");

        extraSimples = findViewById(R.id.extraSimples);
        extraAvancado = findViewById(R.id.extraAvancado);

        // admob banner simples
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // admob interstitial ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9268401392658071/2919685343");
        AdRequest adRequestInterstitial = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequestInterstitial);
    }

    public void voltar(View view) {
        startActivity(new Intent(this, MainActivity.class));
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }
}
