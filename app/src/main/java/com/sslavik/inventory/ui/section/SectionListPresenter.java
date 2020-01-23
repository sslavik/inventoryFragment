package com.sslavik.inventory.ui.section;

import android.os.AsyncTask;

import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.data.repository.SectionRepository;

import java.util.List;

public class SectionListPresenter implements SectionListContract.Presenter {
    @Override
    public void delete(Section section) {
        SectionRepository.getInstance().delete(section);
    }

    @Override
    public void load() {
        new AsyncTask<Void, Void, List< Section >>(){

            @Override
            protected List<Section> doInBackground(Void... voids) {
                return SectionRepository.getInstance().getList();
            }
        }.execute();
    }

    @Override
    public void add(Section section) {

    }
}
