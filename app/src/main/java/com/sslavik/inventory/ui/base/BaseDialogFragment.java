package com.sslavik.inventory.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.sslavik.inventory.R;
import com.sslavik.inventory.ui.dependency.DependencyFragment;

public class BaseDialogFragment extends DialogFragment {

    // Listener del DIALOGFRAGMENT
    public interface OnFinishDialog{
        // CUANDO SE PULSA ACEPTAR
        void onFinishDialog();
    }

    public static final String TITLE = "title";
    public static final String MESSSAGE = "message";
    public static final String TAG = "dialog" ;


    public static BaseDialogFragment newInstance (Bundle bundle){

        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();

        if(bundle != null){
            baseDialogFragment.setArguments(bundle);
        }

        return baseDialogFragment;
    }

    // METODOS EXTENDIDOS


    @Override
    public void setTargetFragment(@Nullable Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // OBTENEMOS LOS DATOS DEL BUNDLE INSTANCIADO EN EL CONSTRUCTOR
        String title = getArguments().getString(TITLE);
        String message = getArguments().getString(MESSSAGE);
        // CREAMOS EL DIALOG
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnFinishDialog onFinishDialogListener = (OnFinishDialog) getTargetFragment();
                onFinishDialogListener.onFinishDialog();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
