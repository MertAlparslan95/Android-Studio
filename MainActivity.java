package com.example.yemekgetir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnRestoran,btnMisafir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buton tanımlamaları yapılarak butonlara tıklandığında yeni sayfalara geçişleri sağlandı

        btnMisafir=findViewById(R.id.btn_Misafir);
        btnRestoran=findViewById(R.id.btn_Restoran);

        btnRestoran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restoran = new Intent(getApplicationContext(),Restoran.class);
                startActivity(restoran);
            }
        });

        btnMisafir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent misafir = new Intent(getApplicationContext(),Misafir.class);
                startActivity(misafir);

            }
        });


    }
}
