package com.ga.calculadora.calculadoradecombustivelga;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,
                "ca-app-pub-9268401392658071~6252691620");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void abrirSimples(View view) {
        startActivity(new Intent(this, SimplesActivity.class));
    }

    public void abrirAvancado(View view) {
        startActivity(new Intent(this, AvancadoActivity.class));
    }

    public void abrirExtras(View view) {
        startActivity(new Intent(this, ExtraActivity.class));
    }
}
