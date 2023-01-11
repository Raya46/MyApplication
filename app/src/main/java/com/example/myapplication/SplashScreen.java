package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        //ini untuk pengaturan lamanya splash screen berjalan
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(7000); // set Waktu Pending selama 2 detik
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashScreen.this, Menu.class));
                    finish(); // Menutup Activity
                }
            }
        };
        thread.start();
    }
}