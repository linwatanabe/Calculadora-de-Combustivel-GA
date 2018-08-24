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

public class SimplesResultadoActivity extends AppCompatActivity {

    private TextView vGasolina, vAlcool, tvResultado;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private Double valorG, valorA, resultadoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplesresultado);
        MobileAds.initialize(this,
                "ca-app-pub-9268401392658071~6252691620");

        vGasolina = findViewById(R.id.tv_valorG);
        vAlcool = findViewById(R.id.tv_valorA);
        tvResultado = findViewById(R.id.tv_resultado);

        // admob banner
        mAdView = findViewById(R.id.adView);
        AdRequest adRequestBanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestBanner);

        // admob interstitial ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9268401392658071/2919685343");
        AdRequest adRequestInterstitial = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequestInterstitial);

        // recebimento de dados
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String stringG = bundle.getString("stringG");
        String stringE = bundle.getString("stringE");

        // calculo
        vGasolina.setText("R$ " + stringG);
        vAlcool.setText("R$ " + stringE);

        valorG = Double.parseDouble(stringG.replaceAll(",", "."));
        valorA = Double.parseDouble(stringE.replaceAll(",", "."));

        resultadoF = valorA / valorG;

        if (resultadoF >= 0.7) {
            tvResultado.setText("Pode abastecer com Gasolina!");
        }else {
            tvResultado.setText("Pode abastecer com álcool!");
        }

    }

    public void voltar(View view) {
        startActivity(new Intent(this, MainActivity.class));
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}