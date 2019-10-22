package com.sslavik.inventory.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
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






}

