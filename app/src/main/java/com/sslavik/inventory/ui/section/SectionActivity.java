package com.sslavik.inventory.ui.section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Section;

public class SectionActivity extends AppCompatActivity {

    // FRAGMENTS IMPLEMENTADOS
    private DependencyFragment dependencyFragment;
    private DependencyManageFragment dependencyManageFragment;
    // PRESENTADORES DE LOS FRAGMENTS
    private DependencyManagePresenter dependencyManagePresenter;
    private DependencyListPresenter dependencyListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
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
        }
        fragmentManager.beginTransaction().replace(R.id.contentDependency, dependencyFragment, DependencyFragment.TAG).commit();
        // Creamos el presentador para el DependencyList
        dependencyListPresenter = new DependencyListPresenter(dependencyFragment);
        dependencyFragment.setPresenter(dependencyListPresenter);
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
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(android.R.id.content, dependencyManageFragment, DependencyManageFragment.TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        dependencyManagePresenter = new DependencyManagePresenter(dependencyManageFragment);
        dependencyManageFragment.setPresenter(dependencyManagePresenter);


    }


    // OVERRIDE METODS FROM ACTIVITY




    // OVERRIDE METHODS FROM LISTENER
    @Override
    public void onManageDependency(Dependency dependency, boolean edit) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Dependency",dependency);
        showAddFragrament(bundle);
    }
}
