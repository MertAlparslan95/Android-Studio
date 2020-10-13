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

public class Restoran extends AppCompatActivity {
    EditText etResTc,etResSifre;
    Button btnResGiris;
    isyeri Isyeri=new isyeri();

    String res_tc,res_sifre,tc,sifre;

    //Veritabanı bağlantısı sağlandı
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mesaj = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restoran);

        etResSifre=findViewById(R.id.et_MisSifre);
        etResTc=findViewById(R.id.et_MisTc);
        btnResGiris=findViewById(R.id.btn_MisGiris);



        btnResGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Restoran girişi yapabilmek için girile bilgiler alınıyor
                res_tc=etResTc.getText().toString();
                res_sifre=etResSifre.getText().toString();

                //Giriş yapılabilmesi için veritanbanında Restoran - restoran tc sinin tutulduğu yere gidiliyor
                DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("Restoran").child(res_tc);
                if(res_tc.isEmpty()){
                    Toast.makeText(getApplicationContext(),"TC Giriniz",Toast.LENGTH_SHORT).show();
                }
                else if(res_tc.length()>11 || res_tc.length()<11){
                    Toast.makeText(getApplicationContext(),"TC 11 Haneli Olmalı",Toast.LENGTH_SHORT).show();
                }
                else if(res_sifre.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Şifre Boş Bırakılamaz",Toast.LENGTH_SHORT).show();
                }
                else{
                    ValueEventListener dinle = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Veritabanından tc ve şifre alınıyor
                            tc = dataSnapshot.child("Tc").getValue(String.class);
                            sifre = dataSnapshot.child("Sifre").getValue(String.class);
                            Isyeri.ad=dataSnapshot.child("Ad").getValue(String.class);

                            //Şifreler uyuşuyorsa giriş işlemi yapılıyor
                            if(res_sifre.equals(sifre)){
                                Intent git = new Intent(getApplicationContext(),RestoranMain.class);
                                git.putExtra("Ad",Isyeri.ad);
                                git.putExtra("Tc",res_tc);
                                startActivity(git);
                            }
                            else if(!res_sifre.equals(sifre)){
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
