package com.sslavik.inventory.ui.dependency;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DependencyManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DependencyManageFragment extends Fragment {

    // CAMPOS
    public static final String TAG = "DependencyManageFragment";
    private EditText edShortName;
    private EditText edLongName;
    private Spinner spInventory;
    private EditText edDescription;

    public DependencyManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DependencyManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DependencyManageFragment newInstance(Bundle bundle) {
        DependencyManageFragment fragment = new DependencyManageFragment();
        if ( bundle != null ){
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dependency_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edShortName = view.findViewById(R.id.edShortName);
        edLongName = view.findViewById(R.id.edLongName);
        edDescription = view.findViewById(R.id.edDescription);
        spInventory = view.findViewById(R.id.spInventory);

        // DEVOLVEMOS EL BUDLE PASADO AL MANAGER
        Bundle bundle = getArguments();

        if(bundle != null) {
            Dependency dependency = bundle.getParcelable("Dependency");
            edShortName.setText(dependency.getShortName());
            edLongName.setText(dependency.getName());
            edDescription.setText(dependency.getDescription());
            spInventory.setSelection(0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
