package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MisafirKayit extends AppCompatActivity {
    EditText etAd,etSoyad,etTc,etTelefon,etAdres,etSifre,etSifreTekrar;
    Button btnUyeKayit;
    musteri m = new musteri();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_kayit);

        //Editext ve butonlar tanımlandı
        etAd=findViewById(R.id.et_Ad);
        etSoyad=findViewById(R.id.et_Soyad);
        etTc=findViewById(R.id.et_Tc);
        etTelefon=findViewById(R.id.et_Telefon);
        etAdres=findViewById(R.id.et_Adres);
        etSifre=findViewById(R.id.et_Sifre);
        etSifreTekrar=findViewById(R.id.et_SifreTekrar);
        btnUyeKayit=findViewById(R.id.btn_UyeKayit);

        //Veritabanı bağlantısı yapıldı
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mesaj = database.getReference();



        btnUyeKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Kullancının girdiği bilgiler alındı
                m.ad=etAd.getText().toString();
                m.soyad=etSoyad.getText().toString();
                m.telefon=etTelefon.getText().toString();
                m.tc=etTc.getText().toString();
                m.adres=etAdres.getText().toString();
                m.sifre=etSifre.getText().toString();
                m.sifreTekrar=etSifreTekrar.getText().toString();
                String sifre1=etSifre.getText().toString();
                String sifre2=etSifreTekrar.getText().toString();

                //Girilen şifreler aynıysa kayıt işlemi yapılır
                if(sifre1.equals(sifre2)){
                    final DatabaseReference oku1 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(etTc.getText().toString());
                    oku1.setValue(etTc.getText().toString());
                    oku1.child("Ad").setValue(m.ad);
                    oku1.child("Soyad").setValue(m.soyad);
                    oku1.child("Adres").setValue(m.adres);
                    oku1.child("Sifre").setValue(m.sifre);
                    oku1.child("Telefon").setValue(m.telefon);
                    oku1.child("Tc").setValue(m.tc);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Şifreler Uyuşmuyor Lütfen Tekrar Deneyin",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
