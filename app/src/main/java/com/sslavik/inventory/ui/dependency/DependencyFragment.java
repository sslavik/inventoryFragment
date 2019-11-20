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

public class DependencyFragment extends Fragment implements DependencyAdapter.OnClickHolderListener {


    //INTERFAZ
    interface OnAddDependencyListener{
        void onAddDependency();
    }



    public static final String TAG = "DependencyFragment" ;
    // CAMPOS
    private static final int SPAN_COUNT = 3;
    private RecyclerView rvDependency;
    private DependencyAdapter dependencyAdapter ;
    private FloatingActionButton fabAdd;
    private OnAddDependencyListener onAddDependencyListener;


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
            onAddDependencyListener = (OnAddDependencyListener) context;
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
                onAddDependencyListener.onAddDependency();
            }
        });
    }

    private void initRecyclerViewDependency() {

        // INSTANCIAMOS ADAPTER
        dependencyAdapter = new DependencyAdapter(getActivity(), this);
        // Creamos diseño del RecyclerView
        // OPCION 1
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        // OPCION 2
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);
        // Vincular vista al modelo
        rvDependency.setLayoutManager(linearLayoutManager);
        rvDependency.setAdapter(dependencyAdapter);
    }

    @Override
    public void onClick(Dependency dependency) {
        Toast.makeText(getActivity(), dependency.toString(), Toast.LENGTH_SHORT).show();
    }
}
