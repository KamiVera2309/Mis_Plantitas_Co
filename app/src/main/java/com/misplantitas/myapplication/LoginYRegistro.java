package com.misplantitas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginYRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginyregistro);

        Button btnIrARegistrarse = findViewById(R.id.btnIrARegistrarse);
        btnIrARegistrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);

            }
        });

        Button btnIrAInicio = findViewById(R.id.btnIrAInicio);
        btnIrAInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                startActivity(intent);

            }
        });
    }
}