package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuGuncelle extends AppCompatActivity {



    EditText etGuncelUrunAd,etGuncelurunFiyat;
    Button btnUrunGuncelle,btnUrunSil;
    isyeri i = new isyeri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_guncelle);


        //EditText buton ve işyeri classı tanımlandı
        etGuncelUrunAd=findViewById(R.id.et_GuncelUrunAd);
        etGuncelurunFiyat=findViewById(R.id.et_GuncelurunFiyat);
        btnUrunGuncelle=findViewById(R.id.btn_UrunGuncelle);
        btnUrunSil=findViewById(R.id.btn_UrunSil);


        btnUrunGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Güncelle butonuna tıklandığında kullanıcının girdiği degerler alındı

                i.yemek=etGuncelUrunAd.getText().toString();
                i.fiyat=etGuncelurunFiyat.getText().toString();

                //Veritabanına bağlantı sağlandı
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mesaj = database.getReference();


                //Alınan veriler veritabanında Restoran - Yiyecekler - Yemekler altına eklenir veye güncellenir
                final DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("Restoran").child("Yiyecekler").child("Yemekler");
                oku.child(i.yemek).setValue(i.fiyat);

            }
        });

        btnUrunSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Sil butonuna tıklandığında kullanıcının girdiği degerler alındı

                i.yemek=etGuncelUrunAd.getText().toString();
                i.fiyat=etGuncelurunFiyat.getText().toString();


                //Veritabanına bağlantı sağlandı
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mesaj = database.getReference();


                //İsmi girilen yemeği silmek için Restoran - Yiyecekler - Yemekler sekmesinden yemek bulunur ve silinir
                final DatabaseReference oku = FirebaseDatabase.getInstance().getReference().child("Restoran").child("Yiyecekler").child("Yemekler");
                oku.child(i.yemek).setValue(null);

            }
        });
    }
}
