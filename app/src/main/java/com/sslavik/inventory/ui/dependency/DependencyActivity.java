package com.sslavik.inventory.ui.dependency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;

public class DependencyActivity extends AppCompatActivity implements DependencyFragment.OnManageDependencyListener {

    //Fragments que tiene
    private DependencyFragment dependencyFragment;
    private DependencyManageFragment dependencyManageFragment;

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


    // Muestra el fragmento con los datos o sin ellos
    private void showAddFragrament(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyManageFragment = (DependencyManageFragment)fragmentManager.findFragmentByTag(DependencyManageFragment.TAG);
        if(dependencyManageFragment == null) {
            if(bundle.getParcelable("Dependency") == null) {
                dependencyManageFragment = DependencyManageFragment.newInstance(null);
            } else {
                dependencyManageFragment = DependencyManageFragment.newInstance(bundle);
            }
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, dependencyManageFragment, DependencyManageFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onManageDependency(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Dependency",dependency);
        showAddFragrament(bundle);
    }
}
