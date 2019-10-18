package com.sslavik.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sslavik.inventory.R;
import com.sslavik.inventory.utils.CommonUtils;

public class SignUpActivity extends AppCompatActivity {

    private Button btSignUp;
    private TextInputEditText tiedUser;
    private TextInputEditText tiedPassword1;
    private TextInputEditText tiedPassword2;
    private TextInputEditText tiedEmail;

    private TextInputLayout tilUser;
    private TextInputLayout tilPassword1;
    private TextInputLayout tilPassword2;
    private TextInputLayout tilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Instancia TextInputEditText
        btSignUp = findViewById(R.id.btSingUpSignUp);
        tiedUser = findViewById(R.id.tiedUser);
        tiedPassword1 = findViewById(R.id.tiedPassword1);
        tiedPassword2 = findViewById(R.id.tiedPassword2);
        tiedEmail = findViewById(R.id.tiedEmail);
        // TextInputLayout
        tilUser = findViewById(R.id.tiedUser);
        tilPassword1 = findViewById(R.id.tiedPassword1);
        tilPassword2 = findViewById(R.id.tiedPassword2);
        tilEmail = findViewById(R.id.tiedEmail);
        // Evento OnClick SignUp
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    /**
     * Valida el estado del formulario para poder insertarlo en la base de datos
     */
    private void validate() {
        if(CommonUtils.validateUser(tiedUser.getText().toString())
                && CommonUtils.validatePassword(tiedPassword1.getText().toString(), tiedPassword2.getText().toString())
                && CommonUtils.validateEmail(tiedEmail.getText().toString())) {
            // 1- Se guarda el usuario en la BD
            // 2- Envio de correo al usuario (Firebase)
            // 3- Se pasa por la ventana LogInActivity
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP));
            // intent.set(Intent.FLAG_ACTIVITY_SINGLE_TOP) Con esto conseguiremos que la activity pueda ser lanzada solo 1 vez

            finish();
        } else
            Toast.makeText(this, "Sign Up Failed.", Toast.LENGTH_SHORT);
    }

    private void errorShow(){
        if(CommonUtils.validateUser(tiedUser.getText().toString())) {
            tilUser.setError(R.string.errUserEmpty);
        }
    }

}
