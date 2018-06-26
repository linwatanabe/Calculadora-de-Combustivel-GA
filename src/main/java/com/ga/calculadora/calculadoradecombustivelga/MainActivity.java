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
    private String stringG, stringE;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        vGasolina = findViewById(R.id.et_vGasolina);
        vAlcool = findViewById(R.id.et_vAlcool);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequestBanner = new AdRequest.Builder().build();
        mAdView.loadAd(adRequestBanner);

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

                Intent intent = new Intent(this, ResultadoActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("stringG", stringG);
                bundle.putString("stringE", stringE);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        }

    }

}