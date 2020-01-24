package com.sslavik.inventory.ui.section;

import android.os.AsyncTask;

import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.data.repository.SectionRepository;

import java.util.List;

public class SectionListPresenter implements SectionListContract.Presenter {

    // FIELDS
    SectionListContract.View view;

    public SectionListPresenter(SectionListContract.View view) {
        this.view = view;
    }

    @Override
    public void delete(Section section) {
        SectionRepository.getInstance().delete(section);
    }

    @Override
    public void load() {
        new AsyncTask<Void, Void, List< Section >>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showLoadProgress();
            }

            @Override
            protected List<Section> doInBackground(Void... voids) {
                return SectionRepository.getInstance().getList();
            }

            @Override
            protected void onPostExecute(List<Section> sectionList) {
                super.onPostExecute(sectionList);
                view.hideLoadProgress();
                if(sectionList.isEmpty())
                    view.showNoData();
                else
                    view.showData(sectionList);
            }
        }.execute();
    }


    // ESTO HACE DE UNDO
    @Override
    public void add(Section section) {
        SectionRepository.getInstance().add(section);
    }
}
