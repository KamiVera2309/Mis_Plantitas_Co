package com.misplantitas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextContraseña;
    private EditText editTextEmail;
    private Button btnRegistrar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        Button btnVolver = findViewById(R.id.btnVolver);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        editTextEmail = findViewById(R.id.editTextEmail);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnVolver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginYRegistro.class);
                startActivity(intent);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString();
                String password = editTextContraseña.getText().toString();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Registro.this, LoginYRegistro.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(Registro.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }
    }

