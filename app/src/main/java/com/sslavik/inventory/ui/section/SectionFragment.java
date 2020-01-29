package com.sslavik.inventory.ui.section;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sslavik.inventory.R;
import com.sslavik.inventory.adapter.SectionAdapter;
import com.sslavik.inventory.data.model.Section;

import java.util.List;

public class SectionFragment extends Fragment implements SectionListContract.View{


    // FIELDS
    RecyclerView rvSection;
    FloatingActionButton fabAddSection;
    SectionAdapter sectionAdapter;

    // DELEGADOS
    SectionAdapter.OnManageSection onManageSection;
    SectionListContract.Presenter presenter;

    // FRAGMENT
    SectionManageFragment sectionManageFragment;

    // PRESENTER
    SectionManagePresenter sectionManagePresenter;

    public static SectionFragment newInstance(Bundle bundle) {
        SectionFragment fragment = new SectionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // INSTANCIAMOS CON BUNDLE
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // INSTANCIATE VIEWS
        rvSection = view.findViewById(R.id.rvSection);
        fabAddSection = view.findViewById(R.id.fabAddSection);

        // METHODS OF INIT
        initAddFabSection();
        initRvSection();

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.load();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // METODOS CREADOS

    void showFragmentManageSection(Section section){

        Bundle bundle = null;

        // CHECKS FOR EDIT OR ADD SECTION
        if (section != null) {
            bundle = new Bundle();
            bundle.putSerializable("section", section);
        }

        sectionManageFragment = (SectionManageFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentSectionManage);

        if(sectionManageFragment == null){
            sectionManageFragment = SectionManageFragment.newInstance(bundle);
        }

        FragmentTransaction fragmentTransaction =  getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentSection, sectionManageFragment);
        fragmentTransaction.addToBackStack(null).commit();

        sectionManagePresenter = new SectionManagePresenter(sectionManageFragment);
        sectionManageFragment.setPresenter(sectionManagePresenter);
    }

    private void initRvSection() {

        initAdapterHandler();

        sectionAdapter = new SectionAdapter(getContext(), onManageSection);
        rvSection.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSection.setAdapter(sectionAdapter);


    }
    // ADAPTER HANDLER IMPLEMENTATION

    private void initAdapterHandler() {

        onManageSection = new SectionAdapter.OnManageSection() {
            @Override
            public void OnManageSection(Section section) {
                showFragmentManageSection(section);
            }

            @Override
            public void OnDeleteSection(Section section) {
                // MENSAJE ANTES DE BORRADO
                presenter.delete(section);
                sectionAdapter.delete(section);
            }
        };

    }

    private void initAddFabSection() {
        fabAddSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ABRIMOS EL MANAGER PARA CREAR UNA SECCION
                showFragmentManageSection(null);
            }
        });
    }
    // METODOS IMPLEMENTADOS


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
    public void showData(List<Section> sectionList) {
        sectionAdapter.load(sectionList);
    }

    @Override
    public void undoDelete(Section section) {

    }

    @Override
    public void onSuccessDeleted() {

    }

    @Override
    public void onSuccessUndo(Section section) {

    }

    @Override
    public void setPresenter(SectionListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(int error) {

    }

}
