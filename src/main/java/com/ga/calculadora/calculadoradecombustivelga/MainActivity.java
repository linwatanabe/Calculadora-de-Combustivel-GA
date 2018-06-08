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

public class MainActivity extends AppCompatActivity {

    private EditText vGasolina, vAlcool;
    private Button btReset;
    private TextView tvResultado;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private String stringG, stringE;
    private Double valorG, valorE, resultadoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        vGasolina = findViewById(R.id.et_vGasolina);
        vAlcool = findViewById(R.id.et_vAlcool);
        tvResultado = findViewById(R.id.tv_resultado);

        btReset = findViewById(R.id.bt_reset);
        btReset.setVisibility(View.INVISIBLE);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequestBanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestBanner);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

    }

    public void calcular(View view) {

        stringG = vGasolina.getText().toString();
        stringE = vAlcool.getText().toString();

        if (stringG.isEmpty()){
            Toast.makeText(MainActivity.this, "Digite o preço da gasolina", Toast.LENGTH_SHORT).show();
        }else {
            if (stringE.isEmpty()){
                Toast.makeText(MainActivity.this, "Digite o preço do álcool", Toast.LENGTH_SHORT).show();
            }else {

                btReset.setVisibility(View.VISIBLE);
                AdRequest adRequestInterstitial = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequestInterstitial);

                valorG = Double.parseDouble(stringG);
                valorE = Double.parseDouble(stringE);

                resultadoF = valorE/valorG;

                if (resultadoF >= 0.7){
                    tvResultado.setText("Abasteça com Gasolina!");
                }else {
                    tvResultado.setText("Abasteça com álcool!");
                }

            }
        }

    }

    public void recalcular(View view) {
        vGasolina.setText("");
        vAlcool.setText("");
        tvResultado.setText("");
        btReset.setVisibility(View.INVISIBLE);

        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}
