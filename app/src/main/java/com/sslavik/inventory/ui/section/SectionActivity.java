package com.sslavik.inventory.ui.section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Section;

public class SectionActivity extends AppCompatActivity {

    // FRAGMENTS IMPLEMENTADOS
    SectionFragment sectionFragment;

    // PRESENTADORES DE LOS FRAGMENTS
    SectionListPresenter sectionListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        startFragment();
    }

    private void startFragment() {

        sectionFragment = (SectionFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentSection);

        if(sectionFragment == null)
            sectionFragment = SectionFragment.newInstance(null);

        getSupportFragmentManager().beginTransaction().replace(R.id.contentSection,sectionFragment).commit();

        sectionListPresenter = new SectionListPresenter(sectionFragment);
        sectionFragment.setPresenter(sectionListPresenter);
    }

}
