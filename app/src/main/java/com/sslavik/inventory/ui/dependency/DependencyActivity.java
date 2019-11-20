package com.sslavik.inventory.ui.dependency;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sslavik.inventory.R;
import com.sslavik.inventory.adapter.DependencyAdapter;
import com.sslavik.inventory.data.model.Dependency;

public class DependencyActivity extends AppCompatActivity implements DependencyAddFragment.OnFragmentInteractionListener ,DependencyAdapter.OnClickHolderListener, DependencyFragment.OnAddDependencyListener {

    //Fragments que tiene
    private DependencyFragment dependencyFragment;
    private DependencyAddFragment dependencyAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency);

        showListFragmets();

    }


    /**
     * Inicializa el contexto del fragmento
     */
    private void showListFragmets() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyFragment = (DependencyFragment) fragmentManager.findFragmentByTag(DependencyFragment.TAG);

        if(dependencyFragment == null){
            dependencyFragment = (DependencyFragment) DependencyFragment.newInstance(null);
            fragmentManager.beginTransaction().add(android.R.id.content, dependencyFragment, DependencyFragment.TAG).commit();
        }
    }

    @Override
    public void onClick(Dependency dependency) {
        Toast.makeText(this, dependency.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddDependency() {
        showAddFragrament();
    }

    private void showAddFragrament() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyAddFragment = (DependencyAddFragment)fragmentManager.findFragmentByTag(DependencyAddFragment.TAG);
        if(dependencyAddFragment == null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            dependencyAddFragment = DependencyAddFragment.newInstance(null);
            fragmentTransaction.replace(android.R.id.content, dependencyAddFragment, DependencyAddFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
