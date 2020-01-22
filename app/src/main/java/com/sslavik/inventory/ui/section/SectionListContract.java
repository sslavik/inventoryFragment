package com.sslavik.inventory.ui.section;

import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.ui.base.BaseView;

import java.util.List;

public interface SectionListContract {
    interface View extends BaseView<Presenter>{
        void showLoadProgress();
        void hideLoadProgress();
        void showNoData();
        void showData(List<Dependency> dependencyList);
        void undoDelete(Dependency dependency);
        void onSuccessDeleted();
        void onSuccessUndo(Dependency dependency);
    }

    interface Presenter{
        void delete(Dependency dependency);
        void load();
        void add(Dependency dependency);
    }
}
