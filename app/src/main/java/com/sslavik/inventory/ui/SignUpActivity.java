package com.sslavik.inventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
        tilUser = findViewById(R.id.tilUser);
        tilPassword1 = findViewById(R.id.tilPassword1);
        tilPassword2 = findViewById(R.id.tilPassword2);
        tilEmail = findViewById(R.id.tilEmail);
        // TextChanged
        tiedUser.addTextChangedListener(new SignUpWatcher(tiedUser));
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
        if(validateUser(tiedUser)
                && validatePassword(tiedPassword1, tiedPassword2)
                && CommonUtils.validateEmail(tiedEmail.getText().toString())) {
            // 1- Se guarda el usuario en la BD
            // 2- Envio de correo al usuario (Firebase)
            // 3- Se pasa por la ventana LogInActivity
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            // intent.set(Intent.FLAG_ACTIVITY_SINGLE_TOP) Con esto conseguiremos que la activity pueda ser lanzada solo 1 vez

            finish();
            Toast.makeText(this, "SignUp Successful.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SignUp Failed.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 1. Comprueba que sean iguales
     * 2. Que tenga minimo 8 caracteres.
     * 3. 1 Mayuscula
     * 4. 1 Número
     * 5. No puede ser vacio
     * @return Valida Contraseña
     */
    private boolean validatePassword(View view, View view2) {
        String pass1 = ((TextInputEditText)view).getText().toString();
        String pass2 = ((TextInputEditText)view2).getText().toString();
        Log.d("SignIn", " Equals : " + pass1.equals(pass2) + "Length: " + (pass1.length() >= 8 && pass1.length() <= 12) + " mayus : " + pass1.matches(".*([A-Z]){1,}.*") + " num : " + pass1.matches(".*([0-9]){1,}.*"));
        if(pass1.length() >= 8
                && pass1.length() <= 12
                && pass1.equals(pass2)
                && pass1.matches(".*([A-Z]){1,}.*")
                && pass1.matches(".*([0-9]){1,}.*")){
            return true;
        } else {
            displaySoftKeyboard(view);
            tilPassword1.setError(getString(R.string.errEmail));
            return false;
        }
    }

    /**
     * @return Valida Nombre
     */
    private boolean validateUser(View view) {
        if(((TextInputEditText)view).getText().toString().length() >= 4)
            return true;
        else {
            displaySoftKeyboard(view);
            tilUser.setError(getString(R.string.errUserEmpty));
            return false;
        }
    }


    private void displaySoftKeyboard(View view){
        if(view.requestFocus())
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }


    private class SignUpWatcher implements TextWatcher {

        private View view;
        public SignUpWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()){
                case R.id.tiedUser:
                    if(!validateUser(view)){
                        tilUser.setError(getString(R.string.errUserEmpty));
                    }
                    else tilUser.setError(null);
                    break;
                case R.id.tiedPassword1:
                    if(!validatePassword(view, tiedPassword2)){
                        tilPassword1.setError(getString(R.string.errPassword));
                    }
                    else tilPassword1.setError(null);
                    break;
                case R.id.tiedEmail:
                    if(!CommonUtils.validateEmail(((TextInputEditText)view).getText().toString())){
                        tilEmail.setError(getString(R.string.errEmail));
                    }
                    else tilEmail.setError(null);
                    break;

            }
        }
    }

}
