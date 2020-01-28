package com.sslavik.inventory.ui.section;

import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.data.repository.SectionRepository;

public class SectionManagePresenter implements SectionManageContract.Presenter {

    //DELEGADO
    SectionManageContract.View view;

    public SectionManagePresenter(SectionManageContract.View view) {
        this.view = view;
    }


    @Override
    public void add(Section section) {
        SectionRepository.getInstance().add(section);
    }

    @Override
    public void manage(Section section) {
        SectionRepository.getInstance().edit(section);
    }
}
