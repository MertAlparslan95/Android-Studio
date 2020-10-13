package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RestoranMain extends AppCompatActivity {

    Button btnMenuGuncelle,btnSiparisTakip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran_main);


        //Butonlar tanımlandı ve butonlara tıklandığında sipariştakip ve menu güncelle sayfalarına geçiş sağlnadı
        btnMenuGuncelle = findViewById(R.id.btn_MenuGuncelle);
        btnSiparisTakip = findViewById(R.id.btn_SiparisTakip);

        btnSiparisTakip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takip = new Intent(getApplicationContext(),SiparisTakip.class);
                startActivity(takip);
            }
        });

        btnMenuGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guncelle = new Intent(getApplicationContext(),MenuGuncelle.class);
                startActivity(guncelle);
            }
        });
    }
}
