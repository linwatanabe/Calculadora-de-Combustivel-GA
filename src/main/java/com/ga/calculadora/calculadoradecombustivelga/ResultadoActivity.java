package com.ga.calculadora.calculadoradecombustivelga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class ResultadoActivity extends AppCompatActivity {

    private TextView vGasolina, vAlcool;
    private TextView tvResultado;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private Double valorG, valorE, resultadoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        vGasolina = findViewById(R.id.tv_vGasolina);
        vAlcool = findViewById(R.id.tv_vAlcool);
        tvResultado = findViewById(R.id.tv_resultado);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequestBanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestBanner);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequestInterstitial = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequestInterstitial);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String stringG = bundle.getString("stringG");
        String stringE = bundle.getString("stringE");

        vGasolina.setText("R$"+stringG);
        vAlcool.setText("R$"+stringE);

        valorG = Double.parseDouble(stringG);
        valorE = Double.parseDouble(stringE);

        resultadoF = valorE / valorG;

        if (resultadoF >= 0.7) {
            tvResultado.setText("Abasteça com Gasolina!");
        }else {
            tvResultado.setText("Abasteça com álcool!");
        }

    }

    public void recalcular(View view) {
        startActivity(new Intent(this, MainActivity.class));
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}