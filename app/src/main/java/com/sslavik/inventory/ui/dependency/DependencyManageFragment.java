package com.sslavik.inventory.ui.dependency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sslavik.inventory.InventoryApplication;
import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DependencyManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DependencyManageFragment extends Fragment implements DependencyManageContract.View, DependencyFragment.OnManageDependencyListener {

    // DELEGADO
    private DependencyManageContract.Presenter dependencyManagePresenter;

    // CAMPOS
    public static final String TAG = "DependencyManageFragment";
    private EditText edShortName;
    private EditText edLongName;
    private Spinner spInventory;
    private EditText edDescription;
    private FloatingActionButton fabAdd;

    public DependencyManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DependencyManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DependencyManageFragment newInstance(Bundle bundle) {
        DependencyManageFragment fragment = new DependencyManageFragment();
        if ( bundle != null ){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dependency_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edShortName = view.findViewById(R.id.edShortName);
        edLongName = view.findViewById(R.id.edLongName);
        edDescription = view.findViewById(R.id.edDescription);
        spInventory = view.findViewById(R.id.spInventory);
        fabAdd = view.findViewById(R.id.fabManageDependency);

        // DEVOLVEMOS EL BUDLE PASADO AL MANAGER
        Bundle bundle = getArguments();

        if(bundle != null) {
            Dependency dependency = bundle.getParcelable("Dependency");
            edShortName.setText(dependency.getShortName());
            edLongName.setText(dependency.getName());
            edDescription.setText(dependency.getDescription());
            spInventory.setSelection(0);
        }

        initializeFab();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // METODOS DE INSTANCIA
    /**
     * Valida la dependencia. Añade o edita.
     */
    private void initializeFab() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDependencyValid())
                    dependencyManagePresenter.validateDependency(getDependency());
            }
        });
    }

    /**
     * Recoge los datos de la vista y se crea una Dependencia.
     *
     * @return
     */
    private Dependency getDependency() {
        Dependency dependency = new Dependency();
        dependency.setName(edLongName.getText().toString());
        dependency.setShortName(edShortName.getText().toString());
        dependency.setInventory(spInventory.getSelectedItem().toString());
        dependency.setDescription(edDescription.getText().toString());
        return dependency;
    }

    /**
     * Comprueba las reglas de negocio del Modelo Dependency
     *
     * @return
     */
    private boolean isDependencyValid() {
        // RN1: campos no vacíos
        if (TextUtils.isEmpty(edLongName.getText().toString())) {
            showError(R.string.errNameEmpty);
            return false;
        }
        if (TextUtils.isEmpty(edShortName.getText().toString())) {
            showError(R.string.errShortNameEmpty);
            return false;
        }
        if (TextUtils.isEmpty(edDescription.getText().toString())) {
            showError(R.string.errDescriptionEmpty);
            return false;
        }
        return true;
    }

        // METODOS IMPLEMENTADOS DE DependencyManageContract

    /**
     * Es llamado desde el Presenter después de comprobar que la dependencia es correcta.
     */
    @Override
    public void onSuccessValidate(Dependency dependency) {
        if(getArguments() != null){
            dependencyManagePresenter.edit(dependency);
        }
        else {
            dependencyManagePresenter.add(dependency);
        }

        // Create an explicit intent for an Activity in your app

        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), InventoryApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_inventory_vector)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(1,builder.build());

        getActivity().onBackPressed();
    }
    // Métodos del contrato DependencyManageContract
    @Override
    public void setPresenter(DependencyManageContract.Presenter presenter) {
        this.dependencyManagePresenter = presenter;
    }

    @Override
    public void showError(int error) {
        Toast.makeText(getContext(), getResources().getString(error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onManageDependency(Dependency dependency, boolean edit) {
        if(edit)
            dependencyManagePresenter.edit(dependency);
        else
            dependencyManagePresenter.add(dependency);


    }
}
