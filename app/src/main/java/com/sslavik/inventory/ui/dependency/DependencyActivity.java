package com.sslavik.inventory.ui.dependency;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.ui.base.BaseActivity;

public class DependencyActivity extends BaseActivity implements DependencyFragment.OnManageDependencyListener {

    // FRAGMENTS IMPLEMENTADOS
    private DependencyFragment dependencyFragment;
    private DependencyManageFragment dependencyManageFragment;
    // PRESENTADORES DE LOS FRAGMENTS
    private DependencyManagePresenter dependencyManagePresenter;
    private DependencyListPresenter dependencyListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency);
        showListFragmets();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(getIntent().getBooleanExtra("notification", false)){
            Log.d("PARCELABLE", getIntent().getExtras().getParcelable("Dependency").toString());
            showAddFragrament(getIntent().getExtras());
        }
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
            fragmentTransaction.replace(R.id.contentDependency, dependencyManageFragment, DependencyManageFragment.TAG);
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
