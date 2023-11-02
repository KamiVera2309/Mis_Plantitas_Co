package com.misplantitas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia la actividad principal después del tiempo de espera

                Intent intent = new Intent(Splash.this, LoginYRegistro.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}