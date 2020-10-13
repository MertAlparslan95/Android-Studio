package com.example.yemekgetir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SiparisTakip extends AppCompatActivity {

    ListView listView;

    Integer i;

    //Siparişleri listelemek için arraylist ve arrayadapter tanımlandı
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    String value[],value2[];

    musteri m1=new musteri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparis_takip);

        listView=findViewById(R.id.listview);

        //Listeleme yapıldı
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


        //Veri tabanı bağlantısı yapıldı
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mesaj = database.getReference();
        final DatabaseReference oku = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Restoran").child("Siparisler");


        //Siparişler listelendi
        final ValueEventListener dinle = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){

                    String value11 = childDataSnapshot.child("                      Tarih Saat").getKey();
                    String value12 = childDataSnapshot.child("Tarih Saat").getValue(String.class);

                    String value = childDataSnapshot.child("Ad Soyad").getKey();
                    String value2 = childDataSnapshot.child("Ad Soyad").getValue(String.class);


                    String value3 = childDataSnapshot.child("Siparis Detay").getKey();
                    String value4 = childDataSnapshot.child("Siparis Detay").getValue(String.class);


                    String value5 = childDataSnapshot.child("Not").getKey();
                    String value6 = childDataSnapshot.child("Not").getValue(String.class);


                    String value7 = childDataSnapshot.child("Telefon").getKey();
                    String value8 = childDataSnapshot.child("Telefon").getValue(String.class);


                    String value9 = childDataSnapshot.child("Siparis Adresi").getKey();
                    String value10 = childDataSnapshot.child("Siparis Adresi").getValue(String.class);

                    list.add(value11+ ": " +value12);
                    list.add(value+ ": " +value2);
                    list.add(value3+ ": " +value4);
                    list.add(value5+ ": " +value6);
                    list.add(value7+ ": " +value8);
                    list.add(value9+ ": " +value10);

                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        oku.addValueEventListener(dinle);
    }
}
