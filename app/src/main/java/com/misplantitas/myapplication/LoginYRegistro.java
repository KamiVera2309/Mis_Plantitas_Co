package com.misplantitas.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginYRegistro extends AppCompatActivity {


    private EditText editTextContraseña;
    private EditText editTextEmail;
    private Button btnIrAInicio;
    private Button btnIrARegistrarse;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginyregistro);

        //Firebase Autenthication
        mAuth = FirebaseAuth.getInstance();


        editTextContraseña = findViewById(R.id.editTextContraseña);
        editTextEmail = findViewById(R.id.editTextEmail);

        btnIrAInicio = findViewById(R.id.btnIrAInicio);
        btnIrARegistrarse = findViewById(R.id.btnIrARegistrarse);

        Button btnIrARegistrarse = findViewById(R.id.btnIrARegistrarse);
        btnIrARegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Registro.class);
                startActivity(intent);

            }
        });


        Button btnIrAInicio = findViewById(R.id.btnIrAInicio);
        btnIrAInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Obtener los valores de correo electrónico y contraseña
                String email = editTextEmail.getText().toString();
                String password = editTextContraseña.getText().toString();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    // Ahora puedes realizar el inicio de sesión
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginYRegistro.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        try {
                                            // El inicio de sesión fue exitoso
                                            FirebaseUser user = mAuth.getCurrentUser();

                                            // Puedes redirigir a la actividad de inicio o hacer lo que necesites aquí
                                            // Por ejemplo, redirigir a HomePageActivity
                                            Intent intent = new Intent(LoginYRegistro.this, Inicio.class);
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            Log.d("InicioSesion", "Observer: " + e);
                                        }

                                    } else {
                                        // El inicio de sesión falló, mostrar un mensaje de error
                                        Toast.makeText(LoginYRegistro.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Muestra un mensaje de error indicando que el correo electrónico o la contraseña están vacíos.
                    Toast.makeText(LoginYRegistro.this, "Correo electrónico o contraseña vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}