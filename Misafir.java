package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Misafir extends AppCompatActivity {

    Button btnMisGiris,btnMisKayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir);

        //Butonlar tanımlandı
        btnMisGiris=findViewById(R.id.btn_MisGiris);
        btnMisKayit=findViewById(R.id.btn_MisKayit);


        //Üye kaydı yapabilmek için butonlara tıklandığında gerekli sayfalara gitmesi sağlandı
        btnMisKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kayit = new Intent(getApplicationContext(), MisafirKayit.class);
                startActivity(kayit);
            }
        });

        btnMisGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giris = new Intent(getApplicationContext(), MisafirGiris.class);
                startActivity(giris);
            }
        });
    }
}
