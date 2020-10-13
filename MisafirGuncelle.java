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

public class MisafirGuncelle extends AppCompatActivity {
    TextView tvBilgiTekrar;
    EditText et_TelGuncelle, et_AdrGuncelle, et_SfrGuncelle;
    Button btnKisiGuncelle;

    //musteri cklass ı tanımlandı
    musteri m1= new musteri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_guncelle);

        tvBilgiTekrar=findViewById(R.id.tv_BilgiTekrar);
        et_TelGuncelle=findViewById(R.id.et_TelGuncelle);
        et_AdrGuncelle=findViewById(R.id.et_AdrGuncelle);
        et_SfrGuncelle=findViewById(R.id.et_SfrGuncelle);
        btnKisiGuncelle=findViewById(R.id.btn_KisiGuncelle);


        // MisafirMain den gelen kullanıcı bilgileri alındı
        Intent intent = getIntent();
        m1.ad=intent.getStringExtra("Ad");
        m1.soyad=intent.getStringExtra("Soyad");
        m1.tc=intent.getStringExtra("Tc");

        //Kullanıcı bilgileri textview de yazdırıldı
        tvBilgiTekrar.setText(m1.ad+" "+m1.soyad);

        btnKisiGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Veri tabanı bağlantısı yapıldı
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mesaj = database.getReference();


                //Kullanıcının önceki sayfadan alınan Tc bilgisiyle veritabanından kişi bulunuyor
                final DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);

                //Güncellenmek istenen veriler alınıyor
                m1.adres=et_AdrGuncelle.getText().toString();
                m1.sifre=et_SfrGuncelle.getText().toString();
                m1.telefon=et_TelGuncelle.getText().toString();


                //Gerekli şartın sağlanmasıyla güncelleme işlemi yapılıyor
                if(!m1.telefon.isEmpty() && !m1.adres.isEmpty() && !m1.sifre.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Adres").setValue(m1.adres);
                    oku2.child("Telefon").setValue(m1.telefon);
                    oku2.child("Sifre").setValue(m1.sifre);
                }
                else if(!m1.adres.isEmpty() && !m1.sifre.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Adres").setValue(m1.adres);
                    oku2.child("Sifre").setValue(m1.sifre);
                }
                else if(!m1.adres.isEmpty() && !m1.telefon.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Adres").setValue(m1.adres);
                    oku2.child("Telefon").setValue(m1.telefon);

                }
                else if(!m1.sifre.isEmpty() && !m1.telefon.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Sifre").setValue(m1.sifre);
                    oku2.child("Telefon").setValue(m1.telefon);
                }
                if(!m1.adres.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Adres").setValue(m1.adres);
                }
                else if(!m1.sifre.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Sifre").setValue(m1.sifre);
                }
                else if(!m1.telefon.isEmpty()){
                    final DatabaseReference oku2 = FirebaseDatabase.getInstance().getReference().child("Musteri").child(m1.tc);
                    oku2.child("Telefon").setValue(m1.telefon);

                }
                else if(m1.telefon.isEmpty() && m1.adres.isEmpty() && m1.sifre.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Lütfen Güncellenecek Alanı Doldurun",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
