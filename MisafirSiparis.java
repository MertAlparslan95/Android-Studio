package com.example.yemekgetir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MisafirSiparis extends AppCompatActivity {


    ListView listView;
    Button btn_SiparisTamam;

    Integer i;

    //Veri tabanında tutulan yiyecek menusunu listelemek için ArrayList ve ArrayAdapter tanıımlandı
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    String value[],value2[];

    musteri m1=new musteri();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misafir_siparis);

        listView=findViewById(R.id.listview);
        btn_SiparisTamam=findViewById(R.id.btn_SiparisTamam);

        //Listview oluştulrdu multi choice özelliğiyle birden fazla seçim yapılması sağlandı
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setAdapter(adapter);


        //Bir sonraki sayfada kullanmak için bilgiler gönderilir
        Intent intent = getIntent();
        m1.ad=intent.getStringExtra("Ad");
        m1.soyad=intent.getStringExtra("Soyad");
        m1.telefon=intent.getStringExtra("Telefon");



        //Veritabanına bağlantı sağlanıyor
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mesaj = database.getReference();
        final DatabaseReference oku = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Restoran").child("Yiyecekler");

        final ValueEventListener dinle = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Veritabanından listeleme yapılıyor
                for(DataSnapshot childDataSnapshot : dataSnapshot.getChildren()){

                    String value = childDataSnapshot.child("Ezogelin").getKey();
                    String value2 = childDataSnapshot.child("Ezogelin").getValue(String.class);

                    String value3 = childDataSnapshot.child("Tarhana").getKey();
                    String value4 = childDataSnapshot.child("Tarhana").getValue(String.class);

                    String value5 = childDataSnapshot.child("Iskembe").getKey();
                    String value6 = childDataSnapshot.child("Iskembe").getValue(String.class);

                    String value7 = childDataSnapshot.child("Adana").getKey();
                    String value8 = childDataSnapshot.child("Adana").getValue(String.class);

                    String value9 = childDataSnapshot.child("Beyti").getKey();
                    String value10 = childDataSnapshot.child("Beyti").getValue(String.class);

                    String value11 = childDataSnapshot.child("Urfa").getKey();
                    String value12 = childDataSnapshot.child("Urfa").getValue(String.class);

                    String value13 = childDataSnapshot.child("Ayran").getKey();
                    String value14 = childDataSnapshot.child("Ayran").getValue(String.class);

                    String value15 = childDataSnapshot.child("Kola").getKey();
                    String value16 = childDataSnapshot.child("Kola").getValue(String.class);

                    String value17 = childDataSnapshot.child("Su").getKey();
                    String value18 = childDataSnapshot.child("Su").getValue(String.class);

                    String value19 = childDataSnapshot.child("Baklava").getKey();
                    String value20 = childDataSnapshot.child("Baklava").getValue(String.class);

                    String value21 = childDataSnapshot.child("Kunefe").getKey();
                    String value22= childDataSnapshot.child("Kunefe").getValue(String.class);

                    String value23 = childDataSnapshot.child("Sutlac").getKey();
                    String value24 = childDataSnapshot.child("Sutlac").getValue(String.class);


                    list.add(value+ ": " +value2+" "+"TL" );
                    list.add(value3+ ": " +value4+" "+"TL" );
                    list.add(value5+ ": " +value6+" "+"TL" );
                    list.add(value7+ ": " +value8+" "+"TL" );
                    list.add(value9+ ": " +value10+" "+"TL" );
                    list.add(value11+ ": " +value12+" "+"TL" );
                    list.add(value13+ ": " +value14+" "+"TL" );
                    list.add(value15+ ": " +value16+" "+"TL" );
                    list.add(value17+ ": " +value18+" "+"TL" );
                    list.add(value19+ ": " +value20+" "+"TL" );
                    list.add(value21+ ": " +value22+" "+"TL" );
                    list.add(value23+ ": " +value24+" "+"TL" );


                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        oku.addValueEventListener(dinle);

        btn_SiparisTamam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Menuden yemek seçimi yapılıyor
                String selected="";
                int cntChoise=listView.getCount();
                SparseBooleanArray sparseBooleanArray=listView.getCheckedItemPositions();
                for(i=0;i<cntChoise;i++){
                    if(sparseBooleanArray.get(i)){
                        selected+=listView.getItemAtPosition(i).toString()+"\n";
                    }
                }

                //Seçilen yemekler ad soyad telefon bilgileri Sipariş Onay sayfasına yönlendiriliyor
                Intent onay = new Intent(getApplicationContext(),SiparisOnay.class);
                onay.putExtra("selected",selected);
                onay.putExtra("Ad",m1.ad);
                onay.putExtra("Soyad",m1.soyad);
                onay.putExtra("Telefon",m1.telefon);
                startActivity(onay);

            }
        });


    }

}

