package com.ga.calculadora.calculadoradecombustivelga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AvancadoActivity extends AppCompatActivity {

    private EditText vGasolina, vAlcool, consumoG, consumoA;
    private String strValorG, strValorA, strConsumoG, strConsumoA;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avancado);
        MobileAds.initialize(this,
                "ca-app-pub-9268401392658071~6252691620");

        vGasolina = findViewById(R.id.et_gasolina);
        vAlcool = findViewById(R.id.et_alcool);
        consumoG = findViewById(R.id.et_consumoG);
        consumoA = findViewById(R.id.et_consumoA);

        // admob banner
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

        strValorG = vGasolina.getText().toString();
        strValorA = vAlcool.getText().toString();
        strConsumoG = consumoG.getText().toString();
        strConsumoA = consumoA.getText().toString();

        if (strValorG.isEmpty()){
            Toast.makeText(AvancadoActivity.this, "Digite o preço da gasolina", Toast.LENGTH_SHORT).show();
        }else {
            if (strValorA.isEmpty()){
                Toast.makeText(AvancadoActivity.this, "Digite o preço do álcool", Toast.LENGTH_SHORT).show();
            }else {
                if (strConsumoG.isEmpty()){
                    Toast.makeText(this, "Digite o consumo médio de gasolina do seu carro", Toast.LENGTH_SHORT).show();
                }else {
                    if (strConsumoA.isEmpty()){
                        Toast.makeText(this, "Digite o consumo médio de álcool do seu carro", Toast.LENGTH_SHORT).show();
                    }else {

                        Bundle bundle = new Bundle();
                        bundle.putString("strValorG", strValorG);
                        bundle.putString("strValorA", strValorA);
                        bundle.putString("strConsumoG", strConsumoG);
                        bundle.putString("strConsumoA", strConsumoA);

                        Intent intent = new Intent(this, AvancadoResultadoActivity.class);
                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                }
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