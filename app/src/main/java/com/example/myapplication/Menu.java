package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    ImageButton goSiswa, goGuru; // Variable ImageButton


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        goSiswa = findViewById(R.id.datasiswa);
        goGuru = findViewById(R.id.dataguru);

        goSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Digunakan untuk berpindah activity dari activity satu ke activity dua
                startActivity(new Intent(Menu.this,TambahPD.class));
            }
        });
        goGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Digunakan untuk berpindah activity dari activity satu ke activity dua
                startActivity(new Intent(Menu.this,TambahGuru.class));
            }
        });
    }


}