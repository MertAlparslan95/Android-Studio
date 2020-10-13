package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class SiparisOnay extends AppCompatActivity {
    musteri Musteri=new musteri();
    String siparis;
    TextView tvListe;
    EditText etNot,etAdresOnay;
    Button btnSiparisOnay;
    String not,adres2;
    musteri m1=new musteri();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_onay);

        tvListe=findViewById(R.id.tv_Liste);
        etAdresOnay=findViewById(R.id.et_AdresOnay);
        etNot=findViewById(R.id.et_Not);
        btnSiparisOnay=findViewById(R.id.btn_SiparisOnay);

        //Veri tabanı bağlantısı yapıldı
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mesaj = database.getReference();


        //Önceki sayfadan alınan gönderilen bilgiler alındı
        Intent intent=getIntent();
        siparis=intent.getStringExtra("selected");
        m1.ad=intent.getStringExtra("Ad");
        m1.soyad=intent.getStringExtra("Soyad");
        m1.telefon=intent.getStringExtra("Telefon");
        tvListe.setText(siparis);


        //Veri tabanına yapılan siparişin tarih ve saat olarak kaydedilmesi için sistem tarih ve saat bilgileri alındı
        final int currentDay= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        final int currentMonth=Calendar.getInstance().get(Calendar.MONTH)+1;
        final int currentYear=Calendar.getInstance().get(Calendar.YEAR);
        final int currentHour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        final int currentMinute=Calendar.getInstance().get(Calendar.MINUTE);


        final String current=(currentDay+" "+currentMonth+" "+currentYear+" "+currentHour+" "+currentMinute );



        btnSiparisOnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Kullanıcının giridiği not ve adres bilgisi alındı
                not=etNot.getText().toString();
                adres2=etAdresOnay.getText().toString();

                //Veritabanında Restoran - Siparişler - Tarih ve saat altında bulunan yere sipariş onaylanınca kaydolması sağlandı
                final DatabaseReference oku1 = FirebaseDatabase.getInstance().getReference().child("Restoran").child("Siparisler").child(current);
                oku1.child("Siparis Detay").setValue(siparis);
                oku1.child("Tarih Saat").setValue(current);
                oku1.child("Ad Soyad").setValue(m1.ad+" "+m1.soyad);
                oku1.child("Siparis Adresi").setValue(adres2);
                oku1.child("Telefon").setValue(m1.telefon);
                oku1.child("Not").setValue(not);



                Toast.makeText(getApplicationContext(),siparis,Toast.LENGTH_SHORT).show();

            }
        });









    }
}
