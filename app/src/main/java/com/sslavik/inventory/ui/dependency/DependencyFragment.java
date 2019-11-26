package com.sslavik.inventory.ui.dependency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sslavik.inventory.R;
import com.sslavik.inventory.adapter.DependencyAdapter;
import com.sslavik.inventory.data.model.Dependency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class DependencyFragment extends Fragment implements DependencyListContract.View {

    // DELEGADOS
    // Este objeto recoge los eventos del adapter
    private DependencyAdapter.OnManageDependencyListener onManageDependencyAdapterListener;
    private DependencyListContract.Presenter presenter;

    //INTERFAZ
    interface OnManageDependencyListener {
        void onManageDependency(Dependency dependency);
    }


    // CAMPOS
    public static final String TAG = "DependencyFragment" ;
    private static final int SPAN_COUNT = 3;
    private RecyclerView rvDependency;
    private DependencyAdapter dependencyAdapter ;
    private FloatingActionButton fabAdd;
    private OnManageDependencyListener onManageDependencyListener;


    // INSTANCIA DE UN FRAGMENT DINAMICO
    public static Fragment newInstance(Bundle bundle){
        DependencyFragment fragment = new DependencyFragment();
        if( bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onManageDependencyListener = (OnManageDependencyListener) context;
        } catch (Exception e){
            throw e;
        }

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dependecy, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // PASADO DEL OnCreate del Acitvity
        // INSTANCIAMOS FloatingButton
        fabAdd = view.findViewById(R.id.fabAdd);

        // INSTANCIAMOS RecyclerView
        rvDependency = view.findViewById(R.id.rvDependency);

        // INSTANCIAMOS el recycler View en un Metodo
        initRecyclerViewDependency();

        // Metodos del FloatingActionButton
        initFabAdd();


    }

    private void initFabAdd() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onManageDependencyListener.onManageDependency(null);
            }
        });
    }

    private void initRecyclerViewDependency() {

        // Inicializamos el Delegado del DependencyAdapter
        initDependencyAdapterListener(onManageDependencyListener);

        // INSTANCIAMOS ADAPTER
        dependencyAdapter = new DependencyAdapter(getActivity(), onManageDependencyAdapterListener);
        // Creamos diseño del RecyclerView
        // OPCION 1
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        // OPCION 2
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        // Vincular vista al modelo
        rvDependency.setLayoutManager(linearLayoutManager);
        rvDependency.setAdapter(dependencyAdapter);
    }

    private void initDependencyAdapterListener(final OnManageDependencyListener onManageDependencyListener) {

        onManageDependencyAdapterListener = new DependencyAdapter.OnManageDependencyListener() {

            // METODOS DEL INTERFAZ DependencyAdapter

            /**
             * Se ha pulsado sobre un elemento de la lista y hay que mostrar el fragment DependencymanageFragment. Se llama al método de la Activity
             * Para mostar el fragment
             * @param dependency
             */
            @Override
            public void onUpdateDependency(Dependency dependency) {
                Toast.makeText(getActivity(), "Click en : " + dependency.toString(), Toast.LENGTH_SHORT).show();
                onManageDependencyListener.onManageDependency(dependency);
            }
            /**
             * Este método muestra un cuadro de dialogo pidiendo la confirmación del borrado de la dependencia
             * @param dependency
             */
            @Override
            public void onDeleteDependency(Dependency dependency) {
                Toast.makeText(getActivity(), "Intento de borrado en la dependencia :" + dependency.getName(), Toast.LENGTH_SHORT).show();
            }
        };

    }

    //region IMPLEMENTACIÓN DE INTERFACES


    @Override
    public void showLoadProgress() {

    }

    @Override
    public void hideLoadProgress() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(List<Dependency> dependencyList) {

    }

    @Override
    public void setPresenter(DependencyListContract.Presenter presenter) {

    }

    @Override
    public void showError(String error) {

    }

    //endregion

}
