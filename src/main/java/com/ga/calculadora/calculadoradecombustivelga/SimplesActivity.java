package com.ga.calculadora.calculadoradecombustivelga;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormatSymbols;

public class SimplesActivity extends AppCompatActivity {

    private EditText vGasolina, vAlcool;
    private String stringG, stringE;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simples);
        MobileAds.initialize(this,
                "ca-app-pub-9268401392658071~6252691620");

        vGasolina = findViewById(R.id.et_gasolina);
        vAlcool = findViewById(R.id.et_alcool);

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

    public void calcular(View view) {

        stringG = vGasolina.getText().toString();
        stringE = vAlcool.getText().toString();

        if (stringG.isEmpty()){
            Toast.makeText(SimplesActivity.this, "Digite o preço da gasolina", Toast.LENGTH_SHORT).show();
        }else {
            if (stringE.isEmpty()){
                Toast.makeText(SimplesActivity.this, "Digite o preço do álcool", Toast.LENGTH_SHORT).show();
            }else {

                Bundle bundle = new Bundle();
                bundle.putString("stringG", stringG);
                bundle.putString("stringE", stringE);

                Intent intent = new Intent(this, SimplesResultadoActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        }

    }

    public void voltar(View view) {
        startActivity(new Intent(this, MainActivity.class));
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}