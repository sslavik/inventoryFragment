package com.sslavik.inventory.ui.dependency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sslavik.inventory.R;
import com.sslavik.inventory.adapter.DependencyAdapter;
import com.sslavik.inventory.data.model.Dependency;

import android.os.Bundle;
import android.widget.Toast;

public class DependencyListActivity extends AppCompatActivity implements DependencyAdapter.OnClickHolderListener {


    // CAMPOS
    private static final int SPAN_COUNT = 3;
    private RecyclerView rvDependency;
    private DependencyAdapter dependencyAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependecy);
        // INSTANCIAMOS RecyclerView
        rvDependency = findViewById(R.id.rvDependency);
        // INSTANCIAMOS ADAPTER
        dependencyAdapter = new DependencyAdapter(this, this);
        // Creamos diseño del RecyclerView
        // OPCION 1
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        // OPCION 2
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT, RecyclerView.VERTICAL, false);
        // Vincular vista al modelo
        rvDependency.setLayoutManager(linearLayoutManager);
        rvDependency.setAdapter(dependencyAdapter);


    }

    @Override
    public void onClick(Dependency dependency) {
        Toast.makeText(this, dependency.toString(), Toast.LENGTH_SHORT).show();
    }
}
