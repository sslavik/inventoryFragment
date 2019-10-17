package com.sslavik.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    private Button btSignUp;
    private TextInputEditText tiedUser;
    private TextInputEditText tiedPassword1;
    private TextInputEditText tiedPassword2;
    private TextInputEditText tiedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Instancia
        btSignUp = findViewById(R.id.btSingUpSignUp);
        tiedUser = findViewById(R.id.tiedUser);
        tiedPassword1 = findViewById(R.id.tiedPassword1);
        tiedPassword2 = findViewById(R.id.tiedPassword2);
        tiedEmail = findViewById(R.id.tiedEmail);
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
        if(validateUser(tiedUser.getText().toString())
                && validatePassword(tiedPassword1.getText().toString(), tiedPassword2.getText().toString())
                && validateEmail(tiedEmail.getText().toString())) {
            // 1- Se guarda el usuario en la BD
            // 2- Envio de correo al usuario (Firebase)
            // 3- Se pasa por la ventana LogInActivity
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            // intent.set(Intent.FLAG_ACTIVITY_SINGLE_TOP)

            finish();
        }
    }

    /**
     * Tiene que ser un email valido
     * @param text Email
     * @return Valida Correo
     */
    private boolean validateEmail(String text) {
        return text.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$");
    }

    /**
     * 1. Comprueba que sean iguales
     * 2. Que tenga minimo 8 caracteres.
     * 3. 1 Mayuscula
     * 4. 1 Número
     * 5. No puede ser vacio
     * @param pass1 Contraseña
     * @param pass2 Confirmar Contraseña
     * @return Valida Contraseña
     */
    private boolean validatePassword(String pass1, String pass2) {
        return pass1.length() > 6 && pass1.equals(pass2);
    }

    /**
     *
     * @param text Nombre del usuario que no puede ser menor que 4
     * @return Valida Nombre
     */
    private boolean validateUser(String text) {
        return text.length() >= 4;
    }
}
