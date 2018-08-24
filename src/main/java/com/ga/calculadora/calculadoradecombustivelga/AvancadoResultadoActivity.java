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

public class AvancadoResultadoActivity extends AppCompatActivity {

    private TextView vGasolina, vAlcool, cGasolina, cAlcool, tvResultado;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private Double valorG, valorA, consumoG, consumoA, kmG, kmA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avancadoresultado);
        MobileAds.initialize(this,
                "ca-app-pub-9268401392658071~6252691620");

        tvResultado = findViewById(R.id.tv_resultado);
        vGasolina = findViewById(R.id.tv_valorG);
        vAlcool = findViewById(R.id.tv_valorA);
        cGasolina = findViewById(R.id.tv_consumoG);
        cAlcool = findViewById(R.id.tv_consumoA);

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

        String strValorG = bundle.getString("strValorG");
        String strValorA = bundle.getString("strValorA");
        String strConsumoG = bundle.getString("strConsumoG");
        String strConsumoA = bundle.getString("strConsumoA");

        // calculo
        vGasolina.setText("R$ " + strValorG);
        vAlcool.setText("R$ " + strValorA);
        cGasolina.setText(strConsumoG + " km/l");
        cAlcool.setText(strConsumoA + " km/l");

        valorG = Double.parseDouble(strValorG.replaceAll(",", "."));
        valorA = Double.parseDouble(strValorA.replaceAll(",", "."));
        consumoG = Double.parseDouble(strConsumoG.replaceAll(",","."));
        consumoA = Double.parseDouble(strConsumoA.replaceAll(",","."));

        kmG = valorG / consumoG * 100;
        kmA = valorA / consumoA * 100;

        if (kmG > kmA){
            tvResultado.setText("Confia! Abasteça com álcool!\n A cada 100km você gastará R$" + String.format("%.2f", kmG));
        }else {
            tvResultado.setText("Confia! Abasteça com gasolina!\n A cada 100km você gastará R$" + String.format("%.2f", kmA));
        }

    }

    public void voltar(View view) {
        startActivity(new Intent(this, MainActivity.class));
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }
}
