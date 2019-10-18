package com.sslavik.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sslavik.inventory.R;

public class LoginActivity extends AppCompatActivity {

    // Campos
    private Button btSignIn;
    private Button btSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Instanciamos los Botones
        btSignIn = findViewById(R.id.btSignIn);
        btSignUp = findViewById(R.id.btSignUpLogIn);
        // Creamos un evento
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, DashActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
        //Creamos un evento para Registrarnos
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
    }
}
