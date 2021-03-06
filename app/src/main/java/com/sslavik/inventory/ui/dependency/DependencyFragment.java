package com.sslavik.inventory.ui.dependency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sslavik.inventory.R;
import com.sslavik.inventory.adapter.DependencyAdapter;
import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.ui.base.BaseDialogFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class DependencyFragment extends Fragment implements DependencyListContract.View, BaseDialogFragment.OnFinishDialog {

    private static final int REQUEST_CODE_DELETE = 300;
    // DELEGADOS
    // Este objeto recoge los eventos del adapter
    private DependencyAdapter.OnManageDependencyListener onManageDependencyAdapterListener;
    private DependencyListContract.Presenter presenter;



    //INTERFAZ
    interface OnManageDependencyListener {
        void onManageDependency(Dependency dependency, boolean edit);
    }


    // CAMPOS
    public static final String TAG = "DependencyFragment" ;
    private static final int SPAN_COUNT = 3;
    private RecyclerView rvDependency;
    private DependencyAdapter dependencyAdapter ;
    private FloatingActionButton fabAdd;
    private OnManageDependencyListener onManageDependencyListener;
    private LottieAnimationView animationNodata;
    private LottieAnimationView animationLoad;
    Dependency deleted;


    // INSTANCIA DE UN FRAGMENT DINAMICO
    public static Fragment newInstance(Bundle bundle){
        DependencyFragment fragment = new DependencyFragment();
        if( bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }
    // region
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            onManageDependencyListener = (OnManageDependencyListener) context;
        } catch (Exception e){
            throw e;
        }

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_dependency_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_orderbyname:
                Toast.makeText(getActivity(), "Se ha pulsado ordenar por nombre", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        // SE DEBE INDICIAR LA SIGUIENTE LLAMADA PARA QUE LLAME A LOS METODOS QUE CREAN EL MENU
        setHasOptionsMenu(true);
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
        // ANIMATIONS
        animationNodata = view.findViewById(R.id.animation_nodata);
        animationLoad = view.findViewById(R.id.animation_load);
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

    /**
     * Solicita la carga de datos al presentador
     */

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.load();
    }

    private void initFabAdd() {
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onManageDependencyListener.onManageDependency(null,false);
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

    private void showDeleteDialog(Dependency dependency){
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, getString(R.string.title_delete));
        bundle.putString(BaseDialogFragment.MESSSAGE, getString(R.string.message_delete) + dependency.getShortName());
        BaseDialogFragment dialogFragment = BaseDialogFragment.newInstance(bundle);
        dialogFragment.setTargetFragment(DependencyFragment.this, REQUEST_CODE_DELETE);
        dialogFragment.show(getFragmentManager(), BaseDialogFragment.TAG);

        deleted = dependency;

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
                onManageDependencyListener.onManageDependency(dependency, true);
            }
            /**
             * Este método muestra un cuadro de dialogo pidiendo la confirmación del borrado de la dependencia
             * @param dependency
             */
            @Override
            public void onDeleteDependency(Dependency dependency) {
                Toast.makeText(getActivity(), "Intento de borrado en la dependencia :" + dependency.getName(), Toast.LENGTH_SHORT).show();
                showDeleteDialog(dependency);
            }
        };

    }
    private void dependencyDeleted() {
        presenter.delete(deleted);
    }

    //region IMPLEMENTACIÓN DE INTERFACES
    // BASE DIALOG INTERFACE
    @Override
    public void onFinishDialog() {
        // BORRAMOS EN EL REPOSITORY
        dependencyDeleted();
        // BORRAMOS EN EL ADAPTADOR
        presenter.load();

        Snackbar.make(getView(),getString(R.string.snkBarMessageDeleted) + deleted.getShortName(),Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snkBarUndo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        undoDelete(deleted);
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.secondaryLightColor)).show();
    }




    // PRESENTER
    @Override
    public void showLoadProgress() {
        animationNodata.setVisibility(View.INVISIBLE);
        rvDependency.setVisibility(View.INVISIBLE);
        animationLoad.setFrame(0);
        animationLoad.setSpeed(2.6f);
        animationLoad.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadProgress() {
        rvDependency.setVisibility(View.VISIBLE);
        animationLoad.setVisibility(View.GONE);
    }

    @Override
    public void showNoData() {
        animationNodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(List<Dependency> dependencyList) {
        animationNodata.setVisibility(View.INVISIBLE);
        dependencyAdapter.clear();
        dependencyAdapter.load(dependencyList);
    }

    @Override
    public void undoDelete(Dependency dependency) {
        onSuccessUndo(dependency);
    }

    @Override
    public void setPresenter(DependencyListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(int error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessDeleted() {
        dependencyAdapter.delete(deleted);
    }

    @Override
    public void onSuccessUndo(Dependency dependency) {
        dependencyAdapter.add(dependency);
        presenter.add(dependency);
        presenter.load();
    }

    //endregion

}
