package com.example.yemekgetir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MisafirGiris extends AppCompatActivity {

    EditText etTcGiris,etSifreGiris;
    Button btnGiris;

    //Veri tabanı bağlantısı tanımlandı
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mesaj = database.getReference();

    //Musteri class ı tanımlandı
    musteri Musteri=new musteri();
    String uye_tc,uye_sifre,tc,sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_giris);

        etTcGiris=findViewById(R.id.et_TcGiris);
        etSifreGiris=findViewById(R.id.et_SifreGiris);
        btnGiris=findViewById(R.id.btn_Giris);


        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Kullanıcının girdiği bilgiler alındı
                uye_tc=etTcGiris.getText().toString();
                uye_sifre=etSifreGiris.getText().toString();


                //Kullanıcının sisteme girmesi için Veritabanındaki Müşteri sekmesine gidildi

                DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("Musteri").child(uye_tc);
                if(uye_tc.isEmpty()){
                    Toast.makeText(getApplicationContext(),"TC Giriniz",Toast.LENGTH_SHORT).show();
                }
                else if(uye_tc.length()>11 || uye_tc.length()<11){
                    Toast.makeText(getApplicationContext(),"TC 11 Haneli Olmalı",Toast.LENGTH_SHORT).show();
                }
                else if(uye_sifre.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Şifre Boş Bırakılamaz",Toast.LENGTH_SHORT).show();
                }
                else{
                    ValueEventListener dinle = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Müştrei bilgileri veri tabanına Tc,Sifre gibi isimlerle bilgiler alındı
                            tc = dataSnapshot.child("Tc").getValue(String.class);
                            sifre = dataSnapshot.child("Sifre").getValue(String.class);
                            Musteri.ad=dataSnapshot.child("Ad").getValue(String.class);
                            Musteri.soyad=dataSnapshot.child("Soyad").getValue(String.class);
                            Musteri.telefon=dataSnapshot.child("Telefon").getValue(String.class);

                            //Veritabanındaki şifreyle girilen şifre aynı mı diye kontrol edildi
                            if(uye_sifre.equals(sifre)){

                                //Daha sonra kullanmak için kullanıcının bilgileri MisafirMain sayfasına gönderildi
                                Intent git = new Intent(getApplicationContext(),MisafirMain.class);
                                git.putExtra("Ad",Musteri.ad);
                                git.putExtra("Soyad",Musteri.soyad);
                                git.putExtra("Tc",uye_tc);
                                git.putExtra("Telefon",Musteri.telefon);
                                startActivity(git);



                            }
                            else if(!uye_sifre.equals(sifre)){
                                Toast.makeText(getApplicationContext(),"Şifre yada Giriş Yöntemi Yanlış",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    };
                    oku.addValueEventListener(dinle);
                }

            }
        });
    }
}
