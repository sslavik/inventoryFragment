package com.sslavik.inventory.ui.section;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sslavik.inventory.R;
import com.sslavik.inventory.adapter.DependencyAdapter;
import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.data.repository.DependencyRepository;
import com.sslavik.inventory.ui.dependency.DependencyListPresenter;

import java.util.List;

public class SectionManageFragment extends Fragment implements SectionManageContract.View {

    // INTERFACE

    // DELEGADOS
    SectionManageContract.Presenter presenter;

    // FIELDS
    Section section;
    List<Dependency> dependencyList;
    EditText edShortName;
    EditText edLongName;
    EditText edDescription;
    Spinner spDependency;
    FloatingActionButton fabAddManage;


    public static SectionManageFragment newInstance(Bundle bundle) {
        SectionManageFragment fragment = new SectionManageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // INIT FRAGMENT WITH BUNDLE
            section = (Section) getArguments().get("section");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // INSTANCIATE VIEWS
        edShortName = view.findViewById(R.id.edShortName);
        edLongName = view.findViewById(R.id.edLongName);
        edDescription = view.findViewById(R.id.edDescription);
        spDependency = view.findViewById(R.id.spDependency);
        fabAddManage = view.findViewById(R.id.fabManageSection);

        // initValues
        initViewValuesFragment();
        // initFab
        initFabAddManage();
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

    private void initFabAddManage() {
        fabAddManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(section != null){
                    validateSection();
                    presenter.manage(section);
                } else {
                    validateSection();
                    presenter.add(section);
                }
            }
        });
    }

    private void validateSection() {
        if(edShortName.getText().length() > 2 && edLongName.getText().length() != 0 && edDescription.getText().length() != 0){
            section = new Section(null, null, null, null, 0);
            section.setShortName(edShortName.getText().toString());
            section.setName(edLongName.getText().toString());
            section.setDescription(edDescription.getText().toString());
            section.setDependency((Dependency)spDependency.getSelectedItem());
            section.setImageSection(0);
        } else {
            showError(R.string.err_not_correct_section);
        }
    }

    private void initViewValuesFragment() {
        initSpinnerValues();

        if(section != null){
            edShortName.setText(section.getShortName());
            edLongName.setText(section.getName());
            edDescription.setText(section.getDescription());
            spDependency.setSelection(dependencyList.indexOf(section.getDependency()));
        }
    }

    private void initSpinnerValues() {
        // OBTAIN STRING VALUES
        dependencyList = DependencyRepository.getInstance().getList(null);
        int listSize = dependencyList.size();
        String[] dependencyValues = new String[listSize];
        for (int i = 0; i < listSize; i++) {
            dependencyValues[i] = dependencyList.get(i).getName();
        }

        // START ADAPTER
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, dependencyValues);

        // SET ADAPTER TO SPINNER
        spDependency.setAdapter(arrayAdapter);
    }


    // METODOS IMPLEMENTADOS
    @Override
    public void setPresenter(SectionManageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(int error) {

    }
}
