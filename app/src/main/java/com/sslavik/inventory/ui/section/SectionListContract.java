package com.sslavik.inventory.ui.section;

import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.ui.base.BaseView;

import java.util.List;

public interface SectionListContract {
    interface View extends BaseView<Presenter>{
        void showLoadProgress();
        void hideLoadProgress();
        void showNoData();
        void showData(List<Section> sectionList);
        void undoDelete(Section section);
        void onSuccessDeleted();
        void onSuccessUndo(Section section);
    }

    interface Presenter{
        void delete(Section section);
        void load();
        void add(Section section);
    }
}
