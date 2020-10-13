package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MisafirMain extends AppCompatActivity {
    TextView tvBilgi;
    Button btnGuncelle,btnSiparis;
    musteri m1= new musteri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_main);

        tvBilgi=findViewById(R.id.tv_Bilgi);
        btnGuncelle=findViewById(R.id.btn_Guncelle);
        btnSiparis=findViewById(R.id.btn_Siparis);

        //Önceki sayfadan gelen bilgiler alındı
        Intent intent = getIntent();
        m1.ad=intent.getStringExtra("Ad");
        m1.soyad=intent.getStringExtra("Soyad");
        m1.tc=intent.getStringExtra("Tc");
        m1.telefon=intent.getStringExtra("Telefon");


        //Veri tabanı bağlantısı yapıldı
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mesaj = database.getReference();

        tvBilgi.setText(m1.ad+" "+m1.soyad);


        //Butonlara tıklandığında MisafirGuncelle ve MisafirSiparis sayfalarına bilgiler gönderildi
        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent guncelle = new Intent(getApplicationContext(),MisafirGuncelle.class);
                guncelle.putExtra("Tc",m1.tc);
                guncelle.putExtra("Ad",m1.ad);
                guncelle.putExtra("Soyad",m1.soyad);
                startActivity(guncelle);
            }
        });

        btnSiparis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siparis = new Intent(getApplicationContext(),MisafirSiparis.class);
                siparis.putExtra("Ad",m1.ad);
                siparis.putExtra("Soyad",m1.soyad);
                siparis.putExtra("Telefon",m1.telefon);
                startActivity(siparis);
            }
        });


    }
}
