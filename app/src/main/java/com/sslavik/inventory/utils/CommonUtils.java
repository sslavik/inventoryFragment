package com.sslavik.inventory.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sslavik.inventory.R;

/**
 * Estas clases no se pueden heredar por eso se indica que son final
 */
public class CommonUtils {

    /**
     * Tiene que ser un email valido
     * @param text Email
     * @return Valida Correo
     */
    public static boolean validateEmail(String text) {
        Log.d("SignIn", text + " Valido Email : " + text.matches("^.+@.+\\.[A-Za-z]{2,}$"));
        return text.matches("^.+@.+\\.[A-Za-z]{2,}$");
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
    public static boolean validatePassword(String pass1, String pass2) {
        Log.d("SignIn", " Equals : " + pass1.equals(pass2) + "Length: " + (pass1.length() >= 8 && pass1.length() <= 12) + " mayus : " + pass1.matches(".*([A-Z]){1,}.*") + " num : " + pass1.matches(".*([0-9]){1,}.*"));
        return pass1.length() >= 8
                && pass1.length() <= 12
                && pass1.equals(pass2)
                && pass1.matches(".*([A-Z]){1,}.*")
                && pass1.matches(".*([0-9]){1,}.*");
    }

    /**
     *
     * @param text Nombre del usuario que no puede ser menor que 4
     * @return Valida Nombre
     */
    public static boolean validateUser(String text) {
        return text.length() >= 4;
    }


}

class SignUpWatcher implements TextWatcher {

    private View view;
    public SignUpWatcher(View text, View parentText){
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
            case R.id.tilUser:
                if(CommonUtils.validateUser(((EditText)view).getText().toString()))
                    ((EditText) view).setError("");

        }
    }
}